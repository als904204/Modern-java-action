package part1.ch02.pattern;

import part1.ch01.FilteringApples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        // 전략 패턴
        List<Apple> predicatedGreenApple = filterApples(inventory, new AppleGreenColorPredicate());
        System.out.println("predicatedGreenApple = " + predicatedGreenApple);

        List<Apple> predicatedHeavyWeightApple = filterApples(inventory, new AppleHeavyWeightPredicate());
        System.out.println("predicatedHeavyWeightApple = " + predicatedHeavyWeightApple);

        List<Apple> predicatedHeavyWeightAndRedApple = filterApples(inventory, new AppleRedAndHeavyWeight());
        System.out.println("predicatedHeavyWeightAndRedApple = " + predicatedHeavyWeightAndRedApple);

        // 익명 클래스
        List<Apple> anonymousClass1 = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });

        List<Apple> anonymousClass2 = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight() > 150;
            }
        });

        System.out.println("anonymousClass1 = " + anonymousClass1);
        System.out.println("anonymousClass2 = " + anonymousClass2);

        // 람다 표현식 사용
        List<Apple> lambda = filterApples(inventory, (Apple a) -> "red".equals(a.getColor()));
        System.out.println("lambda = " + lambda);
    }

    private static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}

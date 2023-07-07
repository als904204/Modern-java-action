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

        // 리스트 형식으로 추상화
        List<Apple> list = listFilter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        System.out.println("list = " + list);

        List<Integer> numbers = Arrays.asList(1, 2, 3,4,5,6);
        List<Integer> evenNumbers = listFilter(numbers, (Integer i) -> i % 2 == 0);
        System.out.println("evenNumbers = " + evenNumbers);
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

    // 리스트 형식으로 추상화
    private interface Predicate<T> {
        boolean test(T t);
    }

    private static <T> List<T> listFilter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}

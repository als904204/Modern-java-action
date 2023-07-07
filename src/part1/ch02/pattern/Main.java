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

        List<Apple> predicatedGreenApple = filterApples(inventory, new AppleGreenColorPredicate());
        System.out.println("predicatedGreenApple = " + predicatedGreenApple);

        List<Apple> predicatedHeavyWeightApple = filterApples(inventory, new AppleHeavyWeightPredicate());
        System.out.println("predicatedHeavyWeightApple = " + predicatedHeavyWeightApple);

        List<Apple> predicatedHeavyWeightAndRedApple = filterApples(inventory, new AppleRedAndHeavyWeight());
        System.out.println("predicatedHeavyWeightAndRedApple = " + predicatedHeavyWeightAndRedApple);
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

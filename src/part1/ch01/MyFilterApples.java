package part1.ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class MyFilterApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        );

        // 람다를 사용하지않고 사용
        List<Apple> noLambdaGreenApple = myGreenAppleFilter(inventory);
        System.out.println("noLambdaGreenApple = " + noLambdaGreenApple);

        // 람다를 사용하여 메서드를 파라매터에 넣음
        List<Apple> lambdaGreenApple = filterApples(inventory, MyFilterApples::isGreenApple);
        System.out.println("lambdaGreenApple = " + lambdaGreenApple);

        // 람다를 사용하여 메서드를 파라매터에 넣음
        List<Apple> lambdaHeavyApple = filterApples(inventory, MyFilterApples::isHeavyApple);
        System.out.println("lambdaHeavyApple = " + lambdaHeavyApple);

        // 익명 함수 이용
        // 한번만 사용할 메서드는 따로 정의할 필요가 없음
        List<Apple> lambdaGreenApple2 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        List<Apple> lambdaHeavyApple2 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
        List<Apple> lambdaOrFilter = filterApples(inventory, (Apple a) -> a.getWeight() < 80 || "red".equals(a.getColor()));
        System.out.println("lambdaGreenApple2 = " + lambdaGreenApple2);
        System.out.println("lambdaHeavyApple2 = " + lambdaHeavyApple2);
        System.out.println("lambdaOrFilter = " + lambdaOrFilter);

        // 순차 처리 시간 측정
        long startTime = System.currentTimeMillis();
        List<Apple> streamFilter1 = inventory
                .stream()
                .filter((Apple a) -> a.getWeight() > 150)
                .toList();
        long endTime = System.currentTimeMillis();
        long sequentialTime = endTime - startTime;

        List<Apple> streamFilter2 = inventory
                .stream()
                .filter((Apple a) -> a.getWeight() > 80 || "green".equals(a.getColor()))
                .toList();

        System.out.println("streamFilter1 = " + streamFilter1);
        System.out.println("streamFilter2 = " + streamFilter2);

        // 병렬 처리 시간 측정
        startTime = System.currentTimeMillis();
        List<Apple> list = inventory
                .parallelStream()
                .filter((Apple a) -> a.getWeight() > 150)
                .toList();
        endTime = System.currentTimeMillis();
        long parallelTime = endTime - startTime;

        System.out.println("sequentialTime = " + sequentialTime);
        System.out.println("parallelTime = " + parallelTime);


        System.out.println("==========================");

        List<Apple> streamApplesInventory = IntStream
                .range(0, 200000)
                .mapToObj(i -> new Apple(i + 50, "green"))
                .toList();

        List<Apple> 순차처리 = streamApplesInventory
                .stream()
                .filter((Apple a) -> a.getWeight() > 150)
                .toList();
        long 순차처리속도 = endTime - startTime;

        // 병렬 처리 시간 측정
        startTime = System.currentTimeMillis();
        List<Apple> 병렬처리 = streamApplesInventory
                .parallelStream()
                .filter((Apple a) -> a.getWeight() > 150)
                .toList();
        endTime = System.currentTimeMillis();
        long 병렬처리속도 = endTime - startTime;
        System.out.println("순차처리속도 = " + 순차처리속도);
        System.out.println("병렬처리속도 = " + 병렬처리속도);


    }

    // 메서드 중복 해결
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    // 파라매터에 넣을 함수1
    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    // 파라매터에 넣을 함수2
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    // 메서드 중복 1
    public static List<Apple> myGreenAppleFilter(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }


    // 메서드 중복 2
    public static List<Apple> myHeavyAppleFilter(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static class Apple {
        private int weight = 0;

        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color='%s', weight=%d}", color, weight);
        }

    }
}

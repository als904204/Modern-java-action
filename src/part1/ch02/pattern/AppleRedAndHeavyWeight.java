package part1.ch02.pattern;

public class AppleRedAndHeavyWeight implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150 && "green".equals(apple.getColor());
    }

}

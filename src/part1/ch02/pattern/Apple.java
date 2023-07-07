package part1.ch02.pattern;

public class Apple {
    int weight;
    String color;

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("Apple{color=%s, weight=%d}", color, weight);
    }

}

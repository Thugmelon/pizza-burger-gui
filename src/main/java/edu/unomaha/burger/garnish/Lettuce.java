package edu.unomaha.burger.garnish;

public class Lettuce extends BurgerGarnish {
    @Override
    public String toString() {
        return "Lettuce";
    }

    @Override
    public Double getPrice() {
        return 0.25;
    }

    @Override
    public String toNiceString() {
        return "Garnish: Lettuce ($0.25)";
    }
}

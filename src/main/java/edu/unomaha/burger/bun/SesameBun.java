package edu.unomaha.burger.bun;

public class SesameBun extends BurgerBun {
    @Override
    public String toString() {
        return "Sesame Bun";
    }

    @Override
    public Double getPrice() {
        return 0.75;
    }

    @Override
    public String toNiceString() {
        return "Bun: Sesame ($0.75)";
    }
}

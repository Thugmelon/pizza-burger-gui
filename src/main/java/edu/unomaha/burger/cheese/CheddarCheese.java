package edu.unomaha.burger.cheese;

public class CheddarCheese extends BurgerCheese {
    @Override
    public String toString() {
        return "Cheddar Cheese";
    }

    @Override
    public Double getPrice() {
        return 0.60;
    }

    @Override
    public String toNiceString() {
        return "Cheese: Cheddar ($0.60)";
    }
}

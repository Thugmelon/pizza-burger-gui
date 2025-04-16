package edu.unomaha.burger.patty;

public class BeefPatty extends BurgerPatty {
    @Override
    public String toString() {
        return "Beef Patty";
    }

    @Override
    public Double getPrice() {
        return 1.50;
    }

    @Override
    public String toNiceString() {
        return "Patty: Beef ($1.50)";
    }
}

package edu.unomaha.burger;

import java.util.ArrayList;
import java.util.Collections;

import edu.unomaha.pizza.AbstractMenuItem;
import edu.unomaha.pizza.MenuItem;
import edu.unomaha.burger.bun.BurgerBun;
import edu.unomaha.burger.patty.BurgerPatty;
import edu.unomaha.burger.cheese.BurgerCheese;
import edu.unomaha.burger.garnish.BurgerGarnish;

public class Burger extends AbstractMenuItem {
    private BurgerBun bun;
    private ArrayList<BurgerPatty> patties;
    private ArrayList<BurgerCheese> cheeses;
    private ArrayList<BurgerGarnish> garnishes;
    private ArrayList<AbstractMenuItem> burgerComponents;

    public Burger() {
        this.patties = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.garnishes = new ArrayList<>();
        this.burgerComponents = new ArrayList<>();
    }

    public void setBun(BurgerBun bun) {
        this.bun = bun;
        this.burgerComponents.add(bun);
    }

    public void addPatty(BurgerPatty patty) {
        this.patties.add(patty);
        this.burgerComponents.add(patty);
    }

    public void addCheese(BurgerCheese cheese) {
        this.cheeses.add(cheese);
        this.burgerComponents.add(cheese);
    }

    public void addGarnish(BurgerGarnish garnish) {
        this.garnishes.add(garnish);
        this.burgerComponents.add(garnish);
    }

    public ArrayList<AbstractMenuItem> getComponents() {
        return burgerComponents;
    }

    @Override
    public Double getPrice() {
        Double total = 0.0;
        for (MenuItem m : burgerComponents) {
            total += m.getPrice();
        }
        return total;
    }

    @Override
    public String toNiceString() {
        return "Burger is: " + toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (bun != null) sb.append(bun).append(", ");
        for (BurgerPatty p : patties) sb.append(p).append(", ");
        for (BurgerCheese c : cheeses) sb.append(c).append(", ");
        for (BurgerGarnish g : garnishes) sb.append(g).append(", ");
        return sb.toString().replaceAll(", $", "");
    }
}

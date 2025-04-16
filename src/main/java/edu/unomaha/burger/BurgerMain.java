package edu.unomaha.burger;

import edu.unomaha.burger.bun.SesameBun;
import edu.unomaha.burger.patty.BeefPatty;
import edu.unomaha.burger.cheese.CheddarCheese;
import edu.unomaha.burger.garnish.Lettuce;

public class BurgerMain {
    public static void main(String[] args) {
        Burger burger = new Burger();
        burger.setBun(new SesameBun());
        burger.addPatty(new BeefPatty());
        burger.addCheese(new CheddarCheese());
        burger.addGarnish(new Lettuce());

        System.out.println(burger.toNiceString());
        System.out.printf("Total: $%.2f\n", burger.getPrice());
    }
}


package edu.unomaha.pizza;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.unomaha.Order;
import edu.unomaha.pizza.crust.ThinCrust;
import edu.unomaha.pizza.crust.ThickCrust;
import edu.unomaha.pizza.sauce.TomatoSauce;
import edu.unomaha.pizza.sauce.AlfredoSauce;
import edu.unomaha.pizza.topping.MushroomTopping;
import edu.unomaha.pizza.topping.PepperoniTopping;
import edu.unomaha.pizza.AbstractMenuItem;
import edu.unomaha.burger.Burger;
import edu.unomaha.burger.patty.BeefPatty;
import edu.unomaha.burger.garnish.Lettuce;

/**
 * Unit tests for pizza and burger pricing logic using Order.
 * Follows Google Java Style Guide and Javadoc conventions.
 */
public class PizzaOrderTest {

    /**
     * Check that adding a topping increases pizza price by the topping's cost.
     */
    @Test
    public void testPizzaToppingPriceIncrease() {
        Pizza pizza = new Pizza();
        pizza.setCrust(new ThinCrust());
        pizza.setSauce(new TomatoSauce());
        double initialPrice = pizza.getPrice();
        pizza.addTopping(new PepperoniTopping());
        assertEquals(initialPrice + new PepperoniTopping().getPrice(), pizza.getPrice(), 0.01);
    }

    /**
     * Check that pizza price equals sum of crust, sauce, and toppings.
     */
    @Test
    public void testPizzaPriceIsSumOfComponents() {
        Pizza pizza = new Pizza();
        pizza.setCrust(new ThickCrust());
        pizza.setSauce(new AlfredoSauce());
        PepperoniTopping topping1 = new PepperoniTopping();
        MushroomTopping topping2 = new MushroomTopping();
        pizza.addTopping(topping1);
        pizza.addTopping(topping2);

        double expectedPrice = pizza.getCrust().getPrice() + pizza.getSauce().getPrice() +
                               topping1.getPrice() + topping2.getPrice();
        assertEquals(expectedPrice, pizza.getPrice(), 0.01);
    }

    /**
     * Check that order total increases after adding a pizza.
     */
    @Test
    public void testOrderTotalAfterAddingPizza() {
        Pizza pizza = new Pizza();
        pizza.setCrust(new ThinCrust());
        pizza.setSauce(new TomatoSauce());
        pizza.addTopping(new MushroomTopping());

        Order order = new Order();
        order.addItem(pizza);

        assertEquals(pizza.getPrice(), order.getTotalPrice(), 0.01);
    }

    /**
     * Check that burger price is calculated after components are added.
     */
    @Test
    public void testBurgerPriceCalculation() {
        Burger burger = new Burger();
        burger.addPatty(new BeefPatty());
        burger.addGarnish(new Lettuce());
        double expected = burger.getPrice();
        assertTrue(expected > 0);
    }

    /**
     * Check that order total increases correctly after adding two pizzas.
     */
    @Test
    public void testOrderPriceAfterTwoPizzas() {
        Pizza pizza1 = new Pizza();
        pizza1.setCrust(new ThinCrust());
        pizza1.setSauce(new TomatoSauce());
        pizza1.addTopping(new PepperoniTopping());

        Pizza pizza2 = new Pizza();
        pizza2.setCrust(new ThickCrust());
        pizza2.setSauce(new AlfredoSauce());
        pizza2.addTopping(new MushroomTopping());

        Order order = new Order();
        order.addItem(pizza1);
        order.addItem(pizza2);

        double expected = pizza1.getPrice() + pizza2.getPrice();
        assertEquals(expected, order.getTotalPrice(), 0.01);
    }
}

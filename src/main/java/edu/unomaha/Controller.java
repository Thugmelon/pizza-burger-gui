package edu.unomaha;

import edu.unomaha.pizza.AbstractMenuItem;
import edu.unomaha.pizza.Pizza;
import edu.unomaha.burger.Burger;
import edu.unomaha.pizza.crust.*;
import edu.unomaha.pizza.sauce.*;
import edu.unomaha.pizza.topping.*;
import edu.unomaha.burger.bun.*;
import edu.unomaha.burger.patty.*;
import edu.unomaha.burger.cheese.*;
import edu.unomaha.burger.garnish.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML private ComboBox<PizzaCrust> pizzaCrustBox;
    @FXML private ComboBox<PizzaSauce> pizzaSauceBox;
    @FXML private ListView<PizzaTopping> pizzaToppingList;

    @FXML private ComboBox<BurgerBun> burgerBunBox;
    @FXML private ListView<BurgerPatty> burgerPattyList;
    @FXML private ListView<BurgerCheese> burgerCheeseList;
    @FXML private ListView<BurgerGarnish> burgerGarnishList;

    @FXML private VBox orderDisplayBox;
    @FXML private Label subtotalLabel;
    @FXML private Label totalLabel;

    private final List<AbstractMenuItem> orderItems = new ArrayList<>();

    public void initialize() {
        pizzaCrustBox.getItems().addAll(new ThinCrust(), new ThickCrust());
        pizzaSauceBox.getItems().addAll(new TomatoSauce(), new AlfredoSauce());

        pizzaToppingList.getItems().addAll(
            new PepperoniTopping(), new SausageTopping(), new MushroomTopping(),
            new MozzarellaTopping(), new AsiagoTopping(), new PepperTopping()
        );
        pizzaToppingList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        burgerBunBox.getItems().addAll(new SesameBun());
        burgerPattyList.getItems().addAll(new BeefPatty());
        burgerCheeseList.getItems().addAll(new CheddarCheese());
        burgerGarnishList.getItems().addAll(new Lettuce());

        burgerPattyList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        burgerCheeseList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        burgerGarnishList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        updateTotals();
    }

    @FXML
    private void handleAddPizza() {
        Pizza pizza = new Pizza();
        if (pizzaCrustBox.getValue() != null) pizza.setCrust(pizzaCrustBox.getValue());
        if (pizzaSauceBox.getValue() != null) pizza.setSauce(pizzaSauceBox.getValue());

        List<PizzaTopping> toppings = pizzaToppingList.getSelectionModel().getSelectedItems();
        for (int i = 0; i < Math.min(toppings.size(), 4); i++) {
            pizza.addTopping(toppings.get(i));
        }

        orderItems.add(pizza);
        orderDisplayBox.getChildren().add(new Label(pizza.toNiceString() + " - $" + String.format("%.2f", pizza.getPrice())));
        updateTotals();
    }

    @FXML
    private void handleAddBurger() {
        Burger burger = new Burger();
        if (burgerBunBox.getValue() != null) burger.setBun(burgerBunBox.getValue());

        List<BurgerPatty> patties = burgerPattyList.getSelectionModel().getSelectedItems();
        for (int i = 0; i < Math.min(patties.size(), 4); i++) {
            burger.addPatty(patties.get(i));
        }

        List<BurgerCheese> cheeses = burgerCheeseList.getSelectionModel().getSelectedItems();
        for (int i = 0; i < Math.min(cheeses.size(), 4); i++) {
            burger.addCheese(cheeses.get(i));
        }

        List<BurgerGarnish> garnishes = burgerGarnishList.getSelectionModel().getSelectedItems();
        for (int i = 0; i < Math.min(garnishes.size(), 4); i++) {
            burger.addGarnish(garnishes.get(i));
        }

        orderItems.add(burger);
        orderDisplayBox.getChildren().add(new Label(burger.toNiceString() + " - $" + String.format("%.2f", burger.getPrice())));
        updateTotals();
    }

    @FXML
    private void handleViewReceipt() {
        orderItems.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
        Alert receiptAlert = new Alert(Alert.AlertType.INFORMATION);
        receiptAlert.setTitle("Receipt");

        StringBuilder receipt = new StringBuilder();
        double total = 0.0;
        for (AbstractMenuItem item : orderItems) {
            receipt.append(item.toNiceString()).append(" - $").append(String.format("%.2f", item.getPrice())).append("\n");
            total += item.getPrice();
        }

        receipt.append("\nTotal: $").append(String.format("%.2f", total));
        receiptAlert.setContentText(receipt.toString());
        receiptAlert.showAndWait();
    }

    private void updateTotals() {
        double total = orderItems.stream().mapToDouble(AbstractMenuItem::getPrice).sum();
        subtotalLabel.setText("Subtotal: $" + String.format("%.2f", total));
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }
}

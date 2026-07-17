package decorator;

import pizza.Pizza;

public class BasePizzaOrder implements PizzaOrder {
    private final Pizza pizza;

    public BasePizzaOrder(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.toString();
    }

    @Override
    public int getPrice() {
        return pizza.getTotalPrice();
    }
}

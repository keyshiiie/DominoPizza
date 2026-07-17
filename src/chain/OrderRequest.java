package chain;

import pizza.*;

import java.util.Map;

public class OrderRequest {
    private final Dough dough;
    private final Size size;
    private final Sauce sauce;
    private final Map<Topping, Integer> toppings;
    private final int price;

    public OrderRequest(Pizza pizza) {
        this.dough = pizza.getDough();
        this.size = pizza.getSize();
        this.sauce = pizza.getSauce();
        this.toppings = pizza.getToppings();
        this.price = pizza.getTotalPrice();
    }

    public Dough getDough() {
        return dough;
    }

    public Size getSize() {
        return size;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public Map<Topping, Integer> getToppings() {
        return toppings;
    }

    public int getPrice() {
        return price;
    }
}

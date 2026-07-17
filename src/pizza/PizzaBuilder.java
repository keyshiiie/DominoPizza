package pizza;
import strategy.CookingStrategy;
import strategy.ThickDoughCookingStrategy;
import strategy.ThinDoughCookingStrategy;
import strategy.VeganCookingStrategy;

import java.util.HashMap;
import java.util.Map;

public class PizzaBuilder {
    private final Dough dough;
    private final Size size;
    private Sauce sauce;
    private final Map<Topping, Integer> toppings;
    private CookingStrategy cookingStrategy;

    public PizzaBuilder(Dough dough, Size size){
        if(dough == null || size == null) {
            throw new IllegalArgumentException("Вы не указала вид теста и размер пиццы!");
        }
        this.dough = dough;
        this.size = size;
        this.toppings = new HashMap<>();
        this.cookingStrategy = selectStrategy(dough);
    }

    private CookingStrategy selectStrategy(Dough dough){
        return switch (dough){
            case THIN -> new ThinDoughCookingStrategy();
            case THICK -> new ThickDoughCookingStrategy();
            case KEFIR -> new VeganCookingStrategy();
        };
    }

    public PizzaBuilder sauce(Sauce sauce){
        this.sauce = sauce;
        return this;
    }

    public PizzaBuilder addTopping(Topping topping){
        if(topping == null){
            throw new IllegalArgumentException("В пицце обязательно должна быть начинка!");
        }
        toppings.put(topping, toppings.getOrDefault(topping, 0) + 1);
        return this;
    }

    public String getCurrentToppingsDisplay() {
        if (toppings.isEmpty()) {
            return "пока нет начинок";
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<Topping, Integer> entry : toppings.entrySet()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(entry.getKey().getDisplayName())
                    .append(" x")
                    .append(entry.getValue());
            first = false;
        }
        return sb.toString();
    }

    public Pizza build(){
        return new Pizza(dough, size, sauce, toppings, cookingStrategy);
    }
}

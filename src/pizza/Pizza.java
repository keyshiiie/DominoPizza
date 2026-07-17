package pizza;
import strategy.CookingStrategy;

import java.util.Collections;
import java.util.Map;

public class Pizza {
    private final Dough dough;
    private final Size size;
    private final Sauce sauce;
    private final Map<Topping, Integer> toppings;
    private final CookingStrategy cookingStrategy;

    Pizza(Dough dough, Size size, Sauce sauce, Map<Topping, Integer> toppings, CookingStrategy cookingStrategy){
        this.dough = dough;
        this.size = size;
        this.sauce = sauce;

        this.toppings = toppings == null ? Collections.emptyMap() : Collections.unmodifiableMap(toppings);
        this.cookingStrategy = cookingStrategy;
    }

    public CookingStrategy getCookingStrategy() {
        return cookingStrategy;
    }

    public String cook(){
        return "Способ приготоления: " + cookingStrategy.getDescription();
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

    public int getTotalPrice(){
        int basePrice = switch(size){
            case SMALL -> 300;
            case MEDIUM -> 400;
            case LARGE -> 500;
        };
        int toppingsPrice = 0;
        for (Map.Entry<Topping, Integer> entry : toppings.entrySet()) {
            toppingsPrice += entry.getKey().getPrice() * entry.getValue();
        }

        return basePrice + toppingsPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("Ваш заказ:\n");
        sb.append("Тесто: ").append(dough.getDisplayName()).append("\n");
        sb.append("Размер: ").append(size).append("\n");

        if (sauce != null) {
            sb.append("Соус: ").append(sauce.getDisplayName()).append("\n");
        } else {
            sb.append("Соус: БЕЗ СОУСА\n");
        }

        if (toppings.isEmpty()) {
            sb.append("Начинки: БЕЗ НАЧИНОК\n");
        } else {
            sb.append("Начинки:\n");
            for (Map.Entry<Topping, Integer> entry : toppings.entrySet()) {
                sb.append("  - ")
                        .append(entry.getKey().getDisplayName())
                        .append(" x")
                        .append(entry.getValue())
                        .append("\n");
            }
        }

        sb.append("Способ приготовления: ").append(cookingStrategy.getDescription()).append("\n");
        sb.append("Итоговая стоимость: ")
                .append(getTotalPrice())
                .append(" руб.\n");
        sb.append("=========================================");

        return sb.toString();
    }
}

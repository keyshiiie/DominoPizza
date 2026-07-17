package chain;

import pizza.Dough;
import pizza.Topping;

public class IngredientAvailabilityHandler extends BaseOrderHandler {
    @Override
    protected  boolean process(OrderRequest request){
        System.out.println("Проверяем ингредиенты на складе...");

        for (Topping topping : request.getToppings().keySet()) {
            if (!isToppingAvailable(topping)) {
                System.out.println("Ошибка! Ингредиент:  '" +
                        topping.getDisplayName() + " закончился!");
                return false;
            }
        }

        if (!isDoughAvailable(request.getDough())) {
            System.out.println("Ошибка! Тесто '" +
                    request.getDough().getDisplayName() + "' закончилось!");
            return false;
        }

        System.out.println("Все ингредиенты есть в наличии!");
        return true;
    }

    // моки
    private boolean isToppingAvailable(Topping topping) {
        return topping != Topping.PEPPER;
    }

    private boolean isDoughAvailable(Dough dough) {
        return dough != Dough.KEFIR;
    }
}

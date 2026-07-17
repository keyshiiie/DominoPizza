package decorator;

public class ExpressCookingDecorator extends PizzaDecorator {
    private static final int PRICE = 200;
    private static final String SERVICE_NAME = "Вне очереди";

    public ExpressCookingDecorator(PizzaOrder wrappedOrder) {
        super(wrappedOrder);
    }

    @Override
    public String getDescription() {
        return wrappedOrder.getDescription() +
                "\n  + " + SERVICE_NAME + " (+" + PRICE + " руб.)";
    }

    @Override
    public int getPrice() {
        return wrappedOrder.getPrice() + PRICE;
    }
}
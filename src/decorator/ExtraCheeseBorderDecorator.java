package decorator;

public class ExtraCheeseBorderDecorator extends PizzaDecorator {
    private static final int PRICE = 50;
    private static final String SERVICE_NAME = "Сырный бортик";

    public ExtraCheeseBorderDecorator(PizzaOrder wrappedOrder) {
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
package decorator;

public abstract class PizzaDecorator implements PizzaOrder {
    protected PizzaOrder wrappedOrder;

    public PizzaDecorator(PizzaOrder wrappedOrder) {
        this.wrappedOrder = wrappedOrder;
    }

    @Override
    public String getDescription() {
        return wrappedOrder.getDescription();
    }

    @Override
    public int getPrice() {
        return wrappedOrder.getPrice();
    }
}
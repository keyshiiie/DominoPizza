package chain;

public interface OrderHandler {
    void setNext(OrderHandler next);
    boolean handle(OrderRequest request);
}

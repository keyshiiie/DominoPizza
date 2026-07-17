package proxy;

import pizza.Pizza;

public interface OrderLogger {
    void logOrder(Pizza pizza, String orderNumber, String receivedTime, String readyTime);
}

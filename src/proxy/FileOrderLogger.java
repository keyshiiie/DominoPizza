package proxy;

import pizza.Pizza;

public class FileOrderLogger implements OrderLogger{
    @Override
    public void logOrder(Pizza pizza, String orderNumber, String receivedTime, String readyTime) {
        System.out.println("Заказ #" + orderNumber + " записан в журнал");
        System.out.println("Состав: " + pizza.toString());
        System.out.println("Получен: " + receivedTime);
        System.out.println("Готов к: " + readyTime);
    }
}

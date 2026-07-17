package chain;

public class KitchenCapacityHandler extends BaseOrderHandler {
    private static final int MAX_ORDERS_PER_HOUR = 10;
    private static int currentOrdersInLastHour = 8;

    @Override
    protected boolean process(OrderRequest request) {
        System.out.println("Проверка загрузки кухни...");

        if (currentOrdersInLastHour >= MAX_ORDERS_PER_HOUR) {
            System.out.println("Ошибка! кухня перегружена! " +
                    "Максимум " + MAX_ORDERS_PER_HOUR + " заказов в час.");
            return false;
        }

        currentOrdersInLastHour++;
        System.out.println("Кухня готова принять заказ. " +
                "Текущая загрузка: " + currentOrdersInLastHour + "/" + MAX_ORDERS_PER_HOUR);
        return true;
    }
}
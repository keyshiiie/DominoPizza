package chain;

public class DeliveryAvailabilityHandler extends BaseOrderHandler {
    private static final int MAX_DELIVERIES = 10;
    private static int currentDeliveries = 4;

    @Override
    protected boolean process(OrderRequest request){
        System.out.println("Проверка наличия курьеров...");

        if (currentDeliveries >= MAX_DELIVERIES) {
            System.out.println("Ошибка! нет свободных курьеров!");
            return false;
        }

        currentDeliveries++;
        System.out.println("Курьер назначен. Свободных курьеров: " +
                (MAX_DELIVERIES - currentDeliveries));
        return true;
    }

}

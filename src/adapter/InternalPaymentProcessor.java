package adapter;

public class InternalPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean pay(int amount, String currency) {
        System.out.println("[ВНУТРЕННЯЯ СИСТЕМА] Оплата " + amount + " " + currency);
        System.out.println("Статус: УСПЕШНО");
        return true;
    }
}
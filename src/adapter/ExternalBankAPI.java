package adapter;

public class ExternalBankAPI {
    public String makeTransaction(String cardNumber, double amount, String description) {
        System.out.println("[ВНЕШНИЙ БАНК] Транзакция по карте " + cardNumber);
        System.out.println("Сумма: " + amount + " руб.");
        System.out.println("Описание: " + description);
        System.out.println("Статус: ОДОБРЕНО");
        return "TX-" + System.currentTimeMillis();
    }
}
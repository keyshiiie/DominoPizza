package adapter;

public class BankAdapter implements PaymentProcessor {
    private final ExternalBankAPI bankAPI;
    private final String cardNumber;

    public BankAdapter(ExternalBankAPI bankAPI, String cardNumber) {
        this.bankAPI = bankAPI;
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean pay(int amount, String currency) {
        String description = "Оплата заказа в пиццерии ДоМиНо";
        String result = bankAPI.makeTransaction(cardNumber, amount, description);
        return result != null && !result.isEmpty();
    }
}
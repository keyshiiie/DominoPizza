package decorator;

public enum ExtraService {
    CHEESE_BORDER("Сырный бортик", 50),
    EXPRESS_COOKING("Вне очереди", 200);

    private final String displayName;
    private final int price;

    ExtraService(String displayName, int price) {
        this.displayName = displayName;
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return displayName + " (" + price + " руб.)";
    }
}
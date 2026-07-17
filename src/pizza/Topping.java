package pizza;

public enum Topping {
    CHEESE("Сыр", 50),
    MUSHROOMS("Грибы", 80),
    CHICKEN("Курица", 130),
    BACON("Бекон", 120),
    PEPPER("Перец", 40);

    private final String displayName;
    private final int price;

    Topping(String displayName, int price){
        this.displayName = displayName;
        this.price = price;
    }

    public String getDisplayName(){
        return displayName;
    }

    public int getPrice(){
        return  price;
    }

    @Override
    public String toString(){
        return displayName;
    }
}

package pizza;

public enum Sauce {
    TOMATO("Томатный"),
    CREAM("Сливочный"),
    BBQ("Барбекю");

    private final String displayName;

    Sauce(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }

    @Override
    public String toString(){
        return displayName;
    }
}

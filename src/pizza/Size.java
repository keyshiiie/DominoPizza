package pizza;

public enum Size {
    SMALL("Маленькая", 25),
    MEDIUM("Средняя", 30),
    LARGE("Большая", 40);

    private final String displayName;
    private final int diameter;

    Size(String displayName, int diameter){
        this.displayName = displayName;
        this.diameter = diameter;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getDiameter(){
        return diameter;
    }

    @Override
    public String toString(){
        return displayName + " (" + diameter + " см)";
    }
}

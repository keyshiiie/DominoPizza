package pizza;

public enum Dough {
    THIN("Тонкое"),
    THICK("Толстое"),
    KEFIR("На кефире");

    private final String displayName;

    Dough(String displayName){
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

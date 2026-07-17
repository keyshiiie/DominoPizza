package strategy;

public class ThinDoughCookingStrategy implements CookingStrategy {
    @Override
    public int getCookingTime(){
        return 10;
    }

    @Override
    public int getTemperature(){
        return 300;
    }

    @Override
    public String getDescription(){
        return "10 мин при 300°C";
    }
}

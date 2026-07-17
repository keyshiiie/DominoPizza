package strategy;

public class VeganCookingStrategy implements CookingStrategy {
    @Override
    public int getCookingTime(){
        return 12;
    }

    @Override
    public int getTemperature(){
        return 280;
    }

    @Override
    public String getDescription(){
        return "12 мин при 280°C";
    }
}

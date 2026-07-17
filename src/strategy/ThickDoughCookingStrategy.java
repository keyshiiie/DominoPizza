package strategy;

public class ThickDoughCookingStrategy implements CookingStrategy {
    @Override
    public int getCookingTime() {
        return 15;
    }

    @Override
    public int getTemperature() {
        return 250;
    }

    @Override
    public String getDescription() {
        return "15 мин при 250°C";
    }
}
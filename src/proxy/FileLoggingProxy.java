package proxy;

import pizza.Pizza;
import pizza.Topping;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

public class FileLoggingProxy implements OrderLogger {
    private final FileOrderLogger logger;
    private final String logFilePath;
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public FileLoggingProxy(FileOrderLogger logger, String logFilePath) {
        this.logger = logger;
        this.logFilePath = logFilePath;
    }

    @Override
    public void logOrder(Pizza pizza, String orderNumber, String receivedTime, String readyTime) {
        logger.logOrder(pizza, orderNumber, receivedTime, readyTime);

        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            writer.println("=========================================");
            writer.println("ЗАКАЗ #" + orderNumber);
            writer.println("Время получения: " + receivedTime);
            writer.println("Время готовности: " + readyTime);
            writer.println("-----------------------------------------");
            writer.println("Тесто: " + pizza.getDough().getDisplayName());
            writer.println("Размер: " + pizza.getSize().getDisplayName());
            writer.println("Соус: " + (pizza.getSauce() != null ? pizza.getSauce().getDisplayName() : "БЕЗ СОУСА"));

            if (pizza.getToppings().isEmpty()) {
                writer.println("Начинки: БЕЗ НАЧИНОК");
            } else {
                writer.println("Начинки:");
                for (java.util.Map.Entry<Topping, Integer> entry : pizza.getToppings().entrySet()) {
                    writer.println("  - " + entry.getKey().getDisplayName() + " x" + entry.getValue());
                }
            }

            writer.println("Режим приготовления: " + pizza.getCookingStrategy().getDescription());
            writer.println("Итоговая стоимость: " + pizza.getTotalPrice() + " руб.");
            writer.println("=========================================");
            writer.println();

            System.out.println("Заказ #" + orderNumber + " записан в файл: " + logFilePath);

        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}
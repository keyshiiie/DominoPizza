package proxy;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderNumberGenerator {
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String COUNTER_FILE = "order_counter.txt";
    private static int counter = loadCounter();

    private static int loadCounter() {
        try (BufferedReader reader = new BufferedReader(new FileReader(COUNTER_FILE))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Создан новый файл счетчика заказов");
        }
        return 1000;
    }

    private static void saveCounter() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COUNTER_FILE))) {
            writer.println(counter);
        } catch (IOException e) {
            System.err.println("Не удалось сохранить счетчик: " + e.getMessage());
        }
    }

    public static String generateOrderNumber() {
        int number = counter++;
        saveCounter();
        return String.valueOf(number);
    }

    public static String getCurrentTime() {
        return LocalDateTime.now().format(formatter);
    }

    public static String getReadyTime(int cookingTimeMinutes) {
        return LocalDateTime.now().plusMinutes(cookingTimeMinutes).format(formatter);
    }
}
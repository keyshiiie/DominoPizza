import chain.*;
import decorator.*;
import pizza.*;
import proxy.FileLoggingProxy;
import proxy.FileOrderLogger;
import proxy.OrderNumberGenerator;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Пиццерию \"ДоМиНо\"!");
        System.out.println("=========================================\n");

        // Шаг 1: Тесто
        System.out.println("Шаг 1. Выберите тесто (1-ТОНКОЕ, 2-ТОЛСТОЕ, 3-НА КЕФИРЕ):");
        int doughChoice = Integer.parseInt(scanner.nextLine());
        Dough dough = Dough.values()[doughChoice - 1];

        // Шаг 2: Размер
        System.out.println("Шаг 2. Выберите размер (1-МАЛЕНЬКАЯ, 2-СРЕДНЯЯ, 3-БОЛЬШАЯ):");
        int sizeChoice = Integer.parseInt(scanner.nextLine());
        Size size = Size.values()[sizeChoice - 1];

        // Создаем билдер
        PizzaBuilder builder = new PizzaBuilder(dough, size);

        // Шаг 3: Соус (опционально)
        System.out.println("Шаг 3. Выберите соус (1-ТОМАТНЫЙ, 2-СЛИВОЧНЫЙ, 3-БАРБЕКЮ, 4-БЕЗ СОУСА):");
        int sauceChoice = Integer.parseInt(scanner.nextLine());
        if (sauceChoice >= 1 && sauceChoice <= 3) {
            builder.sauce(Sauce.values()[sauceChoice - 1]);
        }

        // Шаг 4: Начинки (цикл)
        System.out.println("Шаг 4. Добавление начинок (0 - завершить):");
        while (true) {
            System.out.println("\nДоступные начинки:");
            System.out.println("1 - СЫР (50 руб.)");
            System.out.println("2 - ГРИБЫ (70 руб.)");
            System.out.println("3 - КУРИЦА (120 руб.)");
            System.out.println("4 - БЕКОН (130 руб.)");
            System.out.println("5 - ПЕРЕЦ (40 руб.)");
            System.out.println("0 - ЗАВЕРШИТЬ");
            System.out.print("Ваш выбор: ");

            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                System.out.println("Выбор завершен.");
                break;
            }
            if (choice >= 1 && choice <= 5) {
                Topping topping = Topping.values()[choice - 1];
                builder.addTopping(topping);
                System.out.println("Добавлено: " + topping.getDisplayName());
                System.out.println("Текущий состав: " + builder.getCurrentToppingsDisplay());
            }
        }

        // Готовим пиццу
        Pizza pizza = builder.build();

        // Создаем заказ
        PizzaOrder order = new BasePizzaOrder(pizza);

        // Шаг 5: Дополнительные услуги
        System.out.println("Шаг 5. Дополнительные услуги (0 - завершить):");
        while(true){
            System.out.println("\nДоступные услуги:");
            ExtraService[] services = ExtraService.values();
            for (int i = 0; i < services.length; i++) {
                System.out.println((i + 1) + " - " + services[i]);
            }
            System.out.println("0 - ЗАВЕРШИТЬ");
            System.out.print("Ваш выбор: ");

            int choice = Integer.parseInt((scanner.nextLine()));
            if(choice == 0){
                System.out.println("Выбор завершен.");
                break;
            }
            if(choice >= 1 && choice <= services.length){
                ExtraService selected = services[choice - 1];
                order = switch (selected) {
                    case CHEESE_BORDER -> new ExtraCheeseBorderDecorator(order);
                    case EXPRESS_COOKING -> new ExpressCookingDecorator(order);
                };
                System.out.println("Добавлено: " + selected.getDisplayName());
                System.out.println("Текущая стоимость: " + order.getPrice() + " руб.");
            } else {
                System.out.println("Ошибка: введите число от 0 до " + services.length);
            }
        }

        OrderRequest request = new OrderRequest(pizza);
        OrderHandler ingredientHandler = new IngredientAvailabilityHandler();
        OrderHandler kitchenHandler = new KitchenCapacityHandler();
        OrderHandler deliveryHandler = new DeliveryAvailabilityHandler();

        ingredientHandler.setNext(kitchenHandler);
        kitchenHandler.setNext(deliveryHandler);

        System.out.println("\nОбработка заказа...");
        boolean isOrderAccepted = ingredientHandler.handle(request);

        System.out.println("\n " + pizza.cook());
        if (isOrderAccepted) {
            System.out.println("\n=========================================");
            System.out.println("📝 Логирование заказа на кухню...");
            System.out.println("=========================================");

            // Генерируем данные для заказа
            String orderNumber = OrderNumberGenerator.generateOrderNumber();
            String receivedTime = OrderNumberGenerator.getCurrentTime();
            int cookingTime = pizza.getCookingStrategy().getCookingTime();
            String readyTime = OrderNumberGenerator.getReadyTime(cookingTime);

            // Создаем реальный логгер
            FileOrderLogger realLogger = new FileOrderLogger();

            // Создаем прокси с записью в файл "kitchen_orders.txt"
            FileLoggingProxy fileLogger = new FileLoggingProxy(realLogger, "kitchen_orders.txt");

            // Логируем заказ через прокси
            fileLogger.logOrder(pizza, orderNumber, receivedTime, readyTime);

            System.out.println("Заказ принят! Отправляем на кухню.");
            System.out.println("\n" + order.getDescription());
            System.out.println("\nЗаказ передан на кухню! Приятного аппетита!");
        } else {
            System.out.println("Заказ отклонен. Пожалуйста, повторите заказ, выбрав другие ингредиенты.");
        }

        scanner.close();
    }
}
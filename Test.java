import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        // Создаем менеджер инвентаря
        InventoryManager inventoryManager = new InventoryManager();

        // 1. Добавляем продукты
        Product milk = new PerishableProduct("Milk", 20, 1.50, "Dairy", LocalDate.of(2024, 12, 1));
        Product rice = new NonPerishableProduct("Rice", 100, 0.90, "Grains");

        inventoryManager.addProduct(milk);
        inventoryManager.addProduct(rice);

        // 2. Обновляем продукт (например, обновим количество и цену молока)
        System.out.println("\nОбновление продукта 'Milk':");
        inventoryManager.updateProduct("Milk", 15, 1.60, "Dairy");

        // 3. Продаем продукт (например, продаем некоторое количество риса)
        System.out.println("\nПродажа продукта 'Rice':");
        inventoryManager.sellProduct("Rice", 10);

        // 4. Отображаем текущий инвентарь
        System.out.println("\nТекущий список продуктов:");
        inventoryManager.displayProducts();

        // 5. Генерируем отчет по продажам
        System.out.println("\nГенерация отчета по продажам:");
        ReportGenerator reportGenerator = new ReportGenerator(inventoryManager);
        reportGenerator.generateSalesReport();

        // 6. Сохраняем данные о продуктах в файл
        System.out.println("\nСохранение данных о продуктах в файл...");
        inventoryManager.saveProductsToFile("products_data.txt");

        // 7. Сохраняем историю транзакций в файл
        System.out.println("\nСохранение истории транзакций в файл...");
        inventoryManager.saveTransactionHistoryToFile("transaction_history.txt");

        System.out.println("\nВсе операции выполнены и сохранены в файлы.");
    }
}
import java.time.LocalDate;

public class main {
    public static void main(String[] args) {
        InventoryManager inventory = new InventoryManager();

        // Добавляем продукты
        Product milk = new PerishableProduct("Milk", 10, 1.5, "Dairy", LocalDate.of(2024, 12, 1));
        Product rice = new NonPerishableProduct("Rice", 50, 0.9, "Grains");
        Product potato = new NonPerishableProduct("potato", 53, 1.43, "Grains");
        Product tomato = new NonPerishableProduct("tomato", 20, 4.9, "Grains");
        inventory.addProduct(milk);
        inventory.addProduct(rice);
        inventory.addProduct(potato);
        inventory.addProduct(tomato);

        // Обновляем количество молока
        inventory.updateProduct("Milk", 5, 1.5, "Dairy");

        // Совершаем продажу
        inventory.sellProduct("Rice", 10);

        // Отображаем продукты
        inventory.displayProducts();

        // Сохраняем данные о продуктах в файл
        inventory.saveProductsToFile("products_data.txt");

        // Сохраняем историю транзакций в файл
        inventory.saveTransactionHistoryToFile("transaction_history.txt");
    }
}
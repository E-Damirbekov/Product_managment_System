import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class InventoryManager implements InventoryActions {
    private ArrayList<Product> products;
    private ArrayList<String> transactionHistory;

    public InventoryManager() {
        this.products = new ArrayList<>();
        this.transactionHistory = new ArrayList<>();

    }

    // Метод для добавления нового продукта
    public void addProduct(Product product) {
        products.add(product);
        transactionHistory.add("Добавлен продукт: " + product.getName() + " (Категория: " + product.getCategory() + ")");

    }

    // Метод для обновления продукта по имени
    public void updateProduct(String name, int quantity, double price, String category) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                product.setQuantity(quantity);
                int InQuantity =product.getInitialQuantity();
                product.setPrice(price);
                product.setCategory(category);
                transactionHistory.add("Обновлен продукт: " + name + " (Количество было: " + InQuantity + "Стало" +quantity + ", Цена: " + price + ")");
                return;
            } else {
                System.out.println("Продукт не найден.");
            }
        }
//        System.out.println("Продукт не найден.");
    }

    public void sellProduct(String name, int soldQuantity) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                if (product.getQuantity() >= soldQuantity) {
                    product.setQuantity(product.getQuantity() - soldQuantity);
                    double revenue = soldQuantity * product.getPrice();
                    // Добавляем запись о транзакции
                    transactionHistory.add("Продажа продукта: " + name + " (Количество: " + soldQuantity + ", Выручка: " + revenue + ")");
                    return;
                } else {
                    System.out.println("Недостаточно товара на складе.");
                    return;
                }
            }
        }System.out.println("Продукт не найден.");
    }

    public void saveProductsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("==== Данные о продуктах ====\n");
            writer.write(String.format("%-20s %-10s %-10s %-15s %-15s%n",
                    "Название", "Количество", "Цена", "Категория", "Начальное количество"));
            writer.write("-------------------------------------------------------------\n");  // Заголовок
            for (Product product : products) {
                writer.write(String.format("%-20s %-10d %-10.2f %-15s %-15d%n",
                        product.getName(),
                        product.getQuantity(),
                        product.getPrice(),
                        product.getCategory(),
                        product.getInitialQuantity()));
            }
            System.out.println("Данные о продуктах сохранены в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных о продуктах: " + e.getMessage());
        }
    }

    public void saveTransactionHistoryToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Transaction History:\n");  // Заголовок
            for (String transaction : transactionHistory) {
                writer.write(transaction + "\n");
            }
            System.out.println("История транзакций сохранена в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении истории транзакций: " + e.getMessage());
        }
    }

    // Метод для отображения всех продуктов
    public void displayProducts() {
        System.out.println("Список продуктов:");
        for (Product product : products) {
            product.displayProductInfo();
        }
    }

    // Метод для проверки и оповещения о низком уровне запасов
    public void checkLowStock(int threshold) {
        for (Product product : products) {
            if (product.getQuantity() < threshold) {
                System.out.println("Низкий уровень запаса: " + product.getName());
            }
        }
    }

    // Пример метода для настройки инвентаря (корректировка количества)
    public void adjustInventory(String name, int newQuantity) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                product.setQuantity(newQuantity);
                return;
            }
        }
        System.out.println("Продукт не найден.");
    }

    public ArrayList<Product> getProducts() {
        return products;
    }


}

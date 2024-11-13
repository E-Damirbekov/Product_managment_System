import java.time.LocalDate;

public class main {
    public static void main(String[] args) {
        InventoryManager inventory = new InventoryManager();

        Product milk = new PerishableProduct("Milk", 10, 1.5, "Dairy", LocalDate.of(2024, 12, 1));
        Product rice = new NonPerishableProduct("Rice", 50, 0.9, "Grains");
        Product potato = new NonPerishableProduct("potato", 53, 1.43, "Grains");
        Product tomato = new NonPerishableProduct("tomato", 20, 4.9, "Grains");
        inventory.addProduct(milk);
        inventory.addProduct(rice);
        inventory.addProduct(potato);
        inventory.addProduct(tomato);

        inventory.updateProduct(1,"Milk", 5, 1.5, "Dairy");
        inventory.updateProduct(1,"Milk", 2, 1.5, "Dairy");

        inventory.displayProducts();
        inventory.sellProduct(1, 1);
        inventory.adjustInventory(1,23);
        inventory.saveProductsToFile("products_data.txt");
        inventory.saveTransactionHistoryToFile("transaction_history.txt");
    }
}
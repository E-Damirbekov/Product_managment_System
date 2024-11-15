import java.time.LocalDate;

public interface InventoryActions {
    void addProduct(Product product);
    void updateProduct(int id, String name, int quantity, double price, String category, LocalDate date);
    void displayProducts();
}


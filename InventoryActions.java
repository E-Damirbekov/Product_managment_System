public interface InventoryActions {
    void addProduct(Product product);
    void updateProduct(String name, int quantity, double price, String category);
    void displayProducts();
}


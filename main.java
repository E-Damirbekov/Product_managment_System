import java.time.LocalDate;

public class main {
    public static void main(String[] args) {


        InventoryManager inventory = new InventoryManager();
        //Product rice = new NonPerishableProduct("Rice", 50, 0.9, "Grains");
        Product milk = new PerishableProduct("Mik", 10, 1.5, "Dairy", LocalDate.of(2024, 12, 1));
        Product rice = new NonPerishableProduct("Rice", 50, 0.9, "Grains");
        Product potato = new NonPerishableProduct("potato", 53, 1.43, "Grains");
        Product tomato = new NonPerishableProduct("tomato", 20, 4.9, "Grains");
        inventory.addProduct(rice);
        inventory.addProduct(milk);

        inventory.addProduct(potato);
        inventory.addProduct(tomato);

        inventory.updateProduct(1,"Milk", 5, 1.5, "Dairy");
        //inventory.updateProduct(1,"COCo", 2, 1.5, "Dairy");
//        inventory.deleteProduct("Milk");
//        inventory.searchProducts("Milk");
        inventory.displayProducts();
        inventory.sellProduct("mik", 1);
        inventory.adjustInventory("RICE",23);

        inventory.saveProductsToFile("products_data.txt");
        inventory.saveTransactionHistoryToFile("transaction_history.txt");
    }
}
import java.time.LocalDate;
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

    public void addProduct(Product product) {
        products.add(product);
        transactionHistory.add("|Product added|" + " ID:"+ product.getId() +" Name:"+ product.getName() + " (Category: " + product.getCategory() + ")");

    }

    public void updateProduct(int id,String name, int quantity, double price, String category) {
        for (Product product : products) {
            if (product.getId() == id) {
                int InQuantity = product.getQuantity();
                product.setQuantity(quantity);
                product.setName(name);
                product.setPrice(price);
                product.setCategory(category);
                transactionHistory.add("|Product was Updated| " + "New Name:" + name + " (Amount was: " + InQuantity + " Amount now: " +quantity + ", Price: " + price + ")");
                return;
            } else {
                System.out.println("Product wasn't found.");
            }
        }

    }

    public void sellProduct(int id, int soldQuantity) {
        for (Product product : products) {
            if (product.getId() == id) {
                if (product.getQuantity() >= soldQuantity) {
                    product.setQuantity(product.getQuantity() - soldQuantity);
                    double revenue = soldQuantity * product.getPrice();
                    transactionHistory.add("|Product sold| " + "ID:"+ id +" " + "Name " +product.getName() + " (Quantity: " + soldQuantity + ", Revenue: " + revenue + ")");
                    return;
                } else {
                    System.out.println("Not enough goods in stock.");
                    return;
                }
            }
        }System.out.println("Product not found.");
    }

    public void saveProductsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("==== Product data ====\n");
            writer.write(String.format("%-10s %-20s %-10s %-10s %-15s %-20s %-20s %-20s%n",
                    "ID", "Name", "Quantity", "Price", "Category", "Initial Quantity","ExpiryStartDate","ExpiryEndDate"));
            writer.write("------------------------------------------------------------------------------------------------------\n");

            for (Product product : products) {
                writer.write(String.format("%-10d %-20s %-10d %-10.2f %-15s %-20d %-20s %-20s%n",
                        product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.getPrice(),
                        product.getCategory(),
                        product.getInitialQuantity(),
                        product.getCurrentDate().toString(),
                        product.getExpirationDate()));
            }
            System.out.println("Product data saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving product data: " + e.getMessage());
        }
    }

    public void saveTransactionHistoryToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Transaction History:\n");
            for (String transaction : transactionHistory) {
                writer.write(transaction + "\n");
            }
            System.out.println("Transaction history saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving transaction history: "+ e.getMessage());
        }
    }


    public void displayProducts() {
        System.out.println("List of products:");
        for (Product product : products) {
            product.displayProductInfo();
        }
    }

    public void checkLowStock(int threshold) {
        for (Product product : products) {
            if (product.getQuantity() < threshold) {
                System.out.println("Low stock level:" + product.getName());
            }
        }
    }

    public  void adjustInventory(int id, int addedQuantity) {
        for (Product product : products) {
            if (product.getId() == id) {
                int Quantity = product.getQuantity();
                product.setQuantity(Quantity + addedQuantity);
                transactionHistory.add("|Product was reinforced| " + "ID:" + id +" " + "Name: " + product.getName() + ("Amount was: " + Quantity + " Amount added: " +addedQuantity));
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public ArrayList<Product> getProducts() {
        return products;
    }


}

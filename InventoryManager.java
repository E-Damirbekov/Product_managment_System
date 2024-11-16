
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class InventoryManager implements InventoryActions {
    private ArrayList<Product> products;
    private ArrayList<String> transactionHistory;
    private ArrayList<String> reportHistory;

    public InventoryManager() {
        this.products = new ArrayList<>();
        this.transactionHistory = new ArrayList<>();
        this.reportHistory = new ArrayList<>();

    }

    public void addProduct(Product product) {
        products.add(product);
        transactionHistory.add("|Product added|" + " ID:" + product.getId() + " Name:" + product.getName() + " (Category: " + product.getCategory() + "Price: "+ product.getPrice() + ")");

    }

    @Override
    public void updateProduct(int Id,String name, int quantity, double price, String category, LocalDate date) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId() == Id) {
                products.remove(i);

                if (date != null) {
                    Product updatedProduct = new PerishableProduct(name, quantity, price, category, date);
                    this.products.add(updatedProduct);
                } else {

                    Product updatedProduct = new NonPerishableProduct(name, quantity, price, category);
                    this.products.add(updatedProduct);
                }

                transactionHistory.add("|Product was Updated| " +
                        ", ID: " + Id +
                        ", New Name: " + name +
                        ", New Category: " + category +
                        ", Quantity: " + quantity +
                        ", Price: " + price +
                        ", Expiration Date: " + (date != null ? date.toString() : "N/A"));

                System.out.println("Product updated successfully.");
                return;
            }
        }
        System.out.println("Product with wasn't found.");
    }

    public void sellProduct(String name, int soldQuantity) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                if (product.getQuantity() >= soldQuantity) {
                    product.setQuantity(product.getQuantity() - soldQuantity);
                    System.out.println(product.getQuantity());
                    double revenue = soldQuantity * product.getPrice();
                    transactionHistory.add("|Product sold| " + "ID:" + product.getId() + " " + "Name " + product.getName() + " (Quantity: " + soldQuantity + ", Revenue: " + revenue + ")");
                    return;
                } else {
                    System.out.println("Not enough goods in stock.");
                    return;
                }
            }
        }
        System.out.println("Product not found.");
    }

    public void saveProductsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("==== Product data ====\n");
            writer.write(String.format("%-10s %-20s %-10s %-10s %-15s %-20s %-20s %-20s%n",
                    "ID", "Name", "Quantity", "Price", "Category", "Initial Quantity", "ExpiryStartDate", "ExpiryEndDate"));
            writer.write("----------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

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
            System.out.println("Error saving transaction history: " + e.getMessage());
        }
    }


    public void displayProducts() {
        System.out.println("List of products:");
        for (Product product : products) {
            product.displayProductInfo();
        }
    }

    public void checkLowStock(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                int threshold = (int) (product.getInitialQuantity() * 0.2);
                if (product.getQuantity() < threshold) {
                    System.out.println("Low stock level for product '" + product.getName() +
                            "': Current quantity: " + product.getQuantity() +
                            ", Threshold: " + threshold);
                } else {
                    System.out.println("Stock level for product '" + product.getName() +
                            "' is sufficient. Current quantity: " + product.getQuantity());
                }
                return;
            }
        }
        System.out.println("Product with name '" + name + "' not found.");
    }


    public void adjustInventory(String name, int addedQuantity) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                int Quantity = product.getQuantity();
                product.setQuantity(Quantity + addedQuantity);
                transactionHistory.add("|Product was reinforced| " + "ID:" + product.getId() + " " + "Name: " + product.getName() + ("Amount was: " + Quantity + " Amount added: " + addedQuantity));
                return;
            }
        }
        System.out.println("Product not found.");
    }


    public void searchProducts(String searchName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(searchName)) {
                System.out.println("Product found: " + "ID: " + product.getId() + "Name: " + product.getName() + " (Category: " + product.getCategory() + ")");
            }
        }
    }

    public void deleteProduct(String name) {
        boolean productFound = false;
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getName().equalsIgnoreCase(name)) {
                transactionHistory.add("|Product deleted| ID:" + product.getId() + " Name: " + product.getName());
                products.remove(i);
                productFound = true;
                System.out.println("Product '" + name + "' deleted successfully.");
                break;
            }
        }
        if (!productFound) {
            System.out.println("Product '" + name + "' not found.");
        }
    }

    public void generateSalesReport() {
        double totalRevenue = 0;
        for (Product product : products) {
            int soldQuantity = product.getInitialQuantity() - product.getQuantity();
            double revenue = soldQuantity * product.getPrice();
            totalRevenue += revenue;

            reportHistory.add("Product: " + product.getName() +
                    ", Sold: " + soldQuantity +
                    ", Revenue: " + revenue);
        }

        reportHistory.add("Total Revenue: " + totalRevenue);
    }

    public void saveReportToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Sales Report:\n");

            for (String report : reportHistory) {
                writer.write(report + "\n");
            }

            System.out.println("Report saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving report: " + e.getMessage());
        }
    }

}


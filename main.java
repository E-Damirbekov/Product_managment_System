import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        InventoryManager inventory = new InventoryManager();
        inventory.checkLowStock("123");
        while (true) {
            printMenu();
            int choice = getUserChoice(scanner);

            try {
                switch (choice) {
                    case 1:
                        addProduct(scanner, inventory);
                        break;

                    case 2:
                        updateProduct(scanner, inventory);
                        break;

                    case 3:
                        deleteProduct(scanner, inventory);
                        break;

                    case 4:
                        searchProduct(scanner, inventory);
                        break;

                    case 5:
                        adjustInventory(scanner, inventory);
                        break;

                    case 6:
                        sellProduct(scanner, inventory);
                        break;
                    case 7:
                        checkLowStock(scanner, inventory);
                        break;
                    case 8:
                        inventory.displayProducts();
                        break;
                    case 9:
                        System.out.println("Generating sales report...");
                        reportGeneration(inventory);
                        break;
                    case 10:
                        System.out.println("Exit....");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option...");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date in d-M-yyyy format.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter the correct type of data.");
                scanner.nextLine();
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Product Management System =====");
        System.out.println("1. Add product");
        System.out.println("2. Update product");
        System.out.println("3. Delete product");
        System.out.println("4. Search product");
        System.out.println("5. Add amount");
        System.out.println("6. Sell product");
        System.out.println("7. Check for low stock");
        System.out.println("8. Display info");
        System.out.println("9. Generate sales report");
        System.out.println("10. Exit");
        System.out.print("Select an option: ");
    }

    private static int getUserChoice(Scanner scanner) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer choice.");
                scanner.nextLine();
            }
        }
    }

    private static void addProduct(Scanner scanner, InventoryManager inventory) {
        System.out.println("Enter name of product:");
        String name = scanner.nextLine();

        System.out.println("Enter amount of product:");
        int quantity = getPositiveInt(scanner);

        System.out.println("Enter price of product:");
        double price = getPositiveDouble(scanner);

        System.out.println("Enter category of product:");
        String category = scanner.nextLine();

        String perishable = "";
        while (true) {
            System.out.println("Is the product Perishable or Nonperishable?");
            perishable = scanner.nextLine().trim().toLowerCase();
            if (perishable.equals("perishable") || perishable.equals("nonperishable")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'Perishable' or 'Nonperishable'.");
            }
        }

        if (perishable.equals("perishable")) {
            System.out.println("Enter expiration date of product (d-M-yyyy):");
            LocalDate date = getValidDate(scanner);
            Product product = new PerishableProduct(name, quantity, price, category, date);
            inventory.addProduct(product);
        } else {
            Product product = new NonPerishableProduct(name, quantity, price, category);
            inventory.addProduct(product);
        }

        inventory.saveProductsToFile("products_data.txt");
        inventory.saveTransactionHistoryToFile("transaction_history.txt");
    }

    private static void updateProduct(Scanner scanner, InventoryManager inventory) {
        System.out.println("Enter id of product:");
        int id = getPositiveInt(scanner);

        System.out.println("Enter new name of product:");
        String name = scanner.nextLine();

        System.out.println("Enter new amount of product:");
        int quantity = getPositiveInt(scanner);

        System.out.println("Enter new price of product:");
        double price = getPositiveDouble(scanner);

        System.out.println("Enter new category of product:");
        String category = scanner.nextLine();

        String perishable = "";
        while (true) {
            System.out.println("Is the product Perishable or Nonperishable?");
            perishable = scanner.nextLine().trim().toLowerCase();
            if (perishable.equalsIgnoreCase("perishable") || perishable.equalsIgnoreCase("nonperishable")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'Perishable' or 'Nonperishable'.");
            }
        }

        if (perishable.equals("perishable")) {
            System.out.println("Enter new expiration date of product (d-M-yyyy):");
            LocalDate date = getValidDate(scanner);
            inventory.updateProduct(id, name, quantity, price, category, date);
        } else {
            inventory.updateProduct(id, name, quantity, price, category, null);
        }
        inventory.saveProductsToFile("products_data.txt");
        inventory.saveTransactionHistoryToFile("transaction_history.txt");
    }

    private static void deleteProduct(Scanner scanner, InventoryManager inventory) {
        System.out.println("Enter name of product to delete:");
        String name = scanner.nextLine();
        inventory.deleteProduct(name);
        inventory.saveProductsToFile("products_data.txt");
        inventory.saveTransactionHistoryToFile("transaction_history.txt");
    }

    private static void checkLowStock(Scanner scanner, InventoryManager inventory) {
        System.out.println("Enter the name for check:");
        String name = scanner.nextLine();
        inventory.checkLowStock(name);
    }

    private static void searchProduct(Scanner scanner, InventoryManager inventory) {
        System.out.println("Enter name of product to search:");
        String name = scanner.nextLine();
        inventory.searchProducts(name);
    }

    private static void adjustInventory(Scanner scanner, InventoryManager inventory) {
        System.out.println("Enter the name of product:");
        String name = scanner.nextLine();

        System.out.println("Enter quantity to add:");
        int quantity = getPositiveInt(scanner);

        inventory.adjustInventory(name, quantity);
        inventory.saveProductsToFile("products_data.txt");
        inventory.saveTransactionHistoryToFile("transaction_history.txt");
    }

    private static void sellProduct(Scanner scanner, InventoryManager inventory) {
        System.out.println("Enter the name of product:");
        String name = scanner.nextLine();

        System.out.println("Enter quantity to sell:");
        int quantity = getPositiveInt(scanner);

        inventory.sellProduct(name, quantity);
        inventory.saveProductsToFile("products_data.txt");
        inventory.saveTransactionHistoryToFile("transaction_history.txt");
    }
    private static void reportGeneration(InventoryManager inventory) {
        inventory.generateSalesReport();
        inventory.saveReportToFile("report_data.txt");
    }

    private static int getPositiveInt(Scanner scanner) {
        while (true) {
            try {
                int number = scanner.nextInt();
                scanner.nextLine();
                if (number >= 0) {
                    return number;
                } else {
                    System.out.println("Please enter a non-negative integer:");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer:");
                scanner.nextLine();
            }
        }
    }

    private static double getPositiveDouble(Scanner scanner) {
        while (true) {
            try {
                double number = scanner.nextDouble();
                scanner.nextLine();
                if (number >= 0) {
                    return number;
                } else {
                    System.out.println("Please enter a non-negative number:");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number:");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private static LocalDate getValidDate(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        while (true) {
            try {
                String dateInput = scanner.nextLine();
                return LocalDate.parse(dateInput,formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date in d-M-yyyy format:");
            }
        }

    }

}

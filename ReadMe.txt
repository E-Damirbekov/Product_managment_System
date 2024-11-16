# Inventory Management System

## Project Overview
This Inventory Management System is developed in Java and allows users to manage product inventory, track sales, and generate reports. The system supports adding, updating, and selling products, as well as storing transaction history and product data in files.

## Features
- Add new products to the inventory
- Update product details
- Track and manage inventory levels
- Generate sales reports with revenue calculations
- Save product data and transaction history in text files

## Classes and Methods

### 1. Class `Product`
The `Product` class represents a basic product with the following properties:
- `name`: Name of the product
- `quantity`: Current stock level
- `price`: Price per unit
- `category`: Product category
- `initialQuantity`: Initial quantity when the product was added

**Methods:**
- `Product(String name, int quantity, double price, String category)`: Constructor to initialize the product's details.
- `getName()`: Returns the name of the product.
- `getQuantity()`: Returns the current stock level of the product.
- `setQuantity(int quantity)`: Updates the product's quantity.
- `getPrice()`: Returns the price of the product.
- `getCategory()`: Returns the product category.
- `getInitialQuantity()`: Returns the initial stock quantity.
- `displayProductInfo()`: Prints the product's information in a readable format.

### 2. Class `PerishableProduct` (extends `Product`)
This subclass is used to represent perishable products with an expiration date.
- `expirationDate`: Date when the product expires.

**Methods:**
- `PerishableProduct(String name, int quantity, double price, String category, LocalDate expirationDate)`: Constructor that includes an expiration date.
- `getExpirationDate()`: Returns the product's expiration date.

### 3. Class `NonPerishableProduct` (extends `Product`)
A subclass representing non-perishable products. No additional properties are added, but it inherits all properties and methods from `Product`.

### 4. Class `InventoryManager`
The main class for managing inventory. It includes an ArrayList to store `Product` objects and transaction history.

**Methods:**
- `addProduct(Product product)`: Adds a product to the inventory and logs the transaction.
- `updateProduct(String name, int quantity, double price, String category)`: Updates the product's details (quantity, price, category) if found.
- `sellProduct(String name, int soldQuantity)`: Sells a specified quantity of a product, reduces stock, and logs the transaction. Prints an error if insufficient stock.
- `displayProducts()`: Displays all products currently in the inventory.
- `saveProductsToFile(String filename)`: Saves current product data to a specified file in a formatted table.
- `saveTransactionHistoryToFile(String filename)`: Saves transaction history to a specified file.


### 5. Class `Test`
The `Test` class is a demo class to check the functionality of the system. It includes examples of adding, updating, selling products, displaying inventory, generating reports, and saving data to files.

## Usage Instructions

1. **Adding Products**:
   ```java
   Product milk = new PerishableProduct("Milk", 20, 1.50, "Dairy", LocalDate.of(2024, 12, 1));
   Product rice = new NonPerishableProduct("Rice", 100, 0.90, "Grains");
   inventoryManager.addProduct(milk);
   inventoryManager.addProduct(rice);

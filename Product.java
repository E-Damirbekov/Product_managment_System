public abstract class Product {
    private String name;
    private int quantity;
    private double price;
    private String category;
    private int InitialQuantity;

    // Конструктор
    public Product(String name, int quantity, double price, String category) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.InitialQuantity = quantity;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }
    public int getInitialQuantity(){
        return InitialQuantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Метод для получения общей стоимости на складе
    public double getTotalValue() {
        return quantity * price;
    }

    // Абстрактный метод для особых действий (переопределяется в подклассах)
    public abstract void checkExpiry();

    public void displayProductInfo() {
        System.out.println("Name: " + name + ", Category: " + category + ", Quantity: " + quantity + ", Price: " + price);
    }
}
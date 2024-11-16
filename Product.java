import java.time.LocalDate;

public abstract class Product {
    private static int nextId = 1;
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String category;
    private int InitialQuantity ;

    public Product(String name, int quantity, double price, String category) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.InitialQuantity = quantity;
    }


    public int getId(){
        return id;
    }


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

    public void setInitialQuantity(int newInitialQuantity){
        this.InitialQuantity = newInitialQuantity;
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

    public abstract void checkExpiry();
    public abstract LocalDate getExpirationDate();
    public abstract LocalDate getCurrentDate();

    public void displayProductInfo() {
        System.out.println("ID: "+ id+ " Name: " + name + ", Category: " + category + ", Quantity: " + quantity + ", Price: " + price + ", ExpiryEndDate: " + getExpirationDate());
    }


}
public class NonPerishableProduct extends Product {
    public NonPerishableProduct(String name, int quantity, double price, String category) {
        super(name, quantity, price, category);
    }
    public void checkExpiry(){
        System.out.println("This product don't have expiry");
    }
}
import java.time.LocalDate;

public class NonPerishableProduct extends Product {
    private LocalDate expirationDate ;
    public NonPerishableProduct(String name, int quantity, double price, String category) {
        super(name, quantity, price, category);
    }
    public void checkExpiry(){
        System.out.println("This product don't have expiry");
    }
    @Override
    public LocalDate getExpirationDate() {
        return this.expirationDate = LocalDate.parse("1111-11-11");  //This date means that the product do not have Expiry date
    }
    @Override
    public LocalDate getCurrentDate(){
        return LocalDate.now();
    }
}
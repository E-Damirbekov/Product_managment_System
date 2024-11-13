import java.time.LocalDate;

public class PerishableProduct extends Product {
    private LocalDate expirationDate;

    public PerishableProduct(String name, int quantity, double price, String category, LocalDate expirationDate) {
        super(name,quantity, price, category);
        this.expirationDate = expirationDate;
    }
    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    @Override
    public LocalDate getCurrentDate(){
        return LocalDate.now();
    }

    @Override
    public void checkExpiry() {
        if (expirationDate.isBefore(LocalDate.now())) {
            System.out.println("Id"+ getId() + "Product " + getName() + " expired!");
        } else {
            System.out.println("Id"+ getId() +"Product " + getName() + " will expire " + getExpirationDate());
        }
    }
}
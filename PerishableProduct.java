import java.time.LocalDate;

public class PerishableProduct extends Product {
    private LocalDate expirationDate;

    public PerishableProduct(String name, int quantity, double price, String category, LocalDate expirationDate) {
        super(name, quantity, price, category);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() { return expirationDate; }

    @Override
    public void checkExpiry() {
        if (expirationDate.isBefore(LocalDate.now())) {
            System.out.println("Product " + getName() + " expired!");
        } else {
            System.out.println("Product " + getName() + " will expire " + getExpirationDate());
        }
    }
}
public class ReportGenerator implements ReportActions{
    private InventoryManager inventory;

    public ReportGenerator(InventoryManager inventory) {
        this.inventory = inventory;
    }

    public void generateSalesReport() {
        double totalRevenue = 0;

        System.out.println("Sells Report:");
        for (Product product : inventory.getProducts()) {
            int soldQuantity = product.getInitialQuantity() - product.getQuantity();
            double revenue = soldQuantity * product.getPrice();
            totalRevenue += revenue;

            System.out.println("Product: " + product.getName() +
                    ", Sold: " + soldQuantity +
                    ", Revenue: " + revenue);
        }

        System.out.println("Total Revenue: " + totalRevenue);
    }
}

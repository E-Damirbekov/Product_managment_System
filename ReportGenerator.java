public class ReportGenerator implements ReportActions{
    private InventoryManager inventory;

    public ReportGenerator(InventoryManager inventory) {
        this.inventory = inventory;
    }

    // Метод для генерации отчета по продажам
    public void generateSalesReport() {
        double totalRevenue = 0;

        System.out.println("Отчет по продажам:");
        for (Product product : inventory.getProducts()) {
            int soldQuantity = product.getInitialQuantity() - product.getQuantity();
            double revenue = soldQuantity * product.getPrice();
            totalRevenue += revenue;

            System.out.println("Продукт: " + product.getName() +
                    ", Продано: " + soldQuantity +
                    ", Выручка: " + revenue);
        }

        System.out.println("Общая выручка: " + totalRevenue);
    }
}

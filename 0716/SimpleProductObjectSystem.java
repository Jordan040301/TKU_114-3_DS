public class SimpleProductObjectSystem {
    public static Product1 findProduct(
            Product1[] products, String name) {
        for (Product1 product : products) {
            if (product.getName().equalsIgnoreCase(name.trim())) {
                return product;
            }
        }
        return null;
    }

    public static long calculateTotalValue(Product1[] products) {
        long total = 0;
        for (Product1 product : products) {
            total += product.getInventoryValue();
        }
        return total;
    }

    public static void main(String[] args) {
        Product1[] products = {
            new Product1("Keyboard", 890, 12),
            new Product1("Mouse", 490, 20),
            new Product1("Monitor", 5200, 5)
        };

        System.out.println("全部商品：");
        for (Product1 product : products) {
            System.out.println(product);
        }

        Product1 found = findProduct(products, "monitor");
        if (found != null && found.sell(2)) {
            System.out.println("出售成功：" + found);
        }

        System.out.println("庫存總價值："
                + calculateTotalValue(products));
    }
}
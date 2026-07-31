public class ProductArrayDemo {
    public static Product1 findProduct(
            Product1[] products, String name) {
        for (Product1 product : products) {
            if (product != null
                    && product.getName().equalsIgnoreCase(name.trim())) {
                return product;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product1[] products = new Product1[5];
        products[0] = new Product1("Keyboard", 890, 12);
        products[1] = new Product1("Mouse", 490, 20);
        products[2] = new Product1("Monitor", 5200, 5);

        System.out.println("全部商品：");
        for (Product1 product : products) {
            if (product != null) {
                System.out.println(product);
            }
        }

        Product1 found = findProduct(products, " mouse ");
        if (found != null) {
            System.out.println("搜尋結果：" + found);
        } else {
            System.out.println("找不到商品");
        }
    }
}
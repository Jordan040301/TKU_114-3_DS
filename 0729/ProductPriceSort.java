class Product {
    private String id;
    private String name;
    private int price;

    // 建構子
    public Product(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getter 方法
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    // toString 方法
    @Override
    public String toString() {
        return "Product{id='" + id + "', name='" + name + "', price=" + price + "}";
    }
}

// 主類別
public class ProductPriceSort {

    public static void main(String[] args) {
        Product[] products = {
            new Product("P103", "Keyboard", 1290),
            new Product("P205", "Mouse", 650),
            new Product("P118", "Monitor", 5200),
            new Product("P310", "Webcam", 1290)
        };

        System.out.println("排序前：");
        for (Product product : products) {
            System.out.println(product);
        }

        // 呼叫插入排序
        insertionSortByPrice(products);

        System.out.println("\n排序後（按價格升序）：");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    /**
     * 插入排序（依價格升序）
     */
    public static void insertionSortByPrice(Product[] products) {
        for (int index = 1; index < products.length; index++) {
            Product key = products[index];
            int position = index - 1;

            while (position >= 0 && products[position].getPrice() > key.getPrice()) {
                products[position + 1] = products[position];
                position--;
            }
            products[position + 1] = key;
        }
    }
}
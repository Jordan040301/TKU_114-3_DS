public class Product1 {
    private String id;
    private String name;
    private int price;
    private int stock;

    // 構造方法
    public Product1(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }

    // 重寫 toString 方法
    @Override
    public String toString() {
        return "Product{編號='" + id + "', 名稱='" + name + "', 價格=" + price + ", 庫存=" + stock + "}";
    }
}
public class StoreProduct {
    private String id;
    private String name;
    private int price;
    private int stock;

    // 構造方法
    public StoreProduct(String id, String name, int price, int stock) {
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

    // Setter 方法
    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // 重寫 toString 方法
    @Override
    public String toString() {
        return String.format("商品{編號='%s', 名稱='%s', 價格=%d, 庫存=%d}", 
                           id, name, price, stock);
    }
}
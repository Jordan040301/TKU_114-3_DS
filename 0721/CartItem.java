public class CartItem {
    private String code;      // 商品代碼
    private String name;      // 商品名稱
    private double price;     // 單價
    private int quantity;     // 數量
    
    // 建構子
    public CartItem(String code, String name, double price, int quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = Math.max(quantity, 1); // 數量至少為1
    }
    
    // Getter 方法
    public String getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    // Setter 方法
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPrice(double price) {
        this.price = Math.max(price, 0);
    }
    
    public void setQuantity(int quantity) {
        this.quantity = Math.max(quantity, 0);
    }
    
    // 增加數量（不建立重複物品）
    public void addQuantity(int amount) {
        if (amount > 0) {
            this.quantity += amount;
        }
    }
    
    // 計算小計
    public double getSubtotal() {
        return price * quantity;
    }
    
    // 顯示商品資訊
    @Override
    public String toString() {
        return String.format("代碼：%s | 名稱：%s | 單價：$%.2f | 數量：%d | 小計：$%.2f", 
            code, name, price, quantity, getSubtotal());
    }
}
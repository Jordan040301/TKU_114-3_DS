/**
 * Product 類別 - 表示商品
 * 包含名稱、價格、庫存及相關管理功能
 */
public class Product {
    
    // 私有欄位
    private String name;
    private int price;
    private int stock;
    
    /**
     * 建構子 - 驗證名稱不可空白、價格須大於0、庫存不得小於0
     * @param name 商品名稱（不可為空白）
     * @param price 商品價格（必須大於0）
     * @param stock 庫存數量（不得小於0）
     */
    public Product(String name, int price, int stock) {
        setName(name);
        setPrice(price);
        setStock(stock);
    }
    
    /**
     * 取得商品名稱
     * @return 商品名稱
     */
    public String getName() {
        return name;
    }
    
    /**
     * 取得商品價格
     * @return 商品價格
     */
    public int getPrice() {
        return price;
    }
    
    /**
     * 取得庫存數量
     * @return 庫存數量
     */
    public int getStock() {
        return stock;
    }
    
    /**
     * 設定商品名稱 - 驗證不可為空白
     * @param name 商品名稱
     */
    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("商品名稱不可為空白");
        }
        this.name = name.trim();
    }
    
    /**
     * 設定商品價格 - 驗證須大於0
     * @param price 商品價格
     */
    public void setPrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("商品價格必須大於0，輸入值：" + price);
        }
        this.price = price;
    }
    
    /**
     * 設定庫存 - 驗證不得小於0（僅供內部使用）
     * @param stock 庫存數量
     */
    private void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("庫存數量不得小於0，輸入值：" + stock);
        }
        this.stock = stock;
    }
    
    /**
     * 補充庫存 - 增加庫存數量
     * @param amount 補充數量（必須大於0）
     * @return 成功回傳 true，失敗回傳 false
     */
    public boolean restock(int amount) {
        if (amount <= 0) {
            System.out.println("錯誤：補充數量必須大於0");
            return false;
        }
        stock += amount;
        System.out.println("補充成功：+" + amount + "，目前庫存：" + stock);
        return true;
    }
    
    /**
     * 銷售商品 - 減少庫存數量
     * @param quantity 銷售數量（必須大於0且不超過庫存）
     * @return 成功回傳 true，失敗回傳 false
     */
    public boolean sell(int quantity) {
        if (quantity <= 0) {
            System.out.println("錯誤：銷售數量必須大於0");
            return false;
        }
        if (quantity > stock) {
            System.out.println("錯誤：庫存不足！需要：" + quantity + "，目前庫存：" + stock);
            return false;
        }
        stock -= quantity;
        System.out.println("銷售成功：-" + quantity + "，目前庫存：" + stock);
        return true;
    }
    
    /**
     * 判斷是否為低庫存（庫存少於10）
     * @return 庫存 < 10 回傳 true，否則 false
     */
    public boolean isLowStock() {
        return stock < 10;
    }
    
    /**
     * 計算庫存總價值
     * @return 庫存總價值 = 價格 × 庫存
     */
    public int getInventoryValue() {
        return price * stock;
    }
    
    /**
     * 顯示商品完整資訊
     * @return 商品詳細資訊
     */
    @Override
    public String toString() {
        return String.format(
            "Product[名稱=%s, 價格=%d, 庫存=%d, 總價值=%d, 低庫存=%s]",
            name, price, stock, getInventoryValue(), isLowStock()
        );
    }
}
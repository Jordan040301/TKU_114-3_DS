/**
 * ProductDemo 類別 - 測試 Product 類別
 * 建立多個商品並測試所有功能
 */
public class ProductDemo {
    
    public static void main(String[] args) {
        System.out.println("=== 商品類別測試程式 ===\n");
        
        // 建立多個商品
        System.out.println("=== 建立商品 ===");
        Product product1 = new Product("Keyboard", 890, 12);
        Product product2 = new Product("Mouse", 490, 20);
        Product product3 = new Product("Monitor", 5200, 5);
        
        System.out.println("商品1：" + product1);
        System.out.println("商品2：" + product2);
        System.out.println("商品3：" + product3);
        System.out.println();
        
        // 測試 getter 方法
        System.out.println("=== 測試 Getter 方法 ===");
        testGetters(product1);
        System.out.println();
        
        // 測試 setPrice 方法
        System.out.println("=== 測試 setPrice ===");
        testSetPrice(product1);
        System.out.println();
        
        // 測試 restock 方法
        System.out.println("=== 測試 restock（補充庫存） ===");
        testRestock(product1);
        System.out.println();
        
        // 測試 sell 方法
        System.out.println("=== 測試 sell（銷售商品） ===");
        testSell(product1);
        System.out.println();
        
        // 測試 isLowStock 方法
        System.out.println("=== 測試 isLowStock（低庫存判斷） ===");
        testLowStock(product2, product3);
        System.out.println();
        
        // 測試 getInventoryValue 方法
        System.out.println("=== 測試 getInventoryValue（庫存總價值） ===");
        testInventoryValue(product2);
        System.out.println();
        
        // 測試錯誤處理
        System.out.println("=== 錯誤處理測試 ===");
        testErrorHandling();
        System.out.println();
        
        // 顯示所有商品最終狀態
        System.out.println("=== 最終商品狀態 ===");
        System.out.println("商品1：" + product1);
        System.out.println("商品2：" + product2);
        System.out.println("商品3：" + product3);
    }
    
    /**
     * 測試 Getter 方法
     */
    public static void testGetters(Product product) {
        System.out.println("商品名稱：" + product.getName());
        System.out.println("商品價格：" + product.getPrice());
        System.out.println("庫存數量：" + product.getStock());
        System.out.println("是否低庫存：" + product.isLowStock());
        System.out.println("庫存總價值：" + product.getInventoryValue());
    }
    
    /**
     * 測試 setPrice 方法
     */
    public static void testSetPrice(Product product) {
        System.out.println("原價：" + product.getPrice());
        
        // 成功設定價格
        System.out.println("嘗試設定價格為 999：");
        product.setPrice(999);
        System.out.println("新價格：" + product.getPrice());
        
        // 失敗設定價格（價格為0）
        try {
            System.out.println("嘗試設定價格為 0：");
            product.setPrice(0);
        } catch (IllegalArgumentException e) {
            System.out.println("捕獲異常：" + e.getMessage());
        }
        
        // 失敗設定價格（價格為負數）
        try {
            System.out.println("嘗試設定價格為 -100：");
            product.setPrice(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("捕獲異常：" + e.getMessage());
        }
    }
    
    /**
     * 測試 restock 方法
     */
    public static void testRestock(Product product) {
        System.out.println("目前庫存：" + product.getStock());
        
        // 成功補充
        System.out.println("嘗試補充 10 件：");
        product.restock(10);
        
        // 失敗補充（數量為0）
        System.out.println("嘗試補充 0 件：");
        product.restock(0);
        
        // 失敗補充（數量為負數）
        System.out.println("嘗試補充 -5 件：");
        product.restock(-5);
    }
    
    /**
     * 測試 sell 方法
     */
    public static void testSell(Product product) {
        System.out.println("目前庫存：" + product.getStock());
        
        // 成功銷售
        System.out.println("嘗試銷售 5 件：");
        product.sell(5);
        
        // 失敗銷售（數量為0）
        System.out.println("嘗試銷售 0 件：");
        product.sell(0);
        
        // 失敗銷售（數量為負數）
        System.out.println("嘗試銷售 -3 件：");
        product.sell(-3);
        
        // 失敗銷售（數量超過庫存）
        System.out.println("嘗試銷售 100 件：");
        product.sell(100);
    }
    
    /**
     * 測試 isLowStock 方法
     */
    public static void testLowStock(Product product1, Product product2) {
        System.out.println("商品：" + product1.getName() + "，庫存：" + product1.getStock());
        System.out.println("是否低庫存：" + (product1.isLowStock() ? "是" : "否"));
        
        System.out.println("商品：" + product2.getName() + "，庫存：" + product2.getStock());
        System.out.println("是否低庫存：" + (product2.isLowStock() ? "是" : "否"));
    }
    
    /**
     * 測試 getInventoryValue 方法
     */
    public static void testInventoryValue(Product product) {
        System.out.println("商品：" + product.getName());
        System.out.println("單價：" + product.getPrice());
        System.out.println("庫存：" + product.getStock());
        System.out.println("庫存總價值：" + product.getInventoryValue());
    }
    
    /**
     * 測試錯誤處理
     */
    public static void testErrorHandling() {
        // 測試名稱空白
        try {
            System.out.println("嘗試建立名稱為空白的商品...");
            Product invalidProduct = new Product("", 100, 10);
            System.out.println("建立成功：" + invalidProduct);
        } catch (IllegalArgumentException e) {
            System.out.println("捕獲異常：" + e.getMessage());
        }
        
        // 測試名稱 null
        try {
            System.out.println("嘗試建立名稱為 null 的商品...");
            Product invalidProduct = new Product(null, 100, 10);
            System.out.println("建立成功：" + invalidProduct);
        } catch (IllegalArgumentException e) {
            System.out.println("捕獲異常：" + e.getMessage());
        }
        
        // 測試價格為0
        try {
            System.out.println("嘗試建立價格為 0 的商品...");
            Product invalidProduct = new Product("Test", 0, 10);
            System.out.println("建立成功：" + invalidProduct);
        } catch (IllegalArgumentException e) {
            System.out.println("捕獲異常：" + e.getMessage());
        }
        
        // 測試價格為負數
        try {
            System.out.println("嘗試建立價格為 -50 的商品...");
            Product invalidProduct = new Product("Test", -50, 10);
            System.out.println("建立成功：" + invalidProduct);
        } catch (IllegalArgumentException e) {
            System.out.println("捕獲異常：" + e.getMessage());
        }
        
        // 測試庫存為負數
        try {
            System.out.println("嘗試建立庫存為 -5 的商品...");
            Product invalidProduct = new Product("Test", 100, -5);
            System.out.println("建立成功：" + invalidProduct);
        } catch (IllegalArgumentException e) {
            System.out.println("捕獲異常：" + e.getMessage());
        }
    }
}
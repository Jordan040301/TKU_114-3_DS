import java.util.Scanner;

/**
 * ProductManagementSystem 類別 - 商品管理系統
 * 使用 Product 類別管理商品陣列，提供完整的 CRUD 操作
 */
public class ProductManagementSystem {
    
    // 商品陣列
    private static Product[] products = new Product[10];
    private static int productCount = 0;
    
    // 操作統計
    private static int totalAdditions = 0;
    private static int totalSales = 0;
    private static int totalRestocks = 0;
    private static int totalPriceChanges = 0;
    private static int totalRevenue = 0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 初始化 5 項商品
        initializeProducts();
        
        int choice;
        System.out.println("=== 商品管理系統 ===");
        System.out.println("歡迎使用商品管理系統！");
        
        do {
            displayMenu();
            System.out.print("請選擇功能（1-9）：");
            choice = sc.nextInt();
            sc.nextLine(); // 清除換行
            System.out.println();
            
            switch (choice) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    searchProductByName(sc);
                    break;
                case 3:
                    addProduct(sc);
                    break;
                case 4:
                    sellProduct(sc);
                    break;
                case 5:
                    restockProduct(sc);
                    break;
                case 6:
                    updateProductPrice(sc);
                    break;
                case 7:
                    displayLowStockProducts();
                    break;
                case 8:
                    displayTotalInventoryValue();
                    break;
                case 9:
                    exitSystem();
                    break;
                default:
                    System.out.println("錯誤：請輸入 1 到 9 之間的選項！\n");
            }
        } while (choice != 9);
        
        sc.close();
    }
    
    /**
     * 初始化 5 項商品
     */
    public static void initializeProducts() {
        try {
            products[0] = new Product("Keyboard", 890, 12);
            products[1] = new Product("Mouse", 490, 20);
            products[2] = new Product("Monitor", 5200, 5);
            products[3] = new Product("USB Cable", 250, 30);
            products[4] = new Product("Headset", 1290, 8);
            productCount = 5;
            System.out.println("已初始化 5 項商品，尚有 5 個空間可新增\n");
        } catch (IllegalArgumentException e) {
            System.out.println("初始化商品失敗：" + e.getMessage());
        }
    }
    
    /**
     * 顯示主選單
     */
    public static void displayMenu() {
        System.out.println("=== 主選單 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依完整名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示庫存低商品");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("9. 結束並顯示操作摘要");
    }
    
    /**
     * 1. 顯示全部商品
     */
    public static void displayAllProducts() {
        if (productCount == 0) {
            System.out.println("目前沒有任何商品！\n");
            return;
        }
        
        System.out.println("=== 全部商品列表 ===");
        System.out.println("編號\t商品名稱\t\t單價\t庫存\t總價值\t低庫存");
        System.out.println("----\t--------\t\t----\t----\t----\t------");
        
        for (int i = 0; i < productCount; i++) {
            Product p = products[i];
            System.out.printf("%d\t%-15s\t%d\t%d\t%d\t%s\n", 
                            (i + 1), 
                            p.getName(), 
                            p.getPrice(), 
                            p.getStock(),
                            p.getInventoryValue(),
                            p.isLowStock() ? "是" : "否");
        }
        System.out.println();
    }
    
    /**
     * 2. 依完整名稱搜尋（忽略大小寫與空白）
     */
    public static void searchProductByName(Scanner sc) {
        System.out.print("請輸入要搜尋的商品名稱：");
        String keyword = sc.nextLine().trim();
        
        // 標準化關鍵字（去除空白、轉小寫）
        String normalizedKeyword = normalizeString(keyword);
        boolean found = false;
        
        System.out.println("=== 搜尋結果 ===");
        for (int i = 0; i < productCount; i++) {
            Product p = products[i];
            String normalizedName = normalizeString(p.getName());
            if (normalizedName.equals(normalizedKeyword)) {
                System.out.println("找到商品！");
                System.out.println("編號：" + (i + 1));
                System.out.println("名稱：" + p.getName());
                System.out.println("單價：" + p.getPrice());
                System.out.println("庫存：" + p.getStock());
                System.out.println("總價值：" + p.getInventoryValue());
                System.out.println("低庫存：" + (p.isLowStock() ? "是" : "否"));
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("找不到符合的商品！");
        }
        System.out.println();
    }
    
    /**
     * 3. 新增商品（不可重複名稱、已滿時不可新增）
     */
    public static void addProduct(Scanner sc) {
        System.out.println("=== 新增商品 ===");
        
        // 檢查是否已滿
        if (productCount >= products.length) {
            System.out.println("錯誤：商品空間已滿（最多 " + products.length + " 項）！\n");
            return;
        }
        
        System.out.print("請輸入商品名稱：");
        String name = sc.nextLine().trim();
        
        // 檢查名稱是否為空白
        if (name.isEmpty()) {
            System.out.println("錯誤：商品名稱不可為空白！\n");
            return;
        }
        
        // 檢查名稱是否重複（忽略大小寫與空白）
        if (isProductNameExists(name)) {
            System.out.println("錯誤：商品名稱已存在！\n");
            return;
        }
        
        System.out.print("請輸入商品價格（必須大於0）：");
        int price = sc.nextInt();
        sc.nextLine();
        
        System.out.print("請輸入庫存數量（不得小於0）：");
        int stock = sc.nextInt();
        sc.nextLine();
        
        try {
            Product newProduct = new Product(name, price, stock);
            products[productCount] = newProduct;
            productCount++;
            totalAdditions++;
            System.out.println("商品新增成功！\n");
        } catch (IllegalArgumentException e) {
            System.out.println("新增失敗：" + e.getMessage() + "\n");
        }
    }
    
    /**
     * 4. 出售商品
     */
    public static void sellProduct(Scanner sc) {
        System.out.print("請輸入要出售的商品名稱：");
        String name = sc.nextLine().trim();
        
        int index = findProductIndex(name);
        if (index == -1) {
            System.out.println("錯誤：找不到該商品！\n");
            return;
        }
        
        Product p = products[index];
        System.out.println("商品：" + p.getName());
        System.out.println("目前庫存：" + p.getStock());
        System.out.print("請輸入出售數量：");
        int quantity = sc.nextInt();
        sc.nextLine();
        
        if (p.sell(quantity)) {
            totalSales += quantity;
            totalRevenue += p.getPrice() * quantity;
        }
        System.out.println();
    }
    
    /**
     * 5. 補充庫存
     */
    public static void restockProduct(Scanner sc) {
        System.out.print("請輸入要補充庫存的商品名稱：");
        String name = sc.nextLine().trim();
        
        int index = findProductIndex(name);
        if (index == -1) {
            System.out.println("錯誤：找不到該商品！\n");
            return;
        }
        
        Product p = products[index];
        System.out.println("商品：" + p.getName());
        System.out.println("目前庫存：" + p.getStock());
        System.out.print("請輸入補充數量：");
        int amount = sc.nextInt();
        sc.nextLine();
        
        if (p.restock(amount)) {
            totalRestocks += amount;
        }
        System.out.println();
    }
    
    /**
     * 6. 修改商品價格
     */
    public static void updateProductPrice(Scanner sc) {
        System.out.print("請輸入要修改價格的商品名稱：");
        String name = sc.nextLine().trim();
        
        int index = findProductIndex(name);
        if (index == -1) {
            System.out.println("錯誤：找不到該商品！\n");
            return;
        }
        
        Product p = products[index];
        System.out.println("商品：" + p.getName());
        System.out.println("目前價格：" + p.getPrice());
        System.out.print("請輸入新價格（必須大於0）：");
        int newPrice = sc.nextInt();
        sc.nextLine();
        
        try {
            p.setPrice(newPrice);
            totalPriceChanges++;
            System.out.println("價格修改成功！\n");
        } catch (IllegalArgumentException e) {
            System.out.println("修改失敗：" + e.getMessage() + "\n");
        }
    }
    
    /**
     * 7. 顯示庫存低商品（庫存 < 10）
     */
    public static void displayLowStockProducts() {
        System.out.println("=== 低庫存商品列表（庫存 < 10） ===");
        boolean found = false;
        
        System.out.println("編號\t商品名稱\t\t庫存");
        System.out.println("----\t--------\t\t----");
        
        for (int i = 0; i < productCount; i++) {
            Product p = products[i];
            if (p.isLowStock()) {
                System.out.printf("%d\t%-15s\t%d\n", 
                                (i + 1), p.getName(), p.getStock());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("所有商品庫存充足！");
        }
        System.out.println();
    }
    
    /**
     * 8. 顯示全部庫存總價值
     */
    public static void displayTotalInventoryValue() {
        if (productCount == 0) {
            System.out.println("目前沒有任何商品！\n");
            return;
        }
        
        System.out.println("=== 全部庫存總價值 ===");
        int totalValue = 0;
        
        System.out.println("編號\t商品名稱\t\t單價\t庫存\t小計");
        System.out.println("----\t--------\t\t----\t----\t----");
        
        for (int i = 0; i < productCount; i++) {
            Product p = products[i];
            int subtotal = p.getInventoryValue();
            totalValue += subtotal;
            System.out.printf("%d\t%-15s\t%d\t%d\t%d\n", 
                            (i + 1), p.getName(), p.getPrice(), p.getStock(), subtotal);
        }
        System.out.println("----------------------------------------");
        System.out.println("總庫存價值：" + totalValue + " 元\n");
    }
    
    /**
     * 9. 結束並顯示操作摘要
     */
    public static void exitSystem() {
        System.out.println("=== 操作摘要 ===");
        System.out.println("新增商品次數：" + totalAdditions + " 次");
        System.out.println("出售商品總數：" + totalSales + " 件");
        System.out.println("補充庫存總數：" + totalRestocks + " 件");
        System.out.println("修改價格次數：" + totalPriceChanges + " 次");
        System.out.println("總營業額：" + totalRevenue + " 元");
        System.out.println("目前商品總數：" + productCount + " 項");
        
        // 顯示各商品庫存狀態
        System.out.println("\n各商品庫存狀態：");
        for (int i = 0; i < productCount; i++) {
            Product p = products[i];
            System.out.printf("  %s：%d 件 %s\n", 
                            p.getName(), 
                            p.getStock(),
                            p.isLowStock() ? "⚠️ 低庫存" : "✅ 充足");
        }
        
        System.out.println("\n感謝使用商品管理系統，再見！");
    }
    
    /**
     * 輔助方法：標準化字串（去除空白、轉小寫）
     */
    public static String normalizeString(String str) {
        if (str == null) {
            return "";
        }
        return str.replaceAll("\\s+", "").toLowerCase();
    }
    
    /**
     * 輔助方法：檢查商品名稱是否存在（忽略大小寫與空白）
     */
    public static boolean isProductNameExists(String name) {
        String normalizedName = normalizeString(name);
        for (int i = 0; i < productCount; i++) {
            String normalizedExisting = normalizeString(products[i].getName());
            if (normalizedExisting.equals(normalizedName)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 輔助方法：尋找商品索引（忽略大小寫與空白）
     */
    public static int findProductIndex(String name) {
        String normalizedName = normalizeString(name);
        for (int i = 0; i < productCount; i++) {
            String normalizedExisting = normalizeString(products[i].getName());
            if (normalizedExisting.equals(normalizedName)) {
                return i;
            }
        }
        return -1;
    }
    
    /*
     * ===== 測試案例 =====
     * 
     * 測試案例1：顯示全部商品
     *   操作：選擇功能 1
     *   預期：顯示 5 項初始商品
     * 
     * 測試案例2：完整名稱搜尋 - 成功
     *   操作：選擇功能 2，輸入 "Keyboard"
     *   預期：找到 Keyboard，顯示詳細資訊
     * 
     * 測試案例3：完整名稱搜尋 - 忽略大小寫
     *   操作：選擇功能 2，輸入 "keyboard"
     *   預期：找到 Keyboard（忽略大小寫）
     * 
     * 測試案例4：完整名稱搜尋 - 忽略空白
     *   操作：選擇功能 2，輸入 "USB Cable"
     *   預期：找到 USB Cable（忽略空白）
     * 
     * 測試案例5：完整名稱搜尋 - 失敗
     *   操作：選擇功能 2，輸入 "NotExist"
     *   預期：顯示「找不到符合的商品！」
     * 
     * 測試案例6：新增商品 - 成功
     *   操作：選擇功能 3，輸入 Speaker,1500,15
     *   預期：新增成功，商品數量變為 6
     * 
     * 測試案例7：新增商品 - 重複名稱
     *   操作：選擇功能 3，輸入 Mouse,100,10
     *   預期：顯示「商品名稱已存在！」
     * 
     * 測試案例8：新增商品 - 已滿
     *   操作：連續新增 5 次後再新增
     *   預期：顯示「商品空間已滿」
     * 
     * 測試案例9：出售商品 - 成功
     *   操作：選擇功能 4，輸入 Mouse，數量 5
     *   預期：庫存減少 5，營業額增加
     * 
     * 測試案例10：出售商品 - 庫存不足
     *   操作：選擇功能 4，輸入 Monitor，數量 10
     *   預期：顯示庫存不足錯誤
     * 
     * 測試案例11：補充庫存 - 成功
     *   操作：選擇功能 5，輸入 Monitor，數量 10
     *   預期：庫存增加 10
     * 
     * 測試案例12：修改價格 - 成功
     *   操作：選擇功能 6，輸入 Headset，新價格 1500
     *   預期：價格修改為 1500
     * 
     * 測試案例13：修改價格 - 無效價格
     *   操作：選擇功能 6，輸入 Headset，新價格 0
     *   預期：顯示錯誤訊息
     * 
     * 測試案例14：顯示低庫存商品
     *   操作：選擇功能 7
     *   預期：顯示庫存 < 10 的商品
     * 
     * 測試案例15：顯示全部庫存總價值
     *   操作：選擇功能 8
     *   預期：正確計算所有商品總價值
     * 
     * 測試案例16：結束並顯示摘要
     *   操作：選擇功能 9
     *   預期：顯示所有操作統計
     */
}
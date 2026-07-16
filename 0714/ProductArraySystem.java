import java.util.Scanner;

public class ProductArraySystem {
    
    // 商品資料（三個對應陣列）
    private static String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
    private static int[] prices = {890, 490, 5200, 250, 1290};
    private static int[] stocks = {12, 20, 5, 30, 8};
    
    // 操作記錄
    private static int totalPurchases = 0;
    private static int totalRestocks = 0;
    private static int totalRevenue = 0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        
        System.out.println("=== 商品分組管理系統 ===");
        System.out.println("歡迎使用商品管理系統！");
        
        do {
            displayMenu();
            System.out.print("請選擇功能（1-7）：");
            choice = sc.nextInt();
            System.out.println();
            
            switch (choice) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    queryProductById(sc);
                    break;
                case 3:
                    purchaseProduct(sc);
                    break;
                case 4:
                    restockProduct(sc);
                    break;
                case 5:
                    displayLowStockProducts();
                    break;
                case 6:
                    displayTotalInventoryValue();
                    break;
                case 7:
                    exitSystem();
                    break;
                default:
                    System.out.println("錯誤：請輸入 1 到 7 之間的選項！\n");
            }
        } while (choice != 7);
        
        sc.close();
    }
    
    /**
     * 顯示主選單
     */
    public static void displayMenu() {
        System.out.println("=== 主選單 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示庫存低商品（< 10）");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("7. 結束並顯示操作摘要");
    }
    
    /**
     * 1. 顯示全部商品
     */
    public static void displayAllProducts() {
        System.out.println("=== 全部商品列表 ===");
        System.out.println("編號\t商品名稱\t\t單價\t庫存");
        System.out.println("----\t--------\t\t----\t----");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d\t%-15s\t%d\t%d\n", 
                            (i + 1), names[i], prices[i], stocks[i]);
        }
        System.out.println();
    }
    
    /**
     * 2. 依商品編號查詢
     */
    public static void queryProductById(Scanner sc) {
        System.out.print("請輸入商品編號（1-" + names.length + "）：");
        int id = sc.nextInt();
        
        int index = id - 1; // 轉換成索引
        if (isValidIndex(index)) {
            System.out.println("=== 商品詳細資訊 ===");
            System.out.println("編號：" + id);
            System.out.println("名稱：" + names[index]);
            System.out.println("單價：" + prices[index]);
            System.out.println("庫存：" + stocks[index]);
        } else {
            System.out.println("錯誤：商品編號不存在！");
        }
        System.out.println();
    }
    
    /**
     * 3. 購買商品
     */
    public static void purchaseProduct(Scanner sc) {
        System.out.print("請輸入要購買的商品編號（1-" + names.length + "）：");
        int id = sc.nextInt();
        int index = id - 1;
        
        if (!isValidIndex(index)) {
            System.out.println("錯誤：商品編號不存在！\n");
            return;
        }
        
        System.out.print("請輸入購買數量：");
        int quantity = sc.nextInt();
        
        if (quantity <= 0) {
            System.out.println("錯誤：購買數量必須大於 0！\n");
            return;
        }
        
        if (quantity > stocks[index]) {
            System.out.println("錯誤：庫存不足！目前庫存為 " + stocks[index] + " 件\n");
            return;
        }
        
        // 執行購買
        stocks[index] -= quantity;
        int amount = prices[index] * quantity;
        totalPurchases += quantity;
        totalRevenue += amount;
        
        System.out.println("購買成功！");
        System.out.println("商品：" + names[index]);
        System.out.println("數量：" + quantity);
        System.out.println("總金額：" + amount);
        System.out.println("剩餘庫存：" + stocks[index] + "\n");
    }
    
    /**
     * 4. 補充商品庫存
     */
    public static void restockProduct(Scanner sc) {
        System.out.print("請輸入要補充的商品編號（1-" + names.length + "）：");
        int id = sc.nextInt();
        int index = id - 1;
        
        if (!isValidIndex(index)) {
            System.out.println("錯誤：商品編號不存在！\n");
            return;
        }
        
        System.out.print("請輸入補充數量：");
        int quantity = sc.nextInt();
        
        if (quantity <= 0) {
            System.out.println("錯誤：補充數量必須大於 0！\n");
            return;
        }
        
        // 執行補充
        stocks[index] += quantity;
        totalRestocks += quantity;
        
        System.out.println("補充成功！");
        System.out.println("商品：" + names[index]);
        System.out.println("補充數量：" + quantity);
        System.out.println("目前庫存：" + stocks[index] + "\n");
    }
    
    /**
     * 5. 顯示庫存低商品（庫存少於10）
     */
    public static void displayLowStockProducts() {
        System.out.println("=== 低庫存商品列表（庫存 < 10） ===");
        boolean found = false;
        
        System.out.println("編號\t商品名稱\t\t庫存");
        System.out.println("----\t--------\t\t----");
        for (int i = 0; i < stocks.length; i++) {
            if (stocks[i] < 10) {
                System.out.printf("%d\t%-15s\t%d\n", 
                                (i + 1), names[i], stocks[i]);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("所有商品庫存充足！");
        }
        System.out.println();
    }
    
    /**
     * 6. 顯示全部庫存總價值
     */
    public static void displayTotalInventoryValue() {
        System.out.println("=== 庫存總價值計算 ===");
        int totalValue = 0;
        
        System.out.println("編號\t商品名稱\t\t單價\t庫存\t小計");
        System.out.println("----\t--------\t\t----\t----\t----");
        for (int i = 0; i < names.length; i++) {
            int subtotal = prices[i] * stocks[i];
            totalValue += subtotal;
            System.out.printf("%d\t%-15s\t%d\t%d\t%d\n", 
                            (i + 1), names[i], prices[i], stocks[i], subtotal);
        }
        System.out.println("----------------------------------------");
        System.out.println("總庫存價值：" + totalValue + " 元\n");
    }
    
    /**
     * 7. 結束並顯示操作摘要
     */
    public static void exitSystem() {
        System.out.println("=== 操作摘要 ===");
        System.out.println("總購買次數：" + totalPurchases + " 件");
        System.out.println("總補充次數：" + totalRestocks + " 件");
        System.out.println("總營業額：" + totalRevenue + " 元");
        System.out.println("\n感謝使用商品管理系統，再見！");
    }
    
    /**
     * 驗證索引是否有效
     */
    public static boolean isValidIndex(int index) {
        return index >= 0 && index < names.length;
    }
}
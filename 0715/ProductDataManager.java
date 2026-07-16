import java.util.Scanner;

public class ProductDataManager {
    
    // 原始資料記錄
    private static String[] records = {
        "Keyboard,890,12",
        "Mouse,490,20",
        "Monitor,5200,5",
        "USB Cable,250,30",
        "Headset,1290,8"
    };
    
    // 解析後的資料陣列
    private static String[] names;
    private static int[] prices;
    private static int[] stocks;
    private static int productCount = 0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 初始化資料
        initializeData();
        
        int choice;
        System.out.println("=== 商品文字資料管理器 ===");
        System.out.println("歡迎使用商品資料管理系統！");
        
        do {
            displayMenu();
            System.out.print("請選擇功能（1-7）：");
            choice = sc.nextInt();
            sc.nextLine(); // 清除換行
            System.out.println();
            
            switch (choice) {
                case 1:
                    displayProductTable();
                    break;
                case 2:
                    searchProductByName(sc);
                    break;
                case 3:
                    searchPartialName(sc);
                    break;
                case 4:
                    displayLowStockProducts();
                    break;
                case 5:
                    displayTotalInventoryValue();
                    break;
                case 6:
                    addNewProduct(sc);
                    break;
                case 7:
                    System.out.println("感謝使用商品資料管理系統，再見！");
                    break;
                default:
                    System.out.println("錯誤：請輸入 1 到 7 之間的選項！\n");
            }
        } while (choice != 7);
        
        sc.close();
    }
    
    /**
     * 初始化並解析資料
     */
    public static void initializeData() {
        productCount = records.length;
        names = new String[productCount];
        prices = new int[productCount];
        stocks = new int[productCount];
        
        for (int i = 0; i < records.length; i++) {
            parseRecord(records[i], i);
        }
    }
    
    /**
     * 解析單筆記錄
     */
    public static void parseRecord(String record, int index) {
        try {
            // 1. 使用 split() 解析每筆資料
            String[] parts = record.split(",");
            
            // 檢查格式是否正確（必須有3個部分）
            if (parts.length != 3) {
                System.out.println("警告：記錄 \"" + record + "\" 格式錯誤，應為 3 個欄位");
                return;
            }
            
            // 2. 分別存入商品名稱、價格與庫存
            names[index] = parts[0].trim();
            
            try {
                prices[index] = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                System.out.println("警告：記錄 \"" + record + "\" 價格轉換錯誤：" + e.getMessage());
                prices[index] = 0;
            }
            
            try {
                stocks[index] = Integer.parseInt(parts[2].trim());
            } catch (NumberFormatException e) {
                System.out.println("警告：記錄 \"" + record + "\" 庫存轉換錯誤：" + e.getMessage());
                stocks[index] = 0;
            }
            
        } catch (Exception e) {
            System.out.println("警告：記錄 \"" + record + "\" 解析失敗：" + e.getMessage());
        }
    }
    
    /**
     * 顯示主選單
     */
    public static void displayMenu() {
        System.out.println("=== 主選單 ===");
        System.out.println("1. 顯示解析後的商品表格");
        System.out.println("2. 完整名稱搜尋");
        System.out.println("3. 部分名稱搜尋");
        System.out.println("4. 顯示庫存低商品（庫存 < 10）");
        System.out.println("5. 顯示庫存總價值");
        System.out.println("6. 新增商品記錄");
        System.out.println("7. 結束");
    }
    
    /**
     * 3. 顯示解析後的商品表格
     */
    public static void displayProductTable() {
        System.out.println("=== 商品表格 ===");
        System.out.println("編號\t商品名稱\t\t單價\t庫存");
        System.out.println("----\t--------\t\t----\t----");
        
        for (int i = 0; i < productCount; i++) {
            if (names[i] != null) {
                System.out.printf("%d\t%-15s\t%d\t%d\n", 
                                (i + 1), names[i], prices[i], stocks[i]);
            }
        }
        System.out.println();
    }
    
    /**
     * 4. 支援完整名稱搜尋
     */
    public static void searchProductByName(Scanner sc) {
        System.out.print("請輸入要搜尋的完整商品名稱：");
        String keyword = sc.nextLine().trim();
        
        boolean found = false;
        System.out.println("=== 搜尋結果 ===");
        
        for (int i = 0; i < productCount; i++) {
            if (names[i] != null && names[i].equalsIgnoreCase(keyword)) {
                System.out.println("找到商品！");
                System.out.println("編號：" + (i + 1));
                System.out.println("名稱：" + names[i]);
                System.out.println("單價：" + prices[i]);
                System.out.println("庫存：" + stocks[i]);
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
     * 5. 支援部分名稱搜尋
     */
    public static void searchPartialName(Scanner sc) {
        System.out.print("請輸入要搜尋的部分名稱關鍵字：");
        String keyword = sc.nextLine().trim();
        
        boolean found = false;
        int matchCount = 0;
        
        System.out.println("=== 搜尋結果 ===");
        System.out.println("編號\t商品名稱\t\t單價\t庫存");
        System.out.println("----\t--------\t\t----\t----");
        
        for (int i = 0; i < productCount; i++) {
            if (names[i] != null && names[i].toLowerCase().contains(keyword.toLowerCase())) {
                System.out.printf("%d\t%-15s\t%d\t%d\n", 
                                (i + 1), names[i], prices[i], stocks[i]);
                found = true;
                matchCount++;
            }
        }
        
        if (!found) {
            System.out.println("找不到符合的商品！");
        } else {
            System.out.println("共找到 " + matchCount + " 筆符合的商品");
        }
        System.out.println();
    }
    
    /**
     * 6. 顯示庫存低商品（庫存 < 10）
     */
    public static void displayLowStockProducts() {
        System.out.println("=== 低庫存商品列表（庫存 < 10） ===");
        boolean found = false;
        
        System.out.println("編號\t商品名稱\t\t庫存");
        System.out.println("----\t--------\t\t----");
        
        for (int i = 0; i < productCount; i++) {
            if (names[i] != null && stocks[i] < 10) {
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
     * 7. 顯示庫存總價值
     */
    public static void displayTotalInventoryValue() {
        System.out.println("=== 庫存總價值計算 ===");
        int totalValue = 0;
        
        System.out.println("編號\t商品名稱\t\t單價\t庫存\t小計");
        System.out.println("----\t--------\t\t----\t----\t----");
        
        for (int i = 0; i < productCount; i++) {
            if (names[i] != null) {
                int subtotal = prices[i] * stocks[i];
                totalValue += subtotal;
                System.out.printf("%d\t%-15s\t%d\t%d\t%d\n", 
                                (i + 1), names[i], prices[i], stocks[i], subtotal);
            }
        }
        System.out.println("----------------------------------------");
        System.out.println("總庫存價值：" + totalValue + " 元\n");
    }
    
    /**
     * 8. 允許輸入新商品記錄並驗證格式
     */
    public static void addNewProduct(Scanner sc) {
        System.out.println("=== 新增商品記錄 ===");
        System.out.print("請輸入新商品記錄（格式：名稱,價格,庫存）：");
        String newRecord = sc.nextLine().trim();
        
        // 驗證格式
        if (!validateRecordFormat(newRecord)) {
            System.out.println("錯誤：格式不正確，請使用「名稱,價格,庫存」的格式\n");
            return;
        }
        
        // 擴充陣列並加入新記錄
        addRecordToArray(newRecord);
        System.out.println("商品新增成功！\n");
    }
    
    /**
     * 驗證記錄格式
     */
    public static boolean validateRecordFormat(String record) {
        if (record == null || record.isEmpty()) {
            System.out.println("錯誤：記錄不能為空");
            return false;
        }
        
        String[] parts = record.split(",");
        
        // 檢查欄位數量
        if (parts.length != 3) {
            System.out.println("錯誤：應包含 3 個欄位（名稱,價格,庫存），實際為 " + parts.length + " 個");
            return false;
        }
        
        // 檢查價格是否為有效數字
        try {
            int price = Integer.parseInt(parts[1].trim());
            if (price < 0) {
                System.out.println("錯誤：價格不能為負數");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("錯誤：價格格式錯誤，請輸入有效的整數");
            return false;
        }
        
        // 檢查庫存是否為有效數字
        try {
            int stock = Integer.parseInt(parts[2].trim());
            if (stock < 0) {
                System.out.println("錯誤：庫存不能為負數");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("錯誤：庫存格式錯誤，請輸入有效的整數");
            return false;
        }
        
        return true;
    }
    
    /**
     * 將新記錄加入陣列
     */
    public static void addRecordToArray(String record) {
        // 擴充陣列
        String[] newNames = new String[productCount + 1];
        int[] newPrices = new int[productCount + 1];
        int[] newStocks = new int[productCount + 1];
        
        // 複製原有資料
        for (int i = 0; i < productCount; i++) {
            newNames[i] = names[i];
            newPrices[i] = prices[i];
            newStocks[i] = stocks[i];
        }
        
        // 解析並加入新記錄
        String[] parts = record.split(",");
        newNames[productCount] = parts[0].trim();
        newPrices[productCount] = Integer.parseInt(parts[1].trim());
        newStocks[productCount] = Integer.parseInt(parts[2].trim());
        
        // 更新陣列和計數
        names = newNames;
        prices = newPrices;
        stocks = newStocks;
        productCount++;
        
        // 同時更新 records 陣列
        String[] newRecords = new String[records.length + 1];
        System.arraycopy(records, 0, newRecords, 0, records.length);
        newRecords[records.length] = record;
        records = newRecords;
    }
    
    /*
     * ===== 測試案例 =====
     * 
     * 測試案例1：解析原始資料 - 成功
     *   輸入：Keyboard,890,12
     *   預期：名稱=Keyboard, 價格=890, 庫存=12
     * 
     * 測試案例2：解析原始資料 - 名稱含空白
     *   輸入：USB Cable,250,30
     *   預期：名稱=USB Cable, 價格=250, 庫存=30
     * 
     * 測試案例3：完整名稱搜尋 - 成功
     *   輸入：Monitor
     *   預期：找到 Monitor 商品
     * 
     * 測試案例4：完整名稱搜尋 - 忽略大小寫
     *   輸入：keyboard
     *   預期：找到 Keyboard（忽略大小寫）
     * 
     * 測試案例5：部分名稱搜尋 - 多筆結果
     *   輸入：er
     *   預期：找到 Keyboard 和 Headset
     * 
     * 測試案例6：部分名稱搜尋 - 無結果
     *   輸入：XYZ
     *   預期：顯示「找不到符合的商品！」
     * 
     * 測試案例7：新增商品 - 格式正確
     *   輸入：Speaker,1500,15
     *   預期：新增成功，顯示在表格中
     * 
     * 測試案例8：新增商品 - 格式錯誤（欄位不足）
     *   輸入：Speaker,1500
     *   預期：顯示錯誤訊息，程式不中斷
     * 
     * 測試案例9：新增商品 - 價格格式錯誤
     *   輸入：Speaker,abc,15
     *   預期：顯示價格轉換錯誤，程式不中斷
     * 
     * 測試案例10：新增商品 - 庫存為負數
     *   輸入：Speaker,1500,-5
     *   預期：顯示庫存不能為負數
     * 
     * 測試案例11：低庫存顯示
     *   預期：顯示 Monitor(5) 和 Headset(8)
     * 
     * 測試案例12：庫存總價值計算
     *   預期：顯示所有商品的小計和總價值
     */
}
import java.util.Scanner;

public class ProductSearchSystem {
    
    // 商品資料（沿用0714的資料）
    private static String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
    private static int[] prices = {890, 490, 5200, 250, 1290};
    private static int[] stocks = {12, 20, 5, 30, 8};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        
        System.out.println("=== 商品名稱搜尋系統 ===");
        System.out.println("歡迎使用商品搜尋系統！");
        
        do {
            displayMenu();
            System.out.print("請選擇功能（1-6）：");
            choice = sc.nextInt();
            sc.nextLine(); // 清除換行
            System.out.println();
            
            switch (choice) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    searchExactName(sc);
                    break;
                case 3:
                    searchPartialName(sc);
                    break;
                case 4:
                    displayLastProduct();
                    break;
                case 5:
                    displayFirstOccurrence(sc);
                    break;
                case 6:
                    System.out.println("感謝使用商品搜尋系統，再見！");
                    break;
                default:
                    System.out.println("錯誤：請輸入 1 到 6 之間的選項！\n");
            }
        } while (choice != 6);
        
        sc.close();
    }
    
    /**
     * 顯示主選單
     */
    public static void displayMenu() {
        System.out.println("=== 主選單 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 完整名稱搜尋（忽略大小寫與空白）");
        System.out.println("3. 部分名稱搜尋（可顯示多筆結果）");
        System.out.println("4. 顯示名稱最長的商品");
        System.out.println("5. 顯示商品名稱與搜尋關鍵字首次出現的位置");
        System.out.println("6. 結束");
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
     * 2. 完整名稱搜尋（忽略大小寫與空白）
     */
    public static void searchExactName(Scanner sc) {
        System.out.print("請輸入要搜尋的完整商品名稱：");
        String keyword = sc.nextLine();
        
        // 忽略大小寫與空白進行比對
        String normalizedKeyword = normalizeString(keyword);
        boolean found = false;
        
        System.out.println("=== 搜尋結果 ===");
        for (int i = 0; i < names.length; i++) {
            String normalizedName = normalizeString(names[i]);
            if (normalizedName.equals(normalizedKeyword)) {
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
     * 3. 部分名稱搜尋（可顯示多筆結果）
     */
    public static void searchPartialName(Scanner sc) {
        System.out.print("請輸入要搜尋的部分名稱關鍵字：");
        String keyword = sc.nextLine();
        
        // 忽略大小寫與空白進行比對
        String normalizedKeyword = normalizeString(keyword);
        boolean found = false;
        int matchCount = 0;
        
        System.out.println("=== 搜尋結果 ===");
        System.out.println("編號\t商品名稱\t\t單價\t庫存");
        System.out.println("----\t--------\t\t----\t----");
        
        for (int i = 0; i < names.length; i++) {
            String normalizedName = normalizeString(names[i]);
            if (normalizedName.contains(normalizedKeyword)) {
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
     * 4. 顯示名稱最長的商品
     */
    public static void displayLastProduct() {
        System.out.println("=== 名稱最長的商品 ===");
        
        int maxLength = 0;
        int maxIndex = 0;
        boolean hasMultiple = false;
        
        // 找出最長名稱的長度
        for (int i = 0; i < names.length; i++) {
            if (names[i].length() > maxLength) {
                maxLength = names[i].length();
                maxIndex = i;
                hasMultiple = false;
            } else if (names[i].length() == maxLength && i != maxIndex) {
                hasMultiple = true;
            }
        }
        
        // 顯示所有最長名稱的商品
        System.out.println("最長名稱長度：" + maxLength + " 個字元");
        System.out.println("編號\t商品名稱\t\t長度");
        System.out.println("----\t--------\t\t----");
        
        for (int i = 0; i < names.length; i++) {
            if (names[i].length() == maxLength) {
                System.out.printf("%d\t%-15s\t%d\n", 
                                (i + 1), names[i], names[i].length());
            }
        }
        
        if (hasMultiple) {
            System.out.println("注意：有多個商品名稱同為最長！");
        }
        System.out.println();
    }
    
    /**
     * 5. 顯示商品名稱與搜尋關鍵字首次出現的位置
     */
    public static void displayFirstOccurrence(Scanner sc) {
        System.out.print("請輸入要搜尋的關鍵字：");
        String keyword = sc.nextLine();
        
        System.out.println("=== 關鍵字首次出現位置 ===");
        System.out.println("關鍵字：「" + keyword + "」");
        System.out.println("編號\t商品名稱\t\t首次出現位置");
        System.out.println("----\t--------\t\t------------");
        
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            // 忽略大小寫進行搜尋
            String lowerName = names[i].toLowerCase();
            String lowerKeyword = keyword.toLowerCase();
            int position = lowerName.indexOf(lowerKeyword);
            
            if (position != -1) {
                System.out.printf("%d\t%-15s\t%d\n", 
                                (i + 1), names[i], position);
                found = true;
            } else {
                System.out.printf("%d\t%-15s\t未找到\n", 
                                (i + 1), names[i]);
            }
        }
        
        if (!found) {
            System.out.println("在所有商品名稱中皆未找到該關鍵字！");
        }
        System.out.println();
    }
    
    /**
     * 標準化字串：移除空白並轉為小寫
     */
    public static String normalizeString(String str) {
        if (str == null) {
            return "";
        }
        // 移除所有空白（包括前後和內部空白）
        return str.replaceAll("\\s+", "").toLowerCase();
    }
    
    /*
     * ===== 測試案例 =====
     * 
     * 測試案例1：完整名稱搜尋 - 成功
     *   輸入：Keyboard
     *   預期：找到 Keyboard，顯示完整資訊
     * 
     * 測試案例2：完整名稱搜尋 - 忽略大小寫
     *   輸入：keyboard
     *   預期：找到 Keyboard（忽略大小寫）
     * 
     * 測試案例3：完整名稱搜尋 - 忽略空白
     *   輸入：USB Cable
     *   預期：找到 USB Cable（忽略空白）
     * 
     * 測試案例4：完整名稱搜尋 - 失敗
     *   輸入：Monitor2
     *   預期：顯示「找不到符合的商品！」
     * 
     * 測試案例5：部分名稱搜尋 - 多筆結果
     *   輸入：er
     *   預期：找到 Keyboard 和 Headset（包含 "er"）
     * 
     * 測試案例6：部分名稱搜尋 - 忽略大小寫
     *   輸入：key
     *   預期：找到 Keyboard（忽略大小寫）
     * 
     * 測試案例7：名稱最長的商品
     *   預期：Keyboard 和 Headset（8個字元）
     * 
     * 測試案例8：關鍵字首次出現位置
     *   輸入：e
     *   預期：顯示每個商品名稱中 'e' 首次出現的位置
     * 
     * 測試案例9：關鍵字首次出現位置 - 未找到
     *   輸入：z
     *   預期：所有商品顯示「未找到」
     */
}
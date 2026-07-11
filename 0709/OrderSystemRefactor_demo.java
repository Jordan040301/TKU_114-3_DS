import java.util.Scanner;

public class OrderSystemRefactor_demo {
    
    // ============================================
    // 主程式
    // ============================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int totalItems = 0;      // 總數量
        int totalAmount = 0;     // 總金額
        int orderCount = 0;      // 訂單筆數
        
        printWelcome();          // 顯示歡迎訊息
        
        while (true) {
            printMenu();         // 顯示選單
            
            int option = sc.nextInt();
            
            // 選擇 0 時結帳
            if (option == 0) {
                break;
            }
            
            // 檢查選項是否合法 (1-3)
            if (option < 1 || option > 3) {
                System.out.println("❌ 無效選項，請輸入 0-3！\n");
                continue;
            }
            
            // 取得商品名稱和價格
            String itemName = getItemName(option);
            int price = getPrice(option);
            
            // 讀取有效數量
            int quantity = readValidQuantity(sc);
            
            // 計算小計
            int subtotal = calculateSubtotal(price, quantity);
            
            // 累加總數量與總金額
            totalItems += quantity;
            totalAmount += subtotal;
            orderCount++;
            
            // 顯示本次訂購明細
            printOrderDetail(itemName, quantity, price, subtotal, totalAmount);
        }
        
        // 列印收據
        printReceipt(orderCount, totalItems, totalAmount);
        
        sc.close();
    }
    
    // ============================================
    // 方法 1：顯示歡迎訊息
    // ============================================
    public static void printWelcome() {
        System.out.println("====================================");
        System.out.println("      歡迎光臨 飲料點餐系統         ");
        System.out.println("====================================");
        System.out.println();
    }
    
    // ============================================
    // 方法 2：顯示選單
    // ============================================
    public static void printMenu() {
        System.out.println("=== 飲料菜單 ===");
        System.out.println("1. 紅茶 - $30");
        System.out.println("2. 綠茶 - $35");
        System.out.println("3. 咖啡 - $50");
        System.out.println("0. 結帳離開");
        System.out.println("================");
        System.out.print("請選擇飲料（0-3）：");
    }
    
    // ============================================
    // 方法 3：取得商品名稱
    // ============================================
    public static String getItemName(int option) {
        switch (option) {
            case 1: return "紅茶";
            case 2: return "綠茶";
            case 3: return "咖啡";
            default: return "";
        }
    }
    
    // ============================================
    // 方法 4：取得商品價格（有參數 + 有回傳值）
    // ============================================
    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 50;
            default: return 0;
        }
    }
    
    // ============================================
    // 方法 5：讀取合法數量（有參數 + 有回傳值 + 輸入驗證）
    // ============================================
    public static int readValidQuantity(Scanner sc) {
        int quantity;
        while (true) {
            System.out.print("請輸入數量：");
            quantity = sc.nextInt();
            if (quantity > 0) {
                break;
            }
            System.out.println("❌ 數量必須大於 0，請重新輸入！");
        }
        return quantity;
    }
    
    // ============================================
    // 方法 6：計算小計（有參數 + 有回傳值）
    // ============================================
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }
    
    // ============================================
    // 方法 7：顯示訂購明細
    // ============================================
    public static void printOrderDetail(String itemName, int quantity, 
                                         int price, int subtotal, int totalAmount) {
        System.out.println("✅ 已加入 " + quantity + " 杯 " + itemName);
        System.out.println("   單價：$" + price);
        System.out.println("   小計：$" + subtotal);
        System.out.println("   目前總金額：$" + totalAmount);
        System.out.println();
    }
    
    // ============================================
    // 方法 8：列印收據（有參數）
    // ============================================
    public static void printReceipt(int orderCount, int totalItems, int totalAmount) {
        System.out.println();
        System.out.println("====================================");
        System.out.println("          訂單收據                  ");
        System.out.println("====================================");
        System.out.println("總筆數：" + orderCount + " 筆");
        System.out.println("總數量：" + totalItems + " 杯");
        System.out.println("總金額：$" + totalAmount);
        System.out.println("====================================");
        System.out.println("感謝您的購買，歡迎再次光臨！");
    }
}
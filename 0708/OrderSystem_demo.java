import java.util.Scanner;

public class OrderSystem_demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int totalItems = 0;      // 總數量
        int totalAmount = 0;     // 總金額
        int orderCount = 0;      // 訂單筆數
        
        System.out.println("====================================");
        System.out.println("      歡迎光臨 飲料點餐系統         ");
        System.out.println("====================================");
        System.out.println();
        
        while (true) {
            // 1. 顯示選單
            System.out.println("=== Order Menu ===");
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee  $50");
            System.out.println("0. Checkout");
            System.out.println("================");
            System.out.print("請輸入選項：");
            
            int option = sc.nextInt();
            
            // 7. 選擇 0 時結帳
            if (option == 0) {
                break;
            }
            
            // 檢查選項是否合法（1-3）
            if (option < 1 || option > 3) {
                System.out.println("❌ 無效選項，請輸入 0-3！\n");
                continue;  // 回到迴圈開頭
            }
            
            // 取得商品名稱和價格
            String itemName = getItemName(option);
            int price = getPrice(option);
            
            // 3. 要求輸入數量
            System.out.print("請輸入 " + itemName + " 數量：");
            int quantity = sc.nextInt();
            
            // 4. 數量必須大於 0（輸入驗證）
            while (quantity <= 0) {
                System.out.print("❌ 數量必須大於 0，請重新輸入：");
                quantity = sc.nextInt();
            }
            
            // 5. 計算本次小計
            int subtotal = price * quantity;
            
            // 6. 累加總數量與總金額
            totalItems += quantity;
            totalAmount += subtotal;
            orderCount++;
            
            // 顯示本次訂購明細
            System.out.println("Subotal：" + totalAmount);
            System.out.println();
        }
        
        // 7. 產出總數量與總金額
        System.out.println();
        System.out.println("====================================");
        System.out.println("          Receipt                  ");
        System.out.println("====================================");
        System.out.println("Total items：" + totalItems );
        System.out.println("Total：" + totalAmount);
        System.out.println("====================================");
        System.out.println("感謝您的購買，歡迎再次光臨！");
        
        sc.close();
    }
    
    // 根據選項取得商品名稱
    public static String getItemName(int option) {
        switch (option) {
            case 1: return "紅茶";
            case 2: return "綠茶";
            case 3: return "咖啡";
            default: return "";
        }
    }
    
    // 根據選項取得商品價格
    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 50;
            default: return 0;
        }
    }
}

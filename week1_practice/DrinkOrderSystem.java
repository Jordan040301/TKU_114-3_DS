import java.util.Scanner;

public class DrinkOrderSystem {
    
    // ============================================
    // 主程式
    // ============================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 各商品銷售數量
        int blackTeaCount = 0;    // 紅茶
        int greenTeaCount = 0;    // 綠茶
        int milkTeaCount = 0;     // 奶茶
        int coffeeCount = 0;      // 咖啡
        
        int totalItems = 0;       // 總杯數
        int totalAmount = 0;      // 折扣前總金額
        
        System.out.println("====================================");
        System.out.println("      歡迎光臨 飲料點餐系統         ");
        System.out.println("====================================");
        System.out.println("【注意事項】");
        System.out.println("  • 消費滿 $300 可享 9 折優惠");
        System.out.println("  • 輸入 0 結帳");
        System.out.println("====================================");
        System.out.println();
        
        while (true) {
            printMenu();          // 顯示選單
            
            int option = sc.nextInt();
            
            // 選擇 0 時結帳
            if (option == 0) {
                break;
            }
            
            // 檢查選項是否合法 (1-4)
            if (option < 1 || option > 4) {
                System.out.println("❌ 無效選項，請輸入 0-4！\n");
                continue;
            }
            
            // 取得商品名稱和價格
            String itemName = getItemName(option);
            int price = getPrice(option);
            
            // 讀取有效數量
            int quantity = readValidQuantity(sc);
            
            // 計算本次小計
            int subtotal = calculateSubtotal(price, quantity);
            
            // 累加統計
            totalItems += quantity;
            totalAmount += subtotal;
            
            // 更新各商品銷售數量
            switch (option) {
                case 1: blackTeaCount += quantity; break;
                case 2: greenTeaCount += quantity; break;
                case 3: milkTeaCount += quantity; break;
                case 4: coffeeCount += quantity; break;
            }
            
            // 顯示本次訂購明細
            System.out.println("✅ 已加入 " + quantity + " 杯 " + itemName);
            System.out.println("   單價：$" + price);
            System.out.println("   小計：$" + subtotal);
            System.out.println("   目前總金額：$" + totalAmount);
            System.out.println();
        }
        
        // 結帳：計算折扣並輸出收據
        int discountedTotal = calculateDiscountedTotal(totalAmount);
        boolean hasDiscount = (totalAmount >= 300);
        
        printReceipt(blackTeaCount, greenTeaCount, milkTeaCount, coffeeCount,
                     totalItems, totalAmount, discountedTotal, hasDiscount);
        
        sc.close();
    }
    
    // ============================================
    // 方法 1：顯示選單
    // ============================================
    /**
     * 顯示飲料菜單
     */
    public static void printMenu() {
        System.out.println("=== 飲料菜單 ===");
        System.out.println("1. 紅茶 - $30");
        System.out.println("2. 綠茶 - $35");
        System.out.println("3. 奶茶 - $45");
        System.out.println("4. 咖啡 - $50");
        System.out.println("0. 結帳離開");
        System.out.println("================");
        System.out.print("請選擇飲料（0-4）：");
    }
    
    // ============================================
    // 方法 2：取得商品價格
    // ============================================
    /**
     * 根據選項取得商品價格
     * @param option 選項編號 (1-4)
     * @return 商品價格
     */
    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 45;
            case 4: return 50;
            default: return 0;
        }
    }
    
    // ============================================
    // 方法 3：取得商品名稱
    // ============================================
    /**
     * 根據選項取得商品名稱
     * @param option 選項編號 (1-4)
     * @return 商品名稱
     */
    public static String getItemName(int option) {
        switch (option) {
            case 1: return "紅茶";
            case 2: return "綠茶";
            case 3: return "奶茶";
            case 4: return "咖啡";
            default: return "";
        }
    }
    
    // ============================================
    // 方法 4：讀取有效數量（含輸入驗證）
    // ============================================
    /**
     * 讀取並驗證數量（必須大於 0）
     * @param sc Scanner 物件
     * @return 有效數量
     */
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
    // 方法 5：計算小計
    // ============================================
    /**
     * 計算商品小計
     * @param price 單價
     * @param quantity 數量
     * @return 小計金額
     */
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }
    
    // ============================================
    // 方法 6：計算折扣後總金額
    // ============================================
    /**
     * 計算折扣後總金額（滿 $300 打 9 折）
     * @param totalAmount 折扣前總金額
     * @return 折扣後總金額
     */
    public static int calculateDiscountedTotal(int totalAmount) {
        if (totalAmount >= 300) {
            return (int)(totalAmount * 0.9);
        }
        return totalAmount;
    }
    
    // ============================================
    // 方法 7：輸出完整收據
    // ============================================
    /**
     * 列印完整訂單收據
     * @param blackTeaCount 紅茶杯數
     * @param greenTeaCount 綠茶杯數
     * @param milkTeaCount 奶茶杯數
     * @param coffeeCount 咖啡杯數
     * @param totalItems 總杯數
     * @param totalAmount 折扣前總金額
     * @param discountedTotal 折扣後總金額
     * @param hasDiscount 是否有折扣
     */
    public static void printReceipt(int blackTeaCount, int greenTeaCount, 
                                     int milkTeaCount, int coffeeCount,
                                     int totalItems, int totalAmount, 
                                     int discountedTotal, boolean hasDiscount) {
        System.out.println();
        System.out.println("====================================");
        System.out.println("          訂單收據                  ");
        System.out.println("====================================");
        
        // 各商品銷售杯數
        System.out.println("【銷售明細】");
        if (blackTeaCount >= 0) {
            System.out.println("  紅茶：" + blackTeaCount + " 杯");
        }
        if (greenTeaCount >= 0) {
            System.out.println("  綠茶：" + greenTeaCount + " 杯");
        }
        if (milkTeaCount >= 0) {
            System.out.println("  奶茶：" + milkTeaCount + " 杯");
        }
        if (coffeeCount >= 0) {
            System.out.println("  咖啡：" + coffeeCount + " 杯");
        }
        if (blackTeaCount == 0 && greenTeaCount == 0 && 
            milkTeaCount == 0 && coffeeCount == 0) {
            System.out.println("  （無任何商品）");
        }
        
        System.out.println("------------------------------------");
        System.out.println("總杯數：" + totalItems + " 杯");
        System.out.println("折扣前總金額：$" + totalAmount);
        
        // 是否有折扣
        if (hasDiscount) {
            System.out.println("折扣：9 折（滿 $300 優惠）");
            System.out.println("折扣後總金額：$" + discountedTotal);
        } else {
            System.out.println("折扣：無（未滿 $300）");
            System.out.println("折扣後總金額：$" + discountedTotal);
        }
        
        System.out.println("====================================");
        System.out.println("感謝您的購買，歡迎再次光臨！");
    }
}
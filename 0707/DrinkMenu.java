import java.util.Scanner;

public class DrinkMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 飲料資訊陣列
        String[] drinkNames = {"Black tea", "Green tea", "Coffee"};
        int[] drinkPrices = {45, 50, 60};
        int[] drinkCounts = {0, 0, 0};  // 記錄每種飲料的數量
        
        System.out.println("=== 飲料菜單 ===");
        for (int i = 0; i < drinkNames.length; i++) {
            System.out.println((i + 1) + ". " + drinkNames[i] + " - $" + drinkPrices[i]);
        }
        System.out.println("0. 結帳");
        System.out.println("==================");
        
        int total = 0;
        String orderDetails = "";
        
        while (true) {
            System.out.print("請輸入選項（輸入 0 結帳）：");
            int option = sc.nextInt();
            
            if (option == 0) {
                break;
            }
            
            if (option < 1 || option > 3) {
                System.out.println("❌ 無效選項，請重新輸入\n");
                continue;
            }
            
            System.out.print("請輸入數量：");
            int quantity = sc.nextInt();
            
            int index = option - 1;  // 陣列索引從 0 開始
            drinkCounts[index] += quantity;
            int subtotal = drinkPrices[index] * quantity;
            total += subtotal;
            
            System.out.println("✓ 已加入 " + quantity + " 杯 " + drinkNames[index]);
            orderDetails += drinkNames[index] + " x" + quantity + 
                          "  $" + drinkPrices[index] + " x " + quantity + 
                          " = $" + subtotal + "\n";
            System.out.println();
        }
        
        // 顯示結帳明細
        System.out.println("\n=== 結帳明細 ===");
        System.out.println(orderDetails);
        System.out.println("------------------");
        
        int totalCups = 0;
        for (int count : drinkCounts) {
            totalCups += count;
        }
        System.out.println("總杯數：" + totalCups + " 杯");
        System.out.println("總金額：$" + total);
        System.out.println("==================");
        
        // 顯示各品項統計
        System.out.println("\n=== 品項統計 ===");
        for (int i = 0; i < drinkNames.length; i++) {
            if (drinkCounts[i] > 0) {
                System.out.println(drinkNames[i] + "：" + drinkCounts[i] + " 杯");
            }
        }
        
        sc.close();
    }
}
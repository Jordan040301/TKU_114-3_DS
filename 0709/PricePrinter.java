import java.util.Scanner;

public class PricePrinter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalAmount = 0;  // 總金額
        
        System.out.println("=== 購物系統 ===");
        System.out.println("輸入 0 結束輸入");
        System.out.println("================");
        System.out.println();
        
        int count = 1;
        while (true) {
            System.out.println("--- 第 " + count + " 項商品 ---");
            
            System.out.print("請輸入商品名稱（輸入 0 結束）：");
            String name = sc.nextLine();
            
            // 檢查是否結束
            if (name.equals("0")) {
                break;
            }
            
            System.out.print("請輸入商品單價：");
            int price = sc.nextInt();
            
            System.out.print("請輸入商品數量：");
            int quantity = sc.nextInt();
            sc.nextLine();  // 清除換行符號
            
            // 累加總金額
            totalAmount += price * quantity;
            
            // 印出商品資訊
            printItem(name, price, quantity);
            
            count++;
        }
        
        System.out.println("=================");
        System.out.println("總金額：$" + totalAmount);
        System.out.println("感謝您的購買！");
        
        sc.close();
    }

    public static void printItem(String itemName, int price, int quantity) {
        int total = price * quantity;
        System.out.println("商品名稱：" + itemName);
        System.out.println("  單價：$" + price);
        System.out.println("  數量：" + quantity);
        System.out.println("  小計：$" + total);
        System.out.println();
    }
}
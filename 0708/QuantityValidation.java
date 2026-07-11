import java.util.Scanner;

public class QuantityValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String continueInput;
        
        System.out.println("=== 購物系統 ===");
        System.out.println("數量必須大於 0");
        System.out.println("================");
        System.out.println();
        
        int totalItems = 0;
        int totalAmount = 0;
        int itemCount = 1;
        
        do {
            System.out.println("--- 第 " + itemCount + " 項商品 ---");
            
            System.out.print("請輸入商品名稱：");
            String name = sc.nextLine();
            
            // 驗證數量（必須大於 0）
            int quantity;
            while (true) {
                System.out.print("請輸入 " + name + " 的數量（必須大於 0）：");
                quantity = sc.nextInt();
                
                if (quantity > 0) {
                    break;  // 輸入正確，離開迴圈
                }
                System.out.println("❌ 數量必須大於 0，請重新輸入！");
            }
            
            System.out.print("請輸入單價：");
            int price = sc.nextInt();
            sc.nextLine();  // 清除換行符號
            
            int subtotal = price * quantity;
            totalItems += quantity;
            totalAmount += subtotal;
            
            System.out.println("✅ " + name + " x " + quantity + " = $" + subtotal);
            System.out.println();
            
            System.out.print("是否繼續輸入？（y/n）：");
            continueInput = sc.nextLine();
            System.out.println();
            
            itemCount++;
            
        } while (continueInput.equalsIgnoreCase("y"));
        
        System.out.println("=== 購物總結 ===");
        System.out.println("總品項：" + (itemCount - 1) + " 項");
        System.out.println("總數量：" + totalItems + " 個");
        System.out.println("總金額：$" + totalAmount);
        
        sc.close();
    }
}
import java.util.Scanner;

public class SubtotalCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== 購物結算系統 ===");
        System.out.println();
        
        System.out.print("請輸入商品名稱：");
        String name = sc.nextLine();

        System.out.print("請輸入商品單價：");
        int price = sc.nextInt();
        
        System.out.print("請輸入商品數量：");
        int quantity = sc.nextInt();
        
        System.out.print("請輸入折扣（%）（例如：10 表示 9 折）：");
        int discount = sc.nextInt();
        
        // 計算小計
        int subtotal = calculateSubtotal(price, quantity);
        
        // 計算折扣後金額
        int discountedPrice = calculateDiscountedPrice(subtotal, discount);
        
        System.out.println();
        System.out.println("=== 計算結果 ===");
        System.out.println("名稱：" + name);
        System.out.println("單價：$" + price);
        System.out.println("數量：" + quantity);
        System.out.println("小計：$" + subtotal);
        System.out.println("折扣：" + discount + "%");
        System.out.println("折扣後金額：$" + discountedPrice);
        
        sc.close();
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }
    
    public static int calculateDiscountedPrice(int subtotal, int discount) {
        return subtotal * (100 - discount) / 100;
    }
}
import java.util.Scanner;

public class WhileInputDemo1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== 數字輸入器 ===");
        System.out.println("輸入 0 結束程式");
        System.out.println("===================");
        
        while (true) {
            System.out.print("請輸入一個整數：");
            
            // 檢查是否為整數
            if (sc.hasNextInt()) {
                int number = sc.nextInt();
                
                if (number == 0) {
                    break;
                }
                
                System.out.println("您輸入的是：" + number);
            } else {
                System.out.println("❌ 請輸入有效的整數！");
                sc.next();  // 清除無效輸入
            }
        }
        
        System.out.println("程式結束！");
        sc.close();
    }
}
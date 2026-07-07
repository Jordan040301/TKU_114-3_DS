import java.util.Scanner;

public class WhileInputDemo2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number;
        
        System.out.print("請輸入正整數（輸入 0 或負數結束）：");
        number = sc.nextInt();
        
        while (number > 0) {
            System.out.println("✓ " + number + " 是正整數");
            System.out.print("請輸入下一個數字：");
            number = sc.nextInt();
        }
        
        System.out.println("輸入 " + number + "，程式結束！");
        sc.close();
    }
}
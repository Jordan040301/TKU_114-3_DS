import java.util.Scanner;

public class WhileInputDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("請輸入開始數字：");
        int start = sc.nextInt();
        
        System.out.print("請輸入結束數字：");
        int end = sc.nextInt();
        
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        
        System.out.println("Sum (" + start + " 到 " + end + "): " + sum);
        
        sc.close();
    }
}
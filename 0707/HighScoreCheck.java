import java.util.Scanner;

public class HighScoreCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("請輸入分數：");
        int score = sc.nextInt();
        
        System.out.println("Score: " + score);
        
        if (score >= 90) {
            System.out.println("Excellent");
        } else {
            System.out.println("Keep trying!");
        }
        
        sc.close();
    }
}
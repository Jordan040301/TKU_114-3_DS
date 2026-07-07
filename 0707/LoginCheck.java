import java.util.Scanner;  // ⭐ 加上這行

public class LoginCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 正確的帳號密碼（預設值）
        String correctUsername = "admin";
        String correctPassword = "1234";
        
        // 讓使用者輸入
        System.out.print("請輸入帳號：");
        String inputUsername = sc.nextLine();
        
        System.out.print("請輸入密碼：");
        String inputPassword = sc.nextLine();
        
        // 判斷是否登入成功
        boolean loginSuccess = correctUsername.equals(inputUsername) &&
                               correctPassword.equals(inputPassword);
        
        // 輸出結果
        System.out.println("Username Correct: " + correctUsername.equals(inputUsername));
        System.out.println("Password Correct: " + correctPassword.equals(inputPassword));
        System.out.println("Login Success: " + loginSuccess);
        
        sc.close();
    }
}
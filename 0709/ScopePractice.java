import java.util.Scanner;

public class ScopePractice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("請輸入你的名字：");
        String name = sc.nextLine();  // 在 main 中宣告 name
        
        System.out.println("\n=== 結果 ===");
        printName(name);  // 傳遞 name 給方法
        
        sc.close();
    }

    public static void printName(String name) {
        // 這個 name 是參數，和 main 中的 name 是不同變數
        // 但值是一樣的（因為傳遞過來了)
        System.out.println("Name: " + name);
        System.out.println("Hello, " + name + "!");
    }
}
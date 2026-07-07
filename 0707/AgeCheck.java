import java.util.Scanner;  // 1. 匯入 Scanner

public class AgeCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // 2. 建立 Scanner 物件
        
        System.out.print("請輸入姓名：");      // 3. 修正語法（去掉 s:）
        String name = sc.nextLine();           // 讀取姓名
        
        System.out.print("請輸入年齡：");
        int age = sc.nextInt();                // 4. 讓使用者輸入年齡
        
        boolean adult = age >= 18;             // 判斷是否成年
        
        System.out.println("姓名：" + name);
        System.out.println("年齡：" + age);
        System.out.println("Adult: " + adult);
        
        sc.close();  // 5. 關閉 Scanner（良好習慣）
    }
}

import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        // 錯誤1：編譯錯誤 - 缺少一個 } 
        // 錯誤2：陣列越界錯誤 - i <= scores.length 改為 i < scores.length
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        // 錯誤3：整數除法造成的邏輯錯誤 - 將 total 轉為 double
        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f\n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();

        // 錯誤4：scanner 換行問題 - 加入 sc.nextLine() 清除換行
        sc.nextLine(); // 清除輸入緩衝區的換行符

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        // 錯誤5：字符串比較錯誤 - 使用 equals() 而非 ==
        if (command.equals("exit")) {
            System.out.println("系統結束，年齡：" + age);
        } else {
            System.out.println("指令：" + command + "，年齡：" + age);
        }

        sc.close();
    }
}
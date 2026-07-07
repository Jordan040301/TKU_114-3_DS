import java.util.Scanner;

public class HealthAdvisor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String continueInput;  // 記錄是否繼續
        
        System.out.println("=== BMI 計算機 ===");
        System.out.println();
        
        while (true) {
            // 1. 輸入姓名
            System.out.print("請輸入姓名：");
            String name = sc.nextLine();
            
            // 2. 輸入身高（公尺）
            System.out.print("請輸入身高（公尺，例如：1.75）：");
            double height = sc.nextDouble();
            
            // 3. 輸入體重（公斤）
            System.out.print("請輸入體重（公斤，例如：65）：");
            double weight = sc.nextDouble();
            
            // 4. 計算 BMI
            double bmi = weight / (height * height);
            
            // 5. 使用 if-else if-else 判斷 BMI 分級
            String level;
            if (bmi < 18.5) {
                level = "體重過輕";
            } else if (bmi >= 18.5 && bmi < 24) {
                level = "正常範圍";
            } else if (bmi >= 24 && bmi < 27) {
                level = "體重過重";
            } else if (bmi >= 27 && bmi < 30) {
                level = "輕度肥胖";
            } else if (bmi >= 30 && bmi < 35) {
                level = "中度肥胖";
            } else {
                level = "重度肥胖";
            }
            
            // 6. 輸出姓名、BMI 與分級結果
            System.out.println("\n=== 結果 ===");
            System.out.println("姓名：" + name);
            System.out.printf("BMI：%.2f\n", bmi);  // 小數點後兩位
            System.out.println("分級：" + level);
            System.out.println("============");
            System.out.println();
            
            // 7. 使用 while 詢問是否繼續輸入下筆資料
            while (true) {
                System.out.print("是否繼續輸入？（y/n）：");
                continueInput = sc.next();
                
                if (continueInput.equalsIgnoreCase("y") || 
                    continueInput.equalsIgnoreCase("n")) {
                    break;  // 輸入正確，離開內層迴圈
                } else {
                    System.out.println("❌ 請輸入 y 或 n！");
                }
            }
            
            // 清除緩衝區的換行符號
            sc.nextLine();
            
            if (continueInput.equalsIgnoreCase("n")) {
                System.out.println("程式結束，謝謝使用！");
                break;  // 離開外層迴圈
            }
            
            System.out.println();  // 空行分隔
        }
        
        sc.close();
    }
}
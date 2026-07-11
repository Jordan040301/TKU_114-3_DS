import java.util.Scanner;

public class PersonalProfileApp {
    
    // ============================================
    // 主程式
    // ============================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("====================================");
        System.out.println("      健康資料系統                  ");
        System.out.println("====================================");
        System.out.println();
        
        // 1. 輸入姓名
        System.out.print("請輸入姓名：");
        String name = sc.nextLine();
        
        // 2. 輸入年齡（必須大於 0）
        int age = readPositiveInt(sc, "請輸入年齡：");
        
        // 3. 輸入身高（必須大於 0）
        double height = readPositiveDouble(sc, "請輸入身高（公尺）：");
        
        // 4. 輸入體重（必須大於 0）
        double weight = readPositiveDouble(sc, "請輸入體重（公斤）：");
        
        // 5. 計算 BMI
        double bmi = calculateBMI(height, weight);
        
        // 6. 判斷 BMI 分級
        String level = getBMILevel(bmi);
        
        // 7. 判斷是否成年
        boolean adult = isAdult(age);
        
        // 8. 輸出完整報告
        printReport(name, age, adult, height, weight, bmi, level);
        
        sc.close();
    }
    
    // ============================================
    // 方法 1：讀取正整數（含輸入驗證）
    // ============================================
    /**
     * 讀取並驗證正整數（必須大於 0）
     * @param sc Scanner 物件
     * @param message 顯示的提示訊息
     * @return 輸入的正整數
     */
    public static int readPositiveInt(Scanner sc, String message) {
        int value;
        while (true) {
            System.out.print(message);
            value = sc.nextInt();
            if (value > 0) {
                break;
            }
            System.out.println("❌ 輸入必須大於 0，請重新輸入！");
        }
        return value;
    }
    
    // ============================================
    // 方法 2：讀取正浮點數（含輸入驗證）
    // ============================================
    /**
     * 讀取並驗證正浮點數（必須大於 0）
     * @param sc Scanner 物件
     * @param message 顯示的提示訊息
     * @return 輸入的正浮點數
     */
    public static double readPositiveDouble(Scanner sc, String message) {
        double value;
        while (true) {
            System.out.print(message);
            value = sc.nextDouble();
            if (value > 0) {
                break;
            }
            System.out.println("❌ 輸入必須大於 0，請重新輸入！");
        }
        return value;
    }
    
    // ============================================
    // 方法 3：計算 BMI
    // ============================================
    /**
     * 計算 BMI 值
     * @param height 身高（公尺）
     * @param weight 體重（公斤）
     * @return BMI 值
     */
    public static double calculateBMI(double height, double weight) {
        return weight / (height * height);
    }
    
    // ============================================
    // 方法 4：判斷 BMI 分級
    // ============================================
    /**
     * 根據 BMI 值判斷分級
     * @param bmi BMI 值
     * @return BMI 分級字串
     */
    public static String getBMILevel(double bmi) {
        if (bmi < 18.5) {
            return "體重過輕";
        } else if (bmi < 24) {
            return "普通";
        } else if (bmi < 27) {
            return "超重";
        } else {
            return "肥胖";
        }
    }
    
    // ============================================
    // 方法 5：判斷是否成年
    // ============================================
    /**
     * 判斷年齡是否滿 18 歲
     * @param age 年齡
     * @return true 表示成年，false 表示未成年
     */
    public static boolean isAdult(int age) {
        return age >= 18;
    }
    
    // ============================================
    // 方法 6：輸出完整報告
    // ============================================
    /**
     * 列印完整健康報告
     * @param name 姓名
     * @param age 年齡
     * @param adult 是否成年
     * @param height 身高
     * @param weight 體重
     * @param bmi BMI 值
     * @param level BMI 分級
     */
    public static void printReport(String name, int age, boolean adult, 
                                    double height, double weight, 
                                    double bmi, String level) {
        System.out.println();
        System.out.println("====================================");
        System.out.println("          健康報告                  ");
        System.out.println("====================================");
        System.out.println("姓名：" + name);
        System.out.println("年齡：" + age + " 歲");
        System.out.println("是否成年：" + (adult ? "是" : "否"));
        System.out.printf("身高：%.2f 公尺\n", height);
        System.out.printf("體重：%.1f 公斤\n", weight);
        System.out.printf("BMI：%.2f\n", bmi);
        System.out.println("BMI 分級：" + level);
        System.out.println("====================================");
    }
}
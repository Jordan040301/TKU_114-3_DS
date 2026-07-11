import java.util.Scanner;

public class GradeStatisticsApp {
    
    // ============================================
    // 主程式
    // ============================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int total = 0;           // 總分
        int count = 0;           // 總人數
        int max = Integer.MIN_VALUE;   // 最高分（先設為最小值）
        int min = Integer.MAX_VALUE;   // 最低分（先設為最大值）
        int passCount = 0;       // 及格人數
        int failCount = 0;       // 不及格人數
        
        System.out.println("====================================");
        System.out.println("      成績統計系統                  ");
        System.out.println("====================================");
        System.out.println("【說明】");
        System.out.println("  • 輸入 0 到 100 之間的成績");
        System.out.println("  • 輸入 -1 結束輸入");
        System.out.println("====================================");
        System.out.println();
        
        while (true) {
            System.out.print("請輸入成績（-1 結束）：");
            int score = sc.nextInt();
            
            // 檢查是否結束輸入
            if (score == -1) {
                break;
            }
            
            // 驗證成績是否合法（0 到 100）
            if (!isValidScore(score)) {
                System.out.println("❌ 成績必須在 0 到 100 之間，請重新輸入！");
                System.out.println();
                continue;
            }
            
            // 累加總分
            total += score;
            count++;
            
            // 更新最高分
            if (score > max) {
                max = score;
            }
            
            // 更新最低分
            if (score < min) {
                min = score;
            }
            
            // 統計及格與不及格人數
            if (isPassing(score)) {
                passCount++;
            } else {
                failCount++;
            }
            
            System.out.println("✅ 已記錄成績：" + score);
            System.out.println();
        }
        
        // 檢查是否有任何成績輸入
        if (count == 0) {
            System.out.println("No scores entered.");
        } else {
            // 計算平均
            double average = (double) total / count;
            
            // 判斷等第
            String grade = getGrade(average);
            
            // 輸出統計結果
            printSummary(count, total, average, max, min, passCount, failCount, grade);
        }
        
        sc.close();
    }
    
    // ============================================
    // 方法 1：驗證成績是否合法
    // ============================================
    /**
     * 驗證成績是否在 0 到 100 之間
     * @param score 成績
     * @return true 表示合法，false 表示不合法
     */
    public static boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }
    
    // ============================================
    // 方法 2：判斷是否及格
    // ============================================
    /**
     * 判斷成績是否及格（60 分以上）
     * @param score 成績
     * @return true 表示及格，false 表示不及格
     */
    public static boolean isPassing(int score) {
        return score >= 60;
    }
    
    // ============================================
    // 方法 3：判斷等第
    // ============================================
    /**
     * 根據平均成績判斷等第
     * @param average 平均成績
     * @return 等第字串（A、B、C、D、F）
     */
    public static String getGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
    
    // ============================================
    // 方法 4：輸出統計結果
    // ============================================
    /**
     * 列印完整的成績統計報告
     * @param count 總人數
     * @param total 總分
     * @param average 平均分數
     * @param max 最高分
     * @param min 最低分
     * @param passCount 及格人數
     * @param failCount 不及格人數
     * @param grade 等第
     */
    public static void printSummary(int count, int total, double average, 
                                      int max, int min, int passCount, 
                                      int failCount, String grade) {
        System.out.println();
        System.out.println("====================================");
        System.out.println("          成績統計報告              ");
        System.out.println("====================================");
        System.out.println("總人數：" + count + " 人");
        System.out.println("總分：" + total);
        System.out.printf("平均：%.2f\n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("及格人數：" + passCount + " 人");
        System.out.println("不及格人數：" + failCount + " 人");
        System.out.println("等第：" + grade);
        System.out.println("====================================");
    }
}
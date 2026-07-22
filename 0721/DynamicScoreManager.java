import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {
    
    // 主程式
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();
        
        System.out.println("=== 動態成績管理系統 ===");
        System.out.println("請輸入成績（0-100），輸入 -1 結束輸入：");
        
        // 持續輸入成績
        while (true) {
            System.out.print("請輸入成績：");
            
            // 處理非數字輸入
            if (!scanner.hasNextInt()) {
                System.out.println("❌ 錯誤：請輸入有效的整數！");
                scanner.next(); // 清除無效輸入
                continue;
            }
            
            int score = scanner.nextInt();
            
            // 檢查是否結束輸入
            if (score == -1) {
                break;
            }
            
            // 檢查成績是否在 0-100 之間
            if (score < 0 || score > 100) {
                System.out.println("❌ 錯誤：成績必須在 0 到 100 之間！");
                continue;
            }
            
            // 新增成績
            scores.add(score);
            System.out.println("✅ 已新增成績：" + score);
        }
        
        scanner.close();
        
        // 檢查是否有成績
        if (scores.isEmpty()) {
            System.out.println("\n⚠️ 沒有輸入任何成績！");
            return;
        }
        
        // 輸出統計結果
        System.out.println("\n=== 成績統計結果 ===");
        System.out.println("總筆數：" + getCount(scores));
        System.out.println("平均分數：" + getAverage(scores));
        System.out.println("最高分數：" + getMax(scores));
        System.out.println("最低分數：" + getMin(scores));
        System.out.println("及格名單（>=60）：" + getPassList(scores));
        System.out.println("不及格名單（<60）：" + getFailList(scores));
    }
    
    /**
     * 取得成績總筆數
     */
    public static int getCount(ArrayList<Integer> scores) {
        return scores.size();
    }
    
    /**
     * 計算平均成績
     */
    public static double getAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.size();
    }
    
    /**
     * 取得最高成績
     */
    public static int getMax(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0;
        }
        int max = scores.get(0);
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }
    
    /**
     * 取得最低成績
     */
    public static int getMin(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0;
        }
        int min = scores.get(0);
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }
    
    /**
     * 取得及格名單（成績 >= 60）
     */
    public static ArrayList<Integer> getPassList(ArrayList<Integer> scores) {
        ArrayList<Integer> passList = new ArrayList<>();
        for (int score : scores) {
            if (score >= 60) {
                passList.add(score);
            }
        }
        return passList;
    }
    
    /**
     * 取得不及格名單（成績 < 60）
     */
    public static ArrayList<Integer> getFailList(ArrayList<Integer> scores) {
        ArrayList<Integer> failList = new ArrayList<>();
        for (int score : scores) {
            if (score < 60) {
                failList.add(score);
            }
        }
        return failList;
    }
}
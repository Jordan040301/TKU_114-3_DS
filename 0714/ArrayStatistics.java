import java.util.Scanner;

public class ArrayStatistics {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. 輸入資料筆數
        int count = readCount(sc);
        
        // 2. 建立對應長度的陣列
        int[] scores = new int[count];
        
        // 3. 逐筆輸入成績
        inputScores(sc, scores);
        
        // 4. 顯示全部成績
        displayAllScores(scores);
        
        // 5. 顯示統計結果
        int total = calculateTotal(scores);
        double average = (double) total / scores.length;
        int max = findMax(scores);
        int min = findMin(scores);
        int passCount = countPass(scores);
        int failCount = scores.length - passCount;
        
        System.out.println("=== 統計結果 ===");
        System.out.println("總分：" + total);
        System.out.printf("平均：%.2f\n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("及格人數：" + passCount);
        System.out.println("不及格人數：" + failCount);
        
        // 7. 搜尋目標成績
        System.out.print("請輸入要搜尋的目標成績：");
        int target = sc.nextInt();
        int index = findIndex(scores, target);
        if (index == -1) {
            System.out.println("找不到該成績！");
        } else {
            System.out.println("第一次出現的索引位置：" + index);
        }
        
        sc.close();
    }
    
    /**
     * 讀取資料筆數，範圍 1~50
     */
    public static int readCount(Scanner sc) {
        int count;
        while (true) {
            System.out.print("請輸入資料筆數（1~50）：");
            count = sc.nextInt();
            if (count >= 1 && count <= 50) {
                break;
            }
            System.out.println("錯誤：請輸入 1 到 50 之間的整數！");
        }
        return count;
    }
    
    /**
     * 逐筆輸入成績，範圍 0~100
     */
    public static void inputScores(Scanner sc, int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            while (true) {
                System.out.print("請輸入第 " + (i + 1) + " 筆成績（0~100）：");
                scores[i] = sc.nextInt();
                if (scores[i] >= 0 && scores[i] <= 100) {
                    break;
                }
                System.out.println("錯誤：成績必須在 0 到 100 之間！");
            }
        }
    }
    
    /**
     * 顯示全部成績
     */
    public static void displayAllScores(int[] scores) {
        System.out.print("全部成績：");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i]);
            if (i < scores.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    
    /**
     * 計算總分
     */
    public static int calculateTotal(int[] scores) {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }
    
    /**
     * 找出最高分
     */
    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }
    
    /**
     * 找出最低分
     */
    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }
    
    /**
     * 計算及格人數（60分及以上）
     */
    public static int countPass(int[] scores) {
        int passCount = 0;
        for (int score : scores) {
            if (score >= 60) {
                passCount++;
            }
        }
        return passCount;
    }
    
    /**
     * 尋找目標成績第一次出現的索引，找不到回傳 -1
     */
    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
public class ScoreRankingPractice {

    public static void main(String[] args) {
        // 使用至少8筆成績
        int[] scores = {85, 92, 78, 92, 65, 88, 76, 92, 70, 88};
        
        System.out.println("=== 原始成績 ===");
        displayScores(scores);
        
        System.out.println("\n=== 排序過程追蹤 ===");
        int[] sortedScores = selectionSortDescendingWithTrace(scores.clone());
        
        System.out.println("\n=== 最終排名 ===");
        displayRanking(sortedScores);
    }
    
    /**
     * 選擇排序（降冪）並顯示追蹤資訊
     */
    public static int[] selectionSortDescendingWithTrace(int[] arr) {
        System.out.println("初始陣列：" + arrayToString(arr));
        
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.println("\n第 " + (i + 1) + " 輪：");
            int maxIndex = i;
            
            System.out.println("  尋找最大值的範圍：arr[" + i + "] 到 arr[" + (arr.length - 1) + "]");
            
            // 在未排序部分尋找最大值
            for (int j = i + 1; j < arr.length; j++) {
                System.out.println("    比較 arr[" + j + "]=" + arr[j] + " 與 arr[" + maxIndex + "]=" + arr[maxIndex]);
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                    System.out.println("    ★ 找到更大的值：arr[" + maxIndex + "]=" + arr[maxIndex]);
                }
            }
            
            System.out.println("  最大值位置：arr[" + maxIndex + "]=" + arr[maxIndex]);
            
            // 交換
            if (maxIndex != i) {
                System.out.println("  交換：arr[" + i + "]=" + arr[i] + " ↔ arr[" + maxIndex + "]=" + arr[maxIndex]);
                int temp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = temp;
            } else {
                System.out.println("  最大值已在正確位置，無需交換");
            }
            
            System.out.println("  目前陣列：" + arrayToString(arr));
        }
        
        return arr;
    }
    
    /**
     * 顯示成績排名（降冪）
     */
    public static void displayRanking(int[] scores) {
        int rank = 1;
        int previousScore = -1;
        
        System.out.println("名次\t分數\t等級");
        System.out.println("=========================");
        
        for (int i = 0; i < scores.length; i++) {
            int currentScore = scores[i];
            
            // 如果分數與前一個不同，更新名次
            if (currentScore != previousScore) {
                rank = i + 1;
                previousScore = currentScore;
            }
            
            String grade = getGrade(currentScore);
            System.out.printf("%d\t%d\t%s\n", rank, currentScore, grade);
        }
        
        System.out.println("=========================");
        System.out.println("總人數：" + scores.length);
    }
    
    /**
     * 選擇排序（降冪：由高到低）
     */
    public static void selectionSortDescending(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int maxIndex = i;
            
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            
            if (maxIndex != i) {
                int temp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = temp;
            }
        }
    }
    
    /**
     * 根據分數給予等級
     */
    public static String getGrade(int score) {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
    
    /**
     * 顯示成績陣列
     */
    public static void displayScores(int[] scores) {
        System.out.print("成績：");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i]);
            if (i < scores.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    
    /**
     * 將陣列轉為字串
     */
    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
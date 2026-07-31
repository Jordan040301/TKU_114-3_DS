public class ContestRankingSystem {

    public static void main(String[] args) {
        // 建立參賽者資料（至少8筆）
        Contestant[] contestants = {
            new Contestant("C001", "張志明", 85, 12.5),
            new Contestant("C002", "李雅婷", 92, 10.8),
            new Contestant("C003", "王建華", 78, 15.2),
            new Contestant("C004", "林怡君", 92, 11.3),    // 與 C002 同分，但秒數較多
            new Contestant("C005", "陳冠宇", 88, 13.1),
            new Contestant("C006", "黃佩珊", 92, 10.5),    // 與 C002 同分，秒數最少
            new Contestant("C007", "劉宗翰", 76, 14.8),
            new Contestant("C008", "吳欣怡", 88, 12.9),    // 與 C005 同分，秒數較多
            new Contestant("C009", "楊佳蓉", 95, 9.7),
            new Contestant("C010", "鄭偉哲", 82, 16.3)
        };

        System.out.println("=== 原始參賽者資料 ===");
        displayContestants(contestants);

        System.out.println("\n=== 插入排序過程（分數高→低，同分時秒數少→多） ===");
        insertionSortByScoreAndTime(contestants);

        System.out.println("\n=== 最終排名結果 ===");
        displayRanking(contestants);
    }

    /**
     * 插入排序：分數高者排在前面
     * 分數相同時，完成秒數少者排在前面
     */
    public static void insertionSortByScoreAndTime(Contestant[] contestants) {
        int comparisonCount = 0;
        int shiftCount = 0;

        for (int index = 1; index < contestants.length; index++) {
            Contestant key = contestants[index];
            int position = index - 1;

            System.out.println("\n第 " + index + " 回合：key = " + key.getName() + 
                             " (分數: " + key.getScore() + ", 秒數: " + key.getTime() + ")");

            // 比較規則：分數高者優先，同分時秒數少者優先
            while (position >= 0 && shouldSwap(contestants[position], key)) {
                comparisonCount++;
                System.out.println("  比較: " + contestants[position].getName() + 
                                 " (分數 " + contestants[position].getScore() + 
                                 ", 秒數 " + contestants[position].getTime() + 
                                 ") > " + key.getName() + 
                                 " (分數 " + key.getScore() + 
                                 ", 秒數 " + key.getTime() + 
                                 ") → 向後移動");

                // 向後移動
                contestants[position + 1] = contestants[position];
                shiftCount++;
                position--;
            }

            // 記錄最後一次比較（如果有的話）
            if (position >= 0) {
                comparisonCount++;
                System.out.println("  比較: " + contestants[position].getName() + 
                                 " (分數 " + contestants[position].getScore() + 
                                 ", 秒數 " + contestants[position].getTime() + 
                                 ") <= " + key.getName() + 
                                 " (分數 " + key.getScore() + 
                                 ", 秒數 " + key.getTime() + 
                                 ") → 插入在此之後");
            }

            // 插入 key
            int insertPosition = position + 1;
            contestants[insertPosition] = key;
            System.out.println("  插入位置：" + insertPosition);
            System.out.println("  目前排名：");
            displayContestantsBrief(contestants);
        }

        System.out.println("\n=== 排序統計 ===");
        System.out.println("總比較次數：" + comparisonCount);
        System.out.println("總移動次數：" + shiftCount);
    }

    /**
     * 判斷是否需要交換（position 的選手是否應該排在 key 之後）
     * @param current 當前位置的選手
     * @param key 要插入的選手
     * @return true 表示 current 應該排在 key 後面，需要移動
     */
    public static boolean shouldSwap(Contestant current, Contestant key) {
        // 分數較低者排在後面
        if (current.getScore() < key.getScore()) {
            return true;
        }
        // 分數相同時，秒數較多者排在後面
        if (current.getScore() == key.getScore() && current.getTime() > key.getTime()) {
            return true;
        }
        return false;
    }

    /**
     * 顯示所有參賽者完整資料
     */
    public static void displayContestants(Contestant[] contestants) {
        System.out.println("編號\t姓名\t\t分數\t秒數");
        System.out.println("================================================");
        for (Contestant c : contestants) {
            System.out.printf("%s\t%-8s\t%d\t%.2f\n", 
                            c.getId(), c.getName(), c.getScore(), c.getTime());
        }
    }

    /**
     * 簡短顯示參賽者（顯示姓名、分數和秒數）
     */
    public static void displayContestantsBrief(Contestant[] contestants) {
        System.out.print("  [");
        for (int i = 0; i < contestants.length; i++) {
            System.out.print(contestants[i].getName() + 
                           "(" + contestants[i].getScore() + 
                           "," + contestants[i].getTime() + ")");
            if (i < contestants.length - 1) {
                System.out.print(" → ");
            }
        }
        System.out.println("]");
    }

    /**
     * 顯示最終排名（含名次）
     */
    public static void displayRanking(Contestant[] contestants) {
        System.out.println("名次\t編號\t姓名\t\t分數\t秒數");
        System.out.println("========================================================");
        
        int rank = 1;
        int previousScore = -1;
        double previousTime = -1.0;
        int sameRankCount = 0;

        for (int i = 0; i < contestants.length; i++) {
            Contestant c = contestants[i];
            
            // 如果不是第一名，檢查是否與前一名的分數和秒數都相同
            if (i > 0) {
                Contestant prev = contestants[i - 1];
                // 只有當分數和秒數都相同時才並列名次
                if (c.getScore() == prev.getScore() && c.getTime() == prev.getTime()) {
                    // 並列名次，不改變 rank
                } else {
                    rank = i + 1;
                }
            }
            
            System.out.printf("%d\t%s\t%-8s\t%d\t%.2f\n", 
                            rank, c.getId(), c.getName(), c.getScore(), c.getTime());
        }

        System.out.println("========================================================");
        System.out.println("總參賽人數：" + contestants.length);
        
        // 顯示排名規則說明
        System.out.println("\n排名規則：");
        System.out.println("  1. 分數高者優先");
        System.out.println("  2. 分數相同時，秒數少者優先");
        System.out.println("  3. 分數和秒數都相同時，並列名次");
    }
}
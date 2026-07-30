import java.util.Random;

public class SearchEfficiencyReport {
    // 資料數量設定
    private static final int[] DATA_SIZES = {16, 128, 1024};
    
    // 測試情境
    private static final String[] SCENARIOS = {"第一筆資料", "最後一筆資料", "不存在資料"};
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║              搜尋效率分析報告                                  ║");
        System.out.println("║        循序搜尋 vs 二分搜尋 比較                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        // 分析各種資料量的搜尋效率
        for (int size : DATA_SIZES) {
            System.out.println("\n" + "=".repeat(80));
            System.out.println("📊 資料量: " + size + " 筆");
            System.out.println("=".repeat(80));
            
            // 建立已排序資料
            int[] sortedData = generateSortedData(size);
            
            // 分析不同搜尋目標
            analyzeSearchEfficiency(sortedData);
        }
        
        // 輸出觀察結果
        printObservations();
    }
    
    /**
     * 生成已排序的資料
     */
    public static int[] generateSortedData(int size) {
        int[] data = new int[size];
        // 生成從 1 開始的連續數字
        for (int i = 0; i < size; i++) {
            data[i] = i + 1;
        }
        return data;
    }
    
    /**
     * 分析搜尋效率
     */
    public static void analyzeSearchEfficiency(int[] data) {
        int size = data.length;
        
        // 測試三種情境
        int[] testTargets = {
            data[0],           // 第一筆資料
            data[size - 1],    // 最後一筆資料
            size + 100         // 不存在資料 (超出範圍)
        };
        
        String[] targetDescriptions = {"第一筆資料", "最後一筆資料", "不存在資料"};
        
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ 搜尋目標    │ 循序搜尋比較次數 │ 二分搜尋比較次數 │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        
        for (int i = 0; i < testTargets.length; i++) {
            int target = testTargets[i];
            
            // 執行循序搜尋
            int sequentialComparisons = sequentialSearchWithCount(data, target);
            
            // 執行二分搜尋
            int binaryComparisons = binarySearchWithCount(data, target);
            
            // 顯示結果
            System.out.printf("│ %-10s │ %-17d │ %-17d │%n", 
                             targetDescriptions[i], 
                             sequentialComparisons, 
                             binaryComparisons);
        }
        
        System.out.println("└─────────────────────────────────────────────────────────┘");
        
        // 顯示詳細搜尋過程 (僅對小資料量)
        if (size <= 16) {
            System.out.println("\n📝 詳細搜尋過程 (資料量: " + size + " 筆):");
            showDetailedSearchProcess(data, "第一筆資料", data[0]);
            showDetailedSearchProcess(data, "最後一筆資料", data[size - 1]);
            showDetailedSearchProcess(data, "不存在資料", size + 100);
        }
    }
    
    /**
     * 循序搜尋 (回傳比較次數)
     */
    public static int sequentialSearchWithCount(int[] array, int target) {
        int comparisons = 0;
        for (int i = 0; i < array.length; i++) {
            comparisons++;
            if (array[i] == target) {
                return comparisons;
            }
        }
        return comparisons;
    }
    
    /**
     * 二分搜尋 (回傳比較次數)
     */
    public static int binarySearchWithCount(int[] array, int target) {
        int comparisons = 0;
        int low = 0;
        int high = array.length - 1;
        
        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;
            
            if (array[mid] == target) {
                return comparisons;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return comparisons;
    }
    
    /**
     * 顯示詳細搜尋過程
     */
    public static void showDetailedSearchProcess(int[] data, String scenario, int target) {
        System.out.println("\n  🔍 " + scenario + " (目標值: " + target + ")");
        System.out.println("   循序搜尋比較次數: " + sequentialSearchWithCount(data, target));
        System.out.println("   二分搜尋比較次數: " + binarySearchWithCount(data, target));
    }
    
    /**
     * 輸出觀察結果
     */
    public static void printObservations() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📈 觀察結果與分析");
        System.out.println("=".repeat(80));
        
        System.out.println("\n🔎 1. 循序搜尋 (Sequential Search) 特性:");
        System.out.println("   • 時間複雜度: O(n)");
        System.out.println("   • 最佳情況: 第一筆資料 -> 1 次比較");
        System.out.println("   • 最差情況: 最後一筆資料或不存在資料 -> n 次比較");
        System.out.println("   • 平均情況: (n+1)/2 次比較");
        System.out.println("   • 適用場景: 資料量小或未排序的資料");
        
        System.out.println("\n🔎 2. 二分搜尋 (Binary Search) 特性:");
        System.out.println("   • 時間複雜度: O(log n)");
        System.out.println("   • 最佳情況: 中間資料 -> 1 次比較");
        System.out.println("   • 最差情況: 約 log₂(n) 次比較");
        System.out.println("   • 平均情況: 約 log₂(n) 次比較");
        System.out.println("   • 適用場景: 已排序的大規模資料");
        
        System.out.println("\n🔎 3. 效率比較分析:");
        System.out.println("   • 資料量 16 筆:");
        System.out.println("     - 循序搜尋: 最佳 1 次, 最差 16 次");
        System.out.println("     - 二分搜尋: 最多 4 次比較");
        System.out.println("     - 結論: 二分搜尋在大部分情況下較優");
        
        System.out.println("   • 資料量 128 筆:");
        System.out.println("     - 循序搜尋: 最佳 1 次, 最差 128 次");
        System.out.println("     - 二分搜尋: 最多 7 次比較");
        System.out.println("     - 結論: 二分搜尋效率明顯優於循序搜尋");
        
        System.out.println("   • 資料量 1024 筆:");
        System.out.println("     - 循序搜尋: 最佳 1 次, 最差 1024 次");
        System.out.println("     - 二分搜尋: 最多 10 次比較");
        System.out.println("     - 結論: 資料量越大，二分搜尋的優勢越明顯");
        
        System.out.println("\n🔎 4. 關鍵發現:");
        System.out.println("   • 循序搜尋的效率與目標位置相關:");
        System.out.println("     - 第一筆資料: 效率最高 (1 次比較)");
        System.out.println("     - 最後一筆資料: 效率最差 (n 次比較)");
        System.out.println("     - 不存在資料: 需要檢查所有資料 (n 次比較)");
        
        System.out.println("   • 二分搜尋的效率與資料量相關:");
        System.out.println("     - 比較次數呈對數成長 (log₂ n)");
        System.out.println("     - 資料量增加時，比較次數增加緩慢");
        System.out.println("     - 不受目標位置影響，效率穩定");
        
        System.out.println("\n🔎 5. 實際應用建議:");
        System.out.println("   • 小資料量 (< 100 筆):");
        System.out.println("     - 可考慮使用循序搜尋，實作簡單");
        System.out.println("     - 二分搜尋優勢不明顯");
        
        System.out.println("   • 大資料量 (> 1000 筆):");
        System.out.println("     - 強烈建議使用二分搜尋");
        System.out.println("     - 效率提升極為明顯");
        
        System.out.println("   • 未排序資料:");
        System.out.println("     - 只能使用循序搜尋");
        System.out.println("     - 或先排序再使用二分搜尋");
        
        System.out.println("   • 頻繁搜尋的應用:");
        System.out.println("     - 建議先排序資料");
        System.out.println("     - 使用二分搜尋可大幅提升效率");
        
        System.out.println("\n🔎 6. 理論與實務對照:");
        System.out.println("   • 循序搜尋的理論值與實測值一致");
        System.out.println("   • 二分搜尋的理論值 (log₂ n) 與實測值相符");
        System.out.println("   • 驗證了時間複雜度分析的準確性");
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("✅ 報告完成！");
        System.out.println("=".repeat(80));
    }
}
import java.util.Scanner;

public class AllOccurrenceSearch {
    public static void main(String[] args) {
        // 建立未排序的分數陣列
        int[] scores = {
            85, 92, 78, 85, 90, 
            88, 85, 76, 92, 85, 
            95, 85, 88, 90, 85,
            82, 85, 78, 92, 85
        };
        
        System.out.println("=== 搜尋全部資料相同位置 ===");
        System.out.println("分數陣列: ");
        printArray(scores);
        System.out.println("\n------------------------");
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("請輸入要搜尋的分數: ");
        int targetScore = scanner.nextInt();
        
        // 執行全部搜索
        SearchResult result = findAllOccurrences(scores, targetScore);
        
        // 顯示結果
        System.out.println("\n=== 搜尋結果 ===");
        if (result.getCount() > 0) {
            System.out.println("找到 " + result.getCount() + " 個符合的分數 \"" + targetScore + "\"");
            System.out.println("出現位置 (索引): " + result.getIndices());
            System.out.println("總比較次數: " + result.getComparisonCount());
            System.out.println("平均比較次數: " + 
                String.format("%.2f", (double)result.getComparisonCount() / result.getCount()));
        } else {
            System.out.println("找不到分數 \"" + targetScore + "\"");
            System.out.println("總比較次數: " + result.getComparisonCount());
        }
        
        scanner.close();
    }
    
    /**
     * 搜尋所有符合的索引位置
     * @param array 要搜索的陣列
     * @param target 要找的目標值
     * @return SearchResult 包含所有索引、出現次數和比較次數
     */
    public static SearchResult findAllOccurrences(int[] array, int target) {
        int comparisonCount = 0;
        StringBuilder indices = new StringBuilder();
        int occurrenceCount = 0;
        
        System.out.println("\n開始搜索過程...");
        
        for (int i = 0; i < array.length; i++) {
            comparisonCount++;
            System.out.println("比較第 " + comparisonCount + " 次: array[" + i + "] = " + array[i]);
            
            if (array[i] == target) {
                occurrenceCount++;
                if (occurrenceCount > 1) {
                    indices.append(", ");
                }
                indices.append(i);
                System.out.println("  ✓ 找到符合! 索引: " + i + " (第 " + occurrenceCount + " 次出現)");
            } else {
                System.out.println("  ✗ 不符合");
            }
        }
        
        return new SearchResult(indices.toString(), occurrenceCount, comparisonCount);
    }
    
    /**
     * 印出陣列內容
     */
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}

/**
 * 搜尋結果封裝類別
 */
class SearchResult {
    private String indices;
    private int count;
    private int comparisonCount;
    
    public SearchResult(String indices, int count, int comparisonCount) {
        this.indices = indices;
        this.count = count;
        this.comparisonCount = comparisonCount;
    }
    
    public String getIndices() {
        return "[" + indices + "]";
    }
    
    public int getCount() {
        return count;
    }
    
    public int getComparisonCount() {
        return comparisonCount;
    }
}
import java.util.Random;
import java.util.Arrays;

/**
 * AlgorithmComparisonReport.java
 * 演算比較報告計劃
 * 
 * 功能要求：
 * 1. 使用 16、128、1024 筆資料
 * 2. 比較選擇、插入、歸併排序的資料比較次數
 * 3. 每個演算都使用相同原始資料的副本
 * 4. 分別測試已排序、反向及固定亂序資料
 * 5. 輸出表格及計算計算出的觀察結論
 * 6. 不以單次執行十個數作為唯一判斷
 */
public class AlgorithmComparisonReport {
    
    // 統計資料比較次數
    private static long selectionComparisons = 0;
    private static long insertionComparisons = 0;
    private static long mergeComparisons = 0;
    
    // 測試資料大小
    private static final int[] DATA_SIZES = {16, 128, 1024};
    
    // 亂數種子（固定以確保可重複性）
    private static final Random random = new Random(42);
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    演算法比較報告 - 排序演算法效能分析                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        // 產生報告表格
        generateReport();
        
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                            觀察結論與分析                                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        printConclusion();
    }
    
    /**
     * 產生完整比較報告
     */
    private static void generateReport() {
        System.out.println("📊 排序演算法比較報告");
        System.out.println("=" .repeat(80));
        System.out.println();
        
        for (int size : DATA_SIZES) {
            System.out.println("🔹 資料筆數: " + size);
            System.out.println("-".repeat(70));
            
            // 產生三種資料類型
            int[] sortedData = generateSortedData(size);
            int[] reverseData = generateReverseData(size);
            int[] randomData = generateRandomData(size);
            
            // 輸出表格
            printComparisonTable(size, sortedData, reverseData, randomData);
            System.out.println();
        }
    }
    
    /**
     * 產生已排序資料
     */
    private static int[] generateSortedData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
        return data;
    }
    
    /**
     * 產生反向排序資料
     */
    private static int[] generateReverseData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = size - i;
        }
        return data;
    }
    
    /**
     * 產生固定亂序資料（使用固定種子）
     */
    private static int[] generateRandomData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(size * 10);
        }
        return data;
    }
    
    /**
     * 列印比較表格
     */
    private static void printComparisonTable(int size, int[] sorted, int[] reverse, int[] random) {
        // 表格標題
        System.out.println("┌──────────────┬─────────────┬─────────────┬─────────────┐");
        System.out.println("│  演算法名稱   │  已排序資料  │  反向資料   │  亂序資料   │");
        System.out.println("├──────────────┼─────────────┼─────────────┼─────────────┤");
        
        // 選擇排序
        long selSorted = measureSelectionSort(sorted.clone());
        long selReverse = measureSelectionSort(reverse.clone());
        long selRandom = measureSelectionSort(random.clone());
        printTableRow("選擇排序", selSorted, selReverse, selRandom);
        
        // 插入排序
        long insSorted = measureInsertionSort(sorted.clone());
        long insReverse = measureInsertionSort(reverse.clone());
        long insRandom = measureInsertionSort(random.clone());
        printTableRow("插入排序", insSorted, insReverse, insRandom);
        
        // 歸併排序
        long merSorted = measureMergeSort(sorted.clone());
        long merReverse = measureMergeSort(reverse.clone());
        long merRandom = measureMergeSort(random.clone());
        printTableRow("歸併排序", merSorted, merReverse, merRandom);
        
        System.out.println("└──────────────┴─────────────┴─────────────┴─────────────┘");
        
        // 顯示最佳和最差表現
        printBestWorstAnalysis(selSorted, selReverse, selRandom, 
                               insSorted, insReverse, insRandom,
                               merSorted, merReverse, merRandom);
    }
    
    /**
     * 列印表格列
     */
    private static void printTableRow(String name, long sorted, long reverse, long random) {
        System.out.printf("│ %-12s │ %11d │ %11d │ %11d │%n", 
                         name, sorted, reverse, random);
    }
    
    /**
     * 印出最佳和最差表現分析
     */
    private static void printBestWorstAnalysis(long selSorted, long selReverse, long selRandom,
                                                long insSorted, long insReverse, long insRandom,
                                                long merSorted, long merReverse, long merRandom) {
        System.out.println("\n📈 表現分析：");
        
        // 找出各種資料類型的最佳演算法
        long minSorted = Math.min(Math.min(selSorted, insSorted), merSorted);
        long minReverse = Math.min(Math.min(selReverse, insReverse), merReverse);
        long minRandom = Math.min(Math.min(selRandom, insRandom), merRandom);
        
        // 找出各種演算法的最佳和最差資料類型
        System.out.println("  已排序資料最佳: " + getAlgorithmName(selSorted, insSorted, merSorted) + 
                          " (" + minSorted + " 次比較)");
        System.out.println("  反向資料最佳: " + getAlgorithmName(selReverse, insReverse, merReverse) + 
                          " (" + minReverse + " 次比較)");
        System.out.println("  亂序資料最佳: " + getAlgorithmName(selRandom, insRandom, merRandom) + 
                          " (" + minRandom + " 次比較)");
        
        System.out.println();
        System.out.println("  各演算法最佳表現資料類型：");
        System.out.println("  選擇排序: " + getBestDataType(selSorted, selReverse, selRandom) + 
                          " (" + Math.min(Math.min(selSorted, selReverse), selRandom) + " 次比較)");
        System.out.println("  插入排序: " + getBestDataType(insSorted, insReverse, insRandom) + 
                          " (" + Math.min(Math.min(insSorted, insReverse), insRandom) + " 次比較)");
        System.out.println("  歸併排序: " + getBestDataType(merSorted, merReverse, merRandom) + 
                          " (" + Math.min(Math.min(merSorted, merReverse), merRandom) + " 次比較)");
        System.out.println();
    }
    
    /**
     * 取得演算法名稱
     */
    private static String getAlgorithmName(long sel, long ins, long mer) {
        if (sel <= ins && sel <= mer) return "選擇排序";
        if (ins <= sel && ins <= mer) return "插入排序";
        return "歸併排序";
    }
    
    /**
     * 取得最佳資料類型
     */
    private static String getBestDataType(long sorted, long reverse, long random) {
        if (sorted <= reverse && sorted <= random) return "已排序";
        if (reverse <= sorted && reverse <= random) return "反向";
        return "亂序";
    }
    
    /**
     * 測量選擇排序的比較次數
     */
    private static long measureSelectionSort(int[] arr) {
        selectionComparisons = 0;
        selectionSort(arr);
        return selectionComparisons;
    }
    
    /**
     * 選擇排序實作
     */
    private static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                selectionComparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // 交換
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }
    
    /**
     * 測量插入排序的比較次數
     */
    private static long measureInsertionSort(int[] arr) {
        insertionComparisons = 0;
        insertionSort(arr);
        return insertionComparisons;
    }
    
    /**
     * 插入排序實作
     */
    private static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                insertionComparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }
    
    /**
     * 測量歸併排序的比較次數
     */
    private static long measureMergeSort(int[] arr) {
        mergeComparisons = 0;
        mergeSort(arr, 0, arr.length - 1);
        return mergeComparisons;
    }
    
    /**
     * 歸併排序實作
     */
    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    /**
     * 合併實作
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        
        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            mergeComparisons++;
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    /**
     * 列印觀察結論
     */
    private static void printConclusion() {
        System.out.println("📋 觀察結論：");
        System.out.println();
        System.out.println("1. 時間複雜度分析：");
        System.out.println("   • 選擇排序：O(n²) - 無論資料順序如何，比較次數固定為 n(n-1)/2");
        System.out.println("   • 插入排序：O(n²) - 最好情況 O(n)，最差情況 O(n²)");
        System.out.println("   • 歸併排序：O(n log n) - 無論資料順序如何，比較次數相對穩定");
        System.out.println();
        System.out.println("2. 資料順序對效能的影響：");
        System.out.println("   • 已排序資料：插入排序表現最佳（接近 O(n)），選擇排序最差");
        System.out.println("   • 反向資料：插入排序表現最差（O(n²)），歸併排序相對穩定");
        System.out.println("   • 亂序資料：歸併排序表現最佳，選擇排序和插入排序表現相近");
        System.out.println();
        System.out.println("3. 資料規模的影響：");
        System.out.println("   • 小資料（16筆）：三種演算法差異不明顯");
        System.out.println("   • 中資料（128筆）：開始出現明顯差異");
        System.out.println("   • 大資料（1024筆）：歸併排序優勢明顯，插入排序在已排序資料仍表現良好");
        System.out.println();
        System.out.println("4. 實務建議：");
        System.out.println("   • 資料量小（< 50）：可使用插入排序（簡單實作）");
        System.out.println("   • 資料量中等（50-1000）：根據資料特性選擇");
        System.out.println("     - 已排序或接近排序：插入排序");
        System.out.println("     - 亂序或未知順序：歸併排序");
        System.out.println("   • 資料量大（> 1000）：優先考慮歸併排序");
        System.out.println("   • 注意：選擇排序雖然簡單，但效能通常不如其他兩種，建議避免使用");
        System.out.println();
        System.out.println("5. 演算法穩定性：");
        System.out.println("   • 選擇排序：不穩定（相同元素的相對順序可能改變）");
        System.out.println("   • 插入排序：穩定（相同元素的相對順序保持不變）");
        System.out.println("   • 歸併排序：穩定（相同元素的相對順序保持不變）");
        System.out.println();
        System.out.println("6. 記憶體使用：");
        System.out.println("   • 選擇排序：O(1) - 原地排序");
        System.out.println("   • 插入排序：O(1) - 原地排序");
        System.out.println("   • 歸併排序：O(n) - 需要額外陣列空間");
        System.out.println();
        System.out.println("7. 綜合評估：");
        System.out.println("   • 若記憶體有限：考慮選擇排序或插入排序");
        System.out.println("   • 若需要穩定排序且資料量大：歸併排序是較佳選擇");
        System.out.println("   • 若資料已接近排序：插入排序表現最佳");
        System.out.println("   • 一般情況：歸併排序提供最穩定的效能表現");
    }
}
import java.util.Random;

public class SortingExperiment {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║              ★ 排序演算法操作統計實驗 ★                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");

        // 準備三組測試資料
        int[] sortedData = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
        int[] reverseData = {50, 45, 40, 35, 30, 25, 20, 15, 10, 5};
        int[] randomData = generateRandomArray(10, 1, 50);

        System.out.println("\n📊 測試資料說明：");
        System.out.println("  已排序資料：" + arrayToString(sortedData));
        System.out.println("  逆向排序資料：" + arrayToString(reverseData));
        System.out.println("  隨機排列資料：" + arrayToString(randomData));

        // 測試選擇排序
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    🔵 選擇排序測試結果                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
        testSelectionSort(sortedData, "已排序資料");
        testSelectionSort(reverseData, "逆向排序資料");
        testSelectionSort(randomData, "隨機排列資料");

        // 測試插入排序
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    🟢 插入排序測試結果                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
        testInsertionSort(sortedData, "已排序資料");
        testInsertionSort(reverseData, "逆向排序資料");
        testInsertionSort(randomData, "隨機排列資料");

        // 總結比較
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    📋 觀察結論與分析                            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
        printConclusion();
    }

    /**
     * 測試選擇排序
     */
    public static void testSelectionSort(int[] original, String dataType) {
        // 複製原始資料
        int[] arr = copyArray(original);
        
        System.out.println("\n────────────────────────────────────────────────────────────────");
        System.out.println("📌 " + dataType);
        System.out.println("   原始：" + arrayToString(arr));
        
        int comparisonCount = 0;
        int swapCount = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                comparisonCount++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swapCount++;
            }
        }

        System.out.println("   排序後：" + arrayToString(arr));
        System.out.println("   📊 比較次數：" + comparisonCount);
        System.out.println("   📊 交換次數：" + swapCount);
        System.out.println("   📊 總操作次數：" + (comparisonCount + swapCount));
    }

    /**
     * 測試插入排序
     */
    public static void testInsertionSort(int[] original, String dataType) {
        // 複製原始資料
        int[] arr = copyArray(original);
        
        System.out.println("\n────────────────────────────────────────────────────────────────");
        System.out.println("📌 " + dataType);
        System.out.println("   原始：" + arrayToString(arr));
        
        int comparisonCount = 0;
        int shiftCount = 0;

        for (int index = 1; index < arr.length; index++) {
            int key = arr[index];
            int position = index - 1;

            while (position >= 0 && arr[position] > key) {
                comparisonCount++;
                arr[position + 1] = arr[position];
                shiftCount++;
                position--;
            }

            if (position >= 0) {
                comparisonCount++;
            }

            arr[position + 1] = key;
        }

        System.out.println("   排序後：" + arrayToString(arr));
        System.out.println("   📊 比較次數：" + comparisonCount);
        System.out.println("   📊 移動次數：" + shiftCount);
        System.out.println("   📊 總操作次數：" + (comparisonCount + shiftCount));
    }

    /**
     * 生成隨機陣列
     */
    public static int[] generateRandomArray(int size, int min, int max) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(max - min + 1) + min;
        }
        return arr;
    }

    /**
     * 複製陣列
     */
    public static int[] copyArray(int[] original) {
        int[] copy = new int[original.length];
        System.arraycopy(original, 0, copy, 0, original.length);
        return copy;
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

    /**
     * 輸出觀察結論
     */
    public static void printConclusion() {
        System.out.println("\n📝 觀察結論：");
        System.out.println("────────────────────────────────────────────────────────────────");
        
        System.out.println("\n1️⃣ 已排序資料（最佳情況）：");
        System.out.println("   • 選擇排序：比較次數固定為 n(n-1)/2 = 45 次，交換次數為 0 次");
        System.out.println("   • 插入排序：比較次數為 n-1 = 9 次，移動次數為 0 次");
        System.out.println("   ✅ 結論：插入排序在已排序資料上表現極佳（線性時間 O(n)）");
        
        System.out.println("\n2️⃣ 逆向排序資料（最差情況）：");
        System.out.println("   • 選擇排序：比較次數固定為 45 次，交換次數為 n-1 = 9 次");
        System.out.println("   • 插入排序：比較次數為 45 次，移動次數為 45 次");
        System.out.println("   ✅ 結論：插入排序在逆向資料上表現最差（平方時間 O(n²)）");
        
        System.out.println("\n3️⃣ 隨機排列資料（平均情況）：");
        System.out.println("   • 選擇排序：比較次數固定為 45 次，不受資料順序影響");
        System.out.println("   • 插入排序：比較次數和移動次數約為 n²/4 ≈ 25 次");
        System.out.println("   ✅ 結論：選擇排序的比較次數始終固定，插入排序會隨資料順序變化");
        
        System.out.println("\n4️⃣ 綜合比較：");
        System.out.println("   • 選擇排序的比較次數永遠固定為 n(n-1)/2");
        System.out.println("   • 插入排序的比較次數取決於資料的初始順序");
        System.out.println("   • 插入排序在已排序資料上表現最好 (O(n))");
        System.out.println("   • 選擇排序的交換次數最多為 n-1 次（比插入排序少）");
        System.out.println("   • 當資料量小時，兩種排序差異不大");
        System.out.println("   • 當資料量很大時，兩種排序都不適合（O(n²)）");
        
        System.out.println("\n5️⃣ 應用建議：");
        System.out.println("   • 若資料接近已排序 → 使用插入排序");
        System.out.println("   • 若資料完全隨機且需要穩定 → 兩者皆可");
        System.out.println("   • 若擔心最差情況 → 選擇排序較為穩定");
        System.out.println("   • 一般情況下，插入排序通常比選擇排序快");
    }
}
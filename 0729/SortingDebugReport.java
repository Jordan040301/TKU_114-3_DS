import java.util.Arrays;

public class SortingDebugReport {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║              ★ 排序演算法除錯報告 ★                             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");

        // 準備測試資料（能夠清楚顯示錯誤）
        int[] testData = {30, 10, 50, 20, 40};
        int[] originalData = {30, 10, 50, 20, 40};

        System.out.println("\n📊 原始測試資料：" + arrayToString(testData));
        System.out.println("═══════════════════════════════════════════════════════════════════\n");

        // ==================== 錯誤版本 1：內層範圍錯誤 ====================
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║              🔴 錯誤版本 1：內層範圍錯誤                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
        
        int[] data1 = copyArray(originalData);
        System.out.println("原始資料：" + arrayToString(data1));
        selectionSortWrongRange(data1);
        System.out.println("錯誤結果：" + arrayToString(data1));
        System.out.println("\n✅ 修正後：");
        int[] data1Fixed = copyArray(originalData);
        selectionSortCorrect(data1Fixed);
        System.out.println("修正結果：" + arrayToString(data1Fixed));

        // ==================== 錯誤版本 2：key 未儲存 ====================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║              🔴 錯誤版本 2：key 未儲存                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
        
        int[] data2 = copyArray(originalData);
        System.out.println("原始資料：" + arrayToString(data2));
        insertionSortWrongKey(data2);
        System.out.println("錯誤結果：" + arrayToString(data2));
        System.out.println("\n✅ 修正後：");
        int[] data2Fixed = copyArray(originalData);
        insertionSortCorrect(data2Fixed);
        System.out.println("修正結果：" + arrayToString(data2Fixed));

        // ==================== 錯誤版本 3：比較方向錯誤 ====================
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║              🔴 錯誤版本 3：比較方向錯誤                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
        
        int[] data3 = copyArray(originalData);
        System.out.println("原始資料：" + arrayToString(data3));
        selectionSortWrongDirection(data3);
        System.out.println("錯誤結果：" + arrayToString(data3));
        System.out.println("\n✅ 修正後：");
        int[] data3Fixed = copyArray(originalData);
        selectionSortCorrect(data3Fixed);
        System.out.println("修正結果：" + arrayToString(data3Fixed));

        // ==================== 總結報告 ====================
        printSummary();
    }

    // ================================================================
    // 錯誤版本 1：內層範圍錯誤（選擇排序）
    // 錯誤說明：內層迴圈的起始值錯誤，導致重複比較或跳過元素
    // ================================================================
    
    /**
     * ❌ 錯誤版本 1：選擇排序 - 內層範圍錯誤
     * 錯誤原因：內層迴圈從 i 開始而不是 i+1，導致自己跟自己比較
     * 影響：可能找到錯誤的最小值，排序結果不正確
     */
    public static void selectionSortWrongRange(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            // ❌ 錯誤：應該從 i+1 開始，卻從 i 開始
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 交換
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    /**
     * ✅ 修正版本 1：選擇排序 - 正確的內層範圍
     * 修正說明：內層迴圈從 i+1 開始，避免自己跟自己比較
     */
    public static void selectionSortCorrect(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            // ✅ 正確：從 i+1 開始
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 交換
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    // ================================================================
    // 錯誤版本 2：key 未儲存（插入排序）
    // 錯誤說明：在移動元素時，key 的值被覆蓋，導致資料遺失
    // ================================================================

    /**
     * ❌ 錯誤版本 2：插入排序 - key 未儲存
     * 錯誤原因：在 while 迴圈中直接使用 arr[i] 作為 key，移動時被覆蓋
     * 影響：key 的值遺失，插入錯誤的資料
     */
    public static void insertionSortWrongKey(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // ❌ 錯誤：沒有儲存 key，直接使用 arr[i]
            int position = i - 1;
            while (position >= 0 && arr[position] > arr[i]) {
                arr[position + 1] = arr[position];
                position--;
            }
            // 這裡 arr[i] 可能已經被覆蓋
            arr[position + 1] = arr[i];
        }
    }

    /**
     * ✅ 修正版本 2：插入排序 - 正確儲存 key
     * 修正說明：先將 key 儲存到變數中，避免被覆蓋
     */
    public static void insertionSortCorrect(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // ✅ 正確：先儲存 key
            int key = arr[i];
            int position = i - 1;
            while (position >= 0 && arr[position] > key) {
                arr[position + 1] = arr[position];
                position--;
            }
            arr[position + 1] = key;
        }
    }

    // ================================================================
    // 錯誤版本 3：比較方向錯誤（選擇排序）
    // 錯誤說明：比較時使用錯誤的方向，導致選擇最大值而非最小值
    // ================================================================

    /**
     * ❌ 錯誤版本 3：選擇排序 - 比較方向錯誤
     * 錯誤原因：應該找最小值卻使用 > 找最大值
     * 影響：排序結果為降冪而非升冪
     */
    public static void selectionSortWrongDirection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int targetIndex = i;
            // ❌ 錯誤：使用 > 找最大值，應該使用 < 找最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[targetIndex]) {
                    targetIndex = j;
                }
            }
            // 交換
            int temp = arr[i];
            arr[i] = arr[targetIndex];
            arr[targetIndex] = temp;
        }
    }

    /**
     * ✅ 修正版本 3：選擇排序 - 正確的比較方向
     * 修正說明：使用 < 尋找最小值，確保升冪排序
     */
    public static void selectionSortCorrectDirection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            // ✅ 正確：使用 < 找最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 交換
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    // ================================================================
    // 輔助方法
    // ================================================================

    /**
     * 複製陣列
     */
    public static int[] copyArray(int[] original) {
        return Arrays.copyOf(original, original.length);
    }

    /**
     * 將陣列轉為字串
     */
    public static String arrayToString(int[] arr) {
        if (arr == null || arr.length == 0) {
            return "[]";
        }
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
     * 輸出總結報告
     */
    public static void printSummary() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║              📋 除錯總結報告                                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n┌────────────────────────────────────────────────────────────────────┐");
        System.out.println("│ 錯誤編號 │ 錯誤類型         │ 錯誤原因           │ 影響         │");
        System.out.println("├──────────┼──────────────────┼────────────────────┼──────────────┤");
        System.out.println("│   1      │ 內層範圍錯誤     │ j 從 i 開始        │ 自己跟自己   │");
        System.out.println("│          │ (選擇排序)       │ 而非 i+1          │ 比較，可能   │");
        System.out.println("│          │                  │                    │ 選錯最小值   │");
        System.out.println("├──────────┼──────────────────┼────────────────────┼──────────────┤");
        System.out.println("│   2      │ key 未儲存       │ 直接使用 arr[i]   │ key 被覆蓋   │");
        System.out.println("│          │ (插入排序)       │ 未先儲存          │ 資料遺失     │");
        System.out.println("├──────────┼──────────────────┼────────────────────┼──────────────┤");
        System.out.println("│   3      │ 比較方向錯誤     │ 使用 > 找最大值   │ 降冪排序     │");
        System.out.println("│          │ (選擇排序)       │ 應使用 < 找最小值  │ 而非升冪     │");
        System.out.println("└──────────┴──────────────────┴────────────────────┴──────────────┘");
        
        System.out.println("\n📝 除錯心得與建議：");
        System.out.println("  1. 選擇排序的內層迴圈一定要從 i+1 開始，避免自己跟自己比較");
        System.out.println("  2. 插入排序一定要先儲存 key，移動元素時才不會遺失資料");
        System.out.println("  3. 排序前先確認想要的排序方向（升冪/降冪）");
        System.out.println("  4. 使用測試資料驗證排序結果是否正確");
        System.out.println("  5. 建議使用 small data 進行逐步追蹤除錯");
        System.out.println("  6. 比較符號的方向決定升冪或降冪：");
        System.out.println("     - 升冪：使用 < 找最小值");
        System.out.println("     - 降冪：使用 > 找最大值");
    }
}
/**
 * MergeSortPractice.java
 * 課堂實踐題二：歸併排序追蹤
 * 
 * 功能：
 * 1. 使用 {41, 12, 35, 8, 27, 19, 50, 3} 進行測試。
 * 2. 完成 mergeSort() 及 merge()。
 * 3. 顯示每一次分割範圍。
 * 4. 顯示每次合併後的區間內容。
 * 5. 測試空白、單筆、已排序及逆向資料。
 * 
 * 完成標準：傳回範圍不重疊、不遺漏、合併結果及停止條件正確。
 */
public class MergeSortPractice {

    // 用於追蹤遞迴深度，方便縮排顯示
    private static int depth = 0;

    public static void main(String[] args) {
        System.out.println("=== 測試案例 1：一般資料（包含重複值） ===");
        int[] array1 = {41, 12, 35, 8, 27, 19, 50, 3};
        testMergeSort(array1, "一般資料");

        System.out.println("\n=== 測試案例 2：空白陣列 ===");
        int[] array2 = {};
        testMergeSort(array2, "空白陣列");

        System.out.println("\n=== 測試案例 3：單筆資料 ===");
        int[] array3 = {42};
        testMergeSort(array3, "單筆資料");

        System.out.println("\n=== 測試案例 4：已排序資料 ===");
        int[] array4 = {1, 2, 3, 4, 5, 6, 7, 8};
        testMergeSort(array4, "已排序資料");

        System.out.println("\n=== 測試案例 5：逆向資料 ===");
        int[] array5 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        testMergeSort(array5, "逆向資料");
    }

    /**
     * 測試歸併排序並顯示結果
     * @param arr 要排序的陣列
     * @param description 測試描述
     */
    private static void testMergeSort(int[] arr, String description) {
        System.out.println("原始 " + description + ": " + arrayToString(arr));
        if (arr.length == 0) {
            System.out.println("結果: []");
            return;
        }
        int[] copy = arr.clone(); // 複製一份以保留原始資料
        depth = 0;
        mergeSort(copy, 0, copy.length - 1);
        System.out.println("排序後結果: " + arrayToString(copy));
    }

    /**
     * 歸併排序主方法（使用遞迴）
     * @param arr 要排序的陣列
     * @param left 左邊界索引（包含）
     * @param right 右邊界索引（包含）
     */
    public static void mergeSort(int[] arr, int left, int right) {
        // 停止條件：區間內只有一個元素或無效區間
        if (left >= right) {
            // 顯示停止條件（只有一個元素時）
            if (left == right) {
                printIndent("停止條件：區間 [" + left + ", " + right + "] 只有單一元素 [" + arr[left] + "]");
            }
            return;
        }

        // 計算中間點
        int mid = left + (right - left) / 2;

        // 顯示當前分割範圍
        printIndent("分割區間 [" + left + ", " + right + "] -> 左 [" + left + ", " + mid + "] 右 [" + (mid + 1) + ", " + right + "]");

        // 遞迴排序左半部
        depth++;
        mergeSort(arr, left, mid);
        depth--;

        // 遞迴排序右半部
        depth++;
        mergeSort(arr, mid + 1, right);
        depth--;

        // 合併兩個已排序的子陣列
        printIndent("合併前：左區間 [" + left + ", " + mid + "] = " + subarrayToString(arr, left, mid) + 
                   ", 右區間 [" + (mid + 1) + ", " + right + "] = " + subarrayToString(arr, mid + 1, right));
        
        merge(arr, left, mid, right);

        // 顯示合併後的區間內容
        printIndent("合併後：區間 [" + left + ", " + right + "] = " + subarrayToString(arr, left, right));
        System.out.println();
    }

    /**
     * 合併兩個已排序的子陣列
     * @param arr 原始陣列
     * @param left 左邊界
     * @param mid 中間點（左子陣列的最後一個元素索引）
     * @param right 右邊界
     */
    public static void merge(int[] arr, int left, int mid, int right) {
        // 計算兩個子陣列的長度
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // 建立臨時陣列
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // 複製資料到臨時陣列
        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }

        // 合併兩個臨時陣列回到原陣列
        int i = 0; // 左子陣列索引
        int j = 0; // 右子陣列索引
        int k = left; // 原陣列索引

        // 比較並合併
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // 複製左子陣列剩餘元素
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // 複製右子陣列剩餘元素
        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    /**
     * 輔助方法：根據深度印出縮排
     * @param message 要印出的訊息
     */
    private static void printIndent(String message) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("  ");
        }
        System.out.println(indent.toString() + message);
    }

    /**
     * 輔助方法：將陣列轉換為字串
     * @param arr 要轉換的陣列
     * @return 陣列的字串表示
     */
    private static String arrayToString(int[] arr) {
        if (arr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
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
     * 輔助方法：將陣列的子區間轉換為字串
     * @param arr 原始陣列
     * @param start 起始索引
     * @param end 結束索引
     * @return 子區間的字串表示
     */
    private static String subarrayToString(int[] arr, int start, int end) {
        if (start > end || start < 0 || end >= arr.length) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = start; i <= end; i++) {
            sb.append(arr[i]);
            if (i < end) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
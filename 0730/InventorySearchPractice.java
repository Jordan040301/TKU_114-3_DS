/**
 * InventorySearchPractice.java
 * 課堂實踐題三：先排序再搜尋
 * 
 * 功能：
 * 1. 至少建立12筆未排序庫存編號。
 * 2. 使用Merge Sort依序排序。
 * 3. 使用二分查找查詢指定編號。
 * 4. 顯示排序前、排序後及搜尋索引。
 * 5. 測試第一筆、最後一筆及不存在編號。
 * 
 * 完成標準：排序與搜尋使用鍵相同值及方向，所有邊界案例。
 */
public class InventorySearchPractice {

    public static void main(String[] args) {
        // 建立12筆未排序庫存編號（包含重複值和各種情況）
        int[] inventory = {104, 201, 58, 92, 167, 33, 450, 76, 215, 188, 92, 311};
        
        System.out.println("=== 庫存系統示範：排序與二分搜尋 ===");
        System.out.println("原始庫存編號: " + arrayToString(inventory));
        System.out.println("庫存總筆數: " + inventory.length);
        System.out.println();
        
        // 複製原始資料進行排序（保留原始資料以供對比）
        int[] sortedInventory = inventory.clone();
        
        // 使用Merge Sort排序
        System.out.println("=== 開始歸併排序 ===");
        mergeSort(sortedInventory, 0, sortedInventory.length - 1);
        System.out.println("排序後庫存編號: " + arrayToString(sortedInventory));
        System.out.println();
        
        // 測試各種搜尋案例
        System.out.println("=== 二分搜尋測試 ===");
        
        // 測試案例1：搜尋第一筆（最小值）
        int target1 = sortedInventory[0];
        testBinarySearch(sortedInventory, target1, "第一筆（最小值）");
        
        // 測試案例2：搜尋最後一筆（最大值）
        int target2 = sortedInventory[sortedInventory.length - 1];
        testBinarySearch(sortedInventory, target2, "最後一筆（最大值）");
        
        // 測試案例3：搜尋中間值
        int target3 = sortedInventory[sortedInventory.length / 2];
        testBinarySearch(sortedInventory, target3, "中間值");
        
        // 測試案例4：搜尋不存在的編號（小於最小值）
        int target4 = sortedInventory[0] - 1;
        testBinarySearch(sortedInventory, target4, "不存在的編號（小於最小值）");
        
        // 測試案例5：搜尋不存在的編號（大於最大值）
        int target5 = sortedInventory[sortedInventory.length - 1] + 1;
        testBinarySearch(sortedInventory, target5, "不存在的編號（大於最大值）");
        
        // 測試案例6：搜尋不存在的編號（在範圍內）
        int target6 = sortedInventory[sortedInventory.length / 2] + 1;
        // 確保這個編號真的不存在
        while (binarySearch(sortedInventory, target6) != -1) {
            target6++;
        }
        testBinarySearch(sortedInventory, target6, "不存在的編號（在範圍內）");
        
        // 測試案例7：搜尋重複值（92出現了兩次）
        int target7 = 92;
        testBinarySearch(sortedInventory, target7, "重複值（92，出現兩次）");
    }
    
    /**
     * 測試二分搜尋並顯示結果
     * @param arr 已排序陣列
     * @param target 要搜尋的目標
     * @param description 測試描述
     */
    private static void testBinarySearch(int[] arr, int target, String description) {
        System.out.println("\n--- 測試：" + description + " ---");
        System.out.println("搜尋目標: " + target);
        
        int index = binarySearch(arr, target);
        
        if (index != -1) {
            System.out.println("找到目標！索引位置: " + index);
            System.out.println("該位置的值: " + arr[index]);
            
            // 檢查是否有重複值（前後是否相同）
            boolean hasDuplicate = false;
            if (index > 0 && arr[index - 1] == target) {
                hasDuplicate = true;
                System.out.println("注意：此值在索引 " + (index - 1) + " 也有相同值（重複）");
            }
            if (index < arr.length - 1 && arr[index + 1] == target) {
                hasDuplicate = true;
                System.out.println("注意：此值在索引 " + (index + 1) + " 也有相同值（重複）");
            }
            if (!hasDuplicate) {
                System.out.println("此值在陣列中是唯一的");
            }
        } else {
            System.out.println("找不到目標！目標編號 " + target + " 不存在於庫存中");
        }
        
        // 顯示搜尋範圍輔助
        System.out.println("陣列範圍: [" + arr[0] + ", " + arr[arr.length - 1] + "]");
    }
    
    /**
     * 歸併排序主方法
     * @param arr 要排序的陣列
     * @param left 左邊界
     * @param right 右邊界
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            // 遞迴排序左右兩半
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            
            // 合併已排序的兩半
            merge(arr, left, mid, right);
        }
    }
    
    /**
     * 合併兩個已排序的子陣列
     * @param arr 原始陣列
     * @param left 左邊界
     * @param mid 中間點
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
        
        // 合併兩個臨時陣列
        int i = 0, j = 0, k = left;
        
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
        
        // 複製剩餘元素
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
     * 二分搜尋（迭代版本）
     * @param arr 已排序陣列
     * @param target 要搜尋的目標值
     * @return 目標值的索引，如果找不到則返回 -1
     */
    public static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = arr.length - 1;
        int steps = 0;
        
        System.out.println("開始二分搜尋...");
        
        while (left <= right) {
            steps++;
            int mid = left + (right - left) / 2;
            
            System.out.println("  步驟 " + steps + ": 搜尋範圍 [" + left + ", " + right + 
                              "], 中間索引 " + mid + ", 值 = " + arr[mid]);
            
            if (arr[mid] == target) {
                System.out.println("  找到目標！共花費 " + steps + " 步");
                return mid;
            } else if (arr[mid] < target) {
                System.out.println("  " + arr[mid] + " < " + target + ", 往右搜尋");
                left = mid + 1;
            } else {
                System.out.println("  " + arr[mid] + " > " + target + ", 往左搜尋");
                right = mid - 1;
            }
        }
        
        System.out.println("  搜尋結束，共花費 " + steps + " 步，未找到目標");
        return -1;
    }
    
    /**
     * 輔助方法：將陣列轉換為字串
     * @param arr 要轉換的陣列
     * @return 陣列的字串表示
     */
    private static String arrayToString(int[] arr) {
        if (arr == null || arr.length == 0) {
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
}
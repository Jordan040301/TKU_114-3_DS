/**
 * MergeArrayPractice.java
 * 課堂實踐題一：合併兩個排序排列
 * 
 * 功能：
 * 1. 建立兩個長度不同的已排序序列。
 * 2. 不使用 Arrays.sort()。
 * 3. 使用三個索引完成合併。
 * 4. 處理正確的一個陣列為空。
 * 5. 測試重複值及負數。
 * 
 * 完成標準：所有輸入元素只出現一次，結果長度及排序順序正確。
 */
public class MergeArrayPractice {

    public static void main(String[] args) {
        // 測試案例 1：一般情況，包含重複值與負數
        int[] array1 = {-5, -3, 0, 2, 2, 8, 10};
        int[] array2 = {-10, -3, 1, 2, 5, 7, 9, 12, 15};

        System.out.println("測試案例 1：");
        System.out.print("陣列 1: ");
        printArray(array1);
        System.out.print("陣列 2: ");
        printArray(array2);

        int[] merged = mergeSortedArraysUnique(array1, array2);
        System.out.print("合併後 (唯一元素，已排序): ");
        printArray(merged);
        System.out.println("長度: " + merged.length);
        System.out.println();

        // 測試案例 2：其中一個陣列為空
        int[] array3 = {};
        int[] array4 = {-5, 0, 5, 10};

        System.out.println("測試案例 2 (陣列 1 為空)：");
        System.out.print("陣列 1: ");
        printArray(array3);
        System.out.print("陣列 2: ");
        printArray(array4);

        int[] merged2 = mergeSortedArraysUnique(array3, array4);
        System.out.print("合併後 (唯一元素，已排序): ");
        printArray(merged2);
        System.out.println("長度: " + merged2.length);
        System.out.println();

        // 測試案例 3：另一個陣列為空
        int[] array5 = {-2, 0, 3};
        int[] array6 = {};

        System.out.println("測試案例 3 (陣列 2 為空)：");
        System.out.print("陣列 1: ");
        printArray(array5);
        System.out.print("陣列 2: ");
        printArray(array6);

        int[] merged3 = mergeSortedArraysUnique(array5, array6);
        System.out.print("合併後 (唯一元素，已排序): ");
        printArray(merged3);
        System.out.println("長度: " + merged3.length);
        System.out.println();

        // 測試案例 4：兩個都是空陣列
        int[] array7 = {};
        int[] array8 = {};

        System.out.println("測試案例 4 (兩個皆為空)：");
        System.out.print("陣列 1: ");
        printArray(array7);
        System.out.print("陣列 2: ");
        printArray(array8);

        int[] merged4 = mergeSortedArraysUnique(array7, array8);
        System.out.print("合併後 (唯一元素，已排序): ");
        printArray(merged4);
        System.out.println("長度: " + merged4.length);
    }

    /**
     * 合併兩個已排序的整數陣列，並移除重複元素，結果保持排序。
     * 使用三個索引（i, j, k）進行合併。
     *
     * @param arr1 第一個已排序陣列（可能為空）
     * @param arr2 第二個已排序陣列（可能為空）
     * @return 包含所有唯一元素且已排序的新陣列
     */
    public static int[] mergeSortedArraysUnique(int[] arr1, int[] arr2) {
        // 處理特殊情況：兩個都為空
        if (arr1.length == 0 && arr2.length == 0) {
            return new int[0];
        }

        // 預先計算最多可能的元素數量（兩個陣列總長）
        int maxSize = arr1.length + arr2.length;
        int[] temp = new int[maxSize]; // 暫存陣列，可能包含重複

        int i = 0; // arr1 的索引
        int j = 0; // arr2 的索引
        int k = 0; // temp 的索引

        // 合併兩個陣列（暫存所有元素，包含重複）
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                temp[k++] = arr1[i++];
            } else {
                temp[k++] = arr2[j++];
            }
        }

        // 將 arr1 剩餘元素複製到 temp
        while (i < arr1.length) {
            temp[k++] = arr1[i++];
        }

        // 將 arr2 剩餘元素複製到 temp
        while (j < arr2.length) {
            temp[k++] = arr2[j++];
        }

        // 現在 temp[0..k-1] 是已排序但可能包含重複的陣列
        // 建立結果陣列（移除重複）
        if (k == 0) {
            return new int[0];
        }

        // 先計算唯一元素的數量
        int uniqueCount = 1;
        for (int idx = 1; idx < k; idx++) {
            if (temp[idx] != temp[idx - 1]) {
                uniqueCount++;
            }
        }

        // 建立結果陣列並填入唯一元素
        int[] result = new int[uniqueCount];
        result[0] = temp[0];
        int resultIdx = 1;
        for (int idx = 1; idx < k; idx++) {
            if (temp[idx] != temp[idx - 1]) {
                result[resultIdx++] = temp[idx];
            }
        }

        return result;
    }

    /**
     * 輔助方法：印出陣列內容。
     *
     * @param arr 要印出的陣列
     */
    public static void printArray(int[] arr) {
        if (arr.length == 0) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
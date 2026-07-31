public class SelectionSortPractice {

    public static void main(String[] args) {
        System.out.println("=== 測試1：一般數組 ===");
        int[] arr1 = {42, 18, 35, 7, 29, 14};
        selectionSortWithTrace(arr1);
        
        System.out.println("\n=== 測試2：空數組 ===");
        int[] arr2 = {};
        selectionSortWithTrace(arr2);
        
        System.out.println("\n=== 測試3：單一元素數組 ===");
        int[] arr3 = {5};
        selectionSortWithTrace(arr3);
    }
    
    /**
     * 選擇排序（升冪），並顯示每輪追蹤資訊
     */
    public static void selectionSortWithTrace(int[] arr) {
        // 檢查空數組或單一元素
        if (arr.length <= 1) {
            System.out.print("數組內容：[");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]);
                if (i < arr.length - 1) System.out.print(", ");
            }
            System.out.println("]");
            System.out.println("比較次數：0");
            System.out.println("實際交換次數：0");
            return;
        }
        
        int comparisonCount = 0;  // 比較次數
        int swapCount = 0;        // 實際交換次數
        
        System.out.println("原始數組：" + arrayToString(arr));
        System.out.println();
        
        // 選擇排序主迴圈
        for (int start = 0; start < arr.length - 1; start++) {
            System.out.println("=== 第 " + (start + 1) + " 輪 ===");
            System.out.println("起始位置 start = " + start);
            
            int minIndex = start;
            
            // 在未排序部分尋找最小值
            for (int i = start + 1; i < arr.length; i++) {
                comparisonCount++;  // 每次比較都計數
                System.out.println("  比較 arr[" + i + "]=" + arr[i] + " 與 arr[" + minIndex + "]=" + arr[minIndex]);
                
                if (arr[i] < arr[minIndex]) {
                    minIndex = i;
                    System.out.println("  ★ 找到更小的值：arr[" + minIndex + "]=" + arr[minIndex]);
                }
            }
            
            System.out.println("  選取的索引：minIndex = " + minIndex + ", 內容 = " + arr[minIndex]);
            
            // 如果最小值不在起始位置，才進行交換
            if (minIndex != start) {
                System.out.println("  執行交換：arr[" + start + "]=" + arr[start] + " ↔ arr[" + minIndex + "]=" + arr[minIndex]);
                // 交換
                int temp = arr[start];
                arr[start] = arr[minIndex];
                arr[minIndex] = temp;
                swapCount++;
            } else {
                System.out.println("  最小值已在正確位置，無需交換");
            }
            
            System.out.println("  當前數組：" + arrayToString(arr));
            System.out.println();
        }
        
        // 輸出統計結果
        System.out.println("=== 排序完成 ===");
        System.out.println("最終數組：" + arrayToString(arr));
        System.out.println("總比較次數：" + comparisonCount);
        System.out.println("總實際交換次數：" + swapCount);
    }
    
    /**
     * 將數組轉為字串顯示
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
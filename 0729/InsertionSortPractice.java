public class InsertionSortPractice {

    public static void main(String[] args) {
        System.out.println("=== 測試1：一般數組 ===");
        int[] arr1 = {30, 10, 20, 50, 40, 5};
        insertionSortWithTrace(arr1);
        
        System.out.println("\n=== 測試2：已排序數組（最佳情況） ===");
        int[] arr2 = {5, 10, 20, 30, 40, 50};
        insertionSortWithTrace(arr2);
        
        System.out.println("\n=== 測試3：逆向排序數組（最差情況） ===");
        int[] arr3 = {50, 40, 30, 20, 10, 5};
        insertionSortWithTrace(arr3);
        
        System.out.println("\n=== 測試4：單一元素數組 ===");
        int[] arr4 = {10};
        insertionSortWithTrace(arr4);
        
        System.out.println("\n=== 測試5：空數組 ===");
        int[] arr5 = {};
        insertionSortWithTrace(arr5);
    }
    
    /**
     * 插入排序並顯示追蹤資訊
     */
    public static void insertionSortWithTrace(int[] arr) {
        if (arr.length <= 1) {
            System.out.println("數組內容：" + arrayToString(arr));
            System.out.println("比較次數：0");
            System.out.println("移動次數：0");
            return;
        }
        
        System.out.println("原始數組：" + arrayToString(arr));
        System.out.println();
        
        int comparisonCount = 0;  // 比較次數
        int shiftCount = 0;       // 元素移動次數
        
        for (int index = 1; index < arr.length; index++) {
            int key = arr[index];
            int position = index - 1;
            
            System.out.println("=== 第 " + index + " 回合 ===");
            System.out.println("key = arr[" + index + "] = " + key);
            System.out.println("已排序區間：[0] 到 [" + (index - 1) + "]");
            
            // 尋找插入位置並移動元素
            while (position >= 0 && arr[position] > key) {
                comparisonCount++;
                System.out.println("  比較：arr[" + position + "]=" + arr[position] + " > key=" + key + " (true)");
                System.out.println("  移動：arr[" + (position + 1) + "] = arr[" + position + "] = " + arr[position]);
                
                arr[position + 1] = arr[position];  // 向右移動
                shiftCount++;
                position--;
            }
            
            // 如果 while 條件因 position < 0 結束，最後一次比較可能沒計算到
            if (position >= 0) {
                comparisonCount++;
                System.out.println("  比較：arr[" + position + "]=" + arr[position] + " > key=" + key + " (false)");
            }
            
            // 插入 key
            int insertPosition = position + 1;
            System.out.println("  插入位置：" + insertPosition);
            arr[insertPosition] = key;
            
            System.out.println("  目前數組：" + arrayToString(arr));
            System.out.println();
        }
        
        // 輸出統計結果
        System.out.println("=== 排序完成 ===");
        System.out.println("最終數組：" + arrayToString(arr));
        System.out.println("總比較次數：" + comparisonCount);
        System.out.println("總移動次數：" + shiftCount);
        System.out.println();
    }
    
    /**
     * 將陣列轉為字串顯示
     */
    public static String arrayToString(int[] arr) {
        if (arr.length == 0) {
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
}
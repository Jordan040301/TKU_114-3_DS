public class RecursiveNameSearchPractice {
    public static void main(String[] args) {
        // 建立姓名陣列
        String[] names = {
            "Alice", "Bob", "Charlie", "David", 
            "Emma", "Frank", "Grace", "Henry", 
            "Ivy", "Jack", "Kevin", "Lisa"
        };
        
        System.out.println("姓名陣列: ");
        for (int i = 0; i < names.length; i++) {
            System.out.println("索引 " + i + ": " + names[i]);
        }
        System.out.println("------------------------");
        
        // 測試各種情況
        testSearch(names, "Alice");    // 第一筆
        testSearch(names, "Lisa");     // 最後一筆
        testSearch(names, "Frank");    // 中間資料
        testSearch(names, "Henry");    // 中間資料
        testSearch(names, "Zoe");      // 不存在資料
        testSearch(names, "");         // 空字串
        testSearch(names, null);       // null 測試
    }
    
    /**
     * 測試搜索功能的輔助方法
     */
    public static void testSearch(String[] names, String target) {
        System.out.println("搜尋目標: \"" + target + "\"");
        int result = search(names, target, 0);
        
        if (result != -1) {
            System.out.println("找到 \"" + target + "\"，位於索引 " + result);
        } else {
            System.out.println("找不到 \"" + target + "\"");
        }
        System.out.println("------------------------");
    }
    
    /**
     * 遞迴搜索方法
     * @param names 姓名陣列
     * @param target 要找的目標字串
     * @param index 當前搜索的索引位置
     * @return 找到時回傳索引，找不到時回傳 -1
     */
    public static int search(String[] names, String target, int index) {
        // 基本情況 1: 處理 null 或空陣列
        if (names == null || index >= names.length) {
            return -1;
        }
        
        // 基本情況 2: 處理 target 為 null 的情況
        if (target == null) {
            // 檢查當前元素是否為 null
            if (names[index] == null) {
                return index;
            }
            // 繼續搜索下一個
            return search(names, target, index + 1);
        }
        
        // 基本情況 3: 找到目標值
        if (target.equals(names[index])) {
            return index;
        }
        
        // 遞迴情況: 繼續搜索下一個索引
        return search(names, target, index + 1);
    }
}
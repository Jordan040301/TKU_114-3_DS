public class RecursiveDigitCounter {
    public static void main(String[] args) {
        System.out.println("=== 遞迴統計數字出現次數 ===\n");
        
        // 至少準備 6 組測試數據
        testCount(1234567890, 0);  // 測試 0
        testCount(1234567890, 1);  // 測試 1
        testCount(1234567890, 5);  // 測試中間數字
        testCount(1234567890, 9);  // 測試 9
        testCount(1111111111, 1);  // 測試重複數字
        testCount(1234567890, 3);  // 測試存在的數字
        testCount(1234567890, 7);  // 測試存在的數字
        testCount(987654321, 0);   // 測試目標不存在 (0 不在 987654321 中)
        testCount(555555555, 5);   // 測試全部相同數字
        testCount(1000000000, 0);  // 測試多個 0
        testCount(0, 0);           // 測試數字為 0
        testCount(0, 5);           // 測試數字為 0，目標不存在
    }
    
    /**
     * 測試輔助方法
     */
    public static void testCount(int number, int target) {
        System.out.println("數字: " + number + ", 目標數字: " + target);
        int result = countDigit(number, target);
        System.out.println("出現次數: " + result);
        System.out.println("------------------------");
    }
    
    /**
     * 遞迴統計數字出現次數
     * @param number 要統計的數字 (非負整數)
     * @param target 要統計的目標數字 (0-9)
     * @return 目標數字在 number 中出現的次數
     */
    public static int countDigit(int number, int target) {
        // 基本情況 1: 處理 target 不在 0-9 範圍內
        if (target < 0 || target > 9) {
            return -1;  // 回傳 -1 表示無效的 target
        }
        
        // 基本情況 2: number 為 0 時的特殊處理
        if (number == 0) {
            // 如果 target 也是 0，表示數字 0 中包含一個 0
            return (target == 0) ? 1 : 0;
        }
        
        // 基本情況 3: 數字已處理完畢 (number 變為 0)
        if (number == 0) {
            return 0;
        }
        
        // 遞迴情況: 檢查最後一位數字
        int lastDigit = number % 10;
        int count = (lastDigit == target) ? 1 : 0;
        
        // 遞迴處理剩餘數字
        return count + countDigit(number / 10, target);
    }
}
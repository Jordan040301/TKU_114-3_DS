public class Q05_RecursiveArrayReport {
    public static void main(String[] args) {
        int[] data = {12, -3, 25, 8, 25, 40, 5};

        System.out.println("10~30 筆數: " + countInRange(data, 0, 10, 30));
        System.out.println("正數總和: " + sumPositive(data, 0));
        System.out.println("25 最後索引: " + findLast(data, 0, 25));
        System.out.println("99 最後索引: " + findLast(data, 0, 99));
    }

    public static int countInRange(int[] data, int index, int minimum, int maximum) {
        // 遞迴終止條件：已處理完所有元素
        if (index >= data.length) {
            return 0;
        }
        
        // 檢查當前元素是否在範圍內（包含上下界）
        int count = 0;
        if (data[index] >= minimum && data[index] <= maximum) {
            count = 1;
        }
        
        // 遞迴處理下一個元素
        return count + countInRange(data, index + 1, minimum, maximum);
    }

    public static int sumPositive(int[] data, int index) {
        // 遞迴終止條件：已處理完所有元素
        if (index >= data.length) {
            return 0;
        }
        
        // 檢查當前元素是否為正數
        int value = 0;
        if (data[index] > 0) {
            value = data[index];
        }
        
        // 遞迴處理下一個元素
        return value + sumPositive(data, index + 1);
    }

    public static int findLast(int[] data, int index, int target) {
        // 遞迴終止條件：已處理完所有元素
        if (index >= data.length) {
            return -1;
        }
        
        // 先遞迴處理後面的元素（從右到左找）
        int lastIndex = findLast(data, index + 1, target);
        
        // 如果後面有找到，直接回傳
        if (lastIndex != -1) {
            return lastIndex;
        }
        
        // 如果後面沒找到，檢查當前元素
        if (data[index] == target) {
            return index;
        }
        
        // 都沒找到
        return -1;
    }
}
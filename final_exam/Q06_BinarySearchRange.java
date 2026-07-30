public class Q06_BinarySearchRange {
    public static void main(String[] args) {
        int[] data = {5, 10, 10, 10, 18, 25, 25, 40};

        System.out.println("10 第一次: " + findFirst(data, 10));
        System.out.println("10 最后一次: " + findLast(data, 10));
        System.out.println("10 出現次數: " + countOccurrences(data, 10));
        System.out.println("99 第一次: " + findFirst(data, 99));
    }

    public static int findFirst(int[] data, int target) {
        if (data == null || data.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = data.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (data[mid] == target) {
                result = mid;      // 记录找到的位置
                right = mid - 1;   // 继续向左找第一个
            } else if (data[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }

    public static int findLast(int[] data, int target) {
        if (data == null || data.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = data.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (data[mid] == target) {
                result = mid;      // 记录找到的位置
                left = mid + 1;    // 继续向右找最后一个
            } else if (data[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }

    public static int countOccurrences(int[] data, int target) {
        if (data == null || data.length == 0) {
            return 0;
        }
        
        int first = findFirst(data, target);
        if (first == -1) {
            return 0;
        }
        
        int last = findLast(data, target);
        return last - first + 1;
    }
}
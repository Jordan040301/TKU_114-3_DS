public class Q08_ArrayRemove {
    public static void main(String[] args) {
        int[] values = {4, 7, 2, 7, 9, 7, 1};
        int target = 7;

        System.out.println("出現次數：" + countOccurrences(values, target));
        System.out.println("最終索引：" + findLastIndex(values, target));

        int[] result = removeAll(values, target);
        System.out.print("移除后：");
        printArray(result);
        System.out.print("原始阵列：");
        printArray(values);
    }

    // ==================== 方法1：计算出现次数 ====================
    public static int countOccurrences(int[] data, int target) {
        if (data == null) {
            return 0;
        }
        int count = 0;
        for (int value : data) {
            if (value == target) {
                count++;
            }
        }
        return count;
    }

    // ==================== 方法2：查找最后出现的索引 ====================
    public static int findLastIndex(int[] data, int target) {
        if (data == null) {
            return -1;
        }
        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // ==================== 方法3：移除所有指定值 ====================
    public static int[] removeAll(int[] data, int target) {
        if (data == null) {
            return new int[0];
        }

        // 第一步：计算移除后新数组的长度
        int newSize = 0;
        for (int value : data) {
            if (value != target) {
                newSize++;
            }
        }

        // 第二步：建立刚好足够长度的新数组
        int[] result = new int[newSize];

        // 第三步：填入非目标值的元素
        int index = 0;
        for (int value : data) {
            if (value != target) {
                result[index] = value;
                index++;
            }
        }

        return result;
    }

    // ==================== 工具方法：打印数组 ====================
    private static void printArray(int[] data) {
        System.out.print("[");
        if (data != null) {
            for (int i = 0; i < data.length; i++) {
                System.out.print(data[i]);
                if (i < data.length - 1) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println("]");
    }
}
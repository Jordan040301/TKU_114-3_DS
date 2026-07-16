public class Q09_MatrixReport {
    public static void main(String[] args) {
        int[][] data = {
            {5, 8, 2},
            {9, 4, 7},
            {3, 6, 10}
        };

        System.out.println("第 1 列總和：" + rowSum(data, 1));
        System.out.println("第 2 欄總和：" + columnSum(data, 2));
        System.out.println("大於等於 7 的筆數：" + countAtLeast(data, 7));
        System.out.println("總和最大的列索引：" + findRowWithLargestTotal(data));
    }

    // ==================== 方法1：计算指定列的总和 ====================
    public static int rowSum(int[][] data, int row) {
        // 检查二维数组是否为 null 或空
        if (data == null || data.length == 0) {
            return -1;
        }

        // 检查列索引是否合法
        if (row < 0 || row >= data.length) {
            return -1;
        }

        // 计算该列的总和
        int sum = 0;
        for (int value : data[row]) {
            sum += value;
        }
        return sum;
    }

    // ==================== 方法2：计算指定栏的总和 ====================
    public static int columnSum(int[][] data, int column) {
        // 检查二维数组是否为 null 或空
        if (data == null || data.length == 0) {
            return -1;
        }

        // 检查栏索引是否合法（需要检查每一行是否有该栏位）
        if (column < 0) {
            return -1;
        }

        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            // 如果某一行长度不够，栏索引不合法
            if (data[i] == null || column >= data[i].length) {
                return -1;
            }
            sum += data[i][column];
        }
        return sum;
    }

    // ==================== 方法3：计算大于等于门坎的笔数 ====================
    public static int countAtLeast(int[][] data, int target) {
        if (data == null || data.length == 0) {
            return 0;
        }

        int count = 0;
        for (int[] row : data) {
            if (row != null) {
                for (int value : row) {
                    if (value >= target) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // ==================== 方法4：找出总和最大的列索引 ====================
    public static int findRowWithLargestTotal(int[][] data) {
        // 检查二维数组是否为 null 或空
        if (data == null || data.length == 0) {
            return -1;
        }

        int maxSum = Integer.MIN_VALUE;
        int maxIndex = -1;

        for (int i = 0; i < data.length; i++) {
            // 计算第 i 列的总和
            int sum = 0;
            if (data[i] != null) {
                for (int value : data[i]) {
                    sum += value;
                }
            }

            // 如果总和大于目前最大，更新；如果相等，保留索引较小的（已经自然满足）
            if (sum > maxSum) {
                maxSum = sum;
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}
public class Q04_LoopRepair {
    public static void main(String[] args) {
        System.out.println(sumOddRange(3, 7));
        System.out.println(sumOddRange(7, 3));
        System.out.println(sumOddRange(2, 2));
        System.out.println(sumOddRange(-3, 3));
    }

    public static int sumOddRange(int start, int end) {
        // 1. 处理反向区间：确保 start <= end
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        int sum = 0;

        // 2. 遍历区间 [start, end]，包含两端
        for (int i = start; i <= end; i++) {
            // 3. 判断是否为奇数（负数的奇数判断同样有效）
            if (i % 2 != 0) {
                sum += i;
            }
        }

        return sum;
    }
}
import java.util.Arrays;

public class Q07_SortingRepair {
    public static void main(String[] args) {
        int[] source = {31, 12, 45, 12, 8, 27};

        System.out.println("Selection 降幂: " + Arrays.toString(selectionSortDescending(source)));
        System.out.println("Insertion 升幂: " + Arrays.toString(insertionSortAscending(source)));
        System.out.println("原始資料: " + Arrays.toString(source));
    }

    public static int[] selectionSortDescending(int[] source) {
        int[] result = source.clone();

        for (int start = 0; start < result.length - 1; start++) {
            int selectedIndex = start;

            for (int index = start + 1; index < result.length; index++) {
                // 降幂：找最大的（改成 >）
                if (result[index] > result[selectedIndex]) {
                    selectedIndex = index;
                }
            }

            int temp = result[start];
            result[start] = result[selectedIndex];
            result[selectedIndex] = temp;
        }

        return result;
    }

    public static int[] insertionSortAscending(int[] source) {
        int[] result = source.clone();

        for (int index = 1; index < result.length; index++) {
            int key = result[index];
            int position = index - 1;

            // 升幂：改成 >（向右移，直到找到正确位置）
            while (position >= 0 && result[position] > key) {
                result[position + 1] = result[position];  // 修正：用 position+1
                position--;
            }
            result[position + 1] = key;  // 修正：用 position+1
        }

        return result;
    }
}
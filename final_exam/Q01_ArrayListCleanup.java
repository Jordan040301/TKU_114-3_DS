import java.util.ArrayList;
import java.util.Arrays;

public class Q01_ArrayListCleanup {
    public static void main(String[] args) {
        ArrayList<Integer> scores = new ArrayList<>();
        // 修正：将 Arrays.asList 的结果添加到 ArrayList
        scores.addAll(Arrays.asList(72, 35, 28, 80, 41, 39, 90));
        
        int removed = removeBelow(scores, 40);
        System.out.println("移除筆數：" + removed);
        System.out.println("保留資料：" + scores);
    }

    public static int removeBelow(ArrayList<Integer> scores, int minimum) {
        int removed = 0;
        
        // 反向遍历，避免删除导致索引错位
        for (int index = scores.size() - 1; index >= 0; index--) {
            if (scores.get(index) < minimum) {
                scores.remove(index);
                removed++;
            }
        }
        
        return removed;
    }
}
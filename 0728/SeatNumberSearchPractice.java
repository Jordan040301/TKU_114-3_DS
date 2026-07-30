import java.util.Scanner;

public class SeatNumberSearchPractice {
    public static void main(String[] args) {
        // 使用至少 12 筆已排序的座位編號
        int[] seatNumbers = {
            101, 105, 108, 112, 115, 120, 
            125, 130, 135, 140, 145, 150, 
            155, 160  // 14 筆資料，確保超過 12 筆
        };
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("請輸入要搜尋的座位編號: ");
        int targetSeat = scanner.nextInt();
        
        // 執行二分搜索
        int result = binarySearch(seatNumbers, targetSeat);
        
        // 顯示結果
        if (result != -1) {
            System.out.println("找到座位編號 " + targetSeat + "，位於索引 " + result);
        } else {
            System.out.println("找不到座位編號 " + targetSeat);
        }
        
        scanner.close();
    }
    
    /**
     * 二分搜索方法
     * @param array 已排序的陣列
     * @param target 要找的目標值
     * @return 找到時回傳索引，找不到時回傳 -1
     */
    public static int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        int step = 0;
        
        System.out.println("開始二分搜索...");
        System.out.println("初始範圍: low=" + low + ", high=" + high);
        System.out.println("------------------------");
        
        while (low <= high) {
            step++;
            int mid = low + (high - low) / 2;  // 避免溢位
            int midValue = array[mid];
            
            System.out.println("第 " + step + " 次搜索:");
            System.out.println("  low=" + low + ", mid=" + mid + ", high=" + high);
            System.out.println("  array[mid]=" + midValue + ", target=" + target);
            
            if (midValue == target) {
                System.out.println("✓ 找到目標值！");
                return mid;
            } else if (midValue < target) {
                System.out.println("  " + midValue + " < " + target + "，向右搜尋");
                low = mid + 1;
            } else {
                System.out.println("  " + midValue + " > " + target + "，向左搜尋");
                high = mid - 1;
            }
            System.out.println("  更新範圍: low=" + low + ", high=" + high);
            System.out.println("------------------------");
        }
        
        System.out.println("✗ 搜尋範圍縮小為空，找不到目標值");
        return -1;
    }
}
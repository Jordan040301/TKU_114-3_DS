import java.util.Scanner;

public class ProductIdSearchPractice {
    public static void main(String[] args) {
        // 至少建立 8 筆未排序的商品編號
        String[] productIds = {
            "P105", "P203", "P118", "P301", 
            "P092", "P156", "P247", "P189", 
            "P324", "P076"  // 10 筆資料，確保超過 8 筆
        };
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("請輸入要搜尋的商品編號: ");
        String targetId = scanner.nextLine().trim();
        
        // 執行循序搜索
        int result = sequentialSearch(productIds, targetId);
        
        // 顯示結果
        if (result != -1) {
            System.out.println("找到商品編號 \"" + targetId + "\"，位於索引 " + result);
        } else {
            System.out.println("找不到商品編號 \"" + targetId + "\"");
        }
        
        scanner.close();
    }
    
    /**
     * 循序搜索方法
     * @param array 要搜索的陣列
     * @param target 要找的目標值
     * @return 找到時回傳索引，找不到時回傳 -1
     */
    public static int sequentialSearch(String[] array, String target) {
        int comparisonCount = 0;
        
        for (int i = 0; i < array.length; i++) {
            comparisonCount++;
            System.out.println("比較第 " + comparisonCount + " 次: array[" + i + "] = " + array[i]);
            
            if (array[i].equals(target)) {
                System.out.println("總共比較了 " + comparisonCount + " 次");
                return i;
            }
        }
        
        System.out.println("總共比較了 " + comparisonCount + " 次");
        return -1;  // 找不到時回傳 -1，不會用來存取陣列
    }
}
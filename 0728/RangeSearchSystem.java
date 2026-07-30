import java.util.Arrays;

public class RangeSearchSystem {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         第一筆與最後一筆相同資料位置搜尋系統              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        // 建立包含重複資料的已排序清單
        int[] sortedData = {
            10, 20, 20, 20, 30, 30, 40, 40, 40, 40,
            50, 50, 60, 70, 70, 80, 80, 80, 90, 100
        };
        
        System.out.println("\n📋 已排序資料陣列:");
        printArray(sortedData);
        System.out.println("陣列長度: " + sortedData.length + " 筆\n");
        
        // 測試各種目標值
        int[] testTargets = {20, 40, 70, 80, 100, 30, 15, 45, 60, 90};
        
        System.out.println("=".repeat(80));
        System.out.println("🔍 搜尋結果分析");
        System.out.println("=".repeat(80));
        
        for (int target : testTargets) {
            System.out.println("\n▶ 搜尋目標: " + target);
            int[] range = searchRange(sortedData, target);
            
            if (range[0] == -1) {
                System.out.println("  結果: [-1, -1]");
                System.out.println("  ❌ 找不到目標值 " + target);
            } else {
                System.out.println("  結果: [" + range[0] + ", " + range[1] + "]");
                System.out.println("  ✅ 找到目標值 " + target);
                System.out.println("  首次出現索引: " + range[0]);
                System.out.println("  最後出現索引: " + range[1]);
                System.out.println("  出現次數: " + (range[1] - range[0] + 1));
                System.out.println("  範圍: " + target + " 連續出現 " + (range[1] - range[0] + 1) + " 次");
            }
            System.out.println("  " + "-".repeat(50));
        }
        
        // 顯示詳細搜尋過程 (針對特定目標)
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📝 詳細搜尋過程展示");
        System.out.println("=".repeat(80));
        
        showDetailedSearch(sortedData, 40);
        showDetailedSearch(sortedData, 20);
        showDetailedSearch(sortedData, 15);
    }
    
    /**
     * 尋找目標值的範圍 (第一次和最後一次出現位置)
     * @param array 已排序的陣列
     * @param target 要找的目標值
     * @return [firstIndex, lastIndex]，找不到時回傳 [-1, -1]
     */
    public static int[] searchRange(int[] array, int target) {
        int[] result = new int[2];
        
        // 找出第一次出現的位置
        int firstIndex = findFirst(array, target);
        
        // 如果找不到，直接回傳 [-1, -1]
        if (firstIndex == -1) {
            return new int[]{-1, -1};
        }
        
        // 找出最後一次出現的位置
        int lastIndex = findLast(array, target);
        
        result[0] = firstIndex;
        result[1] = lastIndex;
        
        return result;
    }
    
    /**
     * 修改後的二分搜尋：尋找第一次出現的位置
     */
    public static int findFirst(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        int result = -1;
        int step = 0;
        
        System.out.println("\n  🔍 尋找第一次出現位置 (目標: " + target + ")");
        
        while (low <= high) {
            step++;
            int mid = low + (high - low) / 2;
            
            System.out.println("    第 " + step + " 步: low=" + low + 
                             ", mid=" + mid + ", high=" + high);
            System.out.println("      array[" + mid + "] = " + array[mid]);
            
            if (array[mid] == target) {
                result = mid;
                System.out.println("      ✓ 找到目標，記錄位置: " + mid + "，繼續向左搜尋");
                high = mid - 1;  // 繼續向左搜尋，尋找更早的出現
            } else if (array[mid] < target) {
                System.out.println("      " + array[mid] + " < " + target + "，向右搜尋");
                low = mid + 1;
            } else {
                System.out.println("      " + array[mid] + " > " + target + "，向左搜尋");
                high = mid - 1;
            }
        }
        
        if (result != -1) {
            System.out.println("    ✅ 第一次出現位置: " + result);
        } else {
            System.out.println("    ❌ 找不到目標值");
        }
        
        return result;
    }
    
    /**
     * 修改後的二分搜尋：尋找最後一次出現的位置
     */
    public static int findLast(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        int result = -1;
        int step = 0;
        
        System.out.println("\n  🔍 尋找最後一次出現位置 (目標: " + target + ")");
        
        while (low <= high) {
            step++;
            int mid = low + (high - low) / 2;
            
            System.out.println("    第 " + step + " 步: low=" + low + 
                             ", mid=" + mid + ", high=" + high);
            System.out.println("      array[" + mid + "] = " + array[mid]);
            
            if (array[mid] == target) {
                result = mid;
                System.out.println("      ✓ 找到目標，記錄位置: " + mid + "，繼續向右搜尋");
                low = mid + 1;  // 繼續向右搜尋，尋找更晚的出現
            } else if (array[mid] < target) {
                System.out.println("      " + array[mid] + " < " + target + "，向右搜尋");
                low = mid + 1;
            } else {
                System.out.println("      " + array[mid] + " > " + target + "，向左搜尋");
                high = mid - 1;
            }
        }
        
        if (result != -1) {
            System.out.println("    ✅ 最後一次出現位置: " + result);
        } else {
            System.out.println("    ❌ 找不到目標值");
        }
        
        return result;
    }
    
    /**
     * 顯示詳細搜尋過程 (整合版)
     */
    public static void showDetailedSearch(int[] array, int target) {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("📌 搜尋目標: " + target);
        System.out.println("─".repeat(60));
        
        int[] range = searchRange(array, target);
        
        if (range[0] == -1) {
            System.out.println("❌ 結果: 找不到目標值 " + target);
            System.out.println("   回傳: [-1, -1]");
        } else {
            System.out.println("✅ 搜尋結果:");
            System.out.println("   第一次出現: 索引 " + range[0]);
            System.out.println("   最後一次出現: 索引 " + range[1]);
            System.out.println("   出現次數: " + (range[1] - range[0] + 1));
            System.out.println("   回傳範圍: [" + range[0] + ", " + range[1] + "]");
        }
        System.out.println("─".repeat(60));
    }
    
    /**
     * 印出陣列內容
     */
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%4d", array[i]);
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
import java.util.ArrayList;

/**
 * RepairAlgorithms.java
 * 維修工作排程的演算法工具類別
 * 包含：歸併排序（依優先等級降冪，相同等級保持順序）、順序搜尋
 */
public class RepairAlgorithms {
    
    // ========== 歸併排序：依優先等級降冪（穩定排序） ==========
    
    /**
     * 依優先等級降冪排序（使用歸併排序，穩定排序）
     * 相同優先等級保持原有順序
     * @param tasks 工作陣列
     * @param left 左邊界
     * @param right 右邊界
     */
    public static void mergeSortByPriorityDesc(RepairTask[] tasks, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSortByPriorityDesc(tasks, left, mid);
            mergeSortByPriorityDesc(tasks, mid + 1, right);
            mergeByPriorityDesc(tasks, left, mid, right);
        }
    }
    
    /**
     * 合併兩個已排序的子陣列（依優先等級降冪，穩定排序）
     */
    private static void mergeByPriorityDesc(RepairTask[] tasks, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        RepairTask[] leftArray = new RepairTask[n1];
        RepairTask[] rightArray = new RepairTask[n2];
        
        for (int i = 0; i < n1; i++) {
            leftArray[i] = tasks[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = tasks[mid + 1 + j];
        }
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            // 降序：優先等級大的在前
            // 如果優先等級相同，保持原有順序（左邊先取）
            if (leftArray[i].getPriority() >= rightArray[j].getPriority()) {
                tasks[k] = leftArray[i];
                i++;
            } else {
                tasks[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            tasks[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            tasks[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    // ========== 順序搜尋 ==========
    
    /**
     * 順序搜尋 - 依設備名稱搜尋所有符合的工作
     * @param tasks 工作陣列（可以是未排序的）
     * @param deviceName 要搜尋的設備名稱
     * @return 所有符合的工作陣列
     */
    public static RepairTask[] sequentialSearchByDeviceName(RepairTask[] tasks, String deviceName) {
        if (tasks == null || tasks.length == 0) {
            return new RepairTask[0];
        }
        
        // 先計算符合條件的數量
        int count = 0;
        for (RepairTask task : tasks) {
            if (task.getDeviceName().equals(deviceName)) {
                count++;
            }
        }
        
        if (count == 0) {
            return new RepairTask[0];
        }
        
        // 建立結果陣列
        RepairTask[] results = new RepairTask[count];
        int index = 0;
        for (RepairTask task : tasks) {
            if (task.getDeviceName().equals(deviceName)) {
                results[index++] = task;
            }
        }
        
        return results;
    }
    
    /**
     * 順序搜尋 - 依工作描述搜尋所有符合的工作（部分匹配）
     * @param tasks 工作陣列
     * @param keyword 要搜尋的關鍵字
     * @return 所有符合的工作陣列
     */
    public static RepairTask[] sequentialSearchByDescription(RepairTask[] tasks, String keyword) {
        if (tasks == null || tasks.length == 0) {
            return new RepairTask[0];
        }
        
        // 先計算符合條件的數量
        int count = 0;
        for (RepairTask task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                count++;
            }
        }
        
        if (count == 0) {
            return new RepairTask[0];
        }
        
        // 建立結果陣列
        RepairTask[] results = new RepairTask[count];
        int index = 0;
        for (RepairTask task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results[index++] = task;
            }
        }
        
        return results;
    }
    
    /**
     * 輔助方法：複製工作陣列
     */
    public static RepairTask[] copyTasks(RepairTask[] tasks) {
        if (tasks == null) return null;
        RepairTask[] copy = new RepairTask[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            copy[i] = tasks[i];
        }
        return copy;
    }
}
/**
 * OrderAlgorithms.java
 * 訂單管理的演算法工具類別
 * 包含：歸併排序（依金額降冪）、二分搜尋（依顧客姓名）
 */
public class OrderAlgorithms2 {
    
    /**
     * 歸併排序 - 依金額降冪排列
     * @param orders 訂單陣列
     * @param left 左邊界
     * @param right 右邊界
     */
    public static void mergeSortByAmountDesc(Order[] orders, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            // 遞迴排序左右兩半
            mergeSortByAmountDesc(orders, left, mid);
            mergeSortByAmountDesc(orders, mid + 1, right);
            
            // 合併已排序的兩半（依金額降冪）
            mergeByAmountDesc(orders, left, mid, right);
        }
    }
    
    /**
     * 合併兩個已排序的子陣列（依金額降冪）
     */
    private static void mergeByAmountDesc(Order[] orders, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        // 建立臨時陣列
        Order[] leftArray = new Order[n1];
        Order[] rightArray = new Order[n2];
        
        // 複製資料到臨時陣列
        for (int i = 0; i < n1; i++) {
            leftArray[i] = orders[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = orders[mid + 1 + j];
        }
        
        // 合併（降冪：從大到小）
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            // 降冪：大的在前
            if (leftArray[i].getAmount() >= rightArray[j].getAmount()) {
                orders[k] = leftArray[i];
                i++;
            } else {
                orders[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        // 複製剩餘元素
        while (i < n1) {
            orders[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            orders[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    /**
     * 二分搜尋 - 依顧客姓名搜尋（假設陣列已依姓名排序）
     * @param orders 已排序的訂單陣列（依姓名排序）
     * @param customerName 要搜尋的顧客姓名
     * @return 所有符合的訂單陣列，如果找不到則返回空陣列
     */
    public static Order2[] binarySearchByCustomerName(Order2[] orders, String customerName) {
        if (orders == null || orders.length == 0) {
            return new Order2[0];
        }
        
        // 先找到第一個匹配的位置
        int firstIndex = findFirstMatch(orders, customerName);
        if (firstIndex == -1) {
            return new Order2[0];
        }
        
        // 找到最後一個匹配的位置
        int lastIndex = findLastMatch(orders, customerName);
        
        // 建立結果陣列
        int count = lastIndex - firstIndex + 1;
        Order2[] result = new Order2[count];
        for (int i = 0; i < count; i++) {
            result[i] = orders[firstIndex + i];
        }
        
        return result;
    }
    
    /**
     * 尋找第一個匹配的索引（使用二分搜尋）
     */
    private static int findFirstMatch(Order2[] orders, String customerName) {
        int left = 0;
        int right = orders.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = orders[mid].getCustomerName().compareTo(customerName);
            
            if (compare == 0) {
                result = mid;
                right = mid - 1; // 繼續往左找
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * 尋找最後一個匹配的索引（使用二分搜尋）
     */
    private static int findLastMatch(Order2[] orders, String customerName) {
        int left = 0;
        int right = orders.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = orders[mid].getCustomerName().compareTo(customerName);
            
            if (compare == 0) {
                result = mid;
                left = mid + 1; // 繼續往右找
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * 輔助方法：複製訂單陣列
     */
    public static Order[] copyOrders(Order[] orders) {
        if (orders == null) return null;
        Order[] copy = new Order[orders.length];
        for (int i = 0; i < orders.length; i++) {
            copy[i] = orders[i];
        }
        return copy;
    }
}
import java.util.ArrayList;

/**
 * RegistrationAlgorithms.java
 * 報名系統的演算法工具類別
 * 包含：歸併排序（依報名編號排序）、二分搜尋、順序搜尋
 */
public class RegistrationAlgorithms {
    
    // ========== 歸併排序：依報名編號排序 ==========
    
    /**
     * 依報名編號排序（使用歸併排序）
     * @param registrations 報名陣列
     * @param left 左邊界
     * @param right 右邊界
     */
    public static void mergeSortById(Registration[] registrations, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSortById(registrations, left, mid);
            mergeSortById(registrations, mid + 1, right);
            mergeById(registrations, left, mid, right);
        }
    }
    
    /**
     * 合併兩個已排序的子陣列（依編號排序）
     */
    private static void mergeById(Registration[] registrations, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        Registration[] leftArray = new Registration[n1];
        Registration[] rightArray = new Registration[n2];
        
        for (int i = 0; i < n1; i++) {
            leftArray[i] = registrations[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = registrations[mid + 1 + j];
        }
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].getRegistrationId().compareTo(rightArray[j].getRegistrationId()) <= 0) {
                registrations[k] = leftArray[i];
                i++;
            } else {
                registrations[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            registrations[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            registrations[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    // ========== 二分搜尋：依報名編號查詢 ==========
    
    /**
     * 二分搜尋 - 依報名編號查詢
     * @param registrations 已排序的報名陣列（依編號升序）
     * @param registrationId 要查詢的報名編號
     * @return 找到的報名，如果找不到則返回 null
     */
    public static Registration binarySearchById(Registration[] registrations, String registrationId) {
        if (registrations == null || registrations.length == 0) {
            return null;
        }
        
        int left = 0;
        int right = registrations.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = registrations[mid].getRegistrationId().compareTo(registrationId);
            
            if (compare == 0) {
                return registrations[mid];
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return null;
    }
    
    // ========== 順序搜尋：依姓名查詢所有報名 ==========
    
    /**
     * 順序搜尋 - 依參加者姓名搜尋所有報名
     * @param registrations 報名陣列（可以是未排序的）
     * @param participantName 要搜尋的參加者姓名
     * @return 所有符合的報名陣列
     */
    public static Registration[] sequentialSearchByName(Registration[] registrations, String participantName) {
        if (registrations == null || registrations.length == 0) {
            return new Registration[0];
        }
        
        // 先計算符合條件的數量
        int count = 0;
        for (Registration reg : registrations) {
            if (reg.getParticipantName().equals(participantName)) {
                count++;
            }
        }
        
        if (count == 0) {
            return new Registration[0];
        }
        
        // 建立結果陣列
        Registration[] results = new Registration[count];
        int index = 0;
        for (Registration reg : registrations) {
            if (reg.getParticipantName().equals(participantName)) {
                results[index++] = reg;
            }
        }
        
        return results;
    }
    
    /**
     * 順序搜尋 - 依活動名稱搜尋所有報名
     * @param registrations 報名陣列
     * @param eventName 要搜尋的活動名稱
     * @return 所有符合的報名陣列
     */
    public static Registration[] sequentialSearchByEvent(Registration[] registrations, String eventName) {
        if (registrations == null || registrations.length == 0) {
            return new Registration[0];
        }
        
        // 先計算符合條件的數量
        int count = 0;
        for (Registration reg : registrations) {
            if (reg.getEventName().equals(eventName)) {
                count++;
            }
        }
        
        if (count == 0) {
            return new Registration[0];
        }
        
        // 建立結果陣列
        Registration[] results = new Registration[count];
        int index = 0;
        for (Registration reg : registrations) {
            if (reg.getEventName().equals(eventName)) {
                results[index++] = reg;
            }
        }
        
        return results;
    }
    
    /**
     * 輔助方法：複製報名陣列
     */
    public static Registration[] copyRegistrations(Registration[] registrations) {
        if (registrations == null) return null;
        Registration[] copy = new Registration[registrations.length];
        for (int i = 0; i < registrations.length; i++) {
            copy[i] = registrations[i];
        }
        return copy;
    }
}
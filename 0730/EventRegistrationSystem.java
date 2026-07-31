import java.util.*;

/**
 * EventRegistrationSystem.java
 * 活動報名與候補系統
 * 
 * 功能要求：
 * 1. ArrayList 保存全部報名資料
 * 2. Queue 儲存候補順序
 * 3. Stack 保存最近取消記錄並支援複數
 * 4. Merge Sort 依編號排序
 * 5. 二分查找依編號查詢，順序查找依姓名查詢
 * 6. 處理額滿、重複編號、取消不存在資料及空候補行列
 */
public class EventRegistrationSystem {
    
    private ArrayList<Registration> allRegistrations;  // 所有報名資料
    private Queue<Registration> waitingQueue;          // 候補佇列（FIFO）
    private Stack<Registration> cancelledStack;        // 取消記錄堆疊（LIFO）
    private int maxCapacity;                            // 活動最大容量
    
    /**
     * 建構子：初始化系統
     * @param maxCapacity 活動最大容量
     */
    public EventRegistrationSystem(int maxCapacity) {
        this.allRegistrations = new ArrayList<>();
        this.waitingQueue = new LinkedList<>();
        this.cancelledStack = new Stack<>();
        this.maxCapacity = maxCapacity;
    }
    
    /**
     * 新增報名（防止重複編號，處理額滿）
     * @param registration 要新增的報名
     * @return true 如果新增成功，false 如果失敗
     */
    public boolean addRegistration(Registration registration) {
        // 檢查是否已存在相同編號的報名
        for (Registration existing : allRegistrations) {
            if (existing.getRegistrationId().equals(registration.getRegistrationId())) {
                System.out.println("❌ 錯誤：報名編號 " + registration.getRegistrationId() + " 已存在！");
                return false;
            }
        }
        
        // 檢查是否已額滿
        int confirmedCount = getConfirmedCount();
        if (confirmedCount >= maxCapacity) {
            // 額滿：加入候補
            registration.setStatus("WAITING");
            waitingQueue.offer(registration);
            allRegistrations.add(registration);
            System.out.println("⚠️ 活動已額滿！報名 " + registration.getRegistrationId() + 
                             " 已加入候補佇列 (候補位置: " + waitingQueue.size() + ")");
            System.out.println("   " + registration);
            return true;
        }
        
        // 正常報名
        allRegistrations.add(registration);
        System.out.println("✅ 成功新增報名：" + registration);
        System.out.println("   目前報名人數: " + (confirmedCount + 1) + "/" + maxCapacity);
        return true;
    }
    
    /**
     * 取消報名（處理取消不存在資料）
     * @param registrationId 要取消的報名編號
     * @return true 如果取消成功，false 如果失敗
     */
    public boolean cancelRegistration(String registrationId) {
        // 尋找要取消的報名
        Registration target = null;
        int index = -1;
        for (int i = 0; i < allRegistrations.size(); i++) {
            if (allRegistrations.get(i).getRegistrationId().equals(registrationId)) {
                target = allRegistrations.get(i);
                index = i;
                break;
            }
        }
        
        if (target == null) {
            System.out.println("❌ 錯誤：找不到報名編號 " + registrationId + "，取消失敗！");
            return false;
        }
        
        // 檢查是否已取消
        if (target.getStatus().equals("CANCELLED")) {
            System.out.println("⚠️ 報名 " + registrationId + " 已經取消過了！");
            return false;
        }
        
        // 從系統中移除
        allRegistrations.remove(index);
        
        // 更新狀態並推入取消堆疊
        target.setStatus("CANCELLED");
        cancelledStack.push(target);
        
        System.out.println("🗑️ 已取消報名：" + target);
        
        // 如果有候補，將候補轉為正式
        if (!waitingQueue.isEmpty()) {
            Registration nextWait = waitingQueue.poll();
            nextWait.setStatus("CONFIRMED");
            // 將候補加入正式名單（重新加入 ArrayList）
            allRegistrations.add(nextWait);
            System.out.println("🔄 候補轉正：" + nextWait);
            System.out.println("   目前報名人數: " + getConfirmedCount() + "/" + maxCapacity);
        } else {
            System.out.println("   目前報名人數: " + getConfirmedCount() + "/" + maxCapacity);
        }
        
        return true;
    }
    
    /**
     * 取得已確認報名人數
     */
    private int getConfirmedCount() {
        int count = 0;
        for (Registration reg : allRegistrations) {
            if (reg.getStatus().equals("CONFIRMED")) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * 依編號排序（使用歸併排序）
     * @return 排序後的報名陣列
     */
    public Registration[] sortById() {
        if (allRegistrations.isEmpty()) {
            System.out.println("目前沒有報名資料");
            return new Registration[0];
        }
        
        Registration[] sorted = allRegistrations.toArray(new Registration[0]);
        RegistrationAlgorithms.mergeSortById(sorted, 0, sorted.length - 1);
        
        System.out.println("📋 依報名編號排序完成");
        return sorted;
    }
    
    /**
     * 二分搜尋 - 依編號查詢報名
     * @param registrationId 要查詢的報名編號
     * @return 找到的報名，如果找不到則返回 null
     */
    public Registration searchById(String registrationId) {
        if (allRegistrations.isEmpty()) {
            System.out.println("目前沒有報名資料");
            return null;
        }
        
        Registration[] sorted = sortById();
        Registration result = RegistrationAlgorithms.binarySearchById(sorted, registrationId);
        
        if (result == null) {
            System.out.println("❌ 找不到報名編號為 \"" + registrationId + "\" 的報名");
        } else {
            System.out.println("✅ 找到報名：" + result);
        }
        
        return result;
    }
    
    /**
     * 順序搜尋 - 依參加者姓名查詢所有報名
     * @param participantName 參加者姓名
     * @return 所有符合的報名陣列
     */
    public Registration[] searchByName(String participantName) {
        if (allRegistrations.isEmpty()) {
            System.out.println("目前沒有報名資料");
            return new Registration[0];
        }
        
        Registration[] all = allRegistrations.toArray(new Registration[0]);
        Registration[] results = RegistrationAlgorithms.sequentialSearchByName(all, participantName);
        
        if (results.length == 0) {
            System.out.println("❌ 找不到參加者姓名為 \"" + participantName + "\" 的報名");
        } else {
            System.out.println("✅ 找到 " + results.length + " 筆參加者姓名為 \"" + participantName + "\" 的報名");
        }
        
        return results;
    }
    
    /**
     * 順序搜尋 - 依活動名稱查詢所有報名
     * @param eventName 活動名稱
     * @return 所有符合的報名陣列
     */
    public Registration[] searchByEvent(String eventName) {
        if (allRegistrations.isEmpty()) {
            System.out.println("目前沒有報名資料");
            return new Registration[0];
        }
        
        Registration[] all = allRegistrations.toArray(new Registration[0]);
        Registration[] results = RegistrationAlgorithms.sequentialSearchByEvent(all, eventName);
        
        if (results.length == 0) {
            System.out.println("❌ 找不到活動名稱為 \"" + eventName + "\" 的報名");
        } else {
            System.out.println("✅ 找到 " + results.length + " 筆活動名稱為 \"" + eventName + "\" 的報名");
        }
        
        return results;
    }
    
    /**
     * 查看最近取消記錄（從堆疊頂端查看，不取出）
     * @return 最近取消的報名，如果沒有則返回 null
     */
    public Registration viewLastCancelled() {
        if (cancelledStack.isEmpty()) {
            System.out.println("📭 取消記錄堆疊為空，沒有取消記錄");
            return null;
        }
        
        Registration lastCancelled = cancelledStack.peek();
        System.out.println("📋 最近取消的報名：" + lastCancelled);
        return lastCancelled;
    }
    
    /**
     * 查看所有取消記錄
     * @return 所有取消記錄陣列
     */
    public Registration[] viewAllCancelled() {
        if (cancelledStack.isEmpty()) {
            System.out.println("📭 取消記錄堆疊為空");
            return new Registration[0];
        }
        
        Registration[] result = new Registration[cancelledStack.size()];
        for (int i = cancelledStack.size() - 1; i >= 0; i--) {
            result[cancelledStack.size() - 1 - i] = cancelledStack.get(i);
        }
        
        System.out.println("📋 取消記錄（從最近到最早）：");
        for (Registration reg : result) {
            System.out.println("  " + reg);
        }
        return result;
    }
    
    /**
     * 查看候補佇列
     */
    public void viewWaitingQueue() {
        System.out.println("\n=== 候補佇列 ===");
        if (waitingQueue.isEmpty()) {
            System.out.println("📭 候補佇列為空");
        } else {
            System.out.println("候補人數：" + waitingQueue.size());
            int position = 1;
            for (Registration reg : waitingQueue) {
                System.out.println("  候補 #" + position + ": " + reg);
                position++;
            }
        }
        System.out.println();
    }
    
    /**
     * 顯示系統狀態
     */
    public void displayStatus() {
        System.out.println("\n=== 系統狀態 ===");
        System.out.println("活動容量：" + maxCapacity);
        System.out.println("已確認報名人數：" + getConfirmedCount() + "/" + maxCapacity);
        System.out.println("候補人數：" + waitingQueue.size());
        System.out.println("取消記錄數：" + cancelledStack.size());
        System.out.println("總報名數：" + allRegistrations.size());
        System.out.println();
    }
    
    /**
     * 顯示所有報名
     */
    public void displayAllRegistrations() {
        System.out.println("\n=== 所有報名資料 ===");
        if (allRegistrations.isEmpty()) {
            System.out.println("目前沒有報名資料");
        } else {
            System.out.println("總筆數：" + allRegistrations.size());
            Registration[] sorted = allRegistrations.toArray(new Registration[0]);
            RegistrationAlgorithms.mergeSortById(sorted, 0, sorted.length - 1);
            for (Registration reg : sorted) {
                System.out.println("  " + reg);
            }
        }
        System.out.println();
    }
    
    // ========== 測試主程式 ==========
    public static void main(String[] args) {
        // 建立系統（容量設為5）
        EventRegistrationSystem system = new EventRegistrationSystem(5);
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              活動報名與候補系統測試                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        // 1. 測試新增報名（含額滿測試）
        System.out.println("--- 測試1：新增報名（容量5人） ---");
        system.addRegistration(new Registration("R001", "王小明", "程式設計工作坊", "0912-345-678"));
        system.addRegistration(new Registration("R002", "陳美玲", "程式設計工作坊", "0923-456-789"));
        system.addRegistration(new Registration("R003", "張大偉", "程式設計工作坊", "0934-567-890"));
        system.addRegistration(new Registration("R004", "李素芬", "程式設計工作坊", "0945-678-901"));
        system.addRegistration(new Registration("R005", "林志強", "程式設計工作坊", "0956-789-012"));
        // 第六人（應進入候補）
        system.addRegistration(new Registration("R006", "趙雅婷", "程式設計工作坊", "0967-890-123"));
        // 第七人（應進入候補）
        system.addRegistration(new Registration("R007", "黃俊傑", "程式設計工作坊", "0978-901-234"));
        
        // 重複編號測試
        System.out.println("\n--- 重複編號測試 ---");
        system.addRegistration(new Registration("R001", "重複測試", "程式設計工作坊", "0999-999-999"));
        
        system.displayAllRegistrations();
        system.displayStatus();
        system.viewWaitingQueue();
        
        // 2. 測試依編號排序與二分搜尋
        System.out.println("--- 測試2：依編號排序與二分搜尋 ---");
        Registration[] sorted = system.sortById();
        System.out.println("排序後的報名：");
        for (Registration reg : sorted) {
            System.out.println("  " + reg);
        }
        System.out.println();
        
        // 搜尋存在的編號
        system.searchById("R003");
        System.out.println();
        
        // 搜尋不存在的編號
        system.searchById("R999");
        System.out.println();
        
        // 3. 測試依姓名順序搜尋
        System.out.println("--- 測試3：依姓名順序搜尋 ---");
        Registration[] foundByName = system.searchByName("王小明");
        for (Registration reg : foundByName) {
            System.out.println("  找到：" + reg);
        }
        System.out.println();
        
        // 搜尋不存在的姓名
        system.searchByName("不存在的人");
        System.out.println();
        
        // 4. 測試取消報名（含候補自動遞補）
        System.out.println("--- 測試4：取消報名與候補遞補 ---");
        system.cancelRegistration("R002");  // 取消後，R006 應遞補
        
        System.out.println("\n--- 取消後狀態 ---");
        system.displayAllRegistrations();
        system.displayStatus();
        system.viewWaitingQueue();
        
        // 再次取消
        system.cancelRegistration("R005");
        System.out.println("\n--- 再次取消後狀態 ---");
        system.displayAllRegistrations();
        system.displayStatus();
        system.viewWaitingQueue();
        
        // 5. 測試取消不存在的報名
        System.out.println("--- 測試5：取消不存在的報名 ---");
        system.cancelRegistration("R999");
        System.out.println();
        
        // 6. 測試取消已取消的報名
        System.out.println("--- 測試6：取消已取消的報名 ---");
        system.cancelRegistration("R002");
        System.out.println();
        
        // 7. 測試查看取消記錄
        System.out.println("--- 測試7：查看取消記錄 ---");
        system.viewLastCancelled();
        System.out.println();
        system.viewAllCancelled();
        System.out.println();
        
        // 8. 測試空候補行列
        System.out.println("--- 測試8：處理更多取消（候補已空） ---");
        // 取消所有正式報名
        system.cancelRegistration("R001");
        system.cancelRegistration("R003");
        system.cancelRegistration("R004");
        // 此時候補已空
        system.displayStatus();
        System.out.println();
        
        // 9. 測試從空的取消堆疊查看
        System.out.println("--- 測試9：從空的取消堆疊查看 ---");
        system.viewLastCancelled();
        System.out.println();
        
        System.out.println("=== 測試完成 ===");
    }
}
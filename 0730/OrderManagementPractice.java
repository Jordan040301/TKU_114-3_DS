import java.util.*;

/**
 * OrderManagementPractice.java
 * 增強訂單管理系統
 * 
 * 功能：
 * 1. 加入「依金額降冪」的歸併排序
 * 2. 加入依顧客姓名搜尋全部訂單
 * 3. 加入顯示下一筆待處理訂單
 * 4. 防止加入重複的訂單編號
 * 5. 測試空行列、空堆疊、重複編號及找不到資料
 */
public class OrderManagementPractice {
    
    private List<Order2> mainOrders;        // 主資料（所有訂單）
    private Queue<Order2> waitingQueue;     // 等待行列（待處理訂單）
    private Stack<Order2> completedStack;   // 完成堆疊（已完成訂單）
    
    /**
     * 建構子：初始化系統
     */
    public OrderManagementPractice() {
        mainOrders = new ArrayList<>();
        waitingQueue = new LinkedList<>();
        completedStack = new Stack<>();
    }
    
    /**
     * 新增訂單（防止重複編號）
     * @param order 要新增的訂單
     * @return true 如果新增成功，false 如果訂單編號已存在
     */
    public boolean addOrder(Order2 order) {
        // 檢查是否已存在相同編號的訂單
        for (Order2 existing : mainOrders) {
            if (existing.getOrderId().equals(order.getOrderId())) {
                System.out.println("錯誤：訂單編號 " + order.getOrderId() + " 已存在！");
                return false;
            }
        }
        
        // 新增訂單到主資料
        mainOrders.add(order);
        // 同時加入等待行列（預設為待處理）
        waitingQueue.offer(order);
        System.out.println("成功新增訂單：" + order);
        return true;
    }
    
    /**
 * 依金額降冪排序（使用歸併排序）
 * @return 排序後的訂單陣列
 */
public Order2[] sortByAmountDesc() {
    if (mainOrders.isEmpty()) {
        System.out.println("目前沒有訂單資料");
        return new Order2[0];
    }
    
    // 複製主資料進行排序
    Order2[] sorted = mainOrders.toArray(new Order2[0]);
    OrderAlgorithms2.mergeSortByAmountDesc(sorted, 0, sorted.length - 1);
    
    System.out.println("依金額降冪排序完成");
    return sorted;
}
    
    /**
     * 依顧客姓名搜尋全部訂單
     * @param customerName 顧客姓名
     * @return 符合條件的訂單陣列
     */
    public Order2[] searchByCustomerName(String customerName) {
        if (mainOrders.isEmpty()) {
            System.out.println("目前沒有訂單資料");
            return new Order2[0];
        }
        
        // 先複製並依姓名排序
        Order2[] sortedByName = mainOrders.toArray(new Order2[0]);
        // 使用Java內建排序（或可使用自訂的歸併排序依姓名）
        Arrays.sort(sortedByName, (o1, o2) -> o1.getCustomerName().compareTo(o2.getCustomerName()));
        
        // 使用二分搜尋
        Order2[] results = OrderAlgorithms2.binarySearchByCustomerName(sortedByName, customerName);
        
        if (results.length == 0) {
            System.out.println("找不到顧客姓名為 \"" + customerName + "\" 的訂單");  // ✅ 修正：補上缺失的 +
        } else {
            System.out.println("找到 " + results.length + " 筆顧客姓名為 \"" + customerName + "\" 的訂單");  // ✅ 修正：補上缺失的 +
        }
        
        return results;
    }
    
    /**
     * 顯示下一筆待處理訂單（從等待行列取出）
     * @return 下一筆待處理訂單，如果沒有則返回 null
     */
    public Order2 getNextPendingOrder() {
        if (waitingQueue.isEmpty()) {
            System.out.println("等待行列為空，沒有待處理訂單");
            return null;
        }
        
        Order2 nextOrder = waitingQueue.poll();
        System.out.println("下一筆待處理訂單：" + nextOrder);
        return nextOrder;
    }
    
    /**
     * 完成訂單處理（從等待行列移到完成堆疊）
     * @return true 如果處理成功，false 如果等待行列為空
     */
    public boolean processNextOrder() {
        Order2 nextOrder = getNextPendingOrder();
        if (nextOrder == null) {
            return false;
        }
        
        // 更新狀態
        nextOrder.setStatus("COMPLETED");
        // 推入完成堆疊
        completedStack.push(nextOrder);
        System.out.println("訂單已完成處理：" + nextOrder);
        return true;
    }
    
    /**
     * 顯示訂單完成記錄（從完成堆疊取回）
     * @return 最近的完成訂單，如果沒有則返回 null
     */
    public Order2 getLastCompletedOrder() {
        if (completedStack.isEmpty()) {
            System.out.println("完成堆疊為空，沒有已完成訂單");
            return null;
        }
        
        Order2 lastCompleted = completedStack.peek();
        System.out.println("最近完成的訂單：" + lastCompleted);
        return lastCompleted;
    }
    
    /**
     * 顯示系統狀態
     */
    public void displayStatus() {
        System.out.println("\n=== 系統狀態 ===");
        System.out.println("主資料總筆數：" + mainOrders.size());
        System.out.println("等待行列筆數：" + waitingQueue.size());
        System.out.println("完成堆疊筆數：" + completedStack.size());
        
        if (!mainOrders.isEmpty()) {
            System.out.println("\n所有訂單：");
            for (Order2 order : mainOrders) {
                System.out.println("  " + order);
            }
        }
        
        if (!waitingQueue.isEmpty()) {
            System.out.println("\n待處理訂單（佇列前端）：");
            System.out.println("  " + waitingQueue.peek());
        }
        
        if (!completedStack.isEmpty()) {
            System.out.println("\n最近完成訂單（堆疊頂端）：");
            System.out.println("  " + completedStack.peek());
        }
        System.out.println();
    }
    
    /**
     * 顯示排序後的訂單（依金額降冪）
     */
    public void displaySortedByAmount() {
        Order2[] sorted = sortByAmountDesc();
        if (sorted.length > 0) {
            System.out.println("依金額降冪排列：");
            for (Order2 order : sorted) {
                System.out.println("  " + order);
            }
            System.out.println();
        }
    }
    
    // ========== 測試主程式 ==========
    public static void main(String[] args) {
        OrderManagementPractice system = new OrderManagementPractice();
        
        System.out.println("=== 增強訂單管理系統測試 ===\n");
        
        // 1. 測試新增訂單（含重複編號測試）
        System.out.println("--- 測試1：新增訂單 ---");
        system.addOrder(new Order2("ORD001", "王小明", 1500.0));
        system.addOrder(new Order2("ORD002", "陳美玲", 2300.5));
        system.addOrder(new Order2("ORD003", "張大偉", 890.0));
        system.addOrder(new Order2("ORD004", "李素芬", 3100.0));
        system.addOrder(new Order2("ORD005", "王小明", 1750.0));  // 同顧客不同訂單
        system.addOrder(new Order2("ORD001", "林志強", 2000.0));  // 重複編號測試
        system.displayStatus();
        
        // 2. 測試依金額降冪排序
        System.out.println("--- 測試2：依金額降冪排序 ---");
        system.displaySortedByAmount();
        
        // 3. 測試依顧客姓名搜尋
        System.out.println("--- 測試3：依顧客姓名搜尋 ---");
        // 搜尋存在的顧客
        Order2[] results1 = system.searchByCustomerName("王小明");
        for (Order2 order : results1) {
            System.out.println("  找到： " + order);
        }
        System.out.println();
        
        // 搜尋不存在的顧客
        Order2[] results2 = system.searchByCustomerName("趙志明");
        System.out.println();
        
        // 4. 測試空等待行列
        System.out.println("--- 測試4：處理訂單（等待行列操作）---");
        // 處理第一筆
        system.processNextOrder();
        // 顯示處理後的狀態
        system.displayStatus();
        // 處理第二筆
        system.processNextOrder();
        system.displayStatus();
        
        // 5. 測試完成堆疊
        System.out.println("--- 測試5：查看完成訂單 ---");
        system.getLastCompletedOrder();
        // 處理所有剩餘訂單
        system.processNextOrder();
        system.processNextOrder();
        system.processNextOrder();
        system.displayStatus();
        
        // 6. 測試空等待行列和空完成堆疊
        System.out.println("--- 測試6：邊界案例測試 ---");
        System.out.println("嘗試從空等待行列取得訂單：");
        system.getNextPendingOrder();
        System.out.println("嘗試從空完成堆疊查看訂單：");
        system.getLastCompletedOrder();
        
        // 7. 測試找不到資料
        System.out.println("--- 測試7：搜尋不存在的顧客 ---");
        system.searchByCustomerName("不存在的人");
        
        // 8. 測試新增重複編號
        System.out.println("--- 測試8：嘗試新增重複編號 ---");
        system.addOrder(new Order2("ORD001", "重複測試", 999.0));
        
        System.out.println("\n=== 測試完成 ===");
    }
}
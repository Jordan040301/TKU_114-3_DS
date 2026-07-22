import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DeliveryProcessingSystem {
    
    // Queue 儲存待處理工作
    private Queue<DeliveryTask> waitingQueue;
    // Stack 儲存完成記錄
    private Stack<DeliveryTask> completedStack;
    private int totalProcessed;
    private int maxRetry;
    
    // 建構子
    public DeliveryProcessingSystem() {
        this.waitingQueue = new LinkedList<>();
        this.completedStack = new Stack<>();
        this.totalProcessed = 0;
        this.maxRetry = 3; // 最大重試次數
    }
    
    public static void main(String[] args) {
        System.out.println("=== 產品工作流程系統 ===");
        System.out.println("Queue 儲存待處理工作，Stack 儲存完成記錄\n");
        
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();
        
        // 測試1：新增工作
        System.out.println("=== 測試1：新增工作 ===");
        system.addTask("T001", "產品包裝", "高");
        system.addTask("T002", "品質檢查", "中");
        system.addTask("T003", "出貨準備", "高");
        system.addTask("T004", "文件審核", "低");
        system.addTask("T005", "庫存盤點", "中");
        system.showWaitingQueue();
        system.showStatistics();
        
        // 測試2：檢視下一個工作
        System.out.println("\n=== 測試2：檢視下一個工作 ===");
        system.peekNext();
        
        // 測試3：完成下一個工作
        System.out.println("\n=== 測試3：完成下一個工作 ===");
        system.processNext();
        system.processNext();
        system.showWaitingQueue();
        system.showCompletedStack();
        system.showStatistics();
        
        // 測試4：繼續處理
        System.out.println("\n=== 測試4：繼續處理 ===");
        system.processNext();
        system.processNext();
        system.showWaitingQueue();
        system.showCompletedStack();
        system.showStatistics();
        
        // 測試5：多次後工作回到等待列尾端
        System.out.println("\n=== 測試5：多次後工作回到等待列尾端 ===");
        system.addTask("T006", "客戶通知", "高");
        system.addTask("T007", "資料備份", "低");
        system.showWaitingQueue();
        
        // 處理工作，觀察重試機制
        system.processNext();
        system.processNext();
        system.processNext();
        system.processNext();
        system.showWaitingQueue();
        system.showCompletedStack();
        system.showStatistics();
        
        // 測試6：空隊列操作（測試安全）
        System.out.println("\n=== 測試6：空隊列操作（測試安全） ===");
        system.processNext();
        system.peekNext();
        system.showWaitingQueue();
        
        // 測試7：檢視最近完成的工作
        System.out.println("\n=== 測試7：檢視最近完成的工作 ===");
        system.showRecentCompleted(3);
        system.showRecentCompleted(5);
        
        // 測試8：新增更多工作
        System.out.println("\n=== 測試8：新增更多工作 ===");
        system.addTask("T008", "最終審核", "高");
        system.addTask("T009", "出貨確認", "中");
        system.showWaitingQueue();
        system.processNext();
        system.processNext();
        system.showCompletedStack();
        system.showStatistics();
        
        // 測試9：完整記錄
        System.out.println("\n=== 測試9：完整記錄 ===");
        system.showAllRecords();
        
        System.out.println("\n=== 所有測試完成 ===");
        System.out.println("總操作次數：9 次測試");
    }
    
    /**
     * 新增工作（加入等待佇列）
     */
    public void addTask(String taskCode, String description, String priority) {
        if (taskCode == null || taskCode.trim().isEmpty()) {
            System.out.println("  ❌ 錯誤：工作代碼不得為空白！");
            return;
        }
        
        if (description == null || description.trim().isEmpty()) {
            System.out.println("  ❌ 錯誤：工作說明不得為空白！");
            return;
        }
        
        if (priority == null || priority.trim().isEmpty()) {
            priority = "一般";
        }
        
        // 檢查代碼是否已存在
        if (findTaskByCode(taskCode) != null) {
            System.out.println("  ❌ 錯誤：工作代碼 \"" + taskCode + "\" 已存在！");
            return;
        }
        
        DeliveryTask task = new DeliveryTask(taskCode.trim(), description.trim(), priority.trim());
        waitingQueue.offer(task);
        System.out.println("  ✅ 新增工作：" + task);
        System.out.println("  📊 等待工作數：" + waitingQueue.size());
    }
    
    /**
     * 完成下一個工作
     * 如果重試次數超過限制，移到完成記錄
     * 否則回到等待列尾端
     */
    public void processNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("  ⚠️ 沒有待處理的工作！");
            return;
        }
        
        DeliveryTask task = waitingQueue.poll();
        
        // 檢查重試次數
        if (task.getRetryCount() >= maxRetry) {
            // 超過最大重試次數，標記為完成
            completedStack.push(task);
            totalProcessed++;
            System.out.println("  ✅ 完成工作（已達最大重試次數）：" + task);
            System.out.println("  📊 剩餘等待工作數：" + waitingQueue.size());
            return;
        }
        
        // 模擬處理失敗（根據優先級決定是否重試）
        boolean success = processTask(task);
        
        if (success) {
            // 處理成功，移到完成記錄
            completedStack.push(task);
            totalProcessed++;
            System.out.println("  ✅ 完成工作：" + task);
        } else {
            // 處理失敗，增加重試次數，回到等待列尾端
            task.incrementRetry();
            waitingQueue.offer(task);
            System.out.println("  🔄 工作處理失敗，回到等待列尾端：" + task);
            System.out.println("  📊 重試次數：" + task.getRetryCount() + "/" + maxRetry);
        }
        System.out.println("  📊 剩餘等待工作數：" + waitingQueue.size());
    }
    
    /**
     * 模擬工作處理（根據優先級決定成功率）
     */
    private boolean processTask(DeliveryTask task) {
        // 高優先級：80% 成功率
        // 中優先級：60% 成功率
        // 低優先級：40% 成功率
        double successRate;
        switch (task.getPriority()) {
            case "高":
                successRate = 0.8;
                break;
            case "中":
                successRate = 0.6;
                break;
            case "低":
                successRate = 0.4;
                break;
            default:
                successRate = 0.5;
        }
        
        // 使用簡單的隨機模擬（基於重試次數提高成功率）
        double adjustedRate = Math.min(successRate + (task.getRetryCount() * 0.1), 0.95);
        return Math.random() < adjustedRate;
    }
    
    /**
     * 檢視下一個工作（不取出）
     */
    public void peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("  ℹ️ 沒有待處理的工作");
            return;
        }
        
        DeliveryTask next = waitingQueue.peek();
        System.out.println("  👀 下一個工作：" + next);
        System.out.println("  📊 等待工作數：" + waitingQueue.size());
    }
    
    /**
     * 顯示等待佇列
     */
    public void showWaitingQueue() {
        if (waitingQueue.isEmpty()) {
            System.out.println("  📋 等待佇列：空（沒有待處理的工作）");
            return;
        }
        
        System.out.println("  📋 等待佇列（共 " + waitingQueue.size() + " 個工作）：");
        System.out.println("  ┌────┬──────────┬─────────────────────┬──────────┬────────┐");
        System.out.println("  │ 順序 │ 代碼     │ 工作說明            │ 優先級   │ 重試次數│");
        System.out.println("  ├────┼──────────┼─────────────────────┼──────────┼────────┤");
        
        int position = 1;
        for (DeliveryTask task : waitingQueue) {
            String desc = task.getDescription();
            String priority = task.getPriority();
            if (desc.length() > 19) {
                desc = desc.substring(0, 19);
            }
            System.out.printf("  │  %-2d │ %-8s │ %-19s │ %-8s │ %-6d │%n", 
                position, task.getTaskCode(), desc, priority, task.getRetryCount());
            position++;
        }
        System.out.println("  └────┴──────────┴─────────────────────┴──────────┴────────┘");
    }
    
    /**
     * 顯示完成記錄（Stack）
     */
    public void showCompletedStack() {
        if (completedStack.isEmpty()) {
            System.out.println("  📋 完成記錄：空（尚未完成任何工作）");
            return;
        }
        
        System.out.println("  📋 完成記錄（共 " + completedStack.size() + " 個工作，最近完成在上方）：");
        System.out.println("  ┌────┬──────────┬─────────────────────┬──────────┬────────┐");
        System.out.println("  │ 順序 │ 代碼     │ 工作說明            │ 優先級   │ 重試次數│");
        System.out.println("  ├────┼──────────┼─────────────────────┼──────────┼────────┤");
        
        // 複製 Stack 以顯示（不影響原 Stack）
        @SuppressWarnings("unchecked")
        Stack<DeliveryTask> tempStack = (Stack<DeliveryTask>) completedStack.clone();
        int position = 1;
        while (!tempStack.isEmpty()) {
            DeliveryTask task = tempStack.pop();
            String desc = task.getDescription();
            String priority = task.getPriority();
            if (desc.length() > 19) {
                desc = desc.substring(0, 19);
            }
            System.out.printf("  │  %-2d │ %-8s │ %-19s │ %-8s │ %-6d │%n", 
                position, task.getTaskCode(), desc, priority, task.getRetryCount());
            position++;
        }
        System.out.println("  └────┴──────────┴─────────────────────┴──────────┴────────┘");
    }
    
    /**
     * 檢視最近完成的 n 個工作
     */
    public void showRecentCompleted(int n) {
        if (completedStack.isEmpty()) {
            System.out.println("  ℹ️ 沒有完成記錄");
            return;
        }
        
        int count = Math.min(n, completedStack.size());
        System.out.println("  📋 最近 " + count + " 個完成的工作：");
        
        @SuppressWarnings("unchecked")
        Stack<DeliveryTask> tempStack = (Stack<DeliveryTask>) completedStack.clone();
        int shown = 0;
        while (!tempStack.isEmpty() && shown < count) {
            DeliveryTask task = tempStack.pop();
            System.out.println("    " + (shown + 1) + ". " + task);
            shown++;
        }
    }
    
    /**
     * 顯示所有記錄
     */
    public void showAllRecords() {
        System.out.println("\n  📊 完整記錄：");
        System.out.println("  等待工作數：" + waitingQueue.size());
        System.out.println("  完成工作數：" + completedStack.size());
        System.out.println("  總處理數：" + totalProcessed);
        System.out.println("  最大重試次數：" + maxRetry);
        
        System.out.println("\n  📋 等待佇列：");
        showWaitingQueue();
        
        System.out.println("\n  📋 完成記錄：");
        showCompletedStack();
    }
    
    /**
     * 顯示統計資訊
     */
    public void showStatistics() {
        System.out.println("  📊 統計資訊：");
        System.out.println("    等待工作數：" + waitingQueue.size());
        System.out.println("    完成工作數：" + completedStack.size());
        System.out.println("    總處理數：" + totalProcessed);
        System.out.println("    最大重試次數：" + maxRetry);
    }
    
    /**
     * 依代碼搜尋工作
     */
    private DeliveryTask findTaskByCode(String taskCode) {
        for (DeliveryTask task : waitingQueue) {
            if (task.getTaskCode().equalsIgnoreCase(taskCode)) {
                return task;
            }
        }
        for (DeliveryTask task : completedStack) {
            if (task.getTaskCode().equalsIgnoreCase(taskCode)) {
                return task;
            }
        }
        return null;
    }
}
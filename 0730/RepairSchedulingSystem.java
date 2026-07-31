import java.util.*;

/**
 * RepairSchedulingSystem.java
 * 維修工作排程系統
 * 
 * 功能要求：
 * 1. Queue 儲存等待維修工作
 * 2. Stack 保存已完成工作及支援複數
 * 3. ArrayList 保存所有工作
 * 4. 使用歸併排序依優先等級降冪；相同等級保持排序順序
 * 5. 支援依設備名稱搜尋
 * 6. 顯示等待、完成及所有工作統計
 */
public class RepairSchedulingSystem {
    
    private ArrayList<RepairTask> allTasks;          // 所有工作
    private Queue<RepairTask> waitingQueue;          // 等待維修工作（FIFO）
    private Stack<RepairTask> completedStack;        // 已完成工作（LIFO）
    
    /**
     * 建構子：初始化系統
     */
    public RepairSchedulingSystem() {
        allTasks = new ArrayList<>();
        waitingQueue = new LinkedList<>();
        completedStack = new Stack<>();
    }
    
    /**
     * 新增維修工作（防止重複編號）
     * @param task 要新增的工作
     * @return true 如果新增成功，false 如果編號已存在
     */
    public boolean addTask(RepairTask task) {
        // 檢查是否已存在相同編號的工作
        for (RepairTask existing : allTasks) {
            if (existing.getTaskId().equals(task.getTaskId())) {
                System.out.println("錯誤：工作編號 " + task.getTaskId() + " 已存在！");
                return false;
            }
        }
        
        // 新增到所有工作清單
        allTasks.add(task);
        // 加入等待佇列
        waitingQueue.offer(task);
        System.out.println("成功新增工作：" + task);
        return true;
    }
    
    /**
     * 處理下一筆工作（從等待佇列移到完成堆疊）
     * @return true 如果處理成功，false 如果等待佇列為空
     */
    public boolean processNextTask() {
        if (waitingQueue.isEmpty()) {
            System.out.println("等待佇列為空，沒有待處理的工作");
            return false;
        }
        
        RepairTask nextTask = waitingQueue.poll();
        nextTask.setStatus("COMPLETED");
        completedStack.push(nextTask);
        System.out.println("工作已完成處理：" + nextTask);
        return true;
    }
    
    /**
     * 取得所有工作（依優先等級降冪排序）
     * @return 排序後的工作陣列
     */
    public RepairTask[] sortByPriorityDesc() {
        if (allTasks.isEmpty()) {
            System.out.println("目前沒有工作資料");
            return new RepairTask[0];
        }
        
        RepairTask[] sorted = allTasks.toArray(new RepairTask[0]);
        RepairAlgorithms.mergeSortByPriorityDesc(sorted, 0, sorted.length - 1);
        
        System.out.println("依優先等級降冪排序完成（相同等級保持原有順序）");
        return sorted;
    }
    
    /**
     * 依設備名稱搜尋所有工作（順序搜尋）
     * @param deviceName 設備名稱
     * @return 所有符合的工作陣列
     */
    public RepairTask[] searchByDeviceName(String deviceName) {
        if (allTasks.isEmpty()) {
            System.out.println("目前沒有工作資料");
            return new RepairTask[0];
        }
        
        RepairTask[] allTasksArray = allTasks.toArray(new RepairTask[0]);
        RepairTask[] results = RepairAlgorithms.sequentialSearchByDeviceName(allTasksArray, deviceName);
        
        if (results.length == 0) {
            System.out.println("找不到設備名稱為 \"" + deviceName + "\" 的工作");
        } else {
            System.out.println("找到 " + results.length + " 筆設備名稱為 \"" + deviceName + "\" 的工作");
        }
        
        return results;
    }
    
    /**
     * 依工作描述搜尋所有工作（順序搜尋，部分匹配）
     * @param keyword 關鍵字
     * @return 所有符合的工作陣列
     */
    public RepairTask[] searchByDescription(String keyword) {
        if (allTasks.isEmpty()) {
            System.out.println("目前沒有工作資料");
            return new RepairTask[0];
        }
        
        RepairTask[] allTasksArray = allTasks.toArray(new RepairTask[0]);
        RepairTask[] results = RepairAlgorithms.sequentialSearchByDescription(allTasksArray, keyword);
        
        if (results.length == 0) {
            System.out.println("找不到描述中包含 \"" + keyword + "\" 的工作");
        } else {
            System.out.println("找到 " + results.length + " 筆描述中包含 \"" + keyword + "\" 的工作");
        }
        
        return results;
    }
    
    /**
     * 顯示下一筆待處理工作（從等待佇列查看，不取出）
     * @return 下一筆待處理工作，如果沒有則返回 null
     */
    public RepairTask peekNextTask() {
        if (waitingQueue.isEmpty()) {
            System.out.println("等待佇列為空，沒有待處理工作");
            return null;
        }
        
        RepairTask nextTask = waitingQueue.peek();
        System.out.println("下一筆待處理工作：" + nextTask);
        return nextTask;
    }
    
    /**
     * 顯示最近完成的工作（從完成堆疊查看，不取出）
     * @return 最近完成的工作，如果沒有則返回 null
     */
    public RepairTask peekLastCompleted() {
        if (completedStack.isEmpty()) {
            System.out.println("完成堆疊為空，沒有已完成工作");
            return null;
        }
        
        RepairTask lastCompleted = completedStack.peek();
        System.out.println("最近完成的工作：" + lastCompleted);
        return lastCompleted;
    }
    
    /**
     * 顯示系統統計資訊
     */
    public void displayStatistics() {
        System.out.println("\n=== 系統統計資訊 ===");
        System.out.println("總工作數：" + allTasks.size());
        System.out.println("等待佇列工作數：" + waitingQueue.size());
        System.out.println("完成堆疊工作數：" + completedStack.size());
        
        // 計算各優先等級的數量
        int[] priorityCount = new int[6]; // 1-5級
        for (RepairTask task : allTasks) {
            if (task.getPriority() >= 1 && task.getPriority() <= 5) {
                priorityCount[task.getPriority()]++;
            }
        }
        
        System.out.println("\n優先等級分布：");
        String[] levels = {"極低", "低", "中", "高", "緊急"};
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + levels[i] + " (等級" + (i+1) + ")：" + priorityCount[i+1] + " 筆");
        }
        
        // 顯示各狀態數量
        int waiting = 0, completed = 0;
        for (RepairTask task : allTasks) {
            if (task.getStatus().equals("WAITING")) {
                waiting++;
            } else if (task.getStatus().equals("COMPLETED")) {
                completed++;
            }
        }
        System.out.println("\n狀態分布：");
        System.out.println("  等待中：" + waiting + " 筆");
        System.out.println("  已完成：" + completed + " 筆");
        System.out.println();
    }
    
    /**
     * 顯示所有工作
     */
    public void displayAllTasks() {
        System.out.println("\n=== 所有工作 ===");
        if (allTasks.isEmpty()) {
            System.out.println("目前沒有工作資料");
        } else {
            System.out.println("總筆數：" + allTasks.size());
            for (RepairTask task : allTasks) {
                System.out.println("  " + task);
            }
        }
        System.out.println();
    }
    
    /**
     * 顯示等待佇列
     */
    public void displayWaitingQueue() {
        System.out.println("\n=== 等待佇列（FIFO） ===");
        if (waitingQueue.isEmpty()) {
            System.out.println("等待佇列為空");
        } else {
            System.out.println("筆數：" + waitingQueue.size());
            for (RepairTask task : waitingQueue) {
                System.out.println("  " + task);
            }
        }
        System.out.println();
    }
    
    /**
     * 顯示完成堆疊
     */
    public void displayCompletedStack() {
        System.out.println("\n=== 完成堆疊（LIFO） ===");
        if (completedStack.isEmpty()) {
            System.out.println("完成堆疊為空");
        } else {
            System.out.println("筆數：" + completedStack.size());
            // 從堆疊頂端開始顯示
            for (int i = completedStack.size() - 1; i >= 0; i--) {
                System.out.println("  " + completedStack.get(i));
            }
        }
        System.out.println();
    }
    
    /**
     * 顯示排序後的工作（依優先等級降冪）
     */
    public void displaySortedByPriority() {
        RepairTask[] sorted = sortByPriorityDesc();
        System.out.println("\n=== 依優先等級降冪排列 ===");
        if (sorted.length == 0) {
            System.out.println("沒有工作資料");
        } else {
            System.out.println("總筆數：" + sorted.length);
            for (RepairTask task : sorted) {
                System.out.println("  " + task);
            }
        }
        System.out.println();
    }
    
    // ========== 測試主程式 ==========
    public static void main(String[] args) {
        RepairSchedulingSystem system = new RepairSchedulingSystem();
        
        System.out.println("=== 維修工作排程系統測試 ===\n");
        
        // 1. 測試新增工作（含重複編號測試）
        System.out.println("--- 測試1：新增維修工作 ---");
        system.addTask(new RepairTask("T001", "伺服器A", "硬碟故障更換", 5));
        system.addTask(new RepairTask("T002", "印表機B", "碳粉不足補充", 2));
        system.addTask(new RepairTask("T003", "網路交換器C", "連接埠故障維修", 4));
        system.addTask(new RepairTask("T004", "伺服器A", "系統更新維護", 3));
        system.addTask(new RepairTask("T005", "電腦D", "無法開機檢查", 5));
        system.addTask(new RepairTask("T006", "投影機E", "燈泡更換", 2));
        system.addTask(new RepairTask("T007", "伺服器A", "備份資料還原", 4));
        system.addTask(new RepairTask("T008", "電腦F", "軟體安裝設定", 1));
        system.addTask(new RepairTask("T001", "重複測試", "重複編號測試", 3)); // 重複編號測試
        
        system.displayAllTasks();
        
        // 2. 顯示系統統計
        System.out.println("--- 測試2：系統統計資訊 ---");
        system.displayStatistics();
        
        // 3. 顯示等待佇列
        System.out.println("--- 測試3：顯示等待佇列 ---");
        system.displayWaitingQueue();
        
        // 4. 測試依優先等級排序（穩定排序）
        System.out.println("--- 測試4：依優先等級降冪排序（穩定排序） ---");
        system.displaySortedByPriority();
        
        // 5. 測試工作處理
        System.out.println("--- 測試5：處理工作 ---");
        // 查看下一筆
        system.peekNextTask();
        // 處理一筆
        system.processNextTask();
        // 處理第二筆
        system.processNextTask();
        
        // 6. 顯示更新後的狀態
        System.out.println("--- 更新後的系統狀態 ---");
        system.displayAllTasks();
        system.displayWaitingQueue();
        system.displayCompletedStack();
        system.displayStatistics();
        
        // 7. 測試搜尋功能（依設備名稱）
        System.out.println("--- 測試6：依設備名稱搜尋 ---");
        RepairTask[] searchResults1 = system.searchByDeviceName("伺服器A");
        for (RepairTask task : searchResults1) {
            System.out.println("  找到：" + task);
        }
        System.out.println();
        
        // 搜尋不存在的設備
        RepairTask[] searchResults2 = system.searchByDeviceName("不存在的設備");
        System.out.println();
        
        // 8. 測試搜尋功能（依描述關鍵字）
        System.out.println("--- 測試7：依描述關鍵字搜尋 ---");
        RepairTask[] searchResults3 = system.searchByDescription("更換");
        for (RepairTask task : searchResults3) {
            System.out.println("  找到：" + task);
        }
        System.out.println();
        
        // 9. 處理所有剩餘工作
        System.out.println("--- 測試8：處理所有剩餘工作 ---");
        while (system.waitingQueue.size() > 0) {
            system.processNextTask();
        }
        
        // 10. 顯示最終狀態
        System.out.println("--- 最終系統狀態 ---");
        system.displayAllTasks();
        system.displayWaitingQueue();
        system.displayCompletedStack();
        system.displayStatistics();
        
        // 11. 測試空佇列和空堆疊的邊界案例
        System.out.println("--- 測試9：邊界案例測試 ---");
        System.out.println("嘗試從空等待佇列取下一筆：");
        system.peekNextTask();
        System.out.println("嘗試從空完成堆疊取最近完成：");
        system.peekLastCompleted();
        
        System.out.println("\n=== 測試完成 ===");
    }
}
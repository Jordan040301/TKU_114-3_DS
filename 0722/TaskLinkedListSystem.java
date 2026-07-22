import java.util.Scanner;

public class TaskLinkedListSystem {
    
    private static TaskLinkedList taskList = new TaskLinkedList();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== 工作項目管理系統 ===");
        System.out.println("歡迎使用工作項目管理系統！\n");
        
        // 加入範例資料
        initializeSampleData();
        
        while (true) {
            showMenu();
            System.out.print("請選擇功能 (1-8)：");
            
            if (!scanner.hasNextInt()) {
                System.out.println("  ❌ 錯誤：請輸入數字 1-8！");
                scanner.next();
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addUrgentTask();
                    break;
                case 2:
                    addNormalTask();
                    break;
                case 3:
                    markCompleted();
                    break;
                case 4:
                    deleteCompleted();
                    break;
                case 5:
                    displayIncomplete();
                    break;
                case 6:
                    displayAll();
                    break;
                case 7:
                    showStatistics();
                    break;
                case 8:
                    System.out.println("感謝使用工作項目管理系統，再見！");
                    scanner.close();
                    return;
                default:
                    System.out.println("  ❌ 錯誤：請選擇 1-8 的有效選項！");
            }
        }
    }
    
    /**
     * 初始化範例資料
     */
    private static void initializeSampleData() {
        System.out.println("📌 載入範例工作...");
        taskList.addNormalTask("T001", "完成報告");
        taskList.addNormalTask("T002", "回覆郵件");
        taskList.addUrgentTask("T003", "緊急客戶會議");
        taskList.addNormalTask("T004", "更新系統");
        taskList.addNormalTask("T005", "準備簡報");
        System.out.println("✅ 已載入 5 項範例工作\n");
    }
    
    /**
     * 顯示功能選單
     */
    private static void showMenu() {
        System.out.println("\n=== 功能選單 ===");
        System.out.println("1. 新增緊急工作（前端）");
        System.out.println("2. 新增一般工作（尾端）");
        System.out.println("3. 標記工作為已完成");
        System.out.println("4. 刪除所有已完成工作");
        System.out.println("5. 顯示未完成工作");
        System.out.println("6. 顯示所有工作");
        System.out.println("7. 顯示統計資訊");
        System.out.println("8. 離開系統");
    }
    
    /**
     * 功能1：新增緊急工作（前端）
     */
    private static void addUrgentTask() {
        System.out.println("\n--- 新增緊急工作 ---");
        System.out.print("請輸入工作代碼：");
        String code = scanner.nextLine().trim();
        
        if (code.isEmpty()) {
            System.out.println("  ❌ 錯誤：工作代碼不得為空白！");
            return;
        }
        
        System.out.print("請輸入工作說明：");
        String desc = scanner.nextLine().trim();
        
        if (desc.isEmpty()) {
            System.out.println("  ❌ 錯誤：工作說明不得為空白！");
            return;
        }
        
        taskList.addUrgentTask(code, desc);
    }
    
    /**
     * 功能2：新增一般工作（尾端）
     */
    private static void addNormalTask() {
        System.out.println("\n--- 新增一般工作 ---");
        System.out.print("請輸入工作代碼：");
        String code = scanner.nextLine().trim();
        
        if (code.isEmpty()) {
            System.out.println("  ❌ 錯誤：工作代碼不得為空白！");
            return;
        }
        
        System.out.print("請輸入工作說明：");
        String desc = scanner.nextLine().trim();
        
        if (desc.isEmpty()) {
            System.out.println("  ❌ 錯誤：工作說明不得為空白！");
            return;
        }
        
        taskList.addNormalTask(code, desc);
    }
    
    /**
     * 功能3：標記工作為已完成
     */
    private static void markCompleted() {
        System.out.println("\n--- 標記工作為已完成 ---");
        System.out.print("請輸入要標記完成的工作代碼：");
        String code = scanner.nextLine().trim();
        
        if (code.isEmpty()) {
            System.out.println("  ❌ 錯誤：請輸入有效的代碼！");
            return;
        }
        
        taskList.markTaskCompleted(code);
    }
    
    /**
     * 功能4：刪除所有已完成工作
     */
    private static void deleteCompleted() {
        System.out.println("\n--- 刪除已完成工作 ---");
        int count = taskList.getCompletedCount();
        if (count == 0) {
            System.out.println("  ℹ️ 沒有已完成的工作需要刪除");
            return;
        }
        
        System.out.println("  將刪除 " + count + " 個已完成工作");
        System.out.print("  請輸入 y 確認刪除，其他鍵取消：");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (!confirm.equals("y")) {
            System.out.println("  ❌ 已取消刪除操作");
            return;
        }
        
        taskList.deleteCompletedTasks();
    }
    
    /**
     * 功能5：顯示未完成工作
     */
    private static void displayIncomplete() {
        System.out.println("\n--- 未完成工作清單 ---");
        taskList.displayIncompleteTasks();
    }
    
    /**
     * 功能6：顯示所有工作
     */
    private static void displayAll() {
        System.out.println("\n--- 所有工作清單 ---");
        taskList.displayAllTasks();
    }
    
    /**
     * 功能7：顯示統計資訊
     */
    private static void showStatistics() {
        System.out.println("\n--- 統計資訊 ---");
        taskList.displayStatistics();
    }
}
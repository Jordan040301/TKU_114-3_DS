public class TaskLinkedList {
    private TaskNode head;
    private int size;
    
    // 建構子
    public TaskLinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    /**
     * 取得工作總數
     */
    public int getSize() {
        return size;
    }
    
    /**
     * 檢查是否為空
     */
    public boolean isEmpty() {
        return head == null;
    }
    
    /**
     * 取得未完成工作數量
     */
    public int getIncompleteCount() {
        int count = 0;
        TaskNode current = head;
        while (current != null) {
            if (!current.isCompleted()) {
                count++;
            }
            current = current.getNext();
        }
        return count;
    }
    
    /**
     * 取得已完成工作數量
     */
    public int getCompletedCount() {
        int count = 0;
        TaskNode current = head;
        while (current != null) {
            if (current.isCompleted()) {
                count++;
            }
            current = current.getNext();
        }
        return count;
    }
    
    /**
     * 依工作代碼搜尋
     */
    public TaskNode findByCode(String taskCode) {
        if (taskCode == null || taskCode.trim().isEmpty()) {
            return null;
        }
        
        TaskNode current = head;
        int position = 1;
        while (current != null) {
            if (current.getTaskCode().equalsIgnoreCase(taskCode.trim())) {
                System.out.println("  🔍 找到工作在位置 " + position + "：" + current);
                return current;
            }
            current = current.getNext();
            position++;
        }
        System.out.println("  ❌ 找不到工作代碼 \"" + taskCode + "\"");
        return null;
    }
    
    /**
     * 緊急工作：加到前端
     */
    public boolean addUrgentTask(String taskCode, String description) {
        // 檢查輸入
        if (taskCode == null || taskCode.trim().isEmpty()) {
            System.out.println("  ❌ 錯誤：工作代碼不得為空白！");
            return false;
        }
        if (description == null || description.trim().isEmpty()) {
            System.out.println("  ❌ 錯誤：工作說明不得為空白！");
            return false;
        }
        
        // 檢查代碼是否重複
        if (findByCode(taskCode) != null) {
            System.out.println("  ❌ 錯誤：工作代碼 \"" + taskCode + "\" 已存在！");
            return false;
        }
        
        TaskNode newNode = new TaskNode(taskCode, description);
        newNode.setNext(head);
        head = newNode;
        size++;
        System.out.println("  🚨 緊急工作（前端）：" + taskCode + " | " + description);
        return true;
    }
    
    /**
     * 一般工作：加到尾端
     */
    public boolean addNormalTask(String taskCode, String description) {
        // 檢查輸入
        if (taskCode == null || taskCode.trim().isEmpty()) {
            System.out.println("  ❌ 錯誤：工作代碼不得為空白！");
            return false;
        }
        if (description == null || description.trim().isEmpty()) {
            System.out.println("  ❌ 錯誤：工作說明不得為空白！");
            return false;
        }
        
        // 檢查代碼是否重複
        if (findByCode(taskCode) != null) {
            System.out.println("  ❌ 錯誤：工作代碼 \"" + taskCode + "\" 已存在！");
            return false;
        }
        
        TaskNode newNode = new TaskNode(taskCode, description);
        
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
        System.out.println("  📌 一般工作（尾端）：" + taskCode + " | " + description);
        return true;
    }
    
    /**
     * 標記工作為已完成
     */
    public boolean markTaskCompleted(String taskCode) {
        if (taskCode == null || taskCode.trim().isEmpty()) {
            System.out.println("  ❌ 錯誤：請輸入有效的代碼！");
            return false;
        }
        
        TaskNode found = findByCode(taskCode);
        if (found == null) {
            return false;
        }
        
        if (found.isCompleted()) {
            System.out.println("  ℹ️ 工作 \"" + taskCode + "\" 已經標記為已完成");
            return true;
        }
        
        found.markCompleted();
        System.out.println("  ✅ 工作 \"" + taskCode + "\" 已標記為完成！");
        return true;
    }
    
    /**
     * 刪除已完成工作
     */
    public int deleteCompletedTasks() {
        if (head == null) {
            System.out.println("  ⚠️ 工作清單為空");
            return 0;
        }
        
        int deletedCount = 0;
        
        // 處理頭部已完成的工作
        while (head != null && head.isCompleted()) {
            System.out.println("  🗑️ 刪除已完成工作：" + head.getTaskCode() + " | " + head.getDescription());
            head = head.getNext();
            size--;
            deletedCount++;
        }
        
        // 處理中間和尾部已完成的工作
        if (head != null) {
            TaskNode current = head;
            while (current.getNext() != null) {
                if (current.getNext().isCompleted()) {
                    System.out.println("  🗑️ 刪除已完成工作：" + current.getNext().getTaskCode() + " | " + current.getNext().getDescription());
                    current.setNext(current.getNext().getNext());
                    size--;
                    deletedCount++;
                } else {
                    current = current.getNext();
                }
            }
        }
        
        if (deletedCount == 0) {
            System.out.println("  ℹ️ 沒有已完成的工作需要刪除");
        } else {
            System.out.println("  ✅ 共刪除 " + deletedCount + " 個已完成工作");
        }
        return deletedCount;
    }
    
    /**
     * 顯示所有未完成工作
     */
    public void displayIncompleteTasks() {
        if (head == null) {
            System.out.println("  📋 工作清單為空");
            return;
        }
        
        // 先統計未完成數量
        int incompleteCount = getIncompleteCount();
        if (incompleteCount == 0) {
            System.out.println("  🎉 所有工作都已完成！");
            return;
        }
        
        System.out.println("  📋 未完成工作清單（共 " + incompleteCount + " 項）：");
        System.out.println("  ┌────┬──────────┬─────────────────────────┐");
        System.out.println("  │ 編號 │ 代碼     │ 工作說明                │");
        System.out.println("  ├────┼──────────┼─────────────────────────┤");
        
        TaskNode current = head;
        int position = 1;
        while (current != null) {
            if (!current.isCompleted()) {
                String code = current.getTaskCode();
                String desc = current.getDescription();
                if (desc.length() > 23) {
                    desc = desc.substring(0, 23);
                }
                System.out.printf("  │ %-2d  │ %-8s │ %-23s │%n", 
                    position, code, desc);
            }
            current = current.getNext();
            position++;
        }
        System.out.println("  └────┴──────────┴─────────────────────────┘");
    }
    
    /**
     * 顯示所有工作（含完成狀態）
     */
    public void displayAllTasks() {
        if (head == null) {
            System.out.println("  📋 工作清單為空");
            return;
        }
        
        System.out.println("  📋 所有工作清單（共 " + size + " 項）：");
        System.out.println("  ┌────┬──────────┬─────────────────────────┬──────────┐");
        System.out.println("  │ 編號 │ 代碼     │ 工作說明                │ 狀態     │");
        System.out.println("  ├────┼──────────┼─────────────────────────┼──────────┤");
        
        TaskNode current = head;
        int position = 1;
        while (current != null) {
            String code = current.getTaskCode();
            String desc = current.getDescription();
            String status = current.isCompleted() ? "✅ 完成" : "⏳ 進行中";
            if (desc.length() > 23) {
                desc = desc.substring(0, 23);
            }
            System.out.printf("  │ %-2d  │ %-8s │ %-23s │ %-8s │%n", 
                position, code, desc, status);
            current = current.getNext();
            position++;
        }
        System.out.println("  └────┴──────────┴─────────────────────────┴──────────┘");
    }
    
    /**
     * 顯示統計資訊
     */
    public void displayStatistics() {
        System.out.println("  📊 工作統計：");
        System.out.println("     工作總數：" + size + " 項");
        System.out.println("     已完成：" + getCompletedCount() + " 項");
        System.out.println("     未完成：" + getIncompleteCount() + " 項");
        System.out.println("     完成率：" + (size > 0 ? String.format("%.1f%%", (getCompletedCount() * 100.0 / size)) : "0%"));
    }
}
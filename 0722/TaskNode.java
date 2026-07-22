public class TaskNode {
    private String taskCode;      // 工作程式碼
    private String description;   // 工作說明
    private boolean isCompleted;  // 完成狀態
    private TaskNode next;        // 下一個節點
    
    // 建構子
    public TaskNode(String taskCode, String description) {
        this.taskCode = taskCode;
        this.description = description;
        this.isCompleted = false;  // 預設為未完成
        this.next = null;
    }
    
    // Getter 方法
    public String getTaskCode() {
        return taskCode;
    }
    
    public String getDescription() {
        return description;
    }
    
    public boolean isCompleted() {
        return isCompleted;
    }
    
    public TaskNode getNext() {
        return next;
    }
    
    // Setter 方法
    public void setNext(TaskNode next) {
        this.next = next;
    }
    
    // 標記為已完成
    public void markCompleted() {
        this.isCompleted = true;
    }
    
    // 標記為未完成
    public void markIncomplete() {
        this.isCompleted = false;
    }
    
    @Override
    public String toString() {
        String status = isCompleted ? "✅ 已完成" : "⏳ 進行中";
        return taskCode + " | " + description + " | " + status;
    }
}
public class DeliveryTask {
    private String taskCode;      // 工作代碼
    private String description;   // 工作說明
    private String priority;      // 優先級
    private int retryCount;       // 重試次數
    
    // 建構子
    public DeliveryTask(String taskCode, String description, String priority) {
        this.taskCode = taskCode;
        this.description = description;
        this.priority = priority;
        this.retryCount = 0;
    }
    
    // Getter 方法
    public String getTaskCode() {
        return taskCode;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getPriority() {
        return priority;
    }
    
    public int getRetryCount() {
        return retryCount;
    }
    
    // 增加重試次數
    public void incrementRetry() {
        this.retryCount++;
    }
    
    @Override
    public String toString() {
        return taskCode + " | " + description + " | " + priority + " | 重試" + retryCount + "次";
    }
}
/**
 * RepairTask.java
 * 維修工作實體類別，代表一筆維修任務
 */
public class RepairTask {
    private String taskId;          // 工作編號（唯一）
    private String deviceName;      // 設備名稱
    private String description;     // 工作描述
    private int priority;           // 優先等級（數字越大優先級越高）
    private String status;          // 狀態：WAITING, COMPLETED
    
    /**
     * 建構子
     * @param taskId 工作編號
     * @param deviceName 設備名稱
     * @param description 工作描述
     * @param priority 優先等級（1-5，數字越大越優先）
     */
    public RepairTask(String taskId, String deviceName, String description, int priority) {
        this.taskId = taskId;
        this.deviceName = deviceName;
        this.description = description;
        this.priority = priority;
        this.status = "WAITING"; // 預設為等待中
    }
    
    // Getter 方法
    public String getTaskId() { return taskId; }
    public String getDeviceName() { return deviceName; }
    public String getDescription() { return description; }
    public int getPriority() { return priority; }
    public String getStatus() { return status; }
    
    // Setter 方法
    public void setStatus(String status) { this.status = status; }
    public void setPriority(int priority) { this.priority = priority; }
    
    /**
     * 判斷兩筆工作是否相同（根據工作編號）
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RepairTask task = (RepairTask) obj;
        return taskId.equals(task.taskId);
    }
    
    @Override
    public int hashCode() {
        return taskId.hashCode();
    }
    
    @Override
    public String toString() {
        String priorityLevel = getPriorityLevel();
        return String.format("維修工作[編號:%s, 設備:%s, 描述:%s, 優先級:%d(%s), 狀態:%s]", 
                           taskId, deviceName, description, priority, priorityLevel, status);
    }
    
    /**
     * 取得優先等級的文字描述
     */
    private String getPriorityLevel() {
        switch (priority) {
            case 5: return "緊急";
            case 4: return "高";
            case 3: return "中";
            case 2: return "低";
            case 1: return "極低";
            default: return "未知";
        }
    }
}
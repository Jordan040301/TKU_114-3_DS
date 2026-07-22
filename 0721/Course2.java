public class Course2{
    private String code;
    private String name;
    private int capacity;
    private int currentCount;
    
    public Course2(String code, String name, int capacity) {
        this.code = code;
        this.name = name;
        this.capacity = Math.max(capacity, 1);
        this.currentCount = 0;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public int getCurrentCount() {
        return currentCount;
    }
    
    public boolean hasAvailableSeats() {
        return currentCount < capacity;
    }
    
    public boolean isFull() {
        return currentCount >= capacity;
    }
    
    public int getAvailableSeats() {
        return capacity - currentCount;
    }
    
    public boolean registerStudent() {
        if (hasAvailableSeats()) {
            currentCount++;
            return true;
        }
        return false;
    }
    
    public boolean dropStudent() {
        if (currentCount > 0) {
            currentCount--;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        String status = isFull() ? "額滿" : "可選";
        return "代碼：" + code + " | 名稱：" + name + " | 容量：" + capacity + 
               " | 目前人數：" + currentCount + " | 空位：" + getAvailableSeats() + 
               " | 狀態：" + status;
    }
}
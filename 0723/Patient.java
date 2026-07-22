public class Patient {
    private String ticketNumber;  // 號碼
    private String name;          // 姓名
    private String department;    // 科別
    
    // 建構子
    public Patient(String ticketNumber, String name, String department) {
        this.ticketNumber = ticketNumber;
        this.name = name;
        this.department = department;
    }
    
    // Getter 方法
    public String getTicketNumber() {
        return ticketNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDepartment() {
        return department;
    }
    
    @Override
    public String toString() {
        return ticketNumber + " | " + name + " | " + department;
    }
}
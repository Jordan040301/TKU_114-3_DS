/**
 * Order.java
 * 訂單實體類別，代表系統中的一筆訂單
 */
public class Order2 {
    private String orderId;      // 訂單編號（唯一）
    private String customerName; // 顧客姓名
    private double amount;       // 訂單金額
    private String status;       // 訂單狀態：PENDING, COMPLETED, CANCELLED
    
    /**
     * 建構子
     * @param orderId 訂單編號
     * @param customerName 顧客姓名
     * @param amount 訂單金額
     */
    public Order2(String orderId, String customerName, double amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        this.status = "PENDING"; // 預設為待處理
    }
    
    // Getter 方法
    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
    
    // Setter 方法
    public void setStatus(String status) { this.status = status; }
    
    /**
     * 判斷兩筆訂單是否相同（根據訂單編號）
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order2 order = (Order2) obj;
        return orderId.equals(order.orderId);
    }
    
    @Override
    public int hashCode() {
        return orderId.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("訂單[編號:%s, 顧客:%s, 金額:%.2f, 狀態:%s]", 
                           orderId, customerName, amount, status);
    }
}
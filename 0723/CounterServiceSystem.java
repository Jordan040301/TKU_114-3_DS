import java.util.LinkedList;
import java.util.Queue;

public class CounterServiceSystem {
    
    // 使用 Queue 保存號碼與姓名
    private Queue<Customer> waitingQueue;
    private Queue<Customer> servedHistory;
    private int counter;
    
    // 建構子
    public CounterServiceSystem() {
        this.waitingQueue = new LinkedList<>();
        this.servedHistory = new LinkedList<>();
        this.counter = 0;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 櫃檯叫號系統 ===");
        System.out.println("使用 Queue 模擬櫃檯叫號服務\n");
        
        CounterServiceSystem service = new CounterServiceSystem();
        
        // 測試1：取號
        System.out.println("=== 測試1：取號 ===");
        service.takeNumber("張三");
        service.takeNumber("李四");
        service.takeNumber("王五");
        service.takeNumber("趙六");
        service.takeNumber("陳七");
        service.showWaitingList();
        
        // 測試2：查看下一位
        System.out.println("\n=== 測試2：查看下一位 ===");
        service.peekNext();
        
        // 測試3：叫號
        System.out.println("\n=== 測試3：叫號 ===");
        service.callNext();
        service.callNext();
        service.showWaitingList();
        service.showServedHistory();
        
        // 測試4：繼續叫號
        System.out.println("\n=== 測試4：繼續叫號 ===");
        service.callNext();
        service.callNext();
        service.showWaitingList();
        service.showServedHistory();
        
        // 測試5：查看等待人數
        System.out.println("\n=== 測試5：查看等待人數 ===");
        System.out.println("  等待人數：" + service.getWaitingCount());
        System.out.println("  已服務人數：" + service.getServedCount());
        
        // 測試6：空隊列叫號（測試安全）
        System.out.println("\n=== 測試6：空隊列叫號（測試安全） ===");
        service.callNext();
        service.callNext();
        service.callNext();
        service.showWaitingList();
        service.showServedHistory();
        
        // 測試7：取號後繼續服務
        System.out.println("\n=== 測試7：取號後繼續服務 ===");
        service.takeNumber("孫八");
        service.takeNumber("周九");
        service.showWaitingList();
        service.callNext();
        service.callNext();
        service.showWaitingList();
        service.showServedHistory();
        
        // 測試8：查看所有記錄
        System.out.println("\n=== 測試8：查看所有記錄 ===");
        service.showAllRecords();
        
        // 測試9：叫號與查看下一位組合
        System.out.println("\n=== 測試9：叫號與查看下一位組合 ===");
        service.takeNumber("吳十");
        service.peekNext();
        service.callNext();
        service.peekNext();
        service.showAllRecords();
        
        System.out.println("\n=== 所有測試完成 ===");
        System.out.println("總操作次數：9 次測試");
    }
    
    /**
     * 取號（顧客取得號碼牌）
     */
    public void takeNumber(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("  ❌ 錯誤：姓名不得為空白！");
            return;
        }
        
        counter++;
        String ticketNumber = String.format("%03d", counter);
        Customer customer = new Customer(ticketNumber, name.trim());
        waitingQueue.offer(customer);
        System.out.println("  🎫 取號成功：" + customer);
        System.out.println("  📊 目前等待人數：" + waitingQueue.size());
    }
    
    /**
     * 叫號（服務下一位顧客）
     */
    public void callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("  ⚠️ 目前沒有等待的顧客！");
            return;
        }
        
        Customer customer = waitingQueue.poll();
        customer.setServed(true);
        servedHistory.offer(customer);
        System.out.println("  📢 叫號：" + customer);
        System.out.println("  📊 剩餘等待人數：" + waitingQueue.size());
    }
    
    /**
     * 查看下一位（不取出）
     */
    public void peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("  ℹ️ 目前沒有等待的顧客");
            return;
        }
        
        Customer next = waitingQueue.peek();
        System.out.println("  👀 下一位顧客：" + next);
        System.out.println("  📊 等待人數：" + waitingQueue.size());
    }
    
    /**
     * 顯示等待清單
     */
    public void showWaitingList() {
        if (waitingQueue.isEmpty()) {
            System.out.println("  📋 等待清單：空（沒有等待的顧客）");
            return;
        }
        
        System.out.println("  📋 等待清單（共 " + waitingQueue.size() + " 人）：");
        System.out.println("  ┌────┬──────────┬─────────────────────┐");
        System.out.println("  │ 順序 │ 號碼     │ 姓名                │");
        System.out.println("  ├────┼──────────┼─────────────────────┤");
        
        int position = 1;
        for (Customer customer : waitingQueue) {
            String name = customer.getName();
            if (name.length() > 19) {
                name = name.substring(0, 19);
            }
            System.out.printf("  │  %-2d │ %-8s │ %-19s │%n", 
                position, customer.getTicketNumber(), name);
            position++;
        }
        System.out.println("  └────┴──────────┴─────────────────────┘");
    }
    
    /**
     * 顯示已服務記錄
     */
    public void showServedHistory() {
        if (servedHistory.isEmpty()) {
            System.out.println("  📋 服務記錄：空（尚未服務任何顧客）");
            return;
        }
        
        System.out.println("  📋 已服務記錄（共 " + servedHistory.size() + " 人）：");
        System.out.println("  ┌────┬──────────┬─────────────────────┐");
        System.out.println("  │ 順序 │ 號碼     │ 姓名                │");
        System.out.println("  ├────┼──────────┼─────────────────────┤");
        
        int position = 1;
        for (Customer customer : servedHistory) {
            String name = customer.getName();
            if (name.length() > 19) {
                name = name.substring(0, 19);
            }
            System.out.printf("  │  %-2d │ %-8s │ %-19s │%n", 
                position, customer.getTicketNumber(), name);
            position++;
        }
        System.out.println("  └────┴──────────┴─────────────────────┘");
    }
    
    /**
     * 顯示所有記錄（等待 + 已服務）
     */
    public void showAllRecords() {
        System.out.println("\n  📊 完整記錄：");
        System.out.println("  等待人數：" + waitingQueue.size());
        System.out.println("  已服務人數：" + servedHistory.size());
        System.out.println("  總服務人數：" + counter);
        
        showWaitingList();
        showServedHistory();
    }
    
    /**
     * 取得等待人數
     */
    public int getWaitingCount() {
        return waitingQueue.size();
    }
    
    /**
     * 取得已服務人數
     */
    public int getServedCount() {
        return servedHistory.size();
    }
    
    /**
     * 顧客類別（內部類別）
     */
    static class Customer {
        private String ticketNumber;
        private String name;
        private boolean served;
        
        public Customer(String ticketNumber, String name) {
            this.ticketNumber = ticketNumber;
            this.name = name;
            this.served = false;
        }
        
        public String getTicketNumber() {
            return ticketNumber;
        }
        
        public String getName() {
            return name;
        }
        
        public boolean isServed() {
            return served;
        }
        
        public void setServed(boolean served) {
            this.served = served;
        }
        
        @Override
        public String toString() {
            return ticketNumber + " | " + name;
        }
    }
}
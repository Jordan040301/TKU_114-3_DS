import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;

public class ClinicQueueSystem {
    
    // 使用 Queue 保存患者
    private Queue<Patient> waitingQueue;
    private Queue<Patient> servedHistory;
    private Map<String, Integer> departmentCount;
    private int counter;
    private int totalServed;
    
    // 建構子
    public ClinicQueueSystem() {
        this.waitingQueue = new LinkedList<>();
        this.servedHistory = new LinkedList<>();
        this.departmentCount = new HashMap<>();
        this.counter = 0;
        this.totalServed = 0;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 診所叫號系統 ===");
        System.out.println("使用 Queue 模擬診所掛號叫號服務\n");
        
        ClinicQueueSystem clinic = new ClinicQueueSystem();
        
        // 測試1：掛號
        System.out.println("=== 測試1：掛號 ===");
        clinic.register("張三", "內科");
        clinic.register("李四", "外科");
        clinic.register("王五", "內科");
        clinic.register("趙六", "眼科");
        clinic.register("陳七", "外科");
        clinic.register("孫八", "內科");
        clinic.showWaitingList();
        clinic.showDepartmentStatistics();
        
        // 測試2：查看下一位
        System.out.println("\n=== 測試2：查看下一位 ===");
        clinic.peekNext();
        
        // 測試3：叫號
        System.out.println("\n=== 測試3：叫號 ===");
        clinic.callNext();
        clinic.callNext();
        clinic.showWaitingList();
        clinic.showServedHistory();
        clinic.showDepartmentStatistics();
        
        // 測試4：繼續叫號
        System.out.println("\n=== 測試4：繼續叫號 ===");
        clinic.callNext();
        clinic.callNext();
        clinic.showWaitingList();
        clinic.showServedHistory();
        clinic.showDepartmentStatistics();
        
        // 測試5：查看等待人數
        System.out.println("\n=== 測試5：查看等待人數 ===");
        System.out.println("  等待人數：" + clinic.getWaitingCount());
        System.out.println("  已服務人數：" + clinic.getServedCount());
        System.out.println("  總服務人數：" + clinic.getTotalServed());
        
        // 測試6：空隊列叫號（測試安全）
        System.out.println("\n=== 測試6：空隊列叫號（測試安全） ===");
        clinic.callNext();
        clinic.callNext();
        clinic.showWaitingList();
        clinic.showServedHistory();
        
        // 測試7：掛號後繼續服務
        System.out.println("\n=== 測試7：掛號後繼續服務 ===");
        clinic.register("周九", "眼科");
        clinic.register("吳十", "內科");
        clinic.showWaitingList();
        clinic.callNext();
        clinic.callNext();
        clinic.showWaitingList();
        clinic.showServedHistory();
        clinic.showDepartmentStatistics();
        
        // 測試8：查看完整統計
        System.out.println("\n=== 測試8：查看完整統計 ===");
        clinic.showAllStatistics();
        
        // 測試9：號碼不可重複測試
        System.out.println("\n=== 測試9：號碼不可重複測試 ===");
        clinic.register("鄭十一", "外科");
        clinic.register("鄭十一", "內科");  // 不應該重複
        
        System.out.println("\n=== 所有測試完成 ===");
        System.out.println("總操作次數：9 次測試");
    }
    
    /**
     * 掛號（患者取得號碼牌）
     */
    public void register(String name, String department) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("  ❌ 錯誤：姓名不得為空白！");
            return;
        }
        
        if (department == null || department.trim().isEmpty()) {
            System.out.println("  ❌ 錯誤：科別不得為空白！");
            return;
        }
        
        counter++;
        String ticketNumber = String.format("%03d", counter);
        Patient patient = new Patient(ticketNumber, name.trim(), department.trim());
        
        // 檢查號碼是否已存在（安全檢查）
        if (findPatientByNumber(ticketNumber) != null) {
            System.out.println("  ❌ 錯誤：號碼 " + ticketNumber + " 已存在！");
            counter--;
            return;
        }
        
        waitingQueue.offer(patient);
        
        // 更新科別統計
        departmentCount.put(department.trim(), 
            departmentCount.getOrDefault(department.trim(), 0) + 1);
        
        System.out.println("  🎫 掛號成功：" + patient);
        System.out.println("  📊 目前等待人數：" + waitingQueue.size());
    }
    
    /**
     * 叫號（服務下一位患者）
     */
    public void callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("  ⚠️ 目前沒有等待的患者！");
            return;
        }
        
        Patient patient = waitingQueue.poll();
        servedHistory.offer(patient);
        totalServed++;
        
        // 更新科別統計（減少等待）
        String dept = patient.getDepartment();
        departmentCount.put(dept, departmentCount.getOrDefault(dept, 1) - 1);
        if (departmentCount.get(dept) <= 0) {
            departmentCount.remove(dept);
        }
        
        System.out.println("  📢 叫號：" + patient);
        System.out.println("  📊 剩餘等待人數：" + waitingQueue.size());
    }
    
    /**
     * 查看下一位（不取出）
     */
    public void peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("  ℹ️ 目前沒有等待的患者");
            return;
        }
        
        Patient next = waitingQueue.peek();
        System.out.println("  👀 下一位患者：" + next);
        System.out.println("  📊 等待人數：" + waitingQueue.size());
    }
    
    /**
     * 顯示等待清單
     */
    public void showWaitingList() {
        if (waitingQueue.isEmpty()) {
            System.out.println("  📋 等待清單：空（沒有等待的患者）");
            return;
        }
        
        System.out.println("  📋 等待清單（共 " + waitingQueue.size() + " 人）：");
        System.out.println("  ┌────┬──────────┬─────────────────────┬──────────┐");
        System.out.println("  │ 順序 │ 號碼     │ 姓名                │ 科別     │");
        System.out.println("  ├────┼──────────┼─────────────────────┼──────────┤");
        
        int position = 1;
        for (Patient patient : waitingQueue) {
            String name = patient.getName();
            String dept = patient.getDepartment();
            if (name.length() > 19) {
                name = name.substring(0, 19);
            }
            if (dept.length() > 8) {
                dept = dept.substring(0, 8);
            }
            System.out.printf("  │  %-2d │ %-8s │ %-19s │ %-8s │%n", 
                position, patient.getTicketNumber(), name, dept);
            position++;
        }
        System.out.println("  └────┴──────────┴─────────────────────┴──────────┘");
    }
    
    /**
     * 顯示已服務記錄
     */
    public void showServedHistory() {
        if (servedHistory.isEmpty()) {
            System.out.println("  📋 服務記錄：空（尚未服務任何患者）");
            return;
        }
        
        System.out.println("  📋 已服務記錄（共 " + servedHistory.size() + " 人）：");
        System.out.println("  ┌────┬──────────┬─────────────────────┬──────────┐");
        System.out.println("  │ 順序 │ 號碼     │ 姓名                │ 科別     │");
        System.out.println("  ├────┼──────────┼─────────────────────┼──────────┤");
        
        int position = 1;
        for (Patient patient : servedHistory) {
            String name = patient.getName();
            String dept = patient.getDepartment();
            if (name.length() > 19) {
                name = name.substring(0, 19);
            }
            if (dept.length() > 8) {
                dept = dept.substring(0, 8);
            }
            System.out.printf("  │  %-2d │ %-8s │ %-19s │ %-8s │%n", 
                position, patient.getTicketNumber(), name, dept);
            position++;
        }
        System.out.println("  └────┴──────────┴─────────────────────┴──────────┘");
    }
    
    /**
     * 顯示科別統計
     */
    public void showDepartmentStatistics() {
        System.out.println("  📊 科別統計：");
        if (departmentCount.isEmpty()) {
            System.out.println("    目前沒有等待的患者");
            return;
        }
        
        for (Map.Entry<String, Integer> entry : departmentCount.entrySet()) {
            System.out.println("    " + entry.getKey() + "：" + entry.getValue() + " 人");
        }
    }
    
    /**
     * 顯示所有統計
     */
    public void showAllStatistics() {
        System.out.println("  📊 完整統計：");
        System.out.println("    等待人數：" + waitingQueue.size() + " 人");
        System.out.println("    已服務人數：" + servedHistory.size() + " 人");
        System.out.println("    總服務人數：" + totalServed + " 人");
        System.out.println("    總掛號人數：" + counter + " 人");
        
        System.out.println("  📊 科別統計：");
        if (departmentCount.isEmpty()) {
            System.out.println("    目前沒有等待的患者");
        } else {
            for (Map.Entry<String, Integer> entry : departmentCount.entrySet()) {
                System.out.println("    " + entry.getKey() + "：" + entry.getValue() + " 人");
            }
        }
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
     * 取得總服務人數
     */
    public int getTotalServed() {
        return totalServed;
    }
    
    /**
     * 依號碼搜尋患者（檢查重複）
     */
    private Patient findPatientByNumber(String ticketNumber) {
        for (Patient patient : waitingQueue) {
            if (patient.getTicketNumber().equals(ticketNumber)) {
                return patient;
            }
        }
        for (Patient patient : servedHistory) {
            if (patient.getTicketNumber().equals(ticketNumber)) {
                return patient;
            }
        }
        return null;
    }
}
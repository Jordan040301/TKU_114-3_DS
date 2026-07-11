import java.util.Scanner;

public class AtmAccountSystem {
    
    // ============================================
    // 主程式
    // ============================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int balance = 1000;           // 目前餘額
        int depositCount = 0;         // 成功存款次數
        int withdrawCount = 0;        // 成功提款次數
        int totalDeposit = 0;         // 總存款金額
        int totalWithdraw = 0;        // 總提款金額
        int option;
        
        System.out.println("====================================");
        System.out.println("      歡迎使用 ATM 帳戶系統         ");
        System.out.println("====================================");
        System.out.println("初始餘額：$1000");
        System.out.println("====================================");
        System.out.println();
        
        while (true) {
            printMenu();              // 顯示選單
            option = sc.nextInt();
            
            switch (option) {
                case 1:
                    // 查詢餘額
                    printBalance(balance);
                    break;
                    
                case 2:
                    // 存款
                    System.out.println();
                    System.out.println("【存款】");
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額：");
                    
                    // 執行存款
                    int newBalance = deposit(balance, depositAmount);
                    balance = newBalance;
                    
                    // 更新統計
                    depositCount++;
                    totalDeposit += depositAmount;
                    
                    System.out.println("✅ 存款成功！");
                    System.out.println("存入金額：$" + depositAmount);
                    System.out.println("目前餘額：$" + balance);
                    System.out.println();
                    break;
                    
                case 3:
                    // 提款
                    System.out.println();
                    System.out.println("【提款】");
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額：");
                    
                    // 檢查是否可以提款
                    if (!canWithdraw(balance, withdrawAmount)) {
                        System.out.println("❌ 餘額不足！");
                        System.out.println("目前餘額：$" + balance);
                        System.out.println("欲提款金額：$" + withdrawAmount);
                        System.out.println("不足金額：$" + (withdrawAmount - balance));
                        System.out.println();
                    } else {
                        // 執行提款
                        int newBalance2 = withdraw(balance, withdrawAmount);
                        balance = newBalance2;
                        
                        // 更新統計
                        withdrawCount++;
                        totalWithdraw += withdrawAmount;
                        
                        System.out.println("✅ 提款成功！");
                        System.out.println("提款金額：$" + withdrawAmount);
                        System.out.println("目前餘額：$" + balance);
                        System.out.println();
                    }
                    break;
                    
                case 4:
                    // 顯示目前操作統計
                    printSummary(balance, depositCount, withdrawCount, 
                                 totalDeposit, totalWithdraw);
                    break;
                    
                case 0:
                    // 離開，輸出完整摘要
                    System.out.println();
                    System.out.println("====================================");
                    System.out.println("          操作摘要                  ");
                    System.out.println("====================================");
                    printSummary(balance, depositCount, withdrawCount, 
                                 totalDeposit, totalWithdraw);
                    System.out.println("====================================");
                    System.out.println("      感謝使用 ATM 帳戶系統         ");
                    System.out.println("      祝您有美好的一天！           ");
                    System.out.println("====================================");
                    sc.close();
                    return;
                    
                default:
                    System.out.println();
                    System.out.println("❌ 無效選項，請輸入 0-4！");
                    System.out.println();
            }
        }
    }
    
    // ============================================
    // 方法 1：顯示選單
    // ============================================
    /**
     * 顯示 ATM 功能選單
     */
    public static void printMenu() {
        System.out.println("=== ATM 選單 ===");
        System.out.println("1. 查詢餘額");
        System.out.println("2. 存款");
        System.out.println("3. 提款");
        System.out.println("4. 顯示操作統計");
        System.out.println("0. 離開");
        System.out.println("================");
        System.out.print("請選擇功能（0-4）：");
    }
    
    // ============================================
    // 方法 2：讀取正數金額（含輸入驗證）
    // ============================================
    /**
     * 讀取並驗證金額（必須大於 0）
     * @param sc Scanner 物件
     * @param message 顯示的提示訊息
     * @return 輸入的正數金額
     */
    public static int readPositiveAmount(Scanner sc, String message) {
        int amount;
        while (true) {
            System.out.print(message);
            amount = sc.nextInt();
            if (amount > 0) {
                break;
            }
            System.out.println("❌ 金額必須大於 0，請重新輸入！");
        }
        return amount;
    }
    
    // ============================================
    // 方法 3：存款
    // ============================================
    /**
     * 執行存款功能
     * @param balance 目前餘額
     * @param amount 存款金額
     * @return 更新後的餘額
     */
    public static int deposit(int balance, int amount) {
        return balance + amount;
    }
    
    // ============================================
    // 方法 4：提款
    // ============================================
    /**
     * 執行提款功能
     * @param balance 目前餘額
     * @param amount 提款金額
     * @return 更新後的餘額
     */
    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }
    
    // ============================================
    // 方法 5：檢查是否可以提款
    // ============================================
    /**
     * 檢查提款金額是否不超過目前餘額
     * @param balance 目前餘額
     * @param amount 提款金額
     * @return true 表示可以提款，false 表示餘額不足
     */
    public static boolean canWithdraw(int balance, int amount) {
        return amount <= balance;
    }
    
    // ============================================
    // 方法 6：顯示餘額
    // ============================================
    /**
     * 顯示目前餘額
     * @param balance 目前餘額
     */
    public static void printBalance(int balance) {
        System.out.println();
        System.out.println("【查詢餘額】");
        System.out.println("目前餘額：$" + balance);
        System.out.println();
    }
    
    // ============================================
    // 方法 7：顯示操作摘要
    // ============================================
    /**
     * 顯示操作統計摘要
     * @param balance 最終餘額
     * @param depositCount 成功存款次數
     * @param withdrawCount 成功提款次數
     * @param totalDeposit 總存款金額
     * @param totalWithdraw 總提款金額
     */
    public static void printSummary(int balance, int depositCount, 
                                     int withdrawCount, int totalDeposit, 
                                     int totalWithdraw) {
        System.out.println("最終餘額：$" + balance);
        System.out.println("成功存款次數：" + depositCount + " 次");
        System.out.println("成功提款次數：" + withdrawCount + " 次");
        System.out.println("總存款金額：$" + totalDeposit);
        System.out.println("總提款金額：$" + totalWithdraw);
    }
}
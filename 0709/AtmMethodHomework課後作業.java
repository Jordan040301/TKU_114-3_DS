import java.util.Scanner;

public class AtmMethodHomework課後作業 {
    

    
    // ============================================
    // 主程式
    // ============================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int balance = 1000;      // 初始餘額
        int option;
        
        System.out.println("====================================");
        System.out.println("      歡迎使用 ATM 系統             ");
        System.out.println("====================================");
        System.out.println();
        
        // 選單可重複操作直至輸入 0
        while (true) {
            printMenu();          // 顯示選單
            option = sc.nextInt();
            
            if (option == 0) {
                System.out.println();
                System.out.println("====================================");
                System.out.println("      感謝使用 ATM 系統             ");
                System.out.println("      祝您有美好的一天！           ");
                System.out.println("====================================");
                break;
            }
            
            switch (option) {
                case 1:
                    printBalance(balance);
                    break;
                    
                case 2:
                    // 存款
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額：");
                    balance = deposit(balance, depositAmount);
                    System.out.println("✅ 存款成功！");
                    System.out.println("存入金額：$" + depositAmount);
                    System.out.println("目前餘額：$" + balance);
                    System.out.println();
                    break;
                    
                case 3:
                    // 提款
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額：");
                    
                    // 驗證：提款金額不能大於目前餘額
                    if (withdrawAmount > balance) {
                        System.out.println("❌ 餘額不足！");
                        System.out.println("目前餘額：$" + balance);
                        System.out.println("欲提款金額：$" + withdrawAmount);
                        System.out.println("不足金額：$" + (withdrawAmount - balance));
                        System.out.println();
                    } else {
                        balance = withdraw(balance, withdrawAmount);
                        System.out.println("✅ 提款成功！");
                        System.out.println("提款金額：$" + withdrawAmount);
                        System.out.println("目前餘額：$" + balance);
                        System.out.println();
                    }
                    break;
                    
                default:
                    System.out.println();
                    System.out.println("❌ 無效選項，請輸入 0-3！");
                    System.out.println();
            }
        }
        
        sc.close();
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
        System.out.println("0. 離開");
        System.out.println("================");
        System.out.print("請選擇功能（0-3）：");
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
    // 方法 3：存款（有回傳值）
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
    // 方法 4：提款（有回傳值）
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
    // 方法 5：顯示餘額
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
}

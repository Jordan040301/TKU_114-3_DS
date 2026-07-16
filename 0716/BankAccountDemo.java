/**
 * BankAccountDemo 類別 - 測試 BankAccount 類別
 * 建立兩個帳戶並測試存款、提款、轉帳功能
 */
public class BankAccountDemo {
    
    public static void main(String[] args) {
        System.out.println("=== 銀行帳戶測試程式 ===\n");
        
        // 建立兩個帳戶
        BankAccount account1 = new BankAccount("A001", "王小明", 10000);
        BankAccount account2 = new BankAccount("A002", "李小華", 5000);
        
        // 顯示初始帳戶資訊
        System.out.println("=== 初始帳戶資訊 ===");
        System.out.println("帳戶1：" + account1);
        System.out.println("帳戶2：" + account2);
        System.out.println();
        
        // 測試存款功能
        System.out.println("=== 測試存款 ===");
        testDeposit(account1);
        System.out.println();
        
        // 測試提款功能
        System.out.println("=== 測試提款 ===");
        testWithdraw(account1);
        System.out.println();
        
        // 測試成功轉帳
        System.out.println("=== 測試成功轉帳 ===");
        testSuccessfulTransfer(account1, account2);
        System.out.println();
        
        // 測試失敗轉帳
        System.out.println("=== 測試失敗轉帳 ===");
        testFailedTransfer(account1, account2);
        System.out.println();
        
        // 測試其他邊界情況
        System.out.println("=== 邊界情況測試 ===");
        testEdgeCases();
        System.out.println();
        
        // 顯示最終帳戶資訊
        System.out.println("=== 最終帳戶資訊 ===");
        System.out.println("帳戶1：" + account1);
        System.out.println("帳戶2：" + account2);
        System.out.println();
        
        System.out.println("=== 測試完成 ===");
    }
    
    /**
     * 測試存款功能
     */
    public static void testDeposit(BankAccount account) {
        System.out.println("測試存款功能（帳戶：" + account.getAccountHolder() + "）");
        System.out.println("目前餘額：" + account.getBalance());
        
        // 成功存款
        System.out.println("嘗試存款 3000：");
        account.deposit(3000);
        
        // 失敗存款（金額為0）
        System.out.println("嘗試存款 0：");
        account.deposit(0);
        
        // 失敗存款（金額為負數）
        System.out.println("嘗試存款 -500：");
        account.deposit(-500);
    }
    
    /**
     * 測試提款功能
     */
    public static void testWithdraw(BankAccount account) {
        System.out.println("測試提款功能（帳戶：" + account.getAccountHolder() + "）");
        System.out.println("目前餘額：" + account.getBalance());
        
        // 成功提款
        System.out.println("嘗試提款 2000：");
        account.withdraw(2000);
        
        // 失敗提款（金額為0）
        System.out.println("嘗試提款 0：");
        account.withdraw(0);
        
        // 失敗提款（金額為負數）
        System.out.println("嘗試提款 -100：");
        account.withdraw(-100);
        
        // 失敗提款（金額超過餘額）
        System.out.println("嘗試提款 20000：");
        account.withdraw(20000);
    }
    
    /**
     * 測試成功轉帳
     */
    public static void testSuccessfulTransfer(BankAccount from, BankAccount to) {
        System.out.println("測試成功轉帳");
        System.out.println("轉出帳戶：" + from.getAccountHolder() + "（餘額：" + from.getBalance() + "）");
        System.out.println("轉入帳戶：" + to.getAccountHolder() + "（餘額：" + to.getBalance() + "）");
        
        // 成功轉帳
        System.out.println("嘗試轉帳 3000 從 " + from.getAccountHolder() + " 到 " + to.getAccountHolder());
        from.transferTo(to, 3000);
    }
    
    /**
     * 測試失敗轉帳
     */
    public static void testFailedTransfer(BankAccount from, BankAccount to) {
        System.out.println("測試失敗轉帳");
        System.out.println("轉出帳戶：" + from.getAccountHolder() + "（餘額：" + from.getBalance() + "）");
        System.out.println("轉入帳戶：" + to.getAccountHolder() + "（餘額：" + to.getBalance() + "）");
        
        // 失敗轉帳（金額為0）
        System.out.println("嘗試轉帳 0：");
        from.transferTo(to, 0);
        
        // 失敗轉帳（金額為負數）
        System.out.println("嘗試轉帳 -100：");
        from.transferTo(to, -100);
        
        // 失敗轉帳（金額超過餘額）
        System.out.println("嘗試轉帳 50000：");
        from.transferTo(to, 50000);
    }
    
    /**
     * 測試邊界情況
     */
    public static void testEdgeCases() {
        // 測試轉帳給自己
        BankAccount account = new BankAccount("A003", "張大偉", 1000);
        System.out.println("測試轉帳給自己：");
        account.transferTo(account, 100);
        
        // 測試轉帳給 null
        System.out.println("測試轉帳給 null：");
        account.transferTo(null, 100);
        
        // 測試建立負數初始餘額
        System.out.println("測試負數初始餘額：");
        BankAccount negativeAccount = new BankAccount("A004", "測試", -1000);
        System.out.println("建立結果：" + negativeAccount);
    }
}
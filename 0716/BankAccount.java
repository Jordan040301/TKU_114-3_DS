/**
 * BankAccount 類別 - 表示銀行帳戶
 * 包含帳號、戶名、餘額及相關金融操作
 */
public class BankAccount {
    
    // 私有欄位
    private String accountNumber;
    private String accountHolder;
    private int balance;
    
    /**
     * 建構子 - 建立銀行帳戶
     * @param accountNumber 帳號
     * @param accountHolder 戶名
     * @param initialBalance 初始餘額（必須大於0）
     */
    public BankAccount(String accountNumber, String accountHolder, int initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
            System.out.println("警告：初始餘額不能為負數，已設為0");
        }
    }
    
    /**
     * 取得帳號
     * @return 帳號
     */
    public String getAccountNumber() {
        return accountNumber;
    }
    
    /**
     * 取得戶名
     * @return 戶名
     */
    public String getAccountHolder() {
        return accountHolder;
    }
    
    /**
     * 取得餘額
     * @return 餘額
     */
    public int getBalance() {
        return balance;
    }
    
    /**
     * 存款 - 金額必須大於0
     * @param amount 存款金額
     * @return 成功回傳 true，失敗回傳 false
     */
    public boolean deposit(int amount) {
        if (amount <= 0) {
            System.out.println("錯誤：存款金額必須大於0");
            return false;
        }
        balance += amount;
        System.out.println("存款成功：+" + amount + "，目前餘額：" + balance);
        return true;
    }
    
    /**
     * 提款 - 金額必須大於0且不能超過餘額
     * @param amount 提款金額
     * @return 成功回傳 true，失敗回傳 false
     */
    public boolean withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("錯誤：提款金額必須大於0");
            return false;
        }
        if (amount > balance) {
            System.out.println("錯誤：餘額不足！需要：" + amount + "，目前餘額：" + balance);
            return false;
        }
        balance -= amount;
        System.out.println("提款成功：-" + amount + "，目前餘額：" + balance);
        return true;
    }
    
    /**
     * 轉帳 - 金額必須大於0且不能超過餘額
     * @param target 目標帳戶
     * @param amount 轉帳金額
     * @return 成功回傳 true，失敗回傳 false
     */
    public boolean transferTo(BankAccount target, int amount) {
        // 檢查目標帳戶是否為 null
        if (target == null) {
            System.out.println("錯誤：目標帳戶不存在");
            return false;
        }
        
        // 檢查是否轉帳給自己
        if (this == target) {
            System.out.println("錯誤：不能轉帳給自己");
            return false;
        }
        
        // 檢查金額
        if (amount <= 0) {
            System.out.println("錯誤：轉帳金額必須大於0");
            return false;
        }
        
        // 檢查餘額是否足夠
        if (amount > this.balance) {
            System.out.println("錯誤：轉帳失敗！餘額不足！需要：" + amount + 
                             "，目前餘額：" + this.balance);
            return false;
        }
        
        // 執行轉帳
        this.balance -= amount;
        target.balance += amount;
        System.out.println("轉帳成功：" + amount + " 從 " + this.accountHolder + 
                         " 到 " + target.accountHolder);
        System.out.println("轉出方餘額：" + this.balance + "，轉入方餘額：" + target.balance);
        return true;
    }
    
    /**
     * 顯示帳戶資訊
     * @return 帳戶詳細資訊
     */
    @Override
    public String toString() {
        return String.format(
            "BankAccount[帳號=%s, 戶名=%s, 餘額=%d]",
            accountNumber, accountHolder, balance
        );
    }
}
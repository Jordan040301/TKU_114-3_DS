public class Transaction {
    private String transactionId;
    private String account;
    private double amount;
    private int timeSequence;

    // 構造方法
    public Transaction(String transactionId, String account, double amount, int timeSequence) {
        this.transactionId = transactionId;
        this.account = account;
        this.amount = amount;
        this.timeSequence = timeSequence;
    }

    // Getter 方法
    public String getTransactionId() {
        return transactionId;
    }

    public String getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public int getTimeSequence() {
        return timeSequence;
    }

    // Setter 方法
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTimeSequence(int timeSequence) {
        this.timeSequence = timeSequence;
    }

    // 重寫 toString 方法
    @Override
    public String toString() {
        return String.format("交易{序號='%s', 帳戶='%s', 金額=%.0f, 時間序號=%d}", 
                           transactionId, account, amount, timeSequence);
    }
}
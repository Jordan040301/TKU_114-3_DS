public class TransactionSortingSystem {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║              ★ 交易記錄排序系統 ★                               ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");

        // 建立交易記錄（包含金額相同的情況）
        Transaction[] transactions = {
            new Transaction("T001", "A1001", 15000, 1),
            new Transaction("T002", "A1002", 25000, 2),
            new Transaction("T003", "A1003", 15000, 3),      // 與 T001 金額相同
            new Transaction("T004", "A1004", 32000, 4),
            new Transaction("T005", "A1005", 25000, 5),      // 與 T002 金額相同
            new Transaction("T006", "A1006", 15000, 6),      // 與 T001 金額相同
            new Transaction("T007", "A1007", 40000, 7),
            new Transaction("T008", "A1008", 25000, 8),      // 與 T002 金額相同
            new Transaction("T009", "A1009", 18000, 9),
            new Transaction("T010", "A1010", 15000, 10),     // 與 T001 金額相同
            new Transaction("T011", "A1011", 32000, 11),     // 與 T004 金額相同
            new Transaction("T012", "A1012", 22000, 12)
        };

        System.out.println("\n📋 原始交易記錄：");
        displayTransactions(transactions, "原始順序");

        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║              🔄 開始排序（金額降冪，同金額時間升冪）            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
        
        // 執行排序（帶追蹤）
        insertionSortByAmountAndTime(transactions);

        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║              📊 最終排序結果                                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
        displayTransactionsWithRank(transactions, "金額降冪，同金額時間升冪");

        // 驗證相同金額的排序
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║              ✅ 穩定性驗證                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
        verifySameAmountOrder(transactions);
    }

    /**
     * 插入排序：金額降冪，金額相同時時間序號升冪
     */
    public static void insertionSortByAmountAndTime(Transaction[] transactions) {
        int comparisonCount = 0;
        int shiftCount = 0;

        for (int index = 1; index < transactions.length; index++) {
            Transaction key = transactions[index];
            int position = index - 1;

            System.out.println("\n第 " + index + " 回合：key = " + key.getTransactionId() + 
                             " (金額: " + key.getAmount() + ", 時間: " + key.getTimeSequence() + ")");

            // 比較規則：金額降冪，同金額時間升冪
            while (position >= 0 && shouldSwap(transactions[position], key)) {
                comparisonCount++;
                System.out.println("  比較：" + transactions[position].getTransactionId() + 
                                 " (金額 " + transactions[position].getAmount() + 
                                 ", 時間 " + transactions[position].getTimeSequence() + 
                                 ") > " + key.getTransactionId() + 
                                 " (金額 " + key.getAmount() + 
                                 ", 時間 " + key.getTimeSequence() + 
                                 ") → 向後移動");

                transactions[position + 1] = transactions[position];
                shiftCount++;
                position--;
            }

            // 記錄最後一次比較
            if (position >= 0) {
                comparisonCount++;
                System.out.println("  比較：" + transactions[position].getTransactionId() + 
                                 " (金額 " + transactions[position].getAmount() + 
                                 ", 時間 " + transactions[position].getTimeSequence() + 
                                 ") ≤ " + key.getTransactionId() + 
                                 " (金額 " + key.getAmount() + 
                                 ", 時間 " + key.getTimeSequence() + 
                                 ") → 插入在此之後");
            }

            // 插入 key
            int insertPosition = position + 1;
            transactions[insertPosition] = key;
            System.out.println("  插入位置：" + insertPosition);
            System.out.println("  目前排序：");
            displayTransactionsBrief(transactions);
        }

        System.out.println("\n📊 排序統計：");
        System.out.println("  總比較次數：" + comparisonCount);
        System.out.println("  總移動次數：" + shiftCount);
    }

    /**
     * 判斷是否需要交換（position 的交易是否應該排在 key 之後）
     * @param current 當前位置的交易
     * @param key 要插入的交易
     * @return true 表示 current 應該排在 key 後面，需要移動
     */
    public static boolean shouldSwap(Transaction current, Transaction key) {
        // 金額較小者排在後面（降冪）
        if (current.getAmount() < key.getAmount()) {
            return true;
        }
        // 金額相同時，時間序號較大者排在後面（升冪）
        if (current.getAmount() == key.getAmount() && 
            current.getTimeSequence() > key.getTimeSequence()) {
            return true;
        }
        return false;
    }

    /**
     * 顯示完整交易記錄
     */
    public static void displayTransactions(Transaction[] transactions, String title) {
        System.out.println("\n┌──────────┬────────────┬────────────┬──────────────┐");
        System.out.printf("│ %-8s │ %-10s │ %-10s │ %-12s │\n", 
                         "交易序號", "帳戶", "金額", "時間序號");
        System.out.println("├──────────┼────────────┼────────────┼──────────────┤");
        
        for (Transaction t : transactions) {
            System.out.printf("│ %-8s │ %-10s │ %10.0f │ %12d │\n", 
                            t.getTransactionId(),
                            t.getAccount(),
                            t.getAmount(),
                            t.getTimeSequence());
        }
        System.out.println("└──────────┴────────────┴────────────┴──────────────┘");
        System.out.println("排序方式：" + title);
    }

    /**
     * 簡短顯示交易記錄（顯示序號、金額和時間）
     */
    public static void displayTransactionsBrief(Transaction[] transactions) {
        System.out.print("  [");
        for (int i = 0; i < transactions.length; i++) {
            System.out.print(transactions[i].getTransactionId() + 
                           "(" + transactions[i].getAmount() + 
                           "," + transactions[i].getTimeSequence() + ")");
            if (i < transactions.length - 1) {
                System.out.print(" → ");
            }
        }
        System.out.println("]");
    }

    /**
     * 顯示交易記錄（含名次）
     */
    public static void displayTransactionsWithRank(Transaction[] transactions, String title) {
        System.out.println("\n┌──────┬──────────┬────────────┬────────────┬──────────────┐");
        System.out.printf("│ %-4s │ %-8s │ %-10s │ %-10s │ %-12s │\n", 
                         "名次", "交易序號", "帳戶", "金額", "時間序號");
        System.out.println("├──────┼──────────┼────────────┼────────────┼──────────────┤");
        
        int rank = 1;
        double previousAmount = -1.0;
        int previousTime = -1;
        
        for (int i = 0; i < transactions.length; i++) {
            Transaction t = transactions[i];
            
            // 檢查是否與前一名相同（金額和時間都相同）
            if (i > 0) {
                Transaction prev = transactions[i - 1];
                if (t.getAmount() == prev.getAmount() && 
                    t.getTimeSequence() == prev.getTimeSequence()) {
                    // 並列名次
                } else {
                    rank = i + 1;
                }
            }
            
            System.out.printf("│ %4d │ %-8s │ %-10s │ %10.0f │ %12d │\n", 
                            rank,
                            t.getTransactionId(),
                            t.getAccount(),
                            t.getAmount(),
                            t.getTimeSequence());
        }
        System.out.println("└──────┴──────────┴────────────┴────────────┴──────────────┘");
        System.out.println("排序方式：" + title);
        System.out.println("總交易筆數：" + transactions.length);
    }

    /**
     * 驗證相同金額的交易是否按時間序號正確排序
     */
    public static void verifySameAmountOrder(Transaction[] transactions) {
        System.out.println("\n🔍 檢查相同金額的交易排序：");
        
        boolean hasSameAmount = false;
        for (int i = 0; i < transactions.length - 1; i++) {
            if (transactions[i].getAmount() == transactions[i + 1].getAmount()) {
                hasSameAmount = true;
                System.out.println("\n💰 金額 " + transactions[i].getAmount() + " 的交易群組：");
                
                // 找出所有相同金額的交易
                int start = i;
                while (i < transactions.length && 
                       transactions[i].getAmount() == transactions[start].getAmount()) {
                    System.out.println("   " + transactions[i].getTransactionId() + 
                                     " (時間: " + transactions[i].getTimeSequence() + ")");
                    i++;
                }
                i--; // 調整索引
            }
        }
        
        if (!hasSameAmount) {
            System.out.println("  沒有相同金額的交易");
        }
        
        // 驗證排序規則
        System.out.println("\n✅ 排序規則驗證：");
        boolean valid = true;
        for (int i = 0; i < transactions.length - 1; i++) {
            Transaction current = transactions[i];
            Transaction next = transactions[i + 1];
            
            // 檢查金額是否降冪
            if (current.getAmount() < next.getAmount()) {
                System.out.println("   ❌ 錯誤：金額 " + current.getAmount() + 
                                 " < " + next.getAmount() + " (應為降冪)");
                valid = false;
            }
            
            // 檢查相同金額時時間是否升冪
            if (current.getAmount() == next.getAmount() && 
                current.getTimeSequence() > next.getTimeSequence()) {
                System.out.println("   ❌ 錯誤：相同金額 " + current.getAmount() + 
                                 "，時間 " + current.getTimeSequence() + 
                                 " > " + next.getTimeSequence() + " (應為升冪)");
                valid = false;
            }
        }
        
        if (valid) {
            System.out.println("   ✅ 所有交易排序規則正確！");
            System.out.println("   📌 金額降冪（高 → 低）");
            System.out.println("   📌 相同金額時，時間序號升冪（小 → 大）");
        }
    }
}
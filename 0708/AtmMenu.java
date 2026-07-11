import java.util.Scanner;

public class AtmMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int balance = 1000;
        int todayWithdrawTotal = 0;
        String history = "";
        int transactionCount = 0;
        int option;
        
        System.out.println("====================================");
        System.out.println("      歡迎使用 ATM 系統             ");
        System.out.println("====================================");
        System.out.println("【注意事項】");
        System.out.println("  • 最低餘額限制：$1,000");
        System.out.println("  • 提款單位：$1,000 元（千元為單位）");
        System.out.println("  • 單日提款上限：$50,000");
        System.out.println("====================================");
        System.out.println();
        
        while (true) {
            System.out.println("=== ATM 選單 ===");
            System.out.println("1. 查詢餘額");
            System.out.println("2. 存款");
            System.out.println("3. 提款");
            System.out.println("4. 交易記錄");
            System.out.println("5. 查詢今日提款累計");
            System.out.println("0. 離開");
            System.out.println("================");
            System.out.print("請選擇功能（0-5）：");
            
            option = sc.nextInt();
            
            switch (option) {
                case 1:
                    System.out.println();
                    System.out.println("【查詢餘額】");
                    System.out.println("目前餘額：$" + balance);
                    System.out.println("最低餘額限制：$1,000");
                    System.out.println("可提款上限：$" + (balance - 1000));
                    System.out.println("今日已提款：$" + todayWithdrawTotal);
                    System.out.println("今日尚可提款：$" + (50000 - todayWithdrawTotal));
                    System.out.println();
                    break;
                    
                case 2:
                    System.out.println();
                    System.out.println("【存款】");
                    while (true) {
                        System.out.print("請輸入存款金額（必須大於 0）：");
                        int deposit = sc.nextInt();
                        if (deposit > 0) {
                            balance += deposit;
                            transactionCount++;
                            history += "第" + transactionCount + "筆：存款 $" + deposit + "，餘額 $" + balance + "\n";
                            System.out.println("✅ 存款成功！");
                            System.out.println("存入金額：$" + deposit);
                            System.out.println("目前餘額：$" + balance);
                            System.out.println();
                            break;
                        } else {
                            System.out.println("❌ 存款金額必須大於 0！");
                            System.out.print("是否重新輸入？(y/n)：");
                            String choice = sc.next();
                            if (choice.equalsIgnoreCase("n")) {
                                System.out.println("已取消存款操作\n");
                                break;
                            }
                            System.out.println();
                        }
                    }
                    break;
                    
                case 3:
                    System.out.println();
                    System.out.println("【提款】");
                    System.out.println("目前餘額：$" + balance);
                    System.out.println("最低餘額限制：$1,000");
                    System.out.println("可提款上限：$" + (balance - 1000));
                    System.out.println("提款單位：千元（1000、2000、3000...）");
                    System.out.println();
                    
                    while (true) {
                        System.out.print("請輸入提款金額（千元為單位，且保留至少 $1,000）：");
                        int withdraw = sc.nextInt();
                        
                        if (withdraw <= 0) {
                            System.out.println("❌ 提款金額必須大於 0！");
                            System.out.print("是否重新輸入？(y/n)：");
                            String choice = sc.next();
                            if (choice.equalsIgnoreCase("n")) {
                                System.out.println("已取消提款操作\n");
                                break;
                            }
                            System.out.println();
                            continue;
                        }
                        
                        if (withdraw % 1000 != 0) {
                            System.out.println("❌ 提款金額必須以千元為單位（1000、2000、3000...）！");
                            System.out.print("是否重新輸入？(y/n)：");
                            String choice = sc.next();
                            if (choice.equalsIgnoreCase("n")) {
                                System.out.println("已取消提款操作\n");
                                break;
                            }
                            System.out.println();
                            continue;
                        }
                        
                        if (balance - withdraw < 1000) {
                            System.out.println("❌ 提款後餘額不得低於 $1,000！");
                            System.out.println("目前餘額：$" + balance);
                            System.out.println("請調整提款金額（最多 $" + (balance - 1000) + "）");
                            System.out.print("是否重新輸入？(y/n)：");
                            String choice = sc.next();
                            if (choice.equalsIgnoreCase("n")) {
                                System.out.println("已取消提款操作\n");
                                break;
                            }
                            System.out.println();
                            continue;
                        }
                        
                        if (todayWithdrawTotal + withdraw > 50000) {
                            System.out.println("❌ 今日提款已達上限！");
                            System.out.println("今日已提款：$" + todayWithdrawTotal);
                            System.out.println("今日尚可提款：$" + (50000 - todayWithdrawTotal));
                            System.out.print("是否重新輸入？(y/n)：");
                            String choice = sc.next();
                            if (choice.equalsIgnoreCase("n")) {
                                System.out.println("已取消提款操作\n");
                                break;
                            }
                            System.out.println();
                            continue;
                        }
                        
                        System.out.println();
                        System.out.println("✅ 提款金額 $" + withdraw + " 可正常提領");
                        System.out.print("是否需要換成百元鈔票？(y/n)：");
                        String exchangeChoice = sc.next();
                        
                        if (exchangeChoice.equalsIgnoreCase("y")) {
                            System.out.println();
                            System.out.println("【換鈔結果】");
                            System.out.println("提款金額：$" + withdraw);
                            System.out.println("可換成 " + (withdraw / 100) + " 張百元鈔票");
                            System.out.println();
                        } else {
                            System.out.println();
                            System.out.println("【提款結果】");
                            System.out.println("提款金額：$" + withdraw);
                            System.out.println("以原鈔票面額支付");
                            System.out.println();
                        }
                        
                        balance -= withdraw;
                        todayWithdrawTotal += withdraw;
                        transactionCount++;
                        history += "第" + transactionCount + "筆：提款 $" + withdraw + "，餘額 $" + balance + "\n";
                        
                        System.out.println("✅ 提款成功！");
                        System.out.println("提款金額：$" + withdraw);
                        System.out.println("目前餘額：$" + balance);
                        System.out.println();
                        break;
                    }
                    break;
                    
                case 4:
                    System.out.println();
                    System.out.println("【交易記錄】");
                    if (transactionCount == 0) {
                        System.out.println("目前尚無交易記錄");
                    } else {
                        System.out.println("-------------------");
                        System.out.println(history);
                        System.out.println("-------------------");
                        System.out.println("總交易筆數：" + transactionCount);
                    }
                    System.out.println();
                    break;
                    
                case 5:
                    System.out.println();
                    System.out.println("【今日提款累計】");
                    System.out.println("今日已提款總額：$" + todayWithdrawTotal);
                    System.out.println("今日可提款餘額：$" + (50000 - todayWithdrawTotal));
                    System.out.println("單日上限：$50,000");
                    System.out.println();
                    break;
                    
                case 0:
                    System.out.println();
                    System.out.println("====================================");
                    System.out.println("      感謝使用 ATM 系統             ");
                    System.out.println("      祝您有美好的一天！           ");
                    System.out.println("====================================");
                    sc.close();
                    return;
                    
                default:
                    System.out.println();
                    System.out.println("❌ 無效選項，請輸入 0-5！");
                    System.out.println();
                    break;
            }
            
            System.out.print("是否繼續操作？(y/n)：");
            String choice = sc.next();
            System.out.println();
            
            if (choice.equalsIgnoreCase("n")) {
                System.out.println("====================================");
                System.out.println("      感謝使用 ATM 系統             ");
                System.out.println("      祝您有美好的一天！           ");
                System.out.println("====================================");
                sc.close();
                return;
            }
        }
    }
}
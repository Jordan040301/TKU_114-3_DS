import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== 學生成績管理系統 ===");
        System.out.println();
        
        // 1. 輸入姓名
        System.out.print("請輸入學生姓名：");
        String name = sc.nextLine();
        
        // 2. 輸入三科成績（加入驗證）
        double javaScore = getValidScore(sc, "Java");
        double englishScore = getValidScore(sc, "英文");
        double mathScore = getValidScore(sc, "數學");
        
        // 3. 計算平均分數
        double average = (javaScore + englishScore + mathScore) / 3;
        
        // 4. 判斷及格或不及格
        String passStatus = (average >= 60) ? "及格" : "不及格";
        
        // 5. 判斷等第
        String grade;
        if (average >= 90) {
            grade = "A（優等）";
        } else if (average >= 80) {
            grade = "B（甲等）";
        } else if (average >= 70) {
            grade = "C（乙等）";
        } else if (average >= 60) {
            grade = "D（丙等）";
        } else {
            grade = "F（不及格）";
        }
        
        // 顯示學生資訊
        displayStudentInfo(name, javaScore, englishScore, mathScore, average);
        
        // 7. 使用 while 讓選單重複操作
        while (true) {
            displayMenu();
            int option = sc.nextInt();
            
            // 6. 使用 switch 建立選單
            switch (option) {
                case 1:
                    System.out.println("\n【平均分數】");
                    System.out.printf("%s 的平均分數為：%.2f 分\n\n", name, average);
                    break;
                    
                case 2:
                    System.out.println("\n【及格狀態】");
                    System.out.println(name + " 的狀態為：" + passStatus + "\n");
                    break;
                    
                case 3:
                    System.out.println("\n【等第】");
                    System.out.println(name + " 的等第為：" + grade + "\n");
                    break;
                    
                case 0:
                    System.out.println("\n感謝使用學生成績管理系統！");
                    System.out.println("程式結束！");
                    sc.close();
                    return;
                    
                default:
                    System.out.println("\n❌ 無效選項，請輸入 0-3！\n");
            }
        }
    }
    
    // 驗證分數是否在 0-100 之間
    public static double getValidScore(Scanner sc, String subject) {
        double score;
        while (true) {
            System.out.print("請輸入 " + subject + " 成績（0-100）：");
            score = sc.nextDouble();
            if (score >= 0 && score <= 100) {
                break;
            }
            System.out.println("❌ 分數必須在 0 到 100 之間，請重新輸入！");
        }
        return score;
    }
    
    // 顯示學生資訊
    public static void displayStudentInfo(String name, double java, double english, 
                                          double math, double average) {
        System.out.println("\n=== 學生資料已建立 ===");
        System.out.println("姓名：" + name);
        System.out.println("Java：" + java + " 分");
        System.out.println("英文：" + english + " 分");
        System.out.println("數學：" + math + " 分");
        System.out.printf("平均：%.2f 分\n", average);
        System.out.println("========================");
        System.out.println();
    }
    
    // 顯示選單
    public static void displayMenu() {
        System.out.println("=== 功能選單 ===");
        System.out.println("1. 顯示平均分數");
        System.out.println("2. 顯示及格狀態");
        System.out.println("3. 顯示等第");
        System.out.println("0. 離開");
        System.out.print("請輸入選項（0-3）：");
    }
}
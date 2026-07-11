import java.util.Scanner;

public class GradeMethod {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== 學生成績計算系統 ===");
        System.out.println("1. 執行預設測試");
        System.out.println("2. 自行輸入成績");
        System.out.print("請選擇（1或2）：");
        
        int option = sc.nextInt();
        sc.nextLine();  // 清除換行
        
        if (option == 1) {
            // 執行預設測試
            System.out.println("\n=== 預設測試 ===");
            testGrade(95, 92);
            testGrade(85, 90);
            testGrade(75, 70);
            testGrade(65, 60);
            testGrade(50, 55);
        } else {
            // 使用者輸入
            String choice;
            System.out.println();
            
            do {
                System.out.print("請輸入學生姓名：");
                String name = sc.nextLine();

                System.out.print("請輸入 JavaScript 成績：");
                int js = sc.nextInt();

                System.out.print("請輸入數學成績：");
                int math = sc.nextInt();
                sc.nextLine();

                double average = calculateAverage(js, math);
                String grade = getGrade(average);

                System.out.println();
                System.out.println("=== 成績單 ===");
                System.out.println("姓名：" + name);
                System.out.println("JavaScript：" + js);
                System.out.println("數學：" + math);
                System.out.println("-------------");
                System.out.printf("平均：%.2f\n", average);
                System.out.println("等第：" + grade);
                System.out.println("=============");
                System.out.println();

                System.out.print("是否繼續輸入？（y/n）：");
                choice = sc.nextLine();

            } while (choice.equalsIgnoreCase("y"));
        }

        System.out.println("程式結束，感謝使用！");
        sc.close();
    }

    public static void testGrade(int js, int math) {
        double average = calculateAverage(js, math);
        String grade = getGrade(average);

        System.out.println("JavaScript：" + js + "，數學：" + math);
        System.out.printf("平均：%.2f，等第：%s\n", average, grade);
        System.out.println("------------------------");
    }

    public static double calculateAverage(int javascriptScore, int mathScore) {
        return (javascriptScore + mathScore) / 2.0;
    }

    public static String getGrade(double average) {
        if (average >= 90) {
            return "A（優秀）";
        } else if (average >= 80) {
            return "B（良好）";
        } else if (average >= 70) {
            return "C（中等）";
        } else if (average >= 60) {
            return "D（及格）";
        } else {
            return "F（不及格，需要加強）";
        }
    }
}
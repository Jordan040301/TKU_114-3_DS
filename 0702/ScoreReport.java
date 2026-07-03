import java.util.Scanner;

/**
 * 成绩报表程序
 * 作业要求：ScoreReport.java
 * 班级名称：ScoreReport
 * 功能：输入姓名和三科成绩，计算并显示平均分
 */

public class ScoreReport {
      public static void main(String[] args) {
        // 创建Scanner对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入姓名
        System.out.print("請輸入姓名：");
        String name = scanner.nextLine();

        // 提示用户输入Java成绩
        System.out.print("請輸入 Java 成績：");
        double javaScore = scanner.nextDouble();

        // 提示用户输入英语成绩
        System.out.print("請輸入 English 成績：");
        double englishScore = scanner.nextDouble();

        // 提示用户输入数学成绩
        System.out.print("請輸入 Math 成績：");
        double mathScore = scanner.nextDouble();

        // 计算平均分（使用double类型）
        double average = (javaScore + englishScore + mathScore) / 3.0;

        // 输出成绩报表
        System.out.println("\n=== 成績報表 ===");
        System.out.println("姓名：" + name);
        System.out.println("Java：" + javaScore);
        System.out.println("English：" + englishScore);
        System.out.println("Math：" + mathScore);
        System.out.println("平均：" + average);

        // 关闭Scanner对象，释放资源
        scanner.close();
    }
}

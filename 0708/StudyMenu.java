import java.util.Scanner;

public class StudyMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("=== studyMenu ===");
            System.out.println("1. Review java");
            System.out.println("2. Practice loops");
            System.out.println("3. push to Github");
            System.out.println("0. Exit");
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Hello java");
                    break;
                case 2:
                    System.out.println("Practice loops");
                    break;
                case 3:
                    System.out.println("push to github");
                    break;
                case 0:
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Unknown option");
            }
        }

        sc.close();
    }
}

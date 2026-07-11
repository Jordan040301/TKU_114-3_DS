import java.util.Scanner;

public class SimpleMenu換printMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            printMenu();
            option = sc.nextInt();
            handleOption(option);        // ⭐ 呼叫 handleOption 處理選項
        }
        sc.close();
    }

    // ============================================
    // 方法 1：顯示選單
    // ============================================
    public static void printMenu() {
        System.out.println("== Menu ==");
        System.out.println("1. Say hello");
        System.out.println("2. Say Java");
        System.out.println("0. Exit");
        System.out.print("請輸入選項：");
    }

    // ============================================
    // 方法 2：處理選項
    // ============================================
    public static void handleOption(int option) {
        switch (option) {
            case 1:
                System.out.println("Hello");
                break;
            case 2:
                System.out.println("Java");
                break;
            case 0:
                System.out.println("Bye");
                break;
            default:
                System.out.println("Unknown option");
        }
    }
}
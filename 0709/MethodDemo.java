import java.util.Scanner;

public class MethodDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入你的名字：");
        String name1 = sc.nextLine();

        sayHello(name1);

        sc.close();
    }

    public static void sayHello(String name1) {
        System.out.println("Hello " + name1);
    }
}
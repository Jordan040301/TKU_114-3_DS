import java.util.Scanner;

public class EvenOdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("請輸入一個整數：");
        int number = sc.nextInt();
        
        System.out.println("Number: " + number);
        
        if (number % 2 == 0) {
            System.out.println(number + " is even");
            System.out.println(number + " % 2 = " + (number % 2));
        } else {
            System.out.println(number + " is odd");
            System.out.println(number + " % 2 = " + (number % 2));
        }
        
        sc.close();
    }
}

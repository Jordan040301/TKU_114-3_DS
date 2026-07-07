import java.util.Scanner;

public class TemperatureLevel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("請輸入溫度（°C）：");
        double temperature = sc.nextDouble();
        
        System.out.println("Temperature: " + temperature + "°C");
        
        if (temperature < 15) {
            System.out.println("Cold");
        } else if (temperature >= 15 && temperature < 28) {
            System.out.println("Comfortable");
        } else {
            System.out.println("Hot");
        }
        
        sc.close();
    }
}

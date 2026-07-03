import java.util.Scanner;

public class bmi {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 輸入資料
        System.out.print("請輸入姓名：");
        String name = sc.nextLine();

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();

        System.out.print("請輸入身高(公尺)：");
        double height = sc.nextDouble();

        System.out.print("請輸入體重(公斤)：");
        double weight = sc.nextDouble();

        // BMI 計算
        double bmi = weight / (height * height);

        // 輸出結果
        System.out.println("\n===== 個人健康數據 =====");
        System.out.println("姓名：" + name);
        System.out.println("年齡：" + age + " 歲");
        System.out.println("身高：" + height + " 公尺");
        System.out.println("體重：" + weight + " 公斤");
        System.out.printf("BMI：%.2f\n", bmi);

        // BMI 判斷
        if (bmi < 18.5) {
            System.out.println("體位：過輕");
        } else if (bmi < 24) {
            System.out.println("體位：正常");
        } else if (bmi < 27) {
            System.out.println("體位：過重");
        } else if (bmi < 30) {
            System.out.println("體位：輕度肥胖");
        } else if (bmi < 35) {
            System.out.println("體位：中度肥胖");
        } else {
            System.out.println("體位：重度肥胖");
        }

        sc.close();
    }
}
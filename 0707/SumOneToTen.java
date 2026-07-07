public class SumOneToTen {
     public static void main(String[] args) {
        int sum = 0;
        
        System.out.println("計算 1 到 10 的總和");
        System.out.println("====================");
        
        for (int i = 1; i <= 10; i++) {
            sum += i;  // sum = sum + i
            System.out.println("i = " + i + "，目前總和 = " + sum);
        }
        
        System.out.println("====================");
        System.out.println("Sum: " + sum);
    }
}

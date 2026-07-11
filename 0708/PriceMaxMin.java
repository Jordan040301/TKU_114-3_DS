import java.util.Scanner;

public class PriceMaxMin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("請輸入商品數量：");
        int n = sc.nextInt();
        
        String[] names = new String[n];
        int[] prices = new int[n];
        
        System.out.println();
        for (int i = 0; i < n;i++) {
            System.out.print("請輸入第 " + (i+1) + " 個商品名稱：");
            names[i] = sc.next();
            
            System.out.print("請輸入 " + names[i] + " 的價格：");
            prices[i] = sc.nextInt();
            System.out.println();
        }
        
        int max = prices[0];
        int min = prices[0];
        int maxIndex = 0;
        int minIndex = 0;
        
        for (int i = 1; i < n; i++)
         {
            if (prices[i] > max) {
                max = prices[i];
                maxIndex = i;
            }
            if (prices[i] < min) {
                min = prices[i];
                minIndex = i;
            }
        }
        
        System.out.println("=== 商品價格分析 ===");
        System.out.println("最高價格：" + names[maxIndex] + " - $" + max);
        System.out.println("最低價格：" + names[minIndex] + " - $" + min);
        System.out.println("====================");
        
        sc.close();
    }
}
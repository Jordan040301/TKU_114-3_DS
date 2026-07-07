public class SkipEven {
    public static void main(String[] args) {
        System.out.println("印出 1 到 10 的奇數（跳過偶數）");
        System.out.println("=============================");
        
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println("i = " + i + " 是偶數，跳過！");
                continue;
            }
            System.out.println("✓ " + i + " 是奇數，輸出");
        }
        
        System.out.println("=============================");
        System.out.println("程式結束！");
    }
}
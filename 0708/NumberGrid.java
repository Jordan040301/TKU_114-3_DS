public class NumberGrid {
    public static void main(String[] args) {

        System.out.println("=== "  + "列 x "  + "欄 數字表 ===");
        System.out.println();
        
        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 5; col++) {
                System.out.printf("%2d ", col);  // 每個數字佔2格，對齊更整齊
            }
            System.out.println();
        }
    }
}
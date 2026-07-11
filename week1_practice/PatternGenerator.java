import java.util.Scanner;

public class PatternGenerator {
    
    // ============================================
    // 主程式
    // ============================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;
        
        System.out.println("====================================");
        System.out.println("      圖形與表格產生器              ");
        System.out.println("====================================");
        System.out.println();
        
        while (true) {
            printMenu();           // 顯示選單
            option = sc.nextInt();
            
            switch (option) {
                case 1:
                    // 九九乘法表
                    System.out.println();
                    System.out.println("【九九乘法表】");
                    printMultiplicationTable();
                    break;
                    
                case 2:
                    // 正三角形星號
                    System.out.println();
                    System.out.println("【正三角形】");
                    int height = readPositiveInt(sc, "請輸入三角形高度：");
                    printTriangle(height);
                    break;
                    
                case 3:
                    // 倒三角形星號
                    System.out.println();
                    System.out.println("【倒三角形】");
                    int height2 = readPositiveInt(sc, "請輸入三角形高度：");
                    printReverseTriangle(height2);
                    break;
                    
                case 4:
                    // 數字方格
                    System.out.println();
                    System.out.println("【數字方格】");
                    int rows = readPositiveInt(sc, "請輸入列數：");
                    int cols = readPositiveInt(sc, "請輸入欄數：");
                    printNumberGrid(rows, cols);
                    break;
                    
                case 0:
                    System.out.println();
                    System.out.println("====================================");
                    System.out.println("      感謝使用圖形產生器！         ");
                    System.out.println("====================================");
                    sc.close();
                    return;
                    
                default:
                    System.out.println();
                    System.out.println("❌ 無效選項，請輸入 0-4！");
                    System.out.println();
            }
        }
    }
    
    // ============================================
    // 方法 1：顯示選單
    // ============================================
    /**
     * 顯示功能選單
     */
    public static void printMenu() {
        System.out.println("=== 功能選單 ===");
        System.out.println("1. 九九乘法表");
        System.out.println("2. 正三角形星號");
        System.out.println("3. 倒三角形星號");
        System.out.println("4. 數字方格");
        System.out.println("0. 離開");
        System.out.println("================");
        System.out.print("請選擇功能（0-4）：");
    }
    
    // ============================================
    // 方法 2：讀取正整數（含輸入驗證）
    // ============================================
    /**
     * 讀取並驗證正整數（必須大於 0）
     * @param sc Scanner 物件
     * @param message 顯示的提示訊息
     * @return 輸入的正整數
     */
    public static int readPositiveInt(Scanner sc, String message) {
        int value;
        while (true) {
            System.out.print(message);
            value = sc.nextInt();
            if (value > 0) {
                break;
            }
            System.out.println("❌ 輸入必須大於 0，請重新輸入！");
        }
        return value;
    }
    
    // ============================================
    // 方法 3：九九乘法表
    // ============================================
    /**
     * 輸出完整的 9x9 乘法表
     */
    public static void printMultiplicationTable() {
        System.out.println("===== 九九乘法表 =====");
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.printf("%2d * %2d = %2d  ", i, j, i * j);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // ============================================
    // 方法 4：正三角形星號
    // ============================================
    /**
     * 輸出正三角形星號
     * @param height 三角形高度
     */
    public static void printTriangle(int height) {
        System.out.println("===== 正三角形（高度 " + height + "） =====");
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // ============================================
    // 方法 5：倒三角形星號
    // ============================================
    /**
     * 輸出倒三角形星號
     * @param height 三角形高度
     */
    public static void printReverseTriangle(int height) {
        System.out.println("===== 倒三角形（高度 " + height + "） =====");
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // ============================================
    // 方法 6：數字方格
    // ============================================
    /**
     * 輸出數字方格
     * @param rows 列數
     * @param cols 欄數
     */
    public static void printNumberGrid(int rows, int cols) {
        System.out.println("===== 數字方格（" + rows + " 列 x " + cols + " 欄） =====");
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
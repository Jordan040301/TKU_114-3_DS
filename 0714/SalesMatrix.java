import java.util.Scanner;

public class SalesMatrix {
    
    // 常數定義
    private static final int ROWS = 3;      // 3項商品
    private static final int COLS = 4;      // 4個分區
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 建立3x4的二維陣列
        int[][] sales = new int[ROWS][COLS];
        
        // 1. 輸入每一格銷售數量
        inputSales(sc, sales);
        
        // 2. 以表格形式顯示完整資料
        displaySalesMatrix(sales);
        
        // 3. 計算每一項商品的銷售總額（列總和）
        int[] productTotals = calculateProductTotals(sales);
        System.out.println("=== 各商品銷售總額 ===");
        for (int i = 0; i < productTotals.length; i++) {
            System.out.println("商品 " + (i + 1) + "： " + productTotals[i] + " 件");
        }
        
        // 4. 計算每個分區的銷售總額（欄總和）
        int[] regionTotals = calculateRegionTotals(sales);
        System.out.println("=== 各分區銷售總額 ===");
        for (int j = 0; j < regionTotals.length; j++) {
            System.out.println("分區 " + (j + 1) + "： " + regionTotals[j] + " 件");
        }
        
        // 5. 確定總銷售量最高的商品
        int bestProduct = findBestProduct(productTotals);
        System.out.println("=== 銷售冠軍 ===");
        System.out.println("總銷售量最高的商品：商品 " + (bestProduct + 1));
        System.out.println("銷售數量：" + productTotals[bestProduct] + " 件");
        
        sc.close();
    }
    
    /**
     * 輸入每一格銷售數量（數值不得小於0）
     */
    public static void inputSales(Scanner sc, int[][] sales) {
        System.out.println("=== 請輸入銷售數據 ===");
        for (int i = 0; i < ROWS; i++) {
            System.out.println("--- 商品 " + (i + 1) + " ---");
            for (int j = 0; j < COLS; j++) {
                while (true) {
                    System.out.print("  分區 " + (j + 1) + " 的銷售數量：");
                    sales[i][j] = sc.nextInt();
                    if (sales[i][j] >= 0) {
                        break;
                    }
                    System.out.println("錯誤：銷售數量不得小於0，請重新輸入！");
                }
            }
        }
        System.out.println();
    }
    
    /**
     * 以表格形式顯示完整資料
     */
    public static void displaySalesMatrix(int[][] sales) {
        System.out.println("=== 銷售矩陣報表 ===");
        
        // 顯示表頭
        System.out.print("商品\\分區\t");
        for (int j = 0; j < COLS; j++) {
            System.out.print("分區" + (j + 1) + "\t");
        }
        System.out.println("商品總計");
        
        // 顯示分隔線
        System.out.print("--------\t");
        for (int j = 0; j < COLS; j++) {
            System.out.print("--------\t");
        }
        System.out.println("--------");
        
        // 顯示數據與列總和
        for (int i = 0; i < ROWS; i++) {
            System.out.print("商品" + (i + 1) + "\t\t");
            
            int rowTotal = 0;
            for (int j = 0; j < COLS; j++) {
                System.out.print(sales[i][j] + "\t");
                rowTotal += sales[i][j];
            }
            System.out.println(rowTotal);
        }
        
        // 顯示欄總和
        System.out.print("分區總計\t");
        int[] regionTotals = calculateRegionTotals(sales);
        for (int j = 0; j < COLS; j++) {
            System.out.print(regionTotals[j] + "\t");
        }
        
        // 顯示總銷售量
        int grandTotal = calculateGrandTotal(sales);
        System.out.println(grandTotal);
        System.out.println();
    }
    
    /**
     * 計算每一項商品的銷售總額（列總和）
     */
    public static int[] calculateProductTotals(int[][] sales) {
        int[] productTotals = new int[ROWS];
        for (int i = 0; i < ROWS; i++) {
            int total = 0;
            for (int j = 0; j < COLS; j++) {
                total += sales[i][j];
            }
            productTotals[i] = total;
        }
        return productTotals;
    }
    
    /**
     * 計算每個分區的銷售總額（欄總和）
     */
    public static int[] calculateRegionTotals(int[][] sales) {
        int[] regionTotals = new int[COLS];
        for (int j = 0; j < COLS; j++) {
            int total = 0;
            for (int i = 0; i < ROWS; i++) {
                total += sales[i][j];
            }
            regionTotals[j] = total;
        }
        return regionTotals;
    }
    
    /**
     * 計算總銷售量
     */
    public static int calculateGrandTotal(int[][] sales) {
        int total = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                total += sales[i][j];
            }
        }
        return total;
    }
    
    /**
     * 確定總銷售量最高的商品
     */
    public static int findBestProduct(int[] productTotals) {
        int bestIndex = 0;
        for (int i = 1; i < productTotals.length; i++) {
            if (productTotals[i] > productTotals[bestIndex]) {
                bestIndex = i;
            }
        }
        return bestIndex;
    }
}
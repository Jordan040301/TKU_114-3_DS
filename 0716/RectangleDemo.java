/**
 * RectangleDemo 類別 - 測試 Rectangle 類別
 * 建立至少3個長方形物件，顯示相關資訊
 */
public class RectangleDemo {
    
    public static void main(String[] args) {
        System.out.println("=== 長方形測試程式 ===\n");
        
        // 建立3個長方形物件
        Rectangle rect1 = new Rectangle(5.0, 3.0);
        Rectangle rect2 = new Rectangle(4.0, 4.0);
        Rectangle rect3 = new Rectangle(7.5, 2.5);
        
        // 顯示每個長方形的資訊
        displayRectangleInfo(rect1, "長方形 1");
        displayRectangleInfo(rect2, "長方形 2");
        displayRectangleInfo(rect3, "長方形 3");
        
        // 額外測試：顯示所有長方形的比較
        System.out.println("=== 綜合比較 ===");
        compareRectangles(rect1, rect2, rect3);
        
        // 測試錯誤處理
        System.out.println("=== 錯誤處理測試 ===");
        testErrorHandling();
    }
    
    /**
     * 顯示長方形的詳細資訊
     * @param rect 長方形物件
     * @param name 顯示名稱
     */
    public static void displayRectangleInfo(Rectangle rect, String name) {
        System.out.println("--- " + name + " ---");
        System.out.println("寬度：" + rect.getWidth());
        System.out.println("高度：" + rect.getHeight());
        System.out.println("面積：" + rect.calculateArea());
        System.out.println("周長：" + rect.calculatePerimeter());
        System.out.println("是否為正方形：" + (rect.isSquare() ? "是" : "否"));
        System.out.println("完整資訊：" + rect.toString());
        System.out.println();
    }
    
    /**
     * 比較三個長方形
     * @param r1 長方形1
     * @param r2 長方形2
     * @param r3 長方形3
     */
    public static void compareRectangles(Rectangle r1, Rectangle r2, Rectangle r3) {
        Rectangle[] rects = {r1, r2, r3};
        String[] names = {"長方形1", "長方形2", "長方形3"};
        
        // 找出最大面積
        int maxAreaIndex = 0;
        for (int i = 1; i < rects.length; i++) {
            if (rects[i].calculateArea() > rects[maxAreaIndex].calculateArea()) {
                maxAreaIndex = i;
            }
        }
        System.out.println("最大面積：" + names[maxAreaIndex] + 
                          "（面積 = " + rects[maxAreaIndex].calculateArea() + "）");
        
        // 找出最小周長
        int minPerimeterIndex = 0;
        for (int i = 1; i < rects.length; i++) {
            if (rects[i].calculatePerimeter() < rects[minPerimeterIndex].calculatePerimeter()) {
                minPerimeterIndex = i;
            }
        }
        System.out.println("最小周長：" + names[minPerimeterIndex] + 
                          "（周長 = " + rects[minPerimeterIndex].calculatePerimeter() + "）");
        
        // 檢查有多少個正方形
        int squareCount = 0;
        for (int i = 0; i < rects.length; i++) {
            if (rects[i].isSquare()) {
                squareCount++;
                System.out.println(names[i] + " 是正方形");
            }
        }
        if (squareCount == 0) {
            System.out.println("沒有正方形");
        }
        System.out.println();
    }
    
    /**
     * 測試錯誤處理
     */
    public static void testErrorHandling() {
        System.out.println("測試無效輸入：");
        
        try {
            System.out.println("嘗試建立寬度為 -1 的長方形...");
            Rectangle invalidRect = new Rectangle(-1, 5);
            System.out.println("建立成功：" + invalidRect);
        } catch (IllegalArgumentException e) {
            System.out.println("捕獲異常：" + e.getMessage());
        }
        
        try {
            System.out.println("嘗試設定高度為 0...");
            Rectangle rect = new Rectangle(5, 5);
            rect.setHeight(0);
            System.out.println("設定成功：" + rect);
        } catch (IllegalArgumentException e) {
            System.out.println("捕獲異常：" + e.getMessage());
        }
        
        try {
            System.out.println("嘗試設定寬度為負數...");
            Rectangle rect = new Rectangle(5, 5);
            rect.setWidth(-3.5);
            System.out.println("設定成功：" + rect);
        } catch (IllegalArgumentException e) {
            System.out.println("捕獲異常：" + e.getMessage());
        }
        
        System.out.println();
    }
}
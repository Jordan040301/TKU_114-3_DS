/**
 * Rectangle 類別 - 表示長方形
 * 包含寬度、高度屬性及相關計算方法
 */
public class Rectangle {
    
    // 私有欄位
    private double width;
    private double height;
    
    /**
     * 建構子 - 接收寬和高
     * @param width 寬度（必須為正數）
     * @param height 高度（必須為正數）
     */
    public Rectangle(double width, double height) {
        setWidth(width);
        setHeight(height);
    }
    
    /**
     * 取得寬度
     * @return 寬度
     */
    public double getWidth() {
        return width;
    }
    
    /**
     * 取得高度
     * @return 高度
     */
    public double getHeight() {
        return height;
    }
    
    /**
     * 設定寬度 - 驗證正數
     * @param width 寬度（必須為正數）
     */
    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("寬度必須為正數，輸入值：" + width);
        }
        this.width = width;
    }
    
    /**
     * 設定高度 - 驗證正數
     * @param height 高度（必須為正數）
     */
    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("高度必須為正數，輸入值：" + height);
        }
        this.height = height;
    }
    
    /**
     * 計算面積
     * @return 面積 = 寬 × 高
     */
    public double calculateArea() {
        return width * height;
    }
    
    /**
     * 計算周長
     * @return 周長 = 2 × (寬 + 高)
     */
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
    
    /**
     * 判斷是否為正方形
     * @return 如果寬等於高則回傳 true，否則 false
     */
    public boolean isSquare() {
        return width == height;
    }
    
    /**
     * 回傳物件的字串表示
     * @return 包含寬、高、面積、周長及是否為正方形的資訊
     */
    @Override
    public String toString() {
        return String.format(
            "Rectangle[寬=%.2f, 高=%.2f, 面積=%.2f, 周長=%.2f, 正方形=%s]",
            width, height, calculateArea(), calculatePerimeter(), isSquare()
        );
    }
}
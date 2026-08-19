/**
 * Book.java
 * 圖書實體類別，代表圖書館中的一本書
 */
public class Book {
    private String id;          // 圖書編號（唯一）
    private String title;       // 書名
    private String category;    // 分類
    private int borrowCount;    // 借閱次數
    
    /**
     * 建構子
     * @param id 圖書編號
     * @param title 書名
     * @param category 分類
     * @param borrowCount 借閱次數
     */
    public Book(String id, String title, String category, int borrowCount) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.borrowCount = borrowCount;
    }
    
    // Getter 方法
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public int getBorrowCount() { return borrowCount; }
    
    // Setter 方法
    public void setTitle(String title) { this.title = title; }
    public void setCategory(String category) { this.category = category; }
    public void setBorrowCount(int borrowCount) { this.borrowCount = borrowCount; }
    
    /**
     * 判斷兩本書是否相同（根據圖書編號）
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return id.equals(book.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("圖書[編號:%s, 書名:%s, 分類:%s, 借閱次數:%d]", 
                           id, title, category, borrowCount);
    }
}
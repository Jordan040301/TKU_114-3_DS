import java.util.ArrayList;
import java.util.Arrays;

/**
 * LibraryManagementSystem.java
 * 圖書借閱資料管理系統
 * 
 * 功能要求：
 * 1. Book 包含編號、書名、分類及借閱次數
 * 2. 使用ArrayList保存所有書籍
 * 3. 使用合併排序依編號升序及借閱次數降序
 * 4. 使用二分查找依排序後編號查詢
 * 5. 使用順序搜尋依書名尋找所有書籍
 * 6. 處理空資料、重複編號及找不到資料
 */
public class LibraryManagementSystem {
    
    private ArrayList<Book> books;  // 所有書籍
    
    /**
     * 建構子：初始化系統
     */
    public LibraryManagementSystem() {
        books = new ArrayList<>();
    }
    
    /**
     * 新增圖書（防止重複編號）
     * @param book 要新增的圖書
     * @return true 如果新增成功，false 如果編號已存在
     */
    public boolean addBook(Book book) {
        // 檢查是否已存在相同編號的圖書
        for (Book existing : books) {
            if (existing.getId().equals(book.getId())) {
                System.out.println("錯誤：圖書編號 " + book.getId() + " 已存在！");
                return false;
            }
        }
        
        books.add(book);
        System.out.println("成功新增圖書：" + book);
        return true;
    }
    
    /**
     * 取得所有書籍
     * @return 所有書籍陣列
     */
    public Book[] getAllBooks() {
        return books.toArray(new Book[0]);
    }
    
    /**
     * 依編號升序排序（使用合併排序）
     * @return 排序後的書籍陣列
     */
    public Book[] sortByIdAsc() {
        if (books.isEmpty()) {
            System.out.println("目前沒有書籍資料");
            return new Book[0];
        }
        
        Book[] sorted = books.toArray(new Book[0]);
        BookAlgorithms.mergeSortByIdAsc(sorted, 0, sorted.length - 1);
        
        System.out.println("依編號升序排序完成");
        return sorted;
    }
    
    /**
     * 依借閱次數降序排序（使用合併排序）
     * @return 排序後的書籍陣列
     */
    public Book[] sortByBorrowCountDesc() {
        if (books.isEmpty()) {
            System.out.println("目前沒有書籍資料");
            return new Book[0];
        }
        
        Book[] sorted = books.toArray(new Book[0]);
        BookAlgorithms.mergeSortByBorrowCountDesc(sorted, 0, sorted.length - 1);
        
        System.out.println("依借閱次數降序排序完成");
        return sorted;
    }
    
    /**
     * 二分搜尋 - 依編號查詢書籍
     * @param id 要查詢的編號
     * @return 找到的書籍，如果找不到則返回 null
     */
    public Book searchById(String id) {
        if (books.isEmpty()) {
            System.out.println("目前沒有書籍資料");
            return null;
        }
        
        // 先依編號排序
        Book[] sorted = sortByIdAsc();
        Book result = BookAlgorithms.binarySearchById(sorted, id);
        
        if (result == null) {
            System.out.println("找不到編號為 \"" + id + "\" 的書籍");
        } else {
            System.out.println("找到書籍：" + result);
        }
        
        return result;
    }
    
    /**
     * 順序搜尋 - 依書名搜尋所有書籍
     * @param title 要搜尋的書名
     * @return 所有符合的書籍陣列
     */
    public Book[] searchByTitle(String title) {
        if (books.isEmpty()) {
            System.out.println("目前沒有書籍資料");
            return new Book[0];
        }
        
        Book[] allBooks = books.toArray(new Book[0]);
        Book[] results = BookAlgorithms.sequentialSearchByTitle(allBooks, title);
        
        if (results.length == 0) {
            System.out.println("找不到書名為 \"" + title + "\" 的書籍");
        } else {
            System.out.println("找到 " + results.length + " 本書名為 \"" + title + "\" 的書籍");
        }
        
        return results;
    }
    
    /**
     * 順序搜尋 - 依分類搜尋所有書籍
     * @param category 要搜尋的分類
     * @return 所有符合的書籍陣列
     */
    public Book[] searchByCategory(String category) {
        if (books.isEmpty()) {
            System.out.println("目前沒有書籍資料");
            return new Book[0];
        }
        
        Book[] allBooks = books.toArray(new Book[0]);
        Book[] results = BookAlgorithms.sequentialSearchByCategory(allBooks, category);
        
        if (results.length == 0) {
            System.out.println("找不到分類為 \"" + category + "\" 的書籍");
        } else {
            System.out.println("找到 " + results.length + " 本分類為 \"" + category + "\" 的書籍");
        }
        
        return results;
    }
    
    /**
     * 顯示所有書籍
     */
    public void displayAllBooks() {
        System.out.println("\n=== 所有書籍 ===");
        if (books.isEmpty()) {
            System.out.println("目前沒有書籍資料");
        } else {
            System.out.println("總筆數：" + books.size());
            for (Book book : books) {
                System.out.println("  " + book);
            }
        }
        System.out.println();
    }
    
    /**
     * 顯示排序後的書籍
     * @param sorted 排序後的書籍陣列
     * @param title 標題
     */
    public void displaySortedBooks(Book[] sorted, String title) {
        System.out.println("\n=== " + title + " ===");
        if (sorted.length == 0) {
            System.out.println("沒有書籍資料");
        } else {
            System.out.println("總筆數：" + sorted.length);
            for (Book book : sorted) {
                System.out.println("  " + book);
            }
        }
        System.out.println();
    }
    
    // ========== 測試主程式 ==========
    public static void main(String[] args) {
        LibraryManagementSystem system = new LibraryManagementSystem();
        
        System.out.println("=== 圖書借閱資料管理系統測試 ===\n");
        
        // 1. 測試新增圖書（含重複編號測試）
        System.out.println("--- 測試1：新增圖書 ---");
        system.addBook(new Book("B001", "Java程式設計", "程式設計", 15));
        system.addBook(new Book("B002", "資料結構導論", "資訊科學", 23));
        system.addBook(new Book("B003", "演算法概論", "資訊科學", 8));
        system.addBook(new Book("B004", "網路安全基礎", "網路技術", 31));
        system.addBook(new Book("B005", "Java程式設計", "程式設計", 42));  // 同書名不同書
        system.addBook(new Book("B006", "資料庫系統", "資訊科學", 12));
        system.addBook(new Book("B007", "作業系統原理", "資訊科學", 19));
        system.addBook(new Book("B008", "機器學習導論", "人工智慧", 27));
        system.addBook(new Book("B009", "Java程式設計", "程式設計", 5));   // 同書名不同書
        system.addBook(new Book("B001", "重複測試", "測試", 0));           // 重複編號測試
        system.displayAllBooks();
        
        // 2. 測試依編號升序排序
        System.out.println("--- 測試2：依編號升序排序 ---");
        Book[] sortedById = system.sortByIdAsc();
        system.displaySortedBooks(sortedById, "依編號升序排列");
        
        // 3. 測試依借閱次數降序排序
        System.out.println("--- 測試3：依借閱次數降序排序 ---");
        Book[] sortedByBorrow = system.sortByBorrowCountDesc();
        system.displaySortedBooks(sortedByBorrow, "依借閱次數降序排列");
        
        // 4. 測試二分搜尋（依編號）
        System.out.println("--- 測試4：二分搜尋（依編號） ---");
        // 搜尋存在的編號
        Book found1 = system.searchById("B003");
        System.out.println("  結果：" + found1 + "\n");
        
        // 搜尋不存在的編號
        Book found2 = system.searchById("B999");
        System.out.println();
        
        // 5. 測試順序搜尋（依書名）
        System.out.println("--- 測試5：順序搜尋（依書名） ---");
        // 搜尋存在的書名（有多本）
        Book[] results1 = system.searchByTitle("Java程式設計");
        for (Book book : results1) {
            System.out.println("  找到：" + book);
        }
        System.out.println();
        
        // 搜尋不存在的書名
        Book[] results2 = system.searchByTitle("不存在的書");
        System.out.println();
        
        // 6. 測試順序搜尋（依分類）
        System.out.println("--- 測試6：順序搜尋（依分類） ---");
        Book[] results3 = system.searchByCategory("資訊科學");
        for (Book book : results3) {
            System.out.println("  找到：" + book);
        }
        System.out.println();
        
        // 7. 測試空資料
        System.out.println("--- 測試7：空資料測試 ---");
        LibraryManagementSystem emptySystem = new LibraryManagementSystem();
        System.out.println("空系統顯示所有書籍：");
        emptySystem.displayAllBooks();
        System.out.println("空系統執行排序：");
        emptySystem.sortByIdAsc();
        System.out.println("空系統執行搜尋：");
        emptySystem.searchById("B001");
        emptySystem.searchByTitle("Java");
        
        System.out.println("\n=== 測試完成 ===");
    }
}
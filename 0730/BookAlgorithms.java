import java.util.ArrayList;

/**
 * BookAlgorithms.java
 * 圖書管理的演算法工具類別
 * 包含：合併排序（依編號升序、依借閱次數降序）、二分搜尋、順序搜尋
 */
public class BookAlgorithms {
    
    // ========== 合併排序：依編號升序 ==========
    
    /**
     * 依圖書編號升序排序（使用合併排序）
     * @param books 圖書陣列
     * @param left 左邊界
     * @param right 右邊界
     */
    public static void mergeSortByIdAsc(Book[] books, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSortByIdAsc(books, left, mid);
            mergeSortByIdAsc(books, mid + 1, right);
            mergeByIdAsc(books, left, mid, right);
        }
    }
    
    /**
     * 合併兩個已排序的子陣列（依編號升序）
     */
    private static void mergeByIdAsc(Book[] books, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        Book[] leftArray = new Book[n1];
        Book[] rightArray = new Book[n2];
        
        for (int i = 0; i < n1; i++) {
            leftArray[i] = books[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = books[mid + 1 + j];
        }
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            // 升序：字串比較
            if (leftArray[i].getId().compareTo(rightArray[j].getId()) <= 0) {
                books[k] = leftArray[i];
                i++;
            } else {
                books[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            books[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            books[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    // ========== 合併排序：依借閱次數降序 ==========
    
    /**
     * 依借閱次數降序排序（使用合併排序）
     * @param books 圖書陣列
     * @param left 左邊界
     * @param right 右邊界
     */
    public static void mergeSortByBorrowCountDesc(Book[] books, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSortByBorrowCountDesc(books, left, mid);
            mergeSortByBorrowCountDesc(books, mid + 1, right);
            mergeByBorrowCountDesc(books, left, mid, right);
        }
    }
    
    /**
     * 合併兩個已排序的子陣列（依借閱次數降序）
     */
    private static void mergeByBorrowCountDesc(Book[] books, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        Book[] leftArray = new Book[n1];
        Book[] rightArray = new Book[n2];
        
        for (int i = 0; i < n1; i++) {
            leftArray[i] = books[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = books[mid + 1 + j];
        }
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            // 降序：大的在前
            if (leftArray[i].getBorrowCount() >= rightArray[j].getBorrowCount()) {
                books[k] = leftArray[i];
                i++;
            } else {
                books[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            books[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            books[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    // ========== 二分搜尋：依編號查詢 ==========
    
    /**
     * 二分搜尋 - 依圖書編號查詢（陣列必須已依編號排序）
     * @param books 已排序的圖書陣列（依編號升序）
     * @param id 要查詢的圖書編號
     * @return 找到的圖書，如果找不到則返回 null
     */
    public static Book binarySearchById(Book[] books, String id) {
        if (books == null || books.length == 0) {
            return null;
        }
        
        int left = 0;
        int right = books.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = books[mid].getId().compareTo(id);
            
            if (compare == 0) {
                return books[mid];
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return null;
    }
    
    // ========== 順序搜尋：依書名搜尋所有書籍 ==========
    
    /**
     * 順序搜尋 - 依書名搜尋所有符合的書籍
     * @param books 圖書陣列（可以是未排序的）
     * @param title 要搜尋的書名
     * @return 所有符合的圖書陣列
     */
    public static Book[] sequentialSearchByTitle(Book[] books, String title) {
        if (books == null || books.length == 0) {
            return new Book[0];
        }
        
        // 先計算符合條件的數量
        int count = 0;
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                count++;
            }
        }
        
        if (count == 0) {
            return new Book[0];
        }
        
        // 建立結果陣列
        Book[] results = new Book[count];
        int index = 0;
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                results[index++] = book;
            }
        }
        
        return results;
    }
    
    /**
     * 順序搜尋 - 依分類搜尋所有符合的書籍
     * @param books 圖書陣列（可以是未排序的）
     * @param category 要搜尋的分類
     * @return 所有符合的圖書陣列
     */
    public static Book[] sequentialSearchByCategory(Book[] books, String category) {
        if (books == null || books.length == 0) {
            return new Book[0];
        }
        
        // 先計算符合條件的數量
        int count = 0;
        for (Book book : books) {
            if (book.getCategory().equals(category)) {
                count++;
            }
        }
        
        if (count == 0) {
            return new Book[0];
        }
        
        // 建立結果陣列
        Book[] results = new Book[count];
        int index = 0;
        for (Book book : books) {
            if (book.getCategory().equals(category)) {
                results[index++] = book;
            }
        }
        
        return results;
    }
    
    /**
     * 輔助方法：複製圖書陣列
     */
    public static Book[] copyBooks(Book[] books) {
        if (books == null) return null;
        Book[] copy = new Book[books.length];
        for (int i = 0; i < books.length; i++) {
            copy[i] = books[i];
        }
        return copy;
    }
}
import java.util.Stack;

public class BrowserUndoSystem {
    
    // 使用 Stack 儲存瀏覽頁面
    private Stack<String> history;
    private String currentPage;
    
    // 建構子
    public BrowserUndoSystem() {
        this.history = new Stack<>();
        this.currentPage = null;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 瀏覽器頁面管理系統 ===");
        System.out.println("使用 Stack 模擬瀏覽器頁面操作\n");
        
        BrowserUndoSystem browser = new BrowserUndoSystem();
        
        // 測試1：開啟新頁面
        System.out.println("=== 測試1：開啟新頁面 ===");
        browser.openPage("Google");
        browser.openPage("YouTube");
        browser.openPage("Facebook");
        browser.openPage("Twitter");
        browser.showCurrentPage();
        
        // 測試2：返回上一頁
        System.out.println("\n=== 測試2：返回上一頁 ===");
        browser.goBack();
        browser.showCurrentPage();
        browser.goBack();
        browser.showCurrentPage();
        
        // 測試3：開啟更多頁面
        System.out.println("\n=== 測試3：開啟更多頁面 ===");
        browser.openPage("Instagram");
        browser.openPage("LinkedIn");
        browser.showCurrentPage();
        
        // 測試4：返回多頁
        System.out.println("\n=== 測試4：返回多頁 ===");
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.showCurrentPage();
        
        // 測試5：查看堆疊狀態
        System.out.println("\n=== 測試5：查看堆疊狀態 ===");
        browser.showHistory();
        
        // 測試6：繼續返回（測試空堆疊）
        System.out.println("\n=== 測試6：繼續返回（測試空堆疊） ===");
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.showCurrentPage();
        
        // 測試7：空堆疊時開啟新頁面
        System.out.println("\n=== 測試7：空堆疊時開啟新頁面 ===");
        browser.openPage("NewSite");
        browser.showCurrentPage();
        browser.showHistory();
        
        // 測試8：混合操作
        System.out.println("\n=== 測試8：混合操作 ===");
        browser.openPage("PageA");
        browser.openPage("PageB");
        browser.goBack();
        browser.openPage("PageC");
        browser.goBack();
        browser.goBack();
        browser.showCurrentPage();
        browser.showHistory();
        
        // 測試9：開啟頁面後返回
        System.out.println("\n=== 測試9：開啟頁面後返回 ===");
        browser.openPage("FinalPage");
        browser.showCurrentPage();
        browser.goBack();
        browser.showCurrentPage();
        
        System.out.println("\n=== 所有測試完成 ===");
        System.out.println("總操作次數：9 次測試");
    }
    
    /**
     * 開啟新頁面
     * 將目前頁面推入堆疊，設定新頁面為目前頁面
     */
    public void openPage(String page) {
        if (page == null || page.trim().isEmpty()) {
            System.out.println("  ❌ 錯誤：頁面名稱不得為空白！");
            return;
        }
        
        // 如果目前有頁面，先推入堆疊
        if (currentPage != null) {
            history.push(currentPage);
            System.out.println("  📌 儲存目前頁面到堆疊：" + currentPage);
        }
        
        currentPage = page.trim();
        System.out.println("  🌐 開啟新頁面：" + currentPage);
        System.out.println("  📊 堆疊大小：" + history.size());
    }
    
    /**
     * 返回上一頁
     * 從堆疊彈出頁面作為目前頁面
     */
    public void goBack() {
        if (history.isEmpty()) {
            System.out.println("  ⚠️ 沒有上一頁可以返回！");
            if (currentPage == null) {
                System.out.println("  ℹ️ 目前沒有開啟任何頁面");
            } else {
                System.out.println("  ℹ️ 目前頁面：" + currentPage + "（沒有更早的記錄）");
            }
            return;
        }
        
        String previousPage = history.pop();
        System.out.println("  ⬅️ 返回上一頁：" + previousPage);
        currentPage = previousPage;
        System.out.println("  📊 堆疊大小：" + history.size());
    }
    
    /**
     * 查看目前頁面
     */
    public void showCurrentPage() {
        System.out.println("  📍 目前頁面：" + (currentPage == null ? "（無）" : currentPage));
        System.out.println("  📊 堆疊大小：" + history.size());
    }
    
    /**
     * 顯示瀏覽歷史（堆疊內容）
     */
    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("  📋 瀏覽歷史：空（沒有上一頁記錄）");
            return;
        }
        
        System.out.println("  📋 瀏覽歷史（從最近到最舊）：");
        // 複製堆疊以顯示內容（不影響原堆疊）
        @SuppressWarnings("unchecked")
        Stack<String> tempStack = (Stack<String>) history.clone();
        int position = 1;
        while (!tempStack.isEmpty()) {
            System.out.println("     " + position + ". " + tempStack.pop());
            position++;
        }
        System.out.println("  📊 共 " + history.size() + " 筆記錄");
    }
    
    /**
     * 取得目前頁面（供外部使用）
     */
    public String getCurrentPage() {
        return currentPage;
    }
    
    /**
     * 取得歷史大小（供外部使用）
     */
    public int getHistorySize() {
        return history.size();
    }
}
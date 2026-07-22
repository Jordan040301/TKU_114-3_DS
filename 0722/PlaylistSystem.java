import java.util.Scanner;

public class PlaylistSystem {
    
    private static PlaylistLinkedList playlist = new PlaylistLinkedList();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== 播放清單管理系統 ===");
        System.out.println("歡迎使用播放清單管理系統！\n");
        
        // 加入範例資料
        initializeSampleData();
        
        while (true) {
            showMenu();
            System.out.print("請選擇功能 (1-6)：");
            
            if (!scanner.hasNextInt()) {
                System.out.println("  ❌ 錯誤：請輸入數字 1-6！");
                scanner.next();
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addSong();
                    break;
                case 2:
                    searchSong();
                    break;
                case 3:
                    deleteSong();
                    break;
                case 4:
                    displayPlaylist();
                    break;
                case 5:
                    showStatistics();
                    break;
                case 6:
                    System.out.println("感謝使用播放清單管理系統，再見！");
                    scanner.close();
                    return;
                default:
                    System.out.println("  ❌ 錯誤：請選擇 1-6 的有效選項！");
            }
        }
    }
    
    /**
     * 初始化範例資料
     */
    private static void initializeSampleData() {
        System.out.println("📌 載入範例歌曲...");
        playlist.addSong("S001", "告白氣球");
        playlist.addSong("S002", "小幸運");
        playlist.addSong("S003", "那些年");
        playlist.addSong("S004", "魚仔");
        playlist.addSong("S005", "想你的夜");
        System.out.println("✅ 已載入 " + playlist.getSize() + " 首範例歌曲\n");
    }
    
    /**
     * 顯示功能選單
     */
    private static void showMenu() {
        System.out.println("\n=== 功能選單 ===");
        System.out.println("1. 新增歌曲（尾端）");
        System.out.println("2. 搜尋歌曲（依代碼）");
        System.out.println("3. 刪除歌曲（依代碼）");
        System.out.println("4. 顯示完整播放順序");
        System.out.println("5. 顯示統計資訊");
        System.out.println("6. 離開系統");
    }
    
    /**
     * 功能1：新增歌曲
     */
    private static void addSong() {
        System.out.println("\n--- 新增歌曲 ---");
        System.out.print("請輸入歌曲代碼：");
        String code = scanner.nextLine().trim();
        
        if (code.isEmpty()) {
            System.out.println("  ❌ 錯誤：歌曲代碼不得為空白！");
            return;
        }
        
        System.out.print("請輸入歌曲名稱：");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("  ❌ 錯誤：歌曲名稱不得為空白！");
            return;
        }
        
        playlist.addSong(code, name);
    }
    
    /**
     * 功能2：搜尋歌曲
     */
    private static void searchSong() {
        System.out.println("\n--- 搜尋歌曲 ---");
        System.out.print("請輸入要搜尋的歌曲代碼：");
        String code = scanner.nextLine().trim();
        
        if (code.isEmpty()) {
            System.out.println("  ❌ 錯誤：請輸入有效的歌曲代碼！");
            return;
        }
        
        PlaylistNode found = playlist.findByCode(code);
        if (found != null) {
            System.out.println("  歌曲代碼：" + found.getSongCode());
            System.out.println("  歌曲名稱：" + found.getSongName());
        }
    }
    
    /**
     * 功能3：刪除歌曲
     */
    private static void deleteSong() {
        System.out.println("\n--- 刪除歌曲 ---");
        System.out.print("請輸入要刪除的歌曲代碼：");
        String code = scanner.nextLine().trim();
        
        if (code.isEmpty()) {
            System.out.println("  ❌ 錯誤：請輸入有效的歌曲代碼！");
            return;
        }
        
        System.out.println("確定要刪除這首歌曲嗎？");
        System.out.print("請輸入 y 確認刪除，其他鍵取消：");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (!confirm.equals("y")) {
            System.out.println("  ❌ 已取消刪除操作");
            return;
        }
        
        playlist.deleteSong(code);
    }
    
    /**
     * 功能4：顯示完整播放順序
     */
    private static void displayPlaylist() {
        System.out.println("\n--- 完整播放順序 ---");
        playlist.displayPlaylist();
    }
    
    /**
     * 功能5：顯示統計資訊
     */
    private static void showStatistics() {
        System.out.println("\n--- 播放清單統計 ---");
        System.out.println("  總歌曲數：" + playlist.getSize() + " 首");
        System.out.println("  是否為空：" + (playlist.isEmpty() ? "是" : "否"));
        
        if (!playlist.isEmpty()) {
            System.out.println("\n  播放清單簡易顯示：");
            playlist.displaySimple();
        }
    }
}
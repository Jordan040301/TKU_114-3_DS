public class PlaylistLinkedList {
    private PlaylistNode head;
    private int size;
    
    // 建構子
    public PlaylistLinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    /**
     * 取得串列大小
     */
    public int getSize() {
        return size;
    }
    
    /**
     * 檢查是否為空
     */
    public boolean isEmpty() {
        return head == null;
    }
    
    /**
     * 尾端新增歌曲
     * 檢查歌曲代碼是否重複
     */
    public boolean addSong(String songCode, String songName) {
        // 檢查代碼是否為空白
        if (songCode == null || songCode.trim().isEmpty()) {
            System.out.println("  ❌ 錯誤：歌曲代碼不得為空白！");
            return false;
        }
        
        if (songName == null || songName.trim().isEmpty()) {
            System.out.println("  ❌ 錯誤：歌曲名稱不得為空白！");
            return false;
        }
        
        // 檢查代碼是否重複
        if (findByCode(songCode) != null) {
            System.out.println("  ❌ 錯誤：歌曲代碼 \"" + songCode + "\" 已存在！");
            return false;
        }
        
        PlaylistNode newNode = new PlaylistNode(songCode, songName);
        
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
        System.out.println("  ✅ 新增歌曲：" + songCode + " | " + songName);
        return true;
    }
    
    /**
     * 依歌曲代碼搜尋
     */
    public PlaylistNode findByCode(String songCode) {
        if (songCode == null || songCode.trim().isEmpty()) {
            return null;
        }
        
        PlaylistNode current = head;
        int position = 1;
        while (current != null) {
            if (current.getSongCode().equalsIgnoreCase(songCode.trim())) {
                System.out.println("  🔍 找到歌曲在位置 " + position + "：" + current);
                return current;
            }
            current = current.getNext();
            position++;
        }
        System.out.println("  ❌ 找不到歌曲代碼 \"" + songCode + "\"");
        return null;
    }
    
    /**
     * 刪除歌曲（依代碼）
     * 刪除第一首與最後一首都必須正確
     */
    public boolean deleteSong(String songCode) {
        if (songCode == null || songCode.trim().isEmpty()) {
            System.out.println("  ❌ 錯誤：請輸入有效的歌曲代碼！");
            return false;
        }
        
        if (head == null) {
            System.out.println("  ⚠️ 播放清單為空，無法刪除");
            return false;
        }
        
        String trimmedCode = songCode.trim();
        
        // 刪除第一首
        if (head.getSongCode().equalsIgnoreCase(trimmedCode)) {
            System.out.println("  ✅ 刪除第一首歌曲：" + head);
            head = head.getNext();
            size--;
            return true;
        }
        
        // 刪除中間或最後一首
        PlaylistNode current = head;
        while (current.getNext() != null) {
            if (current.getNext().getSongCode().equalsIgnoreCase(trimmedCode)) {
                PlaylistNode deleted = current.getNext();
                String position = (deleted.getNext() == null) ? "最後一首" : "";
                System.out.println("  ✅ 刪除" + position + "歌曲：" + deleted);
                current.setNext(deleted.getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }
        
        System.out.println("  ❌ 找不到歌曲代碼 \"" + songCode + "\"");
        return false;
    }
    
    /**
     * 顯示完整播放順序
     */
    public void displayPlaylist() {
        if (head == null) {
            System.out.println("  📋 播放清單為空");
            return;
        }
        
        System.out.println("  📋 播放清單（共 " + size + " 首歌曲）：");
        System.out.println("  ┌────┬──────────┬─────────────────────┐");
        System.out.println("  │ 編號 │ 歌曲代碼  │ 歌曲名稱            │");
        System.out.println("  ├────┼──────────┼─────────────────────┤");
        
        PlaylistNode current = head;
        int position = 1;
        while (current != null) {
            String code = current.getSongCode();
            String name = current.getSongName();
            // 限制顯示長度
            if (name.length() > 19) {
                name = name.substring(0, 19);
            }
            System.out.printf("  │ %-2d  │ %-8s │ %-19s │%n", 
                position, code, name);
            current = current.getNext();
            position++;
        }
        System.out.println("  └────┴──────────┴─────────────────────┘");
    }
    
    /**
     * 顯示播放清單（簡單版）
     */
    public void displaySimple() {
        if (head == null) {
            System.out.println("  （播放清單為空）");
            return;
        }
        
        PlaylistNode current = head;
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        while (current != null) {
            sb.append(current.getSongCode());
            sb.append(":");
            sb.append(current.getSongName());
            if (current.getNext() != null) {
                sb.append(" → ");
            }
            current = current.getNext();
        }
        System.out.println(sb.toString());
    }
}
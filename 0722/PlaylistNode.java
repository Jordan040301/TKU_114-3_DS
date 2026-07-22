public class PlaylistNode {
    private String songCode;      // 歌曲代碼
    private String songName;      // 歌曲名稱
    private PlaylistNode next;    // 下一個節點
    
    // 建構子
    public PlaylistNode(String songCode, String songName) {
        this.songCode = songCode;
        this.songName = songName;
        this.next = null;
    }
    
    // Getter 方法
    public String getSongCode() {
        return songCode;
    }
    
    public String getSongName() {
        return songName;
    }
    
    public PlaylistNode getNext() {
        return next;
    }
    
    // Setter 方法
    public void setNext(PlaylistNode next) {
        this.next = next;
    }
    
    @Override
    public String toString() {
        return songCode + " | " + songName;
    }
}
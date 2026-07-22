public class Equipment {
    private String code;      // 設備代碼
    private String name;      // 設備名稱
    private boolean available; // 可借用狀態（true=可借用，false=已借出）
    
    // 建構子
    public Equipment(String code, String name) {
        this.code = code;
        this.name = name;
        this.available = true; // 預設為可借用
    }
    
    // 取得設備代碼
    public String getCode() {
        return code;
    }
    
    // 取得設備名稱
    public String getName() {
        return name;
    }
    
    // 取得可借用狀態
    public boolean isAvailable() {
        return available;
    }
    
    // 設定設備名稱（用於修改）
    public void setName(String name) {
        this.name = name;
    }
    
    // 借出設備
    public boolean borrow() {
        if (available) {
            available = false;
            return true;
        }
        return false;
    }
    
    // 歸還設備
    public boolean returnEquipment() {
        if (!available) {
            available = true;
            return true;
        }
        return false;
    }
    
    // 顯示設備資訊
    @Override
    public String toString() {
        String status = available ? "✅ 可借用" : "❌ 已借出";
        return "代碼：" + code + " | 名稱：" + name + " | 狀態：" + status;
    }
}
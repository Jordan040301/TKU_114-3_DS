public class Contact {
    private String code;      // 聯絡人代碼
    private String name;      // 姓名
    private String phone;     // 電話
    private String email;     // 電子郵件
    
    // 建構子
    public Contact(String code, String name, String phone, String email) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    
    // Getter 方法
    public String getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    // Setter 方法
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    // 顯示聯絡人資訊
    @Override
    public String toString() {
        return "代碼：" + code + " | 姓名：" + name + " | 電話：" + phone + " | 電子郵件：" + email;
    }
}
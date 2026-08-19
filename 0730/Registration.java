/**
 * Registration.java
 * 活動報名實體類別，代表一筆報名記錄
 */
public class Registration {
    private String registrationId;  // 報名編號（唯一）
    private String participantName; // 參加者姓名
    private String eventName;       // 活動名稱
    private String phone;           // 聯絡電話
    private String status;          // 狀態：CONFIRMED, WAITING, CANCELLED
    
    /**
     * 建構子
     * @param registrationId 報名編號
     * @param participantName 參加者姓名
     * @param eventName 活動名稱
     * @param phone 聯絡電話
     */
    public Registration(String registrationId, String participantName, String eventName, String phone) {
        this.registrationId = registrationId;
        this.participantName = participantName;
        this.eventName = eventName;
        this.phone = phone;
        this.status = "CONFIRMED"; // 預設為已確認
    }
    
    // Getter 方法
    public String getRegistrationId() { return registrationId; }
    public String getParticipantName() { return participantName; }
    public String getEventName() { return eventName; }
    public String getPhone() { return phone; }
    public String getStatus() { return status; }
    
    // Setter 方法
    public void setStatus(String status) { this.status = status; }
    public void setPhone(String phone) { this.phone = phone; }
    
    /**
     * 判斷兩筆報名是否相同（根據報名編號）
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Registration reg = (Registration) obj;
        return registrationId.equals(reg.registrationId);
    }
    
    @Override
    public int hashCode() {
        return registrationId.hashCode();
    }
    
    @Override
    public String toString() {
        String statusText = "";
        switch (status) {
            case "CONFIRMED": statusText = "已確認"; break;
            case "WAITING": statusText = "候補中"; break;
            case "CANCELLED": statusText = "已取消"; break;
            default: statusText = status;
        }
        return String.format("報名[編號:%s, 姓名:%s, 活動:%s, 電話:%s, 狀態:%s]", 
                           registrationId, participantName, eventName, phone, statusText);
    }
}
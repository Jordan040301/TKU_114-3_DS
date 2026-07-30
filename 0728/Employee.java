/**
 * 員工類別
 * 包含員工的基本資訊
 */
public class Employee {
    private String id;
    private String name;
    private String department;
    private String extension;
    
    /**
     * 建構子
     */
    public Employee(String id, String name, String department, String extension) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.extension = extension;
    }
    
    // Getter 方法
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public String getExtension() {
        return extension;
    }
    
    // Setter 方法 (如果需要修改資料)
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    /**
     * 顯示完整的員工資料
     */
    public void displayInfo() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║          員工詳細資料                       ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║ 員工編號  : " + padRight(id, 30) + "║");
        System.out.println("║ 員工姓名  : " + padRight(name, 30) + "║");
        System.out.println("║ 所屬部門  : " + padRight(department, 30) + "║");
        System.out.println("║ 分機號碼  : " + padRight(extension, 30) + "║");
        System.out.println("╚════════════════════════════════════════════╝");
    }
    
    /**
     * 輔助方法：右邊補空格以對齊
     */
    private String padRight(String str, int length) {
        if (str == null) {
            str = "";
        }
        if (str.length() >= length) {
            return str.substring(0, length);
        }
        return str + " ".repeat(length - str.length());
    }
    
    @Override
    public String toString() {
        return "員工編號: " + id + 
               ", 姓名: " + name + 
               ", 部門: " + department + 
               ", 分機: " + extension;
    }
}
public class Q11_LabDevice {
    // ==================== private field ====================
    private String code;
    private String name;
    private int usageHours;
    private boolean active;

    // ==================== 建构子 ====================
    public Q11_LabDevice(
        String code,
        String name,
        int usageHours,
        boolean active
    ) {
        // code 为 null 或空白时储存为 "UNKNOWN"
        if (code == null || code.trim().isEmpty()) {
            this.code = "UNKNOWN";
        } else {
            this.code = code.trim();
        }

        // name 为 null 或空白时储存为 "Unnamed"
        if (name == null || name.trim().isEmpty()) {
            this.name = "Unnamed";
        } else {
            this.name = name.trim();
        }

        // usageHours 小于 0 时储存为 0
        if (usageHours < 0) {
            this.usageHours = 0;
        } else {
            this.usageHours = usageHours;
        }

        this.active = active;
    }

    // ==================== Getter ====================
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getUsageHours() {
        return usageHours;
    }

    public boolean isActive() {
        return active;
    }

    // ==================== Setter ====================
    // setName() 只接受非 null 且非空白的名称；输入不合法时保留原名
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
        // 输入不合法时什么都不做，保留原名
    }

    // ==================== 方法：增加使用时数 ====================
    // addUsageHours() 只接受大于 0 的时数
    public void addUsageHours(int hours) {
        if (hours > 0) {
            this.usageHours += hours;
        }
        // 小时数不大于 0 时什么都不做
    }

    // ==================== 方法：停用设备 ====================
    public void deactivate() {
        this.active = false;
    }

    // ==================== 方法：是否需要保养 ====================
    // 使用时数大于或等于 100 时回传 true
    public boolean needsMaintenance() {
        return usageHours >= 100;
    }

    // ==================== toString() ====================
    // 格式：代碼 | 名稱 | 使用時數 hours | active 或 inactive
    @Override
    public String toString() {
        String status = active ? "active" : "inactive";
        return code + " | " + name + " | " + usageHours + " hours | " + status;
    }

    // ==================== main 测试 ====================
    public static void main(String[] args) {
        Q11_LabDevice device = new Q11_LabDevice(
            "D01",
            " Printer ",
            90,
            true
        );

        device.addUsageHours(30);
        device.addUsageHours(-5);
        device.setName(" 3D Printer ");

        System.out.println(device);
        System.out.println("需要保養: " + device.needsMaintenance());

        device.deactivate();
        System.out.println("啟用狀態: " + device.isActive());
    }
}
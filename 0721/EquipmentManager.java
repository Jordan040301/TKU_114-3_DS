import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {
    
    private static ArrayList<Equipment> equipmentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== 設備物品管理系統 ===");
        System.out.println("歡迎使用設備物品管理系統！");
        
        // 加入一些範例設備
        initializeSampleData();
        
        while (true) {
            showMenu();
            System.out.print("請選擇功能 (1-7)：");
            
            // 處理非數字輸入
            if (!scanner.hasNextInt()) {
                System.out.println("❌ 錯誤：請輸入數字 1-7！");
                scanner.next(); // 清除無效輸入
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗換行符
            
            switch (choice) {
                case 1:
                    addEquipment();
                    break;
                case 2:
                    searchEquipment();
                    break;
                case 3:
                    borrowEquipment();
                    break;
                case 4:
                    returnEquipment();
                    break;
                case 5:
                    showAvailableEquipment();
                    break;
                case 6:
                    showAllEquipment();
                    break;
                case 7:
                    System.out.println("感謝使用設備物品管理系統，再見！");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ 錯誤：請選擇 1-7 的有效選項！");
            }
        }
    }
    
    /**
     * 初始化範例資料
     */
    private static void initializeSampleData() {
        equipmentList.add(new Equipment("E001", "筆記型電腦"));
        equipmentList.add(new Equipment("E002", "投影機"));
        equipmentList.add(new Equipment("E003", "數位相機"));
        equipmentList.add(new Equipment("E004", "雷射印表機"));
        equipmentList.add(new Equipment("E005", "掃描器"));
        System.out.println("✅ 已載入 " + equipmentList.size() + " 筆範例設備資料");
    }
    
    /**
     * 顯示功能選單
     */
    private static void showMenu() {
        System.out.println("\n=== 功能選單 ===");
        System.out.println("1. 新增設備");
        System.out.println("2. 搜尋設備（依代碼）");
        System.out.println("3. 借出設備");
        System.out.println("4. 歸還設備");
        System.out.println("5. 顯示可借用設備");
        System.out.println("6. 顯示全部設備");
        System.out.println("7. 離開系統");
    }
    
    /**
     * 功能1：新增設備
     */
    private static void addEquipment() {
        System.out.print("請輸入設備代碼：");
        String code = scanner.nextLine().trim();
        
        // 檢查代碼是否為空白
        if (code.isEmpty()) {
            System.out.println("❌ 錯誤：設備代碼不得為空白！");
            return;
        }
        
        // 檢查代碼是否已存在
        if (findEquipmentByCode(code) != null) {
            System.out.println("❌ 錯誤：設備代碼 \"" + code + "\" 已存在！");
            return;
        }
        
        System.out.print("請輸入設備名稱：");
        String name = scanner.nextLine().trim();
        
        // 檢查名稱是否為空白
        if (name.isEmpty()) {
            System.out.println("❌ 錯誤：設備名稱不得為空白！");
            return;
        }
        
        // 新增設備
        Equipment newEquipment = new Equipment(code, name);
        equipmentList.add(newEquipment);
        System.out.println("✅ 成功新增設備：" + newEquipment);
    }
    
    /**
     * 功能2：搜尋設備（依代碼）
     */
    private static void searchEquipment() {
        System.out.print("請輸入要搜尋的設備代碼：");
        String code = scanner.nextLine().trim();
        
        // 檢查代碼是否為空白
        if (code.isEmpty()) {
            System.out.println("❌ 錯誤：請輸入有效的設備代碼！");
            return;
        }
        
        Equipment found = findEquipmentByCode(code);
        
        if (found != null) {
            System.out.println("🔍 找到設備：");
            System.out.println("  " + found);
        } else {
            System.out.println("❌ 找不到設備代碼 \"" + code + "\"");
        }
    }
    
    /**
     * 功能3：借出設備
     */
    private static void borrowEquipment() {
        System.out.print("請輸入要借出的設備代碼：");
        String code = scanner.nextLine().trim();
        
        // 檢查代碼是否為空白
        if (code.isEmpty()) {
            System.out.println("❌ 錯誤：請輸入有效的設備代碼！");
            return;
        }
        
        Equipment found = findEquipmentByCode(code);
        
        if (found == null) {
            System.out.println("❌ 找不到設備代碼 \"" + code + "\"");
            return;
        }
        
        if (found.borrow()) {
            System.out.println("✅ 成功借出設備：" + found.getName());
            System.out.println("   " + found);
        } else {
            System.out.println("❌ 設備 \"" + found.getName() + "\" 目前已被借出，無法借用！");
        }
    }
    
    /**
     * 功能4：歸還設備
     */
    private static void returnEquipment() {
        System.out.print("請輸入要歸還的設備代碼：");
        String code = scanner.nextLine().trim();
        
        // 檢查代碼是否為空白
        if (code.isEmpty()) {
            System.out.println("❌ 錯誤：請輸入有效的設備代碼！");
            return;
        }
        
        Equipment found = findEquipmentByCode(code);
        
        if (found == null) {
            System.out.println("❌ 找不到設備代碼 \"" + code + "\"");
            return;
        }
        
        if (found.returnEquipment()) {
            System.out.println("✅ 成功歸還設備：" + found.getName());
            System.out.println("   " + found);
        } else {
            System.out.println("❌ 設備 \"" + found.getName() + "\" 目前已是可借用狀態，無需歸還！");
        }
    }
    
    /**
     * 功能5：顯示可借用設備
     */
    private static void showAvailableEquipment() {
        ArrayList<Equipment> availableList = new ArrayList<>();
        
        for (Equipment eq : equipmentList) {
            if (eq.isAvailable()) {
                availableList.add(eq);
            }
        }
        
        if (availableList.isEmpty()) {
            System.out.println("📋 目前沒有可借用的設備");
            return;
        }
        
        System.out.println("📋 可借用設備清單（共 " + availableList.size() + " 項）：");
        System.out.println("  編號  代碼  名稱        狀態");
        System.out.println("  -----------------------------");
        for (int i = 0; i < availableList.size(); i++) {
            Equipment eq = availableList.get(i);
            System.out.printf("  %-4d  %-6s %-8s  %s%n", 
                (i + 1), 
                eq.getCode(), 
                eq.getName(),
                "✅ 可借用");
        }
    }
    
    /**
     * 功能6：顯示全部設備
     */
    private static void showAllEquipment() {
        if (equipmentList.isEmpty()) {
            System.out.println("📋 設備清單為空，尚無任何設備");
            return;
        }
        
        System.out.println("📋 全部設備清單（共 " + equipmentList.size() + " 項）：");
        System.out.println("  編號  代碼  名稱        狀態");
        System.out.println("  -----------------------------");
        for (int i = 0; i < equipmentList.size(); i++) {
            Equipment eq = equipmentList.get(i);
            String status = eq.isAvailable() ? "✅ 可借用" : "❌ 已借出";
            System.out.printf("  %-4d  %-6s %-8s  %s%n", 
                (i + 1), 
                eq.getCode(), 
                eq.getName(),
                status);
        }
    }
    
    /**
     * 依代碼搜尋設備
     */
    private static Equipment findEquipmentByCode(String code) {
        for (Equipment eq : equipmentList) {
            if (eq.getCode().equalsIgnoreCase(code)) {
                return eq;
            }
        }
        return null;
    }
}
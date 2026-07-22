import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {
    
    private static ArrayList<String> nameList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== 名單管理系統 ===");
        System.out.println("歡迎使用名單管理系統！");
        
        while (true) {
            showMenu();
            System.out.print("請選擇功能 (1-6)：");
            
            // 處理非數字輸入
            if (!scanner.hasNextInt()) {
                System.out.println("❌ 錯誤：請輸入數字 1-6！");
                scanner.next(); // 清除無效輸入
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗換行符
            
            switch (choice) {
                case 1:
                    addName();
                    break;
                case 2:
                    searchName();
                    break;
                case 3:
                    updateName();
                    break;
                case 4:
                    deleteName();
                    break;
                case 5:
                    showAllNames();
                    break;
                case 6:
                    System.out.println("感謝使用名單管理系統，再見！");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ 錯誤：請選擇 1-6 的有效選項！");
            }
        }
    }
    
    /**
     * 顯示功能選單
     */
    private static void showMenu() {
        System.out.println("\n=== 功能選單 ===");
        System.out.println("1. 新增姓名");
        System.out.println("2. 搜尋姓名");
        System.out.println("3. 修改姓名");
        System.out.println("4. 刪除姓名");
        System.out.println("5. 顯示全部姓名");
        System.out.println("6. 離開系統");
    }
    
    /**
     * 功能1：新增姓名
     */
    private static void addName() {
        System.out.print("請輸入要新增的姓名：");
        String name = scanner.nextLine().trim();
        
        // 檢查是否為空白
        if (name.isEmpty()) {
            System.out.println("❌ 錯誤：姓名不得為空白！");
            return;
        }
        
        // 檢查是否已存在（忽略大小寫）
        if (isNameExists(name)) {
            System.out.println("❌ 錯誤：姓名 \"" + name + "\" 已存在！");
            return;
        }
        
        nameList.add(name);
        System.out.println("✅ 成功新增姓名：" + name);
        System.out.println("目前共有 " + nameList.size() + " 位成員");
    }
    
    /**
     * 功能2：搜尋姓名
     */
    private static void searchName() {
        System.out.print("請輸入要搜尋的姓名：");
        String name = scanner.nextLine().trim();
        
        // 檢查是否為空白
        if (name.isEmpty()) {
            System.out.println("❌ 錯誤：請輸入有效的姓名！");
            return;
        }
        
        // 搜尋姓名（忽略大小寫）
        ArrayList<String> results = findNames(name);
        
        if (results.isEmpty()) {
            System.out.println("🔍 找不到姓名 \"" + name + "\"");
        } else {
            System.out.println("🔍 找到 " + results.size() + " 筆相符的姓名：");
            for (int i = 0; i < results.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + results.get(i));
            }
        }
    }
    
    /**
     * 功能3：修改姓名
     */
    private static void updateName() {
        System.out.print("請輸入要修改的姓名：");
        String oldName = scanner.nextLine().trim();
        
        // 檢查是否為空白
        if (oldName.isEmpty()) {
            System.out.println("❌ 錯誤：請輸入有效的姓名！");
            return;
        }
        
        // 檢查姓名是否存在（忽略大小寫）
        if (!isNameExists(oldName)) {
            System.out.println("❌ 錯誤：找不到姓名 \"" + oldName + "\"");
            return;
        }
        
        System.out.print("請輸入新的姓名：");
        String newName = scanner.nextLine().trim();
        
        // 檢查新姓名是否為空白
        if (newName.isEmpty()) {
            System.out.println("❌ 錯誤：新姓名不得為空白！");
            return;
        }
        
        // 檢查新姓名是否已存在（忽略大小寫）
        if (isNameExists(newName) && !oldName.equalsIgnoreCase(newName)) {
            System.out.println("❌ 錯誤：姓名 \"" + newName + "\" 已存在！");
            return;
        }
        
        // 修改姓名（忽略大小寫比對）
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).equalsIgnoreCase(oldName)) {
                nameList.set(i, newName);
                System.out.println("✅ 成功將 \"" + oldName + "\" 修改為 \"" + newName + "\"");
                return;
            }
        }
    }
    
    /**
     * 功能4：刪除姓名
     */
    private static void deleteName() {
        System.out.print("請輸入要刪除的姓名：");
        String name = scanner.nextLine().trim();
        
        // 檢查是否為空白
        if (name.isEmpty()) {
            System.out.println("❌ 錯誤：請輸入有效的姓名！");
            return;
        }
        
        // 搜尋並刪除（忽略大小寫）
        boolean found = false;
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).equalsIgnoreCase(name)) {
                String deletedName = nameList.remove(i);
                System.out.println("✅ 成功刪除姓名：" + deletedName);
                System.out.println("目前共有 " + nameList.size() + " 位成員");
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("❌ 找不到姓名 \"" + name + "\"");
        }
    }
    
    /**
     * 功能5：顯示全部姓名
     */
    private static void showAllNames() {
        if (nameList.isEmpty()) {
            System.out.println("📋 名單目前為空，尚無任何成員");
            return;
        }
        
        System.out.println("📋 全部名單（共 " + nameList.size() + " 位成員）：");
        System.out.println("  編號  姓名");
        System.out.println("  ----------");
        for (int i = 0; i < nameList.size(); i++) {
            System.out.printf("  %-4d  %s%n", (i + 1), nameList.get(i));
        }
    }
    
    /**
     * 檢查姓名是否已存在（忽略大小寫）
     */
    private static boolean isNameExists(String name) {
        for (String existingName : nameList) {
            if (existingName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 搜尋相符的姓名（忽略大小寫）
     */
    private static ArrayList<String> findNames(String name) {
        ArrayList<String> results = new ArrayList<>();
        for (String existingName : nameList) {
            if (existingName.equalsIgnoreCase(name)) {
                results.add(existingName);
            }
        }
        return results;
    }
}
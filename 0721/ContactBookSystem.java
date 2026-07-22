import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {
    
    private static ArrayList<Contact> contactList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== 聯絡人管理系統 ===");
        System.out.println("歡迎使用聯絡人管理系統！");
        
        // 加入一些範例聯絡人
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
                    addContact();
                    break;
                case 2:
                    searchContact();
                    break;
                case 3:
                    updatePhone();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    showAllContacts();
                    break;
                case 6:
                    showContactCount();
                    break;
                case 7:
                    System.out.println("感謝使用聯絡人管理系統，再見！");
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
        contactList.add(new Contact("C001", "張三", "0912-345-678", "zhangsan@email.com"));
        contactList.add(new Contact("C002", "李四", "0923-456-789", "lisi@email.com"));
        contactList.add(new Contact("C003", "王五", "0934-567-890", "wangwu@email.com"));
        contactList.add(new Contact("C004", "趙六", "0945-678-901", "zhaoliu@email.com"));
        contactList.add(new Contact("C005", "陳七", "0956-789-012", "chenqi@email.com"));
        System.out.println("✅ 已載入 " + contactList.size() + " 筆範例聯絡人資料");
    }
    
    /**
     * 顯示功能選單
     */
    private static void showMenu() {
        System.out.println("\n=== 功能選單 ===");
        System.out.println("1. 新增聯絡人");
        System.out.println("2. 搜尋聯絡人");
        System.out.println("3. 修改電話");
        System.out.println("4. 刪除聯絡人");
        System.out.println("5. 顯示完整清單");
        System.out.println("6. 顯示聯絡人總數");
        System.out.println("7. 離開系統");
    }
    
    /**
     * 功能1：新增聯絡人
     */
    private static void addContact() {
        System.out.print("請輸入聯絡人代碼：");
        String code = scanner.nextLine().trim();
        
        // 檢查代碼是否為空白
        if (isBlankInput(code)) {
            System.out.println("❌ 錯誤：聯絡人代碼不得為空白！");
            return;
        }
        
        // 檢查代碼是否已存在
        if (findContactByCode(code) != null) {
            System.out.println("❌ 錯誤：聯絡人代碼 \"" + code + "\" 已存在！");
            return;
        }
        
        System.out.print("請輸入姓名：");
        String name = scanner.nextLine().trim();
        
        // 檢查姓名是否為空白（重點要求）
        if (isBlankInput(name)) {
            System.out.println("❌ 錯誤：姓名不得為空白！");
            return;
        }
        
        System.out.print("請輸入電話：");
        String phone = scanner.nextLine().trim();
        
        // 檢查電話是否為空白
        if (isBlankInput(phone)) {
            System.out.println("❌ 錯誤：電話不得為空白！");
            return;
        }
        
        System.out.print("請輸入電子郵件：");
        String email = scanner.nextLine().trim();
        
        // 檢查電子郵件是否為空白（可選，但為了完整性）
        if (isBlankInput(email)) {
            System.out.println("❌ 錯誤：電子郵件不得為空白！");
            return;
        }
        
        // 新增聯絡人
        Contact newContact = new Contact(code, name, phone, email);
        contactList.add(newContact);
        System.out.println("✅ 成功新增聯絡人：" + newContact);
        System.out.println("目前共有 " + contactList.size() + " 位聯絡人");
    }
    
    /**
     * 功能2：搜尋聯絡人
     */
    private static void searchContact() {
        System.out.print("請輸入要搜尋的聯絡人代碼：");
        String code = scanner.nextLine().trim();
        
        // 檢查代碼是否為空白
        if (isBlankInput(code)) {
            System.out.println("❌ 錯誤：請輸入有效的聯絡人代碼！");
            return;
        }
        
        Contact found = findContactByCode(code);
        
        if (found != null) {
            System.out.println("🔍 找到聯絡人：");
            System.out.println("  " + found);
            System.out.println("  姓名：" + found.getName());
            System.out.println("  電話：" + found.getPhone());
            System.out.println("  電子郵件：" + found.getEmail());
        } else {
            System.out.println("❌ 找不到聯絡人代碼 \"" + code + "\"");
        }
    }
    
    /**
     * 功能3：修改電話
     */
    private static void updatePhone() {
        System.out.print("請輸入要修改電話的聯絡人代碼：");
        String code = scanner.nextLine().trim();
        
        // 檢查代碼是否為空白
        if (isBlankInput(code)) {
            System.out.println("❌ 錯誤：請輸入有效的聯絡人代碼！");
            return;
        }
        
        Contact found = findContactByCode(code);
        
        if (found == null) {
            System.out.println("❌ 找不到聯絡人代碼 \"" + code + "\"");
            return;
        }
        
        System.out.println("目前聯絡人資訊：");
        System.out.println("  " + found);
        
        System.out.print("請輸入新的電話號碼：");
        String newPhone = scanner.nextLine().trim();
        
        // 檢查新電話是否為空白
        if (isBlankInput(newPhone)) {
            System.out.println("❌ 錯誤：電話號碼不得為空白！");
            return;
        }
        
        // 修改電話
        String oldPhone = found.getPhone();
        found.setPhone(newPhone);
        System.out.println("✅ 成功將 \"" + found.getName() + "\" 的電話從 \"" + oldPhone + "\" 修改為 \"" + newPhone + "\"");
    }
    
    /**
     * 功能4：刪除聯絡人
     */
    private static void deleteContact() {
        System.out.print("請輸入要刪除的聯絡人代碼：");
        String code = scanner.nextLine().trim();
        
        // 檢查代碼是否為空白
        if (isBlankInput(code)) {
            System.out.println("❌ 錯誤：請輸入有效的聯絡人代碼！");
            return;
        }
        
        Contact found = findContactByCode(code);
        
        if (found == null) {
            System.out.println("❌ 找不到聯絡人代碼 \"" + code + "\"");
            return;
        }
        
        // 顯示要刪除的聯絡人資訊，確認刪除
        System.out.println("確定要刪除以下聯絡人嗎？");
        System.out.println("  " + found);
        System.out.print("請輸入 y 確認刪除，其他鍵取消：");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (!confirm.equals("y")) {
            System.out.println("❌ 已取消刪除操作");
            return;
        }
        
        // 刪除聯絡人
        boolean removed = contactList.remove(found);
        if (removed) {
            System.out.println("✅ 成功刪除聯絡人：" + found.getName());
            System.out.println("目前共有 " + contactList.size() + " 位聯絡人");
        } else {
            System.out.println("❌ 刪除失敗，請稍後再試");
        }
    }
    
    /**
     * 功能5：顯示完整清單
     */
    private static void showAllContacts() {
        if (contactList.isEmpty()) {
            System.out.println("📋 聯絡人清單為空，尚無任何聯絡人");
            return;
        }
        
        System.out.println("📋 完整聯絡人清單（共 " + contactList.size() + " 位）：");
        System.out.println("  編號  代碼  姓名  電話          電子郵件");
        System.out.println("  ---------------------------------------------");
        for (int i = 0; i < contactList.size(); i++) {
            Contact c = contactList.get(i);
            System.out.printf("  %-4d  %-5s %-4s  %-12s  %s%n", 
                (i + 1), 
                c.getCode(), 
                c.getName(),
                c.getPhone(),
                c.getEmail());
        }
    }
    
    /**
     * 功能6：顯示聯絡人總數
     */
    private static void showContactCount() {
        int count = getContactCount();
        System.out.println("📊 目前聯絡人總數：" + count + " 位");
        
        if (count > 0) {
            System.out.println("  最新加入的聯絡人：");
            Contact latest = contactList.get(count - 1);
            System.out.println("  " + latest);
        }
    }
    
    /**
     * 自訂方法1：依代碼搜尋聯絡人
     */
    private static Contact findContactByCode(String code) {
        for (Contact contact : contactList) {
            if (contact.getCode().equalsIgnoreCase(code)) {
                return contact;
            }
        }
        return null;
    }
    
    /**
     * 自訂方法2：檢查輸入是否為空白
     */
    private static boolean isBlankInput(String input) {
        return input == null || input.trim().isEmpty();
    }
    
    /**
     * 自訂方法3：檢查聯絡人是否存在
     */
    private static boolean isContactExists(String code) {
        return findContactByCode(code) != null;
    }
    
    /**
     * 自訂方法4：取得聯絡人總數
     */
    private static int getContactCount() {
        return contactList.size();
    }
    
    /**
     * 自訂方法5：驗證電話格式（簡易版）
     */
    private static boolean isValidPhone(String phone) {
        // 簡單驗證：至少 8 個字元，包含數字和連字號
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        // 移除連字號後檢查是否全為數字
        String cleaned = phone.replace("-", "").replace(" ", "");
        return cleaned.matches("\\d+") && cleaned.length() >= 8;
    }
    
    /**
     * 自訂方法6：顯示單一聯絡人詳細資訊
     */
    private static void printContactDetails(Contact contact) {
        if (contact == null) {
            System.out.println("❌ 聯絡人不存在");
            return;
        }
        System.out.println("=== 聯絡人詳細資訊 ===");
        System.out.println("代碼：" + contact.getCode());
        System.out.println("姓名：" + contact.getName());
        System.out.println("電話：" + contact.getPhone());
        System.out.println("電子郵件：" + contact.getEmail());
    }
}
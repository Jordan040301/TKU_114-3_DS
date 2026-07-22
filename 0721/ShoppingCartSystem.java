import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {
    
    private static ArrayList<CartItem> cart = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== 購物車系統 ===");
        System.out.println("歡迎使用購物車管理系統！");
        
        // 加入一些範例商品
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
                    addItem();
                    break;
                case 2:
                    updateQuantity();
                    break;
                case 3:
                    removeItem();
                    break;
                case 4:
                    showCart();
                    break;
                case 5:
                    calculateTotal();
                    break;
                case 6:
                    clearCart();
                    break;
                case 7:
                    System.out.println("感謝使用購物車系統，再見！");
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
        cart.add(new CartItem("P001", "筆記型電腦", 25000.00, 2));
        cart.add(new CartItem("P002", "無線滑鼠", 599.00, 3));
        cart.add(new CartItem("P003", "機械鍵盤", 1899.00, 1));
        cart.add(new CartItem("P004", "USB隨身碟", 399.00, 5));
        System.out.println("✅ 已載入 " + cart.size() + " 筆範例商品資料");
    }
    
    /**
     * 顯示功能選單
     */
    private static void showMenu() {
        System.out.println("\n=== 功能選單 ===");
        System.out.println("1. 加入商品");
        System.out.println("2. 修改數量");
        System.out.println("3. 刪除商品");
        System.out.println("4. 顯示購物車");
        System.out.println("5. 計算總金額");
        System.out.println("6. 清空購物車");
        System.out.println("7. 離開系統");
    }
    
    /**
     * 功能1：加入商品
     * 重複加入相同代碼時增加數量，不建立重複物品
     */
    private static void addItem() {
        System.out.print("請輸入商品代碼：");
        String code = scanner.nextLine().trim();
        
        // 檢查代碼是否為空白
        if (isBlankInput(code)) {
            System.out.println("❌ 錯誤：商品代碼不得為空白！");
            return;
        }
        
        // 檢查是否已存在相同代碼
        CartItem existingItem = findItemByCode(code);
        
        if (existingItem != null) {
            // 重複加入相同代碼：增加數量
            System.out.print("商品 \"" + existingItem.getName() + "\" 已在購物車中，");
            System.out.print("請輸入要增加的數量：");
            
            if (!scanner.hasNextInt()) {
                System.out.println("❌ 錯誤：請輸入有效的數字！");
                scanner.next();
                return;
            }
            
            int addQuantity = scanner.nextInt();
            scanner.nextLine();
            
            if (addQuantity <= 0) {
                System.out.println("❌ 錯誤：數量必須大於 0！");
                return;
            }
            
            existingItem.addQuantity(addQuantity);
            System.out.println("✅ 已增加 " + addQuantity + " 件 \"" + existingItem.getName() + "\"");
            System.out.println("   目前數量：" + existingItem.getQuantity() + " 件");
            return;
        }
        
        // 新商品：輸入完整資訊
        System.out.print("請輸入商品名稱：");
        String name = scanner.nextLine().trim();
        
        if (isBlankInput(name)) {
            System.out.println("❌ 錯誤：商品名稱不得為空白！");
            return;
        }
        
        System.out.print("請輸入商品單價：");
        if (!scanner.hasNextDouble()) {
            System.out.println("❌ 錯誤：請輸入有效的金額！");
            scanner.next();
            return;
        }
        double price = scanner.nextDouble();
        
        if (price < 0) {
            System.out.println("❌ 錯誤：單價不得為負數！");
            return;
        }
        
        System.out.print("請輸入數量：");
        if (!scanner.hasNextInt()) {
            System.out.println("❌ 錯誤：請輸入有效的數量！");
            scanner.next();
            return;
        }
        int quantity = scanner.nextInt();
        scanner.nextLine();
        
        if (quantity <= 0) {
            System.out.println("❌ 錯誤：數量必須大於 0！");
            return;
        }
        
        // 新增商品
        CartItem newItem = new CartItem(code, name, price, quantity);
        cart.add(newItem);
        System.out.println("✅ 成功加入商品：" + newItem);
        System.out.println("購物車目前共有 " + cart.size() + " 項商品");
    }
    
    /**
     * 功能2：修改數量
     * 數量小於或等於0時不接受更新
     */
    private static void updateQuantity() {
        System.out.print("請輸入要修改數量的商品代碼：");
        String code = scanner.nextLine().trim();
        
        if (isBlankInput(code)) {
            System.out.println("❌ 錯誤：請輸入有效的商品代碼！");
            return;
        }
        
        CartItem item = findItemByCode(code);
        
        if (item == null) {
            System.out.println("❌ 找不到商品代碼 \"" + code + "\"");
            return;
        }
        
        System.out.println("目前商品資訊：");
        System.out.println("  " + item);
        
        System.out.print("請輸入新的數量（必須大於 0）：");
        
        if (!scanner.hasNextInt()) {
            System.out.println("❌ 錯誤：請輸入有效的數字！");
            scanner.next();
            return;
        }
        
        int newQuantity = scanner.nextInt();
        scanner.nextLine();
        
        // 數量小於或等於0時不接受更新
        if (newQuantity <= 0) {
            System.out.println("❌ 錯誤：數量必須大於 0，不接受更新！");
            return;
        }
        
        int oldQuantity = item.getQuantity();
        item.setQuantity(newQuantity);
        System.out.println("✅ 成功將 \"" + item.getName() + "\" 的數量從 " + oldQuantity + " 修改為 " + newQuantity);
        System.out.println("   新小計：$" + String.format("%.2f", item.getSubtotal()));
    }
    
    /**
     * 功能3：刪除商品
     */
    private static void removeItem() {
        System.out.print("請輸入要刪除的商品代碼：");
        String code = scanner.nextLine().trim();
        
        if (isBlankInput(code)) {
            System.out.println("❌ 錯誤：請輸入有效的商品代碼！");
            return;
        }
        
        CartItem item = findItemByCode(code);
        
        if (item == null) {
            System.out.println("❌ 找不到商品代碼 \"" + code + "\"");
            return;
        }
        
        System.out.println("確定要刪除以下商品嗎？");
        System.out.println("  " + item);
        System.out.print("請輸入 y 確認刪除，其他鍵取消：");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (!confirm.equals("y")) {
            System.out.println("❌ 已取消刪除操作");
            return;
        }
        
        boolean removed = cart.remove(item);
        if (removed) {
            System.out.println("✅ 成功刪除商品：" + item.getName());
            System.out.println("購物車目前共有 " + cart.size() + " 項商品");
        } else {
            System.out.println("❌ 刪除失敗，請稍後再試");
        }
    }
    
    /**
     * 功能4：顯示購物車
     */
    private static void showCart() {
        if (cart.isEmpty()) {
            System.out.println("📋 購物車目前為空");
            return;
        }
        
        System.out.println("📋 購物車內容（共 " + cart.size() + " 項商品）：");
        System.out.println("  編號  代碼  名稱          單價     數量  小計");
        System.out.println("  -----------------------------------------------");
        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);
            System.out.printf("  %-4d  %-5s %-10s  $%-6.2f  %-4d  $%-6.2f%n",
                (i + 1),
                item.getCode(),
                item.getName(),
                item.getPrice(),
                item.getQuantity(),
                item.getSubtotal());
        }
        System.out.println("  -----------------------------------------------");
        System.out.printf("  總金額：$%.2f%n", calculateTotalAmount());
    }
    
    /**
     * 功能5：計算總金額
     */
    private static void calculateTotal() {
        if (cart.isEmpty()) {
            System.out.println("📊 購物車為空，總金額：$0.00");
            return;
        }
        
        double total = calculateTotalAmount();
        int itemCount = getTotalItemCount();
        int productCount = cart.size();
        
        System.out.println("📊 購物車統計：");
        System.out.println("  商品種類數：" + productCount);
        System.out.println("  總數量：" + itemCount);
        System.out.println("  總金額：$" + String.format("%.2f", total));
        
        // 顯示各商品佔比
        if (total > 0) {
            System.out.println("\n  各商品佔比：");
            for (CartItem item : cart) {
                double percentage = (item.getSubtotal() / total) * 100;
                System.out.printf("    %s：$%.2f (%.1f%%)%n", 
                    item.getName(), item.getSubtotal(), percentage);
            }
        }
    }
    
    /**
     * 功能6：清空購物車
     */
    private static void clearCart() {
        if (cart.isEmpty()) {
            System.out.println("📋 購物車已經是空的");
            return;
        }
        
        System.out.print("確定要清空購物車嗎？（請輸入 y 確認）：");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (!confirm.equals("y")) {
            System.out.println("❌ 已取消清空操作");
            return;
        }
        
        cart.clear();
        System.out.println("✅ 購物車已清空");
    }
    
    /**
     * 自訂方法1：依代碼搜尋商品
     */
    private static CartItem findItemByCode(String code) {
        for (CartItem item : cart) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
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
     * 自訂方法3：計算總金額
     */
    private static double calculateTotalAmount() {
        double total = 0;
        for (CartItem item : cart) {
            total += item.getSubtotal();
        }
        return total;
    }
    
    /**
     * 自訂方法4：計算總數量
     */
    private static int getTotalItemCount() {
        int total = 0;
        for (CartItem item : cart) {
            total += item.getQuantity();
        }
        return total;
    }
    
    /**
     * 自訂方法5：檢查商品是否存在
     */
    private static boolean isItemExists(String code) {
        return findItemByCode(code) != null;
    }
}
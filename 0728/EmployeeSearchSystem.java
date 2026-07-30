import java.util.Scanner;

public class EmployeeSearchSystem {
    // 員工陣列 (依照編號排序)
    private static Employee[] employees = {
        new Employee("E001", "張志明", "資訊部", "1234"),
        new Employee("E002", "李美玲", "人事部", "2345"),
        new Employee("E003", "王建國", "業務部", "3456"),
        new Employee("E004", "林淑芬", "財務部", "4567"),
        new Employee("E005", "陳志豪", "資訊部", "5678"),
        new Employee("E006", "楊雅婷", "人事部", "6789"),
        new Employee("E007", "劉家宏", "業務部", "7890"),
        new Employee("E008", "蔡宜君", "行銷部", "8901"),
        new Employee("E009", "周明德", "財務部", "9012"),
        new Employee("E010", "吳欣怡", "資訊部", "0123"),
        new Employee("E011", "許建銘", "行銷部", "1235"),
        new Employee("E012", "鄭雅文", "業務部", "2346")
    };
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║      員工編號查詢系統                      ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        // 顯示所有員工 (確認排序)
        displayAllEmployees();
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n請選擇功能:");
            System.out.println("1. 查詢員工 (二分搜尋)");
            System.out.println("2. 顯示所有員工");
            System.out.println("3. 退出系統");
            System.out.print("請輸入選項 (1-3): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除緩衝區
            
            switch (choice) {
                case 1:
                    searchEmployee(scanner);
                    break;
                case 2:
                    displayAllEmployees();
                    break;
                case 3:
                    System.out.println("感謝使用員工查詢系統！");
                    scanner.close();
                    return;
                default:
                    System.out.println("無效的選項，請重新輸入！");
            }
        }
    }
    
    /**
     * 搜尋員工 (二分搜尋)
     */
    public static void searchEmployee(Scanner scanner) {
        System.out.print("\n請輸入要查詢的員工編號: ");
        String targetId = scanner.nextLine().trim();
        
        // 處理空白輸入
        if (targetId.isEmpty()) {
            System.out.println("⚠ 錯誤: 員工編號不能為空白！");
            return;
        }
        
        System.out.println("\n開始二分搜尋...");
        System.out.println("------------------------");
        
        // 執行二分搜尋
        int result = binarySearch(employees, targetId);
        
        if (result != -1) {
            System.out.println("\n✅ 找到員工！");
            employees[result].displayInfo();
        } else {
            System.out.println("\n❌ 找不到員工編號: " + targetId);
            System.out.println("建議: 請確認編號是否正確，或使用 '顯示所有員工' 查看可用編號");
        }
    }
    
    /**
     * 二分搜尋演算法
     * @param array 已排序的員工陣列
     * @param target 要搜尋的員工編號
     * @return 找到時回傳索引，找不到時回傳 -1
     */
    public static int binarySearch(Employee[] array, String target) {
        int low = 0;
        int high = array.length - 1;
        int step = 0;
        
        while (low <= high) {
            step++;
            int mid = low + (high - low) / 2;
            String midId = array[mid].getId();
            
            System.out.println("第 " + step + " 次搜尋: low=" + low + 
                             ", mid=" + mid + ", high=" + high);
            System.out.println("  比對: array[" + mid + "].getId() = \"" + midId + "\"");
            System.out.println("  目標: \"" + target + "\"");
            
            int compareResult = midId.compareTo(target);
            
            if (compareResult == 0) {
                System.out.println("  ✓ 找到目標！");
                return mid;
            } else if (compareResult < 0) {
                System.out.println("  " + midId + " < " + target + "，向右搜尋");
                low = mid + 1;
            } else {
                System.out.println("  " + midId + " > " + target + "，向左搜尋");
                high = mid - 1;
            }
            System.out.println("  更新範圍: low=" + low + ", high=" + high);
            System.out.println("------------------------");
        }
        
        System.out.println("✗ 搜尋範圍縮小為空，找不到目標");
        return -1;
    }
    
    /**
     * 顯示所有員工
     */
    public static void displayAllEmployees() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║      所有員工列表 (已排序)                 ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.printf("║ %-8s %-8s %-10s %-6s ║%n", 
                         "編號", "姓名", "部門", "分機");
        System.out.println("╠════════════════════════════════════════════╣");
        
        for (Employee emp : employees) {
            System.out.printf("║ %-8s %-8s %-10s %-6s ║%n", 
                             emp.getId(), 
                             emp.getName(), 
                             emp.getDepartment(), 
                             emp.getExtension());
        }
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("總共 " + employees.length + " 位員工");
    }
}
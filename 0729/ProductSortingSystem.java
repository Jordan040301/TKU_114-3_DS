import java.util.Scanner;

public class ProductSortingSystem {

    public static void main(String[] args) {
        // 建立原始商品資料（至少10筆）
        StoreProduct[] originalProducts = {
            new StoreProduct("P001", "筆記型電腦", 35000, 15),
            new StoreProduct("P002", "無線滑鼠", 899, 50),
            new StoreProduct("P003", "機械鍵盤", 2490, 30),
            new StoreProduct("P004", "4K顯示器", 12900, 8),
            new StoreProduct("P005", "USB耳機", 899, 25),
            new StoreProduct("P006", "外接硬碟", 2990, 12),
            new StoreProduct("P007", "手機支架", 899, 100),
            new StoreProduct("P008", "藍牙喇叭", 1990, 20),
            new StoreProduct("P009", "充電線", 299, 200),
            new StoreProduct("P010", "無線充電器", 1290, 35),
            new StoreProduct("P011", "行動電源", 1590, 45),
            new StoreProduct("P012", "智慧手錶", 5990, 10)
        };

        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║              ★ 商品排序系統 ★                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        
        System.out.println("\n=== 原始商品資料 ===");
        displayProducts(originalProducts, "原始順序");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
            System.out.println("║  請選擇排序方式：                                        ║");
            System.out.println("║  1. 價格升冪（低 → 高）                                 ║");
            System.out.println("║  2. 價格降冪（高 → 低）                                 ║");
            System.out.println("║  3. 庫存降冪（多 → 少）                                 ║");
            System.out.println("║  4. 顯示原始資料                                         ║");
            System.out.println("║  0. 結束程式                                             ║");
            System.out.println("╚═══════════════════════════════════════════════════════════╝");
            System.out.print("請輸入選項：");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    sortByPriceAscending(originalProducts);
                    break;
                case 2:
                    sortByPriceDescending(originalProducts);
                    break;
                case 3:
                    sortByStockDescending(originalProducts);
                    break;
                case 4:
                    displayProducts(originalProducts, "原始資料");
                    break;
                case 0:
                    System.out.println("\n感謝使用商品排序系統！");
                    break;
                default:
                    System.out.println("\n❌ 無效選項，請重新輸入！");
            }

        } while (choice != 0);

        scanner.close();
    }

    /**
     * 排序方式1：價格升冪（低 → 高）
     * 使用插入排序
     */
    public static void sortByPriceAscending(StoreProduct[] original) {
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("  📊 排序方式：價格升冪（低 → 高）");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        // 複製原始資料
        StoreProduct[] products = copyProducts(original);
        
        int comparisonCount = 0;
        int shiftCount = 0;

        for (int index = 1; index < products.length; index++) {
            StoreProduct key = products[index];
            int position = index - 1;

            while (position >= 0 && products[position].getPrice() > key.getPrice()) {
                comparisonCount++;
                products[position + 1] = products[position];
                shiftCount++;
                position--;
            }

            if (position >= 0) {
                comparisonCount++;
            }

            products[position + 1] = key;
        }

        displayProducts(products, "價格升冪 ▲");
        displayStatistics(comparisonCount, shiftCount);
    }

    /**
     * 排序方式2：價格降冪（高 → 低）
     * 使用選擇排序
     */
    public static void sortByPriceDescending(StoreProduct[] original) {
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("  📊 排序方式：價格降冪（高 → 低）");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        // 複製原始資料
        StoreProduct[] products = copyProducts(original);
        
        int comparisonCount = 0;
        int swapCount = 0;

        for (int i = 0; i < products.length - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < products.length; j++) {
                comparisonCount++;
                if (products[j].getPrice() > products[maxIndex].getPrice()) {
                    maxIndex = j;
                }
            }

            if (maxIndex != i) {
                StoreProduct temp = products[i];
                products[i] = products[maxIndex];
                products[maxIndex] = temp;
                swapCount++;
            }
        }

        displayProducts(products, "價格降冪 ▼");
        displayStatistics(comparisonCount, swapCount);
    }

    /**
     * 排序方式3：庫存降冪（多 → 少）
     * 使用插入排序
     */
    public static void sortByStockDescending(StoreProduct[] original) {
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("  📊 排序方式：庫存降冪（多 → 少）");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        // 複製原始資料
        StoreProduct[] products = copyProducts(original);
        
        int comparisonCount = 0;
        int shiftCount = 0;

        for (int index = 1; index < products.length; index++) {
            StoreProduct key = products[index];
            int position = index - 1;

            while (position >= 0 && products[position].getStock() < key.getStock()) {
                comparisonCount++;
                products[position + 1] = products[position];
                shiftCount++;
                position--;
            }

            if (position >= 0) {
                comparisonCount++;
            }

            products[position + 1] = key;
        }

        displayProducts(products, "庫存降冪 ▼");
        displayStatistics(comparisonCount, shiftCount);
    }

    /**
     * 複製商品陣列
     */
    public static StoreProduct[] copyProducts(StoreProduct[] original) {
        StoreProduct[] copy = new StoreProduct[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = new StoreProduct(
                original[i].getId(),
                original[i].getName(),
                original[i].getPrice(),
                original[i].getStock()
            );
        }
        return copy;
    }

    /**
     * 顯示商品列表
     */
    public static void displayProducts(StoreProduct[] products, String title) {
        System.out.println("\n┌───────┬────────────┬────────────┬────────────┐");
        System.out.printf("│ %-5s │ %-10s │ %-10s │ %-10s │\n", 
                         "位置", "名稱", "價格", "庫存");
        System.out.println("├───────┼────────────┼────────────┼────────────┤");
        
        for (int i = 0; i < products.length; i++) {
            System.out.printf("│  %3d  │ %-10s │  %7d  │  %7d  │\n", 
                            (i + 1),
                            products[i].getName(),
                            products[i].getPrice(),
                            products[i].getStock());
        }
        System.out.println("└───────┴────────────┴────────────┴────────────┘");
        System.out.println("排序方向：" + title);
    }

    /**
     * 顯示統計資訊
     */
    public static void displayStatistics(int comparisonCount, int operationCount) {
        System.out.println("\n📊 統計資訊：");
        System.out.println("  比較次數：" + comparisonCount);
        System.out.println("  操作次數：" + operationCount);
        System.out.println("  商品總數：" + 12);
    }

    /**
     * 顯示原始資料（含詳細資訊）
     */
    public static void displayOriginalData(StoreProduct[] products) {
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                   原始商品資料                            ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        displayProducts(products, "原始順序（無排序）");
    }
}
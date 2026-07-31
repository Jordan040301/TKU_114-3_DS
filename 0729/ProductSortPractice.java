public class ProductSortPractice {

    public static void main(String[] args) {
        // 至少8筆商品，包含相同價格
        Product1[] products = {
            new Product1("P001", "筆記型電腦", 35000, 15),
            new Product1("P002", "無線滑鼠", 899, 50),
            new Product1("P003", "機械鍵盤", 2490, 30),
            new Product1("P004", "4K顯示器", 12900, 8),
            new Product1("P005", "USB耳機", 899, 25),
            new Product1("P006", "外接硬碟", 2990, 12),
            new Product1("P007", "手機支架", 899, 100),
            new Product1("P008", "藍牙喇叭", 1990, 20),
            new Product1("P009", "充電線", 299, 200),
            new Product1("P010", "無線充電器", 1290, 35)
        };

        System.out.println("=== 排序前的商品列表 ===");
        displayProducts(products);

        System.out.println("\n=== 插入排序過程（依價格升序） ===");
        insertionSortByPrice(products);

        System.out.println("\n=== 排序後的商品列表（依價格升序） ===");
        displayProducts(products);
    }

    /**
     * 插入排序（依價格升序，穩定排序）
     */
    public static void insertionSortByPrice(Product1[] products) {
        int comparisonCount = 0;
        int shiftCount = 0;

        for (int index = 1; index < products.length; index++) {
            Product1 key = products[index];
            int position = index - 1;

            System.out.println("\n第 " + index + " 回合：key = " + key.getName() + 
                             " (價格: " + key.getPrice() + ")");

            while (position >= 0 && products[position].getPrice() > key.getPrice()) {
                comparisonCount++;
                System.out.println("  比較：" + products[position].getName() + 
                                 " (價格 " + products[position].getPrice() + 
                                 ") > " + key.getName() + " (價格 " + key.getPrice() + 
                                 ") → 移動");
                
                products[position + 1] = products[position];
                shiftCount++;
                position--;
            }

            if (position >= 0) {
                comparisonCount++;
                if (products[position].getPrice() == key.getPrice()) {
                    System.out.println("  比較：" + products[position].getName() + 
                                     " (價格 " + products[position].getPrice() + 
                                     ") == " + key.getName() + " (價格 " + key.getPrice() + 
                                     ") → 不移動，保持相對順序");
                } else {
                    System.out.println("  比較：" + products[position].getName() + 
                                     " (價格 " + products[position].getPrice() + 
                                     ") < " + key.getName() + " (價格 " + key.getPrice() + 
                                     ") → 插入在此之後");
                }
            }

            int insertPosition = position + 1;
            products[insertPosition] = key;
            System.out.println("  插入位置：" + insertPosition);
            System.out.println("  目前陣列：");
            displayProductsBrief(products);
        }

        System.out.println("\n=== 排序統計 ===");
        System.out.println("總比較次數：" + comparisonCount);
        System.out.println("總移動次數：" + shiftCount);
    }

    public static void displayProducts(Product1[] products) {
        System.out.println("編號\t名稱\t\t價格\t庫存");
        System.out.println("================================================");
        for (Product1 p : products) {
            System.out.printf("%s\t%-10s\t%d\t%d\n", 
                            p.getId(), p.getName(), p.getPrice(), p.getStock());
        }
    }

    public static void displayProductsBrief(Product1[] products) {
        System.out.print("  [");
        for (int i = 0; i < products.length; i++) {
            System.out.print(products[i].getName() + "(" + products[i].getPrice() + ")");
            if (i < products.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
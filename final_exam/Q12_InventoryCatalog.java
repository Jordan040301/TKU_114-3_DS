import java.util.ArrayList;

class Q12_Product {
    private String id;
    private String name;
    private int price;
    private int stock;

    public Q12_Product(String id, String name, int price, int stock) {
        // 处理id：null转空字符串，去除前后空白
        this.id = (id == null) ? "" : id.trim();
        
        // 处理name：null转空字符串，去除前后空白
        this.name = (name == null) ? "" : name.trim();
        
        // 处理price：小于0改成0
        this.price = (price < 0) ? 0 : price;
        
        // 处理stock：小于0改成0
        this.stock = (stock < 0) ? 0 : stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return id + " " + name + " price=" + price + " stock=" + stock;
    }
}

public class Q12_InventoryCatalog {
    private ArrayList<Q12_Product> products = new ArrayList<>();

    public boolean addProduct(Q12_Product product) {
        // 拒绝null
        if (product == null) {
            return false;
        }
        
        // 拒绝空编号
        String id = product.getId();
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        
        // 拒绝重复编号（忽略大小写）
        for (Q12_Product existing : products) {
            if (existing.getId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        
        products.add(product);
        return true;
    }

    public Q12_Product[] createSortedCopyById() {
        if (products.isEmpty()) {
            return new Q12_Product[0];
        }
        
        // 创建副本
        Q12_Product[] copy = new Q12_Product[products.size()];
        for (int i = 0; i < products.size(); i++) {
            copy[i] = products.get(i);
        }
        
        // 使用Merge Sort排序
        Q12_Product[] temp = new Q12_Product[copy.length];
        mergeSort(copy, temp, 0, copy.length - 1);
        
        return copy;
    }

    private void mergeSort(Q12_Product[] data, Q12_Product[] temp, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int mid = left + (right - left) / 2;
        mergeSort(data, temp, left, mid);
        mergeSort(data, temp, mid + 1, right);
        merge(data, temp, left, mid, right);
    }

    private void merge(Q12_Product[] data, Q12_Product[] temp, int left, int mid, int right) {
        // 复制到temp
        for (int i = left; i <= right; i++) {
            temp[i] = data[i];
        }
        
        int i = left;
        int j = mid + 1;
        int k = left;
        
        // 按编号升序合并
        while (i <= mid && j <= right) {
            if (temp[i].getId().compareToIgnoreCase(temp[j].getId()) <= 0) {
                data[k] = temp[i];
                i++;
            } else {
                data[k] = temp[j];
                j++;
            }
            k++;
        }
        
        // 复制剩余元素
        while (i <= mid) {
            data[k] = temp[i];
            i++;
            k++;
        }
        
        while (j <= right) {
            data[k] = temp[j];
            j++;
            k++;
        }
    }

    public Q12_Product binarySearchById(Q12_Product[] sortedProducts, String id) {
        if (sortedProducts == null || id == null || id.trim().isEmpty()) {
            return null;
        }
        
        int left = 0;
        int right = sortedProducts.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = sortedProducts[mid].getId().compareToIgnoreCase(id);
            
            if (compare == 0) {
                return sortedProducts[mid];
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return null;
    }

    public ArrayList<Q12_Product> findByNameKeyword(String keyword) {
        ArrayList<Q12_Product> result = new ArrayList<>();
        
        // null或空白关键字返回空集合
        if (keyword == null || keyword.trim().isEmpty()) {
            return result;
        }
        
        String lowerKeyword = keyword.toLowerCase().trim();
        
        for (Q12_Product product : products) {
            if (product.getName().toLowerCase().contains(lowerKeyword)) {
                result.add(product);
            }
        }
        
        return result;
    }

    public ArrayList<Q12_Product> findLowStock(int maximumStock) {
        ArrayList<Q12_Product> result = new ArrayList<>();
        
        for (Q12_Product product : products) {
            if (product.getStock() <= maximumStock) {
                result.add(product);
            }
        }
        
        return result;
    }

    public int totalInventoryValue() {
        int total = 0;
        
        for (Q12_Product product : products) {
            total += product.getPrice() * product.getStock();
        }
        
        return total;
    }
}

class Q12_InventoryDemo {
    public static void main(String[] args) {
        Q12_InventoryCatalog catalog = new Q12_InventoryCatalog();

        catalog.addProduct(
            new Q12_Product("P205", "Wireless Mouse", 650, 4)
        );

        catalog.addProduct(
            new Q12_Product("P101", "Keyboard", 1200, 8)
        );

        catalog.addProduct(
            new Q12_Product("P330", "Gaming Mouse", 1800, 2)
        );

        catalog.addProduct(
            new Q12_Product("P150", "Monitor", 5200, 5)
        );

        Q12_Product[] sorted = catalog.createSortedCopyById();
        System.out.println("依編號排序：");
        for (Q12_Product product : sorted) {
            System.out.println(product);
        }

        System.out.println("查詢 P150：");
        Q12_Product found = catalog.binarySearchById(sorted, "p150");
        System.out.println(found);
        
        System.out.println("名稱包含 mouse：");
        ArrayList<Q12_Product> mouseResults = catalog.findByNameKeyword("mouse");
        for (Q12_Product product : mouseResults) {
            System.out.println(product);
        }
        
        System.out.println("低庫存：");
        ArrayList<Q12_Product> lowStock = catalog.findLowStock(4);
        for (Q12_Product product : lowStock) {
            System.out.println(product);
        }
        
        System.out.println("庫存總值：");
        System.out.println(catalog.totalInventoryValue());
    }
}
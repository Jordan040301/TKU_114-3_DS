public class NumberHistoryList {
    
    // 節點類別（內部類別）
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    private int size;
    
    // 建構子
    public NumberHistoryList() {
        this.head = null;
        this.size = 0;
    }
    
    public static void main(String[] args) {
        System.out.println("=== 數字記錄串列測試 ===");
        NumberHistoryList list = new NumberHistoryList();
        
        // 測試1：空串列統計
        System.out.println("\n=== 測試1：空串列統計 ===");
        list.printStats();
        
        // 測試2：前端新增
        System.out.println("\n=== 測試2：前端新增 (10, 20, 30) ===");
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        System.out.println("前端新增 30, 20, 10 後：");
        list.printList();
        list.printStats();
        
        // 測試3：尾端新增
        System.out.println("\n=== 測試3：尾端新增 (40, 50) ===");
        list.addLast(40);
        list.addLast(50);
        System.out.println("尾端新增 40, 50 後：");
        list.printList();
        list.printStats();
        
        // 測試4：搜尋功能
        System.out.println("\n=== 測試4：搜尋功能 ===");
        System.out.println("搜尋 30：" + (list.search(30) ? "找到" : "找不到"));
        System.out.println("搜尋 25：" + (list.search(25) ? "找到" : "找不到"));
        System.out.println("搜尋 50：" + (list.search(50) ? "找到" : "找不到"));
        System.out.println("搜尋 99：" + (list.search(99) ? "找到" : "找不到"));
        
        // 測試5：刪除功能 - 刪除頭部
        System.out.println("\n=== 測試5：刪除頭部 (30) ===");
        System.out.println("刪除前：");
        list.printList();
        list.remove(30);
        System.out.println("刪除後：");
        list.printList();
        list.printStats();
        
        // 測試6：刪除功能 - 刪除中間
        System.out.println("\n=== 測試6：刪除中間 (40) ===");
        System.out.println("刪除前：");
        list.printList();
        list.remove(40);
        System.out.println("刪除後：");
        list.printList();
        list.printStats();
        
        // 測試7：刪除功能 - 刪除尾部
        System.out.println("\n=== 測試7：刪除尾部 (50) ===");
        System.out.println("刪除前：");
        list.printList();
        list.remove(50);
        System.out.println("刪除後：");
        list.printList();
        list.printStats();
        
        // 測試8：刪除不存在的值
        System.out.println("\n=== 測試8：刪除不存在的值 (99) ===");
        System.out.println("刪除前：");
        list.printList();
        list.remove(99);
        System.out.println("刪除後（應保持不變）：");
        list.printList();
        list.printStats();
        
        // 測試9：新增更多資料
        System.out.println("\n=== 測試9：新增更多資料 ===");
        list.addFirst(100);
        list.addLast(200);
        list.addFirst(5);
        list.addLast(300);
        System.out.println("新增 100(前), 200(後), 5(前), 300(後) 後：");
        list.printList();
        list.printStats();
        
        // 測試10：連續刪除到空串列
        System.out.println("\n=== 測試10：連續刪除到空串列 ===");
        System.out.println("刪除 100：");
        list.remove(100);
        list.printList();
        System.out.println("刪除 200：");
        list.remove(200);
        list.printList();
        System.out.println("刪除 5：");
        list.remove(5);
        list.printList();
        System.out.println("刪除 300：");
        list.remove(300);
        list.printList();
        System.out.println("刪除 20：");
        list.remove(20);
        list.printList();
        list.printStats();
        
        System.out.println("\n=== 所有測試完成 ===");
        System.out.println("總操作次數：10 次測試");
    }
    
    /**
     * 前端新增
     */
    public void addFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("  ✅ 前端新增：" + data);
    }
    
    /**
     * 尾端新增
     */
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("  ✅ 尾端新增：" + data);
    }
    
    /**
     * 搜尋數字
     */
    public boolean search(int data) {
        if (head == null) {
            return false;
        }
        
        Node current = head;
        int position = 1;
        while (current != null) {
            if (current.data == data) {
                System.out.println("  🔍 找到 " + data + " 在位置 " + position);
                return true;
            }
            current = current.next;
            position++;
        }
        System.out.println("  ❌ 找不到 " + data);
        return false;
    }
    
    /**
     * 刪除數字（第一個符合的）
     */
    public boolean remove(int data) {
        if (head == null) {
            System.out.println("  ⚠️ 串列為空，無法刪除 " + data);
            return false;
        }
        
        // 刪除頭部
        if (head.data == data) {
            head = head.next;
            size--;
            System.out.println("  ✅ 刪除頭部：" + data);
            return true;
        }
        
        // 刪除中間或尾部
        Node current = head;
        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
                size--;
                System.out.println("  ✅ 刪除：" + data);
                return true;
            }
            current = current.next;
        }
        
        System.out.println("  ❌ 找不到 " + data + "，無法刪除");
        return false;
    }
    
    /**
     * 輸出串列內容
     */
    public void printList() {
        if (head == null) {
            System.out.println("  （串列為空）");
            return;
        }
        
        Node current = head;
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        System.out.println(sb.toString());
    }
    
    /**
     * 輸出統計資訊
     */
    public void printStats() {
        System.out.println("  📊 統計資訊：");
        System.out.println("     大小（節點數）：" + getSize());
        System.out.println("     總和：" + getSum());
        System.out.println("     頂部（最大值）：" + getMax());
        System.out.println("     簡單（最小值）：" + getMin());
        System.out.println("     平均值：" + getAverage());
    }
    
    /**
     * 取得串列大小
     */
    public int getSize() {
        return size;
    }
    
    /**
     * 計算總和
     */
    public int getSum() {
        if (head == null) {
            return 0;
        }
        
        int sum = 0;
        Node current = head;
        while (current != null) {
            sum += current.data;
            current = current.next;
        }
        return sum;
    }
    
    /**
     * 取得最大值（頂部）
     */
    public int getMax() {
        if (head == null) {
            return 0;
        }
        
        int max = head.data;
        Node current = head.next;
        while (current != null) {
            if (current.data > max) {
                max = current.data;
            }
            current = current.next;
        }
        return max;
    }
    
    /**
     * 取得最小值（簡單）
     */
    public int getMin() {
        if (head == null) {
            return 0;
        }
        
        int min = head.data;
        Node current = head.next;
        while (current != null) {
            if (current.data < min) {
                min = current.data;
            }
            current = current.next;
        }
        return min;
    }
    
    /**
     * 計算平均值
     */
    public double getAverage() {
        if (head == null) {
            return 0.0;
        }
        return (double) getSum() / size;
    }
}
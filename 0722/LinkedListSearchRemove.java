public class LinkedListSearchRemove {
    
    // 節點類別（內部類別）
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 鏈結串列搜尋與刪除測試 ===");
        
        // 建立測試用的鏈結串列：10 -> 20 -> 30 -> 40 -> 50
        Node head = buildLinkedList();
        System.out.println("原始鏈結串列：");
        printLinkedList(head);
        
        // 測試1：刪除 head（第一個節點）
        System.out.println("\n=== 測試1：刪除 head (10) ===");
        System.out.println("刪除前：");
        printLinkedList(head);
        head = removeValue(head, 10);
        System.out.println("刪除後：");
        printLinkedList(head);
        System.out.println("是否包含 10？ " + contains(head, 10));
        System.out.println("是否包含 20？ " + contains(head, 20));
        
        // 測試2：刪除中間節點
        System.out.println("\n=== 測試2：刪除中間節點 (30) ===");
        System.out.println("刪除前：");
        printLinkedList(head);
        head = removeValue(head, 30);
        System.out.println("刪除後：");
        printLinkedList(head);
        System.out.println("是否包含 30？ " + contains(head, 30));
        System.out.println("是否包含 40？ " + contains(head, 40));
        
        // 測試3：刪除最後一個節點
        System.out.println("\n=== 測試3：刪除最後節點 (50) ===");
        System.out.println("刪除前：");
        printLinkedList(head);
        head = removeValue(head, 50);
        System.out.println("刪除後：");
        printLinkedList(head);
        System.out.println("是否包含 50？ " + contains(head, 50));
        System.out.println("是否包含 40？ " + contains(head, 40));
        
        // 測試4：刪除不存在的值
        System.out.println("\n=== 測試4：刪除不存在的值 (99) ===");
        System.out.println("刪除前：");
        printLinkedList(head);
        head = removeValue(head, 99);
        System.out.println("刪除後（應保持不變）：");
        printLinkedList(head);
        System.out.println("是否包含 99？ " + contains(head, 99));
        
        // 測試5：刪除到只剩一個節點
        System.out.println("\n=== 測試5：刪除到只剩一個節點 ===");
        head = removeValue(head, 20);
        head = removeValue(head, 40);
        System.out.println("刪除 20 和 40 後：");
        printLinkedList(head);
        System.out.println("是否包含 20？ " + contains(head, 20));
        System.out.println("是否包含 40？ " + contains(head, 40));
        System.out.println("是否包含 ？ " + contains(head, 40));
        
        // 測試6：刪除最後一個節點（變空串列）
        System.out.println("\n=== 測試6：刪除最後一個節點 (??) ===");
        head = removeValue(head, 10);
        System.out.println("刪除後（應變空串列）：");
        printLinkedList(head);
        System.out.println("是否包含 10？ " + contains(head, 10));
        
        // 測試7：在空串列上操作
        System.out.println("\n=== 測試7：在空串列上操作 ===");
        System.out.println("空串列是否包含 5？ " + contains(head, 5));
        head = removeValue(head, 5);
        System.out.println("在空串列上刪除 5 後：");
        printLinkedList(head);
        
        System.out.println("\n=== 所有測試完成 ===");
    }
    
    /**
     * 建立測試用的鏈結串列
     */
    public static Node buildLinkedList() {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = new Node(50);
        return head;
    }
    
    /**
     * 印出鏈結串列所有節點
     */
    public static void printLinkedList(Node head) {
        if (head == null) {
            System.out.println("  （串列為空）");
            return;
        }
        
        Node current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        System.out.println("  " + sb.toString());
    }
    
    /**
     * 檢查鏈結串列是否包含特定值
     */
    public static boolean contains(Node head, int value) {
        if (head == null) {
            return false;
        }
        
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    /**
     * 刪除鏈結串列中第一個符合條件的節點
     * 回傳新的 head
     */
    public static Node removeValue(Node head, int value) {
        // 處理空串列
        if (head == null) {
            System.out.println("  ⚠️ 串列為空，無法刪除");
            return null;
        }
        
        // 如果要刪除的是 head
        if (head.data == value) {
            System.out.println("  ✅ 刪除 head 節點：" + value);
            return head.next;
        }
        
        // 尋找要刪除的節點
        Node current = head;
        while (current.next != null) {
            if (current.next.data == value) {
                System.out.println("  ✅ 刪除節點：" + value);
                current.next = current.next.next; // 跳過要刪除的節點
                return head;
            }
            current = current.next;
        }
        
        // 找不到要刪除的值
        System.out.println("  ❌ 找不到值：" + value);
        return head;
    }
}
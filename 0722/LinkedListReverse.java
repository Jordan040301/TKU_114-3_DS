public class LinkedListReverse {
    
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
        System.out.println("=== 鏈結串列反轉測試 ===");
        
        // 測試1：多節點串列
        System.out.println("\n=== 測試1：多節點串列 (10 -> 20 -> 30 -> 40) ===");
        Node head1 = buildList(new int[]{10, 20, 30, 40});
        System.out.print("原始串列：");
        printLinkedList(head1);
        head1 = reverse(head1);
        System.out.print("反轉後：");
        printLinkedList(head1);
        
        // 測試2：單一節點串列
        System.out.println("\n=== 測試2：單一節點串列 (5) ===");
        Node head2 = buildList(new int[]{5});
        System.out.print("原始串列：");
        printLinkedList(head2);
        head2 = reverse(head2);
        System.out.print("反轉後：");
        printLinkedList(head2);
        
        // 測試3：空串列
        System.out.println("\n=== 測試3：空串列 ===");
        Node head3 = null;
        System.out.print("原始串列：");
        printLinkedList(head3);
        head3 = reverse(head3);
        System.out.print("反轉後：");
        printLinkedList(head3);
        
        // 測試4：兩個節點串列
        System.out.println("\n=== 測試4：兩個節點串列 (100 -> 200) ===");
        Node head4 = buildList(new int[]{100, 200});
        System.out.print("原始串列：");
        printLinkedList(head4);
        head4 = reverse(head4);
        System.out.print("反轉後：");
        printLinkedList(head4);
        
        // 測試5：多節點（奇數個）
        System.out.println("\n=== 測試5：奇數個節點 (1 -> 2 -> 3 -> 4 -> 5) ===");
        Node head5 = buildList(new int[]{1, 2, 3, 4, 5});
        System.out.print("原始串列：");
        printLinkedList(head5);
        head5 = reverse(head5);
        System.out.print("反轉後：");
        printLinkedList(head5);
        
        // 測試6：驗證反轉後資料是否正確
        System.out.println("\n=== 測試6：驗證反轉正確性 ===");
        Node head6 = buildList(new int[]{7, 14, 21, 28, 35, 42});
        System.out.print("原始串列：");
        printLinkedList(head6);
        System.out.println("原始串列各節點值：");
        printNodeDetails(head6);
        head6 = reverse(head6);
        System.out.print("反轉後：");
        printLinkedList(head6);
        System.out.println("反轉後各節點值：");
        printNodeDetails(head6);
        
        System.out.println("\n=== 所有測試完成 ===");
        System.out.println("注意：所有反轉皆在原地進行，未建立第二條串列！");
    }
    
    /**
     * 建立鏈結串列（輔助方法）
     */
    public static Node buildList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }
        return head;
    }
    
    /**
     * 印出鏈結串列
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
     * 印出每個節點的詳細資訊（含位址）
     */
    public static void printNodeDetails(Node head) {
        if (head == null) {
            System.out.println("  （串列為空）");
            return;
        }
        
        Node current = head;
        int position = 1;
        while (current != null) {
            String nextAddr = (current.next == null) ? "null" : current.next.toString();
            System.out.println("    節點" + position + ": data=" + current.data + 
                               ", next=" + nextAddr);
            current = current.next;
            position++;
        }
    }
    
    /**
     * 反轉鏈結串列（核心方法）
     * 在原地反轉，不建立第二條串列
     * 
     * 反轉過程：
     * 1. prev = null, current = head
     * 2. 保存下一個節點 nextNode = current.next
     * 3. 將 current.next 指向 prev
     * 4. 移動 prev 和 current
     * 5. 重複直到 current == null
     * 6. 最後 prev 即為新的 head
     */
    public static Node reverse(Node head) {
        // 處理空串列或單一節點
        if (head == null || head.next == null) {
            if (head == null) {
                System.out.println("  ℹ️ 空串列無需反轉");
            } else {
                System.out.println("  ℹ️ 單一節點無需反轉");
            }
            return head;
        }
        
        System.out.println("  🔄 開始反轉串列...");
        
        Node prev = null;
        Node current = head;
        Node nextNode = null;
        
        int step = 1;
        while (current != null) {
            // 保存下一個節點
            nextNode = current.next;
            
            // 反轉指針
            current.next = prev;
            
            // 顯示反轉過程
            System.out.println("    步驟" + step + ": 將節點 " + current.data + 
                               " 的 next 指向 " + (prev == null ? "null" : prev.data));
            
            // 移動 prev 和 current
            prev = current;
            current = nextNode;
            step++;
        }
        
        // prev 即為新的 head
        System.out.println("  ✅ 反轉完成！新的 head 為：" + prev.data);
        return prev;
    }
}
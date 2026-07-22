public class BuildLinkedList {
    
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
        System.out.println("=== 建立鏈結串列 ===");
        
        // 1. 建立10、20、30、40 四個節點
        Node head = new Node(10);
        Node node2 = new Node(20);
        Node node3 = new Node(30);
        Node node4 = new Node(40);
        
        // 2. 使用下一步正確連接
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        
        // 3. 由head走訪輸出
        System.out.println("鏈結串列內容：");
        traverseAndPrint(head);
        
        // 4. 計算節點數與總和
        int count = countNodes(head);
        int sum = sumNodes(head);
        
        System.out.println("\n=== 統計結果 ===");
        System.out.println("節點總數：" + count);
        System.out.println("節點總和：" + sum);
        
        // 測試空白串列處理
        System.out.println("\n=== 測試空白串列 ===");
        System.out.println("空白串列節點數：" + countNodes(null));
        System.out.println("空白串列總和：" + sumNodes(null));
        System.out.print("空白串列走訪：");
        traverseAndPrint(null);
    }
    
    /**
     * 走訪並印出鏈結串列所有節點
     */
    public static void traverseAndPrint(Node head) {
        if (head == null) {
            System.out.println("（串列為空）");
            return;
        }
        
        Node current = head;
        int position = 1;
        while (current != null) {
            System.out.println("  節點 " + position + "：" + current.data);
            current = current.next;
            position++;
        }
    }
    
    /**
     * 計算鏈結串列的節點數量
     */
    public static int countNodes(Node head) {
        if (head == null) {
            return 0;
        }
        
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
    /**
     * 計算鏈結串列所有節點的總和
     */
    public static int sumNodes(Node head) {
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
}
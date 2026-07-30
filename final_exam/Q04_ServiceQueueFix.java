import java.util.ArrayDeque;
import java.util.Deque;

public class Q04_ServiceQueueFix {
    public static void main(String[] args) {
        Deque<String> waiting = new ArrayDeque<>();  // ArrayList 不能转 Deque，要用 ArrayDeque
        waiting.offer("A101 Amy");
        waiting.offer("A102 Ben");
        waiting.offer("A103 Cara");

        System.out.println("下一位：" + peekNext(waiting));
        System.out.println("服務：" + serveNext(waiting));
        System.out.println("服務：" + serveNext(waiting));
        System.out.println("剩餘：" + waiting);
    }

    public static String serveNext(Deque<String> waiting) {
        if (waiting.isEmpty()) {
            return "EMPTY";
        }
        return waiting.pollFirst();  // FIFO：从头部移除
    }

    public static String peekNext(Deque<String> waiting) {
        if (waiting.isEmpty()) {
            return "EMPTY";
        }
        return waiting.peekFirst();  // FIFO：查看头部
    }
}
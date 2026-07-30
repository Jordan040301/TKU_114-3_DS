import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

class Q10_Task {
    private String id;
    private String title;

    public Q10_Task(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return id + " " + title;
    }
}

public class Q10_WorkflowSystem {
    private ArrayList<Q10_Task> allTasks = new ArrayList<>();
    private Deque<Q10_Task> waiting = new ArrayDeque<>();
    private Deque<Q10_Task> completed = new ArrayDeque<>();

    public static void main(String[] args) {
        Q10_WorkflowSystem system = new Q10_WorkflowSystem();
        system.addTask(new Q10_Task("T201", "Backup"));
        system.addTask(new Q10_Task("T105", "Update"));
        system.addTask(new Q10_Task("T330", "Report"));

        System.out.println("下一筆: " + system.peekNext());
        System.out.println("完成: " + system.processNext());
        System.out.println("完成: " + system.processNext());
        System.out.println("恢复: " + system.undoLast());
        System.out.println("下一筆: " + system.peekNext());
        System.out.println("找到: " + system.findById("t330"));
        System.out.println("等待數: " + system.waitingCount());
        System.out.println("完成數: " + system.completedCount());
    }

    public boolean addTask(Q10_Task task) {
        // 拒绝 null
        if (task == null) {
            return false;
        }
        
        // 拒绝空编号
        String id = task.getId();
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        
        // 拒绝重复编号（忽略大小写）
        for (Q10_Task existing : allTasks) {
            if (existing.getId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        
        // 加入所有任务列表
        allTasks.add(task);
        
        // 加入等待队列
        waiting.offer(task);
        
        return true;
    }

    public Q10_Task processNext() {
        if (waiting.isEmpty()) {
            return null;
        }
        
        // 从 Queue 取出下一笔（FIFO）
        Q10_Task task = waiting.poll();
        
        // push 到 Stack
        completed.push(task);
        
        return task;
    }

    public Q10_Task undoLast() {
        if (completed.isEmpty()) {
            return null;
        }
        
        // 从 Stack 取出最近完成的（LIFO）
        Q10_Task task = completed.pop();
        
        // 放回 Queue 前端
        // 使用 Deque 的 addFirst 方法
        if (waiting instanceof ArrayDeque) {
            ((ArrayDeque<Q10_Task>) waiting).addFirst(task);
        } else {
            // 如果不想用 cast，可以用临时集合方式
            Deque<Q10_Task> temp = new ArrayDeque<>();
            temp.add(task);
            temp.addAll(waiting);
            waiting.clear();
            waiting.addAll(temp);
        }
        
        return task;
    }

    public Q10_Task peekNext() {
        if (waiting.isEmpty()) {
            return null;
        }
        return waiting.peek();
    }

    public Q10_Task findById(String id) {
        if (id == null) {
            return null;
        }
        
        // Sequential Search，忽略大小写
        for (Q10_Task task : allTasks) {
            if (task.getId().equalsIgnoreCase(id)) {
                return task;
            }
        }
        
        return null;
    }

    public int waitingCount() {
        return waiting.size();
    }

    public int completedCount() {
        return completed.size();
    }
}
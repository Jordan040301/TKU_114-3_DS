class Q11_Job {
    private String id;
    private String owner;
    private int priority;

    public Q11_Job(String id, String owner, int priority) {
        this.id = id;
        this.owner = owner;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return id + " " + owner + " priority=" + priority;
    }
}

public class Q11_JobSorter {
    public static void main(String[] args) {
        Q11_Job[] jobs = {
            new Q11_Job("J201", "Amy", 3),
            new Q11_Job("J105", "Ben", 5),
            new Q11_Job("J330", "Cara", 3),
            new Q11_Job("J118", "Dan", 5),
            new Q11_Job("J450", "Amy", 1)
        };

        mergeSortByPriority(jobs);

        for (Q11_Job job : jobs) {
            System.out.println(job);
        }
        
        System.out.println("搜尋 Amy : " + findFirstByOwner(jobs, "amy"));
    }

    public static void mergeSortByPriority(Q11_Job[] jobs) {
        if (jobs == null || jobs.length <= 1) {
            return;
        }
        
        Q11_Job[] temp = new Q11_Job[jobs.length];
        mergeSort(jobs, temp, 0, jobs.length - 1);
    }

    private static void mergeSort(Q11_Job[] jobs, Q11_Job[] temp, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int mid = left + (right - left) / 2;
        
        mergeSort(jobs, temp, left, mid);
        mergeSort(jobs, temp, mid + 1, right);
        merge(jobs, temp, left, mid, right);
    }

    private static void merge(Q11_Job[] jobs, Q11_Job[] temp, int left, int mid, int right) {
        // 复制到 temp
        for (int i = left; i <= right; i++) {
            temp[i] = jobs[i];
        }
        
        int i = left;      // 左半部分起始
        int j = mid + 1;   // 右半部分起始
        int k = left;      // 合并位置
        
        // 合并两个已排序的部分
        while (i <= mid && j <= right) {
            // priority 大的排前面（降序）
            // 相同 priority 保持原始顺序（稳定）
            if (temp[i].getPriority() >= temp[j].getPriority()) {
                jobs[k] = temp[i];
                i++;
            } else {
                jobs[k] = temp[j];
                j++;
            }
            k++;
        }
        
        // 复制剩余元素
        while (i <= mid) {
            jobs[k] = temp[i];
            i++;
            k++;
        }
        
        while (j <= right) {
            jobs[k] = temp[j];
            j++;
            k++;
        }
    }

    public static Q11_Job findFirstByOwner(Q11_Job[] jobs, String owner) {
        if (jobs == null || owner == null) {
            return null;
        }
        
        for (Q11_Job job : jobs) {
            if (job != null && job.getOwner() != null && 
                job.getOwner().equalsIgnoreCase(owner)) {
                return job;
            }
        }
        
        return null;
    }
}
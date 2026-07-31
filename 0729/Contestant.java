public class Contestant {
    private String id;
    private String name;
    private int score;
    private double time;

    // 構造方法
    public Contestant(String id, String name, int score, double time) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.time = time;
    }

    // Getter 方法
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public double getTime() {
        return time;
    }

    // Setter 方法
    public void setScore(int score) {
        this.score = score;
    }

    public void setTime(double time) {
        this.time = time;
    }

    // 重寫 toString 方法
    @Override
    public String toString() {
        return String.format("參賽者{編號='%s', 姓名='%s', 分數=%d, 秒數=%.2f}", 
                           id, name, score, time);
    }
}
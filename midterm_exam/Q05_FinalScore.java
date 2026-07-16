public class Q05_FinalScore {
    public static void main(String[] args) {
        System.out.println(calculateFinalScore(80, 90, 5));
        System.out.println(calculateFinalScore(100, 100, 10));
        System.out.println(calculateFinalScore(-1, 80, 5));
        System.out.println(calculateFinalScore(70, 60, 11));
    }

    public static double calculateFinalScore(
        int examScore,
        int assignmentScore,
        int bonus
    ) {
        // 1. 参数合法性验证
        if (examScore < 0 || examScore > 100 ||
            assignmentScore < 0 || assignmentScore > 100 ||
            bonus < 0 || bonus > 10) {
            return -1.0;
        }

        // 2. 计算加权原始成绩
        double rawScore = examScore * 0.4 + assignmentScore * 0.6;

        // 3. 加上加分
        double finalScore = rawScore + bonus;

        // 4. 限制最高为 100 分
        if (finalScore > 100) {
            finalScore = 100;
        }

        return finalScore;
    }
}
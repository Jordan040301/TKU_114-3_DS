public class LogicDemo {
    public static void main(String[] args) {
        int score = 55;
        int attendance = 45;

        boolean passCourse = score >= 60 && attendance >= 80;

        System.out.println("Score: " + score);
        System.out.println("Attendance: " + attendance);
        System.out.println("Pass course: " + passCourse);
    }
}

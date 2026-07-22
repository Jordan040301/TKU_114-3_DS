import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {
    
    private static ArrayList<Course2> courseList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== 選課管理系統 ===");
        System.out.println("歡迎使用選課管理系統！");
        
        initializeSampleData();
        
        while (true) {
            showMenu();
            System.out.print("請選擇功能 (1-8)：");
            
            if (!scanner.hasNextInt()) {
                System.out.println("錯誤：請輸入數字 1-8！");
                scanner.next();
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    registerCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    deleteCourse();
                    break;
                case 5:
                    searchCourse();
                    break;
                case 6:
                    showAllCourses();
                    break;
                case 7:
                    showStatistics();
                    break;
                case 8:
                    System.out.println("感謝使用選課管理系統，再見！");
                    scanner.close();
                    return;
                default:
                    System.out.println("錯誤：請選擇 1-8 的有效選項！");
            }
        }
    }
    
    private static void initializeSampleData() {
        Course2 c1 = new Course2("CS101", "程式設計", 30);
        Course2 c2 = new Course2("CS102", "資料結構", 25);
        Course2 c3 = new Course2("CS103", "演算法", 20);
        Course2 c4 = new Course2("CS104", "資料庫系統", 35);
        Course2 c5 = new Course2("CS105", "網路概論", 40);
        
        c1.registerStudent();
        c1.registerStudent();
        c2.registerStudent();
        
        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        courseList.add(c4);
        courseList.add(c5);
        
        System.out.println("已載入 " + courseList.size() + " 筆範例課程資料");
    }
    
    private static void showMenu() {
        System.out.println("\n=== 功能選單 ===");
        System.out.println("1. 新增課程");
        System.out.println("2. 選課（註冊）");
        System.out.println("3. 退課（取消註冊）");
        System.out.println("4. 刪除課程");
        System.out.println("5. 搜尋課程");
        System.out.println("6. 顯示所有課程");
        System.out.println("7. 顯示統計資訊");
        System.out.println("8. 離開系統");
    }
    
    private static void addCourse() {
        System.out.print("請輸入課程代碼：");
        String code = scanner.nextLine().trim();
        
        if (code.isEmpty()) {
            System.out.println("錯誤：課程代碼不得為空白！");
            return;
        }
        
        if (findCourseByCode(code) != null) {
            System.out.println("錯誤：課程代碼 \"" + code + "\" 已存在！");
            return;
        }
        
        System.out.print("請輸入課程名稱：");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("錯誤：課程名稱不得為空白！");
            return;
        }
        
        System.out.print("請輸入課程容量：");
        if (!scanner.hasNextInt()) {
            System.out.println("錯誤：請輸入有效的數字！");
            scanner.next();
            return;
        }
        
        int capacity = scanner.nextInt();
        scanner.nextLine();
        
        if (capacity <= 0) {
            System.out.println("錯誤：容量必須大於 0！");
            return;
        }
        
        Course2 newCourse = new Course2(code, name, capacity);
        courseList.add(newCourse);
        System.out.println("成功新增課程：" + newCourse);
        System.out.println("目前共有 " + courseList.size() + " 門課程");
    }
    
    private static void registerCourse() {
        System.out.print("請輸入要選課的課程代碼：");
        String code = scanner.nextLine().trim();
        
        if (code.isEmpty()) {
            System.out.println("錯誤：請輸入有效的課程代碼！");
            return;
        }
        
        Course2 course = findCourseByCode(code);
        
        if (course == null) {
            System.out.println("找不到課程代碼 \"" + code + "\"");
            return;
        }
        
        if (course.isFull()) {
            System.out.println("課程 \"" + course.getName() + "\" 已額滿（容量：" + course.getCapacity() + "），無法再選課！");
            return;
        }
        
        boolean success = course.registerStudent();
        if (success) {
            System.out.println("成功選課：" + course.getName());
            System.out.println("目前人數：" + course.getCurrentCount() + " / " + course.getCapacity());
            System.out.println("剩餘空位：" + course.getAvailableSeats());
        } else {
            System.out.println("選課失敗，請稍後再試");
        }
    }
    
    private static void dropCourse() {
        System.out.print("請輸入要退課的課程代碼：");
        String code = scanner.nextLine().trim();
        
        if (code.isEmpty()) {
            System.out.println("錯誤：請輸入有效的課程代碼！");
            return;
        }
        
        Course2 course = findCourseByCode(code);
        
        if (course == null) {
            System.out.println("找不到課程代碼 \"" + code + "\"");
            return;
        }
        
        if (course.getCurrentCount() == 0) {
            System.out.println("課程 \"" + course.getName() + "\" 目前無人選課，無法退課！");
            return;
        }
        
        System.out.println("確定要退選以下課程嗎？");
        System.out.println("  " + course);
        System.out.print("請輸入 y 確認退課，其他鍵取消：");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (!confirm.equals("y")) {
            System.out.println("已取消退課操作");
            return;
        }
        
        boolean success = course.dropStudent();
        if (success) {
            System.out.println("成功退課：" + course.getName());
            System.out.println("目前人數：" + course.getCurrentCount() + " / " + course.getCapacity());
            System.out.println("剩餘空位：" + course.getAvailableSeats());
        } else {
            System.out.println("退課失敗，請稍後再試");
        }
    }
    
    private static void deleteCourse() {
        System.out.print("請輸入要刪除的課程代碼：");
        String code = scanner.nextLine().trim();
        
        if (code.isEmpty()) {
            System.out.println("錯誤：請輸入有效的課程代碼！");
            return;
        }
        
        Course2 course = findCourseByCode(code);
        
        if (course == null) {
            System.out.println("找不到課程代碼 \"" + code + "\"");
            return;
        }
        
        if (course.getCurrentCount() > 0) {
            System.out.println("警告：課程 \"" + course.getName() + "\" 目前有 " + course.getCurrentCount() + " 人選課！");
            System.out.println("刪除課程將影響已選課的學生！");
        }
        
        System.out.println("確定要刪除以下課程嗎？");
        System.out.println("  " + course);
        System.out.print("請輸入 y 確認刪除，其他鍵取消：");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (!confirm.equals("y")) {
            System.out.println("已取消刪除操作");
            return;
        }
        
        boolean removed = courseList.remove(course);
        if (removed) {
            System.out.println("成功刪除課程：" + course.getName());
            System.out.println("目前共有 " + courseList.size() + " 門課程");
        } else {
            System.out.println("刪除失敗，請稍後再試");
        }
    }
    
    private static void searchCourse() {
        System.out.print("請輸入要搜尋的課程代碼：");
        String code = scanner.nextLine().trim();
        
        if (code.isEmpty()) {
            System.out.println("錯誤：請輸入有效的課程代碼！");
            return;
        }
        
        Course2 course = findCourseByCode(code);
        
        if (course != null) {
            System.out.println("找到課程：");
            System.out.println("  " + course);
            System.out.println("  詳細資訊：");
            System.out.println("    課程代碼：" + course.getCode());
            System.out.println("    課程名稱：" + course.getName());
            System.out.println("    容量：" + course.getCapacity());
            System.out.println("    目前人數：" + course.getCurrentCount());
            System.out.println("    剩餘空位：" + course.getAvailableSeats());
            System.out.println("    狀態：" + (course.isFull() ? "額滿" : "可選課"));
        } else {
            System.out.println("找不到課程代碼 \"" + code + "\"");
        }
    }
    
    private static void showAllCourses() {
        if (courseList.isEmpty()) {
            System.out.println("目前沒有任何課程");
            return;
        }
        
        System.out.println("所有課程清單（共 " + courseList.size() + " 門）：");
        System.out.println("  編號  代碼  名稱          容量  人數  空位  狀態");
        System.out.println("  ----------------------------------------------");
        for (int i = 0; i < courseList.size(); i++) {
            Course2 c = courseList.get(i);
            String status = c.isFull() ? "額滿" : "可選";
            String name = c.getName();
            if (name.length() > 10) {
                name = name.substring(0, 10);
            }
            System.out.printf("  %-4d  %-5s %-10s  %-4d  %-4d  %-4d  %s%n",
                (i + 1),
                c.getCode(),
                name,
                c.getCapacity(),
                c.getCurrentCount(),
                c.getAvailableSeats(),
                status);
        }
    }
    
    private static void showStatistics() {
        int totalCourses = courseList.size();
        int totalRegistrations = 0;
        ArrayList<Course2> fullCourses = new ArrayList<>();
        
        for (Course2 c : courseList) {
            totalRegistrations += c.getCurrentCount();
            if (c.isFull()) {
                fullCourses.add(c);
            }
        }
        
        System.out.println("選課統計資訊：");
        System.out.println("  總課程數：" + totalCourses + " 門");
        System.out.println("  總選課人次：" + totalRegistrations + " 人次");
        System.out.println("  額滿課程數：" + fullCourses.size() + " 門");
        
        if (!fullCourses.isEmpty()) {
            System.out.println("\n  額滿課程清單：");
            for (int i = 0; i < fullCourses.size(); i++) {
                Course2 c = fullCourses.get(i);
                System.out.println("    " + (i + 1) + ". " + c.getName() + " (" + c.getCode() + ") - " + 
                                   c.getCurrentCount() + "/" + c.getCapacity());
            }
        }
        
        if (totalCourses > 0) {
            double average = (double) totalRegistrations / totalCourses;
            System.out.printf("\n  平均每門課程選課人數：%.2f 人%n", average);
        }
    }
    
    private static Course2 findCourseByCode(String code) {
        for (Course2 course : courseList) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }
}
public class Q12_BookingReport {
    public static void main(String[] args) {
        Q12_Booking[] bookings = {
            new Q12_Booking("B001", "Amy", 2, 750, true),
            new Q12_Booking("B002", "Ben", 4, 600, false),
            new Q12_Booking("B003", "Cara", 3, 900, true),
            new Q12_Booking("B004", "Dan", 1, 1200, true)
        };

        System.out.println("已確認筆數：" + countConfirmed(bookings));
        System.out.println("已確認收入：" + calculateConfirmedRevenue(bookings));

        Q12_Booking found = findById(bookings, "b003");
        System.out.println("搜尋結果：" + found);

        Q12_Booking largest = findLargestConfirmed(bookings);
        System.out.println("最高確認預約：" + largest);
    }

    // ==================== 方法1：计算已确认笔数 ====================
    public static int countConfirmed(Q12_Booking[] bookings) {
        if (bookings == null) {
            return 0;
        }
        int count = 0;
        for (Q12_Booking booking : bookings) {
            if (booking != null && booking.isConfirmed()) {
                count++;
            }
        }
        return count;
    }

    // ==================== 方法2：计算已确认收入 ====================
    public static double calculateConfirmedRevenue(Q12_Booking[] bookings) {
        if (bookings == null) {
            return 0.0;
        }
        double total = 0.0;
        for (Q12_Booking booking : bookings) {
            if (booking != null && booking.isConfirmed()) {
                total += booking.getTotalPrice();
            }
        }
        return total;
    }

    // ==================== 方法3：依代碼搜寻 ====================
    public static Q12_Booking findById(Q12_Booking[] bookings, String id) {
        if (bookings == null || id == null) {
            return null;
        }
        for (Q12_Booking booking : bookings) {
            if (booking != null && booking.getId() != null) {
                // 忽略英文大小写
                if (booking.getId().equalsIgnoreCase(id)) {
                    return booking;
                }
            }
        }
        return null;
    }

    // ==================== 方法4：找出最高确认预约 ====================
    public static Q12_Booking findLargestConfirmed(Q12_Booking[] bookings) {
        if (bookings == null) {
            return null;
        }

        Q12_Booking largest = null;
        double maxTotal = Double.MIN_VALUE;

        for (Q12_Booking booking : bookings) {
            if (booking != null && booking.isConfirmed()) {
                double total = booking.getTotalPrice();
                // 如果大于目前最大，更新；相等时保留前面（不更新）
                if (total > maxTotal) {
                    maxTotal = total;
                    largest = booking;
                }
            }
        }
        return largest;
    }
}
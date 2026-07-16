public class Q01_ParkingFeeFix {
    public static void main(String[] args) {
        int[] testMinutes = {-20, 30, 31, 60, 61, 120, 121, 420};

        for (int minutes : testMinutes) {
            int fee = calculateFee(minutes);
            System.out.println("停車 " + minutes + " 分鐘，費用：" + fee + " 元");
        }
    }

    public static int calculateFee(int minutes) {
        // 1. 负数处理
        if (minutes < 0) {
            return -1;
        }

        // 2. 免费区间
        if (minutes <= 30) {
            return 0;
        }

        // 3. 31~120 分钟
        if (minutes <= 120) {
            // 超过 30 分钟的部分，每开始 30 分钟收费 20 元
            int extra = minutes - 30;
            int blocks = (extra + 29) / 30; // 向上取整
            return blocks * 20;
        }

        // 4. 超过 120 分钟
        // 前 120 分钟收费 60 元
        int fee = 60;
        // 超过 120 分钟的部分，每开始 60 分钟加收 30 元
        int extra = minutes - 120;
        int blocks = (extra + 59) / 60; // 向上取整
        fee += blocks * 30;

        // 5. 最高收费 180 元
        if (fee > 180) {
            fee = 180;
        }

        return fee;
    }
}
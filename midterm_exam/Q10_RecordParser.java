public class Q10_RecordParser {
    public static void main(String[] args) {
        String[] records = {
            "A101|Keyboard|3|850",
            "A102|Mouse|-1|500",
            "broken data",
            "A103|Monitor|2|4200",
            "A104|1|300"
        };

        for (String record : records) {
            System.out.println(record + " -> " + calculateRecordTotal(record));
        }

        System.out.println("合法筆數: " + countValidRecords(records));
        System.out.println("總金額: " + calculateGrandTotal(records));
    }

    // ==================== 方法1：判断单笔纪录是否合法 ====================
    public static boolean isValidRecord(String record) {
        // 检查 null 或空字串
        if (record == null || record.trim().isEmpty()) {
            return false;
        }

        // 分割字段
        String[] parts = record.split("\\|");
        
        // 规则1：刚好有 4 个字段
        if (parts.length != 4) {
            return false;
        }

        // 规则2：代码与名称移除前后空白后不可为空字串
        String code = parts[0].trim();
        String name = parts[1].trim();
        if (code.isEmpty() || name.isEmpty()) {
            return false;
        }

        // 规则3：数量必须是大於 0 的整数
        try {
            int quantity = Integer.parseInt(parts[2].trim());
            if (quantity <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            // 规则5：文字无法转换时，该笔纪录不合法
            return false;
        }

        // 规则4：单价必须是大於或等於 0 的整数
        try {
            int price = Integer.parseInt(parts[3].trim());
            if (price < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            // 规则5：文字无法转换时，该笔纪录不合法
            return false;
        }

        return true;
    }

    // ==================== 方法2：计算单笔纪录的总金额 ====================
    public static int calculateRecordTotal(String record) {
        // 不合法时回传 -1
        if (!isValidRecord(record)) {
            return -1;
        }

        String[] parts = record.split("\\|");
        int quantity = Integer.parseInt(parts[2].trim());
        int price = Integer.parseInt(parts[3].trim());
        
        return quantity * price;
    }

    // ==================== 方法3：计算合法纪录笔数 ====================
    public static int countValidRecords(String[] records) {
        if (records == null) {
            return 0;
        }

        int count = 0;
        for (String record : records) {
            if (isValidRecord(record)) {
                count++;
            }
        }
        return count;
    }

    // ==================== 方法4：计算总金额 ====================
    public static int calculateGrandTotal(String[] records) {
        if (records == null) {
            return 0;
        }

        int total = 0;
        for (String record : records) {
            int recordTotal = calculateRecordTotal(record);
            // 只累加合法纪录（不合法回传 -1，不累加）
            if (recordTotal != -1) {
                total += recordTotal;
            }
        }
        return total;
    }
}
public class Q06_CommandValidator {
    public static void main(String[] args) {
        String[] commands = {
            "START",
            new String("stop"),
            " Pause ",
            "RUN",
            "",
            null
        };
        for (String command : commands) {
            System.out.println(command + " -> " + isValidCommand(command));
        }
    }

    public static boolean isValidCommand(String command) {
        // 1. 检查是否为 null
        if (command == null) {
            return false;
        }

        // 2. 去除前後空白
        String trimmed = command.trim();

        // 3. 检查是否为空字串
        if (trimmed.isEmpty()) {
            return false;
        }

        // 4. 转换为大写以忽略大小写
        String upper = trimmed.toUpperCase();

        // 5. 检查是否为三种合法指令
        return upper.equals("START") ||
               upper.equals("STOP") ||
               upper.equals("PAUSE");
    }
}
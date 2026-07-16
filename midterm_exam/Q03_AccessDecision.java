public class Q03_AccessDecision {
    public static void main(String[] args) {
        System.out.println(decideAccess(20, true, false, false));
        System.out.println(decideAccess(20, false, true, false));
        System.out.println(decideAccess(17, true, true, false));
        System.out.println(decideAccess(30, true, true, true));
        System.out.println(decideAccess(130, true, true, false));
    }

    public static String decideAccess(
        int age,
        boolean member,
        boolean hasInvitation,
        boolean suspended
    ) {
        // 1. 年龄有效性检查
        if (age < 0 || age > 120) {
            return "INVALID";
        }

        // 2. 停权检查
        if (suspended) {
            return "DENIED";
        }

        // 3. 成年且（会员或邀请码）
        if (age >= 18 && (member || hasInvitation)) {
            return "ALLOWED";
        }

        // 4. 其他情况
        return "REVIEW";
    }
}
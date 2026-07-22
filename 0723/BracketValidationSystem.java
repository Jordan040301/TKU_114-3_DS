import java.util.Stack;

public class BracketValidationSystem {
    
    public static void main(String[] args) {
        System.out.println("=== 括號驗證系統 ===");
        System.out.println("支援小括號 ()、中括號 []、大括號 {}");
        System.out.println("忽略非括號字元\n");
        
        // 測試1：正確的巢狀結構
        System.out.println("=== 測試1：正確的巢狀結構 ===");
        String test1 = "({[]})";
        System.out.println("輸入：" + test1);
        validateAndPrint(test1);
        
        // 測試2：正確的多層巢狀
        System.out.println("\n=== 測試2：正確的多層巢狀 ===");
        String test2 = "{[()]()}";
        System.out.println("輸入：" + test2);
        validateAndPrint(test2);
        
        // 測試3：包含非括號字元
        System.out.println("\n=== 測試3：包含非括號字元 ===");
        String test3 = "function(a, b) { return [1, 2, 3]; }";
        System.out.println("輸入：" + test3);
        validateAndPrint(test3);
        
        // 測試4：順序錯誤（交叉巢狀）
        System.out.println("\n=== 測試4：順序錯誤（交叉巢狀） ===");
        String test4 = "([)]";
        System.out.println("輸入：" + test4);
        validateAndPrint(test4);
        
        // 測試5：缺少左括號
        System.out.println("\n=== 測試5：缺少左括號 ===");
        String test5 = "())";
        System.out.println("輸入：" + test5);
        validateAndPrint(test5);
        
        // 測試6：缺少右括號
        System.out.println("\n=== 測試6：缺少右括號 ===");
        String test6 = "((()";
        System.out.println("輸入：" + test6);
        validateAndPrint(test6);
        
        // 測試7：空字串
        System.out.println("\n=== 測試7：空字串 ===");
        String test7 = "";
        System.out.println("輸入：（空字串）");
        validateAndPrint(test7);
        
        // 測試8：只有非括號字元
        System.out.println("\n=== 測試8：只有非括號字元 ===");
        String test8 = "Hello World 123";
        System.out.println("輸入：" + test8);
        validateAndPrint(test8);
        
        // 測試9：複雜的混合測試
        System.out.println("\n=== 測試9：複雜的混合測試 ===");
        String test9 = "if (x > 0) { arr = [1, 2, (3 + 4)]; }";
        System.out.println("輸入：" + test9);
        validateAndPrint(test9);
        
        // 測試10：只有左括號
        System.out.println("\n=== 測試10：只有左括號 ===");
        String test10 = "({[";
        System.out.println("輸入：" + test10);
        validateAndPrint(test10);
        
        System.out.println("\n=== 所有測試完成 ===");
    }
    
    /**
     * 驗證並印出結果
     */
    public static void validateAndPrint(String input) {
        ValidationResult result = validateBrackets(input);
        
        System.out.println("  結果：" + (result.isValid() ? "✅ 有效" : "❌ 無效"));
        if (!result.isValid()) {
            System.out.println("  錯誤：" + result.getErrorMessage());
        }
        System.out.println("  已忽略：" + result.getIgnoredCount() + " 個非括號字元");
        System.out.println("  處理的括號數：" + result.getBracketCount());
    }
    
    /**
     * 驗證括號是否匹配
     * 返回 ValidationResult 物件包含驗證結果和錯誤訊息
     */
    public static ValidationResult validateBrackets(String input) {
        if (input == null || input.isEmpty()) {
            return new ValidationResult(true, "字串為空", 0, 0);
        }
        
        Stack<Character> stack = new Stack<>();
        int ignoredCount = 0;
        int bracketCount = 0;
        
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            
            // 檢查是否為括號
            if (isBracket(ch)) {
                bracketCount++;
                
                if (isLeftBracket(ch)) {
                    // 左括號：推入堆疊
                    stack.push(ch);
                } else {
                    // 右括號：檢查是否匹配
                    if (stack.isEmpty()) {
                        return new ValidationResult(false, 
                            "缺少左括號：在位置 " + (i + 1) + " 發現 '" + ch + "' 但沒有對應的左括號",
                            ignoredCount, bracketCount);
                    }
                    
                    char left = stack.pop();
                    if (!isMatchingPair(left, ch)) {
                        return new ValidationResult(false,
                            "括號順序錯誤：'" + left + "' 與 '" + ch + "' 不匹配",
                            ignoredCount, bracketCount);
                    }
                }
            } else {
                // 非括號字元：忽略
                ignoredCount++;
            }
        }
        
        // 檢查是否還有未匹配的左括號
        if (!stack.isEmpty()) {
            char remaining = stack.pop();
            return new ValidationResult(false,
                "缺少右括號：'" + remaining + "' 沒有對應的右括號",
                ignoredCount, bracketCount);
        }
        
        return new ValidationResult(true, "所有括號都正確匹配", ignoredCount, bracketCount);
    }
    
    /**
     * 檢查是否為括號字元
     */
    public static boolean isBracket(char ch) {
        return ch == '(' || ch == ')' || ch == '[' || ch == ']' || ch == '{' || ch == '}';
    }
    
    /**
     * 檢查是否為左括號
     */
    public static boolean isLeftBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }
    
    /**
     * 檢查是否為右括號
     */
    public static boolean isRightBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }
    
    /**
     * 檢查兩個括號是否匹配
     */
    public static boolean isMatchingPair(char left, char right) {
        return (left == '(' && right == ')') ||
               (left == '[' && right == ']') ||
               (left == '{' && right == '}');
    }
    
    /**
     * 取得括號類型名稱
     */
    public static String getBracketType(char ch) {
        switch (ch) {
            case '(': case ')': return "小括號";
            case '[': case ']': return "中括號";
            case '{': case '}': return "大括號";
            default: return "未知";
        }
    }
    
    /**
     * 驗證結果類別（封裝驗證結果）
     */
    static class ValidationResult {
        private boolean valid;
        private String errorMessage;
        private int ignoredCount;
        private int bracketCount;
        
        public ValidationResult(boolean valid, String errorMessage, int ignoredCount, int bracketCount) {
            this.valid = valid;
            this.errorMessage = errorMessage;
            this.ignoredCount = ignoredCount;
            this.bracketCount = bracketCount;
        }
        
        public boolean isValid() {
            return valid;
        }
        
        public String getErrorMessage() {
            return errorMessage;
        }
        
        public int getIgnoredCount() {
            return ignoredCount;
        }
        
        public int getBracketCount() {
            return bracketCount;
        }
    }
}
import java.util.Scanner;

public class TextAnalyzer {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. 輸入一行非空白文字
        String text = getValidText(sc);
        
        // 2. 顯示原始字符數
        displayOriginalLength(text);
        
        // 3. 使用 trim() 後顯示有效字符數
        String trimmedText = text.trim();
        displayTrimmedLength(trimmedText);
        
        // 4-5. 使用空白切割單字，顯示單字數量
        String[] words = splitWords(trimmedText);
        displayWordCount(words);
        
        // 6. 計算英文字母元音總數
        displayVowelCount(trimmedText);
        
        // 7. 反轉單字（終極單字）
        displayReversedWords(words);
        
        // 8. 輸入關鍵字並顯示出現次數
        searchKeyword(sc, trimmedText);
        
        sc.close();
    }
    
    /**
     * 1. 輸入一行非空白文字（驗證輸入）
     */
    public static String getValidText(Scanner sc) {
        String text;
        while (true) {
            System.out.print("請輸入一行非空白文字：");
            text = sc.nextLine();
            
            // 檢查是否為空字串或只包含空白
            if (text == null || text.trim().isEmpty()) {
                System.out.println("錯誤：請輸入非空白文字！");
            } else {
                break;
            }
        }
        return text;
    }
    
    /**
     * 2. 顯示原始字符數
     */
    public static void displayOriginalLength(String text) {
        System.out.println("原始字符數：" + text.length());
    }
    
    /**
     * 3. 使用 trim() 後顯示有效字符數
     */
    public static void displayTrimmedLength(String trimmedText) {
        System.out.println("去除前後空白後的有效字符數：" + trimmedText.length());
    }
    
    /**
     * 4-5. 使用空白切割單字，顯示單字數量
     */
    public static String[] splitWords(String trimmedText) {
        // 使用正則表達式 \\s+ 來處理連續空白
        String[] words = trimmedText.split("\\s+");
        return words;
    }
    
    /**
     * 5. 顯示單字數量
     */
    public static void displayWordCount(String[] words) {
        System.out.println("單字數量：" + words.length);
        
        // 顯示所有單字（輔助資訊）
        System.out.print("所有單字：");
        for (int i = 0; i < words.length; i++) {
            System.out.print(words[i]);
            if (i < words.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    
    /**
     * 6. 計算英文字母元音 a, e, i, o, u 的總數
     */
    public static void displayVowelCount(String text) {
        int vowelCount = 0;
        String lowerText = text.toLowerCase();
        
        for (int i = 0; i < lowerText.length(); i++) {
            char c = lowerText.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowelCount++;
            }
        }
        
        System.out.println("元音字母（a, e, i, o, u）總數：" + vowelCount);
        
        // 顯示各元音出現次數（輔助資訊）
        displayDetailedVowelCount(lowerText);
    }
    
    /**
     * 顯示各元音詳細出現次數
     */
    public static void displayDetailedVowelCount(String text) {
        int aCount = 0, eCount = 0, iCount = 0, oCount = 0, uCount = 0;
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            switch (c) {
                case 'a': aCount++; break;
                case 'e': eCount++; break;
                case 'i': iCount++; break;
                case 'o': oCount++; break;
                case 'u': uCount++; break;
            }
        }
        
        System.out.println("  a: " + aCount + " 次");
        System.out.println("  e: " + eCount + " 次");
        System.out.println("  i: " + iCount + " 次");
        System.out.println("  o: " + oCount + " 次");
        System.out.println("  u: " + uCount + " 次");
    }
    
    /**
     * 7. 顯示反轉後的單字（終極單字）
     */
    public static void displayReversedWords(String[] words) {
        System.out.print("終極單字（反轉後）：");
        
        for (int i = 0; i < words.length; i++) {
            // 反轉每個單字
            String reversed = new StringBuilder(words[i]).reverse().toString();
            System.out.print(reversed);
            if (i < words.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
    
    /**
     * 8. 輸入關鍵字並顯示出現次數（忽略大小寫）
     */
    public static void searchKeyword(Scanner sc, String text) {
        System.out.print("請輸入要搜尋的關鍵字：");
        String keyword = sc.nextLine();
        
        // 忽略大小寫比對
        String lowerText = text.toLowerCase();
        String lowerKeyword = keyword.toLowerCase();
        
        int count = countKeywordOccurrences(lowerText, lowerKeyword);
        
        System.out.println("關鍵字 \"" + keyword + "\" 出現次數：" + count + " 次");
    }
    
    /**
     * 計算關鍵字出現次數（忽略大小寫）
     */
    public static int countKeywordOccurrences(String text, String keyword) {
        if (keyword.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        int index = 0;
        
        while ((index = text.indexOf(keyword, index)) != -1) {
            count++;
            index += keyword.length(); // 移動到下一個位置
        }
        
        return count;
    }
}
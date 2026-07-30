public class RecursiveDigitSumPractice {
    public static void main(String[] args) {
        // 測試至少 5 組數據
        System.out.println("digitSum(5729) = " + digitSum(5729));   // 5+7+2+9 = 23
        System.out.println("digitSum(0) = " + digitSum(0));         // 0
        System.out.println("digitSum(123) = " + digitSum(123));     // 1+2+3 = 6
        System.out.println("digitSum(9999) = " + digitSum(9999));   // 9+9+9+9 = 36
        System.out.println("digitSum(1001) = " + digitSum(1001));   // 1+0+0+1 = 2
        System.out.println("digitSum(987654321) = " + digitSum(987654321)); // 9+8+7+6+5+4+3+2+1 = 45
    }

    /**
     * 遞迴計算數字的各位數字總和
     * @param number 非負整數 (0 或正整數)
     * @return 各位數字的總和
     */
    public static int digitSum(int number) {
        // 基本情況 (Base Case): 當數字為 0 時，回傳 0
        if (number == 0) {
            return 0;
        }
        
        // 遞迴情況 (Recursive Case):
        // 將數字除以 10 取得最後一位數字，加上其餘數字的總和
        return (number % 10) + digitSum(number / 10);
    }
}
public class PassCounter {
   public static void main(String[] args) {
        int PassCount = 0;

        int score1 = 80;
        int score2 = 55;
        int score3 = 70;

        if(score1>=60){
            PassCount++;
        }

        if(score2>=60){
            PassCount++;
        }
        if(score3>=60){
            PassCount++;
        }
        System.out.println("Pass Count: " + PassCount);
    } 
}

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nCase = scan.nextInt();
        scan.nextLine();// Handle the new Line character of the case line;
        for(int iCase = 1 ; iCase <= nCase ; iCase++) {
            String N = scan.nextLine();
            String [] result = new Solution().solve(N);
            System.out.printf("Case #%d: %s %s%n",iCase,result[0],result[1]);
        }


    }
    private String[] solve (String N ) {
        StringBuilder aSb = new StringBuilder();
        StringBuilder bSb = new StringBuilder();

        for(int i=0;i<N.length();i++) {
            int curDigit = N.charAt(i)-'0';
            if(curDigit == 4) {
                aSb.append(2);
                bSb.append(2);
            }else {
                if(aSb.length()>0)
                    aSb.append(0);
                bSb.append(curDigit);
            }
        }

        return new String[]{aSb.toString(), bSb.toString()};
    }
}


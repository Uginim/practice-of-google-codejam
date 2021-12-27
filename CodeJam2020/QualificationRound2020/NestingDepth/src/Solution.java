import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numOfCases = scan.nextInt();
        scan.nextLine();
        for(int iCase = 0 ;iCase<numOfCases;iCase++) {
            String line = scan.nextLine();
            String result = new Solution().solve(line);
            System.out.printf("Case #%d: %s%n",iCase+1,result);
        }


    }

    private String solve (String digits) {
        StringBuilder sb = new StringBuilder();
        int curDepth = 0 ;
        for(int i= 0; i<digits.length();i++) {
            int digit = digits.charAt(i)-'0';
            int diff = digit-curDepth;
            int temp = diff;
            if(diff>0) {
                while(temp>0) {
                    sb.append('(');
                    temp--;
                }
            }else if(diff<0) {
                while(temp<0) {
                    sb.append(')');
                    temp++;
                }
            }
            curDepth+=diff;
            sb.append(digit);
        }
        for(int i=0;i<curDepth;i++){
            sb.append(')');
        }

        return sb.toString();
    }
}

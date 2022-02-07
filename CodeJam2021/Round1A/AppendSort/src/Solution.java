import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    private Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        new Solution().run();
    }

    private void run () {
        int nCase = scan.nextInt();
        for(int iCase = 1 ; iCase <= nCase ;iCase++) {
            int N = scan.nextInt();
            List<BigInteger> list = new ArrayList<>();
            for(int i=0;i<N;i++) {
                list.add(scan.nextBigInteger());
            }
            int result = solve(list);
            System.out.printf("Case #%d: %d%n",iCase,result);
        }
    }
    BigInteger TEN = new BigInteger("10");
    BigInteger ONE = new BigInteger("1");



    private int solve(List<BigInteger> inputList){
        int result = 0;
        BigInteger pre = new BigInteger("0");
        BigInteger temp;
        // TC : O(N)
        // SC : O(1)
        for(BigInteger cur:inputList) {
            // 앞에꺼보다 현재가 더 짧을때

            if(pre.toString().length() > cur.toString().length()) {

                result += pre.toString().length() - cur.toString().length();
                BigInteger next = pre.add(ONE);

                if(!next.equals(cur) && next.toString().indexOf(cur.toString())==0) {
                    cur = next;
                }else {
                    BigInteger zeros = TEN.pow(pre.toString().length() - cur.toString().length());
                    cur = cur.multiply(zeros);
                    if(cur.compareTo(pre)<0){
                        cur = cur.multiply(TEN);
                        result++;
                    }
                }

            }
            // 같을때
            else if(pre.toString().length() == cur.toString().length()){
                if(pre.compareTo(cur)>=0){
                    cur = cur.multiply(TEN);
                    result++;
                }
            }
            pre = cur;
        }
        return result;
    }
}

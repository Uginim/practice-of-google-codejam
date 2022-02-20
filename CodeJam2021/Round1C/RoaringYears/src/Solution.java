import java.math.BigInteger;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        new Solution().run();
    }
    private void run() {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for(int iCase = 1 ; iCase<= T;iCase++) {
            BigInteger preYear = scan.nextBigInteger();
            BigInteger result= solve(preYear);
            System.out.printf("Case #%d: %s%n",iCase,result);
        }
    }
    // TC: O(logN)
    // SC: O(1)
    private BigInteger solve(BigInteger preYear) {
//        System.out.printf("%npreYear:%s%n",preYear);
        BigInteger result = BigInteger.ZERO;
        int length = preYear.toString().length();
        int n = length;

        // 시작 숫자의 자릿수

        for(int i=1;i<=9;i++){
            StringBuilder rightSb = new StringBuilder();
            StringBuilder leftSb = new StringBuilder("1");
            for(int j=0 ; j<i;j++){
                rightSb.append("9");
                if(j>0){
                    leftSb.append("0");
                }
            }
            // 몇개 이어붙일지

            for(int curN=2;curN<=18;curN++){

                BigInteger right = new BigInteger(rightSb.toString());
                BigInteger left = new BigInteger(leftSb.toString());
                if(curN<2){
                    continue;
                }
                //
                while(left.compareTo(right)<=0){
                    BigInteger mid = left.add(right).divide(BigInteger.TWO);
                    BigInteger cur = makeRoaringNum(mid,curN);
                    if(preYear.compareTo(cur)<0) {
                        right = mid.subtract(BigInteger.ONE);
                        if(result.compareTo(BigInteger.ZERO)==0){
                            result = cur;
                        }else {
                            result = cur.min(result);
                        }
                    }else {
                        left = mid.add(BigInteger.ONE);
                    }
                }
            }


        }
        return result;
    }
    private BigInteger makeRoaringNum(BigInteger start, int N){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++) {
            sb.append(start.add(new BigInteger(""+i)));
        }
        return new BigInteger(sb.toString());
    }
}

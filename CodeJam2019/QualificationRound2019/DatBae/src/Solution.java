import java.util.Scanner;

public class Solution {
    Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        new Solution().run();
    }
    private void run() {
        int nCase = scan.nextInt();
        for(int iCase = 1 ; iCase <= nCase ; iCase++) {
            int N = scan.nextInt();
            int B = scan.nextInt();
            int F = scan.nextInt();
            scan.nextLine();
            interact(N,B,F);
        }



    }
    void interact(int N, int B, int F) {
        int availNums = (int)Math.pow(2,F);
        int[] values = new int[N-B];
        for(int iStore=0;iStore<F;iStore++) {
            StringBuilder query = new StringBuilder();
//            query.delete(0,query.length() );
            int curBitNum = 0;

            // Make query;
            for(int iBit=0 ;iBit < N;iBit++ ){
                int curBit = (curBitNum >> (F-iStore-1)) & 0x01;
                query.append(curBit);
                curBitNum++;
                curBitNum%=availNums;
            }

            System.out.println(query);
            System.out.flush();
            String res = scan.next();
            for(int i=0;i<res.length();i++) {
                int curBit = res.charAt(i)-'0';
                values[i]<<=1;
                values[i]+=curBit;
            }

        }
        StringBuilder ans = new StringBuilder();
        int numToFind = 0;
        for(int i = 0,iVal = 0;i<N;i++) {
            if(iVal < values.length && values[iVal]==numToFind){
                iVal++;
            }else {
                if(ans.length()>0) {
                    ans.append(" ");
                }
                ans.append(i);
            }
            numToFind++;
            numToFind%=availNums;
        }
        System.out.println(ans);
        System.out.flush();
        String resultOfAnswer = scan.next();
        if(resultOfAnswer.equals("-1")) {
            throw new RuntimeException("WA");
        }
    }


}

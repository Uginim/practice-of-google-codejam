import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        new Solution().run();
    }

    private void run() {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for(int iCase = 1 ; iCase <= T ;iCase++ ) {
            int N = scan.nextInt();
            int K = scan.nextInt();
            int[] tickets = parseArray(N,scan);
            double result = solve(N,K,tickets);
            System.out.printf("Case #%d: %.9f%n",iCase,result);
        }

    }
    private double solve004(int N, int K, int[] tickets) {

        Arrays.sort(tickets);
        PriorityQueue<Integer> diffs = new PriorityQueue<>();
        int pre = tickets[0];
        double result = 0;
        int maxDiff = 0;
        for(int i=1;i<tickets.length;i++) {
            int diff = tickets[i]- pre;
            if(diff  > 1){
                diffs.add(diff);
                if(diffs.size()>2){
                    diffs.poll();
                }
            }
            pre = tickets[i];
        }
        int secondDiff= 0;
        if(diffs.size()==2){
            secondDiff= diffs.poll()-1;
        }
        if(!diffs.isEmpty())
            maxDiff = diffs.poll()-1;
        int maxRange = maxDiff%2 + (maxDiff/2%2)*2 + maxDiff/2/2*2;
        int secondRange = secondDiff%2 + (secondDiff/2%2)*2 + secondDiff/2/2*2;
//        System.out.printf("maxDiff:%d second:%d%n",maxDiff,secondDiff);
        result= Math.max((double)(maxDiff) / K,result);
        result= Math.max((double)(maxRange + secondRange)/K,result);
        result= Math.max((double)(maxRange + tickets[0]-1)/K,result);
        result= Math.max((double)(maxRange + (K - tickets[tickets.length-1]))/K,result);

//        System.out.printf("result : %f %n",result);
        return result;
    }
    /* 003 */
    private double solve(int N, int K, int[] tickets) {

        Arrays.sort(tickets);
        PriorityQueue<Integer> diffs = new PriorityQueue<>();
//        System.out.printf("%nN=%d,K=%d,%s%n",N,K,Arrays.toString(tickets));
        int pre = tickets[0];
        double result = 0;
        int maxDiff = 0;
        for(int i=1;i<tickets.length;i++) {
            int diff = tickets[i]- pre;
            if(diff  > 1){
//                maxDiff = Math.max(maxDiff,diff);
                diffs.add(diff);
                if(diffs.size()>2){
                    diffs.poll();
                }
            }
            pre = tickets[i];
        }
        int secondDiff= 0;
        if(diffs.size()==2){
            secondDiff= diffs.poll();
        }
        if(!diffs.isEmpty())
            maxDiff = diffs.poll();

//        System.out.printf("maxDiff:%d second:%d%n",maxDiff,secondDiff);
        if(maxDiff>=2){
            result = (double) (maxDiff-1 )/K;
        }
//        System.out.printf("result : %f %n",result);
//        if( secondDiff== maxDiff){
        result = Math.max(result,((double) (maxDiff/2) +  (secondDiff/2))/K) ;
//        }
//        System.out.printf("result : %f %n",result);
        if(tickets[0]>1){

            result = Math.max(result,  ((double)(maxDiff/2)+tickets[0]-1) / K );
//            System.out.printf("left result : %f %n",result);
        }

        if(tickets[tickets.length-1]<K){
            result = Math.max(result,  ((double)(maxDiff/2) +(K - tickets[tickets.length-1])) / K);
//            System.out.printf("right result : %f %n",result);
        }
        if(tickets[0]>1 && tickets[tickets.length-1]<K){

            result = Math.max(result,  ((double)(K - tickets[tickets.length-1])+tickets[0]-1) / K );
//            System.out.printf("both side result : %f %n",result);
        }
//        System.out.printf("result : %f %n",result);
        return result;
    }


    private double solve002(int N, int K, int[] tickets) {
       System.out.printf("%nN=%d,K=%d,%s%n",N,K,Arrays.toString(tickets));
       Arrays.sort(tickets);
        double result = 0;
        int pre = tickets[0];
        int maxDiff = 0;
        for(int i=1;i<tickets.length;i++) {
            int diff = tickets[i]- pre;
            if(diff  > 1){
                maxDiff = Math.max(maxDiff,diff);
            }
            pre = tickets[i];
        }
        double diffResult = result = (double)(maxDiff-1) / K;
        System.out.printf("diffResult : %f %n",diffResult);
        if(result<0){
            result = 0;
        }
        System.out.printf("result : %f %n",result);
        if(tickets[0]>1){
            if(diffResult>0) {
                result = Math.max(result,diffResult/2+  (double)tickets[0] - 1 / K);

            }else {
                result = Math.max(result,  (double)tickets[0] - 1 / K);

            }
            System.out.printf("ticket[0]=%d result : %f %n",tickets[0],result);

        }
        if(tickets[tickets.length-1]<K){
            if(diffResult>0) {
                result = Math.max(result,diffResult/2+ (double) (K - tickets[tickets.length-1]) / K);

            }else {
                result = Math.max(result, (double) (K - tickets[tickets.length-1]) / K);

            }
            System.out.printf("tickets[tickets.length-1]=%d result : %f %n",tickets[tickets.length-1],result);
        }
        return result;
    }
    private double solve001(int N, int K, int[] tickets) {
        double result = 0;
        ArrayList<int[]> intervals = new ArrayList<>();
        Arrays.sort(tickets);
        if(tickets[0]>1) {
            intervals.add(new int[]{1,tickets[0]-1 });
        }
        int pre = tickets[0];
        for(int i=1;i<tickets.length;i++) {
//            if(tickets[i]- pre  <= 1){

//            }else {
            if(tickets[i]- pre  > 1){
                intervals.add(new int[]{pre+1,tickets[i]-1});

            }
            pre = tickets[i];
        }
        if(K-tickets[tickets.length-1]>0){
            intervals.add(new int[]{tickets[tickets.length-1]+1,K});
        }
        return result;
    }

    private int[] parseArray(int N, Scanner scan) {
        int[] result = new int[N];
        for(int i=0;i<N;i++) {
            result[i]=scan.nextInt();
        }
        return result;
    }
}

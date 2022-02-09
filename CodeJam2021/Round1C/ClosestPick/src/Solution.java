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
    private double solve(int N, int K, int[] tickets) {

        Arrays.sort(tickets);
        PriorityQueue<Integer> diffs = new PriorityQueue<>();
//        System.out.printf("%nN=%d,K=%d,%s%n",N,K,Arrays.toString(tickets));
        int pre = tickets[0];
        double result = 0;
        // 가장 차이가 큰 2개 찾기
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


        int maxDiff = 0; // 가장 차이가 큰 구간
        int secondDiff= 0; // 두번째 차이가 큰 구간

        if(diffs.size()==2){
            secondDiff= diffs.poll();
        }
        if(!diffs.isEmpty())
            maxDiff = diffs.poll();

        // 5가지 경우

        // 가장큰 구간으로만
        if(maxDiff>=2){
            result = (double) (maxDiff-1 )/K;
        }

        // 첫번째 큰구간과 두번째 큰 구간 반반
        result = Math.max(result,((double) (maxDiff/2) +  (secondDiff/2.0))/K) ;


        // 가장큰 구간 반과 가장 왼쪽 구간
        if(tickets[0]>1){
            result = Math.max(result,  ((double)(maxDiff/2)+tickets[0]-1) / K );
        }

        // 가장큰 구간 반과 가장 오른쪽 구간
        if(tickets[tickets.length-1]<K){
            result = Math.max(result,  ((double)(maxDiff/2) +(K - tickets[tickets.length-1])) / K);
        }

        // 양끝 구간
        if(tickets[0]>1 && tickets[tickets.length-1]<K){
            result = Math.max(result,  ((double)(K - tickets[tickets.length-1])+tickets[0]-1) / K );
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

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nCase = scan.nextInt();
        for(int iCase = 0 ; iCase < nCase ; iCase++) {
            int length= scan.nextInt();
            int[][] inputArr = new int[length][3];
            for(int i = 0;i<length;i++ ){
                inputArr[i][0]=scan.nextInt();
                inputArr[i][1]=scan.nextInt();
                inputArr[i][2]=i;
            }
            String result = new Solution().solve(inputArr);
//            System.out.printf("Case #%d: %s%n",iCase+1, Arrays.deepToString(inputArr));
            System.out.printf("Case #%d: %s%n",iCase+1, result);
        }




    }
    private static String STR_IMPOSSIBLE = "IMPOSSIBLE";
    private String solve(int[][] intervals) {
//        StringBuilder sb = new StringBuilder();
        char[] resultArr = new char[intervals.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Map<Integer,Character> map = new HashMap<>();
        Arrays.sort(intervals,(o1,o2)->o1[0] - o2[0] );
        int max = 0;
        char curP = 'J';
        for(int i=0;i<intervals.length;i++) {
            while(!pq.isEmpty() && pq.peek() <= intervals[i][0] ){
                int curClose=pq.poll();
                map.remove(curClose);
            }
            if(pq.size()>=2) {
                return STR_IMPOSSIBLE;
            }else if(pq.size()==1) {
                if(map.get(pq.peek())=='J'){
                    curP = 'C';
                }else{
                    curP = 'J';
                }
            }
//            sb.append(curP);
            resultArr[intervals[i][2]]= curP;
            map.put(intervals[i][1],curP);
            pq.add(intervals[i][1]);

        }
        return new String(resultArr);
    }
}

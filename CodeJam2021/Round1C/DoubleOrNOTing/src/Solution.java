import java.math.BigInteger;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        new Solution().run();
    }

    private void run() {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for(int iCase=1;iCase <= T ;iCase++) {
            BigInteger bi = scan.nextBigInteger();
            long result = solve(bi.toString());

        }



    }

    private long solve(String startBits,String endBits){
        LinkedList<Integer> sList = convertToList(startBits);
        LinkedList<Integer> eList = convertToList(endBits);
//        removeLeadingZero(sList);
//        removeLeadingZero(eList);
        int K = getGroupNums(sList);
        int L = getGroupNums(sList);
        long result = 0;
        // not reuse
        if(K >= L) {
            result = endBits.length()+K;
        }
        // reuse

        return result;
    }
    private LinkedList<Integer> convertToList(String bits) {
        LinkedList<Integer> list = new LinkedList<>();
        for(char c : bits.toCharArray()) {
            list.add(c-'0');
        }
        return list;
    }
    private void removeLeadingZero(LinkedList<Integer> list) {

        while(list.size()>0 && list.getFirst()==0) {
            list.remove(0);
        }
        if(list.size()==0){
            list.add(0);
        }
    }
    private int getGroupNums(LinkedList<Integer> list){
        if(list.size()==1&&list.getFirst()==0){
            return 1;
        }
        int start=0;
        while(list.size()>0 && list.getFirst()==0) {
            start++;
        }
        int numOfGruops = 1;
        int pre = list.get(start);
        for(int i=start+1;i<list.size();i++) {
            int cur = list.get(i);
            if(pre!=cur){
                numOfGruops++;
            }
            pre = cur;
        }
    }
}

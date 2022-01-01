import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nCase = scan.nextInt();
        for(int iCase = 1 ; iCase <= nCase ; iCase++ ) {
            int N = scan.nextInt();
            scan.nextLine();
            String path = scan.nextLine();
            String result = new Solution().solve(path);
//            String result = new Solution().solve001(N,path);
            System.out.printf("Case #%d: %s%n",iCase,result);
        }


    }
    private String solve( String path){
        StringBuilder resultPath = new StringBuilder();
        for(int i=0;i<path.length();i++ ) {
            if(path.charAt(i)=='S') {
                resultPath.append('E');
            }else {
                resultPath.append('S');
            }
        }
        return resultPath.toString();
    }
    /* failed */
    private String solve001(/* deprecated */int N, String path){

        StringBuilder resultPath = new StringBuilder();
        int cntEUntilFistCurve = 0;
        int cntSUntilFistCurve = 0;
        for(int i=0;i<path.length();i++) {
            char curDirection = path.charAt(i);
            if (curDirection == 'E') {
                cntEUntilFistCurve++;
            } else /*if(curDirection==S)*/ {
                cntSUntilFistCurve++;
            }
            if(cntEUntilFistCurve > 0 && cntSUntilFistCurve > 0) {
                if(curDirection=='E'){
                    cntEUntilFistCurve--;
                    resultPath.append('E');
                }else {
                    cntSUntilFistCurve--;
                    resultPath.append('S');
                }
                while(cntEUntilFistCurve >0) {
                    resultPath.append('E');
                    cntEUntilFistCurve--;
                }
                while(cntSUntilFistCurve >0) {
                    resultPath.append('S');
                    cntSUntilFistCurve--;
                }

                /* Initialize */
                cntEUntilFistCurve=0;
                cntSUntilFistCurve=0;
            }
        }

        if(cntEUntilFistCurve >0) {
            int lastIdx = resultPath.lastIndexOf("S");
            resultPath.deleteCharAt(lastIdx);
            while(cntEUntilFistCurve >0) {
                resultPath.append('E');
                cntEUntilFistCurve--;
            }
            resultPath.append('S');
        }
        if (cntSUntilFistCurve >0) {
            int lastIdx = resultPath.lastIndexOf("E");
            resultPath.deleteCharAt(lastIdx);
            while(cntSUntilFistCurve >0) {
                resultPath.append('S');
                cntSUntilFistCurve--;
            }
            resultPath.append('E');

        }
        return resultPath.toString();
    }

}

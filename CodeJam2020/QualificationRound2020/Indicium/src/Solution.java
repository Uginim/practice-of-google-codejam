import java.util.Scanner;

public class Solution {
    private final static String RESULT_STR_IMPOSSIBLE = "IMPOSSIBLE";
    private final static String RESULT_STR_POSSIBLE = "POSSIBLE";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nCase = scan.nextInt();
        for(int iCase = 1 ; iCase <= nCase ;iCase++) {
            int N = scan.nextInt();
            int K = scan.nextInt();
            int[][] resultMat = new Solution().solve(N,K);
            String result = RESULT_STR_POSSIBLE;
            if(resultMat==null) {
                result = RESULT_STR_IMPOSSIBLE;
            }
            System.out.printf("Case #%d: %s%n",iCase,result);
            if(resultMat != null) {
                printMatrix(resultMat);
            }
        }



    }
    private static void printMatrix(int[][] mat) {
        StringBuilder resultSb = new StringBuilder();
        for(int y=0;y<mat.length;y++) {
            int width = mat[y].length;
            for(int x=0;x<width;x++) {
                resultSb.append(mat[y][x]);
                if(x<width-1)
                    resultSb.append(" ");
            }
            resultSb.append(System.lineSeparator());
        }
        System.out.printf("%s",resultSb);

    }
    private int[][] solve(int N, int K) {

        int[][] resultMat = new int[N][N];
        if(K < N || K > N * N  )
            return null;
        return resultMat;
    }
}

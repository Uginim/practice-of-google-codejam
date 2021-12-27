import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numOfCases = scan.nextInt();
        for(int i=0;i<numOfCases;i++) {
            int N = scan.nextInt();
            int [][] mat = new int[N][N];
            for(int y=0 ; y<N ; y++) {
                for(int x =0 ; x < N ; x++) {
                    mat[y][x] = scan.nextInt();
                }
            }
            Solution sol = new Solution();
            int[] result = sol.solve(mat);
            System.out.printf("Case #%d: %d %d %d%n",i+1,result[0],result[1],result[2]);
        }

    }
    private int[] solve(int[][] matrix) {
        int[] result = new int[3] ;
        for(int i=0;i<matrix.length;i++) {
            result[0]+=matrix[i][i];
        }
        for(int y=0;y<matrix.length;y++) {
            int numOfCloumns = matrix[y].length;
            Set<Integer> colEle = new HashSet<>();
            Set<Integer> rowEle = new HashSet<>();

            colEle.add(matrix[0][y]);
            rowEle.add(matrix[y][0]);
            boolean repeatedRow = false;
            boolean repeatedCol = false;
            for(int x=1 ; x <  numOfCloumns; x++){


                int curColEle = matrix[x][y];
                int curRowEle = matrix[y][x];
                if(rowEle.contains(curRowEle )){
                    repeatedRow|=true;
                }
                if(colEle.contains(curColEle)){
                    repeatedCol|=true;
                }
                if(repeatedRow && repeatedCol){
                    break;
                }
                colEle.add(curColEle);
                rowEle.add(curRowEle);

            }
            if(repeatedRow) {
                result[1]++;
            }
            if(repeatedCol) {
                result[2]++;
            }
        }

        return result;
    }
}

import java.util.Scanner;

public class Solution {
    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        new Solution().run();
    }

    private void run() {
        int nCase = scan.nextInt();
        for(int iCase = 1; iCase <= nCase ;iCase++) {
            double area = scan.nextInt();
            double[][] result = solve(area);
            printResult(iCase,result);
        }
    }
    private void printResult (int iCase ,double[][] result) {
        System.out.printf("Case #%d:%n");
        for(double[] coordinate:result) {
            System.out.printf("%f %f %f%n",coordinate[0],coordinate[1],coordinate[2]);
        }
    }
    private double[][] solve(double area) {
        double[][] result = null;
        return null;
    }


}

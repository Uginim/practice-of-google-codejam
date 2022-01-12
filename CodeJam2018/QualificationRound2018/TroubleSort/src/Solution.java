import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        new Solution().run();
    }
    private void run() {
        int nCase = scan.nextInt();
        for(int iCase = 1 ; iCase <= nCase;iCase++) {
            int n = scan.nextInt();
            int[] arr = parseArr(n,scan);
            int result = solve(arr);
            if(result == -1) {
                System.out.printf("Case #%d: OK%n",iCase);
            }else {
                System.out.printf("Case #%d: %d%n",iCase,result);
            }
        }

    }
    private int[] parseArr(int n, Scanner scan) {
        int[] result = new int[n];
        for(int i = 0; i<n;i++) {
            result[i] = scan.nextInt();
        }
        return result;
    }

    private int solve(int[] arr) {
        int n = arr.length;
        int nA = n/2 + n%2;
        int nB = n/2;
        int[] arrA = new int[nA];
        int[] arrB = new int[nB];
        for(int i=0;i < n;i++) {
            if(i%2==0) {
                arrA[i/2] = arr[i];
            }else {
                arrB[i/2] = arr[i];
            }
        }
        Arrays.sort(arrA);
        Arrays.sort(arrB);
        for(int i=0;i < n;i++) {
            if(i%2==0) {
                arr[i] = arrA[i/2];
            }else {
                arr[i] = arrB[i/2];
            }
        }
//        System.out.printf("%s%n",Arrays.toString(arr));
        int pre = arr[0];
        int errorIdx = -1;
        for(int i= 1;i<n;i++) {
            if(pre>arr[i]){
                errorIdx = i-1;
                break;
            }
            pre = arr[i];
        }
        return errorIdx;
    }
}

import java.math.BigInteger;
import java.util.*;

public class Solution {
    void _err(Object...os) { System.err.println(Arrays.deepToString(os)); }
    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        new Solution().run();
        System.exit(0);
    }
    private void run( ) {
        int nCase = scan.nextInt();
        for(int iCase = 1 ; iCase <= nCase ; iCase++){

            BigInteger N = scan.nextBigInteger();
            int L = scan.nextInt();
            String result = "";
            try{
                ArrayList<BigInteger> numbers = parseBigIntegers(L, scan);
                scan.nextLine();
                result = solve(L,numbers);

                System.out.printf("Case #%d: %s%n",iCase,result);
                System.out.flush();

            }catch (Exception e) {
                _err(e.toString());
            }
        }
    }
    private ArrayList<BigInteger> parseBigIntegers(int L, Scanner scan) throws Exception {
        ArrayList<BigInteger> numbers = new ArrayList<>();
        for(int i = 0; i < L;i++) {
            BigInteger curNumber = scan.nextBigInteger();
            numbers.add(curNumber);
        }
        return numbers;
    }
    private String solve(int L,ArrayList<BigInteger> numbers)  throws Exception {
        char[] ans = new char[]{};
        BigInteger[] primes = new BigInteger[L + 1];
        for (int i = 1; i <= L - 1; ++i) {
            BigInteger gcd = numbers.get(i - 1).gcd(numbers.get(i));
            if (gcd.compareTo(numbers.get(i - 1)) < 0 && gcd.compareTo(numbers.get(i)) < 0) {
                primes[i] = gcd;
                for (int j = i - 1; j >= 0; --j) {
                    primes[j] = numbers.get(j).divide(primes[j + 1]);
                }
                for (int j = i + 1; j <= L; ++j) {
                    primes[j] = numbers.get(j - 1).divide(primes[j - 1]);
                }
                break;
            }
        }
        BigInteger[] sorted = primes.clone();
        Arrays.sort(sorted);
        Map<BigInteger, Character> mapper = new HashMap<>();
        int idxAlpha = 0;
        for (int i = 0; i < sorted.length; i++) {
            BigInteger cur = sorted[i];
            if (!mapper.containsKey(cur)) {
                mapper.put(cur, (char) (idxAlpha++ + 'A'));
            }
        }
//        _err(sorted);
        ans = new char[L + 1];
        for (int i = 0; i <= L; ++i) {
            ans[i] = mapper.get(primes[i]);
        }
        return new String(ans);
    }


}






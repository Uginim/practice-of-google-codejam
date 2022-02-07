import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        new Solution().run();
    }
    private void run() {
        Scanner scan = new Scanner(System.in);
        int nCase = scan.nextInt();
        for(int iCase = 1 ; iCase <= nCase ;iCase ++) {
            int M = scan.nextInt();
            Map<Integer,BigInteger> cardCnts = new HashMap<>();
            for(int i =0  ;i < M ;i++) {
                int P = scan.nextInt();
                BigInteger N = scan.nextBigInteger();
                cardCnts.put(P,N);
            }
            BigInteger result = solve(cardCnts);
            System.out.printf("Case #%d: %s%n",iCase,result.toString());
        }
    }
    int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
            31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
            73, 79, 83, 89, 97, 101, 103, 107, 109,
            113, 127, 131, 137, 139, 149, 151, 157,
            163, 167, 173, 179, 181, 191, 193, 197,
            199, 211, 223, 227, 229, 233, 239, 241,
            251, 257, 263, 269, 271, 277, 281, 283,
            293, 307, 311, 313, 317, 331, 337, 347,
            349, 353, 359, 367, 373, 379, 383, 389,
            397, 401, 409, 419, 421, 431, 433, 439,
            443, 449, 457, 461, 463, 467, 479, 487,
            491, 499};
    BigInteger ONE = new BigInteger("1");
    BigInteger ZERO = new BigInteger("0");
    private BigInteger solve(Map<Integer,BigInteger> cardCnts) {
        BigInteger result = ZERO;
        BigInteger totalSum = ZERO.add(ZERO);
//        System.out.printf("cardCnts:%s%n",cardCnts);
        for(Map.Entry<Integer,BigInteger> entry: cardCnts.entrySet()){
            totalSum=totalSum.add(entry.getValue().multiply(new BigInteger(""+entry.getKey())));
        }
//        System.out.printf("totalSum: %s%n",totalSum);
        BigInteger start = ONE.max(totalSum.subtract(new BigInteger("29940")));
        int endOfIter = Integer.parseInt(totalSum.subtract(start).toString());
//        System.out.printf("endOfIter: %d%n",endOfIter);
        for(int i=0;i<=endOfIter;i++) {
            Map<Integer,Integer> usedNums = new HashMap<>();
            BigInteger curNum = start.add(new BigInteger(""+i));
            BigInteger factorizedNum = curNum.add(ZERO);
            for(int prime:primes) {
                BigInteger curPrime = new BigInteger(""+prime);
                while(cardCnts.containsKey(prime) && factorizedNum.mod(curPrime).equals(ZERO)) {
                    usedNums.merge(prime,1,(oldNum, newNum)->oldNum+newNum);
                    factorizedNum=factorizedNum.divide(curPrime);
                }
            }
            if(factorizedNum.equals(ONE)){
                int bSum =0;
                boolean isValid = true;
                for(Map.Entry<Integer,Integer> entry: usedNums.entrySet()){
                    bSum += entry.getKey()*entry.getValue();
//                    if(cardCnts.get(entry.getKey()).compareTo(new BigInteger(""+entry.getValue())) <0) {
//                        isValid= false;
//                        break;
//                    }
                }
                BigInteger aSum=ZERO.add(ZERO);
                for(Map.Entry<Integer,BigInteger> entry: cardCnts.entrySet()){
                    if(usedNums.containsKey(entry.getKey()) && entry.getValue().subtract(new BigInteger(""+usedNums.get(entry.getKey()))).compareTo(ZERO) >=0){
                        aSum = aSum.add(new BigInteger(entry.getKey()+"").multiply(entry.getValue().subtract(new BigInteger(""+usedNums.get(entry.getKey())))));
                    }else {
                        aSum = aSum.add(new BigInteger(entry.getKey()+"").multiply(entry.getValue()));                    }
                }

//                System.out.printf("i:%d,curNum%s a:%s b:%d%n",i,curNum,aSum,bSum);
                if(bSum==endOfIter-i && aSum.compareTo(curNum)==0){
//                    System.out.printf("usedNums:%s%n",usedNums);
                    result = result.max(curNum);
                }
            }
        }
        return result;
    }

}





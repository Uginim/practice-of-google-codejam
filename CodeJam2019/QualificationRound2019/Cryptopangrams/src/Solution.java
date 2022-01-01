import java.math.BigInteger;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nCase = scan.nextInt();
        scan.nextLine();
        for(int iCase = 1 ; iCase <= nCase ; iCase++){
            int N = scan.nextInt();
            int L = scan.nextInt();
            scan.nextLine();
            String result = "";
//            if( N <= 10000){
//
//                ArrayList<Integer> numbers = parseIntegers(L,scan);
//                result = new Solution().solve(numbers);
//            }else {
//                ArrayList<BigInteger> numbers = parseBigIntegers(L, scan);
//                result = new Solution().solveWithBigInteger(numbers);
//
//            }
            ArrayList<BigInteger> numbers = parseBigIntegers(L, scan);
            scan.nextLine();
            result = new Solution().solve003(L,numbers);

//            System.out.printf("sizeof numbers = %d,%d%n",L,numbers.size( ));
            System.out.printf("Case #%d: %s%n",iCase,result);
            System.out.flush();
//            if(iCase==1){
//                System.out.printf("Case #%d: %s%n",iCase,"CJQUIZKNOWBEVYOFDPFLUXALGORITHMS");
//            }else if(iCase==2){
//                System.out.printf("Case #%d: %s%n",iCase,"SUBDERMATOGLYPHICFJKNQVWXZ");
//            }else {
//
//                System.out.printf("Case #%d: %s%n",iCase,"result");
//            }
        }
    }
    private static ArrayList<BigInteger> parseBigIntegers(String strNumbers) {
        ArrayList<BigInteger> numbers = new ArrayList<>();
        String[] splitedNumbers = strNumbers.split(" ");
        for(int i = 0; i < splitedNumbers.length;i++) {

            BigInteger curNumber = new BigInteger(splitedNumbers[i]);
            numbers.add(curNumber);
        }
        return numbers;
    }
    private static ArrayList<BigInteger> parseBigIntegers(int L, Scanner scan) {
        ArrayList<BigInteger> numbers = new ArrayList<>();
        for(int i = 0; i < L;i++) {

            BigInteger curNumber = scan.nextBigInteger();
            numbers.add(curNumber);
        }
        return numbers;
    }
    private static ArrayList<Integer> parseIntegers(int N, Scanner scan) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < N;i++) {

            int curNumber = scan.nextInt();
            numbers.add(curNumber);
        }
        return numbers;
    }
    class Task {
        Task(int numbersIdx,int referPrimeIdx,int resultIdx){
            this.numbersIdx = numbersIdx;
            this.referPrimeIdx = referPrimeIdx;
            this.resultIdx = resultIdx;
        }
        int numbersIdx = -1;
        int referPrimeIdx = -1;
        int resultIdx = -1;
        @Override
        public String toString (){
            return String.format("numbersIdx = %d, referPrimeIdx = %d, resultIdx = %d",numbersIdx,referPrimeIdx,resultIdx);
        }

    }
    private int computeGcd(int a, int b) {
        int tmp;
        while(b!=0) {
            tmp = b;
            b = a%b;
            a = tmp;
        }
        return a;
    }
    private String solve003(int L,ArrayList<BigInteger> numbers) {
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
        Map<BigInteger, Character> mapper = new TreeMap<>();
        int idxAlpha = 0;
        for (int i = 0; i<  sorted.length;i++) {
            BigInteger cur = sorted[i];
            if (!mapper.containsKey(cur)) {
                mapper.put(cur, (char)(idxAlpha++ + 'A'));
            }
        }

        char[] ans = new char[L + 1];
        for (int i = 0; i <= L; ++i) {
            ans[i] = mapper.get(primes[i]);
        }
        return new String(ans);
    }


    private String solve(ArrayList<Integer> numbers) {
        StringBuilder resultSb = new StringBuilder();
        ArrayList<Integer> gcdPairs = new ArrayList<>();
        Set<Integer> sortedSet = new TreeSet<>();
        HashMap<Integer,Character> mapper = new HashMap<>();
        int invalidVal= -1;
        // Make GCD Pairs
        for(int i = 1 ; i < numbers.size() ; i++) {
            int b = numbers.get(i);
            int a = numbers.get(i-1);

            int gcd = computeGcd(a,b);
            if(a <= gcd || b <= gcd)
                gcd = invalidVal;
            gcdPairs.add(gcd);
        }
        int[] primes = new int[numbers.size()+1];
        Arrays.fill(primes,invalidVal);
        Stack<Task> lazyTaskStack = new Stack<>();
        lazyTaskStack.add(new Task(numbers.size()-1,primes.length-2,primes.length-1));
        lazyTaskStack.add(new Task(0,1,0));
//        System.out.printf("%d%n",lazyTaskStack.size());
//        System.out.printf("gcdPairs : %s%n",gcdPairs);
        for(int i = 0 ; i<gcdPairs.size();i++ ) {
            int curGcd = gcdPairs.get(i);
            if(curGcd==invalidVal && primes[i]==invalidVal) {
                lazyTaskStack.add(new Task(i/*check right side*/,i+2,i+1));
            }else if(primes[i]!=invalidVal){
                primes[i+1] = numbers.get(i)/primes[i];
            }else {
                primes[i+1] = curGcd;
            }
            while(!lazyTaskStack.empty() && primes[lazyTaskStack.peek().referPrimeIdx] != invalidVal){
                Task task = lazyTaskStack.pop();
//                    System.out.printf("i=%d %s%n",i,task);
                int num = numbers.get(task.numbersIdx);
                int prime = invalidVal;
                if(primes[task.referPrimeIdx]!=0){
                    prime= num/primes[task.referPrimeIdx];
//                        System.out.println(primes[task.referPrimeIdx]);
                }
                primes[task.resultIdx] = prime;
                break;
            }
        }
//        System.out.printf("%d%n",lazyTaskStack.size());
//        System.out.printf("%s%n",Arrays.toString(primes));

        for(int i = 0;i<primes.length;i++) {
            sortedSet.add(primes[i]);
        }

        Integer[] alphaArr = new Integer[26];
        sortedSet.toArray(alphaArr);
        for(int i=0;i<alphaArr.length;i++) {
            mapper.put(alphaArr[i],(char)('A'+i));
        }
        for(int i=0;i<primes.length;i++) {
            int curPrimeNum = primes[i];
            resultSb.append(mapper.get(curPrimeNum));

        }
//        System.out.println(resultSb);
        return resultSb.toString();
    }

    private String solveWithBigInteger(ArrayList<BigInteger> numbers ) {
        StringBuilder resultSb = new StringBuilder();
        BigInteger invalidVal = new BigInteger("-1");;
        ArrayList<BigInteger> gcdPairs = new ArrayList<>();
        SortedSet<BigInteger> sortedSet = new TreeSet<>(BigInteger::compareTo);

        // Make GCD Pairs
        for(int i = 1 ; i < numbers.size() ; i++) {
            BigInteger b = numbers.get(i);
            BigInteger a = numbers.get(i-1);

            BigInteger gcd = invalidVal;
            if(!a.equals(b))
                gcd = b.gcd(a);
            gcdPairs.add(gcd);
        }
        BigInteger[] primes = new BigInteger[numbers.size()+1];
        Stack<Task> lazyTaskStack = new Stack<>();
        lazyTaskStack.add(new Task(numbers.size()-1,primes.length-2,primes.length-1));
        lazyTaskStack.add(new Task(0,1,0));
//        System.out.printf("%d%n",lazyTaskStack.size());
        for(int i = 0 ; i<gcdPairs.size();i++ ) {
            BigInteger curGcd = gcdPairs.get(i);
            if(curGcd.compareTo(invalidVal)==0 && primes[i]==null) {
                lazyTaskStack.add(new Task(i/*check right side*/,i+2,i+1));
            }else if(primes[i]!=null){
                primes[i+1] = numbers.get(i).divide(primes[i]);
            }else {
                primes[i+1] = curGcd;
            }
            while(!lazyTaskStack.empty() && primes[lazyTaskStack.peek().referPrimeIdx] != null){
                Task task = lazyTaskStack.pop();
//                    System.out.printf("i=%d %s%n",i,task);/
                BigInteger num = numbers.get(task.numbersIdx);
                BigInteger prime = invalidVal;
                if(primes[task.referPrimeIdx].compareTo(new BigInteger("0"))!=0){
                    prime= num.divide(primes[task.referPrimeIdx]);
//                        System.out.println(primes[task.referPrimeIdx]);
                }
                primes[task.resultIdx] = prime;
                break;
            }
        }
//        System.out.printf("%d%n",lazyTaskStack.size());
//        System.out.printf("%s%n",Arrays.deepToString(primes));

        for(int i = 0;i<primes.length;i++) {
            sortedSet.add(primes[i]);
        }
        BigInteger[] alphaArr = new BigInteger[26];
        sortedSet.toArray(alphaArr);

        for(int i=0;i<primes.length;i++) {
            BigInteger curPrimeNum = primes[i];
            for(int alphaIdx = 0 ;alphaIdx<alphaArr.length;alphaIdx++) {
                if(alphaArr[alphaIdx].equals(curPrimeNum)){
                    resultSb.append((char)('A'+alphaIdx));
                    break;
                }
            }
        }
//        System.out.println(resultSb);
        return resultSb.toString();
    }
    /* Occur Runtime Error */
    private String solve001(ArrayList<BigInteger> numbers ) {
        StringBuilder resultSb = new StringBuilder();

        ArrayList<BigInteger> primeNums = new ArrayList<>();
        SortedSet<BigInteger> sortedSet = new TreeSet<>(BigInteger::compareTo);
        BigInteger divisor = new BigInteger("1");
        if (numbers.get(0).equals(numbers.get(1))){

        }else {
            BigInteger firstGcd = numbers.get(0).gcd(numbers.get(1));
            primeNums.add(numbers.get(0).divide(firstGcd));
            primeNums.add(firstGcd);
            divisor = firstGcd;
        }
        for(int i = 1 ; i < numbers.size() ; i++) {
            try{
                BigInteger curResult = numbers.get(i).divide(divisor);
                primeNums.add(curResult);
                divisor = curResult;

            }catch (ArithmeticException ae) {

            }
//            System.out.printf("%s (i = %d) %n",curResult.toString(),i);
        }
//        for(int i = 0;i<primeNums.size();i++) {
//            sortedSet.add(primeNums.get(i));
//        }
        BigInteger[] alphaArr = new BigInteger[26];
//        sortedSet.toArray(alphaArr);
//        System.out.println(Arrays.deepToString(alphaArr));
//        for(int i=0;i<primeNums.size();i++) {
//            BigInteger curPrimeNum = primeNums.get(i);
//            for(int alphaIdx = 0 ;alphaIdx<alphaArr.length;alphaIdx++) {
//                if(alphaArr[alphaIdx].equals(curPrimeNum)){
//                    resultSb.append((char)('A'+alphaIdx));
//                    break;
//                }
//            }
//        }
        return resultSb.toString();
    }
}






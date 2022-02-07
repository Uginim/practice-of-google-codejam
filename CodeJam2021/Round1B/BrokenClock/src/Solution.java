import java.math.BigInteger;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        new Solution().run(args);
    }


    private void run(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for(int iCase = 1 ; iCase <= T;iCase++) {
            long[] abc = new long[3];
            for(int i =0;i<3;i++) {
                abc[i] = scan.nextLong();
            }
            long[] result = solve(abc);
            System.out.printf("Case #%d: %d %d %d %d%n",iCase,result[0],result[1],result[2],result[3]);
        }


    }
    private long[] solve (long[] abc) {
        long[] result = new long[4];

        long[][] anglesCases = new long[][]{
                abc,
                {abc[0], abc[2], abc[1]},
                {abc[1], abc[0], abc[2]},
                {abc[1], abc[2], abc[0]},
                {abc[2], abc[0], abc[1]},
                {abc[2], abc[1], abc[0]}
        } ;
        // case that hands orders is like A : B : C
        long rotation = 43_200_000_000_001l;

        for(int i=0; i<anglesCases.length;i++) {
//            alpha = findRotatedNS(anglesCases[i]);
            rotation = findRotatedTick(anglesCases[i]);
            if(rotation != 43_200_000_000_001l){
                result = new long[]{
                        ((anglesCases[i][0]+rotation)/3600000000000l),
                        ((anglesCases[i][0]+rotation)%3600000000000l)/60000000000l,
                        ((anglesCases[i][0]+rotation)%60000000000l)/1000000000l,
                        (anglesCases[i][0]+rotation)%1000000000l
                };
            }
        }

//        // case A : C : B
//
//        alpha = findRotatedNS(new BigInteger[]{abc[0], abc[2], abc[1]});
//        // case B : A : C
//        alpha = findRotatedNS(new BigInteger[]{abc[1], abc[0], abc[2]});
//        // case B : C : A
//        alpha = findRotatedNS(new BigInteger[]{abc[1], abc[2], abc[0]});
//        // case C : A : B
//        alpha = findRotatedNS(new BigInteger[]{abc[2], abc[0], abc[1]});
//        // case C : B : A
//        alpha = findRotatedNS(new BigInteger[]{abc[2], abc[1], abc[0]});

        return result;
    }

    static final long MAX_VAL = 43_200_000_000_000l; // 43200000000000
    // 9223372036854775807
    // 43200000000000
    private long findRotatedTick(long[] hms) {
        // 6 * 12
        long result = 43_200_000_000_001l;
        long HAngle = hms[0];
        long MAngle = hms[1];
        long SAngle = hms[2];


//        System.out.printf("------------------%nH:%d M:%d s:%d%n",HAngle,MAngle,SAngle);
        for (int h = 0; h < 12; h++) {
            long th = h * 3600000000000l;
            long diff = (HAngle- MAngle+MAX_VAL)%MAX_VAL;
            long tn =(( MAX_VAL+th-diff)%MAX_VAL)/11;


            if( (( MAX_VAL+th-diff)%MAX_VAL)%11 == 0) {
                long rotation = ((th+tn)-HAngle)%MAX_VAL;

                long hour = ((HAngle+rotation)%MAX_VAL)/3600000000000l;


                long minutesOfH= ((MAX_VAL+HAngle+rotation)%MAX_VAL%3600000000000l)/60000000000l;
                long minutesOfM= (((MAX_VAL+MAngle+rotation)%MAX_VAL)/720000000000l);

                long secondsOfH= ((MAX_VAL+HAngle+rotation)%MAX_VAL%60000000000l)/1000000000l;
                long secondsOfM= ( ((MAX_VAL+MAngle+rotation)%MAX_VAL%720000000000l)/12000000000l);
                long secondsOfS =  ((MAX_VAL+SAngle+rotation)%MAX_VAL)/720000000000l;


                long nanosOfH= ((MAX_VAL+HAngle+rotation)%MAX_VAL%1000000000l);
                long nanosOfM=  ((MAX_VAL+MAngle+rotation)%MAX_VAL%12000000000l)/12;
                long nanosOfS =  (((MAX_VAL+SAngle+rotation)%MAX_VAL)%720000000000l)/720;

                if(h==((HAngle+rotation)%MAX_VAL)/3600000000000l
                            && minutesOfH==minutesOfM
                        && secondsOfH == secondsOfM && secondsOfH == secondsOfS
                    && nanosOfH == nanosOfM  && nanosOfM == nanosOfS){
                    return rotation;
                }
            }


        }

        return result;
    }

}

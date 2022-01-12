import java.util.Scanner;

public class Solution {
    Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        new Solution().run();
    }
    private void run() {
        int nCase = scan.nextInt();

        for(int iCase = 1 ; iCase <= nCase ;iCase++) {
            int D = scan.nextInt();

            String P = scan.nextLine().trim();
            int result = solve(D,P);
            if(result<0){
                System.out.printf("Case #%d: IMPOSSIBLE%n",iCase);
            }else {
                System.out.printf("Case #%d: %d%n",iCase,result);
            }
        }
    }
    private int solve ( int D/* Damage */, String P /* Program */) {
        int result = 0;
        StringBuilder sbP = new StringBuilder(P);
        int patternIdx = -1;
        while(( patternIdx = sbP.lastIndexOf("CS"))>=0 && D < cal(sbP)) {
            char l = sbP.charAt(patternIdx);
            char r = sbP.charAt(patternIdx+1);
            sbP.setCharAt(patternIdx,r);
            sbP.setCharAt(patternIdx+1,l);
            result++;
        }
        if(D < cal(sbP))
            return -1;
        return result;
    }


    private int solve002 ( int D/* Damage */, String P /* Program */) {
        char[] org = P.toCharArray();
        int result = -1;
        int damages = cal(org);
//        System.out.printf("d : %d%n",damages);
        if(damages<=D){
            return 0;
        }
        loop : for(int i=P.length()-1 ; i >= 1 ; i--) {
            for(int j=i ; j>=1 ; j--) {
                char a = org[j-1];
                char b = org[j];
//                System.out.printf("%d %c%c%n",i,a,b);
                if(a=='C' && b=='S') {
                    if(result<0){
                        result=0;
                    }
                    char tmp = org[j];
                    org[j] = org[j-1];
                    org[j-1] = tmp;
                    result++;
//                    System.out.printf("result:%d%n",result);
                    damages = cal(org);
                    if(damages <= D){
                        break loop;
                    }
                }
            }
        }
        if(damages>D){
            return -1;
        }

        return result;
    }
    private int cal(char[] program) {
        int curD = 1;
        int totalD = 0;
        for(char curI : program) {
            if(curI == 'C'){
                curD*=2;
            }else {
                totalD+=curD;
            }
//            System.out.printf("%d:%c%n",totalD,curI);
        }
        return totalD;
    }

    private int cal(StringBuilder sb) {
        int curD = 1;
        int totalD = 0;
        for( int i=0; i<sb.length();i++) {
            char curI = sb.charAt(i);
            if(curI == 'C'){
                curD*=2;
            }else {
                totalD+=curD;
            }
//            System.out.printf("%d:%c%n",totalD,curI);
        }
        return totalD;
    }

    private int solve001 ( int D/* Damage */, String P /* Program */) {
            StringBuilder sb = new StringBuilder(P);
            int result = -1;
            for(int l=0,r=sb.length()-1;l<r ;l++,r--){
                while(P.charAt(l)=='S' && l < P.length()){
                    l++;
                }
                while(r >= 0 && sb.charAt(r)=='C'){
                    r--;
                }
                if(l<r  ) {
                    if(result<0)
                        result = 0;
                    char temp = sb.charAt(l);
                    sb.setCharAt(l,sb.charAt(r));
                    sb.setCharAt(r,temp);
                    result++;
                }
            }



            return result;
        }
    }
//failed

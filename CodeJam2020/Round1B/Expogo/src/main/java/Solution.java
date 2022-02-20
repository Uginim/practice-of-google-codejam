import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        new Solution().run();
    }

    private void run() {
        Scanner scan = new Scanner(System.in);

        int T =scan.nextInt();
        for(int iCase = 1 ;iCase <= T ;iCase++) {
            int X = scan.nextInt();
            int Y = scan.nextInt();
            String result = solve(X,Y);
            System.out.printf("Case #%d: %s%n",iCase,result);
        }
    }

    public static final String STR_IMPOSSIBLE = "IMPOSSIBLE";

    public void reverseSb(StringBuilder sb, char targetA, char targetB) {


        for(int i=0; i<sb.length();i++) {
            if(sb.charAt(i)==targetA){
                sb.setCharAt(i,targetB);
            }else if(sb.charAt(i)==targetB) {
                sb.setCharAt(i,targetA);
            }
        }
    }

    public String solve (int X, int Y ) {
        String result = STR_IMPOSSIBLE;
        if( (X+Y) % 2==0 && (X!=0 || Y!=0) ) {
            return result;
        }

        int absX = Math.abs(X);
        int absY = Math.abs(Y);

        List<Character> path = new ArrayList<>();
        List<Character> minPath = new ArrayList<>();
        for(int i=0;i<100;i++){
            minPath.add('X');
        }
        minPath = dfs(minPath,path,1,0,0,absX,absY);
//        System.out.printf("%s %n",minPath);
        StringBuilder resultSb = new StringBuilder();
        for(char dir:minPath){
            resultSb.append(dir);
        }
        if(X<0) {
//            System.out.printf("before reverse : %s %n",resultSb);
            reverseSb(resultSb,'E','W');
//            System.out.printf("after reverse : %s %n",resultSb);
        }
        if(Y< 0) {
//            System.out.printf("before reverse : %s %n",resultSb);
            reverseSb(resultSb,'N','S');
//            System.out.printf("after reverse : %s %n",resultSb);
        }
        result = resultSb.toString();
        return result;
    }



    int[][] dir = {
                {0,1},// N
                {1,0},// E
                {-1,0},// W
                {0,-1}// S
        };
        char [] dirChar = {
                'N','E','W','S'
                };
    public List<Character>  dfs(List<Character> minPath, List<Character> curPath, int curDistance, int curX, int curY, int X, int Y) {
        if(curPath.size()>8 || curPath.size()>=minPath.size()){
            return minPath;
        }
        if( curX==X && curY == Y) {
            if(minPath.size() > curPath.size()) {
                minPath.clear();
                for(char dir:curPath){
                    minPath.add(dir);
                }

            }
            return minPath;
        }
        for(int i=0;i<4;i++){
            curPath.add(dirChar[i]);
            List<Character> min = dfs(minPath,curPath,curDistance*2,curX+dir[i][0]*curDistance,curY+dir[i][1]*curDistance,X,Y);
            if(min.size()<minPath.size()) {
                minPath = min;
            }
            curPath.remove(curPath.size()-1);
        }
        return minPath;
    }

    public String solve1(int X, int Y) {

        String result = STR_IMPOSSIBLE;
        int absX = Math.abs(X);
        int absY = Math.abs(Y);
        int leftmostX = getMostLetBit(absX);
        int complementX = leftmostX*2-absX-1;
        int reversedX =complementX +leftmostX*2;
        int leftmostY = getMostLetBit(absY);
        int complementY = leftmostY*2-absY-1;
        int reversedY =complementY +leftmostY*2;
        int fullOnesXY = (int)Math.max(leftmostX*2-1,leftmostY*2-1);
        int fullOnesRXY = (int)Math.max(leftmostX*2*2-1,leftmostY-1);
        int fullOnesXRY = (int)Math.max(leftmostX-1,leftmostY*2*2-1);
        char xDir = X>=0 ? 'E':'W';
        char yDir = X>=0 ? 'N':'S';
        System.out.printf("x=%x, y=%x lmx=%x, lmy=%x, rx=%x, ry=%x fxy=%x frxy=%x fxry=%x%n",absX,absY, leftmostX, leftmostY,reversedX
                ,reversedY,fullOnesXY,fullOnesRXY,fullOnesXRY);
        if((absX ^ absY ^fullOnesXY)== 0) {
            StringBuilder resultSb = buildRoute(fullOnesXY,absX ,xDir, yDir);
            result = resultSb.toString();
        }else if( reversedX == (absY|(leftmostY*2))){
            StringBuilder resultSb  = buildRoute(fullOnesRXY,absX ,xDir, yDir );
            int lastXIdx = resultSb.lastIndexOf(xDir+"");
            if ( lastXIdx>= 0) {
                resultSb.replace(lastXIdx, lastXIdx+1, xDir=='E'?"W":"E");
            }
        }else if(  (absX ^reversedY  ^ fullOnesXRY) == 0){
            StringBuilder resultSb  = buildRoute(fullOnesXRY,absX ,xDir, yDir );
            int lastYIdx = resultSb.lastIndexOf(yDir+"");
            if ( lastYIdx>= 0) {
                resultSb.replace(lastYIdx, lastYIdx+1, xDir=='N'?"S":"N");
            }

        }
        return result;
    }
    public  StringBuilder buildRoute(int fullOnes, int X, char xDir, char yDir) {
        StringBuilder sb = new StringBuilder();
        while(fullOnes>0) {
//            System.out.printf("fullOnes :%d%n",fullOnes);

            if((X & 0x1)>0){
                sb.append(xDir);
            }else {
                sb.append(yDir);
            }
            fullOnes/=2;
            X>>=1;
        }

        return sb;
    }


    public int getMostLetBit(int n){
        if (n == 0) {
            return 0;
        }
        int idxBit = -1;
        while(n > 0) {
            n/=2;
            idxBit++;
        }

        return  0x01 <<idxBit;
    }


    public class BinaryNum {

        int myNum;
        List<Byte> bits;
        BinaryNum() {
            this(0);
        }
        BinaryNum(int n) {
            this.myNum = n;

            this.bits = convert2Bits(n);
        }
        public List<Byte> convert2Bits(int n) {
            if(n<0){
                n *= -1;
            }
            List<Byte> bits =new ArrayList<>();
            while(n>0){
                bits.add( (byte)(n & 0x1));
                n/=2;
            }
            return bits;
        }
        public  int convert2Decimal(BinaryNum num) {
            int result = 0;
            for(byte val : num.bits) {
                result<<=1;
                result+=val;
            }
            return result;
        }
        public void addNum(int num ) {
            this.myNum += num;
            this.bits = convert2Bits(this.myNum);
        }

    }
}

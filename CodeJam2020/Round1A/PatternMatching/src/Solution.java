import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {


    public static void main(String[] args) {

        new Solution().run();
//        System.out.printf("%s",Arrays.toString("*gggg*gsgs*".split("\\*")));
//        System.out.printf("%d","sdgsadgs".indexOf(""));

    }


    private void run() {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for(int iCase = 1 ; iCase <= T ;iCase++ ){
            int nPatterns = scan.nextInt();
            String[] patters = new String[nPatterns];
            scan.nextLine(); // deal new line
            for(int i=0;i<nPatterns;i++) {
                patters[i] = scan.nextLine();
            }
            String result = solve(patters);
            System.out.printf("Case #%d: %s%n");
        }
    }
    class Phrase {
        String letterSeq;
        boolean hasLeftAsterisk = true;
        boolean hasRightAsterisk = true;

    }

    private  static final String STR_ONLY_AN_ASTERISK = "*" ;
    private String solve (String[] patterns) {
        String result = STR_ONLY_AN_ASTERISK;
        List<List<Phrase>> letterSeqMatrix = new ArrayList<>();
        for(String pattern : patterns){
            String[] letterSeqArr = pattern.split("\\*");
            List<Phrase> letterSeqList = new ArrayList<>();
            for(int i= 0; i< letterSeqArr.length;i++){
                Phrase lettersSeq = new Phrase();
                if(letterSeqArr[i].length()>0) {
                    lettersSeq.letterSeq = letterSeqArr[i];
                    letterSeqList.add(lettersSeq);
                }
            }
//            // leftmost is not '*'
//            if(pattern.charAt(0)!='*'){
//                letterSeqList.get(0).hasLeftAsterisk=false;
//            }
//
//            // rightmost is not '*'
//            if(pattern.charAt(patterns.length-1)!='*'){
//                letterSeqList.get(0).hasRightAsterisk=false;
//            }
        }
        String preFix = "";
        for(int i=0;i<letterSeqMatrix.size();i++) {
            Phrase leftmost = letterSeqMatrix.get(i).get(0);
            if(preFix.length()<leftmost.letterSeq.length()){
                    preFix =leftmost.letterSeq;
            }
        }
        if(preFix.length()!=0) {
            for(List<Phrase> list : letterSeqMatrix) {
                if(preFix.indexOf(list.get(0).letterSeq)!=0){
                    return "*";
                }
            }
        }
        String sufFix = "";
        for(int i=0;i<letterSeqMatrix.size();i++) {
            Phrase rightmost = letterSeqMatrix.get(i).get(letterSeqMatrix.get(i).size()-1);
            if(sufFix.length()<rightmost.letterSeq.length()){
                sufFix =rightmost.letterSeq;
            }
        }
        if(sufFix.length()!=0) {
            for(List<Phrase> list : letterSeqMatrix) {
                if(sufFix.lastIndexOf(list.get(list.size()-1).letterSeq)!=sufFix.length()-list.get(list.size()-1).letterSeq.length()){
                    return "*";
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(preFix);
        for(List<Phrase> list : letterSeqMatrix ){
            list.get()
        }



        return result;
    }
}

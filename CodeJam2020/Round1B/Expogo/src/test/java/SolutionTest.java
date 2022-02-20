import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {


    int [][] inputs = {
            {2, 3},
            {-2, -3},
            {3, 0},
            {-1, 1}
    };
    String[] outputs ={
        "SEN",
        "NWS",
        "EE",
        "IMPOSSIBLE",
    };
    @Test
    public void testSolveMethod2() {
        for(int i=0;i<inputs.length;i++){
            System.out.printf("test case %d%n",i+1);
            assertEquals(outputs[i],new Solution().solve(inputs[i][0],inputs[i][1]));
        }
    }

    @Test
    public void testBuildRoute() {
        StringBuilder sb = new Solution().buildRoute(0x1111,0x1010,'E','Y');
        System.out.printf("%s",sb);
    }

    @Test
    public void testGetMostLetBit(){
        System.out.printf("started");
        int result =new Solution().getMostLetBit(0x1010);
        assertEquals(0x1000,result);
    }

    @Test
    public void testSolveMethod() {
        String result = new Solution().solve(2,3);
        assertEquals(result,Solution.STR_IMPOSSIBLE);
    }

    @Test
    public void testBist () {
        assertEquals((2 ^5)^7, 0);
    }
}

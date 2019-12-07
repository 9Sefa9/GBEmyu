package mytesting;

import java.util.Arrays;

public class ArrayAddingForZeroPageX {
    public static void main(String[] args){
        int[] fetchValue2 = {5};
        fetchValue2[0]+=1;

        System.out.println(Arrays.toString(fetchValue2));
    }
}

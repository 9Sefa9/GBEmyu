package mytesting;

public class HexTesten {
    public static void main(String[] args){
        int a[] = {0x00,0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,0x09,0xA,0xA1,0xA2};

        System.out.println(a[0x40+8]);
    }
}

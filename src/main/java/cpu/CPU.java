package cpu;

public class CPU {
    RAM

    public void init() {
        for (int i = 0; i < 0x800; ++i) {
            ram.write(i, 0xFF);
        }

        for (int i = 0x4000; i <= 0x4017; ++i) { //may need to change to 0x400f
            ram.write(i, 0x00);
        }

    }

    //setters

    public void run(int[] inst) {
        for (int i = 0; i < inst.length; i++) {

            switch(inst[i]) {

                case 0x78: // SEI imm
                    sei(imm());
                    System.out.println("SEI at " + (String.format("0x%04X", i)));
                    break;
                case 0xD8:
                    cld();
                    System.out.println("CLD at " + (String.format("0x%04X", i)));
                    break;
                case 0xA9:
                    lda(imm());
                    System.out.println("LDA at " + (String.format("0x%04X", i)));
                    break;
                case 0xCA: // dec on x and
                    System.out.println("DEX at " + (String.format("0x%04X", i)));
                    dex();
                    break;
            }
        }

    }


}

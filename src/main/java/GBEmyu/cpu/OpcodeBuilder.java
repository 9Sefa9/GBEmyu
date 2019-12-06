package GBEmyu.cpu;

public class OpcodeBuilder {
    private Opcode[] opcodes;
    private Register register;
    private int[] inst;
    private  Opcode BRKIMPLICIT =  new Opcode(Register.AddressModes.IMPLICIT,"BRKIMPLICIT", 0x00){
        @Override
        public void operation(){
        }
    };

    private  Opcode SEIIMPLICIT =  new Opcode(Register.AddressModes.IMPLICIT,"SEIIMPLICIT", 0x78){
        @Override
        public void operation(){
            register.setSEI();
        }
    };

    private  Opcode CLDIMPLICIT =  new Opcode(Register.AddressModes.IMPLICIT,"CLDIMPLICIT", 0xD8){
        @Override
        public void operation(){
            register.setCLD();
        }
    };
    private  Opcode LDAIMMEDIATE =  new Opcode(Register.AddressModes.IMMEDIATE,"LDAIMMEDIATE", 0xA9){
        @Override
        public void operation(){
            register.setLDA(inst[register.getPC()+1]);
        }
    };
    private  Opcode LDAINDIRECTX =  new Opcode(Register.AddressModes.INDIRECTX,"LDAINDIRECTX", 0xA1){
        @Override
        public void operation(){

        }
    };

    private  Opcode DEXIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"DEXIMPLICIT", 0xCA){
        @Override
        public void operation(){
            register.setDEX();
        }
    };

    private  Opcode ROLZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"ROLZEROPAGE", 0x26){
        @Override
        public void operation(){

        }
    };
    private  Opcode CLCIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"CLCIMPLICIT", 0x18){
        @Override
        public void operation(){

        }
    };
    private  Opcode NOPIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"NOPIMPLICIT", 0xEA){
        @Override
        public void operation(){

        }
    };
    private  Opcode NOPABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"NOPABSOLUTE", 0x0C){
        @Override
        public void operation(){

        }
    };
    private  Opcode NOPABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"NOPABSOLUTEX", 0x1C){
        @Override
        public void operation(){

        }
    };
    private  Opcode NOPZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"NOPZEROPAGE", 0x04){
        @Override
        public void operation(){

        }
    };
    private  Opcode NOPZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"NOPZEROPAGEX", 0x14){
        @Override
        public void operation(){

        }
    };
    private Opcode JSRABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"JSRABSOLUTE", 0x20){
        @Override
        public void operation(){

        }
    };
    private Opcode RORZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"RORZEROPAGE", 0x66){
        @Override
        public void operation(){

        }
    };
    private Opcode PLAIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"PLAIMPLICIT", 0x68){
        @Override
        public void operation(){

        }
    };
    private Opcode ADCIMMEDIATE = new Opcode(Register.AddressModes.IMMEDIATE,"ADCIMMEDIATE", 0x69){
        @Override
        public void operation(){

        }
    };
    private Opcode STAZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"STAZEROPAGE", 0x85){
        @Override
        public void operation(){

        }
    };
    private Opcode STAABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"STAABSOLUTE", 0x8D){
        @Override
        public void operation(){

        }
    };
    private Opcode DECZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"DECZEROPAGEX", 0xD6){
        @Override
        public void operation(){

        }
    };
    private Opcode BEQRELATIVE = new Opcode(Register.AddressModes.RELATIVE,"BEQRELATIVE", 0xF0){
        @Override
        public void operation(){

        }
    };
    //halt the cpu, reset needed to continue..
    private Opcode KIL = new Opcode(Register.AddressModes.HLT,"KIL", 0x02){
        @Override
        public void operation(){

        }
    };

    private Opcode SLOABSOLUTEY = new Opcode(Register.AddressModes.ABSOLUTEY,"SLOABSOLUTEY", 0x1B){
        @Override
        public void operation(){

        }
    };
    private Opcode SLOZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"SLOZEROPAGEX", 0x17){
        @Override
        public void operation(){

        }
    };
    private Opcode SLOABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"SLOABSOLUTE", 0x0F){
        @Override
        public void operation(){

        }
    };
    private Opcode SLOABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"SLOABSOLUTEX", 0x1F){
        @Override
        public void operation(){

        }
    };
    private Opcode SLOZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"SLOZEROPAGE", 0x07){
        @Override
        public void operation(){

        }
    };
    private Opcode SLOINDIRECTX = new Opcode(Register.AddressModes.INDIRECTX,"SLOINDIRECTX", 0x03){
        @Override
        public void operation(){

        }
    };
    private Opcode SLOINDIRECTY = new Opcode(Register.AddressModes.INDIRECTY,"SLOINDIRECTY", 0x13){
        @Override
        public void operation(){

        }
    };
    private  Opcode ORAABSOLUTEY = new Opcode(Register.AddressModes.ABSOLUTEY,"ORAABSOLUTEY", 0x19){
        @Override
        public void operation(){

        }
    };
    private  Opcode ORAABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"ORAABSOLUTEX", 0x1D){
        @Override
        public void operation(){

        }
    };
    private  Opcode ORAINDIRECTY = new Opcode(Register.AddressModes.INDIRECTY,"ORAINDIRECTY", 0x11){
        @Override
        public void operation(){

        }
    };

    private  Opcode ORAZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"ORAZEROPAGEX", 0x15){
        @Override
        public void operation(){

        }
    };
    private  Opcode ORAINDIRECTX = new Opcode(Register.AddressModes.INDIRECTX,"ORAINDIRECTX", 0x01){
        @Override
        public void operation(){

        }
    };
    private  Opcode ORAZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"ORAZEROPAGE", 0x05){
        @Override
        public void operation(){

        }
    };
    private  Opcode ORAABSOLUTE= new Opcode(Register.AddressModes.ABSOLUTE,"ORAABSOLUTE", 0x0D){
        @Override
        public void operation(){

        }
    };
    private  Opcode ORAIMMEDIATE= new Opcode(Register.AddressModes.IMMEDIATE,"ORAIMMEDIATE", 0x09){
        @Override
        public void operation(){

        }
    };

    private  Opcode ASLABSOLUTEX= new Opcode(Register.AddressModes.ABSOLUTEX,"ASLABSOLUTEX", 0x1E){
        @Override
        public void operation(){

        }
    };

    private  Opcode ASLZEROPAGEX= new Opcode(Register.AddressModes.ZEROPAGEX,"ASLZEROPAGEX", 0x16){
        @Override
        public void operation(){

        }
    };
    private  Opcode ASLZEROPAGE= new Opcode(Register.AddressModes.ZEROPAGE,"ASLZEROPAGE", 0x06){
        @Override
        public void operation(){

        }
    };
    private  Opcode ASLABSOLUTE= new Opcode(Register.AddressModes.ABSOLUTE,"ASLABSOLUTE", 0x0E){
        @Override
        public void operation(){

        }
    };
    private  Opcode ASLACCUMULATOR = new Opcode(Register.AddressModes.ACCUMULATOR,"ASLACCUMULATOR", 0x0A){
        @Override
        public void operation(){

        }
    };
    private  Opcode PHPIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"PHPIMPLICIT", 0x08){
        @Override
        public void operation(){

        }
    };
    //http://www.oxyron.de/html/opcodes02.html  Suceh nach ANCIMMEDIATE.
    private  Opcode ANCIMMEDIATE = new Opcode(Register.AddressModes.IMMEDIATE,"ANCIMMEDIATE", 0x0B){
        @Override
        public void operation(){

        }
    };
    private  Opcode LSRZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"LSRZEROPAGE", 0x46){
        @Override
        public void operation(){

        }
    };
    private  Opcode PHAIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"PHAIMPLICIT", 0x48){
        @Override
        public void operation(){

        }
    };

    private  Opcode CMPABSOLUTEY = new Opcode(Register.AddressModes.ABSOLUTEY,"CMPABSOLUTEY", 0xD9){
        @Override
        public void operation(){

        }
    };
       private  Opcode CMPINDIRECTY = new Opcode(Register.AddressModes.INDIRECTY,"CMPINDIRECTY", 0xD1){
        @Override
        public void operation(){

        }
    };
    private  Opcode CMPABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"CMPABSOLUTEX", 0xDD){
        @Override
        public void operation(){

        }
    };
    private  Opcode BPLRELATIVE = new Opcode(Register.AddressModes.RELATIVE,"BPLRELATIVE", 0x10){
        @Override
        public void operation(){

        }
    };
    private  Opcode CMPABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"CMPABSOLUTE", 0xCD){
        @Override
        public void operation(){

        }
    };
    private  Opcode CMPINDIRECTX = new Opcode(Register.AddressModes.INDIRECTX,"CMPINDIRECTX", 0xC1){
        @Override
        public void operation(){

        }
    };

    private  Opcode CMPIMMEDIATE = new Opcode(Register.AddressModes.IMMEDIATE,"CMPIMMEDIATE", 0xC9){
        @Override
        public void operation(){

        }
    };
    private  Opcode CMPZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"CMPZEROPAGE", 0xC5){
        @Override
        public void operation(){

        }
    };
    private  Opcode CMPZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"CMPZEROPAGEX", 0xD5){
        @Override
        public void operation(){

        }
    };

    public OpcodeBuilder(int[] inst, Register register){
        this.register = register;
        this.inst = inst;
        opcodes = new Opcode[0x100];
        buildOpcodes();
    }


    public Opcode[] getOpcodes(){
        return this.opcodes;
    }
    private void buildOpcodes(){
        opcodes[0x00] = BRKIMPLICIT;
        opcodes[0x01] = ORAINDIRECTX; //ORA (Indirect, X)
        opcodes[0x02] = KIL;
        opcodes[0x03] = SLOINDIRECTX;
        opcodes[0x04] = NOPZEROPAGE;
        opcodes[0x05] = ORAZEROPAGE;
        opcodes[0x06] = ASLZEROPAGE;
        opcodes[0x07] = SLOZEROPAGE;
        opcodes[0x08] = PHPIMPLICIT;
        opcodes[0x09] = ORAIMMEDIATE;
        opcodes[0x0A] = ASLACCUMULATOR;
        opcodes[0x0B] = ANCIMMEDIATE;
        opcodes[0x0C] = NOPABSOLUTE;
        opcodes[0x0D] = ORAABSOLUTE;
        opcodes[0x0E] = ASLABSOLUTE;
        opcodes[0x0F] = SLOABSOLUTE;

        opcodes[0x10] = BPLRELATIVE;
        opcodes[0x11] = ORAINDIRECTY;
        opcodes[0x12] = KIL;
        opcodes[0x13] = SLOINDIRECTY;
        opcodes[0x14] = NOPZEROPAGEX;
        opcodes[0x15] = ORAZEROPAGEX;
        opcodes[0x16] = ASLZEROPAGEX;
        opcodes[0x17] = SLOZEROPAGEX;
        opcodes[0x18] = CLCIMPLICIT;
        opcodes[0x19] = ORAABSOLUTEY;
        opcodes[0x1A] = NOPIMPLICIT;
        opcodes[0x1B] = SLOABSOLUTEY;
        opcodes[0x1C] = NOPABSOLUTEX;
        opcodes[0x1D] = ORAABSOLUTEX;
        opcodes[0x1E] = ASLABSOLUTEX;
        opcodes[0x1F] = SLOABSOLUTEX;

        opcodes[0x20] = JSRABSOLUTE;
        opcodes[0x21] = BRKIMPLICIT;
        opcodes[0x22] = KIL;
        opcodes[0x23] = BRKIMPLICIT;
        opcodes[0x24] = BRKIMPLICIT;
        opcodes[0x25] = BRKIMPLICIT;
        opcodes[0x26] = ROLZEROPAGE;
        opcodes[0x27] = BRKIMPLICIT;
        opcodes[0x28] = BRKIMPLICIT;
        opcodes[0x29] = BRKIMPLICIT;
        opcodes[0x2A] = BRKIMPLICIT;
        opcodes[0x2B] = BRKIMPLICIT;
        opcodes[0x2C] = BRKIMPLICIT;
        opcodes[0x2D] = BRKIMPLICIT;
        opcodes[0x2E] = BRKIMPLICIT;
        opcodes[0x2F] = BRKIMPLICIT;

        opcodes[0x30] = BRKIMPLICIT;
        opcodes[0x31] = BRKIMPLICIT;
        opcodes[0x32] = KIL;
        opcodes[0x33] = BRKIMPLICIT;
        opcodes[0x34] = BRKIMPLICIT;
        opcodes[0x35] = BRKIMPLICIT;
        opcodes[0x36] = BRKIMPLICIT;
        opcodes[0x37] = BRKIMPLICIT;
        opcodes[0x38] = BRKIMPLICIT;
        opcodes[0x39] = BRKIMPLICIT;
        opcodes[0x3A] = BRKIMPLICIT;
        opcodes[0x3B] = BRKIMPLICIT;
        opcodes[0x3C] = BRKIMPLICIT;
        opcodes[0x3D] = BRKIMPLICIT;
        opcodes[0x3E] = BRKIMPLICIT;
        opcodes[0x3F] = BRKIMPLICIT;

        opcodes[0x40] = BRKIMPLICIT;
        opcodes[0x41] = BRKIMPLICIT;
        opcodes[0x42] = KIL;
        opcodes[0x43] = BRKIMPLICIT;
        opcodes[0x44] = BRKIMPLICIT;
        opcodes[0x45] = BRKIMPLICIT;
        opcodes[0x46] = LSRZEROPAGE;
        opcodes[0x47] = BRKIMPLICIT;
        opcodes[0x48] = PHAIMPLICIT;
        opcodes[0x49] = BRKIMPLICIT;
        opcodes[0x4A] = BRKIMPLICIT;
        opcodes[0x4B] = BRKIMPLICIT;
        opcodes[0x4C] = BRKIMPLICIT;
        opcodes[0x4D] = BRKIMPLICIT;
        opcodes[0x4E] = BRKIMPLICIT;
        opcodes[0x4F] = BRKIMPLICIT;

        opcodes[0x50] = BRKIMPLICIT;
        opcodes[0x51] = BRKIMPLICIT;
        opcodes[0x52] = KIL;
        opcodes[0x53] = BRKIMPLICIT;
        opcodes[0x54] = BRKIMPLICIT;
        opcodes[0x55] = BRKIMPLICIT;
        opcodes[0x56] = BRKIMPLICIT;
        opcodes[0x57] = BRKIMPLICIT;
        opcodes[0x58] = BRKIMPLICIT;
        opcodes[0x59] = BRKIMPLICIT;
        opcodes[0x5A] = BRKIMPLICIT;
        opcodes[0x5B] = BRKIMPLICIT;
        opcodes[0x5C] = BRKIMPLICIT;
        opcodes[0x5D] = BRKIMPLICIT;
        opcodes[0x5E] = BRKIMPLICIT;
        opcodes[0x5F] = BRKIMPLICIT;

        opcodes[0x60] = BRKIMPLICIT;
        opcodes[0x61] = BRKIMPLICIT;
        opcodes[0x62] = KIL;
        opcodes[0x63] = BRKIMPLICIT;
        opcodes[0x64] = BRKIMPLICIT;
        opcodes[0x65] = BRKIMPLICIT;
        opcodes[0x66] = RORZEROPAGE;
        opcodes[0x67] = BRKIMPLICIT;
        opcodes[0x68] = PLAIMPLICIT;
        opcodes[0x69] = ADCIMMEDIATE;
        opcodes[0x6A] = BRKIMPLICIT;
        opcodes[0x6B] = BRKIMPLICIT;
        opcodes[0x6C] = BRKIMPLICIT;
        opcodes[0x6D] = BRKIMPLICIT;
        opcodes[0x6E] = BRKIMPLICIT;
        opcodes[0x6F] = BRKIMPLICIT;

        opcodes[0x70] = BRKIMPLICIT;
        opcodes[0x71] = BRKIMPLICIT;
        opcodes[0x72] = KIL;
        opcodes[0x73] = BRKIMPLICIT;
        opcodes[0x74] = BRKIMPLICIT;
        opcodes[0x75] = BRKIMPLICIT;
        opcodes[0x76] = BRKIMPLICIT;
        opcodes[0x77] = BRKIMPLICIT;
        opcodes[0x78] = SEIIMPLICIT;
        opcodes[0x79] = BRKIMPLICIT;
        opcodes[0x7A] = BRKIMPLICIT;
        opcodes[0x7B] = BRKIMPLICIT;
        opcodes[0x7C] = BRKIMPLICIT;
        opcodes[0x7D] = BRKIMPLICIT;
        opcodes[0x7E] = BRKIMPLICIT;
        opcodes[0x7F] = BRKIMPLICIT;

        opcodes[0x80] = BRKIMPLICIT;
        opcodes[0x81] = BRKIMPLICIT;
        opcodes[0x82] = BRKIMPLICIT;
        opcodes[0x83] = BRKIMPLICIT;
        opcodes[0x84] = BRKIMPLICIT;
        opcodes[0x85] = STAZEROPAGE;
        opcodes[0x86] = BRKIMPLICIT;
        opcodes[0x87] = BRKIMPLICIT;
        opcodes[0x88] = BRKIMPLICIT;
        opcodes[0x89] = BRKIMPLICIT;
        opcodes[0x8A] = BRKIMPLICIT;
        opcodes[0x8B] = BRKIMPLICIT;
        opcodes[0x8C] = BRKIMPLICIT;
        opcodes[0x8D] = STAABSOLUTE;
        opcodes[0x8E] = BRKIMPLICIT;
        opcodes[0x8F] = BRKIMPLICIT;

        opcodes[0x90] = BRKIMPLICIT;
        opcodes[0x91] = BRKIMPLICIT;
        opcodes[0x92] = KIL;
        opcodes[0x93] = BRKIMPLICIT;
        opcodes[0x94] = BRKIMPLICIT;
        opcodes[0x95] = BRKIMPLICIT;
        opcodes[0x96] = BRKIMPLICIT;
        opcodes[0x97] = BRKIMPLICIT;
        opcodes[0x98] = BRKIMPLICIT;
        opcodes[0x99] = BRKIMPLICIT;
        opcodes[0x9A] = BRKIMPLICIT;
        opcodes[0x9B] = BRKIMPLICIT;
        opcodes[0x9C] = BRKIMPLICIT;
        opcodes[0x9D] = BRKIMPLICIT;
        opcodes[0x9E] = BRKIMPLICIT;
        opcodes[0x9F] = BRKIMPLICIT;

        opcodes[0xA0] = BRKIMPLICIT;
        opcodes[0xA1] = LDAINDIRECTX;
        opcodes[0xA2] = BRKIMPLICIT;
        opcodes[0xA3] = BRKIMPLICIT;
        opcodes[0xA4] = BRKIMPLICIT;
        opcodes[0xA5] = LDAIMMEDIATE;
        opcodes[0xA6] = BRKIMPLICIT;
        opcodes[0xA7] = BRKIMPLICIT;
        opcodes[0xA8] = BRKIMPLICIT;
        opcodes[0xA9] = LDAIMMEDIATE;
        opcodes[0xAA] = BRKIMPLICIT;
        opcodes[0xAB] = BRKIMPLICIT;
        opcodes[0xAC] = BRKIMPLICIT;
        opcodes[0xAD] = BRKIMPLICIT;
        opcodes[0xAE] = BRKIMPLICIT;
        opcodes[0xAF] = BRKIMPLICIT;

        opcodes[0xB0] = BRKIMPLICIT;
        opcodes[0xB1] = BRKIMPLICIT;
        opcodes[0xB2] = KIL;
        opcodes[0xB3] = BRKIMPLICIT;
        opcodes[0xB4] = BRKIMPLICIT;
        opcodes[0xB5] = BRKIMPLICIT;
        opcodes[0xB6] = BRKIMPLICIT;
        opcodes[0xB7] = BRKIMPLICIT;
        opcodes[0xB8] = BRKIMPLICIT;
        opcodes[0xB9] = BRKIMPLICIT;
        opcodes[0xBA] = BRKIMPLICIT;
        opcodes[0xBB] = BRKIMPLICIT;
        opcodes[0xBC] = BRKIMPLICIT;
        opcodes[0xBD] = BRKIMPLICIT;
        opcodes[0xBE] = BRKIMPLICIT;
        opcodes[0xBF] = BRKIMPLICIT;

        opcodes[0xC0] = BRKIMPLICIT;
        opcodes[0xC1] = CMPINDIRECTX;
        opcodes[0xC2] = BRKIMPLICIT;
        opcodes[0xC3] = BRKIMPLICIT;
        opcodes[0xC4] = BRKIMPLICIT;
        opcodes[0xC5] = CMPZEROPAGE;
        opcodes[0xC6] = BRKIMPLICIT;
        opcodes[0xC7] = BRKIMPLICIT;
        opcodes[0xC8] = BRKIMPLICIT;
        opcodes[0xC9] = CMPIMMEDIATE;
        opcodes[0xCA] = DEXIMPLICIT;
        opcodes[0xCB] = BRKIMPLICIT;
        opcodes[0xCC] = BRKIMPLICIT;
        opcodes[0xCD] = CMPABSOLUTE;
        opcodes[0xCE] = BRKIMPLICIT;
        opcodes[0xCF] = BRKIMPLICIT;

        opcodes[0xD0] = BRKIMPLICIT;
        opcodes[0xD1] = CMPINDIRECTY;
        opcodes[0xD2] = KIL;
        opcodes[0xD3] = BRKIMPLICIT;
        opcodes[0xD4] = BRKIMPLICIT;
        opcodes[0xD5] = CMPZEROPAGEX;
        opcodes[0xD6] = DECZEROPAGEX;
        opcodes[0xD7] = BRKIMPLICIT;
        opcodes[0xD8] = CLDIMPLICIT;
        opcodes[0xD9] = CMPABSOLUTEY;
        opcodes[0xDA] = BRKIMPLICIT;
        opcodes[0xDB] = BRKIMPLICIT;
        opcodes[0xDC] = BRKIMPLICIT;
        opcodes[0xDD] = CMPABSOLUTEX;
        opcodes[0xDE] = BRKIMPLICIT;
        opcodes[0xDF] = BRKIMPLICIT;

        opcodes[0xE0] = BRKIMPLICIT;
        opcodes[0xE1] = BRKIMPLICIT;
        opcodes[0xE2] = BRKIMPLICIT;
        opcodes[0xE3] = BRKIMPLICIT;
        opcodes[0xE4] = BRKIMPLICIT;
        opcodes[0xE5] = BRKIMPLICIT;
        opcodes[0xE6] = BRKIMPLICIT;
        opcodes[0xE7] = BRKIMPLICIT;
        opcodes[0xE8] = BRKIMPLICIT;
        opcodes[0xE9] = BRKIMPLICIT;
        opcodes[0xEA] = NOPIMPLICIT;
        opcodes[0xEB] = BRKIMPLICIT;
        opcodes[0xEC] = BRKIMPLICIT;
        opcodes[0xED] = BRKIMPLICIT;
        opcodes[0xEE] = BRKIMPLICIT;
        opcodes[0xEF] = BRKIMPLICIT;

        opcodes[0xF0] = BEQRELATIVE;
        opcodes[0xF1] = BRKIMPLICIT;
        opcodes[0xF2] = KIL;
        opcodes[0xF3] = BRKIMPLICIT;
        opcodes[0xF4] = BRKIMPLICIT;
        opcodes[0xF5] = BRKIMPLICIT;
        opcodes[0xF6] = BRKIMPLICIT;
        opcodes[0xF7] = BRKIMPLICIT;
        opcodes[0xF8] = BRKIMPLICIT;
        opcodes[0xF9] = BRKIMPLICIT;
        opcodes[0xFA] = BRKIMPLICIT;
        opcodes[0xFB] = BRKIMPLICIT;
        opcodes[0xFC] = BRKIMPLICIT;
        opcodes[0xFD] = BRKIMPLICIT;
        opcodes[0xFE] = BRKIMPLICIT;
        opcodes[0xFF] = BRKIMPLICIT;

    }
}

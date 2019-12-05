package GBEmyu.cpu;

public class OpcodeBuilder {
    private Opcode[] opcodes;
    private Register register;
    private int[] inst;
    private  Opcode BRK =  new Opcode("BRK", 0x00){
        @Override
        public void operation(){
        }
    };

    private  Opcode SEI =  new Opcode("SEI", 0x78){
        @Override
        public void operation(){
            register.setSEI();
        }
    };

    private  Opcode CLD =  new Opcode("CLD", 0xD8){
        @Override
        public void operation(){
            register.setCLD();
        }
    };
    private  Opcode LDA =  new Opcode("LDA", 0xA9){
        @Override
        public void operation(){
            register.setLDA(inst[register.getPC()+1]);
        }
    };
    private  Opcode LDAINDIRECTX =  new Opcode("LDAINDIRECTX", 0xA1){
        @Override
        public void operation(){

        }
    };

    private  Opcode DEX = new Opcode("DEX", 0xCA){
        @Override
        public void operation(){
            register.setDEX();
        }
    };

    private  Opcode ROLZEROPAGE = new Opcode("ROLZEROPAGE", 0x26){
        @Override
        public void operation(){

        }
    };
    private  Opcode CLC = new Opcode("CLC", 0x18){
        @Override
        public void operation(){

        }
    };
    private  Opcode NOP = new Opcode("NOP", 0x1A){
        @Override
        public void operation(){

        }
    };
    private Opcode JSR = new Opcode("JSR", 0x20){
        @Override
        public void operation(){

        }
    };
    private Opcode ROR = new Opcode("ROR", 0x66){
        @Override
        public void operation(){

        }
    };
    private Opcode PLA = new Opcode("PLA", 0x68){
        @Override
        public void operation(){

        }
    };
    private Opcode ADC = new Opcode("ADC", 0x69){
        @Override
        public void operation(){

        }
    };
    private Opcode STAZEROPAGE = new Opcode("STAZEROPAGE", 0x85){
        @Override
        public void operation(){

        }
    };
    private Opcode STAABSOLUTE = new Opcode("STAABSOLUTE", 0x8D){
        @Override
        public void operation(){

        }
    };
    private Opcode DECZEROPAGEX = new Opcode("DECZEROPAGEX", 0xD6){
        @Override
        public void operation(){

        }
    };
    private Opcode BEQ = new Opcode("BEQ", 0xF0){
        @Override
        public void operation(){

        }
    };
    private Opcode KIL = new Opcode("KIL", 0x02){
        @Override
        public void operation(){

        }
    };
    private Opcode SLO = new Opcode("SLO", 0x03){
        @Override
        public void operation(){

        }
    };
    private  Opcode ORAINDIRECTX = new Opcode("ORAINDIRECTX", 0x01){
        @Override
        public void operation(){

        }
    };
    private  Opcode ORAZEROPAGE = new Opcode("ORAZEROPAGE", 0x05){
        @Override
        public void operation(){

        }
    };
    private  Opcode ORAABSOLUTE= new Opcode("ORAABSOLUTE", 0x0D){
        @Override
        public void operation(){

        }
    };
    private  Opcode ORAIMMEDIATE= new Opcode("ORAIMMEDIATE", 0x09){
        @Override
        public void operation(){

        }
    };
    private  Opcode ASLZEROPAGE= new Opcode("ASLZEROPAGE", 0x06){
        @Override
        public void operation(){

        }
    };
    private  Opcode ASLABSOLUTE= new Opcode("ASLABSOLUTE", 0x0E){
        @Override
        public void operation(){

        }
    };
    private  Opcode ASLACCUMULATOR = new Opcode("ASLACCUMULATOR", 0x0A){
        @Override
        public void operation(){

        }
    };
    private  Opcode PHP = new Opcode("PHP", 0x08){
        @Override
        public void operation(){

        }
    };
    //http://www.oxyron.de/html/opcodes02.html  Suceh nach ANC.
    private  Opcode ANC = new Opcode("ANC", 0x0B){
        @Override
        public void operation(){

        }
    };
    private  Opcode LSRZEROPAGE = new Opcode("LSRZEROPAGE", 0x46){
        @Override
        public void operation(){

        }
    };
    private  Opcode PHA = new Opcode("PHA", 0x48){
        @Override
        public void operation(){

        }
    };
    private  Opcode CMPABSOLUTEX = new Opcode("CMPABSOLUTEX", 0xDD){
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
        opcodes[0x00] = BRK;
        opcodes[0x01] = ORAINDIRECTX; //ORA (Indirect, X)
        opcodes[0x02] = KIL;
        opcodes[0x03] = SLO;
        opcodes[0x04] = NOP;
        opcodes[0x05] = ORAZEROPAGE;
        opcodes[0x06] = ASLZEROPAGE;
        opcodes[0x07] = SLO;
        opcodes[0x08] = PHP;
        opcodes[0x09] = ORAIMMEDIATE;
        opcodes[0x0A] = ASLACCUMULATOR;
        opcodes[0x0B] = ANC;
        opcodes[0x0C] = NOP;
        opcodes[0x0D] = ORAABSOLUTE;
        opcodes[0x0E] = ASLABSOLUTE;
        opcodes[0x0F] = SLO;

        opcodes[0x10] = BRK;
        opcodes[0x11] = BRK;
        opcodes[0x12] = BRK;
        opcodes[0x13] = BRK;
        opcodes[0x14] = BRK;
        opcodes[0x15] = BRK;
        opcodes[0x16] = BRK;
        opcodes[0x17] = BRK;
        opcodes[0x18] = CLC;
        opcodes[0x19] = BRK;
        opcodes[0x1A] = NOP; // done housekeeping
        opcodes[0x1B] = BRK;
        opcodes[0x1C] = BRK;
        opcodes[0x1D] = BRK;
        opcodes[0x1E] = BRK;
        opcodes[0x1F] = BRK;

        opcodes[0x20] = JSR;
        opcodes[0x21] = BRK;
        opcodes[0x22] = BRK;
        opcodes[0x23] = BRK;
        opcodes[0x24] = BRK;
        opcodes[0x25] = BRK;
        opcodes[0x26] = ROLZEROPAGE;
        opcodes[0x27] = BRK;
        opcodes[0x28] = BRK;
        opcodes[0x29] = BRK;
        opcodes[0x2A] = BRK;
        opcodes[0x2B] = BRK;
        opcodes[0x2C] = BRK;
        opcodes[0x2D] = BRK;
        opcodes[0x2E] = BRK;
        opcodes[0x2F] = BRK;

        opcodes[0x30] = BRK;
        opcodes[0x31] = BRK;
        opcodes[0x32] = BRK;
        opcodes[0x33] = BRK;
        opcodes[0x34] = BRK;
        opcodes[0x35] = BRK;
        opcodes[0x36] = BRK;
        opcodes[0x37] = BRK;
        opcodes[0x38] = BRK;
        opcodes[0x39] = BRK;
        opcodes[0x3A] = BRK;
        opcodes[0x3B] = BRK;
        opcodes[0x3C] = BRK;
        opcodes[0x3D] = BRK;
        opcodes[0x3E] = BRK;
        opcodes[0x3F] = BRK;

        opcodes[0x40] = BRK;
        opcodes[0x41] = BRK;
        opcodes[0x42] = BRK;
        opcodes[0x43] = BRK;
        opcodes[0x44] = BRK;
        opcodes[0x45] = BRK;
        opcodes[0x46] = LSRZEROPAGE;
        opcodes[0x47] = BRK;
        opcodes[0x48] = PHA;
        opcodes[0x49] = BRK;
        opcodes[0x4A] = BRK;
        opcodes[0x4B] = BRK;
        opcodes[0x4C] = BRK;
        opcodes[0x4D] = BRK;
        opcodes[0x4E] = BRK;
        opcodes[0x4F] = BRK;

        opcodes[0x50] = BRK;
        opcodes[0x51] = BRK;
        opcodes[0x52] = BRK;
        opcodes[0x53] = BRK;
        opcodes[0x54] = BRK;
        opcodes[0x55] = BRK;
        opcodes[0x56] = BRK;
        opcodes[0x57] = BRK;
        opcodes[0x58] = BRK;
        opcodes[0x59] = BRK;
        opcodes[0x5A] = BRK;
        opcodes[0x5B] = BRK;
        opcodes[0x5C] = BRK;
        opcodes[0x5D] = BRK;
        opcodes[0x5E] = BRK;
        opcodes[0x5F] = BRK;

        opcodes[0x60] = BRK;
        opcodes[0x61] = BRK;
        opcodes[0x62] = BRK;
        opcodes[0x63] = BRK;
        opcodes[0x64] = BRK;
        opcodes[0x65] = BRK;
        opcodes[0x66] = ROR;
        opcodes[0x67] = BRK;
        opcodes[0x68] = PLA;
        opcodes[0x69] = ADC;
        opcodes[0x6A] = BRK;
        opcodes[0x6B] = BRK;
        opcodes[0x6C] = BRK;
        opcodes[0x6D] = BRK;
        opcodes[0x6E] = BRK;
        opcodes[0x6F] = BRK;

        opcodes[0x70] = BRK;
        opcodes[0x71] = BRK;
        opcodes[0x72] = BRK;
        opcodes[0x73] = BRK;
        opcodes[0x74] = BRK;
        opcodes[0x75] = BRK;
        opcodes[0x76] = BRK;
        opcodes[0x77] = BRK;
        opcodes[0x78] = SEI;
        opcodes[0x79] = BRK;
        opcodes[0x7A] = BRK;
        opcodes[0x7B] = BRK;
        opcodes[0x7C] = BRK;
        opcodes[0x7D] = BRK;
        opcodes[0x7E] = BRK;
        opcodes[0x7F] = BRK;

        opcodes[0x80] = BRK;
        opcodes[0x81] = BRK;
        opcodes[0x82] = BRK;
        opcodes[0x83] = BRK;
        opcodes[0x84] = BRK;
        opcodes[0x85] = STAZEROPAGE;
        opcodes[0x86] = BRK;
        opcodes[0x87] = BRK;
        opcodes[0x88] = BRK;
        opcodes[0x89] = BRK;
        opcodes[0x8A] = BRK;
        opcodes[0x8B] = BRK;
        opcodes[0x8C] = BRK;
        opcodes[0x8D] = STAABSOLUTE;
        opcodes[0x8E] = BRK;
        opcodes[0x8F] = BRK;

        opcodes[0x90] = BRK;
        opcodes[0x91] = BRK;
        opcodes[0x92] = BRK;
        opcodes[0x93] = BRK;
        opcodes[0x94] = BRK;
        opcodes[0x95] = BRK;
        opcodes[0x96] = BRK;
        opcodes[0x97] = BRK;
        opcodes[0x98] = BRK;
        opcodes[0x99] = BRK;
        opcodes[0x9A] = BRK;
        opcodes[0x9B] = BRK;
        opcodes[0x9C] = BRK;
        opcodes[0x9D] = BRK;
        opcodes[0x9E] = BRK;
        opcodes[0x9F] = BRK;

        opcodes[0xA0] = BRK;
        opcodes[0xA1] = LDAINDIRECTX; // TODO: change to LDA
        opcodes[0xA2] = BRK;
        opcodes[0xA3] = BRK;
        opcodes[0xA4] = BRK;
        opcodes[0xA5] = LDA;
        opcodes[0xA6] = BRK;
        opcodes[0xA7] = BRK;
        opcodes[0xA8] = BRK;
        opcodes[0xA9] = LDA;
        opcodes[0xAA] = BRK;
        opcodes[0xAB] = BRK;
        opcodes[0xAC] = BRK;
        opcodes[0xAD] = BRK;
        opcodes[0xAE] = BRK;
        opcodes[0xAF] = BRK;

        opcodes[0xB0] = BRK;
        opcodes[0xB1] = BRK;
        opcodes[0xB2] = BRK;
        opcodes[0xB3] = BRK;
        opcodes[0xB4] = BRK;
        opcodes[0xB5] = BRK;
        opcodes[0xB6] = BRK;
        opcodes[0xB7] = BRK;
        opcodes[0xB8] = BRK;
        opcodes[0xB9] = BRK;
        opcodes[0xBA] = BRK;
        opcodes[0xBB] = BRK;
        opcodes[0xBC] = BRK;
        opcodes[0xBD] = BRK;
        opcodes[0xBE] = BRK;
        opcodes[0xBF] = BRK;

        opcodes[0xC0] = BRK;
     //   opcodes[0xC1] = CMPINDIRECTX;
        opcodes[0xC2] = BRK;
        opcodes[0xC3] = BRK;
        opcodes[0xC4] = BRK;
     //   opcodes[0xC5] = CMPZEROPAGE;
        opcodes[0xC6] = BRK;
        opcodes[0xC7] = BRK;
        opcodes[0xC8] = BRK;
     //   opcodes[0xC9] = CMPIMMEDIATE;
        opcodes[0xCA] = DEX;
        opcodes[0xCB] = BRK;
        opcodes[0xCC] = BRK;
     //   opcodes[0xCD] = CMPABSOLUTE;
        opcodes[0xCE] = BRK;
        opcodes[0xCF] = BRK;

        opcodes[0xD0] = BRK;
     //   opcodes[0xD1] = CMPINDIRECTY;
        opcodes[0xD2] = BRK;
        opcodes[0xD3] = BRK;
        opcodes[0xD4] = BRK;
     //   opcodes[0xD5] = CMPZEROPAGEX;
        opcodes[0xD6] = DECZEROPAGEX;
        opcodes[0xD7] = BRK;
        opcodes[0xD8] = CLD;
     //   opcodes[0xD9] = CMPABSOLUTEY;
        opcodes[0xDA] = BRK;
        opcodes[0xDB] = BRK;
        opcodes[0xDC] = BRK;
        opcodes[0xDD] = CMPABSOLUTEX;
        opcodes[0xDE] = BRK;
        opcodes[0xDF] = BRK;

        opcodes[0xE0] = BRK;
        opcodes[0xE1] = BRK;
        opcodes[0xE2] = BRK;
        opcodes[0xE3] = BRK;
        opcodes[0xE4] = BRK;
        opcodes[0xE5] = BRK;
        opcodes[0xE6] = BRK;
        opcodes[0xE7] = BRK;
        opcodes[0xE8] = BRK;
        opcodes[0xE9] = BRK;
        opcodes[0xEA] = NOP;
        opcodes[0xEB] = BRK;
        opcodes[0xEC] = BRK;
        opcodes[0xED] = BRK;
        opcodes[0xEE] = BRK;
        opcodes[0xEF] = BRK;

        opcodes[0xF0] = BEQ;
        opcodes[0xF1] = BRK;
        opcodes[0xF2] = BRK;
        opcodes[0xF3] = BRK;
        opcodes[0xF4] = BRK;
        opcodes[0xF5] = BRK;
        opcodes[0xF6] = BRK;
        opcodes[0xF7] = BRK;
        opcodes[0xF8] = BRK;
        opcodes[0xF9] = BRK;
        opcodes[0xFA] = BRK;
        opcodes[0xFB] = BRK;
        opcodes[0xFC] = BRK;
        opcodes[0xFD] = BRK;
        opcodes[0xFE] = BRK;
        opcodes[0xFF] = BRK;

    }
}

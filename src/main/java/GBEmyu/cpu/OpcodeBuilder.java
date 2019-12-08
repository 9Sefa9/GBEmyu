package GBEmyu.cpu;

public class OpcodeBuilder {
    private Opcode[] opcodes;
    private Register register;
    private int[] inst;
    private  Opcode BRKIMPLICIT =  new Opcode(Register.AddressModes.IMPLICIT,"BRKIMPLICIT", 0x00,7){
        @Override
        public void operation(int[] operanden){

        }
    };






    //LDA
    private  Opcode LDAIMMEDIATE =  new Opcode(Register.AddressModes.IMMEDIATE,"LDAIMMEDIATE", 0xA9,2){
        @Override
        public void operation(int[] operanden){
            register.setLDA(inst[register.getPC()+1]);
        }
    };
    private  Opcode LDAZEROPAGE =  new Opcode(Register.AddressModes.ZEROPAGE,"LDAZEROPAGE", 0xA5,3){
        @Override
        public void operation(int[] operanden){
            register.setLDA(inst[register.getPC()+1]);
        }
    };
    private  Opcode LDAZEROPAGEX =  new Opcode(Register.AddressModes.ZEROPAGEX,"LDAZEROPAGEX", 0xB5,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LDAINDIRECTX =  new Opcode(Register.AddressModes.INDIRECTX,"LDAINDIRECTX", 0xA1,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LDAINDIRECTY =  new Opcode(Register.AddressModes.INDIRECTY,"LDAINDIRECTY", 0xB1,5){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LDAABSOLUTE =  new Opcode(Register.AddressModes.ABSOLUTE,"LDAABSOLUTE", 0xAD,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LDAABSOLUTEX =  new Opcode(Register.AddressModes.ABSOLUTEX,"LDAABSOLUTEX", 0xBD,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LDAABSOLUTEY =  new Opcode(Register.AddressModes.ABSOLUTEY,"LDAABSOLUTEY", 0xB9,4){
        @Override
        public void operation(int[] operanden){

        }
    };




    //LDX
    private  Opcode LDXIMMEDIATE=  new Opcode(Register.AddressModes.IMMEDIATE,"LDXIMMEDIATE", 0xA2,2){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LDXZEROPAGE =  new Opcode(Register.AddressModes.ZEROPAGE,"LDXZEROPAGE", 0xA6,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LDXZEROPAGEX =  new Opcode(Register.AddressModes.ZEROPAGEX,"LDXZEROPAGEX", 0xB6,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LDXABSOLUTE =  new Opcode(Register.AddressModes.ABSOLUTE,"LDXABSOLUTE", 0xAE,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LDXABSOLUTEX =  new Opcode(Register.AddressModes.ABSOLUTEX,"LDXABSOLUTEX", 0xBE,4){
        @Override
        public void operation(int[] operanden){

        }
    };



    //LDY
    private  Opcode LDYIMMEDIATE=  new Opcode(Register.AddressModes.IMMEDIATE,"LDYIMMEDIATE", 0xA0,2){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LDYZEROPAGE =  new Opcode(Register.AddressModes.ZEROPAGE,"LDYZEROPAGE", 0xA4,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LDYZEROPAGEX =  new Opcode(Register.AddressModes.ZEROPAGEX,"LDYZEROPAGEX", 0xB4,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LDYABSOLUTE =  new Opcode(Register.AddressModes.ABSOLUTE,"LDYABSOLUTE", 0xAC,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LDYABSOLUTEX =  new Opcode(Register.AddressModes.ABSOLUTEX,"LDYABSOLUTEX", 0xBC,4){
        @Override
        public void operation(int[] operanden){

        }
    };


    //STA
    private Opcode STAZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"STAZEROPAGE", 0x85,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode STAABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"STAABSOLUTE", 0x8D,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode STAABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"STAABSOLUTEX", 0x9D,5){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode STAABSOLUTEY = new Opcode(Register.AddressModes.ABSOLUTEY,"STAABSOLUTEY", 0x99,5){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode STAINDIRECTX= new Opcode(Register.AddressModes.INDIRECTX,"STAINDIRECTX", 0x81,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode STAINDIRECTY= new Opcode(Register.AddressModes.INDIRECTY,"STAINDIRECTY", 0x91,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode STAZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"STAZEROPAGEX", 0x95,4){
        @Override
        public void operation(int[] operanden){

        }
    };


    //STX
    private Opcode STXZEROPAGE= new Opcode(Register.AddressModes.ZEROPAGE,"STXZEROPAGE", 0x86,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode STXZEROPAGEY= new Opcode(Register.AddressModes.ZEROPAGEY,"STXZEROPAGEY", 0x96,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode STXABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"STXABSOLUTE", 0x8E,4){
        @Override
        public void operation(int[] operanden){

        }
    };



    //STX
    private Opcode STYZEROPAGE= new Opcode(Register.AddressModes.ZEROPAGE,"STYZEROPAGE", 0x84,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode STYZEROPAGEY= new Opcode(Register.AddressModes.ZEROPAGEY,"STYZEROPAGEY", 0x94,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode STYABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"STYABSOLUTE", 0x8C,4){
        @Override
        public void operation(int[] operanden){

        }
    };



    //TAX
    private Opcode TAXIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"TAXIMPLICIT", 0xAA,2){
        @Override
        public void operation(int[] operanden){

        }
    };

    //TAY
    private Opcode TAYIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"TAYIMPLICIT", 0xA8,2){
        @Override
        public void operation(int[] operanden){

        }
    };

    //TSX
    private Opcode TSXIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"TSXIMPLICIT", 0xBA,2){
        @Override
        public void operation(int[] operanden){

        }
    };

    //TSA
    private Opcode TSAIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"TSAIMPLICIT", 0x8A,2){
        @Override
        public void operation(int[] operanden){

        }
    };

    //TXS
    private Opcode TXSIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"TXSIMPLICIT", 0x9A,2){
        @Override
        public void operation(int[] operanden){

        }
    };

    //TYA
    private Opcode TYAIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"TYAIMPLICIT", 0x98,2){
        @Override
        public void operation(int[] operanden){

        }
    };

    //ADC
    private Opcode ADCIMMEDIATE = new Opcode(Register.AddressModes.IMMEDIATE,"ADCIMMEDIATE", 0x69,2){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode ADCZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"ADCZEROPAGE", 0x65,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode ADCZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"ADCZEROPAGEX", 0x75,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode ADCABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"ADCABSOLUTE", 0x6D,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode ADCABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"ADCABSOLUTEX", 0x7D,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode ADCABSOLUTEY = new Opcode(Register.AddressModes.ABSOLUTEY,"ADCABSOLUTEY", 0x79,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode ADCINDIRECTX= new Opcode(Register.AddressModes.INDIRECTX,"ADCINDIRECTX", 0x61,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode ADCINDIRECTY= new Opcode(Register.AddressModes.INDIRECTY,"ADCINDIRECTY", 0x71,5){
        @Override
        public void operation(int[] operanden){

        }
    };

    //DEC

    private Opcode DECZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"DECZEROPAGEX", 0xD6,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode DECZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"DECZEROPAGE", 0xC6,5){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode DECABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"DECABSOLUTE", 0xCE,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode DECABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"DECABSOLUTEX", 0xDE,7){
        @Override
        public void operation(int[] operanden){

        }
    };



    //DEX

    private  Opcode DEXIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"DEXIMPLICIT", 0xCA,2){
        @Override
        public void operation(int[] operanden){
            register.setDEX();
        }
    };



    //DEY
    private  Opcode DEYIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"DEYIMPLICIT", 0x88,2){
        @Override
        public void operation(int[] operanden){
            register.setDEX();
        }
    };




    //INC
    private  Opcode INCZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"INCZEROPAGE", 0xE6,5){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode INCZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"INCZEROPAGEX", 0xF6,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode INCABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"INCABSOLUTE", 0xEE,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode INCABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"INCABSOLUTEX", 0xFE,7){
        @Override
        public void operation(int[] operanden){

        }
    };

    //INX
    private  Opcode INXIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"INXIMPLICIT", 0xE8,2){
        @Override
        public void operation(int[] operanden){

        }
    };



    //INY
    private  Opcode INYIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"INYIMPLICIT", 0xC8,2){
        @Override
        public void operation(int[] operanden){

        }
    };



    //SBC
    private  Opcode SBCIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"SBCIMPLICIT", 0xE9,2){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode SBCZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"SBCZEROPAGE", 0xE5,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode SBCZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"SBCZEROPAGEX", 0xF5,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode SBCABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"SBCABSOLUTE", 0xED,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode SBCABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"SBCABSOLUTEX", 0xFD,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode SBCABSOLUTEY = new Opcode(Register.AddressModes.ABSOLUTEY,"SBCABSOLUTEY", 0xF9,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode SBCINDIRECTX = new Opcode(Register.AddressModes.INDIRECTX,"SBCINDIRECTX", 0xE1,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode SBCINDIRECTY = new Opcode(Register.AddressModes.INDIRECTY,"SBCINDIRECTY", 0xF1,5){
        @Override
        public void operation(int[] operanden){

        }
    };



    //AND
    private  Opcode ANDIMMEDIATE = new Opcode(Register.AddressModes.IMMEDIATE,"ANDIMMEDIATE", 0x29,2){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ANDZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"ANDZEROPAGE", 0x25,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ANDZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"ANDZEROPAGEX", 0x35,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ANDABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"ANDABSOLUTE", 0x2D,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ANDABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"ANDABSOLUTEX", 0x3D,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ANDABSOLUTEY = new Opcode(Register.AddressModes.ABSOLUTEY,"ANDABSOLUTEY", 0x39,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ANDINDIRECTX = new Opcode(Register.AddressModes.INDIRECTX,"ANDINDIRECTX", 0x21,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ANDINDIRECTY = new Opcode(Register.AddressModes.INDIRECTY,"ANDINDIRECTY", 0x31,5){
        @Override
        public void operation(int[] operanden){

        }
    };



    //ASL
    private  Opcode ASLABSOLUTEX= new Opcode(Register.AddressModes.ABSOLUTEX,"ASLABSOLUTEX", 0x1E,7){
        @Override
        public void operation(int[] operanden){

        }
    };

    private  Opcode ASLZEROPAGEX= new Opcode(Register.AddressModes.ZEROPAGEX,"ASLZEROPAGEX", 0x16,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ASLZEROPAGE= new Opcode(Register.AddressModes.ZEROPAGE,"ASLZEROPAGE", 0x06,5){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ASLABSOLUTE= new Opcode(Register.AddressModes.ABSOLUTE,"ASLABSOLUTE", 0x0E,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ASLACCUMULATOR = new Opcode(Register.AddressModes.ACCUMULATOR,"ASLACCUMULATOR", 0x0A,2){
        @Override
        public void operation(int[] operanden){

        }
    };




    //BIT
    private  Opcode BITZEROPAGE= new Opcode(Register.AddressModes.ZEROPAGE,"BITZEROPAGE", 0x24,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode BITABSOLUTE= new Opcode(Register.AddressModes.ABSOLUTE,"BITABSOLUTE", 0x2C,4){
        @Override
        public void operation(int[] operanden){

        }
    };



    //EOR
    private  Opcode EORIMMEDIATE = new Opcode(Register.AddressModes.IMMEDIATE,"EORIMMEDIATE", 0x49,2){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode EORABSOLUTE= new Opcode(Register.AddressModes.ABSOLUTE,"EORABSOLUTE", 0x4D,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode EORABSOLUTEX= new Opcode(Register.AddressModes.ABSOLUTEX,"EORABSOLUTEX", 0x5D,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode EORABSOLUTEY= new Opcode(Register.AddressModes.ABSOLUTE,"EORABSOLUTEY", 0x59,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode EORZEROPAGEX= new Opcode(Register.AddressModes.ZEROPAGEX,"EORZEROPAGEX", 0x55,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode EORZEROPAGE= new Opcode(Register.AddressModes.ZEROPAGE,"EORZEROPAGE", 0x45,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode EORINDIRECTX= new Opcode(Register.AddressModes.INDIRECTX,"EORINDIRECTX", 0x41,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode EORINDIRECTY= new Opcode(Register.AddressModes.INDIRECTY,"EORINDIRECTY", 0x51,5){
        @Override
        public void operation(int[] operanden){

        }
    };


    //LSR
    private  Opcode LSRACCUMULATOR = new Opcode(Register.AddressModes.ACCUMULATOR,"LSRACCUMULATOR", 0x4A,2){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LSRZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"LSRZEROPAGE", 0x46,5){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LSRZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"LSRZEROPAGEX", 0x56,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LSRABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"LSRABSOLUTE", 0x4E,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode LSRABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"LSRABSOLUTEX", 0x5E,7){
        @Override
        public void operation(int[] operanden){

        }
    };



    //ORA
    private  Opcode ORAABSOLUTEY = new Opcode(Register.AddressModes.ABSOLUTEY,"ORAABSOLUTEY", 0x19,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ORAABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"ORAABSOLUTEX", 0x1D,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ORAINDIRECTY = new Opcode(Register.AddressModes.INDIRECTY,"ORAINDIRECTY", 0x11,5){
        @Override
        public void operation(int[] operanden){

        }
    };

    private  Opcode ORAZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"ORAZEROPAGEX", 0x15,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ORAINDIRECTX = new Opcode(Register.AddressModes.INDIRECTX,"ORAINDIRECTX", 0x01,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ORAZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"ORAZEROPAGE", 0x05,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ORAABSOLUTE= new Opcode(Register.AddressModes.ABSOLUTE,"ORAABSOLUTE", 0x0D,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ORAIMMEDIATE= new Opcode(Register.AddressModes.IMMEDIATE,"ORAIMMEDIATE", 0x09,2){
        @Override
        public void operation(int[] operanden){

        }
    };



    //ROL
    private  Opcode ROLZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"ROLZEROPAGE", 0x26,5){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ROLZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"ROLZEROPAGEX", 0x36,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ROLABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"ROLABSOLUTE", 0x2E,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ROLABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"ROLABSOLUTEX", 0x3E,7){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode ROLACCUMULATOR = new Opcode(Register.AddressModes.ACCUMULATOR,"ROLACCUMULATOR", 0x2A,2){
        @Override
        public void operation(int[] operanden){

        }
    };


    //ROR
    private Opcode RORZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"RORZEROPAGE", 0x66,5){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode RORACCUMULATOR = new Opcode(Register.AddressModes.ACCUMULATOR,"RORACCUMULATOR", 0x6A,2){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode RORZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"RORZEROPAGEX", 0x76,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode RORABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"RORABSOLUTEX", 0x7E,7){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode RORABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"RORABSOLUTE", 0x6E,6){
        @Override
        public void operation(int[] operanden){

        }
    };


    //CLC
    private  Opcode CLCIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"CLCIMPLICIT", 0x18,2){
        @Override
        public void operation(int[] operanden){

        }
    };

    //CLD
    private  Opcode CLDIMPLICIT =  new Opcode(Register.AddressModes.IMPLICIT,"CLDIMPLICIT", 0xD8,2){
        @Override
        public void operation(int[] operanden){
            register.setCLD();
        }
    };

    //CLI
    private  Opcode CLIIMPLICIT =  new Opcode(Register.AddressModes.IMPLICIT,"CLIIMPLICIT", 0x58,2){
        @Override
        public void operation(int[] operanden){
            register.setCLD();
        }
    };


    private  Opcode SEIIMPLICIT =  new Opcode(Register.AddressModes.IMPLICIT,"SEIIMPLICIT", 0x78,	2){
        @Override
        public void operation(int[] operanden){
            register.setSEI();
        }
    };






    private Opcode JSRABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"JSRABSOLUTE", 0x20,6){
        @Override
        public void operation(int[] operanden){

        }
    };

    private Opcode PLAIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"PLAIMPLICIT", 0x68,4){
        @Override
        public void operation(int[] operanden){

        }
    };



    private Opcode BEQRELATIVE = new Opcode(Register.AddressModes.RELATIVE,"BEQRELATIVE", 0xF0,2){
        @Override
        public void operation(int[] operanden){

        }
    };




    //halt the cpu, reset needed to continue..
    private Opcode KIL1 = new Opcode(Register.AddressModes.HLT,"KIL", 0x02,-1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL2 = new Opcode(Register.AddressModes.HLT,"KIL", 0x12,-1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL3= new Opcode(Register.AddressModes.HLT,"KIL", 0x22,-1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL4 = new Opcode(Register.AddressModes.HLT,"KIL", 0x32,-1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL5 = new Opcode(Register.AddressModes.HLT,"KIL", 0x42,-1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL6 = new Opcode(Register.AddressModes.HLT,"KIL", 0x52,-1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL7= new Opcode(Register.AddressModes.HLT,"KIL", 0x62,-1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL8 = new Opcode(Register.AddressModes.HLT,"KIL", 0x72,-1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL9 = new Opcode(Register.AddressModes.HLT,"KIL", 0x82,-1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL10 = new Opcode(Register.AddressModes.HLT,"KIL", 0x92,-1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL11 = new Opcode(Register.AddressModes.HLT,"KIL", 0xB2,-1){
        @Override
        public void operation(int[] operanden){

        }
    };private Opcode KIL12 = new Opcode(Register.AddressModes.HLT,"KIL", 0xD2,-1){
        @Override
        public void operation(int[] operanden){

        }
    };



    private Opcode SLOABSOLUTEY = new Opcode(Register.AddressModes.ABSOLUTEY,"SLOABSOLUTEY", 0x1B,7){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode SLOZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"SLOZEROPAGEX", 0x17,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode SLOABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"SLOABSOLUTE", 0x0F,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode SLOABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"SLOABSOLUTEX", 0x1F,7){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode SLOZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"SLOZEROPAGE", 0x07,5){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode SLOINDIRECTX = new Opcode(Register.AddressModes.INDIRECTX,"SLOINDIRECTX", 0x03,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode SLOINDIRECTY = new Opcode(Register.AddressModes.INDIRECTY,"SLOINDIRECTY", 0x13,8){
        @Override
        public void operation(int[] operanden){

        }
    };



    private  Opcode PHPIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"PHPIMPLICIT", 0x08,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    //http://www.oxyron.de/html/opcodes02.html  Suceh nach ANCIMMEDIATE.
    private  Opcode ANCIMMEDIATE = new Opcode(Register.AddressModes.IMMEDIATE,"ANCIMMEDIATE", 0x0B,2){
        @Override
        public void operation(int[] operanden){

        }
    };

    private  Opcode PHAIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"PHAIMPLICIT", 0x48,3){
        @Override
        public void operation(int[] operanden){

        }
    };

    private  Opcode CMPABSOLUTEY = new Opcode(Register.AddressModes.ABSOLUTEY,"CMPABSOLUTEY", 0xD9,4){
        @Override
        public void operation(int[] operanden){

        }
    };
       private  Opcode CMPINDIRECTY = new Opcode(Register.AddressModes.INDIRECTY,"CMPINDIRECTY", 0xD1,5){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode CMPABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"CMPABSOLUTEX", 0xDD,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode BPLRELATIVE = new Opcode(Register.AddressModes.RELATIVE,"BPLRELATIVE", 0x10,2){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode CMPABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"CMPABSOLUTE", 0xCD,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode CMPINDIRECTX = new Opcode(Register.AddressModes.INDIRECTX,"CMPINDIRECTX", 0xC1,6){
        @Override
        public void operation(int[] operanden){

        }
    };

    private  Opcode CMPIMMEDIATE = new Opcode(Register.AddressModes.IMMEDIATE,"CMPIMMEDIATE", 0xC9,2){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode CMPZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"CMPZEROPAGE", 0xC5,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode CMPZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"CMPZEROPAGEX", 0xD5,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    //opcodes[0x04] = NOPZEROPAGE;   opcodes[0x0C] = NOPABSOLUTE; opcodes[0x14] = NOPZEROPAGEX;  opcodes[0x1C] = NOPABSOLUTEX;
    private  Opcode NOPIMPLICIT = new Opcode(Register.AddressModes.IMPLICIT,"NOPIMPLICIT", 0xEA,2){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode NOPABSOLUTE = new Opcode(Register.AddressModes.ABSOLUTE,"NOPABSOLUTE", 0x0C,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode NOPABSOLUTEX = new Opcode(Register.AddressModes.ABSOLUTEX,"NOPABSOLUTEX", 0x1C,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode NOPZEROPAGE = new Opcode(Register.AddressModes.ZEROPAGE,"NOPZEROPAGE", 0x04,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode NOPZEROPAGEX = new Opcode(Register.AddressModes.ZEROPAGEX,"NOPZEROPAGEX", 0x14,4){
        @Override
        public void operation(int[] operanden){

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
        opcodes[0x02] = KIL1;
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
        opcodes[0x12] = KIL2;
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
        opcodes[0x21] = ANDINDIRECTX; //cycle 6
        opcodes[0x22] = KIL3;
        opcodes[0x23] = RLAINDIRECTX; //8
        opcodes[0x24] = BITZEROPAGE; //3
        opcodes[0x25] = ANDZEROPAGE; //3
        opcodes[0x26] = ROLZEROPAGE;
        opcodes[0x27] = RLAZEROPAGE; //5
        opcodes[0x28] = PLPIMPLICIT; //4
        opcodes[0x29] = ANDIMMEDIATE; //2
        opcodes[0x2A] = ROLACCUMULATOR; //2
        opcodes[0x2B] = ANDIMMEDIATE; //2
        opcodes[0x2C] = BITABSOLUTE; //4
        opcodes[0x2D] = ANDABSOLUTE; //4
        opcodes[0x2E] = ROLABSOLUTE;
        opcodes[0x2F] = RLAABSOLUTE; //6

        opcodes[0x30] = BRKIMPLICIT;
        opcodes[0x31] = ANDINDIRECTY;
        opcodes[0x32] = KIL4;
        opcodes[0x33] = BRKIMPLICIT;
        opcodes[0x34] = BRKIMPLICIT;
        opcodes[0x35] = ANDZEROPAGEX;
        opcodes[0x36] = ROLZEROPAGEX;
        opcodes[0x37] = BRKIMPLICIT;
        opcodes[0x38] = BRKIMPLICIT;
        opcodes[0x39] = ANDABSOLUTEY;
        opcodes[0x3A] = BRKIMPLICIT;
        opcodes[0x3B] = BRKIMPLICIT;
        opcodes[0x3C] = BRKIMPLICIT;
        opcodes[0x3D] = ANDABSOLUTEX;
        opcodes[0x3E] = ROLABSOLUTEX;
        opcodes[0x3F] = BRKIMPLICIT;

        opcodes[0x40] = BRKIMPLICIT;
        opcodes[0x41] = EORINDIRECTX;
        opcodes[0x42] = KIL5;
        opcodes[0x43] = BRKIMPLICIT;
        opcodes[0x44] = BRKIMPLICIT;
        opcodes[0x45] = EORZEROPAGE;
        opcodes[0x46] = LSRZEROPAGE;
        opcodes[0x47] = BRKIMPLICIT;
        opcodes[0x48] = PHAIMPLICIT;
        opcodes[0x49] = EORIMMEDIATE;
        opcodes[0x4A] = LSRACCUMULATOR;
        opcodes[0x4B] = BRKIMPLICIT;
        opcodes[0x4C] = BRKIMPLICIT;
        opcodes[0x4D] = EORABSOLUTE;
        opcodes[0x4E] = LSRABSOLUTE;
        opcodes[0x4F] = BRKIMPLICIT;

        opcodes[0x50] = BRKIMPLICIT;
        opcodes[0x51] = EORINDIRECTY;
        opcodes[0x52] = KIL6;
        opcodes[0x53] = BRKIMPLICIT;
        opcodes[0x54] = BRKIMPLICIT;
        opcodes[0x55] = EORZEROPAGEX;
        opcodes[0x56] = LSRZEROPAGEX;
        opcodes[0x57] = BRKIMPLICIT;
        opcodes[0x58] = CLIIMPLICIT;
        opcodes[0x59] = EORABSOLUTEY;
        opcodes[0x5A] = BRKIMPLICIT;
        opcodes[0x5B] = BRKIMPLICIT;
        opcodes[0x5C] = BRKIMPLICIT;
        opcodes[0x5D] = EORABSOLUTEX;
        opcodes[0x5E] = LSRABSOLUTEX;
        opcodes[0x5F] = BRKIMPLICIT;

        opcodes[0x60] = BRKIMPLICIT;
        opcodes[0x61] = ADCINDIRECTX;
        opcodes[0x62] = KIL7;
        opcodes[0x63] = BRKIMPLICIT;
        opcodes[0x64] = BRKIMPLICIT;
        opcodes[0x65] = ADCZEROPAGE;
        opcodes[0x66] = RORZEROPAGE;
        opcodes[0x67] = BRKIMPLICIT;
        opcodes[0x68] = PLAIMPLICIT;
        opcodes[0x69] = ADCIMMEDIATE;
        opcodes[0x6A] = RORACCUMULATOR;
        opcodes[0x6B] = BRKIMPLICIT;
        opcodes[0x6C] = BRKIMPLICIT;
        opcodes[0x6D] = ADCABSOLUTE;
        opcodes[0x6E] = RORABSOLUTE;
        opcodes[0x6F] = BRKIMPLICIT;

        opcodes[0x70] = BRKIMPLICIT;
        opcodes[0x71] = ADCINDIRECTY;
        opcodes[0x72] = KIL8;
        opcodes[0x73] = BRKIMPLICIT;
        opcodes[0x74] = BRKIMPLICIT;
        opcodes[0x75] = ADCZEROPAGEX;
        opcodes[0x76] = RORZEROPAGEX;
        opcodes[0x77] = BRKIMPLICIT;
        opcodes[0x78] = SEIIMPLICIT;
        opcodes[0x79] = ADCABSOLUTEY;
        opcodes[0x7A] = BRKIMPLICIT;
        opcodes[0x7B] = BRKIMPLICIT;
        opcodes[0x7C] = BRKIMPLICIT;
        opcodes[0x7D] = ADCABSOLUTEX;
        opcodes[0x7E] = RORABSOLUTEX;
        opcodes[0x7F] = BRKIMPLICIT;

        opcodes[0x80] = BRKIMPLICIT;
        opcodes[0x81] = STAINDIRECTX;
        opcodes[0x82] = BRKIMPLICIT;
        opcodes[0x83] = BRKIMPLICIT;
        opcodes[0x84] = STYZEROPAGE;
        opcodes[0x85] = STAZEROPAGE;
        opcodes[0x86] = STXZEROPAGE;
        opcodes[0x87] = BRKIMPLICIT;
        opcodes[0x88] = DEYIMPLICIT;
        opcodes[0x89] = BRKIMPLICIT;
        opcodes[0x8A] = TSAIMPLICIT;
        opcodes[0x8B] = BRKIMPLICIT;
        opcodes[0x8C] = STYABSOLUTE;
        opcodes[0x8D] = STAABSOLUTE;
        opcodes[0x8E] = STXABSOLUTE;
        opcodes[0x8F] = BRKIMPLICIT;

        opcodes[0x90] = BRKIMPLICIT;
        opcodes[0x91] = STAINDIRECTY;
        opcodes[0x92] = KIL9;
        opcodes[0x93] = BRKIMPLICIT;
        opcodes[0x94] = STYZEROPAGEY;
        opcodes[0x95] = STAZEROPAGEX;
        opcodes[0x96] = STXZEROPAGEY;
        opcodes[0x97] = BRKIMPLICIT;
        opcodes[0x98] = BRKIMPLICIT;
        opcodes[0x99] = STAABSOLUTEY;
        opcodes[0x9A] = TXSIMPLICIT;
        opcodes[0x9B] = BRKIMPLICIT;
        opcodes[0x9C] = BRKIMPLICIT;
        opcodes[0x9D] = STAABSOLUTEX;
        opcodes[0x9E] = BRKIMPLICIT;
        opcodes[0x9F] = BRKIMPLICIT;

        opcodes[0xA0] = LDYIMMEDIATE;
        opcodes[0xA1] = LDAINDIRECTX;
        opcodes[0xA2] = LDXIMMEDIATE;
        opcodes[0xA3] = BRKIMPLICIT;
        opcodes[0xA4] = LDYZEROPAGE;
        opcodes[0xA5] = LDAZEROPAGE;
        opcodes[0xA6] = LDXZEROPAGE;
        opcodes[0xA7] = BRKIMPLICIT;
        opcodes[0xA8] = TAYIMPLICIT;
        opcodes[0xA9] = LDAIMMEDIATE;
        opcodes[0xAA] = TAXIMPLICIT;
        opcodes[0xAB] = BRKIMPLICIT;
        opcodes[0xAC] = LDYABSOLUTE;
        opcodes[0xAD] = LDAABSOLUTE;
        opcodes[0xAE] = LDXABSOLUTE;
        opcodes[0xAF] = BRKIMPLICIT;

        opcodes[0xB0] = BRKIMPLICIT;
        opcodes[0xB1] = LDAINDIRECTY;
        opcodes[0xB2] = KIL10;
        opcodes[0xB3] = BRKIMPLICIT;
        opcodes[0xB4] = LDYZEROPAGEX;
        opcodes[0xB5] = LDAZEROPAGEX;
        opcodes[0xB6] = LDXZEROPAGEX;
        opcodes[0xB7] = BRKIMPLICIT;
        opcodes[0xB8] = BRKIMPLICIT;
        opcodes[0xB9] = LDAABSOLUTEY;
        opcodes[0xBA] = TSXIMPLICIT;
        opcodes[0xBB] = BRKIMPLICIT;
        opcodes[0xBC] = LDYABSOLUTEX;
        opcodes[0xBD] = LDAABSOLUTEX;
        opcodes[0xBE] = LDXABSOLUTEX;
        opcodes[0xBF] = BRKIMPLICIT;

        opcodes[0xC0] = BRKIMPLICIT;
        opcodes[0xC1] = CMPINDIRECTX;
        opcodes[0xC2] = BRKIMPLICIT;
        opcodes[0xC3] = BRKIMPLICIT;
        opcodes[0xC4] = BRKIMPLICIT;
        opcodes[0xC5] = CMPZEROPAGE;
        opcodes[0xC6] = BRKIMPLICIT;
        opcodes[0xC7] = BRKIMPLICIT;
        opcodes[0xC8] = INYIMPLICIT;
        opcodes[0xC9] = CMPIMMEDIATE;
        opcodes[0xCA] = DEXIMPLICIT;
        opcodes[0xCB] = BRKIMPLICIT;
        opcodes[0xCC] = BRKIMPLICIT;
        opcodes[0xCD] = CMPABSOLUTE;
        opcodes[0xCE] = BRKIMPLICIT;
        opcodes[0xCF] = BRKIMPLICIT;

        opcodes[0xD0] = BRKIMPLICIT;
        opcodes[0xD1] = CMPINDIRECTY;
        opcodes[0xD2] = KIL11;
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
        opcodes[0xE1] = SBCINDIRECTX;
        opcodes[0xE2] = BRKIMPLICIT;
        opcodes[0xE3] = BRKIMPLICIT;
        opcodes[0xE4] = BRKIMPLICIT;
        opcodes[0xE5] = SBCZEROPAGE;
        opcodes[0xE6] = INCZEROPAGE;
        opcodes[0xE7] = BRKIMPLICIT;
        opcodes[0xE8] = INXIMPLICIT;
        opcodes[0xE9] = SBCIMPLICIT;
        opcodes[0xEA] = NOPIMPLICIT;
        opcodes[0xEB] = BRKIMPLICIT;
        opcodes[0xEC] = BRKIMPLICIT;
        opcodes[0xED] = SBCABSOLUTE;
        opcodes[0xEE] = INCABSOLUTE;
        opcodes[0xEF] = BRKIMPLICIT;

        opcodes[0xF0] = BEQRELATIVE;
        opcodes[0xF1] = SBCINDIRECTY;
        opcodes[0xF2] = KIL12;
        opcodes[0xF3] = BRKIMPLICIT;
        opcodes[0xF4] = BRKIMPLICIT;
        opcodes[0xF5] = SBCZEROPAGEX;
        opcodes[0xF6] = INCZEROPAGEX;
        opcodes[0xF7] = BRKIMPLICIT;
        opcodes[0xF8] = BRKIMPLICIT;
        opcodes[0xF9] = SBCABSOLUTEY;
        opcodes[0xFA] = BRKIMPLICIT;
        opcodes[0xFB] = BRKIMPLICIT;
        opcodes[0xFC] = BRKIMPLICIT;
        opcodes[0xFD] = SBCABSOLUTEX;
        opcodes[0xFE] = INCABSOLUTEX;
        opcodes[0xFF] = BRKIMPLICIT;

    }
}

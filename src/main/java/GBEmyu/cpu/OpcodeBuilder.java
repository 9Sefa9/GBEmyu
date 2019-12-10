package GBEmyu.cpu;

public class OpcodeBuilder {
    private Opcode[] opcodes;
    private Register register;
    private int[] inst;

/*Bemerkungen:
    1. Die einzelnen cyclen NICHT in der jeweilige Operation verwenden und sie der haupt cycle erhöhen! Denn in der CPU klasse wird sowieso am ende
    der jeweilige cycle entnommen und erhöht. Hier ist also nur die operation von deuten. Die ienzelnen Inkrementetionen von Cyclen wird in der CPU klasse vorgenommen.
*/

//ä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#ö ====  OFFICIAL OPCODES SECTION  ====  ä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#ö


    //LDA
    private  Opcode LDAIMMEDIATE =  new Opcode(Flags.AddressModes.IMMEDIATE,"LDAIMMEDIATE", 0xA9,2){
        @Override
        public void operation(int[] operanden){
                register.lda(operanden[0]);
        }
    };
    private  Opcode LDAZEROPAGE =  new Opcode(Flags.AddressModes.ZEROPAGE,"LDAZEROPAGE", 0xA5,3){
        @Override
        public void operation(int[] operanden){
            register.lda(operanden[0]);
        }
    };
    private  Opcode LDAZEROPAGEX =  new Opcode(Flags.AddressModes.ZEROPAGEX,"LDAZEROPAGEX", 0xB5,4){
        @Override
        public void operation(int[] operanden){
            register.lda(operanden[0]);
        }
    };
    private  Opcode LDAINDIRECTX =  new Opcode(Flags.AddressModes.INDIRECTX,"LDAINDIRECTX", 0xA1,6){
        @Override
        public void operation(int[] operanden){
            register.lda(operanden[0]);
        }
    };
    private  Opcode LDAINDIRECTY =  new Opcode(Flags.AddressModes.INDIRECTY,"LDAINDIRECTY", 0xB1,5){
        @Override
        public void operation(int[] operanden){
            register.lda(operanden[0]);
        }
    };
    private  Opcode LDAABSOLUTE =  new Opcode(Flags.AddressModes.ABSOLUTE,"LDAABSOLUTE", 0xAD,4){
        @Override
        public void operation(int[] operanden){
            register.lda(operanden[0]);
        }
    };
    private  Opcode LDAABSOLUTEX =  new Opcode(Flags.AddressModes.ABSOLUTEX,"LDAABSOLUTEX", 0xBD,4){
        @Override
        public void operation(int[] operanden){
            register.lda(operanden[0]);
        }
    };
    private  Opcode LDAABSOLUTEY =  new Opcode(Flags.AddressModes.ABSOLUTEY,"LDAABSOLUTEY", 0xB9,4){
        @Override
        public void operation(int[] operanden){
            register.lda(operanden[0]);
        }
    };



    //LDX
    private  Opcode LDXIMMEDIATE=  new Opcode(Flags.AddressModes.IMMEDIATE,"LDXIMMEDIATE", 0xA2,2){
        @Override
        public void operation(int[] operanden){
            register.ldx(operanden[0]);
        }
    };
    private  Opcode LDXZEROPAGE =  new Opcode(Flags.AddressModes.ZEROPAGE,"LDXZEROPAGE", 0xA6,3){
        @Override
        public void operation(int[] operanden){
            register.ldx(operanden[0]);
        }
    };
    private  Opcode LDXZEROPAGEX =  new Opcode(Flags.AddressModes.ZEROPAGEX,"LDXZEROPAGEX", 0xB6,4){
        @Override
        public void operation(int[] operanden){
            register.ldx(operanden[0]);
        }
    };
    private  Opcode LDXABSOLUTE =  new Opcode(Flags.AddressModes.ABSOLUTE,"LDXABSOLUTE", 0xAE,4){
        @Override
        public void operation(int[] operanden){
            register.ldx(operanden[0]);
        }
    };
    private  Opcode LDXABSOLUTEX =  new Opcode(Flags.AddressModes.ABSOLUTEX,"LDXABSOLUTEX", 0xBE,4){
        @Override
        public void operation(int[] operanden){
            register.ldx(operanden[0]);
        }
    };



    //LDY
    private  Opcode LDYIMMEDIATE=  new Opcode(Flags.AddressModes.IMMEDIATE,"LDYIMMEDIATE", 0xA0,2){
        @Override
        public void operation(int[] operanden){
            register.ldy(operanden[0]);
        }
    };
    private  Opcode LDYZEROPAGE =  new Opcode(Flags.AddressModes.ZEROPAGE,"LDYZEROPAGE", 0xA4,3){
        @Override
        public void operation(int[] operanden){
            register.ldy(operanden[0]);
        }
    };
    private  Opcode LDYZEROPAGEX =  new Opcode(Flags.AddressModes.ZEROPAGEX,"LDYZEROPAGEX", 0xB4,4){
        @Override
        public void operation(int[] operanden){
            register.ldy(operanden[0]);
        }
    };
    private  Opcode LDYABSOLUTE =  new Opcode(Flags.AddressModes.ABSOLUTE,"LDYABSOLUTE", 0xAC,4){
        @Override
        public void operation(int[] operanden){
            register.ldy(operanden[0]);
        }
    };
    private  Opcode LDYABSOLUTEX =  new Opcode(Flags.AddressModes.ABSOLUTEX,"LDYABSOLUTEX", 0xBC,4){
        @Override
        public void operation(int[] operanden){
            register.ldy(operanden[0]);
        }
    };


    //STA
    private Opcode STAZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"STAZEROPAGE", 0x85,3){
        @Override
        public void operation(int[] operanden){
            register.sta(operanden[0]);
        }
    };
    private Opcode STAABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"STAABSOLUTE", 0x8D,4){
        @Override
        public void operation(int[] operanden){
            register.sta(operanden[0]);
        }
    };
    private Opcode STAABSOLUTEX = new Opcode(Flags.AddressModes.ABSOLUTEX,"STAABSOLUTEX", 0x9D,5){
        @Override
        public void operation(int[] operanden){
            register.sta(operanden[0]);
        }
    };
    private Opcode STAABSOLUTEY = new Opcode(Flags.AddressModes.ABSOLUTEY,"STAABSOLUTEY", 0x99,5){
        @Override
        public void operation(int[] operanden){
            register.sta(operanden[0]);
        }
    };
    private Opcode STAINDIRECTX= new Opcode(Flags.AddressModes.INDIRECTX,"STAINDIRECTX", 0x81,6){
        @Override
        public void operation(int[] operanden){
            register.sta(operanden[0]);
        }
    };
    private Opcode STAINDIRECTY= new Opcode(Flags.AddressModes.INDIRECTY,"STAINDIRECTY", 0x91,6){
        @Override
        public void operation(int[] operanden){
            register.sta(operanden[0]);
        }
    };
    private Opcode STAZEROPAGEX = new Opcode(Flags.AddressModes.ZEROPAGEX,"STAZEROPAGEX", 0x95,4){
        @Override
        public void operation(int[] operanden){
            register.sta(operanden[0]);
        }
    };


    //STX
    private Opcode STXZEROPAGE= new Opcode(Flags.AddressModes.ZEROPAGE,"STXZEROPAGE", 0x86,3){
        @Override
        public void operation(int[] operanden){
            register.sta(operanden[0]);
        }
    };
    private Opcode STXZEROPAGEY= new Opcode(Flags.AddressModes.ZEROPAGEY,"STXZEROPAGEY", 0x96,4){
        @Override
        public void operation(int[] operanden){
            register.sta(operanden[0]);
        }
    };
    private Opcode STXABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"STXABSOLUTE", 0x8E,4){
        @Override
        public void operation(int[] operanden){
            register.sta(operanden[0]);
        }
    };



    //STX
    private Opcode STYZEROPAGE= new Opcode(Flags.AddressModes.ZEROPAGE,"STYZEROPAGE", 0x84,3){
        @Override
        public void operation(int[] operanden){
            register.sty(operanden[0]);
        }
    };
    private Opcode STYZEROPAGEY= new Opcode(Flags.AddressModes.ZEROPAGEY,"STYZEROPAGEY", 0x94,4){
        @Override
        public void operation(int[] operanden){
            register.sty(operanden[0]);
        }
    };
    private Opcode STYABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"STYABSOLUTE", 0x8C,4){
        @Override
        public void operation(int[] operanden){
            register.sty(operanden[0]);
        }
    };



    //TAX
    private Opcode TAXIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"TAXIMPLICIT", 0xAA,2){
        @Override
        public void operation(int[] operanden){
            register.tax();
        }
    };

    //TAY
    private Opcode TAYIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"TAYIMPLICIT", 0xA8,2){
        @Override
        public void operation(int[] operanden){
            register.tay();
        }
    };

    //TSX
    private Opcode TSXIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"TSXIMPLICIT", 0xBA,2){
        @Override
        public void operation(int[] operanden){
            register.tsx();
        }
    };

    //TSA
    private Opcode TXAIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"TXAIMPLICIT", 0x8A,2){
        @Override
        public void operation(int[] operanden){
            register.tax();
        }
    };

    //TXS
    private Opcode TXSIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"TXSIMPLICIT", 0x9A,2){
        @Override
        public void operation(int[] operanden){

        }
    };

    //TYA
    private Opcode TYAIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"TYAIMPLICIT", 0x98,2){
        @Override
        public void operation(int[] operanden){
            register.tya();
        }
    };

    //ADC
    private Opcode ADCIMMEDIATE = new Opcode(Flags.AddressModes.IMMEDIATE,"ADCIMMEDIATE", 0x69,2){
        @Override
        public void operation(int[] operanden){
            register.adc(operanden[0]);
        }
    };
    private Opcode ADCZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"ADCZEROPAGE", 0x65,3){
        @Override
        public void operation(int[] operanden){
            register.adc(operanden[0]);
        }
    };
    private Opcode ADCZEROPAGEX = new Opcode(Flags.AddressModes.ZEROPAGEX,"ADCZEROPAGEX", 0x75,4){
        @Override
        public void operation(int[] operanden){
            register.adc(operanden[0]);
        }
    };
    private Opcode ADCABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"ADCABSOLUTE", 0x6D,4){
        @Override
        public void operation(int[] operanden){
            register.adc(operanden[0]);
        }
    };
    private Opcode ADCABSOLUTEX = new Opcode(Flags.AddressModes.ABSOLUTEX,"ADCABSOLUTEX", 0x7D,4){
        @Override
        public void operation(int[] operanden){
            register.adc(operanden[0]);
        }
    };
    private Opcode ADCABSOLUTEY = new Opcode(Flags.AddressModes.ABSOLUTEY,"ADCABSOLUTEY", 0x79,4){
        @Override
        public void operation(int[] operanden){
            register.adc(operanden[0]);
        }
    };
    private Opcode ADCINDIRECTX= new Opcode(Flags.AddressModes.INDIRECTX,"ADCINDIRECTX", 0x61,6){
        @Override
        public void operation(int[] operanden){
            register.adc(operanden[0]);
        }
    };
    private Opcode ADCINDIRECTY= new Opcode(Flags.AddressModes.INDIRECTY,"ADCINDIRECTY", 0x71,5){
        @Override
        public void operation(int[] operanden){
            register.adc(operanden[0]);
        }
    };

    //DEC

    private Opcode DECZEROPAGEX = new Opcode(Flags.AddressModes.ZEROPAGEX,"DECZEROPAGEX", 0xD6,6){
        @Override
        public void operation(int[] operanden){
            register.dec(operanden[0]);
        }
    };
    private Opcode DECZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"DECZEROPAGE", 0xC6,5){
        @Override
        public void operation(int[] operanden){
            register.dec(operanden[0]);
        }
    };
    private Opcode DECABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"DECABSOLUTE", 0xCE,6){
        @Override
        public void operation(int[] operanden){
            register.dec(operanden[0]);
        }
    };
    private Opcode DECABSOLUTEX = new Opcode(Flags.AddressModes.ABSOLUTEX,"DECABSOLUTEX", 0xDE,7){
        @Override
        public void operation(int[] operanden){
            register.dec(operanden[0]);
        }
    };



    //DEX

    private  Opcode DEXIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"DEXIMPLICIT", 0xCA,2){
        @Override
        public void operation(int[] operanden){
            register.dex();
        }
    };



    //DEY
    private  Opcode DEYIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"DEYIMPLICIT", 0x88,2){
        @Override
        public void operation(int[] operanden){
            register.dey();
        }
    };




    //INC
    private  Opcode INCZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"INCZEROPAGE", 0xE6,5){
        @Override
        public void operation(int[] operanden){
            register.inc(operanden[0]);
        }
    };
    private  Opcode INCZEROPAGEX = new Opcode(Flags.AddressModes.ZEROPAGEX,"INCZEROPAGEX", 0xF6,6){
        @Override
        public void operation(int[] operanden){
            register.inc(operanden[0]);
        }
    };
    private  Opcode INCABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"INCABSOLUTE", 0xEE,6){
        @Override
        public void operation(int[] operanden){
            register.inc(operanden[0]);
        }
    };
    private  Opcode INCABSOLUTEX = new Opcode(Flags.AddressModes.ABSOLUTEX,"INCABSOLUTEX", 0xFE,7){
        @Override
        public void operation(int[] operanden){
            register.inc(operanden[0]);
        }
    };

    //INX
    private  Opcode INXIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"INXIMPLICIT", 0xE8,2){
        @Override
        public void operation(int[] operanden){
            register.inx();
        }
    };



    //INY
    private  Opcode INYIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"INYIMPLICIT", 0xC8,2){
        @Override
        public void operation(int[] operanden){
            register.iny();
        }
    };



    //SBC
    private  Opcode SBCIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"SBCIMPLICIT", 0xE9,2){
        @Override
        public void operation(int[] operanden){
            register.sbc(operanden[0]);
        }
    };
    private  Opcode SBCZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"SBCZEROPAGE", 0xE5,3){
        @Override
        public void operation(int[] operanden){
            register.sbc(operanden[0]);
        }
    };
    private  Opcode SBCZEROPAGEX = new Opcode(Flags.AddressModes.ZEROPAGEX,"SBCZEROPAGEX", 0xF5,4){
        @Override
        public void operation(int[] operanden){
            register.sbc(operanden[0]);
        }
    };
    private  Opcode SBCABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"SBCABSOLUTE", 0xED,4){
        @Override
        public void operation(int[] operanden){
            register.sbc(operanden[0]);
        }
    };
    private  Opcode SBCABSOLUTEX = new Opcode(Flags.AddressModes.ABSOLUTEX,"SBCABSOLUTEX", 0xFD,4){
        @Override
        public void operation(int[] operanden){
            register.sbc(operanden[0]);
        }
    };
    private  Opcode SBCABSOLUTEY = new Opcode(Flags.AddressModes.ABSOLUTEY,"SBCABSOLUTEY", 0xF9,4){
        @Override
        public void operation(int[] operanden){
            register.sbc(operanden[0]);
        }
    };
    private  Opcode SBCINDIRECTX = new Opcode(Flags.AddressModes.INDIRECTX,"SBCINDIRECTX", 0xE1,6){
        @Override
        public void operation(int[] operanden){
            register.sbc(operanden[0]);
        }
    };
    private  Opcode SBCINDIRECTY = new Opcode(Flags.AddressModes.INDIRECTY,"SBCINDIRECTY", 0xF1,5){
        @Override
        public void operation(int[] operanden){
            register.sbc(operanden[0]);
        }
    };



    //AND
    private  Opcode ANDIMMEDIATE = new Opcode(Flags.AddressModes.IMMEDIATE,"ANDIMMEDIATE", 0x29,2){
        @Override
        public void operation(int[] operanden){
            register.and(operanden[0]);
        }
    };
    private  Opcode ANDZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"ANDZEROPAGE", 0x25,3){
        @Override
        public void operation(int[] operanden){
            register.and(operanden[0]);
        }
    };
    private  Opcode ANDZEROPAGEX = new Opcode(Flags.AddressModes.ZEROPAGEX,"ANDZEROPAGEX", 0x35,4){
        @Override
        public void operation(int[] operanden){
            register.and(operanden[0]);
        }
    };
    private  Opcode ANDABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"ANDABSOLUTE", 0x2D,4){
        @Override
        public void operation(int[] operanden){
            register.and(operanden[0]);
        }
    };
    private  Opcode ANDABSOLUTEX = new Opcode(Flags.AddressModes.ABSOLUTEX,"ANDABSOLUTEX", 0x3D,4){
        @Override
        public void operation(int[] operanden){
            register.and(operanden[0]);
        }
    };
    private  Opcode ANDABSOLUTEY = new Opcode(Flags.AddressModes.ABSOLUTEY,"ANDABSOLUTEY", 0x39,4){
        @Override
        public void operation(int[] operanden){
            register.and(operanden[0]);
        }
    };
    private  Opcode ANDINDIRECTX = new Opcode(Flags.AddressModes.INDIRECTX,"ANDINDIRECTX", 0x21,6){
        @Override
        public void operation(int[] operanden){
            register.and(operanden[0]);
        }
    };
    private  Opcode ANDINDIRECTY = new Opcode(Flags.AddressModes.INDIRECTY,"ANDINDIRECTY", 0x31,5){
        @Override
        public void operation(int[] operanden){
            register.and(operanden[0]);
        }
    };



    //ASL
    private  Opcode ASLABSOLUTEX= new Opcode(Flags.AddressModes.ABSOLUTEX,"ASLABSOLUTEX", 0x1E,7){
        @Override
        public void operation(int[] operanden){
            register.asl(operanden[0]);
        }
    };

    private  Opcode ASLZEROPAGEX= new Opcode(Flags.AddressModes.ZEROPAGEX,"ASLZEROPAGEX", 0x16,6){
        @Override
        public void operation(int[] operanden){
            register.asl(operanden[0]);
        }
    };
    private  Opcode ASLZEROPAGE= new Opcode(Flags.AddressModes.ZEROPAGE,"ASLZEROPAGE", 0x06,5){
        @Override
        public void operation(int[] operanden){
            register.asl(operanden[0]);
        }
    };
    private  Opcode ASLABSOLUTE= new Opcode(Flags.AddressModes.ABSOLUTE,"ASLABSOLUTE", 0x0E,6){
        @Override
        public void operation(int[] operanden){
            register.asl(operanden[0]);
        }
    };
    private  Opcode ASLACCUMULATOR = new Opcode(Flags.AddressModes.ACCUMULATOR,"ASLACCUMULATOR", 0x0A,2){
        @Override
        public void operation(int[] operanden){
            register.aslAccumulator();
        }
    };




    //BIT
    private  Opcode BITZEROPAGE= new Opcode(Flags.AddressModes.ZEROPAGE,"BITZEROPAGE", 0x24,3){
        @Override
        public void operation(int[] operanden){
            register.bit(operanden[0]);
        }
    };
    private  Opcode BITABSOLUTE= new Opcode(Flags.AddressModes.ABSOLUTE,"BITABSOLUTE", 0x2C,4){
        @Override
        public void operation(int[] operanden){
            register.bit(operanden[0]);
        }
    };



    //EOR
    private  Opcode EORIMMEDIATE = new Opcode(Flags.AddressModes.IMMEDIATE,"EORIMMEDIATE", 0x49,2){
        @Override
        public void operation(int[] operanden){
            register.eor(operanden[0]);
        }
    };
    private  Opcode EORABSOLUTE= new Opcode(Flags.AddressModes.ABSOLUTE,"EORABSOLUTE", 0x4D,4){
        @Override
        public void operation(int[] operanden){
            register.eor(operanden[0]);
        }
    };
    private  Opcode EORABSOLUTEX= new Opcode(Flags.AddressModes.ABSOLUTEX,"EORABSOLUTEX", 0x5D,4){
        @Override
        public void operation(int[] operanden){
            register.eor(operanden[0]);
        }
    };
    private  Opcode EORABSOLUTEY= new Opcode(Flags.AddressModes.ABSOLUTE,"EORABSOLUTEY", 0x59,4){
        @Override
        public void operation(int[] operanden){
            register.eor(operanden[0]);
        }
    };
    private  Opcode EORZEROPAGEX= new Opcode(Flags.AddressModes.ZEROPAGEX,"EORZEROPAGEX", 0x55,4){
        @Override
        public void operation(int[] operanden){
            register.eor(operanden[0]);
        }
    };
    private  Opcode EORZEROPAGE= new Opcode(Flags.AddressModes.ZEROPAGE,"EORZEROPAGE", 0x45,3){
        @Override
        public void operation(int[] operanden){
            register.eor(operanden[0]);
        }
    };
    private  Opcode EORINDIRECTX= new Opcode(Flags.AddressModes.INDIRECTX,"EORINDIRECTX", 0x41,6){
        @Override
        public void operation(int[] operanden){
            register.eor(operanden[0]);
        }
    };
    private  Opcode EORINDIRECTY= new Opcode(Flags.AddressModes.INDIRECTY,"EORINDIRECTY", 0x51,5){
        @Override
        public void operation(int[] operanden){
            register.eor(operanden[0]);
        }
    };


    //LSR
    private  Opcode LSRACCUMULATOR = new Opcode(Flags.AddressModes.ACCUMULATOR,"LSRACCUMULATOR", 0x4A,2){
        @Override
        public void operation(int[] operanden){
            register.lsrAccumulator();
        }
    };
    private  Opcode LSRZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"LSRZEROPAGE", 0x46,5){
        @Override
        public void operation(int[] operanden){
            register.lsr(operanden[0]);
        }
    };
    private  Opcode LSRZEROPAGEX = new Opcode(Flags.AddressModes.ZEROPAGEX,"LSRZEROPAGEX", 0x56,6){
        @Override
        public void operation(int[] operanden){
            register.lsr(operanden[0]);
        }
    };
    private  Opcode LSRABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"LSRABSOLUTE", 0x4E,6){
        @Override
        public void operation(int[] operanden){
            register.lsr(operanden[0]);
        }
    };
    private  Opcode LSRABSOLUTEX = new Opcode(Flags.AddressModes.ABSOLUTEX,"LSRABSOLUTEX", 0x5E,7){
        @Override
        public void operation(int[] operanden){
            register.lsr(operanden[0]);
        }
    };



    //ORA
    private  Opcode ORAABSOLUTEY = new Opcode(Flags.AddressModes.ABSOLUTEY,"ORAABSOLUTEY", 0x19,4){
        @Override
        public void operation(int[] operanden){
            register.ora(operanden[0]);
        }
    };
    private  Opcode ORAABSOLUTEX = new Opcode(Flags.AddressModes.ABSOLUTEX,"ORAABSOLUTEX", 0x1D,4){
        @Override
        public void operation(int[] operanden){
            register.ora(operanden[0]);
        }
    };
    private  Opcode ORAINDIRECTY = new Opcode(Flags.AddressModes.INDIRECTY,"ORAINDIRECTY", 0x11,5){
        @Override
        public void operation(int[] operanden){
            register.ora(operanden[0]);
        }
    };

    private  Opcode ORAZEROPAGEX = new Opcode(Flags.AddressModes.ZEROPAGEX,"ORAZEROPAGEX", 0x15,4){
        @Override
        public void operation(int[] operanden){
            register.ora(operanden[0]);
        }
    };
    private  Opcode ORAINDIRECTX = new Opcode(Flags.AddressModes.INDIRECTX,"ORAINDIRECTX", 0x01,6){
        @Override
        public void operation(int[] operanden){
            register.ora(operanden[0]);
        }
    };
    private  Opcode ORAZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"ORAZEROPAGE", 0x05,3){
        @Override
        public void operation(int[] operanden){
            register.ora(operanden[0]);
        }
    };
    private  Opcode ORAABSOLUTE= new Opcode(Flags.AddressModes.ABSOLUTE,"ORAABSOLUTE", 0x0D,4){
        @Override
        public void operation(int[] operanden){
            register.ora(operanden[0]);
        }
    };
    private  Opcode ORAIMMEDIATE= new Opcode(Flags.AddressModes.IMMEDIATE,"ORAIMMEDIATE", 0x09,2){
        @Override
        public void operation(int[] operanden){
            register.ora(operanden[0]);
        }
    };



    //ROL
    private  Opcode ROLZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"ROLZEROPAGE", 0x26,5){
        @Override
        public void operation(int[] operanden){
            register.rol(operanden[0]);
        }
    };
    private  Opcode ROLZEROPAGEX = new Opcode(Flags.AddressModes.ZEROPAGEX,"ROLZEROPAGEX", 0x36,6){
        @Override
        public void operation(int[] operanden){
            register.rol(operanden[0]);
        }
    };
    private  Opcode ROLABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"ROLABSOLUTE", 0x2E,6){
        @Override
        public void operation(int[] operanden){
            register.rol(operanden[0]);
        }
    };
    private  Opcode ROLABSOLUTEX = new Opcode(Flags.AddressModes.ABSOLUTEX,"ROLABSOLUTEX", 0x3E,7){
        @Override
        public void operation(int[] operanden){
            register.rol(operanden[0]);
        }
    };
    private  Opcode ROLACCUMULATOR = new Opcode(Flags.AddressModes.ACCUMULATOR,"ROLACCUMULATOR", 0x2A,2){
        @Override
        public void operation(int[] operanden){
            register.rolAccumulator();
        }
    };


    //ROR
    private Opcode RORZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"RORZEROPAGE", 0x66,5){
        @Override
        public void operation(int[] operanden){
            register.ror(operanden[0]);
        }
    };
    private Opcode RORACCUMULATOR = new Opcode(Flags.AddressModes.ACCUMULATOR,"RORACCUMULATOR", 0x6A,2){
        @Override
        public void operation(int[] operanden){
            register.rorAccumulator();
        }
    };
    private Opcode RORZEROPAGEX = new Opcode(Flags.AddressModes.ZEROPAGEX,"RORZEROPAGEX", 0x76,6){
        @Override
        public void operation(int[] operanden){
            register.ror(operanden[0]);
        }
    };
    private Opcode RORABSOLUTEX = new Opcode(Flags.AddressModes.ABSOLUTEX,"RORABSOLUTEX", 0x7E,7){
        @Override
        public void operation(int[] operanden){
            register.ror(operanden[0]);
        }
    };
    private Opcode RORABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"RORABSOLUTE", 0x6E,6){
        @Override
        public void operation(int[] operanden){
            register.ror(operanden[0]);
        }
    };


    //CLC
    private  Opcode CLCIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"CLCIMPLICIT", 0x18,2){
        @Override
        public void operation(int[] operanden){
            register.clc();
        }
    };

    //CLD
    private  Opcode CLDIMPLICIT =  new Opcode(Flags.AddressModes.IMPLICIT,"CLDIMPLICIT", 0xD8,2){
        @Override
        public void operation(int[] operanden){
            register.cld();
        }
    };

    //CLI
    private  Opcode CLIIMPLICIT =  new Opcode(Flags.AddressModes.IMPLICIT,"CLIIMPLICIT", 0x58,2){
        @Override
        public void operation(int[] operanden){
            register.cli();
        }
    };

    //CLV
    private  Opcode CLVIMPLICIT =  new Opcode(Flags.AddressModes.IMPLICIT,"CLVIMPLICIT", 0xB8,2){
        @Override
        public void operation(int[] operanden){
            register.clv();
        }
    };


    //CMP

    private  Opcode CMPABSOLUTEY = new Opcode(Flags.AddressModes.ABSOLUTEY,"CMPABSOLUTEY", 0xD9,4){
        @Override
        public void operation(int[] operanden){
            register.cmp(operanden[0]);
        }
    };
    private  Opcode CMPINDIRECTY = new Opcode(Flags.AddressModes.INDIRECTY,"CMPINDIRECTY", 0xD1,5){
        @Override
        public void operation(int[] operanden){
            register.cmp(operanden[0]);
        }
    };
    private  Opcode CMPABSOLUTEX = new Opcode(Flags.AddressModes.ABSOLUTEX,"CMPABSOLUTEX", 0xDD,4){
        @Override
        public void operation(int[] operanden){
            register.cmp(operanden[0]);
        }
    };
    private  Opcode CMPABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"CMPABSOLUTE", 0xCD,4){
        @Override
        public void operation(int[] operanden){
            register.cmp(operanden[0]);
        }
    };
    private  Opcode CMPINDIRECTX = new Opcode(Flags.AddressModes.INDIRECTX,"CMPINDIRECTX", 0xC1,6){
        @Override
        public void operation(int[] operanden){
            register.cmp(operanden[0]);
        }
    };

    private  Opcode CMPIMMEDIATE = new Opcode(Flags.AddressModes.IMMEDIATE,"CMPIMMEDIATE", 0xC9,2){
        @Override
        public void operation(int[] operanden){
            register.cmp(operanden[0]);
        }
    };
    private  Opcode CMPZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"CMPZEROPAGE", 0xC5,3){
        @Override
        public void operation(int[] operanden){
            register.cmp(operanden[0]);
        }
    };
    private  Opcode CMPZEROPAGEX = new Opcode(Flags.AddressModes.ZEROPAGEX,"CMPZEROPAGEX", 0xD5,4){
        @Override
        public void operation(int[] operanden){
            register.cmp(operanden[0]);
        }
    };


    //CPX
    private  Opcode CPXIMMEDIATE = new Opcode(Flags.AddressModes.IMMEDIATE,"CPXIMMEDIATE", 0xE0,2){
        @Override
        public void operation(int[] operanden){
            register.cpx(operanden[0]);
        }
    };
    private  Opcode CPXZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"CPXZEROPAGE", 0xE4,3){
        @Override
        public void operation(int[] operanden){
            register.cpx(operanden[0]);
        }
    };
    private  Opcode CPXABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"CPXABSOLUTE", 0xEC,4){
        @Override
        public void operation(int[] operanden){
            register.cpx(operanden[0]);
        }
    };


    //CPY
    private  Opcode CPYIMMEDIATE = new Opcode(Flags.AddressModes.IMMEDIATE,"CPYIMMEDIATE", 0xC0,2){
        @Override
        public void operation(int[] operanden){
            register.cpy(operanden[0]);
        }
    };
    private  Opcode CPYZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"CPYZEROPAGE", 0xC4,3){
        @Override
        public void operation(int[] operanden){
            register.cpy(operanden[0]);
        }
    };
    private  Opcode CPYABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"CPYABSOLUTE", 0xCC,4){
        @Override
        public void operation(int[] operanden){
            register.cpy(operanden[0]);
        }
    };

    //SEC
    private  Opcode SECIMPLICIT =  new Opcode(Flags.AddressModes.IMPLICIT,"SECIMPLICIT", 0x38,	2){
        @Override
        public void operation(int[] operanden){
            register.sec();
        }
    };

    //SED
    private  Opcode SEDIMPLICIT =  new Opcode(Flags.AddressModes.IMPLICIT,"SEDIMPLICIT ", 0xF8,	2){
        @Override
        public void operation(int[] operanden){
            register.sed();
        }
    };

    //SEI
    private  Opcode SEIIMPLICIT =  new Opcode(Flags.AddressModes.IMPLICIT,"SEIIMPLICIT", 0x78,	2){
        @Override
        public void operation(int[] operanden){
            register.sei();
        }
    };



    //BCC
    private  Opcode BCCRELATIVE =  new Opcode(Flags.AddressModes.RELATIVE,"BCCRELATIVE", 0x90,	2){
        @Override
        public void operation(int[] operanden){
            register.bcc(operanden[0]);
        }
    };

    //BCS
    private  Opcode BCSRELATIVE =  new Opcode(Flags.AddressModes.RELATIVE,"BCSRELATIVE", 0xB0,	2){
        @Override
        public void operation(int[] operanden){
            register.bcs(operanden[0]);
        }
    };

    //BEQ
    private Opcode BEQRELATIVE = new Opcode(Flags.AddressModes.RELATIVE,"BEQRELATIVE", 0xF0,2){
        @Override
        public void operation(int[] operanden){
            register.beq(operanden[0]);
        }
    };
    //BMI
    private Opcode BMIRELATIVE = new Opcode(Flags.AddressModes.RELATIVE,"BMIRELATIVE", 0x30,2){
        @Override
        public void operation(int[] operanden){
            register.bmi(operanden[0]);
        }
    };
    //BNE
    private Opcode BNERELATIVE = new Opcode(Flags.AddressModes.RELATIVE,"BNERELATIVE", 0xD0,2){
        @Override
        public void operation(int[] operanden){
            register.bne(operanden[0]);
        }
    };
    //BPL
    private  Opcode BPLRELATIVE = new Opcode(Flags.AddressModes.RELATIVE,"BPLRELATIVE", 0x10,2){
        @Override
        public void operation(int[] operanden){
            register.bpl(operanden[0]);
        }
    };
    //BVC
    private  Opcode BVCRELATIVE = new Opcode(Flags.AddressModes.RELATIVE,"BVCRELATIVE", 0x50,2){
        @Override
        public void operation(int[] operanden){
            register.bvc(operanden[0]);
        }
    };
    //BVS
    private  Opcode BVSRELATIVE = new Opcode(Flags.AddressModes.RELATIVE,"BVSRELATIVE", 0x70,2){
        @Override
        public void operation(int[] operanden){
            register.bvs(operanden[0]);
        }
    };
    //JMP
    private  Opcode JMPABSOLUTE =  new Opcode(Flags.AddressModes.ABSOLUTE,"JMPABSOLUTE", 0x4C,	3){
        @Override
        public void operation(int[] operanden){
            register.jmp(operanden[0]);
        }
    };

    private  Opcode JMPINDIRECT =  new Opcode(Flags.AddressModes.INDIRECT,"JMPINDIRECT", 0x6C,	5){
        @Override
        public void operation(int[] operanden){
            register.jmp(operanden[0]);
        }
    };

    //JSR
    private Opcode JSRABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"JSRABSOLUTE", 0x20,6){
        @Override
        public void operation(int[] operanden){
            register.jsr(operanden[0]);
        }
    };
    //RTI
    private  Opcode RTIIMPLICIT =  new Opcode(Flags.AddressModes.IMPLICIT,"RTIIMPLICIT", 0x40,	6){
        @Override
        public void operation(int[] operanden){
            register.rti();
        }
    };
    //RTS
    private  Opcode RTSIMPLICIT =  new Opcode(Flags.AddressModes.IMPLICIT,"RTSIMPLICIT", 0x60,	6){
        @Override
        public void operation(int[] operanden){
            register.rts();
        }
    };

    //PHA
    private  Opcode PHAIMPLICIT =  new Opcode(Flags.AddressModes.IMPLICIT,"PHAIMPLICIT", 0x48,3){
        @Override
        public void operation(int[] operanden){
            register.pha();
        }
    };

    //PHP
    private  Opcode PHPIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"PHPIMPLICIT", 0x08,3){
        @Override
        public void operation(int[] operanden){
            register.php();
        }
    };
    //PLA
    private Opcode PLAIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"PLAIMPLICIT", 0x68,4){
        @Override
        public void operation(int[] operanden){
            register.pla();
        }
    };
    //PLP
    private  Opcode PLPIMPLICIT =  new Opcode(Flags.AddressModes.IMPLICIT,"PLPIMPLICIT", 0x78,	2){
        @Override
        public void operation(int[] operanden){
            register.plp();
        }
    };

    //BRK
    private  Opcode BRKIMPLICIT =  new Opcode(Flags.AddressModes.IMPLICIT,"BRKIMPLICIT", 0x00,7){
        @Override
        public void operation(int[] operanden){
            register.brk();
        }
    };
    //NOP
    private  Opcode NOPIMPLICIT = new Opcode(Flags.AddressModes.IMPLICIT,"NOPIMPLICIT", 0xEA,2){
        @Override
        public void operation(int[] operanden){
            register.nop();
        }
    };



//ä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#ö ====  UNOFFICIAL OPCODES SECTION  ====  ä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#öä#ü#ö



    //@TODO die KILs wurden mit cycle 1 versehen, da wir sonst ins negative gehen. Laut dokumentation aber benötigt man kein cycle ?
    //halt the cpu, reset needed to continue..
    private Opcode KIL1 = new Opcode(Flags.AddressModes.HLT,"KIL1", 0x02,1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL2 = new Opcode(Flags.AddressModes.HLT,"KIL2", 0x12,1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL3= new Opcode(Flags.AddressModes.HLT,"KIL3", 0x22,1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL4 = new Opcode(Flags.AddressModes.HLT,"KIL4", 0x32,1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL5 = new Opcode(Flags.AddressModes.HLT,"KIL5", 0x42,1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL6 = new Opcode(Flags.AddressModes.HLT,"KIL6", 0x52,1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL7= new Opcode(Flags.AddressModes.HLT,"KIL7", 0x62,1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL8 = new Opcode(Flags.AddressModes.HLT,"KIL8", 0x72,1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL9 = new Opcode(Flags.AddressModes.HLT,"KIL9", 0x82,1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL10 = new Opcode(Flags.AddressModes.HLT,"KIL10", 0x92,1){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode KIL11 = new Opcode(Flags.AddressModes.HLT,"KIL11", 0xB2,1){
        @Override
        public void operation(int[] operanden){

        }
    };private Opcode KIL12 = new Opcode(Flags.AddressModes.HLT,"KIL12", 0xD2,1){
        @Override
        public void operation(int[] operanden){

        }
    };



    private Opcode SLOABSOLUTEY = new Opcode(Flags.AddressModes.ABSOLUTEY,"SLOABSOLUTEY", 0x1B,7){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode SLOZEROPAGEX = new Opcode(Flags.AddressModes.ZEROPAGEX,"SLOZEROPAGEX", 0x17,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode SLOABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"SLOABSOLUTE", 0x0F,6){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode SLOABSOLUTEX = new Opcode(Flags.AddressModes.ABSOLUTEX,"SLOABSOLUTEX", 0x1F,7){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode SLOZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"SLOZEROPAGE", 0x07,5){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode SLOINDIRECTX = new Opcode(Flags.AddressModes.INDIRECTX,"SLOINDIRECTX", 0x03,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private Opcode SLOINDIRECTY = new Opcode(Flags.AddressModes.INDIRECTY,"SLOINDIRECTY", 0x13,8){
        @Override
        public void operation(int[] operanden){

        }
    };




    //http://www.oxyron.de/html/opcodes02.html  Suceh nach ANCIMMEDIATE.
    private  Opcode ANCIMMEDIATE = new Opcode(Flags.AddressModes.IMMEDIATE,"ANCIMMEDIATE", 0x0B,2){
        @Override
        public void operation(int[] operanden){

        }
    };

    //opcodes[0x04] = NOPZEROPAGE;   opcodes[0x0C] = NOPABSOLUTE; opcodes[0x14] = NOPZEROPAGEX;  opcodes[0x1C] = NOPABSOLUTEX;

    private  Opcode NOPABSOLUTE = new Opcode(Flags.AddressModes.ABSOLUTE,"NOPABSOLUTE", 0x0C,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode NOPABSOLUTEX = new Opcode(Flags.AddressModes.ABSOLUTEX,"NOPABSOLUTEX", 0x1C,4){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode NOPZEROPAGE = new Opcode(Flags.AddressModes.ZEROPAGE,"NOPZEROPAGE", 0x04,3){
        @Override
        public void operation(int[] operanden){

        }
    };
    private  Opcode NOPZEROPAGEX = new Opcode(Flags.AddressModes.ZEROPAGEX,"NOPZEROPAGEX", 0x14,4){
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
        opcodes[0x23] = NOPIMPLICIT;//RLAINDIRECTX; //8
        opcodes[0x24] = BITZEROPAGE; //3
        opcodes[0x25] = ANDZEROPAGE; //3
        opcodes[0x26] = ROLZEROPAGE;
        opcodes[0x27] = NOPIMPLICIT;// RLAZEROPAGE; //5
        opcodes[0x28] = PLPIMPLICIT; //4
        opcodes[0x29] = ANDIMMEDIATE; //2
        opcodes[0x2A] = ROLACCUMULATOR; //2
        opcodes[0x2B] = ANDIMMEDIATE; //2
        opcodes[0x2C] = BITABSOLUTE; //4
        opcodes[0x2D] = ANDABSOLUTE; //4
        opcodes[0x2E] = ROLABSOLUTE;
        opcodes[0x2F] = NOPIMPLICIT;//RLAABSOLUTE; //6

        opcodes[0x30] = BMIRELATIVE;
        opcodes[0x31] = ANDINDIRECTY;
        opcodes[0x32] = KIL4;
        opcodes[0x33] = NOPIMPLICIT;
        opcodes[0x34] = NOPIMPLICIT;
        opcodes[0x35] = ANDZEROPAGEX;
        opcodes[0x36] = ROLZEROPAGEX;
        opcodes[0x37] = NOPIMPLICIT;
        opcodes[0x38] = SECIMPLICIT;
        opcodes[0x39] = ANDABSOLUTEY;
        opcodes[0x3A] = NOPIMPLICIT;
        opcodes[0x3B] = NOPIMPLICIT;
        opcodes[0x3C] = NOPIMPLICIT;
        opcodes[0x3D] = ANDABSOLUTEX;
        opcodes[0x3E] = ROLABSOLUTEX;
        opcodes[0x3F] = NOPIMPLICIT;

        opcodes[0x40] = RTIIMPLICIT;
        opcodes[0x41] = EORINDIRECTX;
        opcodes[0x42] = KIL5;
        opcodes[0x43] = NOPIMPLICIT;
        opcodes[0x44] = NOPIMPLICIT;
        opcodes[0x45] = EORZEROPAGE;
        opcodes[0x46] = LSRZEROPAGE;
        opcodes[0x47] = NOPIMPLICIT;
        opcodes[0x48] = PHAIMPLICIT;
        opcodes[0x49] = EORIMMEDIATE;
        opcodes[0x4A] = LSRACCUMULATOR;
        opcodes[0x4B] = NOPIMPLICIT;
        opcodes[0x4C] = JMPABSOLUTE;
        opcodes[0x4D] = EORABSOLUTE;
        opcodes[0x4E] = LSRABSOLUTE;
        opcodes[0x4F] = NOPIMPLICIT;

        opcodes[0x50] = BVCRELATIVE;
        opcodes[0x51] = EORINDIRECTY;
        opcodes[0x52] = KIL6;
        opcodes[0x53] = NOPIMPLICIT;
        opcodes[0x54] = NOPIMPLICIT;
        opcodes[0x55] = EORZEROPAGEX;
        opcodes[0x56] = LSRZEROPAGEX;
        opcodes[0x57] = NOPIMPLICIT;
        opcodes[0x58] = CLIIMPLICIT;
        opcodes[0x59] = EORABSOLUTEY;
        opcodes[0x5A] = NOPIMPLICIT;
        opcodes[0x5B] = NOPIMPLICIT;
        opcodes[0x5C] = NOPIMPLICIT;
        opcodes[0x5D] = EORABSOLUTEX;
        opcodes[0x5E] = LSRABSOLUTEX;
        opcodes[0x5F] = NOPIMPLICIT;

        opcodes[0x60] = RTSIMPLICIT;
        opcodes[0x61] = ADCINDIRECTX;
        opcodes[0x62] = KIL7;
        opcodes[0x63] = NOPIMPLICIT;
        opcodes[0x64] = NOPIMPLICIT;
        opcodes[0x65] = ADCZEROPAGE;
        opcodes[0x66] = RORZEROPAGE;
        opcodes[0x67] = NOPIMPLICIT;
        opcodes[0x68] = PLAIMPLICIT;
        opcodes[0x69] = ADCIMMEDIATE;
        opcodes[0x6A] = RORACCUMULATOR;
        opcodes[0x6B] = NOPIMPLICIT;
        opcodes[0x6C] = JMPINDIRECT;
        opcodes[0x6D] = ADCABSOLUTE;
        opcodes[0x6E] = RORABSOLUTE;
        opcodes[0x6F] = NOPIMPLICIT;

        opcodes[0x70] = BVSRELATIVE;
        opcodes[0x71] = ADCINDIRECTY;
        opcodes[0x72] = KIL8;
        opcodes[0x73] = NOPIMPLICIT;
        opcodes[0x74] = NOPIMPLICIT;
        opcodes[0x75] = ADCZEROPAGEX;
        opcodes[0x76] = RORZEROPAGEX;
        opcodes[0x77] = NOPIMPLICIT;
        opcodes[0x78] = SEIIMPLICIT;
        opcodes[0x79] = ADCABSOLUTEY;
        opcodes[0x7A] = NOPIMPLICIT;
        opcodes[0x7B] = NOPIMPLICIT;
        opcodes[0x7C] = NOPIMPLICIT;
        opcodes[0x7D] = ADCABSOLUTEX;
        opcodes[0x7E] = RORABSOLUTEX;
        opcodes[0x7F] = NOPIMPLICIT;

        opcodes[0x80] = NOPIMPLICIT;
        opcodes[0x81] = STAINDIRECTX;
        opcodes[0x82] = NOPIMPLICIT;
        opcodes[0x83] = NOPIMPLICIT;
        opcodes[0x84] = STYZEROPAGE;
        opcodes[0x85] = STAZEROPAGE;
        opcodes[0x86] = STXZEROPAGE;
        opcodes[0x87] = NOPIMPLICIT;
        opcodes[0x88] = DEYIMPLICIT;
        opcodes[0x89] = NOPIMPLICIT;
        opcodes[0x8A] = TXAIMPLICIT;
        opcodes[0x8B] = NOPIMPLICIT;
        opcodes[0x8C] = STYABSOLUTE;
        opcodes[0x8D] = STAABSOLUTE;
        opcodes[0x8E] = STXABSOLUTE;
        opcodes[0x8F] = NOPIMPLICIT;

        opcodes[0x90] = BCCRELATIVE;
        opcodes[0x91] = STAINDIRECTY;
        opcodes[0x92] = KIL9;
        opcodes[0x93] = NOPIMPLICIT;
        opcodes[0x94] = STYZEROPAGEY;
        opcodes[0x95] = STAZEROPAGEX;
        opcodes[0x96] = STXZEROPAGEY;
        opcodes[0x97] = NOPIMPLICIT;
        opcodes[0x98] = TYAIMPLICIT;
        opcodes[0x99] = STAABSOLUTEY;
        opcodes[0x9A] = TXSIMPLICIT;
        opcodes[0x9B] = NOPIMPLICIT;
        opcodes[0x9C] = NOPIMPLICIT;
        opcodes[0x9D] = STAABSOLUTEX;
        opcodes[0x9E] = NOPIMPLICIT;
        opcodes[0x9F] = NOPIMPLICIT;

        opcodes[0xA0] = LDYIMMEDIATE;
        opcodes[0xA1] = LDAINDIRECTX;
        opcodes[0xA2] = LDXIMMEDIATE;
        opcodes[0xA3] = NOPIMPLICIT;
        opcodes[0xA4] = LDYZEROPAGE;
        opcodes[0xA5] = LDAZEROPAGE;
        opcodes[0xA6] = LDXZEROPAGE;
        opcodes[0xA7] = NOPIMPLICIT;
        opcodes[0xA8] = TAYIMPLICIT;
        opcodes[0xA9] = LDAIMMEDIATE;
        opcodes[0xAA] = TAXIMPLICIT;
        opcodes[0xAB] = NOPIMPLICIT;
        opcodes[0xAC] = LDYABSOLUTE;
        opcodes[0xAD] = LDAABSOLUTE;
        opcodes[0xAE] = LDXABSOLUTE;
        opcodes[0xAF] = NOPIMPLICIT;

        opcodes[0xB0] = BCSRELATIVE;
        opcodes[0xB1] = LDAINDIRECTY;
        opcodes[0xB2] = KIL10;
        opcodes[0xB3] = NOPIMPLICIT;
        opcodes[0xB4] = LDYZEROPAGEX;
        opcodes[0xB5] = LDAZEROPAGEX;
        opcodes[0xB6] = LDXZEROPAGEX;
        opcodes[0xB7] = NOPIMPLICIT;
        opcodes[0xB8] = CLVIMPLICIT;
        opcodes[0xB9] = LDAABSOLUTEY;
        opcodes[0xBA] = TSXIMPLICIT;
        opcodes[0xBB] = NOPIMPLICIT;
        opcodes[0xBC] = LDYABSOLUTEX;
        opcodes[0xBD] = LDAABSOLUTEX;
        opcodes[0xBE] = LDXABSOLUTEX;
        opcodes[0xBF] = NOPIMPLICIT;

        opcodes[0xC0] = CPYIMMEDIATE;
        opcodes[0xC1] = CMPINDIRECTX;
        opcodes[0xC2] = NOPIMPLICIT;
        opcodes[0xC3] = NOPIMPLICIT;
        opcodes[0xC4] = CPYZEROPAGE;
        opcodes[0xC5] = CMPZEROPAGE;
        opcodes[0xC6] = DECZEROPAGE;
        opcodes[0xC7] = NOPIMPLICIT;
        opcodes[0xC8] = INYIMPLICIT;
        opcodes[0xC9] = CMPIMMEDIATE;
        opcodes[0xCA] = DEXIMPLICIT;
        opcodes[0xCB] = NOPIMPLICIT;
        opcodes[0xCC] = CPYABSOLUTE;
        opcodes[0xCD] = CMPABSOLUTE;
        opcodes[0xCE] = DECABSOLUTE;
        opcodes[0xCF] = NOPIMPLICIT;

        opcodes[0xD0] = BNERELATIVE;
        opcodes[0xD1] = CMPINDIRECTY;
        opcodes[0xD2] = KIL11;
        opcodes[0xD3] = NOPIMPLICIT;
        opcodes[0xD4] = NOPIMPLICIT;
        opcodes[0xD5] = CMPZEROPAGEX;
        opcodes[0xD6] = DECZEROPAGEX;
        opcodes[0xD7] = NOPIMPLICIT;
        opcodes[0xD8] = CLDIMPLICIT;
        opcodes[0xD9] = CMPABSOLUTEY;
        opcodes[0xDA] = NOPIMPLICIT;
        opcodes[0xDB] = NOPIMPLICIT;
        opcodes[0xDC] = NOPIMPLICIT;
        opcodes[0xDD] = CMPABSOLUTEX;
        opcodes[0xDE] = DECABSOLUTEX;
        opcodes[0xDF] = NOPIMPLICIT;

        opcodes[0xE0] = CPXIMMEDIATE;
        opcodes[0xE1] = SBCINDIRECTX;
        opcodes[0xE2] = NOPIMPLICIT;
        opcodes[0xE3] = NOPIMPLICIT;
        opcodes[0xE4] = CPXZEROPAGE;
        opcodes[0xE5] = SBCZEROPAGE;
        opcodes[0xE6] = INCZEROPAGE;
        opcodes[0xE7] = NOPIMPLICIT;
        opcodes[0xE8] = INXIMPLICIT;
        opcodes[0xE9] = SBCIMPLICIT;
        opcodes[0xEA] = NOPIMPLICIT;
        opcodes[0xEB] = NOPIMPLICIT;
        opcodes[0xEC] = CPXABSOLUTE;
        opcodes[0xED] = SBCABSOLUTE;
        opcodes[0xEE] = INCABSOLUTE;
        opcodes[0xEF] = NOPIMPLICIT;

        opcodes[0xF0] = BEQRELATIVE;
        opcodes[0xF1] = SBCINDIRECTY;
        opcodes[0xF2] = KIL12;
        opcodes[0xF3] = NOPIMPLICIT;
        opcodes[0xF4] = NOPIMPLICIT;
        opcodes[0xF5] = SBCZEROPAGEX;
        opcodes[0xF6] = INCZEROPAGEX;
        opcodes[0xF7] = NOPIMPLICIT;
        opcodes[0xF8] = SEDIMPLICIT;
        opcodes[0xF9] = SBCABSOLUTEY;
        opcodes[0xFA] = NOPIMPLICIT;
        opcodes[0xFB] = NOPIMPLICIT;
        opcodes[0xFC] = NOPIMPLICIT;
        opcodes[0xFD] = SBCABSOLUTEX;
        opcodes[0xFE] = INCABSOLUTEX;
        opcodes[0xFF] = NOPIMPLICIT;

    }
}

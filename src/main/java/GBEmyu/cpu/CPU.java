package GBEmyu.cpu;

import GBEmyu.RAM;

public class CPU {

    private Register register;
    private Opcode opcodes;
    private RAM ram;
    private int clockspeed;
    public CPU(RAM ram) {
        this.ram = ram;
        register = new Register();
        opcodes = new Opcode();
        clockspeed = 0;
    }

    //initial Methode
    public void init() {
        for (int i = 0; i < 0x800; ++i) {
            ram.write(i, 0xFF);
        }
    }
    public void loop(){
        while(true){
            //1.Befehl laden
            //2.Befehl dekodieren
            //3.Befehl ausführen
            //4.Ergebnis abspeichern.
            break;
        }

    }
    public void run(int[] inst) {
        for (int i = 0; i < inst.length; i++) {
            System.out.println("i: "+i+" instr: "+inst[i]);
            switch(inst[i]) {

                case 0x78: // SEI imm
                    register.setSEI();
                    System.out.println("SEI at " + (String.format("0x%04X", i)));
                    break;
                case 0xD8:
                    register.setCLD();
                    System.out.println("CLD at " + (String.format("0x%04X", i)));
                    break;
                case 0xA9:
                    register.setLDA();
                    System.out.println("LDA at " + (String.format("0x%04X", i)));
                    break;
                case 0xCA: // dec on x and
                    System.out.println("DEX at " + (String.format("0x%04X", i)));
                    //register.dex();
                    break;
            }


        }

    }


}

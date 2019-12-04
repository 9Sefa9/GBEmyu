package GBEmyu.cpu;

import GBEmyu.RAM;
import com.sun.media.jfxmedia.logging.Logger;

import java.util.logging.Level;

import static java.lang.Thread.sleep;

public class CPU {

    private Register register;
    private Opcode opcodes;
    private RAM ram;
    private int clockspeed;
    private int[] inst;
    private int cycle;
    public CPU(RAM ram, int[] inst) {
        this.ram = ram;
        this.inst = inst;
        register = new Register();
        opcodes = new Opcode();
        clockspeed = 0;
    }
    public void init() {


        for (int i = 0; i < 0x800; ++i) {
            ram.write(i, 0xFF);
        }

        gameLoop();
    }
    private void gameLoop(){

        while(true){

                switch(inst[register.getPc()]) {
                    //case 0x
                    case 0x78: // SEI imm
                        register.setSEI();
                        System.out.println("SEI at " + (String.format("0x%04X", register.getPc())));
                        break;
                    case 0xD8:
                        register.setCLD();
                        System.out.println("CLD at " + (String.format("0x%04X", register.getPc())));
                        break;
                    case 0xA9:
                        register.setLDA(inst[register.getPc()+1]);
                        register.incrementPC();
                        System.out.println("LDA at " + (String.format("0x%04X", register.getPc())));
                        break;
                    case 0xCA: // dec on x and
                        System.out.println("DEX at " + (String.format("0x%04X", register.getPc())));
                        register.setDEX();
                        break;
                    default:{
                        Logger.logMsg(Logger.ERROR,String.format("No command for 0x%02x", inst[register.getPc()]));
                    }
                }
                register.incrementPC();
            try {
                sleep((0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
            //1.Befehl laden
            //2.Befehl dekodieren
            //3.Befehl ausführen
            //4.Ergebnis abspeichern.

    }



}

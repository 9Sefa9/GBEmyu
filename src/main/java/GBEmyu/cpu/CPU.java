package GBEmyu.cpu;

import GBEmyu.RAM;
import GBEmyu.utilities.Conversion;
import com.sun.media.jfxmedia.logging.Logger;

import java.util.logging.Level;

import static java.lang.Thread.sleep;

public class CPU {

    private Register register;
    private Opcode[] opcodes;
    private RAM ram;
    private int clockspeed;
    private int[] inst;
    private int cycle;
    public CPU(RAM ram, int[] inst) {
        this.ram = ram;
        this.inst = inst;
        register = new Register();
        opcodes = new OpcodeBuilder(inst,register).getOpcodes();
        clockspeed = 0;
    }
    public void init() {


        for (int i = 0; i < 0x800; ++i) {
            ram.write(i, 0xFF);
        }
        try {
            sleep((2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameLoop();
    }
    private void gameLoop() {
        //1.Befehl laden
        //2.Befehl dekodieren
        //3.Befehl ausführen
        //4.Ergebnis abspeichern.
        while (true) {
            try{
                int instruction = inst[register.getPc()];

                Opcode instructionToOpcode = opcodes[instruction];
                String opcodeName = instructionToOpcode.getOpcodeName();
                int hexAddress = instructionToOpcode.getHexAddress();
                System.out.println(register.getPc()+"Instruction: "+instruction + " OpcodeName: "+opcodeName+" Hex Addres: "+hexAddress);
                //opcodes[inst[register.getPc()]].operation();
                register.incrementPC();

                sleep(1);
            }catch (InterruptedException | NullPointerException e) {
                System.out.println(register.getPc()+"Instrction: "+ inst[register.getPc()] +" not found in opcodes!");
                e.printStackTrace();
            }

        }
    }
                //switch(inst[register.getPc()]) {
           /* switch(inst[register.getPc()]){
                int hexValue = opcodes[inst[register.getPc()]].getHexAddress();
                        case hexValue:

                        break;

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
                        //System.out.println(String.format("No command for 0x%02x", inst[register.getPc()]));
                       // Logger.logMsg(Logger.ERROR,String.format("No command for 0x%02x", inst[register.getPc()]));
                    }
                }
                register.incrementPC();
            */








}

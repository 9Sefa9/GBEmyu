package GBEmyu.cpu;

import GBEmyu.MemoryMap;

import static java.lang.Thread.sleep;

public class CPU {

    private Register register;
    private Opcode[] opcodes;
    private MemoryMap memoryMap;
    private int clockspeed;
    private int[] instructions;
    private int cycle;
    public CPU(MemoryMap memoryMap, int[] inst) {
        this.memoryMap = memoryMap;
        this.instructions = inst;
        register = new Register();
        opcodes = new OpcodeBuilder(inst,register).getOpcodes();
        clockspeed = 0;
    }
    public void init() {
       // gameLoop();
    }
    private void gameLoop() {
        //1.Befehl laden
        //2.Befehl dekodieren
        //3.Befehl ausführen
        //4.Ergebnis abspeichern.
        while (true) {
            try{
                int instruction = instructions[register.getPC()];

                Opcode instructionToOpcode = opcodes[instruction];
                String opcodeName = instructionToOpcode.getOpcodeName();
                int hexAddress = instructionToOpcode.getHexAddress();
                System.out.println(register.getPC()+"Instruction: "+instruction + " OpcodeName: "+opcodeName+" Hex Addres: "+hexAddress);
                //opcodes[instructions[register.getPC()]].operation();
                register.incrementPC();

                sleep(1);
            }catch (InterruptedException | NullPointerException e) {
                System.out.println(register.getPC()+"Instrction: "+ instructions[register.getPC()] +" not found in opcodes!");
                e.printStackTrace();
            }

        }
    }
                //switch(instructions[register.getPC()]) {
           /* switch(instructions[register.getPC()]){
                int hexValue = opcodes[instructions[register.getPC()]].getHexAddress();
                        case hexValue:

                        break;

                    //case 0x
                    case 0x78: // SEI imm
                        register.setSEI();
                        System.out.println("SEI at " + (String.format("0x%04X", register.getPC())));
                        break;
                    case 0xD8:
                        register.setCLD();
                        System.out.println("CLD at " + (String.format("0x%04X", register.getPC())));
                        break;
                    case 0xA9:
                        register.setLDA(instructions[register.getPC()+1]);
                        register.incrementPC();
                        System.out.println("LDA at " + (String.format("0x%04X", register.getPC())));
                        break;
                    case 0xCA: // dec on x and
                        System.out.println("DEX at " + (String.format("0x%04X", register.getPC())));
                        register.setDEX();
                        break;
                    default:{
                        //System.out.println(String.format("No command for 0x%02x", instructions[register.getPC()]));
                       // Logger.logMsg(Logger.ERROR,String.format("No command for 0x%02x", instructions[register.getPC()]));
                    }
                }
                register.incrementPC();
            */








}

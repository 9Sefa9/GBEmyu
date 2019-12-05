package GBEmyu.cpu;

import GBEmyu.MemoryMap;
import GBEmyu.utilities.Logger;

import java.util.Arrays;
import java.util.logging.Level;

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
        gameLoop();
    }
    private void gameLoop() {

            setCycle(0);
            //1.662607MHz = 1662607Hz
            //1662607Hz/59.9Hz = 27756cycles/frame
            while(getCycle()<=27756) {

                try {
                    //decode instruction
                    int instruction = instructions[register.getPC()];

                    switch(opcodes[instructions[register.getPC()]].getAddressMode()){
                        case IMMEDIATE:

                            break;
                        case ZEROPAGEX:

                            break;

                        case ZEROPAGE:

                            break;

                        case ABSOLUTE:

                            break;

                        case ABSOLUTEX:

                            break;

                        case ABSOLUTEY:

                            break;

                        case INDIRECTX:

                            break;

                        case INDIRECTY:

                            break;

                    }
                    //execute opcode operation
                    opcodes[instructions[register.getPC()]].operation();

                    Logger.LOGGER.log(Level.INFO, "CYCLE::"+getCycle()+"  PC::"+ register.getPC() +"  Instruction(HEX)::" + instruction + "  OpcodeName::" + opcodes[instruction].getOpcodeName() + "  OpcodeHexAddress::" + opcodes[instruction].getHexAddress());
                    register.incrementPC();

                    sleep(500);
                } catch (InterruptedException | NullPointerException e) {
                    System.out.println("CYCLE::"+getCycle()+"  PC::"+ register.getPC() +"  Instruction(HEX)::" + instructions[register.getPC()] + "  NOT FOUND IN OPCODES ! ::"+ Arrays.toString(e.getStackTrace()));
                    break;
                }
            }


    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
}

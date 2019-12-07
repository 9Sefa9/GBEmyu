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
            //1 read or write means 1 CPU cycle.
            setCycle(0);
            //1.662607MHz = 1662607Hz
            //1662607Hz/59.9Hz = 27756cycles/frame
            /*while(getCycle()<=27756)*/
                while(true){

                try {

                    int instruction = instructions[register.getPC()];
                    //Fetch opcode from the current PC address
                    Opcode currentOpcode = opcodes[instructions[register.getPC()]];

                    // opcodes[instructions[register.getPC()]].operation();
                    switch(currentOpcode.getAddressMode()){
                        case HLT:
                            //Halte CPU
                            break;
                        case IMPLICIT:
                            implicit(currentOpcode);
                            break;
                        case IMMEDIATE:
                            immediate(currentOpcode);
                            break;
                        case ACCUMULATOR:
                            //bin mit der reihenfolge nicht sicher aber :
                            //doAkkumulator() oder so
                            //execute opcode operation
                            accumulator();
                            break;
                        case ZEROPAGE:
                            zeropage();
                            break;
                        case ZEROPAGEX:
                            zeropagex();
                            break;
                        case ZEROPAGEY:
                            zeropagey();
                            break;
                        case RELATIVE:
                            relative();
                            break;
                        case ABSOLUTE:
                            absolute();
                            break;
                        case ABSOLUTEX:
                            absolutex();
                            break;
                        case ABSOLUTEY:
                            absolutey();
                            break;
                        case INDIRECT:
                            indirect();
                            break;
                        case INDIRECTX:
                            indirectx();
                            break;
                        case INDIRECTY:
                            indirecty();
                            break;

                    }
                    //opcodes[instructions[register.getPC()]].operation();

                    Logger.LOGGER.log(Level.INFO, "CYCLE::"+getCycle()+"  PC::"+ register.getPC() +"  Instruction(HEX)::" + instruction + "  OpcodeName::" + opcodes[instruction].getOpcodeName() + "  OpcodeHexAddress::" + opcodes[instruction].getHexAddress());
                    register.incrementPC();

                    sleep(0);
                } catch (InterruptedException | NullPointerException e) {
                    System.out.println("CYCLE::"+getCycle()+"  PC::"+ register.getPC() +"  Instruction(HEX)::" + instructions[register.getPC()] + "  NOT FOUND IN OPCODES ! ::"+ Arrays.toString(e.getStackTrace()));
                    break;
                }
            }


    }
    private void implicit(Opcode opcode) {
        // 2 cycles
        register.incrementPC();
        incrementCycle(2);
        opcode.operation(null);
        System.out.println("implicit");
    }
    private void immediate(Opcode opcode) {
        register.incrementPC();
        int[] value = {instructions[register.getPC()]};
        register.incrementPC();
        opcode.operation(value);
        incrementCycle(2);


    }
    private void accumulator() {
        //These instructions have register A (the accumulator) as the target.
        System.out.println("accumulator");

    }


    private void absolute(){

    }
    private void absolutex(){

    }
    private void absolutey(){

    }

    private void relative(){

    }
    private void indirect(){

    }
    private void indirectx(){

    }
    private void indirecty(){

    }
    private void zeropage() {
    }
    private void zeropagex() {
    }
    private void zeropagey() {
        System.out.println("zeropageY");

    }



    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
    private void incrementCycle(int val) {
        this.cycle += val;
    }

}

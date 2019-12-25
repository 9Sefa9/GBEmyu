package GBEmyu.cpu;

import GBEmyu.Bus;

import java.util.Arrays;
import java.util.logging.Level;

import static java.lang.Thread.sleep;

public class CPU {
//@TODO: Memory Mapper0 1 verstehen und anwenden.
    private Register register;
    private Bus bus;
    private Flags flags;
    private Opcode[] opcodes;
   // private int[] instructions;
    private int cycle;
    //einfach nur um zu sehen wie viele zyklen entstehen. Wird nur als print gezeigt.
    public CPU(Bus bus) {
       // this.instructions = inst;
        this.flags = new Flags();
        this.bus = bus;
        this.register = new Register(this,this.bus,this.flags);
        this.opcodes = new OpcodeBuilder(this.register).getOpcodes();
    }
    public void start() {
        //Am anfang soll man ResetInterrupt aufrufen (IRQ). laut https://github.com/bfirsh/jsnes/blob/master/src/mappers.js
        register.setPC(0xC000);
        //Am Anfang sind erstmal keine Zyklen.
        setCycle(0);

        while(true) {

            clock();
            try {
                sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void clock() {
        //System.out.println("CLOCK! "+getCycle());
        //1 read or write means 1 CPU cycle.

        //1.662607MHz = 1662607Hz
        //1662607Hz/59.9Hz = 27756cycles/frame
        /*while(getCycle()<=27756)*/

        if (getCycle() == 0) {
            int instruction;

            Opcode currentOpcode = null;
            try {
                switch (flags.getInterruptFlag()){
                    case interruptNMI:
                        nmi();
                        break;
                    case interruptIRQ:
                        irq();
                        break;
                }

                //liest vielleicht nicht mehr vom ROm, sondern direkt aus dem RAM ???
                incrementCycle(1);
                instruction = bus.read(register.getPC());

                //Fetch opcode from the current PC address
                currentOpcode = opcodes[instruction];
                //Logger.LOGGER.log(Level.INFO,

                // opcodes[instructions[register.getPC()]].operation();
                switch (currentOpcode.getAddressMode()) {
                    case HLT:
                        //Halte CPU - nur wie ? keine ahnung.
                        break;
                    case IMPLICIT:
                        implicit(currentOpcode);
                        break;
                    case IMMEDIATE:
                        immediate(currentOpcode);
                        break;
                    case ACCUMULATOR:
                        accumulator(currentOpcode);
                        break;
                    case ZEROPAGE:
                        zeropage(currentOpcode);
                        break;
                    case ZEROPAGEX:
                        zeropagex(currentOpcode);
                        break;
                    case ZEROPAGEY:
                        zeropagey(currentOpcode);
                        break;
                    case RELATIVE:
                        relative(currentOpcode);
                        break;
                    case ABSOLUTE:
                        absolute(currentOpcode);
                        break;
                    case ABSOLUTEX:
                        absolutex(currentOpcode);
                        break;
                    case ABSOLUTEY:
                        absolutey(currentOpcode);
                        break;
                    case INDIRECT:
                        indirect(currentOpcode);
                        break;
                    case INDIRECTX:
                        indirectx(currentOpcode);
                        break;
                    case INDIRECTY:
                        indirecty(currentOpcode);
                        break;

                }

                incrementCycle(currentOpcode.getCycle()+1);

                GBEmyu.utilities.Logger.LOGGER.log(Level.INFO,"\n\nCLOCK METHOD :::  Name:  "+ currentOpcode.getOpcodeName()+
                        "  AddressMode:  "+currentOpcode.getAddressMode()+" PC(DECIMAL):  "+ register.getPC()+" PC(HEX):  "+String.format("%4X",register.getPC())+"  Instruction(HEX): " + String.format("%08X",currentOpcode.getHexAddress()) +" Cycle: "+(getCycle())+" A:  "+register.getA()+" X:  "+register.getX()+" Y:  "+register.getY()+" P:  "+register.getP()+" SP:  "+register.getSP());


                register.incrementPC();


            } catch ( NullPointerException e) {
               e.printStackTrace();
            }

        }
        decrementCycle(1);
    }

    //TODO SBC nimmt parameter entgegen, den ich nicht kenne ? => CBB4  E9 40     SBC #$40
    private void implicit(Opcode opcode) {


        incrementCycle(2);
        //int[] test ={0};
        opcode.operation(null);

    }
    private void immediate(Opcode opcode) {

        register.incrementPC();
        int[] fetchValue = {bus.read(register.getPC()+1)};
        incrementCycle(2);
        opcode.operation(fetchValue);

    }
    private void accumulator(Opcode opcode) {
        //These instructions have register A (the accumulator) as the target.
        opcode.operation(null);

    }

    private void absolute(Opcode currentOpcode){

        int[] value = {register.read16(register.getPC()+1)};

            currentOpcode.operation(value);



    }
    private void absolutex(Opcode currentOpcode){

        int addr =register.read16(register.getPC()+1) +(register.getX()& 0xFFFF);

        if(pageCrossing(addr-(register.getX()& 0xFFFF),addr)){
            incrementCycle(5);
            currentOpcode.operation(new int[]{addr});
        }else{
            incrementCycle(4);
            currentOpcode.operation(new int[]{addr});
        }
    }



    private void absolutey(Opcode currentOpcode){

        int addr =register.read16(register.getPC()+1) + (register.getY()& 0xFFFF);

        if(pageCrossing(addr-(register.getY()& 0xFFFF),addr)){
            incrementCycle(5);
            currentOpcode.operation(new int[]{addr});
        }else{
            incrementCycle(4);
            currentOpcode.operation(new int[]{addr});
        }

    }
    private void indirect(Opcode currentOpcode){

        int []value = {register.read16bug(register.read16(register.getPC()+1))};

        currentOpcode.operation(value);

    }
    private void indirectx(Opcode currentOpcode){

        int []value = {register.read16bug((bus.read(register.getPC()+1))+register.getX())};
        currentOpcode.operation(value);

    }
    private void indirecty(Opcode currentOpcode){


        int []value = {register.read16bug((bus.read(register.getPC()+1)&0xFFFF)+(register.getY()& 0xFFFF))};
        if(pageCrossing(value[0]-register.getY(), value[0])){
            incrementCycle(6);
            currentOpcode.operation(value);
        }else{
            incrementCycle(5);
            currentOpcode.operation(value);
        }


    }
    private void relative(Opcode currentOpcode){

        incrementCycle(1);

        int offset = bus.read(register.getPC()+1) & 0xFFFF;
        int []value=new int[1];
        if(offset < 0x80) {
            value[0] = register.getPC() + 2 + offset;
            currentOpcode.operation(value);
        }
        else{
            value[0] = register.getPC() + 2 + offset - 0x100;
            currentOpcode.operation(value);
        }

    }

    private void zeropage(Opcode currentOpcode) {

        int[] val = {((bus.read(register.getPC()+1)) & 0xFFFF) & 0xFFFF};
        register.incrementPC();
        incrementCycle(3);
        currentOpcode.operation(val);
    }
    private void zeropagex(Opcode currentOpcode) {

        incrementCycle(1);
        int[] fetchValue = {((bus.read(register.getPC()+1)+ register.getX()) & 0xFFFF) & 0xFF };
        incrementCycle(4);
        currentOpcode.operation(fetchValue);



    }
    private void zeropagey(Opcode currentOpcode) {

        //auf meine weise gelöst. Vllt. klappt es ohne diese anleitung oben.

        incrementCycle(1);
        int[] fetchValue = {((bus.read(register.getPC()+1)+ register.getY()) & 0xFFFF) & 0xFF };
        incrementCycle(4);
        currentOpcode.operation(fetchValue);

    }

    // Reset resets the CPU to its initial powerup state

    private void reset() {
        incrementCycle(1);
        register.setPC(register.read16(0xFFFC));
        register.setSP(0xFD);
        register.setP(0x24);
        // register.setPC(bus.read16(0xFFFC));
        //   cpu.PC = cpu.Read16(0xFFFC)
        //   cpu.SP = 0xFD
        //   cpu.SetFlags(0x24)
    }
    //non maskable interrupt. tritt auf beim nächsten cycle.
    public void triggerNMI(){
        flags.setInterruptFlags(Flags.Interrupt.interruptNMI);
    }
    //sorgt für ein interrupt beim nächsten cycle.
    public void triggerIRQ(){
        if(flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.INTERRUPTDISABLE) == 0){
            flags.setInterruptFlags(Flags.Interrupt.interruptIRQ);
        }
    }
    //wird von der PPU generiert.
    public void nmi(){
        register.push16(register.getPC());
        register.php();
        register.setPC(register.read16(0xFFFA));
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.INTERRUPTDISABLE,1);
        incrementCycle(7);
    }
    public void irq(){
        register.push16(register.getPC());
        register.php();
        register.setPC(register.read16(0xFFFE));
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.INTERRUPTDISABLE,1);
        incrementCycle(7);
    }
    private boolean pageCrossing(int lo, int hi) {
        return (lo & 0xFF00) != (hi & 0xFF00);
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
    public void incrementCycle(int val) {
        this.cycle += val;
    }
    public void decrementCycle(int val) {
        this.cycle -= val;
    }

}

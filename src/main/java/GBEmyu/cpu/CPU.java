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
                sleep(300);
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

                flags.setInterruptFlags(Flags.Interrupt.interruptNone);

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

                GBEmyu.utilities.Logger.LOGGER.log(Level.INFO,"\n\nCLOCK METHOD ::: A: "+register.getA()+" X: "+register.getX()+" Y: "+register.getY()+" P: "+register.getP()+" SP:"+register.getSP()+" PC: "+ register.getPC()+" Cycle: "+(getCycle())+
                        "  AddressMode: "+currentOpcode.getAddressMode()+"  Instruction(HEX): " + String.format("%04X000",currentOpcode.getHexAddress()) +"  Instruction(DEC)):" + currentOpcode.getHexAddress()+"  Name: "+ currentOpcode.getOpcodeName());


                register.incrementPC();


            } catch ( NullPointerException e) {
               e.printStackTrace();
            }

        }
        decrementCycle(1);
    }


    private void implicit(Opcode opcode) {


        /*
        imp() is implied mode, it is for opcodes
        that does not load a value or write to from memory,
        but instead sets a flag, transfer variables,
        or push things onto the stack, etc.
        1. Fetch opcode from the current PC address,
        and then increment the PC.
        2. Read the next instruction byte, but doesn't
        do anything to it, and then do operation on it.
        2 cycles*/

        incrementCycle(2);
        opcode.operation(null);

    }
    private void immediate(Opcode opcode) {
        /*
        mm() is immediate mode, this
        is 2 cycles, where it fetches
        the next place in the program counter
        getting the operand for the opcode to
        act on, this is for things such
        as adding/subtracting constants,
        loading values, etc.
        1. Fetch opcode from the current PC address,
        and then increment the PC.
        2. Fetch the operand from next byte in PC,
        increment the PC, and do operation using the
        value fetched.
        2 cycles
         */
        register.incrementPC();
        //TODO +1 wurde nach hinzugefügt. Wieder entfernen ?
        int[] fetchValue = {bus.read(register.getPC()+1)};
       // register.incrementPC();
        incrementCycle(2);
        opcode.operation(fetchValue);

    }
    private void accumulator(Opcode opcode) {
        //These instructions have register A (the accumulator) as the target.
        opcode.operation(null);

    }

    private void absolute(Opcode currentOpcode){
            /*
            vergiss nicht : opcode := cpu.Read(cpu.PC)
            ----

            aufruf von : cpu.Read16(cpu.PC + 1)

            func (cpu *CPU) Read16(address uint16) uint16 {
            	lo := uint16(cpu.Read(address))
	            hi := uint16(cpu.Read(address + 1))
	            return hi<<8 | lo
}
             */
            //so war das standardmässig.

            //int lo = getInstructions()[((register.getPC() + 1)& 0xFFFF)];
            //int hi = getInstructions()[((register.getPC() + 1)+1 & 0xFFFF)];
            //int []value = {(hi <<8 | lo) & 0xFFFF};
            //System.out.println(value[0]);

        //so war das standardmäßig 18.12.2019
        //incrementCycle(4);
        int[] value = {register.read16(register.getPC()+1)};

            currentOpcode.operation(value);



    }
    private void absolutex(Opcode currentOpcode){
        /*
        // pagesDiffer returns true if the two addresses reference different pages
            func pagesDiffer(a, b uint16) bool {
	            return a&0xFF00 != b&0xFF00

	            case modeAbsoluteX:
		            address = cpu.Read16(cpu.PC+1) + uint16(cpu.X)
		            pageCrossed = pagesDiffer(address-uint16(cpu.X), address)
}
         */
        /* so war es früher.
        register.incrementPC();
        int lo = bus.read(register.getPC());
        register.incrementPC();
        int hi = bus.read(register.getPC());

        int[] value ={ (hi <<8 | lo) + register.getX()};

        if(pageCrossing(lo,hi)){
                incrementCycle(5);
                currentOpcode.operation(value);
        }else{
            incrementCycle(4);
                currentOpcode.operation(value);
        }
        */
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
        /* so war es früher.
        register.incrementPC();
        int lo = bus.read(register.getPC());
        register.incrementPC();
        int hi = bus.read(register.getPC());

        int[] value ={ (hi <<8 | lo) + register.getY()};

        if(pageCrossing(lo,hi)){
            incrementCycle(5);
            currentOpcode.operation(value);
        }else{
            incrementCycle(4);
            currentOpcode.operation(value);
        }

        currentOpcode.operation(value);
        */
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
        //cpu.read16bug(uint16(cpu.Read(cpu.PC+1) + cpu.X))
        /*
        func (cpu *CPU) read16bug(address uint16) uint16 {
	        a := address
	        b := (a & 0xFF00) | uint16(byte(a)+1)
	        lo := cpu.Read(a)
	        hi := cpu.Read(b)
	        return uint16(hi)<<8 | uint16(lo)
}
         */
        int []value = {register.read16bug(register.read16(register.getPC()+1))};
        /*
        register.incrementPC();
        int[] a ={instructions[register.getPC()]};

        int b = ((a[0] & 0xFF) | (a[0]+1) & 0xFF);

        int lo = instructions[a[0]];
        int hi = instructions[b];
        int[] value = { ((hi & 0xFF) <<8) | (lo & 0xFF) };
        incrementCycle(6);*/
        currentOpcode.operation(value);

    }
    private void indirectx(Opcode currentOpcode){
        //cpu.read16bug(uint16(cpu.Read(cpu.PC+1) + cpu.X))
        /*
        func (cpu *CPU) read16bug(address uint16) uint16 {
	        a := address
	        b := (a & 0xFF00) | uint16(byte(a)+1)
	        lo := cpu.Read(a)
	        hi := cpu.Read(b)
	        return uint16(hi)<<8 | uint16(lo)
}
         */

        int []value = {register.read16bug((bus.read(register.getPC()+1))+register.getX())};
/*
        register.incrementPC();
        int[] a = {(instructions[register.getPC()] +register.getX())&0xFF};

        int b = ((a[0] & 0xFF) | (a[0]+1) & 0xFF);
        int lo = instructions[a[0]];
        int hi = instructions[b];
        int[] value = { ((hi & 0xFF) <<8) | (lo & 0xFF) };
        incrementCycle(6);*/
        currentOpcode.operation(value);

    }
    private void indirecty(Opcode currentOpcode){
            /*
            case modeIndirectIndexed:
		        address = cpu.read16bug(uint16(cpu.Read(cpu.PC+1))) + uint16(cpu.Y)
		        pageCrossed = pagesDiffer(address-uint16(cpu.Y), address)
             */
        /*
        register.incrementPC();
        incrementCycle(3);
        int[] a ={bus.read(register.getPC()) +register.getY()};

        int b = ((a[0] & 0xFF) | (a[0]+1) & 0xFF);

        int lo = bus.read(a[0]);
        int hi = bus.read(b);
        int[] value = { ((hi & 0xFF) <<8) | (lo & 0xFF) };
        if(pageCrossing(a[0] - register.getY(), a[0])){
            incrementCycle(6);
            currentOpcode.operation(value);
        }else{
            incrementCycle(5);
            currentOpcode.operation(value);
        }
        */

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
    /*
            case modeRelative:
		        offset := uint16(cpu.Read(cpu.PC + 1))
		        if offset < 0x80 {
			    address = cpu.PC + 2 + offset
		        } else {
			    address = cpu.PC + 2 + offset - 0x100
		        }
    */
        //ursprünglich nicht da gewesen
        //register.incrementPC();

        incrementCycle(1);

        int offset = bus.read(register.getPC()+1) & 0xFFFF;
        int []value=new int[1];
        if(offset < 0x80)
            value[0] = register.getPC() + 2 + offset;
        else
            value[0] = register.getPC() +2 + offset - 0x100;
       //unbedingt beibehalten oder ändern.! register.incrementPC();
        currentOpcode.operation(value);
    }

    private void zeropage(Opcode currentOpcode) {
        /*
        zp_r() is zero page read mode.
It is for opcodes that read from zero page,
to get the value and do operation on it,
such as zero page ADC, SBC, LDA, CMP, etc.
The address range is [0, 0xFF], thus
saving one cycle for getting the hi byte
of the PC.
1. Fetch opcode from the current PC address,
   and then increment the PC.
2. Fetch the operand from the next byte
   in the PC, and then increment the PC.
3. Fetch the value stored in the
   address which is the operand
   value, and do operation using the
   value fetched from the address.
3 cycles
         */
        //auf meine weise gelöst. Vllt. klappt es ohne diese anleitung oben.

        /*
        incrementCycle(1);
        int[] fetchValue = {bus.read(register.getPC()+1) & 0xFFFF };
        currentOpcode.operation(fetchValue);
        register.incrementPC();
         */
        int[] val = {(bus.read(register.getPC()+1)) & 0xFFFF};
        register.incrementPC();
        incrementCycle(3);
        currentOpcode.operation(val);
    }
    private void zeropagex(Opcode currentOpcode) {
        /*
        *	zpx_r() is zero page indexed addressing.
            It is the same as zp_r(), but it adds the operand
            address with the X register value to get the address
            needed.
        1. Fetch opcode from the current PC address,
            and then increment the PC.
        2. Fetch the operand from the next byte
            in the PC, and then increment the PC.
        3. Read from the address, and then
            add the X register to the address,
            it wraps around to 0 if address + X > 0xFF,
         as it does not handle page crossing.
        4. Fetch the value at the address,
             and then do operation on it.
        4 cycles
        */
        //ich lass das erstmal so... @TODO muss aber auf jeden fall mal betrachtet werden.

        incrementCycle(1);
        int[] fetchValue = {(bus.read(register.getPC()+1)+ register.getX()) & 0xFF };
        incrementCycle(4);
        currentOpcode.operation(fetchValue);



    }
    private void zeropagey(Opcode currentOpcode) {

        //auf meine weise gelöst. Vllt. klappt es ohne diese anleitung oben.

        incrementCycle(1);
        int[] fetchValue = {(bus.read(register.getPC()+1)+ register.getY()) & 0xFF };
        incrementCycle(4);
        currentOpcode.operation(fetchValue);

    }

    // Reset resets the CPU to its initial powerup state
    //@TODO Reset implementieren. Eventuell den Mapper0 auch.
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
        if(Flags.ProcessorStatusFlags.INTERRUPTDISABLE.getVal() == 0){
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

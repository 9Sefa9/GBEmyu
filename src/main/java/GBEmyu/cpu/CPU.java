package GBEmyu.cpu;

import java.util.Arrays;

import static java.lang.Thread.sleep;
//@TODO BUS write und read erstellen, da essenziell für die Opcodes. Dannach: Opcode funktionalitäten implementieren.
public class CPU {

    private Register register;
    private Flags flags;
    private Opcode[] opcodes;

    private int[] instructions;
    private int cycle;
    public CPU(int[] inst) {
        this.instructions = inst;
        this.flags = new Flags();
        register = new Register(this.flags);
        opcodes = new OpcodeBuilder(this.instructions,this.register).getOpcodes();
    }
    public void start() {
        setCycle(0);
        while(true) {
            clock();
            try {
                sleep(1);
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

                instruction = instructions[register.getPC()];
                //Fetch opcode from the current PC address
                currentOpcode = opcodes[instruction];
                //Logger.LOGGER.log(Level.INFO,
                System.out.println("\n         MAIN CYCLE::" + getCycle() +" OPCODE Cycle:"+currentOpcode.getCycle()+" AddressMode:  "+currentOpcode.getAddressMode()+"  PC::" + register.getPC() + "  Instruction(HEX)::" + String.format("%04X",instructions[register.getPC()]) + "  OpcodeName::" + currentOpcode.getOpcodeName() + "  OpcodeHexAddress::" + currentOpcode.getHexAddress()+"\n");
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
                incrementCycle(currentOpcode.getCycle());
                register.incrementPC();


            } catch ( NullPointerException e) {
                System.out.println("EXCEPTION _________ MAIN CYCLE::" + getCycle() + ""+"OPCODE Cycle:"+currentOpcode.getCycle()+"   PC::" + register.getPC() + "  Instruction(HEX)::" + String.format("%04X",instructions[register.getPC()]) + "  NOT FOUND IN OPCODES ! ::" + Arrays.toString(e.getStackTrace()));

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
        int[] fetchValue = {instructions[register.getPC()]};
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

            register.incrementPC();
            int lo = instructions[register.getPC()];
            register.incrementPC();
            int hi = instructions[register.getPC()];

            int[] value ={ (hi <<8 | lo)};
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

        register.incrementPC();
        int lo = instructions[register.getPC()];
        register.incrementPC();
        int hi = instructions[register.getPC()];

        int[] value ={ (hi <<8 | lo) + register.getX()};

        if(pageCrossing(lo,hi)){
                incrementCycle(5);
                currentOpcode.operation(value);
        }else{
            incrementCycle(4);
                currentOpcode.operation(value);
        }

        currentOpcode.operation(value);

    }



    private void absolutey(Opcode currentOpcode){

        register.incrementPC();
        int lo = instructions[register.getPC()];
        register.incrementPC();
        int hi = instructions[register.getPC()];

        int[] value ={ (hi <<8 | lo) + register.getY()};

        if(pageCrossing(lo,hi)){
            incrementCycle(5);
            currentOpcode.operation(value);
        }else{
            incrementCycle(4);
            currentOpcode.operation(value);
        }

        currentOpcode.operation(value);
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

        register.incrementPC();
        int[] a ={instructions[register.getPC()]};

        int b = ((a[0] & 0xFF) | (a[0]+1) & 0xFF);

        int lo = instructions[a[0]];
        int hi = instructions[b];
        int[] value = { ((hi & 0xFF) <<8) | (lo & 0xFF) };
        incrementCycle(6);
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

        register.incrementPC();
        int[] a = {(instructions[register.getPC()] +register.getX())&0xFF};

        int b = ((a[0] & 0xFF) | (a[0]+1) & 0xFF);
        int lo = instructions[a[0]];
        int hi = instructions[b];
        int[] value = { ((hi & 0xFF) <<8) | (lo & 0xFF) };
        incrementCycle(6);
        currentOpcode.operation(value);

    }
    private void indirecty(Opcode currentOpcode){
            /*
            case modeIndirectIndexed:
		        address = cpu.read16bug(uint16(cpu.Read(cpu.PC+1))) + uint16(cpu.Y)
		        pageCrossed = pagesDiffer(address-uint16(cpu.Y), address)
             */

        register.incrementPC();
        int[] a ={instructions[register.getPC()] +register.getY()};

        int b = ((a[0] & 0xFF) | (a[0]+1) & 0xFF);

        int lo = instructions[a[0]];
        int hi = instructions[b];
        int[] value = { ((hi & 0xFF) <<8) | (lo & 0xFF) };
        if(pageCrossing(a[0] - register.getY(), a[0])){
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

        register.incrementPC();
        int offset = instructions[register.getPC()];
        int []value=new int[1];
        if(offset < 0x80)
            value[0] = register.getPC() + 2 +offset;
        else
            value[0] = register.getPC() +2 + offset - 0x100;
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

        register.incrementPC();
        int[] fetchValue = {instructions[register.getPC()]};
        incrementCycle(3);
        currentOpcode.operation(fetchValue);
        register.incrementPC();

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

        register.incrementPC();
        int[] fetchValue = {instructions[register.getPC()]};
        fetchValue[0] = (fetchValue[0] + register.getX()) & 0xFF;
        incrementCycle(4);
        currentOpcode.operation(fetchValue);



    }
    private void zeropagey(Opcode currentOpcode) {

        //auf meine weise gelöst. Vllt. klappt es ohne diese anleitung oben.
        register.incrementPC();
        int[] fetchValue = {instructions[register.getPC()]};
        fetchValue[0] = (fetchValue[0] + register.getY()) & 0xFF;
        incrementCycle(4);
        currentOpcode.operation(fetchValue);

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
    private void incrementCycle(int val) {
        this.cycle += val;
    }
    private void decrementCycle(int val) {
        this.cycle -= val;
    }
}

package GBEmyu.cpu;

import GBEmyu.Bus;

public class Register {
    //register
    private int a; // accumulator
    private int x;
    private int y;
    private int p; //status register
    private int pc; //Program counter
    private int sp; //Stack Pointer

    private Flags flags;
    private Bus bus;
    private CPU cpu;
    public Register(CPU cpu, Bus bus, Flags flags){
        this.flags = flags;
        this.bus = bus;
        this.cpu = cpu;
    }


    // Operations according to opcode - vorwiegend aus https://github.com/fogleman/nes/blob/e8f89a2f2d8ab90ff85e41810db8c9829cff3bb8/nes/cpu.go#L302 entnommen.
    public void lda(int value8bit) {
        setA(bus.read(value8bit));
        setZeroNegativeFlag(getA());

    }
    public void ldx(int value8bit) {
        setX(bus.read(value8bit));
        setZeroNegativeFlag(getX());

    }
    public void ldy(int value8bit) {
        setY(bus.read(value8bit));
        setZeroNegativeFlag(getY());

    }
    public void sta(int address){
        cpu.incrementCycle(1);
        bus.write(address, getA());
    }
    public void stx(int address){
        cpu.incrementCycle(1);
        bus.write(address, getX());
    }
    public void sty(int address){
        cpu.incrementCycle(1);
        bus.write(address, getY());
    }
    public void tax(){
        setX(getA());
        setZeroNegativeFlag(getX());
    }
    public void tay(){
        setY(getA());
        setZeroNegativeFlag(getY());
    }
    public void tsx(){
        setX(getSP());
        setZeroNegativeFlag(getX());

    }
    public void txa(){
        setA(getX());
        setZeroNegativeFlag(getA());
    }
    public void txs(){
        setSP(getX());
    }
    public void tya(){
        int y = getY();
        setA(y);
        setZeroNegativeFlag(y);

    }
    public void nop(){
        //keine operation.
    }
    public void ora(int value){
        int a = value | getA();
        setA(a);
        setZeroNegativeFlag(getA());

    }
    public void cli(){ flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.INTERRUPTDISABLE,0); }
    public void clv(){ flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.OVERFLOW,0); }
    public void sec(){ flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,1); }
    public void sed(){ flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.DECIMALMODE,1); }
    public void sei(){
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.INTERRUPTDISABLE,1);
    }
    public void clc(){ flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,0); }
    public void cld(){
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.DECIMALMODE,0);
    }
    public void brk(){
        push16(getPC());
        php();
        sei();
        setPC(read16(0xFFFE));
    }
    public void cmp(int value){
        int v = bus.read(value);
        compare(getA(),v);

    }
    public void cpx(int value){
        int v = bus.read(value);
        compare(getX(),v);
    }
    public void cpy(int value){
        /*
        int y = getY();
        setZeroNegativeFlag(y - value);
        if (y >= value)
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,1);

        else flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,0);
        */
        int v = bus.read(value);
        compare(getY(),v);
    }

    public void and(int value){
        setA(value & getA());
        setZeroNegativeFlag(getA());

    }
    public void aslAccumulator(){

        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,(getA() >> 7)&1);
        setA(getA()<<1);
        //this.a = ((this.a << 1) & 0x100);
        setZeroNegativeFlag(getA());

    }
    public void asl(int address){
        //weil read und write.
        cpu.incrementCycle(2);
        int value = bus.read(address);
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,(value >> 7)&1);
        value <<=1;
        cpu.incrementCycle(1);
        bus.write(address, value);
        setZeroNegativeFlag(value);
    }
    public void bit(int value){
        /*
        func (cpu *CPU) setZ(value byte) {
	if value == 0 {
		cpu.Z = 1
	} else {
		cpu.Z = 0
	}
}

// setN sets the negative flag if the argument is negative (high bit is set)
func (cpu *CPU) setN(value byte) {
	if value&0x80 != 0 {
		cpu.N = 1
	} else {
		cpu.N = 0
	}
}
         */

        int v = bus.read(value);
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.OVERFLOW,(v >> 6) & 1);

        //setZeroFlag
        if((v & getA())==0){
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.ZERO,1);
        }else{
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.ZERO,0);
        }

        //setNegativeFlag
        if((v & 0x80) != 0){
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.NEGATIVE,1);
        }else{
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.NEGATIVE,0);
        }


    }
    public void eor(int value){
        int o = bus.read(value) ^ getA();
        setA(o);
        setZeroNegativeFlag(getA());

    }
    public void lsrAccumulator(){
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,(getA() &1));
        setA(getA()>>1);
        setZeroNegativeFlag(getA());
    }
    public void lsr(int value){
        int a = bus.read(value);
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,(a &1));
        a >>=1;
        cpu.incrementCycle(1);
        bus.write(value, a);
        setZeroNegativeFlag(a);
    }
    public void rolAccumulator(){
        int c = flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY);
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,(getA() >> 7)&1);
        setA( (getA() << 1) | c);
        setZeroNegativeFlag(getA());

    }
    public void rol(int value){
        int c = flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY);
        int v = bus.read(value);
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,(v >> 7)&1);
        v = (v << 1) | c;
        cpu.incrementCycle(1);
        bus.write(value, v);
        setZeroNegativeFlag(v);
    }

    public void rorAccumulator(){
        int c = flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY);
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,(getA() & 1));
        setA( ( getA() >> 1) | (c << 7));
        setZeroNegativeFlag(getA());

    }
    public void ror(int v){
        int c = flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY);
        int value = bus.read(v);
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY, value & 1);
        value = (value >> 1) | (c << 7 );
        cpu.incrementCycle(1);
        bus.write(v, value);
        setZeroNegativeFlag(value);
    }
    public void adc(int value){
        int a = getA();
        int b = bus.read(value);
        int c = flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY);
        setA(a+b+c);
        setZeroNegativeFlag(getA());
        if( (a + b +c) > 0xFF)
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,1);

        else flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,0);

        if( ((a^b) & 0x80) == 0 && ((a^getA()&0x80) != 0))
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.OVERFLOW,1);
        else flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.OVERFLOW,0);

    }
    public void dec(int value){
        int val = bus.read(value)-1;
        cpu.incrementCycle(2);
        bus.write(value,val);
        setZeroNegativeFlag(val);
    }
    public void dex(){
        setX(getX()-1);
        setZeroNegativeFlag(getX());
    }
    public void dey(){
        setY(getY()-1);
        setZeroNegativeFlag(getY());
    }
    public void inc(int value){
        int v = bus.read(value)+1;
        cpu.incrementCycle(1);
        bus.write(value, v);
        setZeroNegativeFlag(value);
    }
    public void inx(){
        int newX = getX()+1;
        setX(newX);
        setZeroNegativeFlag(getX());
    }
    public void iny(){
        int newY = getY()+1;
        setY(newY);
        setZeroNegativeFlag(getY());
    }
    public void jmp(int value){
        setPC(value);
        //decrement existeirt normalerweise nicht. Der Register wrid aber im Cycle erhöht. Somit ist es dann im gejumpten addressraum + 1. Das soll aber nicht sein.
        decrementPC(1);
    }
    public void jsr(int value){
        push16(getPC()-1);
        setPC(value);
        //decrement existeirt normalerweise nicht. Der Register wrid aber im Cycle erhöht. Somit ist es dann im gejumpten addressraum + 1. Das soll aber nicht sein.
        decrementPC(1);
    }
    public void pha(){
        push(getA());
    }
    public void php(){

        push(getP() | 0x10);
    }
    public void pla(){
        setA(pull());
        setZeroNegativeFlag(getA());
    }
    public void plp(){
        setP((pull()&0xEF) | 0x20);
    }
    public void rti(){
        setP(pull() & 0xEF | 0x20);
        setPC(pull16());
    }
    public void rts(){
        setPC(pull16()+1);

    }
    public void sbc(int value){
       int a = getA();

       int b = bus.read(value);
       int c = flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY);
        setA(a-b-(1-c));
        setZeroNegativeFlag(getA());

        if( ((a)- (b) -(1-c))>=0)
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,1);
        else  flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,0);

        if( ((a^ b)&0x80) != 0 && ((a^getA())&0x80) != 0)
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.OVERFLOW,1);
        else  flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.OVERFLOW,0);
    }
    //TODO DIE BRNACHES MÜSSEN GEFIXXT WERDEN!
    public void bcc(int value){
        if(flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY) ==0) {
            addBranchCycles(value);
            setPC(value);
            decrementPC(1);
        }else{
            incrementPC(1);
        }
    }

    public void bcs(int value){
        if(flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY) == 1) {
            addBranchCycles(value);
            setPC(value);
            decrementPC(1);
        }else{
            incrementPC(1);
        }
    }
    //todo geändert zu ==0, war aber != 0. und decrement entfernt.
    public void beq(int value){
        if(flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.ZERO) ==0) {
            addBranchCycles(value);
            setPC(value);
            decrementPC(1);
        }else{
            incrementPC(1);
        }
    }
    //TODO C8C0 den itibaren kontorllieren et.
    public void bne(int value){
        if(flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.ZERO) ==0){
            setPC(value);;
            addBranchCycles(value);
            decrementPC(1);
        }else{
            incrementPC(1);
        }
    }
    public void bmi(int value){
        if(flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.NEGATIVE) !=0){
            setPC(value);
            addBranchCycles(value);
            decrementPC(1);
        }else{
            incrementPC(1);
        }
    }

    public void bpl(int value){
        if(flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.NEGATIVE) ==0){
            setPC(value);
            addBranchCycles(value);
            decrementPC(1);
        }else{
            incrementPC(1);
        }
    }
    public void bvc(int value){
        if(flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.OVERFLOW) ==0){
            setPC(value);
            addBranchCycles(value);
            decrementPC(1);
        }else{
            incrementPC(1);
        }
    }
    public void bvs(int value){
        if(flags.getProcessorStatusFlag(Flags.ProcessorStatusFlags.OVERFLOW) !=0){
            setPC(value);
            addBranchCycles(value);
            decrementPC(1);
        }else{
            incrementPC(1);
        }
    }




    //ILLEGALE OPCODE FUNKTIONALITÄTEN:
    public void kilX(){
        //void hlt()
        //  {
        //some call it KIL
        //halt the CPU, reset needed to continue
        //operation
	            /*   opcode 0x02, 0x12, 0x22, 0x32,
	                 0x42, 0x52, 0x62, 0x72, 0x92, 0xB2,
	                 0xD2, 0xF2
	                 */
        //  }
    }

    private void addBranchCycles(int value) {
        cpu.incrementCycle(1);
        //wenn pages differenzieren.
        if((getPC()&0xFF00) != (value&0xFF00)){
            cpu.incrementCycle(1);
        }
    }
    private void setZeroNegativeFlag(int v) {
        if((v & 0xFF) == 0){
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.ZERO,1);
        }else{
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.ZERO,0);
        }

        //war vorher 0x80 !
        if(((v & 0x80) != 0)){
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.NEGATIVE,1);
        }else{
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.NEGATIVE,0);
        }

    }
    // Read16 reads two bytes using Read to return a double-word value
    public int read16(int address){
        cpu.incrementCycle(2);
        int lo = bus.read(address )& 0xFFFF;
        int hi =  bus.read(address+1)& 0xFFFF;
        return (hi <<8 | lo) & 0xFFFF;
    }
    // read16bug emulates a 6502 bug that caused the low byte to wrap without
// incrementing the high byte
    public int read16bug(int address){
        int a = address;
        int b = (a & 0xFF00 | (((a&0xFF)+1) & 0xFFFF));
        cpu.incrementCycle(2);
        int lo = bus.read(a);
        int hi = bus.read(b);
        return ((hi & 0xFFFF) <<8 | (lo & 0xFFFF))  & 0xFFFF;
    }
    // push pushes a byte onto the stack
    private void push(int value){
        cpu.incrementCycle(1);
        bus.write(0x100|(getSP()) & 0xFFFF,(value & 0xFF));
        decrementSP();
    }
    // pull pops a byte from the stack
    private int pull(){
        incrementSP();
        cpu.incrementCycle(1);
        return (bus.read(0x100|getSP() & 0xFFFF)) & 0xFF;
    }
    public void push16(int value){
        int hi = ((value & 0xFFFF) >> 8) & 0xFF;
        int lo = ((value & 0xFFFF) & 0xFF) & 0xFF;
        push(hi);
        push(lo);
    }
    private int pull16(){
       int lo = pull() & 0xFFFF;

       int hi = pull() & 0xFFFF;

       return (hi<<8 | lo) & 0xFFFF;
    }






    // Borrow
    private void setNegativeZeroBorrowFlag(int v) {
        if((v & 0xFF) == 0){
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.ZERO,1);
        }
        if(((v & 0x80) != 0)){
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.NEGATIVE,1);
        }
        if(((v & 0x100) == 0)){
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,1);
        }

    }

    // Carry
    private void setNegativeZeroCarryFlag(int v) {
        if((v & 0xFF) == 0){
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.ZERO,1);
        }
        if(((v & 0x80) != 0)){
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.NEGATIVE,1);
        }
        if(((v & 0x100) != 0)){
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,1);
        }

    }



    //TODO : Zahl in die einzelnen FLAGS umwandeln.  also die einzelnen BITS aus getP().
    //Register setters
    public void incrementPC() {
        this.pc = (pc + 1) & 0xFFFF; //sicher gehen, dass er 0xFFFF nicht überschreitet.
    }
    public void incrementSP() { setSP(sp+1);/*(sp + 1) & 0xFFFF; */}
    public void incrementPC(int value){
        this.pc = (pc + value) & 0xFFFF;
    }
    public void decrementPC(int value){
        this.pc = (pc - value) & 0xFFFF;
    }
    public void decrementSP() { setSP(sp-1);/*(sp - 1) & 0xFFFF; */}
    public void setPC(int pc){this.pc = (pc & 0xFFFF);}
    // versuche 0x100 in 0xFF zu ändern.
    // versuche 0x100 in 0xFF zu ändern.
    public void setSP(int sp) {
        this.sp = (sp& 0xFF);
    }
    // versuche 0x100 in 0xFF zu ändern.
    public void setA(int a) { this.a = (a & 0xFF); }
    public void setX(int x) {
        this.x = (x & 0xFF);
    }
    // versuche 0x100 in 0xFF zu ändern.
    public void setY(int y) {
        this.y = (y & 0xFF);
    }
    // versuche 0x100 in 0xFF zu ändern.
    public void setP(int p) {
        this.p = (p & 0xFF);
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,(p & 1));
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.ZERO,(p >> 1) & 1);
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.INTERRUPTDISABLE,(p >> 2) & 1);
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.DECIMALMODE,(p >> 3) & 1);
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.BREAKCOMMAND,(p >> 4) & 1);
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.UNKNOWN,(p >> 5) & 1);
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.OVERFLOW,(p >> 6) & 1);
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.NEGATIVE,(p >> 7) & 1);
    }

    //Register getters
    public int getSP() {
        return this.sp & 0xFF;
    }
    public int getPC(){
        return this.pc & 0xFFFF;
    }
    public int getA() { return a & 0xFF; }
    public int getP() {
        int sum=0;
        for(Flags.ProcessorStatusFlags value : flags.getProcessorStatusFlagArray()){
            sum = sum + value.getVal();
        }
        return sum;
    }
    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }
    private void compare(int a, int value8bit) {
        setZeroNegativeFlag(a - value8bit);
        if(a >= value8bit){
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,1);
        } else {
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,0);
        }
    }

}

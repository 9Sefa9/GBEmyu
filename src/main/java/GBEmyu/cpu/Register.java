package GBEmyu.cpu;

import GBEmyu.Bus;

import java.util.logging.Level;

public class Register {
    //register
    private int a; // accumulator
    private int x;
    private int y;
    private int p; //status register
    private int pc=0; //Program counter
    private int sp=0; //Stack Pointer

    private Flags flags;
    private Bus bus;
    private CPU cpu;
    public Register(CPU cpu, Bus bus, Flags flags){
        this.flags = flags;
        this.bus = bus;
        this.cpu = cpu;
    }


    // Operations according to opcode
    public void lda(int value8bit) {
        setA(value8bit);
        setZeroNegativeFlag(getA());

    }
    public void ldx(int value8bit) {
        setX(value8bit);
        setZeroNegativeFlag(getX());

    }
    public void ldy(int value8bit) {
        setY(value8bit);
        setZeroNegativeFlag(getY());

    }
    public void sta(int address){
        bus.write(address, getA());
    }
    public void stx(int address){
        bus.write(address, getX());
    }
    public void sty(int address){
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
        setZeroNegativeFlag(y);
        setA(y);
    }
    public void nop(){
        //keine operation.
    }
    public void ora(int value){
        int a = value | getA();
        setZeroNegativeFlag(a);
        setA(a);
    }
    public void and(int value){
        int o = value & getA();
        setZeroNegativeFlag(o);
        setA(o);
    }
    public void eor(int value){
        int o = value ^ getA();
        setZeroNegativeFlag(o);
        setA(o);
    }
    public void clc(){ flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,0); }
    public void cli(){ flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.INTERRUPTDISABLE,0); }
    public void clv(){ flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.OVERFLOW,0); }
    public void sec(){ flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.CARRY,1); }
    public void sed(){ flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.DECIMALMODE,1); }
    public void cld(){
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.DECIMALMODE,0);
    }
    public void sei(){
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.INTERRUPTDISABLE,1);
    }
    public void brk(){
        //@todo kann probleme verursachen.
        push16(getPC());
        php();
        sei();
        setPC(read16(0xFFFE));
    }
    public void php(){
        //@todo kann probleme verursachen.
        push(getP());
    }
    public void dex(){
        setX(getX()-1);
        setZeroNegativeFlag(getX());
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



    private void setZeroNegativeFlag(int v) {
        if((v & 0xFF) == 0){
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.ZERO,1);
        }

        if(((v & 0x80) != 0)){
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.NEGATIVE,1);
        }

    }
    // Read16 reads two bytes using Read to return a double-word value
    private int read16(int address){
        int lo = cpu.getInstructions()[address & 0xFFFF];
        int hi = cpu.getInstructions()[(address & 0xFFFF)+1];
        return hi <<8 | lo;
    }
    // read16bug emulates a 6502 bug that caused the low byte to wrap without
// incrementing the high byte
    private int read16bug(int address){
        int a = address & 0xFFFF;
        int b = a | ((a +1) & 0xFFFF);
        int lo = cpu.getInstructions()[a];
        int hi = cpu.getInstructions()[b];
        return (hi & 0xFFFF) <<8 | (lo & 0xFFFF);
    }
    // push pushes a byte onto the stack
    private void push(int value){
       bus.write(0x100|(getSP() & 0xFFF),value);
        decrementSP();
    }
    // pull pops a byte from the stack
    private int pull(){
        incrementSP();
        return bus.read(0x100|getSP());
    }
    private void push16(int value){
        int hi = (value << 8);
        int lo = (value & 0xFF);
        push(hi);
        push(lo);
    }
    private int pull16(){
       int lo = pull() & 0xFFFF;

       int hi = pull() & 0xFFFF;

       return hi<<8 | lo;
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
    public void incrementSP() { this.sp = (sp + 1) & 0xFFFF; }
    public void decrementSP() { this.sp = (sp - 1) & 0xFFFF; }
    public void setPC(int pc){this.pc = (pc & 0xFFFF);}
    public void setSP(int sp) {
        this.sp = (sp& 0x100);
    }
    public void setA(int a) { this.a = (a & 0x100); }
    public void setX(int x) {
        this.x = (x & 0x100);
    }
    public void setY(int y) {
        this.y = (y & 0x100);
    }
    public void setP(int p) {
        this.p = (p & 0x80);
    }

    //Register getters
    public int getSP() {
        return this.sp;
    }
    public int getPC(){
        return this.pc;
    }
    public int getA() { return a; }
    public int getP() {
        int sum=0;
        for(Flags.ProcessorStatusFlags value : Flags.ProcessorStatusFlags.values()){
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

    @Override
    public String toString(){
        GBEmyu.utilities.Logger.LOGGER.log(Level.INFO,"Registers::: A "+a+" X "+x+" Y "+y+" P "+p+" PC "+pc+" SP "+sp);
        return "";
    }
}

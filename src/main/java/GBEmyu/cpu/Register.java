package GBEmyu.cpu;

import com.sun.media.jfxmedia.logging.Logger;

import java.util.logging.Level;
//TODO Komplett umkrempeln... funktionen rauschmeisen bzw transpotieren zu OPCODERbuilder
public class Register {
    //register
    private int a; // accumulator
    private int x;
    private int y;
    private int p; //status register
    private int s;
    private int pc=0; //Program counter
    private int sp=0; //Stack Pointer

    //AddressMode - wird von den Opcodes eingesetzt. Und in der CPU Loop.  Siehe http://obelisk.me.uk/6502/reference.html
    private Flags flags;

    public Register(Flags flags){
    this.flags = flags;

    }

    //Register setters
    public void incrementPC() {
        this.pc = (pc + 1) & 0xFFFF; //sicher gehen, dass er 0xFFFF nicht überschreitet.
    }
    public void incrementSP() {

        this.sp = (sp + 1) & 0xFFFF;
    }
    public void setA(int a) {
        this.a = (a & 0x100);
    }
    public void setX(int x) {
        this.x = (x & 0x100);
    }
    public void setY(int y) {
        this.y = (y & 0x100);
    }
    public void setP(int p) {
        this.p = p;
    }
    public void setS(int s) {
        this.s = s;
    }

    //Register getters
    public int getSP() {
        return this.sp;
    }
    public int getPC(){
        return this.pc;
    }
    public int getA() {
        return a;
    }
    public int getS() {
        return s;
    }
    public int getP() {
        return p;
    }
    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }

    // Operations according to opcode
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
    public void setSEI(){
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.INTERRUPTDISABLE,1);
    }

    public void setCLD(){
        flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.DECIMALMODE,0);
    }

    public void setLDA(int value8bit) {
        setA(value8bit);
        if(getA()==0)
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.ZERO,1);

        if(((getA() >> 7) & 1) == 1)
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.NEGATIVE,1);

    }
    //decreae X and set flags appropriately
    public void setDEX(){
        setX(getX()-1);

        if(getX()==0)
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.ZERO,1);

        if(((getX() >> 7) & 1) == 1)
            flags.setProcessorStatusFlag(Flags.ProcessorStatusFlags.NEGATIVE,1);
    }



    @Override
    public String toString(){
        GBEmyu.utilities.Logger.LOGGER.log(Level.INFO,"Registers::: "+" A "+a+" X "+x+" Y "+y+" P "+p+" S "+s+" PC "+pc+" SP "+sp);
        return "";
    }
}

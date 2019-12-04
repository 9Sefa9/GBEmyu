package GBEmyu.cpu;

import com.sun.media.jfxmedia.logging.Logger;

import java.util.logging.Level;

public class Register {
    //register
    private int a; // accumulator
    private int x;
    private int y;
    private int p; //status register
    private int s;
    private int pc; //Program counter
    private int sp; //Stack Pointer



    private enum Flags{
        CARRY(0),
        DECIMALMODE(0),
        INTERRUPTDISABLE(0),
        NEGATIVE(0),
        OVERFLOW(0),
        ZERO(0);

        private int val;
        public int getVal(){
            return this.val;
        }
        public void setVal(int newV){
            this.val = newV;
        }
        Flags(int val) {
         this.val = val;
        }
    }
    private Flags[] flags;
    //setters
    public void setPc(int val) {
        this.pc = val & 0xFFFF; //65535
        this.pc = (pc + 1) & 0xFFFF; //sicher gehen, dass er 0xFFFF nicht überschreitet.
    }
    public void setSp(int sp) {
        this.sp = sp & 0xFFFF;
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

    //set interrupt disable Opcode: $78
    public void setSEI(){
        setFlag(Flags.INTERRUPTDISABLE,1);
    }
    //clear decimal mode Opcode: $D8
    public void setCLD(){
        setFlag(Flags.DECIMALMODE,0);
    }
    //load accumulator , loads 1 byte of memory into the accumulato setting the zero and negative flags as appropriate
    public void setLDA(int value8bit) {
        setA(value8bit);
        if(getA()==0)
            setFlag(Flags.ZERO,1);

        if(((getA() >> 7) & 1) == 1)
            setFlag(Flags.NEGATIVE,1);

    }
    private void setFlag(Flags currentFlag, int value) {
        for (Flags f : this.flags){
            if(f.name().equals(currentFlag.name())){
                f.setVal(value);
                return;
            }
        }
    }

    //getters
    public int getSp() {
        return sp;
    }
    public int getPc(){
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

    @Override
    public String toString(){
        Logger.logMsg(Logger.INFO,"Registers::: "+" A "+a+" X "+x+" Y "+y+" P "+p+" S "+s+" PC "+pc+" SP "+sp);
        return "";
    }
}

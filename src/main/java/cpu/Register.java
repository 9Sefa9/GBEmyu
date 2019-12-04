package cpu;

public class Register {
    //register
    private int a;
    private int x;
    private int y;
    private int p;
    private int s;
    private int pc; //Program counter
    private int sp; //Stack Pointer

    //der momentane Flag
    private Flags currentFlag;



    public enum Flags{
        carry,
        decimalMode,
        interruptDisable,
        negative,
        overflow,
        zero
    }

    //setters
    public void setPc(int val) {
        this.pc = val & 0xFFFF; //65535
        this.pc = (pc + 1) & 0xFFFF; //sicher gehen, dass er 0xFFFF nicht überschreitet.
    }
    public void setSp(int sp) {
        this.sp = sp & 0xFFFF;
    }
    public void setA(int a) {
        this.a = a;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setP(int p) {
        this.p = p;
    }

    public void setS(int s) {
        this.s = s;
    }
    private void setCurrentFlag(Flags currentFlag) {
        this.currentFlag = currentFlag;
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
    public Flags getCurrentFlag() {
        return currentFlag;
    }



}

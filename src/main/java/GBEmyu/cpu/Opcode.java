package GBEmyu.cpu;

interface Action{

    //will be overriden in OpcodeBuilder because every opcode has its own functionality
    //einige Modi brauchen einen oder mehrere operanden. z.B um LDA 10
    void operation(int[] operand);
}
public class Opcode implements Action {
    private String opcodeName;
    private int hexAddress;
    private int cycle;
    private Flags.AddressModes  addressMode;

    public Opcode(Flags.AddressModes addressMode, String opcodeName, int hexAddress, int cycle){
        this.opcodeName = opcodeName;
        this.hexAddress = hexAddress;
        this.addressMode = addressMode;
        this.cycle = cycle;
    }
    public int getCycle(){
        return this.cycle;
    }
    public int getHexAddress(){
        return this.hexAddress;
    }
    public String getOpcodeName(){
        return this.opcodeName;
    }
    public Flags.AddressModes  getAddressMode(){
        return this.addressMode;
    }

    @Override
    public void operation(int []operand) {
        //wird in OpcodeBuilder gefüllt. Operand parameter kann verwendet werden, falls die jeweillige operation es braucht.

        //will be filled in OpcodeBuilder, falls die jeweilige operation einen operant benötigt.
    }
}

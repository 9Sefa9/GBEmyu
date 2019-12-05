package GBEmyu.cpu;

interface Action{

    //will be overriden in OpcodeBuilder because every opcode has its own functionality
    void operation();
}
public class Opcode implements Action {
    private String opcodeName;
    private int hexAddress;
    private Register.AddressModes addressMode;
    public Opcode(Register.AddressModes addressMode, String opcodeName, int hexAddress){
        this.opcodeName = opcodeName;
        this.hexAddress = hexAddress;
        this.addressMode = addressMode;
    }
    public int getHexAddress(){
        return this.hexAddress;
    }
    public String getOpcodeName(){
        return this.opcodeName;
    }
    public Register.AddressModes getAddressMode(){
        return this.addressMode;
    }
    @Override
    public void operation() {
        //will be filled in OpcodeBuilder.
    }
}

package GBEmyu.cpu;

interface Action{

    //will be overriden in OpcodeBuilder because every opcode has its own functionality
    void operation();
}
public class Opcode implements Action {
    private String opcodeName;
    private int hexAddress;

    public Opcode(String opcodeName, int hexAddress){
        this.opcodeName = opcodeName;
        this.hexAddress = hexAddress;
    }
    public int getHexAddress(){
        return this.hexAddress;
    }
    public String getOpcodeName(){
        return this.opcodeName;
    }
    @Override
    public void operation() {
        //will be filled in OpcodeBuilder.
    }
}

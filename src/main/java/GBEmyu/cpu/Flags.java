package GBEmyu.cpu;

public class Flags {
    //Objects of enums
    private ProcessorStatusFlags[] processorStatusFlags;

    //enums
    public enum AddressModes{
        HLT,
        IMPLICIT,
        ACCUMULATOR,
        IMMEDIATE,
        ZEROPAGE,
        ZEROPAGEX,
        ZEROPAGEY,
        RELATIVE,
        ABSOLUTE,
        ABSOLUTEX,
        ABSOLUTEY,
        INDIRECT,
        INDIRECTX,
        INDIRECTY
    }
    public enum ProcessorStatusFlags{
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
        ProcessorStatusFlags(int val) {
            this.val = val;
        }
    }

    public Flags(){
        this.processorStatusFlags = ProcessorStatusFlags.values();
    }

    //getter

    //find and set the flag. wichtig für die einzelnen Opcode funktionen.
    public void setProcessorStatusFlag(ProcessorStatusFlags currentFlag, int value) {
        for (ProcessorStatusFlags p : this.processorStatusFlags){
            if(p.name().equals(currentFlag.name())){
                p.setVal(value);
                return;
            }
        }
    }


}

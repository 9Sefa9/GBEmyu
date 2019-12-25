package GBEmyu.cpu;

public class Flags {
    //Objects of enums
    private ProcessorStatusFlags[] processorStatusFlags;
    private Interrupt interruptFlags;



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
        NEGATIVE(0),
        OVERFLOW(0),
        BREAKCOMMAND(0),
        DECIMALMODE(0),
        INTERRUPTDISABLE(0),
        ZERO(0),
        CARRY(0),
        UNKNOWN(0);
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
    public enum Interrupt{
        interruptNone,
        interruptNMI,
        interruptIRQ;
    }

    public Flags(){
        this.processorStatusFlags = ProcessorStatusFlags.values();
        this. interruptFlags = Interrupt.interruptNone;
    }

    //find and set the flag. wichtig für die einzelnen Opcode funktionen.
    public void setProcessorStatusFlag(ProcessorStatusFlags currentFlag, int value) {
        Flags.ProcessorStatusFlags.valueOf(currentFlag.name()).setVal(value);
    }
    public int getProcessorStatusFlag(ProcessorStatusFlags currentFlag) {
        return Flags.ProcessorStatusFlags.valueOf(currentFlag.name()).getVal();
    }
    public ProcessorStatusFlags[] getProcessorStatusFlagArray() {
        return Flags.ProcessorStatusFlags.values();
    }
    public void setInterruptFlags(Interrupt flag){
        this.interruptFlags = flag;
    }
    public Interrupt getInterruptFlag() {
        return this.interruptFlags;
    }


}

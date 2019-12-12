package GBEmyu;

import GBEmyu.cpu.CPU;

public class Bus {
    private CPU cpu;
    private MemoryMap memoryMap;


    public void link(CPU cpu, MemoryMap memoryMap) {
        this.cpu = cpu;
        this.memoryMap = memoryMap;
    }

    public void write(int address, int val) {
        this.memoryMap.write(address,val);


    }

    public int read(int address) {
        return this.memoryMap.read(address);

    }

}




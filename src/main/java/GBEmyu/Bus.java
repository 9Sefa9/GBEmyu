package GBEmyu;

import GBEmyu.cpu.CPU;

public class Bus {
    private CPU cpu;
    private Mapper0 mapper0;


    public void link(CPU cpu, Mapper0 mapper0) {
        this.cpu = cpu;
        this.mapper0 = mapper0;
    }

    public void write(int address, int val) {

        this.mapper0.write(address,val);


    }

    public int read(int address) {

        return this.mapper0.read(address);

    }

}




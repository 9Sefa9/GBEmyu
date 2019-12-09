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
        // TODO: Create this method
        cpu.incrementCycle(1);
        this.memoryMap.getMemory()[address] = (val & 0x100);

    }

    public int read(int address) {
        // TODO: Create this method
        cpu.incrementCycle(1);
        return this.memoryMap.getMemory()[address];
    }
}

       /* try {
            if (address16 < 0x800) {
                return this.memoryMap.getMemory()[address16];
            } else {
                throw new Exception("Ram Overflow - set "+this.memoryMap.getMemory()[address16]+ " to -1");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        //	else{
        //		return readRamAgain(address16);
        //	}
        return -1;
    }
    // lesen http://nemulator.com/files/nes_emu.txt
	/*private int readRamAgain(int address16) {
		if( address16 < 0x2000)
			return this.memory[address16 & 0x7FF];
		//else if(address16 <= 0x3FFF)
			//return ppu_read(address16);
	//	else if(address16 >= 0x4000 && address16 <= 0x4017)
		//	return apu_read(address16);

		//else	return  readRamFP
	}*/


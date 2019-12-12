package GBEmyu;

import GBEmyu.utilities.Helper;
import GBEmyu.utilities.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
//TODO read und write entsprechend der manual programmieren...
public class MemoryMap {

	//Memory Map hat ein Speicher von 2^16. Also 65535.
	private int[] memory = new int[0x10000];
	//private Mapper mapper;
	//private APU apu;
	//private PPU ppu;

	
	public MemoryMap() {
		createMemory();

	}

	private void createMemory() {
		//setze alle werte mit 0 von 0(inkl) bis 65535(inkl ?.)
		Helper.forSet(memory,0,memory.length,0);
        Logger.LOGGER.log(Level.INFO,"Memory Map created :: "+String.format("0x%04X", memory.length));
	}

	public int[] getMemory(){
		return this.memory;
	}

	public void write(int address, int val) {
		getMemory()[address] = (val & 0x100);
	}

	public int read(int address) {
		return getMemory()[(address & 0x100)];
	}
}

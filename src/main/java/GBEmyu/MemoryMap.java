package GBEmyu;

import GBEmyu.utilities.Helper;
import GBEmyu.utilities.Logger;

import java.util.logging.Level;
//TODO read und write entsprechend der manual programmieren...
public class MemoryMap {

	//Memory Map hat ein Speicher von 2^16. Also 65535.
	private int[] cpuMemoryMap;
	//private Mapper mapper;
	//private APU apu;
	//private PPU ppu;

	
	public MemoryMap() {
	    //erstellt komplette Speicher.
        cpuMemoryMap = new int[0x10000];
		createMemory();

	}

	private void createMemory() {
		//setze alle werte mit 0 von 0(inkl) bis 65535(inkl ?.)
		Helper.forSet(cpuMemoryMap,0,cpuMemoryMap.length,0);
        Logger.LOGGER.log(Level.INFO,"Memory Map created :: "+String.format("0x%04X", cpuMemoryMap.length));
	}

	private int[] getCpuMemoryMap(){
		return this.cpuMemoryMap;
	}
    public int read(int address) {
	    if(address < 0x2000){
	        //RAM
            return getCpuMemoryMap()[address];

        }else if( address <0x4020){
	        //I/O Registers

            return getCpuMemoryMap()[address];
        }
        else if( address <0x6000){
            //Expansion ROM
            return getCpuMemoryMap()[address];
        }
        else if(address <0x8000){
            //SRAM
            return getCpuMemoryMap()[address];
        }
        else if(address <0x10000){
            //PRG-RAM
            return getCpuMemoryMap()[address];
        }
        //ansonsten exception.
        return getCpuMemoryMap()[address];

    }
//#######################################-----RAM SECTION-----#####################################
    // Memory locations $0000-$07FF are mirrored three times at $0800-$1FFF. This means that, for example,
    // any data WRITTEN TO(!!) $0000 will also be written to $0800, $1000 and $1800
    private void mirrorRam(int address, int value) {
	    // 0x123 => 0x923 => B23 => 1923
        int ptr = 0x800;
        //0x800
        getCpuMemoryMap()[(address+ptr) & 0x800] = value;
        ptr += 0x200;
        //0x1000
        getCpuMemoryMap()[(address+ptr) & 0x800] = value;
        //0x1800
        ptr+=0x800;
        getCpuMemoryMap()[(address+ptr) & 0x800] = value;

    }
//#################################################################################################
    private void mirrorIO() {

    }
//#################################################################################################
    public void write(int address, int val) {
        if(address < 0x2000){
            //RAM
            getCpuMemoryMap()[address] = val;
            mirrorRam(address,val);

        }else if( address <0x4020){
            //I/O Registers
          //  mirrorIO();

        }
        else if(address <0x6000){
            //Expansion ROM

        }
        else if(address <0x8000){
            //SRAM

        }
        else if(address <0x10000){
            //PRG-RAM

        }
	}




}

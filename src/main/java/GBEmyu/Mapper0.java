package GBEmyu;

import GBEmyu.utilities.Helper;
import GBEmyu.utilities.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

//TODO read und write entsprechend der manual programmieren...
public class Mapper0 {

	//Memory Map hat ein Speicher von 2^16. Also 65535.
    private int[] allInstructions;
    private int[] cpuMemoryMap;
    private NesUnit unit;
	//private Mapper0 mapper;
	//private APU apu;
	//private PPU ppu;

	
	public Mapper0() {
	    //erstellt komplette Speicher.
        cpuMemoryMap = new int[0x10000];
		createMemory();
	}
    // read file
    public void load(final String nesPath) {
        File rom=null;
        byte[] romBuffer=null;
        FileInputStream fis=null;

        try {
            rom = new File(nesPath);
            romBuffer = new byte[(int) rom.length()];
            fis = new FileInputStream(rom);
            fis.read(romBuffer);

        } catch (IOException e) {
            LOGGER.log (Level.WARNING, "Failed to open file!");
            e.printStackTrace();
        }
        finally{
            try{
                if(fis != null)
                    fis.close();
            }catch (IOException i){
                i.printStackTrace();
            }
        }

        int[] allInstructions = new int[romBuffer.length];
        for (int i = 0; i < romBuffer.length; i++) {
            allInstructions [i] = (short) (romBuffer[i] & 0xFF);
        }
        //wichtig um später drauf zugreifen zu können.
        this.allInstructions = allInstructions ;
        //INes Format anwenden und zugreifbar machen.
        this.unit = new NesUnit(this.allInstructions);
    }


    /*
    copyArrayElements: function(src, srcPos, dest, destPos, length) {
        for (var i = 0; i < length; ++i) {
            dest[destPos + i] = src[srcPos + i];
        }
    },
*/

//#######################################-----RAM SECTION-----#####################################

    private void createMemory() {
    //setze alle werte mit 0 von 0(inkl) bis 65535(inkl ?.)
    Helper.forSet(cpuMemoryMap,0,cpuMemoryMap.length,0);
    Logger.LOGGER.log(Level.INFO,"Memory Map created :: "+String.format("0x%04X", cpuMemoryMap.length));
}
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
            write(address,val);
        }
    }
    private void mirrorIO() {

    }
//#################################################################################################

    public int[] getInstructions() {
        return this.allInstructions;
    }
    private int[] getCpuMemoryMap(){
        return this.cpuMemoryMap;
    }
}

package GBEmyu;

import GBEmyu.cpu.Flags;
import GBEmyu.utilities.Helper;
import GBEmyu.utilities.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;

//TODO read und write entsprechend der manual programmieren...
public class Mapper0 {

	//Memory Map hat ein Speicher von 2^16. Also 65535.
    private int[] allInstructions;
    private int[] cpuMemoryMap;
    private Cartridge cartridge;
	//private Mapper0 mapper;
	//private APU apu;
	//private PPU ppu;

	
	public Mapper0(final String nesPath) {
	    //erstellt komplette Speicher.
        cpuMemoryMap = new int[0x10000];
        Logger.LOGGER.log(Level.INFO,"Create Memory Map...:: "+String.format("0x%04X", cpuMemoryMap.length));
		this.cartridge = new Cartridge(new NesUnit(nesPath));
        Logger.LOGGER.log(Level.INFO,"NesUnit created :: "+this.cartridge);
		writePRG();
        Logger.LOGGER.log(Level.INFO,"PRG Mapping done.");
		writeCHR();
        Logger.LOGGER.log(Level.INFO,"CHR Mapping done.");
        Logger.LOGGER.log(Level.INFO,"Memory Map succesfully created!");
		//TODO LoadBatterRam()

	}


    private void writePRG() {
	    if(this.cartridge.getPrgRomSize()>2){
            try {
                throw new Exception("PRG-ROM SIZE OVER 2 !");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

	    if(this.cartridge.getPrgRomSize()>1){
            int[] prgBank0 = this.cartridge.getPrgBanks().get(0);
            int[] prgBank1 = this.cartridge.getPrgBanks().get(1);
            // Load the two first banks into memory.
            for (int i=0; i<prgBank0.length;i++){
                write(0x8000+i,prgBank0[i]);
            }
            for (int i=0; i<prgBank1.length;i++){
                write(0xC000+i,prgBank1[i]);
            }
        }else{
            // Load the one bank into both memory locations:
            int[] prgBank0 = this.cartridge.getPrgBanks().get(0);

            for (int i=0; i<prgBank0.length;i++){
                write(0x8000+i,prgBank0[i]);
            }
            for (int i=0; i<prgBank0.length;i++){
                write(0xC000+i,prgBank0[i]);
            }
        }


    }

    private void writeCHR() {
       /* SIEHE loadCHRROM unter :  https://github.com/bfirsh/jsnes/blob/master/src/mappers.js
       loadCHRROM: function() {
    // console.log("Loading CHR ROM..");
    if (this.nes.rom.vromCount > 0) {
      if (this.nes.rom.vromCount === 1) {
        this.loadVromBank(0, 0x0000);
        this.loadVromBank(0, 0x1000);
      } else {
        this.loadVromBank(0, 0x0000);
        this.loadVromBank(1, 0x1000);
      }
    } else {
      //System.out.println("There aren't any CHR-ROM banks..");
    }
  },
        */
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
            if(address <=0x2007 && address>0x2000) {
                mirrorIO(address, val);
            }

        }
        //TODO expansion ROm ist leer ?
        else if(address <0x6000){
            //Expansion ROM
            System.out.println("EROM");
            getCpuMemoryMap()[address] = val;
        }
        //TODO SRAM auch ?
        else if(address <0x8000){
            //SRAM
            System.out.println("SROM");
            getCpuMemoryMap()[address] = val;
        }
        else if(address <0x10000){
            //PRG-RAM
            getCpuMemoryMap()[address] = val;
        }
    }
    private void mirrorIO(int address, int value) {
        /*
        The memory mapped I/O registers are located at $2000-$401F. Locations $2000-$2007 are mirrored every 8 bytes
        in the region $2008-$3FFF and the remaining registers follow this mirroring.
        */

        //TODO Könnte probleme verursachen!
        int ptr = 8;
        int addressTemp = address;
        while(addressTemp+ptr<0x3FFF){
            addressTemp+=ptr;
            getCpuMemoryMap()[(addressTemp) & 0x401F] = value;

        }
    }
//#########################################--GETTERS--########################################################

    public int[] getInstructions() {
        return this.allInstructions;
    }
    private int[] getCpuMemoryMap(){
        return this.cpuMemoryMap;
    }
}

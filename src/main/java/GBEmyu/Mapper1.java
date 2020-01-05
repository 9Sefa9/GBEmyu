package GBEmyu;

import GBEmyu.utilities.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;

public class Mapper1 {
    private Cartridge cartridge;
    private final int[] cpuMemoryMap;
    int shiftRegister;
    int control;
    int prgMode;
    int chrMode;
    int prgBank;
    int chrBank0;
    int chrBank1;
    int []prgOffsets;
    int []chrOffsets;

    public Mapper1(final String nesPath) {
        //erstellt komplette Speicher.
        cpuMemoryMap = new int[0x10000];
       
        this.cartridge = new Cartridge(new NesUnit(nesPath));
        prgOffsets = new int[2];
        chrOffsets= new int[2];
        this.shiftRegister = 0x10;
        this.prgOffsets[1] = prgBankOffset(-1);
    }

    private int prgBankOffset(int index) {
        if (index >= 0x80) {
            index -= 0x100;
        }
        index %= /*len(m.PRG)*/ cartridge.getPrgRomSize()/ 0x4000;
        int offset = index * 0x4000;
        if (offset < 0) {
            offset += cartridge.getPrgRomSize();
        }
        return offset;
    }
    public int read(int address){
        if(address < 0x2000) {
            int bank = address / 0x1000;
            int offset = address % 0x1000;
            return cartridge.chrBanks.get(chrOffsets[bank])[chrOffsets[bank]+(offset&0xFFFF)];
        }
        else if(address >= 0x8000) {
            address = address - 0x8000
            bank := address / 0x4000
            offset := address % 0x4000
            return m.PRG[m.prgOffsets[bank]+int(offset)]
        }
        else if(address >= 0x6000){
            return m.SRAM[int(address)-0x6000]
        }

    }

}

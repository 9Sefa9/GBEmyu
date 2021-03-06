package GBEmyu;


import GBEmyu.utilities.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class NesUnit {
    //Alle Instructions aus dem Spiel
	private int[] allInstructions;
	//nur die HEaderInformationen
	private int[] headerInformation;
	//Eine Liste mit 1 oder 2 oder mehreren Banks mit jeweils einem Array fester länge.
	private List<int[]> prgBanks;
	private List<int[]> chrBanks;
	//wie vielemal 16KB gibt es ?
	private int prgRomSize;
	//wie viel mal 8KB gibt es ?
	private int chrRomSize;

    private int romControlByte;
    private int verticalMirroring;
    private int horizontalMirroring;
    private int batteryBackedRAM;
    private int trainer;
    private int fourScreen;
    private int mapperType;

    public NesUnit(String loadPath) {
        prgBanks = new ArrayList<>();
        chrBanks = new ArrayList<>();

		//Lade Spiel.
		load(loadPath);

		//bearbeite / entnehme die Informationen.
		process(this.allInstructions);
	}

    private void process(int[] allInstructions) {
        createNesHeader(allInstructions);
	    createPRGRom();
	    createCHRRom();
    }


    private void createNesHeader(int[] allInstructions) {
		//header identifizieren und initialisieren. Die ersten 16 Byte.
		headerInformation = new int[16];
		System.arraycopy(allInstructions, 0, headerInformation, 0, 16);

		prgRomSize = headerInformation[4];
		chrRomSize = headerInformation[5];
		romControlByte = headerInformation[6];

		//wenn bit ist 1,dann
        verticalMirroring = (romControlByte & 1) & 0xFF;

        //wenn bit ist 0, dann
        horizontalMirroring= (romControlByte & 1) & 0xFF;

        // indicates the presence of battery-backed RAM at memory locations $6000-$7FFF
        batteryBackedRAM = (romControlByte & 2) & 0xFF;
        //Indicates the presence of a 512-byte trainer at memory locations $7000-$71FF.
        trainer = (romControlByte & 4) & 0xFF;

        fourScreen = ((romControlByte & 8) & 0xFF) ;

        mapperType = (romControlByte >> 4) | (headerInformation[7] & 0xF0);

	}
    private void createPRGRom() {
        for (int i = 0; i < this.prgRomSize; i++) {
            this.prgBanks.add(i, new int[16384]);
        }
        //Initialisiere die ersten beiden banks.
        int offset = 16; //weil die ersten 16 header sind, fangen wir auch ab 16 an.
        for (int i = 0; i < getPrgRomSize(); i++) {
            for (int j = 0; j < 16384; j++) {
                if (offset + j >= allInstructions.length) {
                    break;
                } else {
                    this.prgBanks.get(i)[j] = (allInstructions[offset + j] & 0xFF);
                }
            }
            offset += 16384;
        }
    }
    private void createCHRRom() {
        /*
        // provide chr-rom/ram if not in file
	            if header.NumCHR == 0 {
		        chr = make([]byte, 8192)
	    }
         */
        for (int i = 0; i < this.chrRomSize; i++) {
            this.chrBanks.add(i, new int[8192]);
        }
        //Initialisiere die ersten beiden banks.
        int offset = 16; //weil die ersten 16 header sind.
        for (int i = 0; i < getPrgRomSize(); i++) {
            for (int j = 0; j < 8192; j++) {
                if (offset + j >= allInstructions.length) {
                    break;
                } else {
                    this.chrBanks.get(i)[j] = (allInstructions[offset + j] & 0xFF);
                }
            }
            offset += 8192;
        }
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
            Logger.LOGGER.log(Level.WARNING, "Failed to open file!");
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
    }

    public int getPrgRomSize(){
		return this.prgRomSize;
	}
	public int getChrRomSize(){
		return this.chrRomSize;
	}
	public int[] getHeaderInformation(){
		return this.headerInformation;
	}
    public List<int[]> getPrgBanks(){
	    return this.prgBanks;
    }
    public List<int[]> getChrBanks(){
        return this.chrBanks;
    }

    public int getRomControlByte() {
        return romControlByte;
    }

    public void setRomControlByte(int romControlByte) {
        this.romControlByte = romControlByte;
    }

    public int getVerticalMirroring() {
        return verticalMirroring;
    }

    public void setVerticalMirroring(int verticalMirroring) {
        this.verticalMirroring = verticalMirroring;
    }

    public int getHorizontalMirroring() {
        return horizontalMirroring;
    }

    public void setHorizontalMirroring(int horizontalMirroring) {
        this.horizontalMirroring = horizontalMirroring;
    }

    public int getBatteryBackedRAM() {
        return batteryBackedRAM;
    }

    public void setBatteryBackedRAM(int batteryBackedRAM) {
        this.batteryBackedRAM = batteryBackedRAM;
    }

    public int getTrainer() {
        return trainer;
    }

    public void setTrainer(int trainer) {
        this.trainer = trainer;
    }

    public int getFourScreen() {
        return fourScreen;
    }

    public void setFourScreen(int fourScreen) {
        this.fourScreen = fourScreen;
    }

    public int getMapperType() {
        return mapperType;
    }

    public void setMapperType(int mapperType) {
        this.mapperType = mapperType;
    }
    public String toString(){
        return "CHR-ROM-SIZE: "+getChrRomSize()+" PRG-ROM-SIZE: "+prgRomSize+" ";
    }
}

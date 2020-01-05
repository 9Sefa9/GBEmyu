package GBEmyu;

import java.util.List;

public class Cartridge{
    public List<int[]> prgBanks;
    public List<int[]> chrBanks;
    //wie vielemal 16KB gibt es ?
    public int prgRomSize;
    //wie viel mal 8KB gibt es ?
    public int chrRomSize;

    // private int romControlByte;
    public int verticalMirroring;
    public int horizontalMirroring;
    public int batteryBackedRAM;
    //private int trainer;
    //private int fourScreen;
    public int mapperType;

    public Cartridge(NesUnit nesUnit) {
        this.prgRomSize = nesUnit.getPrgRomSize();
        this.chrRomSize = nesUnit.getChrRomSize();
        this.prgBanks = nesUnit.getPrgBanks();
        this.chrBanks = nesUnit.getChrBanks();
        this.mapperType = nesUnit.getMapperType();
        this.verticalMirroring = nesUnit.getVerticalMirroring();
        this.horizontalMirroring = nesUnit.getHorizontalMirroring();
        this.batteryBackedRAM = nesUnit.getBatteryBackedRAM();
    }
    public int getPrgRomSize(){
        return this.prgRomSize;
    }
    public int getChrRomSize(){
        return this.chrRomSize;
    }
    /*
    public int[] getHeaderInformation(){
        return this.headerInformation;
    }
    */

    public List<int[]> getPrgBanks(){
        return this.prgBanks;
    }
    public List<int[]> getChrBanks(){
        return this.chrBanks;
    }
    /*
    public int getRomControlByte() {
        return romControlByte;
    }

    public void setRomControlByte(int romControlByte) {
        this.romControlByte = romControlByte;
    }
    */
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

    /*public int getTrainer() {
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
    }*/

    public int getMapperType() {
        return mapperType;
    }

    public void setMapperType(int mapperType) {
        this.mapperType = mapperType;
    }
}

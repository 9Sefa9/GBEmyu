package GBEmyu;


import java.util.ArrayList;
import java.util.List;

public class NesUnit {
    //Alle Instructions aus dem Spiel
	private int[] allInstructions;
	//nur die HEaderInformationen
	private int[] headerInformation;
	//Eine Liste mit 1 oder 2 oder mehreren Banks mit jeweils einem Array fester länge.
	private List<int[]> prgBanks;
	//wie vielemal 16KB gibt es ?
	private int prgRomSize;
	//wie viel mal 8KB gibt es ?
	private int chrRomSize;


	public NesUnit(int []allInstructions) {
        prgBanks = new ArrayList<>();
		this.allInstructions = allInstructions;
		process(this.allInstructions);
		createNesHeader(this.allInstructions);


	}

    private void process(int[] allInstructions) {
        createNesHeader(allInstructions);
	    createPRGRom();
    }

    private NesUnit createNesHeader(int[] allInstructions) {
		//header identifizieren und initialisieren. Die ersten 16 Byte.
		headerInformation = new int[16];
		System.arraycopy(allInstructions, 0, headerInformation, 0, 16);

		prgRomSize = headerInformation[4];
		chrRomSize = headerInformation[5];
		//ich belasse es erstmal so. https://wiki.nesdev.com/w/index.php/INES#Flags_7
		return this;
	}
    private void createPRGRom() {
        for (int i = 0; i < this.prgBanks.size(); i++) {
            this.prgBanks.set(i, new int[16384]);
        }
        //Initialisiere die ersten beiden banks.
        int offset = 16; //weil die ersten 16 header sind.
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
   /* public void createCHRRom() {
        for (int i = 0; i < this.prgBanks.size(); i++) {
            this.prgBanks.set(i, new int[16384]);
        }
        //Initialisiere die ersten beiden banks.
        int offset = 16; //weil die ersten 16 header sind.
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
    }*/
        /*
         if(getPrgRomSize()>1){
            //Initialisiere die ersten beiden banks.
            int offset = 16; //weil die ersten 16 header sind.
            for(int i = 16; i<16384;i++){
                write(0x8000+i,allInstructions[i]);
            }
            //this.loadRomBank(0, 0x8000);
            for(int i = 0; i<16384;i++){
                write(0xc000+i,allInstructions[i]);
            }
            //  this.loadRomBank(1, 0xc000);
        } else {
            // Load the one bank into both memory locations:
            this.loadRomBank(0, 0x8000);
            this.loadRomBank(0, 0xc000);
        }

         */
        //load

	public int getPrgRomSize(){
		return this.prgRomSize;
	}
	public int getChrRomSize(){
		return this.chrRomSize;
	}
	public int[] getHeaderInformation(){
		return this.headerInformation;
	}


}

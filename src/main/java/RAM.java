import java.util.Arrays;

public class RAM {

	private int[] internalRam = new int[0x800];
	//private Mapper mapper;
	//private APU apu;
	//private PPU ppu;

	
	public RAM() {
		Arrays.fill(internalRam, 0xff);
	}


	public void write(int addr, int val) {
		// TODO: Create this method
		
	}


	public int read(int addr) {
		// TODO: create this method
		return 0;
	}
}

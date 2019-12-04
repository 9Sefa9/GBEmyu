package GBEmyu;

import GBEmyu.cpu.CPU;
import GBEmyu.utilities.Conversion;

public class Main {

	public static void main(String args[]) {
		Main emu = new Main();
		emu.start();

		
	}

	private void start() {
		RAM ram = new RAM();
		int[] code = Conversion.readRom(getClass().getResource("/nestest.nes").getPath());
		CPU cpu = new CPU(ram,code);
		//cpu.init();
		cpu.init();
	}
}

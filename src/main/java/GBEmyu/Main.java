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

		CPU cpu = new CPU(ram);
		int[] code = Conversion.readRom(getClass().getResource("/nestest.nes").getPath());
		cpu.init();
		cpu.run(code);
	}
}

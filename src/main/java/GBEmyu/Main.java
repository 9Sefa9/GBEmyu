package GBEmyu;

import GBEmyu.cpu.CPU;
import GBEmyu.utilities.Helper;

public class Main {

	public static void main(String args[]) {
		Main emu = new Main();
		emu.start();
	}

	private void start() {
		MemoryMap memoryMap = new MemoryMap();
		int[] instructions = Helper.readRom(getClass().getResource("/nestest.nes").getPath());

		CPU cpu = new CPU(memoryMap,instructions);
		cpu.init();
	}
}

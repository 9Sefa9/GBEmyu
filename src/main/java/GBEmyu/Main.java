package GBEmyu;

import GBEmyu.cpu.CPU;
import GBEmyu.utilities.Helper;

public class Main {

	public static void main(String args[]) {
		Main emu = new Main();
		emu.start();
	}

	private void start() {

		int[] instructions = Helper.readRom(getClass().getResource("/nestest.nes").getPath());

        MemoryMap memoryMap = new MemoryMap();
		CPU cpu = new CPU(instructions);
		Bus bus = new Bus();
		bus.link(cpu,memoryMap);

		cpu.start();
	}
}

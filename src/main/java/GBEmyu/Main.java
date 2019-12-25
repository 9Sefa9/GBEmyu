package GBEmyu;

import GBEmyu.cpu.CPU;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;

import static GBEmyu.utilities.Logger.LOGGER;


public class Main {

	public static void main(String args[]) {
		Main emu = new Main();
		emu.start();
	}

	private void start() {
		Mapper0 mapper0 = new Mapper0(getClass().getResource("/nestest.nes").getPath());
		Bus bus = new Bus();

		CPU cpu = new CPU(bus);

		bus.link(cpu, mapper0);

		cpu.start();
	}
	// read rom method - provisorisch TODO in naher zukunf ändern.
	public int[] readINes(final String nesPath) {

		File rom = new File(nesPath);
		byte[] romBuffer = new byte[(int) rom.length()];
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(rom);
			fis.read(romBuffer);
		} catch (IOException e) {
			LOGGER.log (Level.WARNING, "Failed to open file!");
			e.printStackTrace();
		}

		int[] instructions = new int[romBuffer.length];
		for (int i = 0; i < romBuffer.length; i++) {
			instructions[i] = (short) (romBuffer[i] & 0xFF);
		}
		return instructions;
	}
}

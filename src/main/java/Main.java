import Utilities.Conversion;
import cpu.CPU;

public class Main {

	public static void main(String args[]) {
		Main emu = new Main();
		emu.start();

		
	}

	private void start() {
		RAM wRam = new RAM();

		CPU cpu = new CPU(wRam);
		int[] code = Conversion.readRom(getClass().getResource("/nestest.nes").getPath());
		cpu.init();
		cpu.run(code);
	}
}

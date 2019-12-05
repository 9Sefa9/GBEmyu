package GBEmyu.utilities;

import sun.util.logging.PlatformLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class Helper {

	/*
	System.out.println(Integer.decode("0x4d2"))    // output 1234
	//and vice versa
	Syst

	 */
	// conversion toHex methods
	public static String toHex(int number) {
		return Integer.toHexString(number);
	}
	public static String toHex(long number) {
		return Long.toHexString(number).toUpperCase();
	}

	//helper function to iterate through
	public static void forSet(int[] address, int from, int to){

		for(int i = from; i<to; i++){
			address[i] = i;
		}
	}
	
	// read rom method
	public static int[] readRom(final String path) {
		
		File rom = new File(path);
		byte[] rom_buf = new byte[(int) rom.length()];
		FileInputStream stream;
		try {
			stream = new FileInputStream(rom);
			stream.read(rom_buf);
		} catch (IOException e) {
			LOGGER.log (Level.WARNING, "Failed to open file!");
			e.printStackTrace();
		}
		int[] instructions = new int[rom_buf.length];
		
		for (int i = 0; i < rom_buf.length; i++) {
			instructions[i] = (short) (rom_buf[i] & 0xFF);
		}
		
		return instructions;
	}

}

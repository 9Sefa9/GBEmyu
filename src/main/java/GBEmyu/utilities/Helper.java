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
	public static void forSet(int[] address, int from, int to, int value){

		for(int i = from; i<to; i++){
			address[i] = value;
		}
	}

}

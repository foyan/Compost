package ch.hszt.kfh.compost.programs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestUtils {

	public static String readFile(String file) throws IOException {
		String c = "";
		File f = new File(file);
		BufferedReader r = new BufferedReader(new FileReader(f));
		String l;
		while ((l = r.readLine()) != null) {
			c += l + "\n";
		}
		r.close();
		return c;
	}

	
}

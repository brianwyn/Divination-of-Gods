package server.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriting {

	public static void writeToFile(String name, Exception e) {
		String filePath = "Data/FileWriter/" + name + ".txt";
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(filePath, true));
			bw.newLine();
			bw.newLine();
			bw.newLine();
			bw.write("A NEW ERROR HAS OCCURED: ");
			bw.newLine();
			bw.write("" + e + "");

			bw.newLine();
			bw.newLine();
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ioe2) {
				}
			}
		}
	}
}

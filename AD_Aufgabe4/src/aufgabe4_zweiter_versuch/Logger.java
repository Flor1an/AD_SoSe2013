package aufgabe4_zweiter_versuch;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

	File file;

	public Logger(String path) {
		File f = new File(path);
		this.file = f;
		try {
			new FileWriter(file, false); // reset file
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToFile(String message) {
		try {
			FileWriter writer = new FileWriter(file, true);

			writer.append(message + System.getProperty("line.separator"));

			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

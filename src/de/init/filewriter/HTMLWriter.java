package de.init.filewriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLWriter {

	public void writeFile(String filePath, String htmlContent) throws IOException {

		File file = new File(filePath);

		if (file.exists()) {
			file.delete();
			file.createNewFile();
		} else {

			file.createNewFile();
		}
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

		bufferedWriter.write(htmlContent);
		bufferedWriter.flush();
		bufferedWriter.close();

	}

}

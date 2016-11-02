package de.init.filereader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader {

	private volatile InputStream is;
	private volatile InputStreamReader isr;
	private volatile BufferedReader br;

	public String readFile(String path) throws IOException {

		StringBuffer stringBuffer = new StringBuffer();

		try {
			// open input stream test.txt for reading purpose.
			is = new FileInputStream(path);

			// create new input stream reader
			isr = new InputStreamReader(is);

			// create new buffered reader
			br = new BufferedReader(isr);

			String currentLine;

			// reads to the end of the stream
			while ((currentLine = br.readLine()) != null) {
				stringBuffer.append(currentLine + "\n");

			}
			return stringBuffer.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// releases resources associated with the streams
			if (is != null)
				is.close();
			if (isr != null)
				isr.close();
			if (br != null)
				br.close();
		}
		return null;
	}
}

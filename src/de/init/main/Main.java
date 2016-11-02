package de.init.main;

import java.io.IOException;

import de.init.converter.HTMLParser;
import de.init.filereader.FileReader;
import de.init.filewriter.HTMLWriter;
import de.init.usage.Constants;

/*
 * TODO alles datein im ordner parsen und alle html ersetzen, design bis auf 1x alle löschen, erste umbenennen.
 */
public class Main {

	public static void main(String[] args) {
		String path = "";

		if (args.length == 0) {
			showErrorMessageAndExit();
		} else {
			path = args[0];
			if (path.equals("-d")) {
				Constants.DIALOG_STATUS = Constants.Dialog.SHOW_DIALOG;
				try {
					path = args[1];
				} catch (Exception e) {
					showErrorMessageAndExit();
				}
			}
		}

		try {
			System.out.println("Current File is: " + path + ".");
			// read the file
			FileReader fileReader = new FileReader();
			String htmlFile = fileReader.readFile(path);

			// replace the important strings
			HTMLParser htmlParser = new HTMLParser();
			htmlFile = htmlParser.replaceDesignContent(htmlFile);
			System.out.println("String has been replaced.");

			// save the file
			HTMLWriter htmlWriter = new HTMLWriter();
			htmlWriter.writeFile(path, htmlFile);
			System.out.println("The new file has been created.");

		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		System.exit(0);
	}

	private static void showErrorMessageAndExit() {
		System.out.println("No files found.");
		System.exit(1);
	}
}

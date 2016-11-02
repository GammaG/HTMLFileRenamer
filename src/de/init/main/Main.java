package de.init.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import de.init.converter.HTMLParser;
import de.init.filereader.FileReader;
import de.init.filewriter.HTMLWriter;
import de.init.usage.FileSelector;

public class Main {

	public static void main(String[] args) {
		String path = "";

		if (args.length == 0) {
			Path currentRelativePath = Paths.get("");
			path = currentRelativePath.toAbsolutePath().toString();
		} else {
			try {
				path = args[0];
			} catch (Exception e) {
				showErrorMessageAndExit();
			}
		}

		try {
			System.out.println("Current Path is: " + path + ".");
			// read the files
			ArrayList<File> totalElements = new FileSelector().getElementsInTheGivenPath(path);
			System.out.println("Total items are " + totalElements.size());

			ArrayList<File> logoList = new ArrayList<File>();
			for (File file : totalElements) {
				if (file.getName().endsWith(".png")) {
					logoList.add(file);
					continue;
				}
				System.out.println("The current file is: " + file.getAbsolutePath());
				FileReader fileReader = new FileReader();
				String htmlFile = fileReader.readFile(file);

				// replace the important strings
				HTMLParser htmlParser = new HTMLParser();
				htmlFile = htmlParser.replaceDesignContent(htmlFile);
				System.out.println("String has been replaced.");

				// save the file
				HTMLWriter htmlWriter = new HTMLWriter();
				htmlWriter.writeFile(file.getAbsolutePath(), htmlFile);
				System.out.println("The new file has been created.");
			}
			for (int i = 0; i < logoList.size(); i++) {
				if (i == 0) {
					File file = new File(logoList.get(i).getPath());
					logoList.get(i).renameTo(file);
					continue;
				}
				logoList.get(i).delete();
			}

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

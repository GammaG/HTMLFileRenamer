package de.init.usage;

import java.io.File;
import java.util.ArrayList;

public class FileSelector {

	public ArrayList<File> getElementsInTheGivenPath(String path) {
		ArrayList<File> elementList = new ArrayList<File>();
		File directory = new File(path);
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException("The given path was no directory");
		}
		for (File file : directory.listFiles()) {
			if (file.getName().endsWith(".html") | file.getName().endsWith(".png")) {
				elementList.add(file);
			}
		}
		return elementList;
	}

}

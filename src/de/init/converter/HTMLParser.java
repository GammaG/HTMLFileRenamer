package de.init.converter;

import de.init.usage.Constants;

public class HTMLParser {

	public String replaceDesignContent(String htmlFile) {
		boolean test = htmlFile.contains(Constants.REGEX);

		return htmlFile.replaceAll(Constants.REGEX, Constants.REPLACEMENT);
	}

}

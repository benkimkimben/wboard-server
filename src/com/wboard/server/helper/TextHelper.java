package com.wboard.server.helper;

public class TextHelper {
	/**
	 * Removes white space in a string
	 * @param s
	 * @return String
	 */
	public static String filenameFormat(String s){
		// delete all white space
		String ret = s.replaceAll("\\s", "");
		
		//replace all special character into _
		ret = ret.replaceAll("[\\W\\D]", "_");
		return ret;
	}
}

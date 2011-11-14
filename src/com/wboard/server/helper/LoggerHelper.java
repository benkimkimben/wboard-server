package com.wboard.server.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerHelper {
	/**
	 * Returns a log file name with the subsystem name extended by date and (.log) extension
	 * @param subsystem
	 * @return String
	 */
	public static String getFileName(String subsystem){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMddYYYY_HHmmss");
		
		// concatenate filename with subsystem name, date, and .log extension 
		String filename = TextHelper.filenameFormat(subsystem) + "-" + sdf.format(new Date()) + ".log";
		
		return filename;
	}
}

package com.wboard.server.log;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.wboard.server.helper.LoggerHelper;

public class ServerLogger {
	private static FileHandler logFileHandler;
	private static SimpleFormatter logFormatter;

	static public void setup(String subsystem, Level logLevel) throws IOException {
		// Register a subsystem logger in to Logger class
		Logger logger = Logger.getLogger(subsystem);
		logger.setLevel(logLevel);
		
		// Create log file Formatter
		logFileHandler = new FileHandler(LoggerHelper.getFileName(subsystem));
		logFormatter = new SimpleFormatter();
		logFileHandler.setFormatter(logFormatter);
		
		// add the file handler to the logger
		logger.addHandler(logFileHandler);
	}
}
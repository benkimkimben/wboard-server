package com.wboard.server.config;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.wboard.server.xml.XMLParseException;
import com.wboard.server.xml.XMLParser;

public class ServerConfig {
	private int port;
	private String host;
	private String logPath;
	
	public ServerConfig() throws ConfigException{
		configure();
	}
	
	public void configure() throws ConfigException{
		XMLParser xmlp;
		try {
			xmlp = new XMLParser("config/server.xml");
		} catch (XMLParseException e1) {
			throw new ConfigException("Failed to parse the config/server.xml.");
		}
		//get attributes from Server tag
		Element root = xmlp.getRootElement();
		port = Integer.parseInt(root.getAttribute("port"));
		host = root.getAttribute("host");
		
		// fetch other variables
		NodeList nList = root.getChildNodes();
		for(int i=0; i<nList.getLength(); i++){
			Node n = nList.item(i);
			
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}
}

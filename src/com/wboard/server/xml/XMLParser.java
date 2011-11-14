package com.wboard.server.xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLParser {
	private DocumentBuilder dBuild;
	private Document xml;
	
	public XMLParser(){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			dBuild = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	public XMLParser(String filename) throws XMLParseException{
		this();
		parseXMLFile(filename);
	}
	
	protected void parseXMLFile(String filename) throws XMLParseException{
		try {
			xml = dBuild.parse(filename);
		} catch (SAXException e) {
			throw new XMLParseException(e.getMessage());
		} catch (IOException e) {
			throw new XMLParseException(e.getMessage());
		}
	}
	
	public Element getRootElement(){
		return xml.getDocumentElement();
	}
	
}

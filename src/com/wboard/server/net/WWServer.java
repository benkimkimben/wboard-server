package com.wboard.server.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wboard.server.Whiteboard;
import com.wboard.server.config.ConfigException;
import com.wboard.server.config.ServerConfig;

public class WWServer {

	public static final Logger WLOG = Logger.getLogger(WWServer.class.getPackage().getName());

	// Server socket
	private ServerSocket sSocket;
	// Client Socket
	private Socket cSocket;
	
	private DataInputStream dis;
	private DataOutputStream dos;
	
	private Whiteboard wBoard;

	private ServerConfig sConfig;
	private ServerController sControl;
	/**
	 * configure this server from server.xml
	 * create new server socket
	 */
	public WWServer(){
		try {
			sConfig = new ServerConfig();
			sControl = new ServerController(this);
		} catch (ConfigException e) {
			WLOG.log(Level.CONFIG, "Failed to create a server: " + e.getMessage());
		}
		
		try {
			sSocket = new ServerSocket(sConfig.getPort());
		} catch (IOException e) {
			WLOG.log(Level.CONFIG, "Failed to create a server socket: " + e.getMessage());
		}
		WLOG.log(Level.INFO, "Server is created.");
		
		// create the main whiteboard frame
		wBoard = new Whiteboard();
	}
	/**
	 * start up the server. wait for clients to connect
	 */
	public void startup(){
		String cAddr = "";
		WLOG.log(Level.INFO, "Server has started operation.");

		while(true){
			try{
				cSocket = sSocket.accept();
			
				dis = new DataInputStream(cSocket.getInputStream());
				dos = new DataOutputStream(cSocket.getOutputStream());
				cAddr = cSocket.getInetAddress().getHostAddress();
				
				WLOG.log(Level.INFO, cAddr + " has connected.");	
				
				String receiveData;
				ServerThread st = new ServerThread(cSocket);
				do{
					receiveData = receive();
					// return ack
					st.start();
					WLOG.log(Level.INFO, "Received a message: " + receiveData);

					sControl.controlMessage(receiveData);
				}while( !receiveData.equals("@disconnect") );
				WLOG.log(Level.INFO, cAddr + " has disconnected.");
			}catch(IOException e){
				
			}
		}	
	}
	
	/**
	 * receive from socket
	 * @return
	 * @throws IOException
	 */
	private String receive() throws IOException{
		return dis.readUTF();
	}
	
	/**
	 * shutdown the server
	 */
	public void shutdown(){
		try {
			if(sSocket != null) sSocket.close();
			if(cSocket != null) cSocket.close();
			if(dis != null) dis.close();
			if(dos != null) dos.close();
			WLOG.log(Level.INFO, "Server is now shutdown.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ServerSocket getsSocket() {
		return sSocket;
	}
	public void setsSocket(ServerSocket sSocket) {
		this.sSocket = sSocket;
	}
	public Socket getcSocket() {
		return cSocket;
	}
	public void setcSocket(Socket cSocket) {
		this.cSocket = cSocket;
	}
	public Whiteboard getWBoard() {
		return wBoard;
	}
	public void setWBoard(Whiteboard wBoard) {
		this.wBoard = wBoard;
	}
	public ServerConfig getsConfig() {
		return sConfig;
	}
	public void setsConfig(ServerConfig sConfig) {
		this.sConfig = sConfig;
	}
	public ServerController getsControl() {
		return sControl;
	}
	public void setsControl(ServerController sControl) {
		this.sControl = sControl;
	}
}
package com.wboard.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.wboard.server.Server;
import com.wboard.server.ServerProtocol;
import com.wboard.server.model.Board;
import com.wboard.server.model.Drawable;
import com.wboard.server.model.User;

public class WBoardServer extends Thread implements Server, ServerProtocol {

	public static final Logger LOG = Logger.getLogger(WBoardServer.class);

	private final static String SERVERNAME = "WBoardServer";

	private final InetSocketAddress isa;

	// The flag for checking if the server is running
	private volatile boolean running = false;

	private ArrayList<Board> boardList;
	private ArrayList<User> userList;


	// ***********
	// Constructor
	// ***********

	public WBoardServer() throws IOException {
		boardList = new ArrayList<Board>();
		userList = new ArrayList<User>();

		String hostname = "localhost"; //"wboard.server.host";
		int port = 5000; //"wboard.server.port"
		this.isa = new InetSocketAddress(hostname, port);

		
		LOG.info("WBoardServer has created: " + hostname + ":" + port);
	}

	// ****************
	// Client Protocol - com.wboard.server.protocol.ClientProtocol
	// ****************


	/**
	 * Start this server
	 */
	@Override
	public void run() {
		try {
			initialize();
			// We are either the active master or we were asked to shutdown
			if (this.running) {

				
				loop();
			}
		} catch (Throwable t) {
			stop();
		} finally {		}
	}

	
	/**
	 * Helper for run()
	 * @throws InterruptedException 
	 */
	private void loop() throws InterruptedException {
		while (this.running) {
			TimeUnit.SECONDS.sleep(1);
		}
	}


	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
	}

	public boolean joinBoard(int boardid, int userid) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean createBoard(String title) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean createUser(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public void draw(int roomid, Drawable obj) {
		// TODO Auto-generated method stub
		
	}

	public void erase(int roomid, int userid, int objid) {
		// TODO Auto-generated method stub
		
	}

	public long getProtocolVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}
}

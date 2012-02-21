package com.wboard.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.util.Sleeper;
import com.wboard.server.ipc.RpcServer;
import com.wboard.server.ipc.RpcServerProtocol;
import com.wboard.common.conf.Configuration;
import com.wboard.common.model.DrawableObject;
import com.wboard.common.model.Room;
import com.wboard.common.model.User;
import com.wboard.common.protocol.ClientProtocol;

public class WBoardServer extends Thread implements Server, ClientProtocol {
	
	public static final long versionID = 101L;
	
	private static final Log LOG = LogFactory.getLog(WBoardServer.SERVERNAME);

	private final static String SERVERNAME = "WBoardServer";
	
	// The configuration for the Master
	private final Configuration conf;

	// the server address
	private final InetSocketAddress isa;

	// The flag for checking if the server is running
	private volatile boolean running = false;

	private ArrayList<Room> rList;
	private ArrayList<User> uList;

	RpcServerProtocol rpcServer = new RpcServer();

	// ***********
	// Constructor
	// ***********
	
	public WBoardServer(Configuration conf) {
		rList = new ArrayList<Room>();
		uList = new ArrayList<User>();
		this.conf = conf;

		this.isa = this.rpcServer.getListenerAddress();
	}
	
	// ****************
	// Client Protocol - com.wboard.server.protocol.ClientProtocol
	// ****************
	
	@Override
	public boolean createRoom(String title) {
		Room r;
		r = new Room(title);
		if(rList.add(r)){
			LOG.info("Room " + title + " has successfully crreated. Room id: " + r.getId());
			return true;
		}else{
			LOG.error("Failed to create room " + title);
			return false;
		}
	}
	@Override
	public boolean createUser(String name) {
		User u;
		u = new User(name);
		if(uList.add(u)){
			LOG.info("User " + name + " has successfully crreated. User id: " + u.getId());
			return true;
		}else{
			LOG.error("Failed to create room " + name);
			return false;
		}
	}
	@Override
	public boolean joinRoom(int roomid, int userid) {
		User u;
		Room r;
		try {
			u = uList.get(userid);
			r = rList.get(roomid);
			r.addUser(u);
			LOG.info("User " + u.getId() + " has successfully joined the room: " + roomid);
		} catch (Exception e) {
			LOG.error("User " + userid + " was unable to join the room: " + roomid);
			return false;
		}
		return true;
	}

	// ****************
	// Server Operation
	// ****************
	
	/**
	 * Get the configuration of this server
	 */
	@Override
	public Configuration getConfiguration() {
		return conf;
	}
	
	/**
	 * Initialize this server
	 */
	private void initialize(){
		

	}

	/**
	 * Start this server
	 */
	@Override
	public void run() {
		try {
			// We are either the active master or we were asked to shutdown
			if (this.running) {
				initialize();
				loop();
			}
		} catch (Throwable t) {
			stop("Something went wrong with the server.");
		} finally {		}
	}

	// Check if we should stop every second.
	Sleeper stopSleeper = new Sleeper(1000, this);
	
	/**
	 * Helper for run()
	 */
	private void loop() {
		while (this.running) {
			stopSleeper.sleep();
		}
	}

	/**
	 * DONE: 
	 * Check if the server is stopped
	 */
	@Override
	public boolean isStopped() {
		return !this.running;
	}
	
	/**
	 * TODO
	 * Stop this server
	 */
	@Override
	public void stop(String arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Return the name of this server
	 */
	@Override
	public String getServerName() {
		return SERVERNAME;
	}

	/**
	 * Return the version of this protocol
	 */
	@Override
	public long getProtocolVersion(String arg0, long arg1) throws IOException {
		return versionID;
	}
	/**
	 * TODO
	 */
	@Override
	public void draw(int roomid, DrawableObject obj) {
		// TODO Auto-generated method stub		
	}
	
	/**
	 * TODO
	 */
	@Override
	public void erase(int roomid, int userid, int objid) {
		// TODO Auto-generated method stub
		
	}
}

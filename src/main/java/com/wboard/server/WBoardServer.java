package com.wboard.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.ipc.HBaseRPC;
import org.apache.hadoop.hbase.ipc.RpcServer;
import org.apache.hadoop.hbase.util.Sleeper;
import com.wboard.common.model.DrawableObject;
import com.wboard.common.model.Room;
import com.wboard.common.model.WUser;
import com.wboard.common.protocol.ServerProtocol;

public class WBoardServer extends Thread implements Server, ServerProtocol {

	public static final Log LOG = LogFactory.getLog(WBoardServer.class);

	private final static String SERVERNAME = "WBoardServer";

	// The configuration for the Master
	private final Configuration conf;

	// the server address
	private final InetSocketAddress isa;

	// The flag for checking if the server is running
	private volatile boolean running = false;

	private ArrayList<Room> roomList;
	private ArrayList<WUser> userList;

	RpcServer rpcServer;

	// ***********
	// Constructor
	// ***********

	public WBoardServer(Configuration conf) throws IOException {
		roomList = new ArrayList<Room>();
		userList = new ArrayList<WUser>();
		this.conf = conf;

		String hostname = conf.get("wboard.server.host");
		int port = conf.getInt("wboard.server.port", 5000);
		this.isa = new InetSocketAddress(hostname, port);

		this.rpcServer = HBaseRPC.getServer(this,
				new Class<?>[]{ServerProtocol.class},
				isa.getHostName(), 
				isa.getPort(),
				1, // handler count
				1, // meta handler count
				false, // verbose
				conf, 
				10); //highPriorityLevel
		this.LOG.info("WBoardServer has created: " + hostname + ":" + port);
	}

	// ****************
	// Client Protocol - com.wboard.server.protocol.ClientProtocol
	// ****************

	@Override
	public boolean createRoom(String title) {
		Room r;
		r = new Room(title);
		if(roomList.add(r)){
			LOG.info("Room " + title + " has successfully crreated. Room id: " + r.getId());
			return true;
		}else{
			LOG.error("Failed to create room " + title);
			return false;
		}
	}
	@Override
	public boolean createUser(String name) {
		WUser u;
		u = new WUser(name);
		if(userList.add(u)){
			LOG.info("User " + name + " has successfully crreated. User id: " + u.getId());
			return true;
		}else{
			LOG.error("Failed to create room " + name);
			return false;
		}
	}
	@Override
	public boolean joinRoom(int roomid, int userid) {
		WUser u;
		Room r;
		try {
			u = userList.get(userid);
			r = roomList.get(roomid);
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
		this.running = true;

	}

	/**
	 * Start this server
	 */
	@Override
	public void run() {
		try {
			initialize();
			// We are either the active master or we were asked to shutdown
			if (this.running) {

				rpcServer.start();
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
	

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Configuration conf;
		conf = new Configuration(false);
		conf.addResource("server.xml");
		conf.dumpConfiguration(conf, new OutputStreamWriter(System.out));
		System.out.println(conf);
		
		

		Server wbs = null;
		
		try {
			wbs = new WBoardServer(conf);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		wbs.run();
	}
}

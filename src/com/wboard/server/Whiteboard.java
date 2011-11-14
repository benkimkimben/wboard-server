package com.wboard.server;

import java.util.logging.Level;

import com.wboard.server.net.WWServer;
import com.wboard.server.room.RoomManager;
import com.wboard.server.user.UserManager;
import com.wboard.room.Room;
import com.wboard.user.User;

public class Whiteboard {
	private RoomManager rManager;
	private UserManager uManager;
	
	public Whiteboard(){
		rManager = new RoomManager();
		uManager = new UserManager();
	}
	// TODO finish server client connection
	public boolean createRoom(String title, int uid, int type){
		Room r = new Room(title, uid, type);
		int rid = rManager.add(r);
		WWServer.WLOG.log(Level.INFO, "Room " + rid + " is built.");
		return true;
	}
	public boolean createUser(String uname){
		User u = new User(uname);
		int uid = uManager.add(u);
		WWServer.WLOG.log(Level.INFO, "User " + uid + " is built.");
		return true;
	}
}

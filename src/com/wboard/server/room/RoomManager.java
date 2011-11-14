package com.wboard.server.room;

import com.wboard.server.Manager;
import com.wboard.exception.FindException;
import com.wboard.room.Room;

public class RoomManager extends Manager<Room> {
	private int cid;
	public RoomManager(){
		super();
		cid = 0;
	}
	public Room getById(int id) throws FindException{
		for(Room r: list){
			if(r.getId()==id){
				return r;
			}
		}
		throw new FindException("Room not found. " + id);
	}
	
	public int add(Room r){
		r.setId(cid);
		cid++;
		return r.getId();
	}
}

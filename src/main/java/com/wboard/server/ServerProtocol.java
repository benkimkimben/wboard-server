package com.wboard.server;

import com.wboard.server.model.Drawable;

public interface ServerProtocol {
	public boolean createBoard(String title) ;
	public boolean createUser(String name);
	public boolean joinBoard(int boardid, int userid);
	public String getServerName();
	public void draw(int roomid, Drawable obj);
	public void erase(int roomid, int userid, int objid);
	public long getProtocolVersion();
	public boolean process(Message msg);

}

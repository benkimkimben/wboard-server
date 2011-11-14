package com.wboard.server.net;

/**
 * @wwweb room build 1
 * @author SKCCADMIN
 *
 */
public class ServerController {
	WWServer wServer;
	
	public ServerController(WWServer wServer){
		this.wServer = wServer;
	}
	
	public String controlMessage(String msg){
		String[] splitMsg = msg.split(" ");
		if(splitMsg[0].equals("@wwclient")){
			return ctrlClientMsg(splitMsg);
		}
		return "Unknown message.";
	}
	
	public String ctrlClientMsg(String[]msg){
		if(msg[1].equals("room")){
			return ctrlClientRoomMsg(msg);
		}
		return "Unknown message.";
	}
	
	public String ctrlClientRoomMsg(String[]msg){
		if(msg[2].equals("create")){
			String title = msg[3];
			int uid = Integer.parseInt(msg[4]);
			int type = Integer.parseInt(msg[5]);
			wServer.getWBoard().createRoom(title, uid, type);
		}
		return "Unknown message.";
	}
}

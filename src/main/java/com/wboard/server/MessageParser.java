package com.wboard.server;

public class MessageParser {
	public Message parse(String input){
		Message msg = new Message();
		msg.msg = "createRoom";
		msg.params[0] = "roomname";
		return msg;
	}
}

package com.wboard.server.thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread{
	Socket socket;
	DataOutputStream dos;
	public ServerThread(Socket s){
		socket = s;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void run(){
		String sendValue = "ack";
		try {
			send(sendValue);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void send(String msg) throws IOException {
		dos.writeUTF(msg); 
	}
}


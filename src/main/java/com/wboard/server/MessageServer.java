package com.wboard.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class MessageServer implements Server{
	public static final Logger LOG = Logger.getLogger(WBoardServer.class);

	private final static String SERVERNAME = "WBoardServer";

	private final InetSocketAddress isa;
	private final ServerSocket serverSocket;

	// The flag for checking if the server is running
	private volatile boolean running = false;

	private final MessageParser parser = new MessageParser();


	public MessageServer() throws IOException{
		String hostname = "localhost"; //"wboard.server.host";
		int port = 5000; //"wboard.server.port"
		this.isa = new InetSocketAddress(hostname, port);

		serverSocket = new ServerSocket(port);
		serverSocket.bind(isa);

		LOG.info("MessageServer has created: " + hostname + ":" + port);
	}


	public String getServerName() {
		return SERVERNAME;
	}


	public void stop() {
		// TODO Auto-generated method stub

	}


	public void run() {

		initialize();
		Socket clientSocket;
		while (this.running) {

			
			try {
				clientSocket = serverSocket.accept();
				try(PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){
					
					String inputLine = in.readLine();
					Message msg = parser.parse(inputLine);
					if(msg.isValid){
						boolean success = WBoardServer.server.process(msg);

						if(success) out.write("OK");
						else out.write("FAILED");
					}else{
						out.write("Invalid Message - " + msg.msg);
					}
				}
				TimeUnit.SECONDS.sleep(1);
			} catch (IOException e) {
				System.out.println("Accept failed");
			} catch (InterruptedException e) {
				LOG.info("Interrupted while running the server");
				break;
			}			
		}
	}
	
	/**
	 * Helper for run()
	 * @throws InterruptedException 
	 */
	private void loop() throws InterruptedException {
		while (this.running) {
			
		}
	}

	public void initialize() {
		// TODO Auto-generated method stub

	}


	public boolean isRunning() {
		// TODO Auto-generated method stub
		return running;
	}

}

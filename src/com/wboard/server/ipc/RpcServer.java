package com.wboard.server.ipc;

import java.io.IOException;
import java.net.InetSocketAddress;
import org.apache.hadoop.io.Writable;

import com.wboard.common.protocol.ClientProtocol;

/**
 * An abstract IPC service.  IPC calls take a single {@link Writable} as a
 * parameter, and return a {@link Writable} as their value.  
 * A service runs on a port and is defined by a parameter class and a value class.
 *
 * @author SKCCADMIN
 */
public class RpcServer implements RpcServerProtocol{
	
	public RpcServer(){
	/*	
	 * try {
			sConfig = new ServerConfig();
			sControl = new ServerController(this);
		} catch (ConfigException e) {
			WLOG.log(Level.CONFIG, "Failed to create a server: " + e.getMessage());
		}
		
		try {
			sSocket = new ServerSocket(sConfig.getPort());
		} catch (IOException e) {
		}
		
	*/
	}

	@Override
	public void setSocketSendBufSize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		/*
		String cAddr = "";
		WLOG.log(Level.INFO, "Server has started operation.");

		while(true){
			try{
				cSocket = sSocket.accept();
			
				dis = new DataInputStream(cSocket.getInputStream());
				dos = new DataOutputStream(cSocket.getOutputStream());
				cAddr = cSocket.getInetAddress().getHostAddress();
				
				WLOG.log(Level.INFO, cAddr + " has connected.");	
				
				String receiveData;
				ServerThread st = new ServerThread(cSocket);
				do{
					receiveData = receive();
					// return ack
					st.start();
					WLOG.log(Level.INFO, "Received a message: " + receiveData);

					sControl.controlMessage(receiveData);
				}while( !receiveData.equals("@disconnect") );
				WLOG.log(Level.INFO, cAddr + " has disconnected.");
			}catch(IOException e){
				
			}
		}		*/	
	}

	@Override
	public void stop() {
		/*
		try {
			if(sSocket != null) sSocket.close();
			if(cSocket != null) cSocket.close();
			if(dis != null) dis.close();
			if(dos != null) dos.close();
			WLOG.log(Level.INFO, "Server is now shutdown.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/		
	}

	@Override
	public void join() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InetSocketAddress getListenerAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	public Writable call(Class<? extends ClientProtocol> protocol,
			Writable param, long receiveTime) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumOpenConnections() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCallQueueLen() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setErrorHandler(RPCErrorHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openServer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startThreads() {
		// TODO Auto-generated method stub
		
	}

		
}


package com.wboard.server;

/**
 * Interface of server operations
 */
public interface Server {

	/**
	 * Gets the unique server name for this server.
	 * @return unique server name
	 */
	public String getServerName();
	
	public void stop();
	
	public void run();
	
	public void initialize();
	
	public boolean isRunning() ;


}
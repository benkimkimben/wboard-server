import com.wboard.server.net.WWServer;


public class WWServerDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WWServer wwServer = new WWServer();
		wwServer.startup();
		wwServer.shutdown();
	}
}

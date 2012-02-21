import com.wboard.common.conf.ConfigException;
import com.wboard.common.conf.Configuration;
import com.wboard.server.Server;
import com.wboard.server.WBoardServer;


public class ServerDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration sc;
		Server wbs = null;
		try {
			sc = new Configuration();
			wbs = new WBoardServer(sc);
			wbs.run();
			
		} catch (ConfigException e) {
			e.printStackTrace();
		}finally{
			if(wbs != null) wbs.stop("Server exiting");
		}
		
	}
}

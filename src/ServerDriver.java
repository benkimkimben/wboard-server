import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import com.wboard.server.Server;
import com.wboard.server.WBoardServer;


public class ServerDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration conf;
		conf = new Configuration();
		conf.addResource("server.xml");

		Server wbs = null;
		
		try {
			wbs = new WBoardServer(conf);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		wbs.run();
	}
}

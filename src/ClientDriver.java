import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class ClientDriver {
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Client wClient = new Client();
		wClient.connect("localhost");
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String input;
		do{
			System.out.print("Input: ");
			input = br.readLine();
			wClient.send(input);
			
		}while(!input.equals("bye"));
		wClient.disconnect();
	}

}

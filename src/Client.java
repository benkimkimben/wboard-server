
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
	public static final int PORT = 5432;
	Socket socket;
	DataOutputStream dos;
	DataInputStream dis;
	
	public void connect(String host){
		try {
			socket = new Socket(host, PORT);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}catch(ConnectException e){
			System.out.println("서버와 연결할 수 없습니다.");
		}catch(SocketException e){
			System.out.println("연결할 소켓이 없습니다.");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void send(String msg) throws IOException{
		dos.writeUTF(msg);
		// run receive thread
		ClientThread ct = new ClientThread(socket);
		ct.start();
	}
	public void disconnect(){
		if(socket != null)
			try {
				socket.close();
				dis.close();
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
class ClientThread extends Thread{
	Socket socket;
	DataInputStream dis;
	public ClientThread(Socket s){
		socket = s;
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void run(){
		while(true){
			String receiveData = "";
			try {
				receiveData = receive();
			} catch (IOException e) {
				receiveData = "Client 수신오류.";
			}
			System.out.println(receiveData);
		}
	}
	private String receive() throws IOException {
		return dis.readUTF();
	}
}
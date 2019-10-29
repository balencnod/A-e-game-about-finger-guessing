package Project9;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ThreadC extends Thread{
	
	Resource r;
	int num1,num2;
	double t1;int t2;
	int ap,bp;
	private DataOutputStream outputToServer;
	private DataInputStream inputFromServer;
	
	String data;
	
	ThreadC(Resource _r){
		r = _r;
		num1 = 0;
		num2 = 0;
		ap = 0;
		bp = 0;
	}
	
	public void run() { 
		Socket socket;
		try {
			//TCP
			socket = new Socket("localhost", 8002);
			inputFromServer = new DataInputStream(socket.getInputStream());
		    outputToServer = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		for(int i = 0;i < 3;i++) {
			try {
				synchronized(r) {
					r.wait();
					num1 = r.getNum1();
					num2 = r.getNum2();
					t1 = r.getT1();
					t2 = (int)(10000*r.getT2());
					//TCP发送A
					outputToServer.writeInt(num1);
					outputToServer.writeDouble(t1);
					//UDP发送B
					DatagramSocket datagramSocket = new DatagramSocket();
			        String data = String.valueOf(num2+t2);
			        DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length,InetAddress.getLocalHost() , 10070+i);
			        datagramSocket.send(packet);
			        datagramSocket.close();
					//切换线程
					r.setB(false);
					r.notifyAll();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

package Project9;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

	static int num1,num2;
	static int ap,bp;
	static double t1,t2;
	
	static void printChar(int num) {
		if(num == 0) {
			System.out.print("石头" + "\t"+"\t");
		}
		else if(num == 1) {
			System.out.print("剪刀" + "\t"+"\t");
		}
		else {
			System.out.print("布" + "\t"+"\t");
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		num1 = 0;num2 = 0;
		ap = 0;bp = 0;
		
		System.out.println("Thread A"+"\t"+"\t"+"\t"+"Thread B"+"\t"+"\t"+"\t");
		System.out.println("Sleep"+"\t"+"Random"+"\t"+"\t"+"Points"+"\t"+"Sleep"+"\t"+"Random"+"\t"+"\t"+"Points"+"\t");
		System.out.println("time"+"\t"+"character"+"\t"+"obtaine"+"\t"+"time"+"\t"+"character"+"\t"+"obtaine");
		
		//TCP
		ServerSocket serverSocket = new ServerSocket(8002);
		
		Socket socket = serverSocket.accept();
		
		DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
		DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
		
		for(int i = 0;i < 3;i++) {
			//TCP
			num1 = inputFromClient.readInt();
			t1 = inputFromClient.readDouble();
			//UDP
			DatagramSocket  socket0 = new DatagramSocket(10070+i);
	        byte[] buf = new byte[1024];
	        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
	        socket0.receive(datagramPacket); 
	        String s = new String(buf,0,datagramPacket.getLength());
	        socket0.close();
	        num2 = Integer.parseInt(s);
	        t2 = (double)(num2/10)/1000;
	        num2 = num2%10;
	        //处理数据
			System.out.print(t1/1000+"\t");
			printChar(num1);
			if(num1==0&&num2==1||num1==1&&num2==2||num1==2&&num2==0) {
				System.out.print("2"+"\t");
				ap = ap + 2;
			}
			else if(num1==0&&num2==2||num1==1&&num2==0||num1==2&&num2==1) {
				System.out.print("0"+"\t");
			}
			else {
				System.out.print("1"+"\t");
				ap++;
			}
			System.out.print(t2/1000+"\t");
			printChar(num2);
			if(num1==0&&num2==2||num1==1&&num2==0||num1==2&&num2==1) {
				System.out.println("2"+"\t");
				bp = bp + 2;
			}
			else if(num1==0&&num2==1||num1==1&&num2==2||num1==2&&num2==0) {
				System.out.println("0"+"\t");
			}
			else {
				System.out.println("1"+"\t");
				bp++;
			}
		}
		if(ap>bp) {
			System.out.println("A 获得胜利！");
		}
		else if(ap<bp) {
			System.out.println("B 获得胜利！");
		}
		else {
			System.out.println("平局！");
		}
	}
}

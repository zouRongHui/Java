package org.rone.study.java.grammar.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

	public ServerTest() {
		try {
			//创建一个ServerSocket在端口xxxx监听客户请求
			ServerSocket server = new ServerSocket(9901);
			//使用accept()阻塞等待客户请求，有客户请求到来则产生一个Socket对象，并继续执行
			Socket socket = server.accept();
			String line;
			//由socket对象得到输入流，并构造相应的BufferedReader对象
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//由socket对象得到输出流，并构造PrintWriter对象
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			//从系统标准输入读入一个字符串
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			//在标准输出上打印从客户段读入的字符串
			System.out.println("Client:" + is.readLine());
			//从标准输入读入一个字符串
			line = sin.readLine();
			//如果该字符串为 bye 则停止循环
			while(!line.equals("bye")) {
				//向客户端输出该字符串
				os.print(line);
				//刷新输出流，使Client马上收到该字符串
				os.flush();
				//在系统标准输出上打印读入的字符串
				System.out.println("Server:" + line);
				//从Client读入一个字符串，并打印到标准输出上
				System.out.println("Client:" + is.readLine());
				//从标准输入读入一个字符串
				line = sin.readLine();
			}
			//关闭Socket输出流
			os.close();
			//关闭Socket输入流
			is.close();
			//关闭Socket
			socket.close();
			//关闭ServerSocket
			server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ServerTest();
	}

}

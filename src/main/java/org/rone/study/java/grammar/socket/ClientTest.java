package org.rone.study.java.grammar.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTest {

	public ClientTest() {
		try {
			//向本机的xxxx端口发出客户请求
			Socket socket = new Socket("127.0.0.1", 9901);
			//由系统标准输入设备构建BufferedReader对象
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			//由socket对象得到输出流，并构造PrintWriter对象
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			//由socket对象得到输入流，并构造相应的BufferedReader对象
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//从系统标准输入读入一个字符串
			String readline = sin.readLine();
			//若读入字符串为 bye 则停止循环
			while(!readline.equals("bye")) {
				//将字符串输出到Server
				os.println(readline);
				//刷新输出流，使Server马上收到该字符串
				os.flush();
				//在系统标准输出上打印读入字符串
				System.out.println("Client:" + readline);
				//从Server读入一个字符串，并打印到标准输出上
				System.out.println("Server:" + is.readLine());
				//从系统标准输入读入一个字符串
				readline = sin.readLine();
			}
			//关闭socket输出流
			os.close();
			//关闭socket输入流
			is.close();
			//关闭socket
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ClientTest();
	}
}

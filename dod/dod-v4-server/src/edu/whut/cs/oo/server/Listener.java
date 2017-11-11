package edu.whut.cs.oo.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import edu.whut.cs.oo.common.Constants;
import edu.whut.cs.oo.common.Message;
import edu.whut.cs.oo.service.DocumentService;

public class Listener {

	public void init() throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(Constants.SERVER_LISTEN_PORT);
			System.out.println("Listening on " + serverSocket);
			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serverSocket.accept();
				System.out.println(client + " connected");
				// 处理这次连接
				new HandlerThread(client);
			}

		} catch (Exception e) {
			System.out.println("服务器异常: " + e.getMessage());
		} finally {
			serverSocket.close();
		}
	}

	private class HandlerThread implements Runnable {
		private Socket socket;
		private ObjectInputStream in;
		private ObjectOutputStream out;

		public HandlerThread(Socket client) {
			socket = client;
			new Thread(this).start();
		}

		public void run() {
			try {
				System.out.println(this + "is running ......");
				in = new ObjectInputStream(socket.getInputStream());
				out = new ObjectOutputStream(socket.getOutputStream());
//				Message message = (Message)in.readObject(); // read new message
				Dispatcher.forward(in, out);
			} catch (Exception e) {
				System.out.println("服务器 run 异常: " + e.getMessage());
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						out.close();
						in.close();
						socket.close();
					} catch (Exception e) {
						socket = null;
						System.out.println("服务端 finally 异常:" + e.getMessage());
					}
				}
			}
		}
		
	}
}

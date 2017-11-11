package edu.whut.cs.oo;

import edu.whut.cs.oo.server.Listener;

public class ServerApplication extends Application {
	
	public static void main(String[] args) throws Exception {
		Listener listener = new Listener();
		listener.init();
	}
}

package edu.whut.cs.oo;

import edu.whut.cs.oo.frame.LoginFrame;

public class GuiApplication extends Application {
	
	public static void main(String[] args) throws Exception {
		LoginFrame loginframe= new LoginFrame();
		loginframe.setVisible(true);
	}
	
}

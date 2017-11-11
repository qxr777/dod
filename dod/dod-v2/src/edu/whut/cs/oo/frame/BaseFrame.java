package edu.whut.cs.oo.frame;

import javax.swing.JFrame;

import edu.whut.cs.oo.Application;
import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.service.DocumentService;
import edu.whut.cs.oo.service.UserService;

public class BaseFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected User currentUser = Application.currentUser;
	protected UserService userService = Application.userService;
	protected DocumentService documentService = Application.documentService;
	
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
}

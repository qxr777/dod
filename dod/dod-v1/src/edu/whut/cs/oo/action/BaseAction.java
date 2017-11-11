package edu.whut.cs.oo.action;

import edu.whut.cs.oo.Application;
import edu.whut.cs.oo.service.DocumentService;
import edu.whut.cs.oo.service.UserService;

public abstract class BaseAction {
	
	protected UserService userService = Application.userService;
	protected DocumentService documentService = Application.documentService;
	
	public abstract void execute();
	
	public abstract String getText();
	
	protected void output(String message) {
		System.out.println(message);
	}
}

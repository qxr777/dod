package edu.whut.cs.oo.action;

import java.util.Scanner;

import edu.whut.cs.oo.Application;
import edu.whut.cs.oo.service.DocumentService;
import edu.whut.cs.oo.service.UserService;

public abstract class BaseAction {
	
	protected UserService userService = Application.userService;
	protected DocumentService documentService = Application.documentService;
	
	protected Scanner scanner = new Scanner(System.in);
	
	public abstract void execute();
	
	public abstract String getText();
	
	protected void output(String message) {
		System.out.println(message);
	}
}

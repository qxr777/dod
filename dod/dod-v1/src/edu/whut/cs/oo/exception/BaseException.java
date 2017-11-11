package edu.whut.cs.oo.exception;


public class BaseException extends Exception {
	
    protected String message;

	private static final long serialVersionUID = 1326790579993179244L;
	
	public BaseException(){
		message="业务异常";
	}
	
	public String getMessage(){
		return this.message;
	}

}

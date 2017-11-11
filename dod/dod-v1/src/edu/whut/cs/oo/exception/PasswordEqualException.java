package edu.whut.cs.oo.exception;

public class PasswordEqualException extends BaseException {

	/**
	 * 新旧password相同时抛出异常
	 */
	private static final long serialVersionUID = -8077530677006565701L;
	
	public PasswordEqualException(){
		super.message = PASSWORD_ERROR_ERROR;
	}
	
	public static final String PASSWORD_ERROR_ERROR = "新旧密码不能相同！";

	
}

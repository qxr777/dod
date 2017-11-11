package edu.whut.cs.oo.exception;

public class PasswordErrorException extends BaseException {

	/**
	 * password错误时抛出异常
	 */
	private static final long serialVersionUID = -8077530677006565701L;
	
	public PasswordErrorException(){
		super.message = PASSWORD_ERROR_ERROR;
	}
	
	public static final String PASSWORD_ERROR_ERROR = "密码错误！";

	
}

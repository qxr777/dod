package edu.whut.cs.oo.exception;

public class ServerException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServerException(String serverMsg){
		super.message = SERVER_EXCEPTION + serverMsg;
	}
	
	public static String SERVER_EXCEPTION = "服务器: ";
	
}

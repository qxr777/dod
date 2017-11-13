package edu.whut.cs.oo.exception;

public class NetworkException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NetworkException(){
		super.message = NETWORK_EXCEPTION;
	}
	
	public static String NETWORK_EXCEPTION = "网络访问异常";
	
}

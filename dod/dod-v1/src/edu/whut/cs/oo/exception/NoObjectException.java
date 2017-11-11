package edu.whut.cs.oo.exception;

public class NoObjectException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6934854258189330871L;

	/**
	 * 实体不存在时抛出异常
	 */
	
	public NoObjectException(String objectId){
		super.message = objectId + NO_OBJECT_ERROR;
	}	
	
	public static final String NO_OBJECT_ERROR = "不存在！";

}

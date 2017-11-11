package edu.whut.cs.oo.exception;

public class HaveExistException extends BaseException {

	/**
	 * 实体名已经被用时抛出异常
	 */
	private static final long serialVersionUID = 6652098398262607292L;
	
	
	public HaveExistException(String objectName){
		super.message = objectName + Object_NAME_HASEXIST;
	}
	
	public static String Object_NAME_HASEXIST = "已经存在！！";
	
}

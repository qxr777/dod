package edu.whut.cs.oo.exception;

public class DaoException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DaoException(){
		super.message = DAO_ACCESS_ERROR;
	}
	
	public static String DAO_ACCESS_ERROR = "数据访问异常";
	
}

package edu.whut.cs.oo.exception;

public class MenuIndexException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuIndexException(){
		super.message = MENU_INPUT_ERROR;
	}
	
	public static String MENU_INPUT_ERROR = "请输入有效的菜单编号！";
	
}

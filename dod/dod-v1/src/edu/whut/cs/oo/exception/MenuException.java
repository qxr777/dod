package edu.whut.cs.oo.exception;

public class MenuException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuException(){
		super.message = MENU_INSTANTIATION_ERROR;
	}
	
	public static String MENU_INSTANTIATION_ERROR = "功能菜单初始化异常";
	
}

package edu.whut.cs.oo.exception;

public class FileException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileException(){
		super.message = FILE_ACCESS_ERROR;
	}
	
	public static String FILE_ACCESS_ERROR = "文件访问异常";
	
}

package edu.whut.cs.oo.common;

public interface Constants {
	
	static final String UPLOAD_PATH = "d:\\OOP\\uploadfile\\";
	static final String UPLOAD_TEMP_PATH = "d:\\OOP\\uploadfile\\temp\\";
	static final String DOWNLOAD_PATH = "d:\\OOP\\downloadfile\\";
	
	static final String DOCUMENT_FILE = "d:\\OOP\\document.bin";
	static final String USER_FILE = "d:\\OOP\\user.bin";
	
	static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/document";
	static final String DB_USER = "root";
	static final String DB_PASSWORD = "123456";
	
	static final String SERVER_ADDRESS = "localhost";
	static final int SERVER_LISTEN_PORT = 12345;	
	static final int SERVER_EXCEPTION_CODE = 500;
	
}

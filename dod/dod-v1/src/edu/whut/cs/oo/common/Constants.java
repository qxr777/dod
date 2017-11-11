package edu.whut.cs.oo.common;

public interface Constants {
	
	static final String UPLOAD_PATH = "e:\\OOP\\uploadfile\\";
	static final String UPLOAD_TEMP_PATH = "e:\\OOP\\uploadfile\\temp\\";
	static final String DOWNLOAD_PATH = "e:\\OOP\\downloadfile\\";
	
	static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_CONNECTION = "jdbc:mysql://localhost:4306/document";
	static final String DB_USER = "root";
	static final String DB_PASSWORD = "123456";
	
	static final String SERVER_ADDRESS = "localhost";
	static final int SERVER_LISTEN_PORT = 12345;	
	static final int SERVER_EXCEPTION_CODE = 500;
	
	static final String LOGIN_CONTROLLER = "edu.whut.cs.oo.controller.LoginController";
	static final String CHANGE_PASSWORD_CONTROLLER = "edu.whut.cs.oo.controller.ChangePasswordController";
	static final String CREATE_USER_CONTROLLER = "edu.whut.cs.oo.controller.CreateUserController";
	static final String UPDATE_USER_CONTROLLER = "edu.whut.cs.oo.controller.UpdateUserController";
	static final String DELETE_USER_CONTROLLER = "edu.whut.cs.oo.controller.DeleteUserController";
	static final String GET_ALL_USERS_CONTROLLER = "edu.whut.cs.oo.controller.GetAllUsersController";
	static final String GET_USER_CONTROLLER = "edu.whut.cs.oo.controller.GetUserController";
	static final String CLEAR_ALL_USERS_CONTROLLER = "edu.whut.cs.oo.controller.ClearAllUsersController";
	
	static final String UPLOAD_DOCUMENT_CONTROLLER = "edu.whut.cs.oo.controller.UploadDocumentController";
	static final String DOWNLOAD_DOCUMENT_CONTROLLER = "edu.whut.cs.oo.controller.DownloadDocumentController";
	static final String GET_ALL_DOCUMENTS_CONTROLLER = "edu.whut.cs.oo.controller.GetAllDocumentsController";
	static final String CLEAR_ALL_DOCUMENTS_CONTROLLER = "edu.whut.cs.oo.controller.ClearAllDocumentsController";
	static final String GET_DOCUMENT_CONTROLLER = "edu.whut.cs.oo.controller.GetDocumentController";
	
	static final String[] CONTROLLER_CLASSNAMES = {
			LOGIN_CONTROLLER,
			CHANGE_PASSWORD_CONTROLLER,
			GET_ALL_USERS_CONTROLLER,
			CLEAR_ALL_USERS_CONTROLLER,
			DELETE_USER_CONTROLLER,
			GET_USER_CONTROLLER,
			CREATE_USER_CONTROLLER,
			UPDATE_USER_CONTROLLER,
			UPLOAD_DOCUMENT_CONTROLLER,
			DOWNLOAD_DOCUMENT_CONTROLLER,
			GET_ALL_DOCUMENTS_CONTROLLER,
			CLEAR_ALL_DOCUMENTS_CONTROLLER,
			GET_DOCUMENT_CONTROLLER,
	};
}

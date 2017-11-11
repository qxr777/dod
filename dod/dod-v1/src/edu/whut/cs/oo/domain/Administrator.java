package edu.whut.cs.oo.domain;

public class Administrator extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1066705624276087251L;
	private String[] functionClassNames = {
			"edu.whut.cs.oo.action.CreateUserAction", 
			"edu.whut.cs.oo.action.UpdateUserAction", 
			"edu.whut.cs.oo.action.DeleteUserAction", 
			"edu.whut.cs.oo.action.ListUserAction", 
			"edu.whut.cs.oo.action.ListDocumentAction", 
			"edu.whut.cs.oo.action.DownloadDocumentAction",
			"edu.whut.cs.oo.action.ChangePasswordAction",
//			"edu.whut.cs.oo.action.ExitAction"
			};
	
	public Administrator() throws Exception {
//		super.loadFunctions();
	}
	
	public String[] getFunctionClassNames() {
		return functionClassNames; 
	}

}

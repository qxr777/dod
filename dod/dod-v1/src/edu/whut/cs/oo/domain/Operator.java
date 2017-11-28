package edu.whut.cs.oo.domain;

public class Operator extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4297888953645666305L;
	private String[] functionClassNames = {
			"edu.whut.cs.oo.action.UploadDocumentAction", 
			"edu.whut.cs.oo.action.DownloadDocumentAction", 
			"edu.whut.cs.oo.action.ListDocumentAction", 
			"edu.whut.cs.oo.action.ChangePasswordAction",
//			"edu.whut.cs.oo.action.ExitAction"
			};
	
	public String[] getFunctionClassNames() {
		return functionClassNames; 
	}
}

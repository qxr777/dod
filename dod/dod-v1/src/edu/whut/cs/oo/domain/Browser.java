package edu.whut.cs.oo.domain;

public class Browser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -465292455052819445L;
	
	private String[] functionClassNames = {
			"edu.whut.cs.oo.action.DownloadDocumentAction", 
			"edu.whut.cs.oo.action.ListDocumentAction", 
			"edu.whut.cs.oo.action.ChangePasswordAction"//,
//			"edu.whut.cs.oo.action.ExitAction"
			};
	
	public Browser() throws Exception {
//		super.loadFunctions();
	}
	
	public String[] getFunctionClassNames() {
		return functionClassNames; 
	}

}

package edu.whut.cs.oo.action;

import java.util.Scanner;

import edu.whut.cs.oo.Application;
import edu.whut.cs.oo.domain.Document;

public class UploadDocumentAction extends BaseAction {
	
	private Document document;
	
	public static final String UPLOAD_DOCUMENT_TEXT = "上传文件";
	
	private void input() throws Exception {
		Scanner scanner = new Scanner(System.in);
    	System.out.println(UPLOAD_DOCUMENT_TEXT);                        
        System.out.println("请输入源文件名：");
        String filename = scanner.next();
        System.out.println("请输入档案号：");
        String sn = scanner.next();
        System.out.println("请输入档案描述：");
        String description = scanner.next();
        
        document = new Document();
        document.setSn(sn);
        document.setDescription(description);
        document.setSourcePath(filename);
        document.setUser(Application.currentUser);
	}
	

	
	protected void output(String message) {
		System.out.println(message);
	}

	@Override
	public void execute() {
		try {
			input();
			documentService.uploadDocument(document); 
			output(UPLOAD_DOCUMENT_TEXT + "成功！！！");
		} catch (Exception e) {
			output(e.getMessage());
		}
	}

	@Override
	public String getText() {
		return UPLOAD_DOCUMENT_TEXT;
	}

}

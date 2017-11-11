package edu.whut.cs.oo.action;

import java.util.Scanner;

import edu.whut.cs.oo.domain.Document;

public class DownloadDocumentAction extends BaseAction {
	
	private String sn;
	
	public static final String DOWNLOAD_DOCUMENT_TEXT = "下载文件";
	
	private void input() {
		Scanner scanner = new Scanner(System.in);
    	System.out.println(DOWNLOAD_DOCUMENT_TEXT);                        
        System.out.println("请输入档案号：");
        sn = scanner.next();
	}
	
	protected void output(Document document) {
		System.out.println(document);
		System.out.println(DOWNLOAD_DOCUMENT_TEXT + "成功！！！");
	}
	
	@Override
	public void execute() {

			try {
				input();
				Document document = documentService.downloadDocument(sn, null); 
				output(document);
			} catch (Exception e) {
//				e.printStackTrace();
				output(e.getMessage());
			}
	}

	@Override
	public String getText() {
		return DOWNLOAD_DOCUMENT_TEXT;
	}

}

package edu.whut.cs.oo.action;

import java.util.List;

import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.exception.BaseException;

public class ListDocumentAction extends BaseAction {
	
	private List<Document> documents;
	
	public static final String LIST_DOCUMENT_TEXT = "列出档案";
	
	private void input() {
    	System.out.println(LIST_DOCUMENT_TEXT);
	}
	
	protected void output(List<Document> documents) {
		for (Document document : documents) {
			System.out.println(document);
		}
	}
	
	@Override
	public void execute() {
		input();
		try {
			documents = documentService.getAllDocuments();
			output(documents);
		} catch (BaseException e) {
			output(e.getMessage());
		}
	}

	@Override
	public String getText() {
		return LIST_DOCUMENT_TEXT;
	}
	
}

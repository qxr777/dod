package edu.whut.cs.oo.controller;

import java.util.List;

import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.exception.BaseException;

public class GetAllDocumentsController extends BaseController {

	@Override
	public void service() {
		try {
			List<Document> documents = documentService.getAllDocuments();
			message.setData(documents);		
			onSuccess();
		} catch (BaseException e) {
			onException(e);
		}
		
	}

}

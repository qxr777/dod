package edu.whut.cs.oo.controller;

import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.exception.BaseException;

public class GetDocumentController extends BaseController {

	@Override
	public void service() {
		String sn = (String)message.getParameter("sn");
		try {
			Document document = documentService.getDocument(sn);
			message.setData(document);		
			onSuccess();
		} catch (BaseException e) {
			onException(e);
		}
		
	}

}

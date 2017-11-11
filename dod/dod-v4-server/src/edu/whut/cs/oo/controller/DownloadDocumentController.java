package edu.whut.cs.oo.controller;

import java.io.IOException;

import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.exception.BaseException;

public class DownloadDocumentController extends BaseController {

	@Override
	public void service() {
		String sn = (String)message.getParameter("sn");
		try {
			Document document = documentService.getDocument(sn);
			message.setData(document);		
			onSuccess();
			sendFile(document.getAbsolutePath());
		} catch (BaseException e) {
			onException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

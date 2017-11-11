package edu.whut.cs.oo.controller;

import java.io.IOException;

import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.exception.BaseException;

public class UploadDocumentController extends BaseController {

	@Override
	public void service() {
		Document document = (Document)message.getParameter("document");
		
		try {
			String tempPath = receiveTempFile(document.getName());
			document.setSourcePath(tempPath);
			document = documentService.uploadDocument(document);
			deleteTempFile(tempPath);
			message.setData(document);		
			onSuccess();
		} catch (BaseException e) {
			onException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

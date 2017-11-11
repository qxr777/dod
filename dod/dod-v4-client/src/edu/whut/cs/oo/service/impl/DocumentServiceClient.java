package edu.whut.cs.oo.service.impl;

import java.io.IOException;
import java.util.List;

import edu.whut.cs.oo.Application;
import edu.whut.cs.oo.common.Constants;
import edu.whut.cs.oo.common.Message;
import edu.whut.cs.oo.dao.DocumentDao;
import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.service.DocumentService;

public class DocumentServiceClient extends BaseServiceClient implements DocumentService{
	
	public Document uploadDocument(Document document) throws BaseException, IOException {
		Message message = new Message();
		message.setController(Constants.UPLOAD_DOCUMENT_CONTROLLER);
		document.setName(getFileName(document.getSourcePath()));
		document.setUser(Application.currentUser);
		message.setParameter("document", document);
		message = sendDocument(message);
		document = (Document)message.getData();
		return document;
	}

	@Override
	public void setDocumentDao(DocumentDao documentDao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Document createDocument(Document document) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document deleteDocument(String sn) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getDocument(String sn) throws BaseException {
		Message message = new Message();
		message.setController(Constants.GET_DOCUMENT_CONTROLLER);
		message.setParameter("sn", sn);
		message = send(message);
		Document document = (Document)message.getData();
		return document;
	}

	@Override
	public List<Document> getAllDocuments() throws BaseException {
		Message message = new Message();
		message.setController(Constants.GET_ALL_DOCUMENTS_CONTROLLER);
		message = send(message);
		List<Document> documents = (List<Document>)message.getData();
		return documents;
	}

	@Override
	public void clear() throws BaseException {
		Message message = new Message();
		message.setController(Constants.CLEAR_ALL_DOCUMENTS_CONTROLLER);
		message = send(message);		
	}

	@Override
	public Document downloadDocument(String sn, String targetPath) throws BaseException, IOException {
		Message message = new Message();
		message.setController(Constants.DOWNLOAD_DOCUMENT_CONTROLLER);
		message.setParameter("sn", sn);
		message = receiveDocument(message, targetPath);
		Document document = (Document)message.getData();
		return document;
	}
	
}

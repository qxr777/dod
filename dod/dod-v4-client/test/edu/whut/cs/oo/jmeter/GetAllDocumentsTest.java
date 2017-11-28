package edu.whut.cs.oo.jmeter;

import java.util.List;

import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.service.DocumentService;
import edu.whut.cs.oo.service.impl.DocumentServiceClient;

public class GetAllDocumentsTest extends BaseTest {

	@Override
	public void service() throws BaseException {
        DocumentService documentService = new DocumentServiceClient();
        List<Document> documents = documentService.getAllDocuments();
		sr.setResponseMessage(documents.toString());
		sr.setSuccessful(true); 
	}

}

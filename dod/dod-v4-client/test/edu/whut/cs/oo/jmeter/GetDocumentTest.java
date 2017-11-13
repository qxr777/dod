package edu.whut.cs.oo.jmeter;

import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.service.DocumentService;
import edu.whut.cs.oo.service.impl.DocumentServiceClient;

public class GetDocumentTest extends BaseTest {

	@Override
	public void service() throws BaseException {
		DocumentService documentService = new DocumentServiceClient();
		Document document;
		document = documentService.getDocument("D001");
		sr.setResponseMessage(document.toString());
		sr.setSuccessful(true);

	}

}

package edu.whut.cs.oo.controller;

import edu.whut.cs.oo.exception.BaseException;

public class ClearAllDocumentsController extends BaseController {

	@Override
	public void service() {
		try {
			documentService.clear();
			onSuccess();
		} catch (BaseException e) {
			onException(e);
		}
		
	}

}

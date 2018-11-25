package edu.whut.cs.oo.dao.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import edu.whut.cs.oo.dao.DocumentDao;
import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.exception.BaseException;

public class DocumentDaoContainer implements DocumentDao {
	
	private static Hashtable<String, Document> documents = new Hashtable<String, Document>();

	@Override
	public Document insert(Document object) throws BaseException {
		return documents.put(object.getSn(), object);
	}

	@Override
	public Document update(Document object) throws BaseException {
		return documents.replace(object.getSn(), object);
	}

	@Override
	public Document delete(Document object) throws BaseException {
		return documents.remove(object.getSn());
	}

	@Override
	public Document findById(long id) {
		for (Document document : documents.values()) {
			if (document.getId() == id) {
				return document;
			}
		}
		return null;
	}

	@Override
	public List<Document> findAllOnes() {
		return new ArrayList<Document>(documents.values());
	}

	@Override
	public Document findBySn(String sn) {
		return documents.get(sn);
	}

}

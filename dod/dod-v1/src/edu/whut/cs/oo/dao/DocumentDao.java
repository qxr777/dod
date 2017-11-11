package edu.whut.cs.oo.dao;

import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.exception.BaseException;

public interface DocumentDao extends BaseDao<Document> {
	
	Document findBySn(String sn) throws BaseException;

}

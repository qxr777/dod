package edu.whut.cs.oo.service;

import java.io.IOException;
import java.util.List;

import edu.whut.cs.oo.dao.DocumentDao;
import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.exception.BaseException;

public interface DocumentService {
	
	void setDocumentDao(DocumentDao documentDao);
	
	Document uploadDocument(Document document) throws BaseException, IOException;
	
	Document createDocument(Document document) throws BaseException;
	
	Document deleteDocument(String sn) throws BaseException;
	
	Document getDocument(String sn) throws BaseException;
	
	List<Document> getAllDocuments() throws BaseException;
	
	void clear() throws BaseException;
	
    default String getExtensionName(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (filename.length() - 1))) {   
                return filename.substring(dot + 1);   
            }   
        }   
        return filename;   
    } 
    
    default String getFileName(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int split = filename.lastIndexOf('\\');   
            if ((split >-1) && (split < (filename.length() - 1))) {   
                return filename.substring(split + 1);   
            }   
        }   
        return filename;   
    }

	Document downloadDocument(String sn, String targetPath) throws BaseException, IOException;
    
}

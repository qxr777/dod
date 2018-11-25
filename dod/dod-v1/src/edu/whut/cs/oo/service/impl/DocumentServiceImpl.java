package edu.whut.cs.oo.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import edu.whut.cs.oo.Application;
import edu.whut.cs.oo.common.Constants;
import edu.whut.cs.oo.dao.DocumentDao;
import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.exception.FileException;
import edu.whut.cs.oo.exception.HaveExistException;
import edu.whut.cs.oo.exception.NoObjectException;
import edu.whut.cs.oo.service.DocumentService;

public class DocumentServiceImpl implements DocumentService{
	
	private DocumentDao documentDao;
	
	public Document uploadDocument(Document document) throws BaseException {
		if (documentDao.findBySn(document.getSn()) != null) {
			throw new HaveExistException(document.getSn());
		}
		if (document.getSourcePath() != null) {
			document.setAbsolutePath(uploadFile(document.getSourcePath()));
			document.setName(getFileName(document.getSourcePath()));
		}
		if (Application.currentUser != null) {
			document.setUser(Application.currentUser);;
		}
		document.setSourcePath(null);
		document.setTimestamp(new Timestamp(System.currentTimeMillis()));
		return documentDao.insert(document);
	}
	
	public Document createDocument(Document document) throws BaseException {
		if (documentDao.findBySn(document.getSn()) != null) {
			throw new HaveExistException(document.getSn());
		}
		document.setTimestamp(new Timestamp(System.currentTimeMillis()));
		return documentDao.insert(document);
	}
	
	public Document deleteDocument(String sn) throws BaseException {
		Document document = documentDao.findBySn(sn);
		if (document == null) {
			throw new NoObjectException(sn);
		}
		return documentDao.delete(document);
	}
	
	public Document getDocument(String sn) throws BaseException {
		Document document = documentDao.findBySn(sn);
		if (document == null) {
			throw new NoObjectException(sn);
		}
		return document;
	}
	
	public List<Document> getAllDocuments() throws BaseException {
		return documentDao.findAllOnes();
	}

	public void setDocumentDao(DocumentDao documentDao) {
		this.documentDao = documentDao;
	}
	
	private String uploadFile(String sourcefile) throws BaseException {
		File uploadDirectory = new File(Constants.UPLOAD_PATH);
		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdirs();
		}
		
		byte[] buffer = new byte[1024];
		
		File tempFile =new File(sourcefile.trim());
		String filename = tempFile.getName();
		String extName = getExtensionName(filename);
		String absolutePath = Constants.UPLOAD_PATH + System.currentTimeMillis() + "." + extName;
		try {
		BufferedInputStream infile = new BufferedInputStream(new FileInputStream(tempFile));
		BufferedOutputStream targetfile = new BufferedOutputStream(new FileOutputStream(absolutePath)); 

		while (true) {
			int byteRead=infile.read(buffer); //从文件读数据给字节数组
            if (byteRead==-1) //在文件尾，无数据可读
                break;  //退出循环           
            targetfile.write(buffer,0,byteRead);  //将读到的数据写入目标文件
        }
		infile.close();
		targetfile.close();
		} catch (IOException e) {
			throw new FileException();
		}
		return absolutePath;
	}
	
	private void downloadFile(Document document, String targetPath) throws IOException {
		File downloadDirectory = new File(Constants.DOWNLOAD_PATH);
		if (!downloadDirectory.exists()) {
			downloadDirectory.mkdirs();
		}
		
		byte[] buffer = new byte[1024];
		
		File tempFile =new File(document.getAbsolutePath());
		String filename = document.getName();
		
		BufferedInputStream infile = new BufferedInputStream(new FileInputStream(tempFile));
		String saveFilePath = targetPath == null ? Constants.DOWNLOAD_PATH + filename : targetPath;
		BufferedOutputStream targetfile = new BufferedOutputStream(new FileOutputStream(saveFilePath)); 

		while (true) {
			int byteRead=infile.read(buffer); //从文件读数据给字节数组
            if (byteRead==-1) //在文件尾，无数据可读
                break;  //退出循环           
            targetfile.write(buffer,0,byteRead);  //将读到的数据写入目标文件
        }
		infile.close();
		targetfile.close();
	}

	@Override
	public Document downloadDocument(String sn, String targetPath) throws BaseException, IOException {
		Document document = documentDao.findBySn(sn);
		if (document == null) {
			throw new NoObjectException(sn);
		}
		downloadFile(document, targetPath);
		return document;
	}

	@Override
	public void clear() throws BaseException {
		List<Document> documents = documentDao.findAllOnes();
		for (Document document : documents) {
			documentDao.delete(document);
		}
	}

}

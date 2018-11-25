package edu.whut.cs.oo.dao.impl;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import edu.whut.cs.oo.common.Constants;
import edu.whut.cs.oo.dao.DocumentDao;
import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.exception.FileException;

public class DocumentDaoContainerFile extends DocumentDaoContainer implements DocumentDao {
	
	public DocumentDaoContainerFile() {
		
		//创建对象输入流的对象
		ObjectInputStream ois = null;
		try {
			File file = new File(Constants.DOCUMENT_FILE);
			if (!file.exists()) {
				file.createNewFile();
			}
			ois = new ObjectInputStream(new FileInputStream(Constants.DOCUMENT_FILE));
			if (ois != null) {
				while (true) {
					Document document = (Document) ois.readObject();
					super.insert(document);
				}
			}
		} catch(EOFException e) {
//			System.out.println("读到了文件的末尾");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BaseException e) {
			e.printStackTrace();
		} finally {
			//释放资源
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	private void saveToFile() throws FileException {
		//创建对象输出流的对象
				ObjectOutputStream oos;
				try {
					oos = new ObjectOutputStream(new FileOutputStream(Constants.DOCUMENT_FILE));
					//写出对象
					List<Document> documents = this.findAllOnes();
					for (Document document : documents) {
						oos.writeObject(document);
					}
					//释放资源
					oos.close();
				} catch (FileNotFoundException e) {
					throw new FileException();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}

	@Override
	public Document insert(Document object) throws BaseException {
		Document document = super.insert(object);
		saveToFile();
		return document;
	}

	@Override
	public Document update(Document object) throws BaseException  {
		Document document = super.update(object);
		saveToFile();
		return document;
	}

	@Override
	public Document delete(Document object) throws BaseException  {
		Document document = super.delete(object);
		saveToFile();
		return document;
	}

}

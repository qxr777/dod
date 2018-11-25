package edu.whut.cs.oo;

import edu.whut.cs.oo.dao.DocumentDao;
import edu.whut.cs.oo.dao.UserDao;
import edu.whut.cs.oo.dao.impl.DocumentDaoContainerFile;
import edu.whut.cs.oo.dao.impl.UserDaoContainerFile;
import edu.whut.cs.oo.domain.Administrator;
import edu.whut.cs.oo.domain.Browser;
import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.domain.Operator;
import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.service.DocumentService;
import edu.whut.cs.oo.service.UserService;
import edu.whut.cs.oo.service.impl.DocumentServiceImpl;
import edu.whut.cs.oo.service.impl.UserServiceImpl;

/**
 * @author qixin
 *
 */
public class Application {
	
	public static User currentUser;
	public static UserService userService;
	public static DocumentService documentService;
	
	static {
//		UserDao userDao = new UserDaoContainer();
		UserDao userDao = new UserDaoContainerFile();
		userService = new UserServiceImpl();
		userService.setUserDao(userDao);
		
//		DocumentDao documentDao = new DocumentDaoContainer();
		DocumentDao documentDao = new DocumentDaoContainerFile();
		documentService = new DocumentServiceImpl();
		documentService.setDocumentDao(documentDao);
		
		try {
			if (documentService.getAllDocuments().size() == 0) {
				documentService.clear();
				createDocuments();
			}
			if (userService.getAllUsers().size() == 0) {
				userService.clear();
				createUsers();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) throws Exception {
		System.out.println("请运行Application的子类");
	}
	
	protected static void createUsers() throws Exception {
		Administrator administrator = new Administrator();
		administrator.setName("Kate");
		administrator.setPassword("123");
		userService.createUser(administrator);
		
		Operator operator = new Operator();
		operator.setName("Jack");
		operator.setPassword("123");
		userService.createUser(operator);
		
		Browser browser = new Browser();
		browser.setName("Rose");
		browser.setPassword("123");
		userService.createUser(browser);
	}
	
	protected static void createDocuments() throws Exception {
		Document document = new Document();
		document.setSn("D001");
		document.setName("第一个档案");
		document.setDescription("这是一份很重要的档案");
		document.setUser(userService.getUser("Jack"));	
		document.setAbsolutePath("e:\\OOP\\uploadfile\\User.java");
		documentService.createDocument(document);
	}

}

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
import edu.whut.cs.oo.dao.UserDao;
import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.exception.FileException;

public class UserDaoContainerFile extends UserDaoContainer implements UserDao {
	
	public UserDaoContainerFile() {
		
		//创建对象输入流的对象
		ObjectInputStream ois = null;
		try {
			File file = new File(Constants.USER_FILE);
			if (!file.exists()) {
				file.createNewFile();
			}
			ois = new ObjectInputStream(new FileInputStream(Constants.USER_FILE));
			if (ois != null) {
				while (true) {
					User user = (User) ois.readObject();
					super.insert(user);
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
					oos = new ObjectOutputStream(new FileOutputStream(Constants.USER_FILE));
					//写出对象
					List<User> users = this.findAllOnes();
					for (User user : users) {
						oos.writeObject(user);
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
	public User insert(User object) throws BaseException {
		User user = super.insert(object);
		saveToFile();
		return user;
	}

	@Override
	public User update(User object) throws BaseException  {
		User user = super.update(object);
		saveToFile();
		return user;
	}

	@Override
	public User delete(User object) throws BaseException  {
		User user = super.delete(object);
		saveToFile();
		return user;
	}

}

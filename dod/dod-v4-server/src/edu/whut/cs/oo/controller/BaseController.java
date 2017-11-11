package edu.whut.cs.oo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.whut.cs.oo.Application;
import edu.whut.cs.oo.common.Constants;
import edu.whut.cs.oo.common.Message;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.service.DocumentService;
import edu.whut.cs.oo.service.UserService;

public abstract class BaseController {
	
	protected UserService userService = Application.userService;
	protected DocumentService documentService = Application.documentService;
	
	protected Message message;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	
	public abstract void service() throws BaseException;
	
	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}
	
	public void setIn(ObjectInputStream in) {
		this.in = in;
	}
	
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public void onException(BaseException e) {
		message.setCode(Constants.SERVER_EXCEPTION_CODE);
		message.setMsg(e.getMessage());
		
		try {
			out.writeObject(message);
			out.flush(); 
//			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void onSuccess() {
		try {
			out.writeObject(message);
			out.flush(); 
//			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	protected void sendFile(String absolutePath) throws IOException{	
		File file = new File(absolutePath);
		FileInputStream fis =new FileInputStream(file);
                     
        //文件长度
		out.writeLong(file.length());
		out.flush();
         
        //传输文件
        byte[] buffer =new byte[1024];            
        while(true){
        	int read = 0;  
        	if (fis != null) {  
        		read = fis.read(buffer);                   
        	}  
        	if (read == -1)   
        		break;            
        	out.write(buffer,0, read); 
        	out.flush();
        }       
        fis.close();
	}
	
	protected String receiveTempFile(String filename) throws IOException{			
		long fileLength = in.readLong();
//		String extName = documentService.getExtensionName(filename);
//		String tempPath = Constants.UPLOAD_TEMP_PATH + System.currentTimeMillis() + "." + extName;
		String tempPath = Constants.UPLOAD_TEMP_PATH + filename;
		File file = new File(tempPath);
		FileOutputStream fos =new FileOutputStream(file);
        byte[] buffer =new byte[1024];
        int transLen =0;
        System.out.println("----开始接收文件<" + filename +">,文件大小为<" + fileLength +">----");
        while(transLen<fileLength){
            int read =0;
            read = in.read(buffer);
            System.out.println(read);
            if(read == -1)
                break;
            transLen += read;
            System.out.println("接收文件进度" +100 * transLen/fileLength +"%..."+ transLen);
            fos.write(buffer,0, read);
            fos.flush();
        }
        fos.close();
        System.out.println("----接收文件<" + filename +">成功-------");		
        return tempPath;
	} 
	
    protected boolean deleteTempFile(String fileName){     
        File file = new File(fileName);     
        if(file.isFile() && file.exists()){     
            file.delete();     
            System.out.println("删除单个文件"+fileName+"成功！");     
            return true;     
        }else{     
            System.out.println("删除单个文件"+fileName+"失败！");     
            return false;     
        }     
    } 
	
}

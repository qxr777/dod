package edu.whut.cs.oo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import edu.whut.cs.oo.common.Constants;
import edu.whut.cs.oo.common.Message;
import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.exception.BaseException;
import edu.whut.cs.oo.exception.NetworkException;
import edu.whut.cs.oo.exception.ServerException;

public abstract class BaseServiceClient {
	protected Socket client;
	protected ObjectOutputStream output;
	protected ObjectInputStream input;
	
	protected void checkStatusCode(Message message) throws ServerException {
		if (message.getCode() == Constants.SERVER_EXCEPTION_CODE) {
			throw new ServerException(message.getMsg());
		}
	}
	
	protected Message send(Message message) throws BaseException {
		try {
			client = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_LISTEN_PORT);			
			output = new ObjectOutputStream(client.getOutputStream());
			output.writeObject(message);
			output.flush(); 			
			input = new ObjectInputStream(client.getInputStream());
			message = (Message)input.readObject();
			checkStatusCode(message);	
		} catch (IOException e) {
			throw new NetworkException();
		} catch (ClassNotFoundException e) {
			throw new NetworkException();
		} finally {
			try {
				output.close();
				input.close();
				client.close();				
			} catch (IOException e) {
				throw new NetworkException();
			}
		}
		return message;
	}
	
	protected Message sendDocument(Message message) throws BaseException {
		try {
			client = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_LISTEN_PORT);			
			output = new ObjectOutputStream(client.getOutputStream());
			output.writeObject(message);
			output.flush();
			
			Document document = (Document)message.getParameter("document");
			sendFile(document.getSourcePath());
			
			input = new ObjectInputStream(client.getInputStream());
			message = (Message)input.readObject();
			checkStatusCode(message);	
		} catch (IOException e) {
			throw new NetworkException();
		} catch (ClassNotFoundException e) {
			throw new NetworkException();
		} finally {
			try {
				output.close();
				input.close();
				client.close();				
			} catch (IOException e) {
				throw new NetworkException();
			}
		}
		return message;
	}
	
	protected Message receiveDocument(Message message, String targetPath) throws BaseException {
		try {
			client = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_LISTEN_PORT);			
			output = new ObjectOutputStream(client.getOutputStream());
			output.writeObject(message);
			output.flush(); 
			input = new ObjectInputStream(client.getInputStream());
			message = (Message)input.readObject();
			checkStatusCode(message);	
			Document document = (Document)message.getData();
			receiveFile(document.getName(), targetPath);
		} catch (IOException e) {
			throw new NetworkException();
		} catch (ClassNotFoundException e) {
			throw new NetworkException();
		} finally {
			try {
				output.close();
				input.close();
				client.close();				
			} catch (IOException e) {
				throw new NetworkException();
			}
		}
		return message;
	}
	
	protected void sendFile(String sourcePath) throws IOException{	
		File file = new File(sourcePath);
		FileInputStream fis =new FileInputStream(file);
                     
        //文件长度
		output.writeLong(file.length());
		output.flush();
         
        //传输文件
        byte[] buffer =new byte[1024];            
        while(true){
        	int read = 0;  
        	if (fis != null) {  
        		read = fis.read(buffer);                   
        	}  
        	if (read == -1)   
        		break;            
        	output.write(buffer,0, read); 
        	output.flush();
        }       
        fis.close();
	}
	
	protected void receiveFile(String filename, String targetPath) throws IOException{
		long fileLength = input.readLong();
		targetPath = targetPath != null ? targetPath : Constants.DOWNLOAD_PATH + filename;
		FileOutputStream fos = new FileOutputStream(targetPath);
		
        byte[] buffer =new byte[1024];
        int transLen =0;
        System.out.println("----开始接收文件<" + filename +">,文件大小为<" + fileLength +">----");
        while(transLen<fileLength){
            int read =0;
            read = input.read(buffer);
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
	}
}

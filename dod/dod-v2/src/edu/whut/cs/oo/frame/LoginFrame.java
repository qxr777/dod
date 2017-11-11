package edu.whut.cs.oo.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import edu.whut.cs.oo.Application;
import edu.whut.cs.oo.domain.User;
import edu.whut.cs.oo.service.UserService;

public class LoginFrame extends BaseFrame {		
	private JLabel jLabel1;
	private JTextField nameTextField;
	private JLabel jLabel2;
	private JPasswordField passwordField;	
	private JButton okButton,cancelButton;
	private JPanel panel;
	
	public LoginFrame() {
		setTitle("系统登录");
		
		setSize(380, 200);
		Toolkit toolkit = getToolkit();                    // 获得Toolkit对象
		Dimension dimension = toolkit.getScreenSize();     // 获得Dimension对象
		int screenHeight = dimension.height;               // 获得屏幕的高度
		int screenWidth = dimension.width;                 // 获得屏幕的宽度
		int frm_Height = this.getHeight();                 // 获得窗体的高度
		int frm_width = this.getWidth();                   // 获得窗体的宽度
		this.setLocation((screenWidth - frm_width) / 2,
				(screenHeight - frm_Height) / 2);          // 使用窗体居中显示
		setAlwaysOnTop(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		jLabel1 = new JLabel();
		jLabel1.setText("用户名");
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel1.setBounds(new Rectangle(86, 33, 60, 20));		
		panel.add(jLabel1);
				
		nameTextField = new JTextField();
		nameTextField.setBounds(new Rectangle(156, 33, 100, 20));
		nameTextField.setColumns(8);
		panel.add(nameTextField, null);
		
		jLabel2 = new JLabel();
		jLabel2.setBounds(new Rectangle(86, 63, 60, 20));
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel2.setText("密码");
		panel.add(jLabel2);		
		
		passwordField = new JPasswordField();		
		passwordField.setBounds(new Rectangle(156, 63, 100, 20));
		passwordField.requestFocus();
		panel.add(passwordField, null);		
		
		okButton = new JButton();
		okButton.setText("确定");
		okButton.setSize(new Dimension(60, 23));
		okButton.setLocation(new Point(86, 101));
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okButtonActionPerformed(e);
			}
		});
				
		panel.add(okButton, null);
		
		cancelButton= new JButton();
		cancelButton.setText("取消");
		cancelButton.setSize(new Dimension(60, 23));
		cancelButton.setLocation(new Point(194, 101));	
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed(e);
			}
		});		
		
		panel.add(cancelButton, null);
	}

//	private void okButtonActionPerformed(ActionEvent e){	
//		String name=nameTextField.getText();
//		String password=String.valueOf(passwordField.getPassword());
//		try{
//        	User user=DataProcessing.searchUser(name, password);
//
//			if (user==null){
//				System.out.println("用户名口令错误");
//				JOptionPane.showMessageDialog(panel,"帐号或密码错误，请重新输入","输入反馈",JOptionPane.YES_NO_OPTION);
//			}else{
//				loginUser.setName(name);
//				loginUser.setPassword(password);
//				loginUser.setRole(user.getRole());
//				hideFrame();
//				user.showMenu();
//			}
//        }catch(SQLException exception){
//        	System.out.println("数据库错误"+exception.getMessage());                        	
//        	//e.printStackTrace();                        	
//        }
//	}
	
	private void okButtonActionPerformed(ActionEvent e){	
		String name=nameTextField.getText();
		String password=String.valueOf(passwordField.getPassword());
		
		try {
			User user = userService.login(name, password);
			Application.currentUser = user;
			hideFrame();
			MainFrame mainframe=new MainFrame();
//			mainframe.setCurrentUser(user);
			mainframe.setVisible(true);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(panel,e1.getMessage(),"输入反馈",JOptionPane.YES_NO_OPTION);
		}
		
	}

	private void cancelButtonActionPerformed(ActionEvent e){
		nameTextField.setText(null);
		passwordField.setText(null);
	}

	private void hideFrame(){
		this.dispose();
		//this.setVisible(false);
	}

}

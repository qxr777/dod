package edu.whut.cs.oo.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import edu.whut.cs.oo.domain.Administrator;
import edu.whut.cs.oo.domain.Operator;

public class MainFrame extends BaseFrame {	
	private JPanel panel; 
	private JMenuBar menuBar;
	private JMenu menuUser,menuFile,menuSelf;
	private JMenuItem menuItem_modUser,menuItem_delUser,menuItem_addUser,
					  menuItem_listDoc,menuItem_upDoc,menuItem_downDoc,
					  menuItem_querySelf,menuItem_modSelf;
	
	private UserFrame userframe=null;
	private SelfFrame selfframe=null;
	private FileFrame fileframe=null;
	
	public MainFrame(){
				
		initFrame();
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuUser = new JMenu("用户管理");
		menuBar.add(menuUser);
		
		menuItem_modUser = new JMenuItem("修改用户");
		menuItem_modUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuItem_modUserActionPerformed(e);
			}
		});	
		menuUser.add(menuItem_modUser);
		
		menuItem_delUser = new JMenuItem("删除用户");
		menuItem_delUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuItem_delUserActionPerformed(e);
			}
		});
		menuUser.add(menuItem_delUser);
		
		menuItem_addUser = new JMenuItem("新增用户");
		menuItem_addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuItem_addUserActionPerformed(e);
			}
		});
		menuUser.add(menuItem_addUser);
		
		menuFile = new JMenu("档案管理");
		menuBar.add(menuFile);
		
		menuItem_upDoc = new JMenuItem("档案上传");
		menuItem_upDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuItem_upDocActionPerformed(e);
			}
		});
		menuFile.add(menuItem_upDoc);	
		
		menuItem_downDoc = new JMenuItem("档案下载");
		menuItem_downDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuItem_downDocActionPerformed(e);
			}
		});
		menuFile.add(menuItem_downDoc);
		
		menuSelf = new JMenu("个人信息管理");
		menuBar.add(menuSelf);
		
		menuItem_modSelf = new JMenuItem("信息修改");
		menuItem_modSelf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuItem_modSelfActionPerformed(e);
			}
		});
		menuSelf.add(menuItem_modSelf);	
		
		setFrameByRole();
		
//		setVisible(true);
	}
	
	private void initFrame() {
		
		Toolkit toolkit = getToolkit();                    // 获得Toolkit对象
		Dimension dimension = toolkit.getScreenSize();     // 获得Dimension对象
		int screenHeight = dimension.height;               // 获得屏幕的高度
		int screenWidth = dimension.width;                 // 获得屏幕的宽度
		this.setSize(screenWidth, screenHeight);           // 获得窗体的高度
		
		this.setLocation(0,0); 
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		setResizable(false);
				
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}
	
	private void setFrameByRole() {
		if (currentUser instanceof Administrator) {
			setAdminFrame();
		} else if (currentUser instanceof Operator) {
			setOperatorFrame();
		} else {
			setBrowserFrame();
		}
	}
	
	public void setAdminFrame(){
		setTitle("系统管理员界面");	
		menuItem_upDoc.setEnabled(false);
	}
	
	public void setOperatorFrame(){
		setTitle("档案录入员界面");
		menuUser.setEnabled(false);		
	}
	
	public void setBrowserFrame(){
		setTitle("档案浏览员界面");
		menuUser.setEnabled(false);
		menuItem_upDoc.setEnabled(false);
	}
	
	public static void launch() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}
	
	private void menuItem_modUserActionPerformed(ActionEvent e){
		if (userframe==null){
			userframe= new UserFrame();
		}
		userframe.setTabSeq(1);
		userframe.setVisible(true);

	}
	
	private void menuItem_delUserActionPerformed(ActionEvent e){
		if (userframe==null){
			userframe= new UserFrame();
		}			
		userframe.setTabSeq(2);
		userframe.setVisible(true);		
	}
	
	private void menuItem_addUserActionPerformed(ActionEvent e){
		if (userframe==null){
			userframe= new UserFrame();
		}			
		userframe.setTabSeq(0);
		userframe.setVisible(true);	
	}
	
	private void menuItem_upDocActionPerformed(ActionEvent e){
		if (fileframe==null){
			fileframe= new FileFrame();
		}
		fileframe.setTabSeq(1);
		fileframe.setVisible(true);
		
	}
	
	private void menuItem_downDocActionPerformed(ActionEvent e){
		if (fileframe==null){
			fileframe= new FileFrame();
		}
		fileframe.setTabSeq(0);
		fileframe.setVisible(true);
	}

	private void menuItem_modSelfActionPerformed(ActionEvent e){
		if (selfframe==null){
			selfframe= new SelfFrame();
		}		
		selfframe.setVisible(true);
	}
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

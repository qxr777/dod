package edu.whut.cs.oo.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SelfFrame extends BaseFrame {
	private JPanel panel;
	private JTextField nametextField,roletextField;
	private JPasswordField oldpasswordField,passwordField1, passwordField2;	
	
	public SelfFrame() {		
		setTitle("个人信息管理");
		setSize(400, 300);
		Toolkit toolkit = getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int screenHeight = dimension.height;
		int screenWidth = dimension.width;
		int frm_Height = this.getHeight();
		int frm_width = this.getWidth();
		this.setLocation((screenWidth - frm_width) / 2,
				(screenHeight - frm_Height) / 2);		
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel nameLabel = new JLabel("用户名");
		nameLabel.setBounds(30, 30, 55, 15);
		panel.add(nameLabel);
		
		JLabel oldkeyLabel = new JLabel("原口令");
		oldkeyLabel.setBounds(30, 70, 55, 15);
		panel.add(oldkeyLabel);
		
		JLabel newkey1Label = new JLabel("新口令");
		newkey1Label.setBounds(30, 110, 55, 15);
		panel.add(newkey1Label);
		
		JLabel newkey2Label = new JLabel("确认新口令");
		newkey2Label.setBounds(10, 150, 75, 15);
		panel.add(newkey2Label);
		
		JLabel roleLabel = new JLabel("角色");
		roleLabel.setBounds(30, 190, 55, 15);
		panel.add(roleLabel);
		
		nametextField = new JTextField();
		nametextField.setBounds(120, 30, 130, 20);
		panel.add(nametextField);
		nametextField.setColumns(10);
		nametextField.setEditable(false);
		
		oldpasswordField = new JPasswordField();
		oldpasswordField.setBounds(120, 70, 130, 20);
		panel.add(oldpasswordField);
		
		passwordField1 = new JPasswordField();
		passwordField1.setBounds(120, 110, 130, 20);
		panel.add(passwordField1);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(120, 150, 130, 20);
		panel.add(passwordField2);
		
		roletextField = new JTextField();
		roletextField.setBounds(120, 190, 130, 20);
		panel.add(roletextField);
		roletextField.setColumns(10);
		roletextField.setEditable(false);
		
		JButton modButton = new JButton("修改");
		modButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modButtonActionPerformed(e);				
			}
		});
		modButton.setBounds(121, 230, 65, 23);
		panel.add(modButton);
		
		JButton cancelButton = new JButton("返回");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed(e);
			}
		});
		cancelButton.setBounds(187, 230, 65, 23);
		panel.add(cancelButton);
		
		showUserInfoToPanel();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	
}

	public static void launch() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new SelfFrame().setVisible(true);
			}
		});
	}
	
	private void modButtonActionPerformed(ActionEvent evt) {
		String username = getCurrentUser().getName();
		String oldpwd = new String(oldpasswordField.getPassword());
		String newpwd = new String(passwordField1.getPassword());
		String oknewpwd = new String(passwordField2.getPassword());
		if (newpwd == null || newpwd.equals("")) {
			JOptionPane.showMessageDialog(this, "原密码不能为空！！！");
		}
		else if (oldpwd.equals(getCurrentUser().getPassword())) {
			if (newpwd == null || newpwd.equals("")) {
				JOptionPane.showMessageDialog(this, "新密码不能为空！！！");
			}
			else if (oknewpwd == null || oknewpwd.equals("")) {
				JOptionPane.showMessageDialog(this, "确认新密码不能为空！！！");
			}
			else if (!newpwd.equals(oknewpwd)) {
				JOptionPane.showMessageDialog(this, "新密码和确认新密码不一致！！！");
			}
			else {
				try {
					if (JOptionPane.showConfirmDialog(this,
							"确定要修改密码吗？\t\n单击确定按钮将修改。", "确认对话框",
							JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						userService.changePassword(currentUser, newpwd);
						
						JOptionPane.showMessageDialog(this, "密码修改成功！！！");
						currentUser.setPassword(newpwd);
					}
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(this, "数据库异常。\t\n"
							+ ex.getMessage());
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "原密码不正确！！！");
		}
		oldpasswordField.setText(null);
		passwordField1.setText(null);
		passwordField2.setText(null);
		oldpasswordField.requestFocus();		
	}
	
	private void showUserInfoToPanel(){
		nametextField.setText(getCurrentUser().getName());
		roletextField.setText(getCurrentUser().getClass().getSimpleName());		
	}
	
	private void cancelButtonActionPerformed(ActionEvent evt){
		this.dispose();
	}

}

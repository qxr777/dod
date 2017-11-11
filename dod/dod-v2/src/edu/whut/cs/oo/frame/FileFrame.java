package edu.whut.cs.oo.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import edu.whut.cs.oo.domain.Document;
import edu.whut.cs.oo.domain.Operator;
import edu.whut.cs.oo.exception.BaseException;

public class FileFrame extends BaseFrame{
	
	private DefaultTableModel tableModel;
	private JTable table;
	JPanel panel_up,panel_down;
	JTextField DoctextField,filetextField;
	JTabbedPane tabbedPane ;
	JTextArea textArea;
	
	public FileFrame() {		
		setTitle("文件管理界面");
		setSize(400, 300);
		Toolkit toolkit = getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int screenHeight = dimension.height;
		int screenWidth = dimension.width;
		int frm_Height = this.getHeight();
		int frm_width = this.getWidth();
		this.setLocation((screenWidth - frm_width) / 2,
				(screenHeight - frm_Height) / 2);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		//文件下载卡片
		JPanel panel_down = new JPanel();
		tabbedPane.addTab("文件下载", null, panel_down, null);
		panel_down.setLayout(null);		
				
		JScrollPane scrollPane = new JScrollPane();
		panel_down.add(scrollPane);
		
		scrollPane.setBounds(10, 10, 359, 126);
		table = new JTable();	
		
		showFileInfoToTable();		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	
		JButton downButton = new JButton("下载");
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				downButtonActionPerformed(e);
			}
		});
		downButton.setBounds(121, 170, 65, 23);
		panel_down.add(downButton);
		
		JButton cancelButton_del = new JButton("返回");
		cancelButton_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButton_delActionPerformed(e);
			}
		});
		cancelButton_del.setBounds(187, 170, 65, 23);
		panel_down.add(cancelButton_del);
		
		//文件上传卡片
		panel_up = new JPanel();
		tabbedPane.addTab("文件上传", null, panel_up, null);
		panel_up.setLayout(null);
		
		JLabel DocLabel = new JLabel("档案号");
		DocLabel.setBounds(32, 28, 54, 15);
		panel_up.add(DocLabel);		
		
		JLabel DescriptionLabel = new JLabel("档案描述");
		DescriptionLabel.setBounds(32, 71, 54, 15);
		panel_up.add(DescriptionLabel);
		
		JLabel fileLabel = new JLabel("档案文件名");
		fileLabel.setBounds(32, 151, 65, 15);
		panel_up.add(fileLabel);
		
		DoctextField = new JTextField();
		DoctextField.setBounds(121, 25, 131, 21);
		panel_up.add(DoctextField);
		DoctextField.setColumns(10);
		
		JButton upButton = new JButton("上传");
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upButtonActionPerformed(e);				
			}
		});
		upButton.setBounds(121, 188, 65, 23);
		panel_up.add(upButton);
		
		JButton cancelButton = new JButton("取消");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed(e);
			}
		});
		cancelButton.setBounds(187, 188, 65, 23);
		panel_up.add(cancelButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(123, 67, 129, 60);
		panel_up.add(scrollPane_1);
		
		textArea = new JTextArea();
//		textArea.setBounds(123, 67, 129, 60);
//		scrollPane_1.add(textArea);
		scrollPane_1.setViewportView(textArea);
		
		JButton fileButton = new JButton("打开");
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileButtonActionListener(e);
			}
		});
		fileButton.setBounds(262, 147, 65, 23);
		panel_up.add(fileButton);
		
		filetextField = new JTextField();
		filetextField.setBounds(121, 148, 131, 21);
		panel_up.add(filetextField);
		filetextField.setColumns(255);
		
		
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				jTabbedPaneStateChanged(evt);
			}
		});
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		if (currentUser instanceof Operator) 
			tabbedPane.setEnabledAt(1, true);
		else
			tabbedPane.setEnabledAt(1, false);
	}
	
	public static void launch() {	   
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FileFrame().setVisible(true);
			}
		});
	}
	
	public void setTabSeq(int index){
		tabbedPane.setSelectedIndex(index);
	}
	
	private void jTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {
		if(tabbedPane.getSelectedIndex()==0){
			showFileInfoToTable();
		}		
	}
	
	public void showFileInfoToTable() {
		try {
			String[] colName = {"档案号", "创建者", "时间","文件名","描述"};
			String[][] tableValue = new String[10][5];
			List<Document> documents = documentService.getAllDocuments();
			for (int row = 0; row < documents.size(); row++) {
				Document doc = documents.get(row);
				tableValue[row][0]=doc.getSn();
				tableValue[row][1]=doc.getUser().getName();
				tableValue[row][2]=(doc.getTimestamp()).toString();
				tableValue[row][3]=doc.getName();
				tableValue[row][4]=doc.getDescription();
			}
			tableModel = new DefaultTableModel(tableValue, colName);
			table.setModel(tableModel);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void upButtonActionPerformed(ActionEvent evt) {
	try{
		if (JOptionPane.showConfirmDialog(this,
				"确定上传档案吗？\t\n单击确定按钮将上传。", "确认对话框",
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			String ID=DoctextField.getText();
			String filename=filetextField.getText();
			String description=(String)textArea.getText();
			
			Document document = new Document();
			document.setSn(ID);
			document.setSourcePath(filename);
			document.setDescription(description);
			
			documentService.uploadDocument(document);
			
			DoctextField.setText("");
			filetextField.setText("");
			textArea.setText("");
			JOptionPane.showMessageDialog(this, "上传成功！");	
		}
	}catch (BaseException be) {
		JOptionPane.showMessageDialog(this, be.getMessage());		
	}catch(Exception ex){
		JOptionPane.showMessageDialog(this,"上传文件失败！");
	}
}
	
	private void cancelButtonActionPerformed(ActionEvent evt){
		DoctextField.setText("");
		filetextField.setText("");
		textArea.setText("");
	}
	
	private void downButtonActionPerformed(ActionEvent evt){
		try{
			if (JOptionPane.showConfirmDialog(this,
					"确定要下载档案吗？\t\n单击确定按钮将下载。", "确认对话框",
					JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				int currentRow = table.getSelectedRow();
				String documentSn = "";
				String filename = "";						
				Object object = tableModel.getValueAt(currentRow, 0);
				if (object != null) {
					documentSn = object.toString();
					filename = (String)tableModel.getValueAt(currentRow, 3);
				}

				FileDialog dlg_save=new FileDialog(this," 保存文件对话框",FileDialog.SAVE);
				dlg_save.setFile(filename);
				dlg_save.setVisible(true);
				if(dlg_save.getFile()!=null){  
		            File downfile=new File(dlg_save.getDirectory(),dlg_save.getFile());
		            
		            Document document = documentService.getDocument(documentSn);
		            documentService.downloadDocument(documentSn, downfile.getAbsolutePath());
		            
		            JOptionPane.showMessageDialog(this, "下载成功！");				
				}
			}
		}catch (BaseException be) {
			JOptionPane.showMessageDialog(this, be.getMessage());
		}catch(Exception ex){
			 JOptionPane.showMessageDialog(this, "下载失败！");
		}
	}
	
	private void fileButtonActionListener(ActionEvent evt){
		FileDialog dlg_open=new FileDialog(this,"打开文件对话框",FileDialog.LOAD);
		dlg_open.setVisible(true);
		if(dlg_open.getFile()!=null){  
			//File file=new File(dlg_open.getDirectory(),dlg_open.getFile());  
            filetextField.setText(dlg_open.getDirectory()+dlg_open.getFile());
        }  
		
	}
	
	private void cancelButton_delActionPerformed(ActionEvent evt){
		this.dispose();
	}
	
	public static void main(String[] args) {
		FileFrame.launch();				
	}
}

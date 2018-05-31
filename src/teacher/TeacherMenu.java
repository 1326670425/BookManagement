package teacher;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;
import main.FileOperation;
import main.TableList;

public class TeacherMenu extends JPanel{
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JScrollPane spanel;
	private JLabel lname;
	private JLabel lPublishingHouse;
	private JLabel lwriter;
	private JLabel lclass;
	private JTextField tname;
	private JTextField tPublishingHouse;
	private JTextField twriter;
	private JTextField tclass;
	private JButton submit1;
	private JButton submit2;
	private JButton openFile;
	private TableList table;
	private Object[] item;
	private Object[][] bookList;
	
	public TeacherMenu(){
		this.setLayout(new BorderLayout());
		item = new Object[]{"����","������","����","�༶"};
		panel1 = new JPanel();
		lname = new JLabel("������",JLabel.RIGHT);
		lPublishingHouse = new JLabel("�����磺",JLabel.RIGHT);
		lwriter = new JLabel("���ߣ�",JLabel.RIGHT);
		lclass = new JLabel("�༶��",JLabel.RIGHT);
		tname = new JTextField();
		tPublishingHouse = new JTextField();
		twriter = new JTextField();
		tclass = new JTextField();
		submit1 = new JButton("�ύ");
		MySubmitListener submitListener = new MySubmitListener();
		submit1.addActionListener(submitListener);
		openFile = new JButton("�򿪱����ļ�");
		openFile.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = 0;
				//����OpenFile�����ļ�����
				FileOperation of = new FileOperation();
				List<List<String>> list = of.open();
				if(list==null)
					return ;
				bookList = new Object[list.size()][4];
				for(List<String> eachRow:list){
					eachRow.toArray(bookList[i++]);
				}
				table.setModel(new DefaultTableModel(bookList,item));
				panel3.setVisible(true);
			}
		
		});
		panel1.setLayout(new GridLayout(1,10,30,30));
		panel1.setBorder(new EmptyBorder(10,50,10,50));
		panel1.add(lname);
		panel1.add(tname);
		panel1.add(lPublishingHouse);
		panel1.add(tPublishingHouse);
		panel1.add(lwriter);
		panel1.add(twriter);
		panel1.add(lclass);
		panel1.add(tclass);
		panel1.add(submit1);
		panel1.add(openFile);
		this.add(panel1,BorderLayout.NORTH);
		
		table = new TableList();
		spanel = new JScrollPane(table);
		panel2 = new JPanel(new GridLayout());
		panel2.add(spanel);
		this.add(panel2, BorderLayout.CENTER);
		
		panel3 = new JPanel();
		submit2 = new JButton("�ύ��");
		submit2.addActionListener(submitListener);
		panel3.add(submit2);
		panel3.setVisible(false);
		this.add(panel3,BorderLayout.SOUTH);

		this.setVisible(true);
	}

	class MySubmitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name = "";
			String publishingHouse = "";
			String writer = "";
			String classNo = "";
			Object[][] list = null;
			if(e.getSource() == submit1){
				name = tname.getText();
				publishingHouse = tPublishingHouse.getText();
				writer = twriter.getText();
				classNo = tclass.getText();
				if(name.equals("")||publishingHouse.equals("")||writer.equals("")||classNo.equals("")){
					JOptionPane.showMessageDialog(null, "�������Ϊ��");
					return;
				}
				else{
					list = new Object[1][4];
					list[0] = new Object[]{name,publishingHouse,writer,classNo};
				}
			}
			else{
				list = bookList;
			}
			int i = JOptionPane.showConfirmDialog(null, "ȷ���ύ?\n����������Ϣ","��ʾ",JOptionPane.YES_NO_OPTION);
			if(i==JOptionPane.YES_OPTION){
				
			}

		}
		
	}

}

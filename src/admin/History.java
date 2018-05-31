package admin;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import main.TableList;
import main.CreateTable;
import main.FileOperation;

public class History extends JPanel{
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JScrollPane spanel;
	private JButton in;
	private JButton out;
	private JButton print;

	private TableList table;
	private Object[] item;
	private Object[][] list;
	
	public History(){
		this.setLayout(new BorderLayout());
		MyListener listener = new MyListener();
		in = new JButton("入库记录");
		out = new JButton("出库记录");

		in.addActionListener(listener);
		out.addActionListener(listener);
		panel1 = new JPanel();
		panel1.add(in);
		panel1.add(out);
		print = new JButton("打印报表");
		print.setVisible(false);
		print.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(list==null){
					JOptionPane.showMessageDialog(null, "无数据表","警告",JOptionPane.WARNING_MESSAGE);
					return;
				}
				FileOperation fo = new FileOperation();
				fo.save(item, list);
			}
			
		});
		panel1.setLayout(new GridLayout(1,2,100,100));
		panel1.setBorder(new EmptyBorder(10,200,10,200));
		this.add(panel1,BorderLayout.NORTH);
		
		table = new TableList();
		spanel = new JScrollPane(table);
		panel2 = new JPanel(new GridLayout());
		panel2.add(spanel);
		this.add(panel2, BorderLayout.CENTER);
		panel3 = new JPanel();
		panel3.add(print);
		this.add(panel3,BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String sql = "";
			item = new Object[]{""};
			if(e.getSource() == in){
				sql = "";
			}
			else{
				sql = "";
			}
			
			CreateTable ct = new CreateTable();
			list = ct.createTable(sql);
			if(list==null){
				JOptionPane.showMessageDialog(null, "无结果！", "警告", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			table.setModel(new DefaultTableModel(list,item));
			print.setVisible(true);
		}
		
	}

}

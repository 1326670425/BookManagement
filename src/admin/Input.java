package admin;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.QueryUpdate;
import main.FileOperation;
import main.TableList;

public class Input extends JPanel{
	private JPanel panelUp;
	private JPanel panel1;
	private JPanel panel2;
	private JScrollPane spanel;
	private JPanel panelDown;
	private JRadioButton input1;
	private JRadioButton input2;
	private ButtonGroup bg;
	private JButton submit;
	private JLabel lid;
	private JLabel lname;
	private JLabel lwriter;
	private JLabel lpublishingHouse;
	private JLabel lcount;
	private JLabel lprice;
	private JLabel ldetail;
	private JTextField tid;
	private JTextField tname;
	private JTextField twriter;
	private JTextField tpublishingHouse;
	private JTextField tcount;
	private JTextField tprice;
	private JTextField tdetail;
	private CardLayout card;
	private TableList table;
	private Object[] item;
	private Object[][] bookList;
	public Input(){
		this.setLayout(new BorderLayout());
		
		panelUp = new JPanel();
		card = new CardLayout();
		panelUp.setLayout(card);
		
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(8,2,30,30));
		panel1.setBorder(new EmptyBorder(10,50,10,400));
		
		input1 = new JRadioButton("逐条入库");
		input2 = new JRadioButton("读文件入库");
		input1.setSelected(true);
		
		submit = new JButton("入库");
		bg = new ButtonGroup();
		bg.add(input1);
		bg.add(input2);
		MyRadioListener listener = new MyRadioListener();
		input1.addActionListener(listener);
		input2.addActionListener(listener);
		submit.addActionListener(new MyListener());
		
		lid = new JLabel("教材编号:",JLabel.RIGHT);
		lname = new JLabel("教材名称:",JLabel.RIGHT);
		lwriter = new JLabel("教材作者:",JLabel.RIGHT);
		lpublishingHouse = new JLabel("教材出版社:",JLabel.RIGHT);
		lcount = new JLabel("教材数量:",JLabel.RIGHT);
		lprice = new JLabel("教材价格:",JLabel.RIGHT);
		ldetail = new JLabel("教材简介:",JLabel.RIGHT);
		tid = new JTextField();
		tname = new JTextField();
		twriter = new JTextField();
		tpublishingHouse = new JTextField();
		tcount = new JTextField();
		tprice = new JTextField();
		tdetail = new JTextField();
		panel1.add(lid);
		panel1.add(tid);
		panel1.add(lname);
		panel1.add(tname);
		panel1.add(lwriter);
		panel1.add(twriter);
		panel1.add(lpublishingHouse);
		panel1.add(tpublishingHouse);
		panel1.add(lcount);
		panel1.add(tcount);
		panel1.add(lprice);
		panel1.add(tprice);
		panel1.add(ldetail);
		panel1.add(tdetail);
		
		panelUp.add(panel1,"1");
		
		table = new TableList();
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		spanel = new JScrollPane(table);
		panel2.add(spanel);
		panelUp.add(panel2,"2");
		this.add(panelUp,BorderLayout.CENTER);
		
		panelDown = new JPanel();
		panelDown.add(input1);
		panelDown.add(input2);
		panelDown.add(submit);
		this.add(panelDown,BorderLayout.SOUTH);

	}
	class MyRadioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(input1.isSelected())
				card.show(panelUp, "1");
			if(input2.isSelected()){
				FileOperation of = new FileOperation();
				int i = 0;
				List<List<String>> list = of.open();
				if(list==null)
					return ;
				bookList = new Object[list.size()][7];
				item = new Object[]{"编号","书名","作者","出版社","数量","价格","备注"};
				for(List<String> eachRow:list){
					eachRow.toArray(bookList[i++]);
				}
				table.setModel(new DefaultTableModel(bookList,item));
				card.show(panelUp, "2");
			}
		}
	}
	class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String sql = "insert into repertory values (%s,%s,%s,%s,%s,%s,%s)";
			QueryUpdate qu;
			qu = new QueryUpdate();
			
			if(input1.isSelected()){
				
			}
			else{
				for(int i=0;i<bookList.length;i++){
					String.format(sql, bookList[i][0],bookList[i][1],bookList[i][2],bookList[i][3],
							bookList[i][4],bookList[i][6],bookList[i][6],bookList[i][7]
							);
					qu.executeUpdate(sql);
				}
			}
			qu.close();
		}
		
	}
	public static void main(String[] args){
		new Input();
	}
}

package admin;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.QueryUpdate;
import main.CreateTable;
import main.FileOperation;
import main.TableList;

import java.sql.*;

public class Query extends JPanel{
	private JRadioButton name;
	private JRadioButton writer;
	private JRadioButton id;
	private JRadioButton PublishingHouse;
	private ButtonGroup jbg;
	private JTextField text;
	private JButton search;
	private JButton print;
	private JPanel panelUp;
	private JPanel panelUp1;
	private JPanel panelUp2;
	private JPanel panelDown;
	private JScrollPane spanelDown;
	private TableList table;
	final Object[] item = {"编号","书名","作者","出版社","库存量","价格","简介"};
	public Object[][] bookList = null; 
	public int count = 0;
	public Query(){
/*		this.setTitle("库存查询");
		this.setSize(getMaximumSize());*/
		this.setLayout(new BorderLayout());
		panelUp = new JPanel();
		panelUp1 = new JPanel();
		panelUp2 = new JPanel();
		panelDown = new JPanel(new GridLayout (0, 1));

		panelUp1.setBorder(BorderFactory.createTitledBorder("检索条件"));
		panelUp.setLayout(new GridLayout(1,2,30,30));
		panelUp.setBorder(new EmptyBorder(20,60,20,20));
		panelUp1.setLayout(new GridLayout(1,4,30,30));

		name = new JRadioButton("书名");
		name.setSelected(true);
		writer = new JRadioButton("作者");
		id = new JRadioButton("编号");
		PublishingHouse = new JRadioButton("出版社");
		jbg = new ButtonGroup();
		jbg.add(name);
		jbg.add(id);
		jbg.add(writer);
		jbg.add(PublishingHouse);
		panelUp1.add(name);
		panelUp1.add(id);
		panelUp1.add(writer);
		panelUp1.add(PublishingHouse);
		
		text = new JTextField(20);
		text.addFocusListener(new MyHintListener(text,"软件详细设计"));
		text.setFont(new Font("Dialog",1,18));
		search = new JButton("搜索");
		search.setPreferredSize(new Dimension(100,50));
		search.addActionListener(new MySearchListener());
		print = new JButton("打印报表");
		print.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileOperation fo = new FileOperation();
				fo.save(item, bookList);
			}
			
		});
		panelUp2.add(text);
		panelUp2.add(search);
		panelUp2.add(print);

		panelUp.add(panelUp1);
		panelUp.add(panelUp2);

		this.add(panelUp,BorderLayout.NORTH);
		
		table = new TableList();
		table.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				if(e.getButton()==MouseEvent.BUTTON3)
					popupMenu(table,e.getX(),e.getY());
			}
		});
		//table.setVisible(false);
		spanelDown = new JScrollPane(table);
		panelDown.add(spanelDown);
		this.add(panelDown,BorderLayout.CENTER);
/*		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);*/
	}
	public void popupMenu(JTable invoker,int x,int y){
		JPopupMenu menu = new JPopupMenu();
		JMenuItem in = new JMenuItem("入库");
		JMenuItem out = new JMenuItem("出库");
		JMenuItem revise = new JMenuItem("修改教材该信息");
		class MyMenuListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str;
				int number=0;

				if(e.getSource()==revise){
					str = JOptionPane.showInputDialog("请输入新的信息");
					reviseInfo((String)bookList[invoker.getSelectedRow()][0],str,invoker.getSelectedColumn());
					bookList[invoker.getSelectedRow()][invoker.getSelectedColumn()]=str;
				}
				else{
					if(e.getSource()==in){
						str = JOptionPane.showInputDialog("请输入入库数量");
						number = Integer.parseInt(str);
						number += Integer.parseInt((String)bookList[invoker.getSelectedRow()][4]);
					}
					if(e.getSource()==out){
						while(true){
							str = JOptionPane.showInputDialog("请输入出库数量");
							number = Integer.parseInt(str);
							number = Integer.parseInt((String)bookList[invoker.getSelectedRow()][4])-number;
							if(number<=0){
								JOptionPane.showMessageDialog(null, "库存量不足\n请重新输入","警告",JOptionPane.WARNING_MESSAGE);
							}else{
								break;
							}
						}
					}
					String info = String.valueOf(number);
					reviseInfo((String)bookList[invoker.getSelectedRow()][0],info,4);
					bookList[invoker.getSelectedRow()][4]=info;
				}
				
				JOptionPane.showMessageDialog(null, "修改成功");
				invoker.setModel(new DefaultTableModel(bookList,item));
			}
		}
		in.addActionListener(new MyMenuListener());
		out.addActionListener(new MyMenuListener());
		revise.addActionListener(new MyMenuListener());
		menu.add(in);
		menu.add(out);
		menu.add(revise);
		menu.show(invoker, x, y);
	}
	public void reviseInfo(String id,String info,int index){
		String s = "";
		switch(index){
		case 0:
			s = "bid";
			break;
		case 1:
			s = "bname";
			break;
		case 2:
			s = "bwriter";
			break;
		case 3:
			s = "bpubhouse";
			break;
		case 4:
			s = "bcount";
			break;
		case 5:
			s = "bprice";
			break;
		default:
			s = "detail";
		}
		QueryUpdate qu = new QueryUpdate();
		String sql = "update repertory set "+s+"='"+info+"' where bid='"+id+"'";
		qu.executeUpdate(sql);
		qu.close();

	}
	class MySearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String condition = "";
			String str = "'"+text.getText()+"'";
			count = 0;

			if(name.isSelected())
				condition = "bname";
			if(id.isSelected())
				condition = "bid";
			if(PublishingHouse.isSelected())
				condition = "bpubhouse";
			if(writer.isSelected())
				condition = "bwriter";

			String sql = "select * from repertory where "+condition+"="+str;
			CreateTable ct = new CreateTable();
			bookList = ct.createTable(sql);
			
			if(bookList==null){
				JOptionPane.showMessageDialog(null, "无匹配结果！", "警告", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			table.setModel(new DefaultTableModel(bookList,item));		
		}
		
	}
	//搜索框默认输入
	class MyHintListener implements FocusListener{
		private JTextField text;
		private String hintText;
		public MyHintListener(JTextField jTextField,String hintText) {  
	        this.text = jTextField;  
	        this.hintText = hintText;  
	        jTextField.setText(hintText);  
	        jTextField.setForeground(Color.GRAY);  
	    }  
	  
	    @Override  
	    public void focusGained(FocusEvent e) {  
	        //获取焦点时，清空提示内容  
	        String temp = text.getText();  
	        if(temp.equals(hintText)) {  
	            text.setText("");  
	            text.setForeground(Color.BLACK);  
	        }  
	          
	    }  
	  
	    @Override  
	    public void focusLost(FocusEvent e) {     
	        //失去焦点时，没有输入内容，显示提示内容  
	        String temp = text.getText();  
	        if(temp.equals("")) {  
	            text.setForeground(Color.GRAY);  
	            text.setText(hintText);  
	        }  
	    }
	}

}

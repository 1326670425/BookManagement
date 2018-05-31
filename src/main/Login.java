package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import database.QueryUpdate;

import java.sql.*;


public class Login extends JFrame{
	private JLabel Lname;
	private JLabel Lpassword;
	private JLabel Ltitle;
	private JTextField Tname;
	private JPasswordField Tpassword;
	private JButton enter;
	private JButton cencel;
	private JButton register;
	private JRadioButton teacher;
	private JRadioButton student;
	private JRadioButton admin;
	private ButtonGroup bg;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel21;
	private JPanel panel22;
	private JPanel panel3;
	public Login(){
		this.setSize(400, 300);
		this.setLocation(400, 200);
		this.setTitle("用户登录");
		//this.setLayout(new BorderLayout());
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel21 = new JPanel();
		panel21.setLayout(new GridLayout(2,2,20,20));
		panel21.setBorder(new EmptyBorder(0,0,10,110));
		panel22 = new JPanel();
		panel22.setBorder(BorderFactory.createTitledBorder("身份选择"));
		panel3 = new JPanel();
		Ltitle = new JLabel("教材管理系统");
		Ltitle.setFont(new Font("Dialog",1,25));
		panel1.add(Ltitle);
		this.add(panel1,BorderLayout.NORTH);
		
		Lname = new JLabel("用户名：",JLabel.RIGHT);
		Lpassword = new JLabel("密    码：",JLabel.RIGHT);
		Tname = new JTextField(15);
		Tpassword = new JPasswordField(15);
		//Lname.setSize(50, 20);
		//Lpassword.setSize(50, 20);
		//Tname.setSize(50, 20);
		//Tpassword.setSize(50, 20);
		teacher = new JRadioButton("教师");
		teacher.setSelected(true);
		student = new JRadioButton("学生");
		admin = new JRadioButton("管理员");
		bg = new ButtonGroup();
		bg.add(teacher);
		bg.add(student);
		bg.add(admin);
		panel21.add(Lname);
		panel21.add(Tname);
		panel21.add(Lpassword);
		panel21.add(Tpassword);
		panel22.add(teacher);
		panel22.add(student);
		panel22.add(admin);
		panel2.add(panel21);
		panel2.add(panel22);
		this.add(panel2,BorderLayout.CENTER);
		
		enter = new JButton("登录");
		cencel = new JButton("取消");
		register = new JButton("注册");
		enter.addActionListener(new MyListener());
		cencel.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Tname.setText("");
				Tpassword.setText("");
			}
			
		});
		register.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[] identify = {"教师","学生","取消"};
				int n = JOptionPane.showOptionDialog(null, "请选择注册身份", "身份选择",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, identify, identify[0]);
				if(n==JOptionPane.YES_OPTION){
					new Register();
					Register.Lid.setText("教师工号：");
					Register.Sid = "teacher";
					dispose();
				}
				if(n==JOptionPane.NO_OPTION){
					new Register();
					Register.Lid.setText("学号：");
					Register.Sid = "student";
					dispose();
				}
			}
		});
		panel3.add(enter);
		panel3.add(cencel);
		panel3.add(register);
		this.add(panel3,BorderLayout.SOUTH);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name = Tname.getText();
			String password = new String(Tpassword.getPassword());
			if(name.equals("")||password.equals(""))
				JOptionPane.showMessageDialog(null, "用户名或密码为空！","提示",JOptionPane.WARNING_MESSAGE);
			String identify="";
			System.out.println(identify);
			if(teacher.isSelected())
				identify = "teacher";
			if(student.isSelected())
				identify = "student";
			if(admin.isSelected())
				identify = "admin";
			String sql = "select * from user where id='"+name+"' and identify='"+identify+"'";
			ResultSet rs;
			//System.out.println(sql);
			try{
				rs = new QueryUpdate().executeQuery(sql);
				if(!rs.next())
					JOptionPane.showMessageDialog(null, "该ID尚未注册！","提示",JOptionPane.WARNING_MESSAGE);
				else{
					if(!rs.getString("password").equals(password))
						JOptionPane.showMessageDialog(null, "密码错误！","提示",JOptionPane.WARNING_MESSAGE);
					
					else{
						//Main.user.setText(identify+"  :  "+name);
						new Main(identify,name);
						dispose();
					}


				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}	
		}
		
	}
	public static void main(String[] args){
		new Login();
	}
}

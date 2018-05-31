package main;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import database.QueryUpdate;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;


public class Register extends JFrame{
	private JPanel panel;
	public static JLabel Lid = new JLabel("",JLabel.RIGHT);
	public static String Sid = "";
	private JLabel Lpassword;
	private JLabel Lrepassword;
	private JTextField id;
	private JPasswordField Tpassword;
	private JPasswordField Trepassword;
	private JButton yes;
	private JButton no;
	public Register(){
		this.setSize(400, 250);
		this.setLocation(400, 200);
		this.setTitle("用户注册");
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,2,20,20));
		panel.setBorder(new EmptyBorder(30,50,30,80));
		Lpassword = new JLabel("密码：",JLabel.RIGHT);
		Lrepassword = new JLabel("确认密码：",JLabel.RIGHT);
		id = new JTextField(20);
		id.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				ResultSet rs;
				String ID = "'"+id.getText()+"'";
				String sql = "select * from user where identify='"+Sid+"' and id="+ID;
				//System.out.println(sql);
				try{
					rs = new QueryUpdate().executeQuery(sql);
					if(rs.next())
						JOptionPane.showMessageDialog(null, "该ID已注册！","提示",JOptionPane.WARNING_MESSAGE);
					rs.close();
				}catch(SQLException ex){
					ex.printStackTrace();
				}
			}
			
		});
		Tpassword = new JPasswordField(15);
		Trepassword = new JPasswordField(15);
		Trepassword.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String s1 = new String(Tpassword.getPassword());
				String s2 = new String(Trepassword.getPassword());
				if(!s1.equals(s2))
					JOptionPane.showMessageDialog(null, "两次输入密码不一致！","提示",JOptionPane.WARNING_MESSAGE);
				if(s1.length()<5)
					JOptionPane.showMessageDialog(null, "密码长度应不小于5位！","提示",JOptionPane.WARNING_MESSAGE);
			}
			
		});
		yes = new JButton("注册");
		yes.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String ID = id.getText();
				String password = new String(Tpassword.getPassword());
				String repassword = new String(Trepassword.getPassword());
				if(ID.equals("")||password.equals("")||repassword.equals(""))
					JOptionPane.showMessageDialog(null, "输入信息不完整！","提示",JOptionPane.WARNING_MESSAGE);
				else{
					String sql = "insert into user values('"+ID+"','"+password+"','"+Sid+"')";
					System.out.println(sql);
					new QueryUpdate().executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "注册成功\n点击回到登录界面");
					new Login();
					dispose();
				}

			}
			
		});
		no = new JButton("取消");
		no.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(null, "确定要取消注册？\n取消将返回到登录界面","提示",JOptionPane.YES_NO_OPTION);
				if(n==JOptionPane.YES_OPTION){
					new Login();
					dispose();
					
				}
			}
			
		});
		panel.add(Lid);
		panel.add(id);
		panel.add(Lpassword);
		panel.add(Tpassword);
		panel.add(Lrepassword);
		panel.add(Trepassword);
		panel.add(yes);
		panel.add(no);
		this.add(panel);
		this.setVisible(true);
	}
	public static void main(String[] args){
		new Register();
	}
}

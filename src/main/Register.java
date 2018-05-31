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
		this.setTitle("�û�ע��");
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,2,20,20));
		panel.setBorder(new EmptyBorder(30,50,30,80));
		Lpassword = new JLabel("���룺",JLabel.RIGHT);
		Lrepassword = new JLabel("ȷ�����룺",JLabel.RIGHT);
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
						JOptionPane.showMessageDialog(null, "��ID��ע�ᣡ","��ʾ",JOptionPane.WARNING_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "�����������벻һ�£�","��ʾ",JOptionPane.WARNING_MESSAGE);
				if(s1.length()<5)
					JOptionPane.showMessageDialog(null, "���볤��Ӧ��С��5λ��","��ʾ",JOptionPane.WARNING_MESSAGE);
			}
			
		});
		yes = new JButton("ע��");
		yes.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String ID = id.getText();
				String password = new String(Tpassword.getPassword());
				String repassword = new String(Trepassword.getPassword());
				if(ID.equals("")||password.equals("")||repassword.equals(""))
					JOptionPane.showMessageDialog(null, "������Ϣ��������","��ʾ",JOptionPane.WARNING_MESSAGE);
				else{
					String sql = "insert into user values('"+ID+"','"+password+"','"+Sid+"')";
					System.out.println(sql);
					new QueryUpdate().executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "ע��ɹ�\n����ص���¼����");
					new Login();
					dispose();
				}

			}
			
		});
		no = new JButton("ȡ��");
		no.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(null, "ȷ��Ҫȡ��ע�᣿\nȡ�������ص���¼����","��ʾ",JOptionPane.YES_NO_OPTION);
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

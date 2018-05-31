package main;
import javax.swing.*;

import admin.AdminMenu;
import admin.Query;
import student.StudentMenu;
import teacher.TeacherMenu;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class Main extends JFrame{

	private JLabel user ;
	private JButton exit;
	public static JLabel location;
	private JLabel time;
	private JPanel panel;
	private JPanel panel0;
	private JPanel panelUp;
	private JPanel panelUser;
	private JPanel panel3;
	public static CardLayout card = new CardLayout();;

	public Main(String identify,String name){
		this.setTitle("�̲Ĺ���ϵͳ");
		this.setExtendedState(Frame.MAXIMIZED_BOTH);


		panelUp = new JPanel();
		panelUp.setLayout(new GridLayout(1,3));
		panelUser = new JPanel();
		panelUser.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		user = new JLabel(identify+"  :  "+name);
		exit = new JButton("ע��");
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(null, "ȷ��Ҫע����\nע�������ص���¼����","��ʾ",JOptionPane.YES_NO_OPTION);
				if(n==JOptionPane.YES_OPTION){
					new Login();
					dispose();
					
				}
			}
			
		});
		panelUser.add(user);
		panelUser.add(exit);

		if(user.getText().indexOf("admin")==0){
			JButton jb = new JButton("ע���¹���Ա");
			jb.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Register.Lid.setText("����Ա�˻�����");
					Register.Sid = "admin";
					new Register();
					dispose();
				}
				
			});
			panelUser.add(jb);
		}
		
		panelUser.setBackground(new Color(245,255,250));
		//��ǰλ��
		location = new JLabel("���˵�",JLabel.CENTER);
		location.setFont(new Font("Dialog",1,25));
		panel0 = new JPanel();
		panel0.setBackground(new Color(245,255,250));
		panelUp.add(panel0);
		panelUp.add(location);
		panelUp.add(panelUser);
		panelUp.setBackground(new Color(245,255,250));
		this.add(panelUp,BorderLayout.NORTH);
		
		switch(identify){
		case "admin":
			panel = new AdminMenu();
			break;
		case "student":
			panel = new StudentMenu();
			break;
		case "teacher":
			panel = new TeacherMenu();
			break;
		}
		
		
		this.add(panel,BorderLayout.CENTER);
		
		panel3 = new JPanel();
		time = new JLabel();
		panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel3.add(time);
		this.add(panel3,BorderLayout.SOUTH);
		Time now = new Time();
		now.start();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	class Time extends Thread {
		public void run() {
			Calendar cal;
			while (true) {
				try {
					cal = Calendar.getInstance();
					String now = cal.get(Calendar.YEAR) + "��"
							+ (cal.get(Calendar.MONTH) + 1) + "��"
							+ cal.get(Calendar.DATE) + "��"
							+ cal.get(Calendar.HOUR_OF_DAY) + "ʱ"
							+ cal.get(Calendar.MINUTE) + "��"
							+ cal.get(Calendar.SECOND) + "��";
					time.setText(now);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage()
							.toString());
				}
			}
		}
	}
/*	public static void main(String[] args){
		new Main();
	}*/
}

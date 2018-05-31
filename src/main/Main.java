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
		this.setTitle("教材管理系统");
		this.setExtendedState(Frame.MAXIMIZED_BOTH);


		panelUp = new JPanel();
		panelUp.setLayout(new GridLayout(1,3));
		panelUser = new JPanel();
		panelUser.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		user = new JLabel(identify+"  :  "+name);
		exit = new JButton("注销");
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(null, "确定要注销？\n注销将返回到登录界面","提示",JOptionPane.YES_NO_OPTION);
				if(n==JOptionPane.YES_OPTION){
					new Login();
					dispose();
					
				}
			}
			
		});
		panelUser.add(user);
		panelUser.add(exit);

		if(user.getText().indexOf("admin")==0){
			JButton jb = new JButton("注册新管理员");
			jb.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Register.Lid.setText("管理员账户名：");
					Register.Sid = "admin";
					new Register();
					dispose();
				}
				
			});
			panelUser.add(jb);
		}
		
		panelUser.setBackground(new Color(245,255,250));
		//当前位置
		location = new JLabel("主菜单",JLabel.CENTER);
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
					String now = cal.get(Calendar.YEAR) + "年"
							+ (cal.get(Calendar.MONTH) + 1) + "月"
							+ cal.get(Calendar.DATE) + "日"
							+ cal.get(Calendar.HOUR_OF_DAY) + "时"
							+ cal.get(Calendar.MINUTE) + "分"
							+ cal.get(Calendar.SECOND) + "秒";
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

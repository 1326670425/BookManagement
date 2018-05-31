package admin;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class AdminMenu extends JPanel{
	private JPanel rpanel;
	private JPanel lpanel;
	private JButton jin;
	private JButton jout;
	private JButton jquery;
	private JButton jbacklog;
	private JButton jhistory;
	private JButton jexit;
	private CardLayout card;
	public AdminMenu(){
		lpanel = new JPanel();
		rpanel = new JPanel();
		this.setLayout(new BorderLayout());
		lpanel.setLayout(new GridLayout(6,1,30,30));
		lpanel.setBorder(new EmptyBorder(100,30,80,10));

		jin = new JButton("���");
		jout = new JButton("����");
		jquery = new JButton("����ѯ");
		jbacklog = new JButton("��������");
		jhistory = new JButton("��ʷ��¼");
		jexit = new JButton("�˳�ϵͳ");

		MyClickedListener listener = new  MyClickedListener();
		jin.addActionListener(listener);
		jout.addActionListener(listener);
		jquery.addActionListener(listener);
		jbacklog.addActionListener(listener);
		jhistory.addActionListener(listener);
		jexit.addActionListener(listener);

		lpanel.add(jin);
		lpanel.add(jout);
		lpanel.add(jquery);
		lpanel.add(jbacklog);
		lpanel.add(jhistory);
		lpanel.add(jexit);
		this.add(lpanel,BorderLayout.WEST);
		card = new CardLayout();
		rpanel.setLayout(card);
		rpanel.setBorder(new EmptyBorder(20,20,20,20));
		//��ӿ�Ƭ
		rpanel.add(new Input(),"���");
		rpanel.add(new Output(),"����");
		rpanel.add(new Query(),"����ѯ");
		rpanel.add(new Backlog(),"��������");
		rpanel.add(new History(),"��ʷ��¼");


		
		this.add(rpanel,BorderLayout.CENTER);
	}
	class MyClickedListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton jb = (JButton)e.getSource();
			if(jb == jexit)
				System.exit(0);
			else
				card.show(rpanel, jb.getText());
		}
	}
}

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

		jin = new JButton("入库");
		jout = new JButton("出库");
		jquery = new JButton("库存查询");
		jbacklog = new JButton("待办事项");
		jhistory = new JButton("历史记录");
		jexit = new JButton("退出系统");

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
		//添加卡片
		rpanel.add(new Input(),"入库");
		rpanel.add(new Output(),"出库");
		rpanel.add(new Query(),"库存查询");
		rpanel.add(new Backlog(),"待办事项");
		rpanel.add(new History(),"历史记录");


		
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

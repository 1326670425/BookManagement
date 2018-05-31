package student;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import main.CreateTable;
import main.TableList;

public class StudentMenu extends JPanel{
	private JPanel panel1;
	private JPanel panel2;
	private JScrollPane spanel;
	private JLabel chooseTerm;
	private JComboBox<String> year;
	private JComboBox<String> term;
	private JButton search;
	private JButton searchAll;
	private TableList table;
	private Object[] item;
	private Object[][] bookList;
	public StudentMenu(){
		this.setLayout(new BorderLayout());
		panel1 = new JPanel();
		chooseTerm = new JLabel("ѡ��ѧ�ڣ�",JLabel.RIGHT);
		int now = Calendar.getInstance().get(Calendar.YEAR);
		String years[] = new String[6];
		for(int i=years.length-1;i>=0;i--){
			years[i] = (now-1)+"-"+now;
			now--;
		}
		year = new JComboBox<String>(years);
		year.setSelectedIndex(5);
		String terms[] = {"��һѧ��","�ڶ�ѧ��"};
		term = new JComboBox<String>(terms);
		
		search = new JButton("��ѯ��ѧ��");
		searchAll = new JButton("��ѯ����ѧ��");
		MyListener listener = new MyListener();
		search.addActionListener(listener);
		searchAll.addActionListener(listener);
		//��������
		panel1.setLayout(new GridLayout(1,5,100,100));
		panel1.setBorder(new EmptyBorder(10,50,10,50));
		panel1.add(chooseTerm);
		panel1.add(year);
		panel1.add(term);
		panel1.add(search);
		panel1.add(searchAll);
		this.add(panel1,BorderLayout.NORTH);
		
		table = new TableList();
		
		spanel = new JScrollPane(table);
		panel2 = new JPanel(new GridLayout());
		panel2.add(spanel);
		this.add(panel2, BorderLayout.CENTER);

		this.setVisible(true);
	}
	class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String sql = "";

			//��ѯ��ѧ�ڽ̲�
			if(e.getSource()==search){
				String syear = (String)year.getSelectedItem();
				syear = syear.substring(0, 4)+syear.substring(5, 9)+(term.getSelectedIndex()+1);
				//System.out.println(syear);
				sql = "select * from repertory where";
			}
			//��ѯ���н̲�
			else{
				sql = "select * from repertory";
			}
			CreateTable ct = new CreateTable();
			bookList = ct.createTable(sql);
			if(bookList==null){
				JOptionPane.showMessageDialog(null, "�����������", "����", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			table.setModel(new DefaultTableModel(bookList,item));
		}
		
	}

}

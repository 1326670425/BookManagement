package main;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class TableList extends JTable{
	public TableList(){
		//JTable���ݾ���
		DefaultTableCellRenderer r   = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		this.setDefaultRenderer(Object.class, r);
		
		//table.setModel(new DefaultTableModel(bookList,item));
		this.setSelectionForeground(Color.RED);
		this.setFont(new Font("����",Font.PLAIN,15));
		this.setRowHeight(25);
	}
    public boolean isCellEditable(int row, int column)
    {
               return false;//��������༭
    }
}

package main;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class TableList extends JTable{
	public TableList(){
		//JTable内容居中
		DefaultTableCellRenderer r   = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		this.setDefaultRenderer(Object.class, r);
		
		//table.setModel(new DefaultTableModel(bookList,item));
		this.setSelectionForeground(Color.RED);
		this.setFont(new Font("宋体",Font.PLAIN,15));
		this.setRowHeight(25);
	}
    public boolean isCellEditable(int row, int column)
    {
               return false;//表格不允许被编辑
    }
}

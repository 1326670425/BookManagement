package main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;


public class FileOperation {
	public List<List<String>> open(){
		
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new MyFileFilter());
		int choose = jfc.showOpenDialog(null);
		List<List<String>> list = null;
		if(choose==JFileChooser.APPROVE_OPTION){
			File file = jfc.getSelectedFile();
			try{
				list = FileIO.read(file);
			}catch(IOException e){
				e.printStackTrace();
			}

		}
		return list;
	}
	public void save(Object[] item, Object[][] list){
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new MyFileFilter());
		jfc.setSelectedFile(new File("新建Excel工作表.xlsx"));
		int choose = jfc.showSaveDialog(null);
		
		if(choose==JFileChooser.APPROVE_OPTION){
			File file = jfc.getSelectedFile();
			FileIO.write(item,list,file);
		}
		else
			return;
	}
	class MyFileFilter extends FileFilter{

		@Override
		public boolean accept(File f) {
			// TODO Auto-generated method stub
			if(f.isDirectory())
				return true;
			String name = f.getName();
			return name.endsWith(".xls")||name.endsWith(".xlsx");
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "Excel工作表(*.xls, *.xlsx)";
		}
		
	}
}

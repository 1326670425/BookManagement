package main;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileIO {
	public static List<List<String>> read(File file) throws IOException{

		FileInputStream inputStream = new FileInputStream(file);
 
		String name = file.getName();
		Workbook wb = null;
		if(name.endsWith(".xls"))
			wb = new HSSFWorkbook(inputStream);
		else if(name.endsWith(".xlsx"))
			wb = new XSSFWorkbook(inputStream);
		
		Sheet sheet = wb.getSheetAt(0);
		List<List<String>> list = new ArrayList<List<String>>();

/*        //获得当前sheet的开始行  
        int firstRowNum  = sheet.getFirstRowNum();  
        //获得当前sheet的结束行  
        int lastRowNum = sheet.getLastRowNum();  
        //循环除了第一行的所有行  
        for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){  
            //获得当前行  
            Row row = sheet.getRow(rowNum);  
            if(row == null){  
                continue;  
            }  
            //获得当前行的开始列  
            int firstCellNum = row.getFirstCellNum();  
            //获得当前行的列数  
            int lastCellNum = row.getPhysicalNumberOfCells();  
            String[] cells = new String[lastCellNum];  
            //循环当前行  
            for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){  
                Cell cell = row.getCell(cellNum);  
                cells[cellNum] = cell.getStringCellValue();
                System.out.println(cell.getStringCellValue());
            }  
            list.add(cells);
        }*/
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		//格式化班级显示
		DecimalFormat df = new DecimalFormat("#");  
		while(rowIterator.hasNext()){
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			List<String> cells = new ArrayList<String>();
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				switch(cell.getCellTypeEnum()){
				case STRING:
					cells.add(cell.getStringCellValue()+"");
					break;
				case NUMERIC:
					cells.add(df.format(cell.getNumericCellValue())+"");
					break;
				case BLANK:
					cells.add("");
					break;
				default:
					break;		
				}
			}
			list.add(cells);

			//System.out.println(cells);
		}
		//System.out.println(list);
        wb.close();
        return list;
	}
	public static void write(Object[] item, Object[][] list, File file){
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet(file.getName());

		Row row = null;
		row = sheet.createRow(0);
		for(int i=0;i<item.length;i++){
			row.createCell(i).setCellValue((String)item[i]);
		}
		int i=0;
		int j=0;
		for(Object[] eachRow:list){
			row = sheet.createRow(i++);
			for(Object eachCell:eachRow){
				row.createCell(j++).setCellValue((String)eachCell);
			}
			j=0;
		}
		try{
			FileOutputStream fout = new FileOutputStream(file);
			wb.write(fout);
			fout.close();
			wb.close();
		}catch(IOException e){
			e.printStackTrace();
		}

	}
}

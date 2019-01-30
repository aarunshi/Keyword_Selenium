package com.b2c.Helper.ExcelReader_Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;


public class ExcelReader {
String path;
XSSFWorkbook wb;
XSSFSheet wsh;
int i =0;
int j =0;

public  ExcelReader(String excelLocation)
{
	try{
		path=excelLocation;
		File f = new File(excelLocation);
		FileInputStream file = new FileInputStream(f);
		 wb = new XSSFWorkbook(file);
	}
	catch(Exception e)
	
	{
		e.printStackTrace();
	}
	
	
	
}

	
@SuppressWarnings("deprecation")

	public  String[][] getExcelData( String sheetName) throws IOException
	{
		
		
		String dataSets[][]= null;
		wsh = wb.getSheet(sheetName);
		int rowNum= wsh.getLastRowNum()+1;
		int colNum =wsh.getRow(0).getLastCellNum();//colum is called cell
		
		dataSets = new String[rowNum-1] [colNum];

		
		Iterator<Row> rawIterator = wsh.iterator();

		Row raw = rawIterator.next();
/*		for ( int i=1; i <rowNum;i++)
		{
			Row row = wsh.getRow(i);
			
			for ( int j =0;j<colNum;j++){
				Cell cell = row.getCell(j);
				@SuppressWarnings("deprecation")
				CellType type = cell.getCellTypeEnum();
				int typeo = 1;
				
				if ((type.toString()).equals("STRING"))
						 typeo = 1;
				else
					 typeo =2;
				
				if (typeo==(cell.CELL_TYPE_STRING))
				{
					dataSets[i][j] = cell.getStringCellValue();
				}
				else
					dataSets[i][j] = String.valueOf(cell.getNumericCellValue());
			
			
			
			}
		}*/
		
		int i=0;
		while(rawIterator.hasNext())
		{
			int j=0;
			 raw = rawIterator.next();

			Iterator<Cell> cellIterator = raw.cellIterator();
			
				while(cellIterator.hasNext())
			{
					 
				
					Cell cell = cellIterator.next();
					@SuppressWarnings("deprecation")
					CellType type = cell.getCellTypeEnum();
					int typeo = 1;
					if ((type.toString()).equals("STRING"))
							 typeo = 1;
					else
						 typeo =2;
					
					if (typeo==(cell.CELL_TYPE_STRING))
					{
						dataSets[i][j] = cell.getStringCellValue();
					}
					else
						dataSets[i][j] = String.valueOf(cell.getNumericCellValue());
				System.out.println(dataSets[i][j]);
				
				j= j+1;
				
			}
				i=i+1;
		}
		return dataSets ;
	}
	
//	public static void main(String Args[]) throws IOException
//	{
//		ExcelReader ex = new ExcelReader("C:\\Users\\Aarav\\Desktop\\newq1.xlsx");
//		 ex.getExcelData("first");
//	}
}
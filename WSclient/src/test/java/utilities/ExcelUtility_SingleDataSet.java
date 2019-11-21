package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

// Please change the extension of the file from .txt to .java
// It looks like udemy stopped supporting uploading of .java files, this is why I have to change the  extension of the file

public class ExcelUtility_SingleDataSet {
	/*
	 * URL to get the binary - http://poi.apache.org/download.html
	 * Binary Name - poi-bin-3.11-beta2-20140822.zip
	 * Extract the binary
	 * Add all the jars from the location you extracted
	 * Also add all the jars from lib, do not add the jar file of log4j
	 * Also add all the jars from ooxml-lib
	 */
	public static DataFormatter formatter = new DataFormatter();
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	public static int RowNum;
	public static int ColCount;

	/*
	 * Set the File path, open Excel file
	 * @params - Excel Path and Sheet Name
	 */
	public static void setExcelFile(String Path, String SheetName)
			throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the excel data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	/*
	 * Read the test data from the Excel cell
	 * @params - Row num and Col num
	 */
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String cellData = Cell.getStringCellValue();
			return cellData;
		} 
		
		catch (Exception e) {
		e.printStackTrace();
			return "";
		}
	}
	
	/*
	 * Read the test data of date type from the Excel cell
	 * @params - Row num and Col num
	 */
	public static int getDateCellData1(String TCname) throws Exception {
		try {
			
			 //int colcount=Row.getLastCellNum();
			 int rowcount=ExcelWSheet.getLastRowNum();
			
			for (int i=0;i<=rowcount;i++) {
				Cell=ExcelWSheet.getRow(i).getCell(1);
				String cellData=Cell.getStringCellValue();
				if(cellData.contentEquals(TCname)) {
					RowNum=i;
					//ColCount=2;
				}
			}
		    
			return RowNum;
		} catch (Exception e) {
			return RowNum;
		}
	}
	
	public static int getcolumno(String TCname) throws Exception {
		try {
			
			 //int colcount=Row.getLastCellNum();
			 int rowcount=ExcelWSheet.getLastRowNum();
			
			for (int i=0;i<=rowcount;i++) {
				Cell=ExcelWSheet.getRow(i).getCell(1);
				String cellData=Cell.getStringCellValue();
				if(cellData.contentEquals(TCname)) {
					RowNum=i;
					//ColCount=2;
				}
			}
		    
			return RowNum;
		} catch (Exception e) {
			return RowNum;
		}
	}
	public static String getDateCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		    Date dateValue = Cell.getDateCellValue();
		    String dateStringFormat = df.format(dateValue);
		    
			return dateStringFormat;
		} catch (Exception e) {
			return "";
		}
	}

	/*
	 * Write in the Excel cell, String Result
	 * @params - Row num and Col num
	 */
	public static void setCellData(String Result, int RowNum, int ColNum)
			throws Exception {
		try {
			Row = ExcelWSheet.getRow(RowNum);
			// This should handle null pointer exception if Row does not exist
			if (Row == null) {
				Row = ExcelWSheet.createRow(RowNum);
			}
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}

			// Open the file to write the results
			FileOutputStream fileOut = new FileOutputStream(
					Constants.File_Path + Constants.File_Name);

			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}
	
	/*
	 * Write in the Excel cell, double Result
	 * @params - Row num and Col num
	 */
	public static void setCellData(double Result, int RowNum, int ColNum)
			throws Exception {
		try {
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}

			// Open the file to write the results
			FileOutputStream fileOut = new FileOutputStream(
					Constants.File_Path + Constants.File_Name);

			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}
	
	
	@DataProvider
	public static Object[][] Readconfigdata() throws IOException
	{
	//FileInputStream fileInputStream= new FileInputStream("C:/Users/anujg/Desktop/testdata.xlsx"); //Excel sheet file location get mentioned here
	//workbook = new XSSFWorkbook (fileInputStream); //get my workbook 
	ExcelWSheet=ExcelWBook.getSheet("Connection");// get my sheet from workbook
	XSSFRow Row=ExcelWSheet.getRow(0);//get my Row which start from 0   
	int RowNum = ExcelWSheet.getPhysicalNumberOfRows();// count my number of Rows
	int ColNum= Row.getLastCellNum(); // get last ColNum 
	Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
	for(int i=0; i<RowNum-1; i++) //Loop work for Rows
	{
		XSSFRow row= ExcelWSheet.getRow(i+1);
		for (int j=0; j<ColNum; j++) //Loop work for colNum
			{
			if(row==null)Data[i][j]= "";
			else{XSSFCell cell= row.getCell(j);
			if(cell==null)
				Data[i][j]= ""; //if it get Null value it pass no data
			else
						{String value=formatter.formatCellValue(cell);
						Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value}
			}
			}
			}
			}
	return Data;
	}
	@DataProvider
	public static Object[][] Bootnotification() throws IOException
	{
	//FileInputStream fileInputStream= new FileInputStream("C:/Users/anujg/Desktop/testdata.xlsx"); //Excel sheet file location get mentioned here
	//workbook = new XSSFWorkbook (fileInputStream); //get my workbook 
	ExcelWSheet=ExcelWBook.getSheet("BootNotification");// get my sheet from workbook
	XSSFRow Row=ExcelWSheet.getRow(0);//get my Row which start from 0   
	int RowNum = ExcelWSheet.getPhysicalNumberOfRows();// count my number of Rows
	int ColNum= Row.getLastCellNum(); // get last ColNum 
	Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
	for(int i=0; i<RowNum-1; i++) //Loop work for Rows
	{
		XSSFRow row= ExcelWSheet.getRow(i+1);
		for (int j=0; j<ColNum; j++) //Loop work for colNum
			{
			if(row==null)Data[i][j]= "";
			else{XSSFCell cell= row.getCell(j);
			if(cell==null)
				Data[i][j]= ""; //if it get Null value it pass no data
			else
						{String value=formatter.formatCellValue(cell);
						Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value}
			}
			}
			}
			}
	return Data;
	}
	
	@DataProvider
	public static Object[][] Starttrans() throws IOException
	{
	//FileInputStream fileInputStream= new FileInputStream("C:/Users/anujg/Desktop/testdata.xlsx"); //Excel sheet file location get mentioned here
	//workbook = new XSSFWorkbook (fileInputStream); //get my workbook 
	ExcelWSheet=ExcelWBook.getSheet("StartTransaction");// get my sheet from workbook
	XSSFRow Row=ExcelWSheet.getRow(0);//get my Row which start from 0   
	int RowNum = ExcelWSheet.getPhysicalNumberOfRows();// count my number of Rows
	int ColNum= Row.getLastCellNum(); // get last ColNum 
	Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
	for(int i=0; i<RowNum-1; i++) //Loop work for Rows
	{
		XSSFRow row= ExcelWSheet.getRow(i+1);
		for (int j=0; j<ColNum; j++) //Loop work for colNum
			{
			if(row==null)Data[i][j]= "";
			else{XSSFCell cell= row.getCell(j);
			if(cell==null)
				Data[i][j]= ""; //if it get Null value it pass no data
			else
						{String value=formatter.formatCellValue(cell);
						Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value}
			}
			}
			}
			}
	return Data;
	}
	
	@DataProvider
	public static Object[][] Stoptrans() throws IOException
	{
	//FileInputStream fileInputStream= new FileInputStream("C:/Users/anujg/Desktop/testdata.xlsx"); //Excel sheet file location get mentioned here
	//workbook = new XSSFWorkbook (fileInputStream); //get my workbook 
	ExcelWSheet=ExcelWBook.getSheet("StopTransaction");// get my sheet from workbook
	XSSFRow Row=ExcelWSheet.getRow(0);//get my Row which start from 0   
	int RowNum = ExcelWSheet.getPhysicalNumberOfRows();// count my number of Rows
	int ColNum= Row.getLastCellNum(); // get last ColNum 
	Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
	for(int i=0; i<RowNum-1; i++) //Loop work for Rows
	{
		XSSFRow row= ExcelWSheet.getRow(i+1);
		for (int j=0; j<ColNum; j++) //Loop work for colNum
			{
			if(row==null)Data[i][j]= "";
			else{XSSFCell cell= row.getCell(j);
			if(cell==null)
				Data[i][j]= ""; //if it get Null value it pass no data
			else
						{String value=formatter.formatCellValue(cell);
						Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value}
			}
			}
			}
			}
	return Data;
	}
	
}
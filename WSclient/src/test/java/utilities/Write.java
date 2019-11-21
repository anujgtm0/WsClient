package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
 

 
 
public class Write 
    {
        public static XSSFWorkbook workbook;
        public static XSSFSheet worksheet;
       // public String ColName="Result";
        public int col_num;
@Test
    public void WriteResult(String Ress, int DR,String Sheetname,String colName) throws Exception
    {
	
        FileInputStream file_input_stream= new FileInputStream("C:/Users/anujg/Desktop/evsedataset.xlsx");
        workbook=new XSSFWorkbook(file_input_stream);
        worksheet=workbook.getSheet(Sheetname);
        XSSFRow Row=worksheet.getRow(0);
       // System.out.println("Row number is "+ Row);
 
        int sheetIndex=workbook.getSheetIndex(Sheetname);
        //System.out.println("Sheet index is "+ sheetIndex);
        DataFormatter formatter = new DataFormatter();
        if(sheetIndex==-1)
        {
            //System.out.println("No such sheet in file exists");
        } else      {
            col_num=-1;
                for(int i=0;i<Row.getLastCellNum();i++)
                {
                    XSSFCell cols=Row.getCell(i);
                    String colsval=formatter.formatCellValue(cols);
                    if(colsval.trim().equalsIgnoreCase(colName.trim()))
                    {
                        col_num=i;
                        //System.out.println("Colm number is "+ col_num);
                        break;
                    }
                }
//              
                Row= worksheet.getRow(DR);
                try
                    {
                	
                    //get my Row which is equal to Data  Result and that colNum
                        XSSFCell cell=worksheet.getRow(DR).getCell(col_num);
                        // if no cell found then it create cell
                        if(cell==null) {
                            cell=Row.createCell(col_num);                           
                        }
                        //Set Result is pass in that cell number
                       
                        //System.out.println("From method  "+  Ress);
                        cell.setCellValue(Ress);
                        //cell.setCellValue(Res);

                        //XSSFCellStyle style1 = workbook.createCellStyle();
                        //style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 128, 0)));
                    
                        //System.out.println(Ress);
                        
                        
                        
                         
                    }
                catch (Exception e)
                {
                    System.out.println(e.getMessage()); 
                    e.printStackTrace();
                } 
    
        }
            FileOutputStream file_output_stream=new FileOutputStream("C:/Users/anujg/Desktop/evsedataset.xlsx");
            workbook.write(file_output_stream);
            file_output_stream.close();
            if(col_num==-1) {
                System.out.println("Column you are searching for does not exist");
            }
    }
public String readtestdatastatus(int DR,String Sheetname) throws Exception
{
	 try
     {
    FileInputStream file_input_stream= new FileInputStream("C:/Users/anujg/Desktop/evsedataset.xlsx");
    workbook=new XSSFWorkbook(file_input_stream);
    worksheet=workbook.getSheet(Sheetname);
    String ColName1=null;
    XSSFRow Row=worksheet.getRow(0);
   // System.out.println("Row number is "+ Row);

    int sheetIndex=workbook.getSheetIndex(Sheetname);
    System.out.println("Sheet index is "+ sheetIndex);
    DataFormatter formatter = new DataFormatter();
          col_num=-1;
            for(int i=0;i<Row.getLastCellNum();i++)
            {
                XSSFCell cols1=Row.getCell(i);
                String colsva2=formatter.formatCellValue(cols1);
                if(colsva2.trim().equalsIgnoreCase(ColName1.trim()))
                {
                    col_num=i;
                    //System.out.println("Colm number is "+ col_num);
                    break;
                }
            }
//          
            Row= worksheet.getRow(DR);
           
            	
                //get my Row which is equal to Data  Result and that colNum
                    XSSFCell cell=worksheet.getRow(DR).getCell(col_num);
                    // if no cell found then it create cell
                    String cellData=cell.getStringCellValue();
                    
                    
                   return cellData; 
                     
                }
            catch (Exception e)
            {
                System.out.println(e.getMessage()); 
            }
	return "";
		

    
        
		
}
}
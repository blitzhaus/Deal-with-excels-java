package nagarjuna;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.opencsv.CSVReader;





public class ExcelPoi {
	
	FileOutputStream fileOut;

	public static void main(String[] args) throws IOException, RowsExceededException, WriteException{
		new ExcelPoi();
	
	}
	
	public ExcelPoi() throws IOException, RowsExceededException, WriteException{
		
		 CSVReader reader = new CSVReader(new FileReader("ajith.csv"));
	     String [] nextLine;
	     int i=0;
	     int j=0;
	     String[][] data = new String[362261][3];
	     while ((nextLine = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
	    	 i++;
	    	 
	    	 data[i][0] = nextLine[0];
	    
	    	 data[i][1] = nextLine[1];
	    	 data[i][2] = nextLine[2];
	    	/* data[i][3] = nextLine[3];
	    	 data[i][4] = nextLine[4];*/
	    	 
	        
	     }
	     
	     System.out.println("number of lines = "+data.length);
	     
	     for(int m =0;m<1;m++){
	    	 for(int n=0;n<data[m].length;n++){
	    		 System.out.print(data[m][n]+", ");
	    	 }
	    	 System.out.println();
	     }
	    
	     count(data);
	}
	
	
	public void count(String[][] data) throws RowsExceededException, WriteException, IOException{
	
		int numberOfUsersForTHisEntitlement = 0;
		int sheetNumner = 0;
		
		HSSFWorkbook writableWorkbook = createWorkBook();
		/*WritableSheet writableSheet = createNewSheet(writableWorkbook, "MGM MIRAGE", sheetNumner);
		writableSheet.setName("MGM MIRAGE");*/
		//WritableSheet writableSheet = null;
		HSSFSheet writableSheet = null;
		
		int entitlementNumber = 0;
		
		for(int i=2;i<data.length;i++){
			numberOfUsersForTHisEntitlement = 1;
			String property = data[i][0];
			String entitlement = data[i][1];
			
			//If the property changes then cerate a new sheet which means it is a new department
			
			if((i< data.length-1) && (property.equalsIgnoreCase(data[i-1][0]) == false)){
				writableSheet = createNewSheet(writableWorkbook, property, sheetNumner);
				sheetNumner++;
				entitlementNumber = 0;
				//writableSheet.setName(property);
			}
			
			//for each property/department calculate the number of entitlements
			
			while( (i< data.length - 2) && (property.equalsIgnoreCase(data[i+1][0])) && (entitlement.equalsIgnoreCase(data[i+1][1]))){
			
				numberOfUsersForTHisEntitlement++;
				i++;
				
			}
			
			
			if((i<data.length-1)&&(entitlement.equalsIgnoreCase(data[i+1][1]) == false)){
				entitlementNumber++;
			}
			/*Label label = new Label(0, entitlementNumber, entitlement);
			writableSheet.addCell(label);*/
			HSSFRow row1 = writableSheet.createRow((short) entitlementNumber);

			@SuppressWarnings("deprecation")
			HSSFCell cellA1 = row1.createCell((short) 0);
			cellA1.setCellValue(entitlement);
			
			/*Number numbOfUsers = new Number(1, entitlementNumber, numberOfUsersForTHisEntitlement);
			writableSheet.addCell(numbOfUsers);*/
			HSSFCell cellA2 = row1.createCell((short) 1);
			cellA2.setCellValue(numberOfUsersForTHisEntitlement);
			
			
			System.out.println("number of users for department = "+property+" and entitlement = "+ entitlement +"  are = "+ numberOfUsersForTHisEntitlement);
		}
		
		/*writableWorkbook.write();
        writableWorkbook.close();*/
		writableWorkbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	private HSSFSheet createNewSheet(HSSFWorkbook writableWorkbook, String department, int sheetNumber) {
		// TODO Auto-generated method stub
		
		/*WritableSheet writableSheet = writableWorkbook.createSheet(
                department, sheetNumber);*/
		System.out.println("department name = "+ department);
		HSSFSheet writableSheet = writableWorkbook.createSheet(department);
		
		
		return writableSheet;
		
	}

	private HSSFWorkbook createWorkBook() throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		fileOut = new FileOutputStream("output.xls");
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		
		
		/* File exlFile = new File("output.xls");
         try {
			WritableWorkbook writableWorkbook = Workbook.createWorkbook(exlFile);
			
			return writableWorkbook;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return workbook;

         
		
	}
	
	
	
	
	
	
}

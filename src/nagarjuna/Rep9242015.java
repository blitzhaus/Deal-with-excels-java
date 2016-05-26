package nagarjuna;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.opencsv.CSVReader;

public class Rep9242015 {
	
	FileOutputStream fileOut;

	public static void main(String[] args) throws IOException{
		new Rep9242015();
	}
	
	public Rep9242015() throws IOException{
		



		CSVReader reader = new CSVReader(new FileReader("Ajith_3.csv"));
		String[] nextLine;
		int i = 0;
		int j = 0;
		String[][] data = null;

		// this while group will remove the blanks and the inactive users
		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line
			
			if(nextLine[0].equals("14611")){
				System.out.println("This is it");
			}

			if ( (nextLine[0].equals("14611") == false) || (nextLine[0].equals(null))||(nextLine[0].equals("")) ||(nextLine[1].equals(null)) || (nextLine[1].equals(""))
					||(nextLine[2].equals(null)) || (nextLine[2].equals("")) ||(nextLine[4].equalsIgnoreCase("A") == false)) {

				System.out.println("how are you");
			} else {
				// this is the number of rows
				System.out.println("I am fine");
				i++;
			}

		}

		data = new String[i][3];
		reader.close();
		reader = new CSVReader(new FileReader("Ajith_3.csv"));
		System.out.println("i = "+ i);
		i = 0;
		
		HashMap<String, HashMap<String, EntANDNumner>> structure = new HashMap<String, HashMap<String,EntANDNumner>>();
		
		// this while loop will read the processed sheet data into "data" structure
		while ((i < data.length) && ((nextLine = reader.readNext()) != null)) {

			if ( (nextLine[0].equals("14611") == false) || (nextLine[0].equals(null))||(nextLine[0].equals("")) ||(nextLine[2].equals(null)) || (nextLine[2].equals(""))
					||(nextLine[1].equals(null)) || (nextLine[1].equals("")) ||(nextLine[4].equalsIgnoreCase("A") == false)) {

			} else {
				data[i][0] = nextLine[1];
				if(structure.containsKey(data[i][0])){
					
				} else {
					structure.put(data[i][0], new HashMap<String, EntANDNumner>());
					count.put(data[i][1], 0);
				}
				data[i][1] = nextLine[2];
				data[i][2] = nextLine[3];/*
				data[i][3] = nextLine[3];*/
				i++;
				/*
				 * data[i][3] = nextLine[3]; data[i][4] = nextLine[4];
				 */
			}

		}
		System.out.println("number of lines = " + data.length);

		ArrayList<String> ar = new ArrayList<String>();
		for (int m = 0; m < data.length; m++) {
			for (int n = 0; n < data[m].length; n++) {
				System.out.print(data[m][n] + ", ");
			}
			System.out.println();
		}
		
		
		for(int d=0;d<data.length;d++){
			ar.add(data[d][0]+","+data[d][1]);
		}
	
		HashSet<String[]> hs = new HashSet<String[]>();
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for(int e=0;e<ar.size();e++){
			
			if(hm.containsKey(ar.get(e))){
				int increment = hm.get(ar.get(e)) + 1;
				hm.put(ar.get(e), increment);
			} else{
				//hs.add(ar.get(e));
				hm.put(ar.get(e), 0);
			}
		}
		
		
		System.out.println(hm.size());
		data = new String[hm.size()][3];
		int k=0;
		
		int total = 0;
		Map<String, Integer> map = new TreeMap<String, Integer>(hm); 
		Iterator it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println("---"+pair.getKey() + " = " + pair.getValue());
	        String[] split = pair.getKey().toString().split(",");
	        data[k][0] = split[0];
	        data[k][1] = split[1];
	        data[k][2] = pair.getValue()+"";
	        total = total + Integer.parseInt(pair.getValue()+"");
	        k++;
	    }
		
		//data = new String[hm.size()][2];
	    //Arrays.sort(data);
		
		writeToSheet(data, total);

		
		//count(data, structure);
		
	
	
	}

	private void writeToSheet(String[][] data, int total) throws IOException {
		// TODO Auto-generated method stub
		HSSFWorkbook writableWorkbook = null;
		
		int rows = 0;
		writableWorkbook = createWorkBook();
		HSSFSheet writableSheet =  null;
		while (rows < data.length) {
			
			System.out.println("sheet name = "+ data[rows+1][0]+"and row = "+ (rows+1));
			if(writableWorkbook.getSheet(data[rows+1][0])== null){
				writableSheet = createNewSheet(writableWorkbook,
						data[rows+1][0]);
			}
			

			HSSFRow row1 = writableSheet.createRow((short) rows);
			/*@SuppressWarnings("deprecation")
			HSSFCell cellA1 = row1.createCell((short) 0);
			cellA1.setCellValue("PROPERTY");*/

			@SuppressWarnings("deprecation")
			HSSFCell cellA2 = row1.createCell((short) 0);
			cellA2.setCellValue("GROUP");

			// @SuppressWarnings("USERS")
			HSSFCell cellA3 = row1.createCell((short) 1);
			cellA3.setCellValue("USERS");

			// @SuppressWarnings("PERCENTAGE")
			HSSFCell cellA4 = row1.createCell((short) 2);
			cellA4.setCellValue("PERCENTAGE");

			total = 1;
			int i = rows;
			while((i<data.length -1) && (data[i][0].equalsIgnoreCase(data[i+1][0]))){
				total = (int) (total + Double.parseDouble(data[i][2]));
				i++;
			}
			
			int rowNumber = 0;
			while ((rows<data.length-1)&& (data[rows][0].equalsIgnoreCase(data[rows+1][0]))) {

				/*row1 = writableSheet.createRow((short) rows);
				@SuppressWarnings("deprecation")
				HSSFCell cella1 = row1.createCell((short) 0);
				cella1.setCellValue(data[rows][0]);*/
				
				row1 = writableSheet.createRow((short) rowNumber++);
				 
				@SuppressWarnings("deprecation")
				HSSFCell cella2 = row1.createCell((short) 0);
				cella2.setCellValue(data[rows][1]);

				@SuppressWarnings("deprecation")
				HSSFCell cella3 = row1.createCell((short) 1);
				cella3.setCellValue(data[rows][2]);

				@SuppressWarnings("deprecation")
				HSSFCell cella4 = row1.createCell((short) 2);
				double value = ((Double.parseDouble(data[rows][2])) / (double) total) * 100;
				cella4.setCellValue(new DecimalFormat("#.##").format(value)
						+ "%");

				rows++;

			}

			rows++;
		}
		
		writableWorkbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	HashMap<String, Integer> count = new HashMap<String, Integer>();
	private void count(String[][] data,
			HashMap<String, HashMap<String, EntANDNumner>> structure) throws IOException {
		// TODO Auto-generated method stub
		
		count = new HashMap<String, Integer>();
		for(int i =1;i<data.length;i++){
			if(count.containsKey(data[i][0])){
				
				int increment = count.get(data[i][0])+1;
				count.put(data[i][0], (increment));
			} else{
				count.put(data[i][0], 0);
			}
			
		}
		
		HSSFWorkbook writableWorkbook = null;
		Iterator<Entry<String, Integer>> it = count.entrySet().iterator();
		int rows = 0;
		writableWorkbook = createWorkBook();
		HSSFSheet writableSheet = createNewSheet(writableWorkbook, "EmpID Per JobID");
		while( it.hasNext()){
			
			
			Entry<String, Integer> en = it.next();
			if(en.getKey().equals("")){
				
			} else{
				System.out.println("job id = "+ en.getKey()+"and the numner of accounts = "+ en.getValue());
				HSSFRow row1 = writableSheet.createRow((short)rows);
				@SuppressWarnings("deprecation")
				HSSFCell cellA1 = row1.createCell((short) 0); 
				cellA1.setCellValue(en.getKey());
				
				@SuppressWarnings("deprecation")
				HSSFCell cellA2 = row1.createCell((short) 1); 
				cellA2.setCellValue(en.getValue());
				rows++;
			}
			
			
		}
		
		
		
		writableWorkbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
		
		
	}
	
	
	private HSSFSheet createNewSheet(HSSFWorkbook writableWorkbook,
			String department) {
		// TODO Auto-generated method stub

		/*
		 * WritableSheet writableSheet = writableWorkbook.createSheet(
		 * department, sheetNumber);
		 */
		// System.out.println("department name = "+ department);
		HSSFSheet writableSheet = writableWorkbook.createSheet(department);

		return writableSheet;

	}
	
	private HSSFWorkbook createWorkBook() throws FileNotFoundException {
		// TODO Auto-generated method stub

		fileOut = new FileOutputStream("Rep9242015_output_14611JobID.xls");
		HSSFWorkbook workbook = new HSSFWorkbook();

		/*
		 * File exlFile = new File("output.xls"); try { WritableWorkbook
		 * writableWorkbook = Workbook.createWorkbook(exlFile);
		 * 
		 * return writableWorkbook;
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return workbook;

	}
	
	
}

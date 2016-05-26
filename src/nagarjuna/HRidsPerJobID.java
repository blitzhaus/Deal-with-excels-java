package nagarjuna;

import java.io.FileReader;
import java.util.HashMap;

import com.opencsv.CSVReader;

public class HRidsPerJobID {

	public static void main(String[] args){
		new HRidsPerJobID();
	}
	
	public HRidsPerJobID(){
		



		CSVReader reader = new CSVReader(new FileReader("ajith.csv"));
		String[] nextLine;
		int i = 0;
		int j = 0;
		String[][] data = null;

		// this while group will remove the blanks and the inactive users
		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line

			if ((nextLine[0].equals(null))||(nextLine[2].equals("")) ||(nextLine[2].equals(null)) || (nextLine[2].equals("")) || (nextLine[3].equalsIgnoreCase("A") == false)) {

			} else {
				// this is the number of rows
				i++;
			}

		}

		data = new String[i][4];
		reader.close();
		reader = new CSVReader(new FileReader("ajith_1.csv"));
		System.out.println("i = "+ i);
		i = 0;
		
		HashMap<String, HashMap<String, EntANDNumner>> structure = new HashMap<String, HashMap<String,EntANDNumner>>();
		
		// this while loop will read the processed sheet data into "data" structure
		while ((i < data.length) && ((nextLine = reader.readNext()) != null)) {

			if ((nextLine[0].equals(null))||(nextLine[2].equals("")) ||(nextLine[2].equals(null)) || (nextLine[2].equals("")) || (nextLine[3].equalsIgnoreCase("A") == false)) {

			} else {
				data[i][0] = nextLine[0];
				if(structure.containsKey(data[i][0])){
					
				} else {
					structure.put(data[i][0], new HashMap<String, EntANDNumner>());
					count.put(data[i][0], 0);
				}
				data[i][1] = nextLine[1];
				data[i][2] = nextLine[2];
				data[i][3] = nextLine[3];
				i++;
				/*
				 * data[i][3] = nextLine[3]; data[i][4] = nextLine[4];
				 */
			}

		}
		System.out.println("number of lines = " + data.length);

/*		for (int m = 0; m < data.length; m++) {
			for (int n = 0; n < data[m].length; n++) {
				System.out.print(data[m][n] + ", ");
			}
			System.out.println();
		}
*/
		count(data, structure);
	
	
	}
}

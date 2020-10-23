import java.util.*;


import java.io.*;

public class AverageMaker {
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	static class DataPair{
		int Count =0;
		double Data;
		
		public void setCount() {
			this.Count++;
		}
		
		public int getCount() {
			return Count;
		}
		
		public void setData(double x) {
			this.Data = x;
		}
		
		public double getData() {
			return Data;
		}
	}
	public static void main(String[] args) throws IOException{
		//TODO: make pair array of value and count
		ArrayList<DataPair> arrData= new ArrayList<DataPair>();
		BufferedReader csvReader = new BufferedReader(new FileReader("Grids.csv"));
		String row;
		//read in values from CSV file
		while (( row = csvReader.readLine()) != null) {
			
		    String[] data = row.split(",");
		    //checking to see if the first value is a heading or number
		    if (!isNumeric(data[0])) {
				System.out.print("Not a number, shh \n");
				continue;
			}
		    // if number of clues exists in the arraylist it will be incremented and the value added, otherwise a new entry will be made
		    if(Integer.parseInt(data[0]) +1> arrData.size()) {
			    System.out.print("Didn't find " + data[0] + " so we added it \n");
		    	DataPair input = new DataPair();
			    input.Count = 0;
			    input.Data = Double.parseDouble(data[1]);
			    arrData.add(input);
		    }else {
		    	System.out.print("Index " + data[0] + " has been found\n");
		    	//we get the index and the new Data
		    	int countIndex = Integer.parseInt(data[0]);
		    	double DataInc = Double.parseDouble(data[1]);
		    	//get the current count and Data
		    	double DataVal = arrData.get(countIndex).getData() + DataInc;
		    	arrData.get(countIndex).setCount();
		    	arrData.get(countIndex).setData(DataVal);
		    	
		    }
		    
		}
		csvReader.close();
		
		//write in averages into CSV file
		//calculate averages
		double[] averages = new double[arrData.size()];
		for(int i =0; i < arrData.size(); i++) {
			averages[i] = arrData.get(i).getData()/arrData.get(i).getCount();
		}
		//add to CSV file
		FileWriter csvWriter = new FileWriter("Grids_Averaged.csv");
		csvWriter.append("input(number of open spaces),Time(in nanoseconds)");
		csvWriter.append("\n");
		for (int j=0; j < averages.length;j++) {
			csvWriter.append(Integer.toString(j));
			csvWriter.append(",");
			csvWriter.append(Double.toString(averages[j]));
			csvWriter.append("\n");
		}
		csvWriter.flush();
		csvWriter.close();
		
	}
}

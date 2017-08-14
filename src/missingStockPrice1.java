//find missing stock prices (exactly 20 of them)
import java.io.*;
import java.sql.Timestamp;
import java.text.*; //for DateFormat and SimpleDateFormat
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class missingStockPrice1 {
	
	public static LocalDateTime[] timestamps;
	public static double[] prices;
	public static ArrayList<Integer> missing_indices; //record indices of missing prices
	public static int rows;

	public static void main(String[] args) throws ParseException, FileNotFoundException{
		
		Scanner scanner = new Scanner(System.in); //import util
		
		String filePath = new File("").getAbsolutePath();
		//Scanner scanner=new Scanner(new FileReader(filePath + "/src/stocks.txt"));
		
		rows=Integer.parseInt(scanner.nextLine());
		
		//initialize
		timestamps=new LocalDateTime[rows];
		prices=new double[rows];
		missing_indices=new ArrayList<Integer>(20); //record indices of missing prices
		
		/*
		int i=0;
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			String[] entry=line.split("\t");
			
			//capital M: month, capital H:24h, lower m: minute, lower h:12h
			DateTimeFormatter formatter= DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss"); //format is minimum number of digits for each field, but can handle more digits
			LocalDateTime timestamp=LocalDateTime.parse(entry[0],formatter);

			//Record timestamp and price for current record
			timestamps[i]=timestamp;
			
			if(entry[1].contains("Missing")){
				prices[i]=-1;
				missing_indices.add(i);
			}
			else{	
				prices[i]=Double.parseDouble(entry[1]);
			}
			
			i++;
		}
		*/
		
		for (int i=0; i<rows; i++){
			
			String line = scanner.nextLine();
			String[] entry=line.split("\t");
			
			//capital M: month, capital H:24h, lower m: minute, lower h:12h
			DateTimeFormatter formatter= DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss"); //format is minimum number of digits for each field, but can handle more digits
			LocalDateTime timestamp=LocalDateTime.parse(entry[0],formatter);

			//Record timestamp and price for current record
			timestamps[i]=timestamp;
			
			if(entry[1].contains("Missing")){
				missing_indices.add(i);
			}
			else{	
				prices[i]=Double.parseDouble(entry[1]);
			}
			
		}
		
		
		ArrayList<Double> missing_prices=findMissingStockPrices();
		
		for (Double missing_price : missing_prices){
			System.out.println(missing_price.doubleValue());
		}
		
	}
	
	public static ArrayList<Double> findMissingStockPrices(){
		
		ArrayList<Double> missing_prices=new ArrayList<Double>(20);
		
		for(Integer missing_index: missing_indices){
			
			int[] neighbours=getNeighbours(missing_index.intValue());
			double missing_price=solveEquations(missing_index.intValue(),neighbours[0],neighbours[1]);
			
			missing_prices.add(missing_price);
		}
		
		return missing_prices;
	}
	
	//returns 2 closest non-missing indices to the missing index for value approximation
	public static int[] getNeighbours(int missing_index){
		
		int[] neighbours=new int[2];
		int neighbours_found=0;
		int search_index = missing_index;

		if(missing_index==0){ //if missing price is first value, search forward only
			
			while(neighbours_found != 2)
			{
				search_index++;
				
				if(!missing_indices.contains(search_index))
				{
					neighbours[neighbours_found]=search_index;
					neighbours_found++;
				}
			}
			
		}
		else if(missing_index == (rows-1)){ //if missing price is last value, search backward only
			
			while(neighbours_found != 2)
			{
				search_index--;
				
				if(!missing_indices.contains(search_index)){
					neighbours[neighbours_found]=search_index;
					neighbours_found++;
				}
			}
		}
		else{
			//first neighbour: search backward
			search_index--;
			while(missing_indices.contains(search_index)){
				search_index--;
			}
			if (search_index >= 0)
			{
				neighbours[0]=search_index;
			}else{
				neighbours[0]=-1; //can't find
			}
			//second neighbour: search forward
			search_index=missing_index;
			search_index++;
			while(missing_indices.contains(search_index)){
				search_index++;
			}
			neighbours[1]=search_index;	
		}
		
		return neighbours;
	}
	
	//augmented matrix storing system of linear equations:
	//y1=a*x1+b ---> [x1 1 | y1]
	//y2=a*x2+b ---> [x2 1 | y2]
	public static double solveEquations(int missing_index, int i1, int i2){
		
		double a,b; //coefficients
		
		int r=2;
		int c=3;
		
		double[][] equations=new double[r][c];
		
		//fill in first row using first point
		equations[0][0]=timestamps[i1].getDayOfYear(); 
		equations[0][1]=1; 
		equations[0][2]=prices[i1]; 
				
		//fill in second row using second point
		equations[1][0]=timestamps[i2].getDayOfYear();
		equations[1][1]=1;
		equations[1][2]=prices[i2];
		
		//System.out.println("missing_index:"+missing_index+":["+equations[0][0]+","+equations[0][1]+","+equations[0][2]+"]");
		//System.out.println("missing_index:"+missing_index+":["+equations[1][0]+","+equations[1][1]+","+equations[1][2]+"]");
		
		
		//Gaussian elimination for square
		
		//part 1: top to bottom
		for(int i=0; i<r; i++){ //pivot row
			
			double leading_coefficient=equations[i][i];
			
			//make pivot row leading coefficient 1
			for(int j=0; j<c; j++){
				equations[i][j] /= leading_coefficient; //divide each column in this row by leading coefficient
			}
			
			//make subsequent rows subtract from this row
			for(int i_n=i+1; i_n<r; i_n++){
				
				double multiplier=equations[i_n][i]; //multiplier is the leading coefficient of this row
				
				for (int j_n=0; j_n<c; j_n++)
				{
					equations[i_n][j_n] -= multiplier*equations[i][j_n]; 
				}
			}
			
			//then subtract row below from current row
		}
		
		//part 2: bottom to top
		for (int i=r-1; r>0; r--){
			
			//all the rows above it must subtract this row
			for (int i_n=r-1-i; i_n>=0; i_n--){
				
				double multiplier=equations[i_n][i];
				
				for (int j_n=i_n; j_n<c; j_n++){
					equations[i_n][j_n] -= multiplier*equations[i][j_n];
				}
			}
		}
		
		//after elimination
		a=equations[0][2]; //a
		b=equations[1][2]; //b
		
		return a*timestamps[missing_index].getDayOfYear()+b;
	}
}

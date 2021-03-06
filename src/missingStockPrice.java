//find missing stock prices (exactly 20 of them)
import java.io.*;
import java.sql.Timestamp;
import java.text.*; //for DateFormat and SimpleDateFormat
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class missingStockPrice {
	
	public static LocalDateTime[] timestamps;
	public static double[] prices;
	public static ArrayList<Integer> missing_indices; //record indices of missing prices
	public static ArrayList<Integer> valid_indices;//record indices of valid (not missing) prices
	public static int rows;
	public static final int num_missing=20;

	public static void main(String[] args) throws ParseException, FileNotFoundException{
		
		Scanner scanner = new Scanner(System.in); //import util
		
		rows=Integer.parseInt(scanner.nextLine());
		
		//initialize
		timestamps=new LocalDateTime[rows];
		prices=new double[rows];
		missing_indices=new ArrayList<Integer>(num_missing); //record indices of missing prices
		valid_indices=new ArrayList<Integer>();
		
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
				valid_indices.add(i);
			}
			
		}
		
		
		ArrayList<Double> missing_prices=findMissingStockPrices();
		
		for (Double missing_price : missing_prices){
			System.out.println(missing_price.doubleValue());
		}
		
	}
	
	public static ArrayList<Double> findMissingStockPrices(){
		
		ArrayList<Double> missing_prices=new ArrayList<Double>(num_missing);
		
		for(Integer missing_index: missing_indices){
			
			ArrayList<Integer> valid_subset=getNeighbours(missing_index.intValue());
			
			double[] coefficients=solveEquations(valid_subset);
			double missing_price=0;//compute y, our stock price at the missing index
			
			for(int i=0; i<coefficients.length;i++){
				
				//y=C0(x^n)+C1(x^(n-1))+C2(x^(n-2)).....+Cn-2(x^2)+Cn-1(x)+Cn
				double x=(double)timestamps[missing_index].getDayOfYear()/365;
				missing_price += coefficients[i]*Math.pow(x,coefficients.length-1-i);
				//intln("coefficient["+i+"]:"+coefficients[i]+",x:"+x);
			}
			
			missing_prices.add(missing_price);
		}
		
		return missing_prices;
	}
	
	//filtering options based on neighbouring dates and values
	public static ArrayList<Integer> getNeighbours(int missing_index){
		
		ArrayList<Integer> valid_subset = new ArrayList<Integer>();
		
		int min_neighbours=1;
		int index_range=1;
		boolean index_range_found=false;
		
		while (!index_range_found)
		{
			int neighbours_count=0;
			index_range++;
			
			for(Integer valid_index: valid_indices)
			{
				//boolean within_date_range=Math.abs(timestamps[valid_index].getDayOfYear()-timestamps[missing_index].getDayOfYear()) < date_range;
				boolean within_index_range=Math.abs(valid_index-missing_index) < index_range;
				
				if (within_index_range)
				{
					neighbours_count++;
				}
			}
			
			if(neighbours_count >= min_neighbours){
				index_range_found=true;
			}
		}
		
		//use the range found to add all points within the range to the valid subset
		for(Integer valid_index: valid_indices)
		{
			boolean within_index_range=Math.abs(valid_index-missing_index) < index_range;
			
			if (within_index_range)
			{
				valid_subset.add(valid_index);
			}
		}
		
		
		return valid_subset;
	}
	
	//augmented square NxN matrix storing system of linear matrix, where N=number of valid indices
	//y1=a*x1+b ---> [x1 1 | y1]
	//y2=a*x2+b ---> [x2 1 | y2]
	public static double[] solveEquations(ArrayList<Integer> valid_subset){
		
		double[] coefficients=new double[valid_subset.size()];
		
		//number of rows and columns
		int r=valid_subset.size();
		int c=valid_subset.size()+1; //the "+1" column is the augmented part
		
		double[][] matrix=new double[r][c];
		
		//populate matrix
		for(int i=0; i<r; i++){ //go through all the valid points
			for(int j=0; j<c-1; j++){
				double x=(double)timestamps[valid_subset.get(i)].getDayOfYear()/365;
				matrix[i][j]=Math.pow(x,c-2-j); //c-2 because of extra augmented column
				
			}
			//last value:
			matrix[i][c-1]=prices[valid_subset.get(i)];
			//System.out.println("matrix["+i+"]["+(c-1)+"]:"+matrix[i][c-1]);
		}
		
		
		//Gaussian elimination for square
		
		//part 1: top to bottom
		for(int i=0; i<r; i++){ //pivot row
			
			double leading_coefficient=matrix[i][i];
			
			//make pivot row leading coefficient 1
			for(int j=0; j<c; j++){
				matrix[i][j] /= leading_coefficient; //divide each column in this row by leading coefficient
			}
			
			//make subsequent rows subtract from this row
			for(int i_n=i+1; i_n<r; i_n++){
				
				double multiplier=matrix[i_n][i]; //multiplier is the leading coefficient of this row
				
				for (int j_n=0; j_n<c; j_n++)
				{
					matrix[i_n][j_n] -= multiplier*matrix[i][j_n]; 
					//System.out.println("i="+i+",After subtracting "+multiplier+"*"+matrix[i][j_n]+": matrix["+i_n+"]["+j_n+"]:"+matrix[i_n][j_n]);
				}
			}
			
			//then subtract row below from current row
		}

		//part 2: bottom to top
		for (int i=r-1; i>0; i--){
			
			//all the rows above it must subtract this row
			for (int i_n=i-1; i_n>=0; i_n--){
				
				double multiplier=matrix[i_n][i];
				
				for (int j_n=i_n; j_n<c; j_n++){
					matrix[i_n][j_n] -= multiplier*matrix[i][j_n];
					//System.out.println("i="+i+",i_n="+i_n+",After subtracting "+multiplier+"*"+matrix[i][j_n]+": matrix["+i_n+"]["+j_n+"]:"+matrix[i_n][j_n]);
				}
			}
		}
		
		//get coefficient values from matrix
		for (int i=0; i<r; i++){
			coefficients[i]=matrix[i][c-1];
		}
		
		return coefficients;
	}
}

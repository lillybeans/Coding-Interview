import java.io.*;
import java.util.*;

public class spiralMatrix {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        
        Scanner scanner = new Scanner(System.in);
        String rc = scanner.nextLine();
        
        String[] numbers = rc.split(",");
        int rows=Integer.parseInt(numbers[0]); //number of rows
        int cols=Integer.parseInt(numbers[1]); //number of columns
        
        int total_layers = (Math.min(rows,cols)+1)/2-1; //total number of layers: determined by smaller number of rows and columns, then take ceil(min/2)
        
        String[][] matrix = new String[rows][cols];
        
        for (int i=0; i<rows; i++){
            matrix[i] = scanner.nextLine().split(",");
        }
        
    	for (int layer=0; layer<=total_layers; layer++){
            
            //top segment: going right
            for (int col=layer; col<=cols-layer-1; col++){
                if (layer == 0 && col == 0)
                {
                    System.out.print(matrix[layer][col]);
                }
                else{
                    System.out.print("," + matrix[layer][col]);
                }
            }
                
            //right segment: going down
            for (int row=layer+1; row<=rows-layer-1;row++){
                System.out.print("," + matrix[row][cols-layer-1]);
            }
            
            //bottom segment: going left
            for (int col=cols-layer-2; col>=layer; col--){
                if (rows - layer - 1> layer)
                {
                    System.out.print("," + matrix[rows-layer-1][col]); //if this is not the same row as top segment
                }
            }
            
            //left segment: going up
            for (int row=rows-layer-2; row>layer; row--){
                if (cols - layer - 1 > layer)
                {
                    System.out.print("," + matrix[row][layer]); //if this is not the same column as bottom segment
                }
            }
            
        }
       
    }
}
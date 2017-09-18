import java.util.*;
	
public class Minesweeper {
	    public char[][] updateBoard(char[][] board, int[] click) {
	        int row=click[0];
	        int col=click[1];
	        
	        if(board[row][col]=='M'){ //uncovered mine
	            board[row][col]='X'; //game over
	            return board; //return
	        }
	        
	        //else if empty, get solution
	        char[][] solution = new char[board.length][board[0].length]; //initialize solution as copy of board

	        for(int i=0; i<board.length; i++){
	            for(int j=0; j<board[0].length; j++){
	                solution[i][j]=board[i][j];
	            }
	        }

	        getSolution(solution); //stores solution in 'solution' 2D array

	        reveal(board, solution, row, col);

	        return board;
	    }
	    
	    public void reveal(char[][] board, char[][] solution, int row, int col){ //reveal the solution as much as needed
	        
	        board[row][col]=solution[row][col]; //reveal me
	        
	        int rows=board.length;
	        int cols=board[0].length;
	        
	        if(board[row][col]=='B'){ //if I'm blank, reveal my neighbours
	            
	            ArrayList<int[]> neighbours = getNeighbours(row,col,rows,cols);
	            for(int[] nb: neighbours){
	                int nb_row=nb[0];
	                int nb_col=nb[1];
	                
	                if(board[nb_row][nb_col]=='E'){//if we haven't visited this neighbor yet
	                    reveal(board,solution,nb_row,nb_col); //reveal neighbour 
	                }
	                
	            }
	        }
	        
	    }
	    
	    public void getSolution(char[][] board){ //solve the entire minesweeper
	        
	        int rows=board.length;
	        int cols=board[0].length;
	                     
	        //1. find all bombs and update their neighbors
	        for(int i=0; i<rows; i++){
	            for(int j=0; j<cols; j++){
	                if(board[i][j]=='M'){
	                    ArrayList<int[]> neighbours = getNeighbours(i,j,rows,cols);
	                    
	                    for(int[] nb: neighbours){
	                        int nb_row=nb[0];
	                        int nb_col=nb[1];
	                        if(board[nb_row][nb_col]=='M'){ //bomb
	                            continue;
	                        }
	                        if(board[nb_row][nb_col]=='E'){
	                            board[nb_row][nb_col]='1';
	                        }else{ //1-7
	                            board[nb_row][nb_col]++;
	                        }
	                    }
	                }
	            }
	        }
	        
	        //2. after bomb positions have been identified: change all the cells that are still 'E's to 'B's
	        for(int i=0; i<rows; i++){
	            for(int j=0; j<cols; j++){
	                if(board[i][j]=='E'){
	                    board[i][j]='B';
	                }
	            }
	        }
	    }
	    

	    
	    public ArrayList<int[]> getNeighbours(int row, int col, int rows, int cols){
	        ArrayList<int[]> valid_neighbours = new ArrayList<int[]>();
	        int[][] neighbours = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

	        for(int[] neighbour: neighbours){ //go though my neighbors
	            int nb_row=row+neighbour[0];
	            int nb_col=col+neighbour[1];

	            if(!(nb_row < 0 || nb_col < 0 || nb_row > rows-1 || nb_col > cols-1)){ //if neighbor within bounds
	                valid_neighbours.add(new int[]{nb_row, nb_col});
	            }
	        }
	    
	        return valid_neighbours;
	    }
}



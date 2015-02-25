package com.pk.talal.matrix;
import java.util.*;

//class for matrix to hold information about the matrix
public class Matrix {
	
	public int numrows;
    public int numcols;
    public int[][] data;
    


	//intialize the variables of object matrix according to value provided
	public void intialize(int numrows, int numcols) {
		this.numrows = numrows;
		this.numcols = numcols;
		//intialize the size of the matrix
		data = new int[numrows][numcols];
	}
	
	//Fill the matrix with the appropriate data
	public void FillMatrix(ArrayList<Integer> A){
		int j =0;
	      for (int c = 0 ; c < numrows ; c++ ){
	          for (int d = 0 ; d < numcols ; d++ ){
	        	 data[c][d] = A.get(j);
	             j++;
	          }
	      }
	    
	  //PrintMatrix();
	}
	
	//print matrix for checking
	public boolean PrintMatrix(){
		for (int c = 0 ; c < numrows ; c++ ){
	         {
	            for (int d = 0 ; d < numcols ; d++ )
	               System.out.print(data[c][d]+"\t");
	 
	            System.out.print("\n");
	         }
	      }
		
		return true;
	}
	

}

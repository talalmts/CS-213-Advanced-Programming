package com.pk.talal.matrix;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//Matrix Calculator class for basic operation of i.e Addition, Subtraction and Multiplication
public class MatrixCalculator {
	
	//List of object of class Matrix to store multiple numbers of Matix read from the file.
	static List<Matrix> Lst = new LinkedList<Matrix>();

	//function for reading the sample file and filling the matrix according
	void ReadFile(){

		//reading from the file and placing it in the matrix 
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users/Talal Saleem/workspace/AP/MatrixCal/MatrixCal/src/com/pk/talal/matrix/matrix.txt")))
		{
			String readline;
			while ((readline = br.readLine()) != null) {
				//System.out.println(readline);
				newMatrix(readline);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}
	//Filling the matrix with the correct values
	int newMatrix(String line){
		Matrix m = new Matrix();
		//Arraylist for adding element to the it. and later using it to fill the Matrix 
		ArrayList<Integer> E = new ArrayList<Integer>();
		//separating the string with token ; to separate rows
		String[] r = line.split(";");
		//System.out.println(r.length);

		for(int i =0 ; i<r.length;i++){
			//again separating the provided string to get the element
			String[] element = r[i].split("-");
			//adding the element to the arraylist
			for(int j = 0 ; j<element.length;j++){
				E.add(Integer.parseInt(element[j]));
			}
		
			//intailize=ing the matrix with number of rows and number of columns
			m.intialize(r.length, element.length);
		}
		//System.out.println(E);
		//calling function to fill the 2d array with data
		m.FillMatrix(E);
		//adding matrix to the Global list of matrix
		Lst.add(m);
		return 0;
	}
	
	//adding two matrix 
	public Matrix AddMatrix(Matrix m , Matrix n){
		Matrix Result = new Matrix();
		if(m.numrows == n.numrows && m.numcols == n.numcols){
			Result.intialize(m.numrows, m.numcols);
			for (int c = 0 ; c < m.numrows ; c++ ){
				for (int  d = 0 ; d < m.numcols ; d++ ){
					Result.data[c][d] = m.data[c][d] + n.data[c][d];
				}
			}
			return Result;
		}
		System.out.println("Not Possible. Mismatching dimension of matrix");
		return Result;
	}
	
	//subtracting two matrix
	public Matrix SubtractMatrix(Matrix m , Matrix n){
		Matrix Result = new Matrix();
		if(m.numrows == n.numrows && m.numcols == n.numcols){
			Result.intialize(m.numrows, m.numcols);
			for (int c = 0 ; c < m.numrows ; c++ ){
				for (int  d = 0 ; d < m.numcols ; d++ ){
					Result.data[c][d] = m.data[c][d] - n.data[c][d];
				}
			}
			return Result;
		}
		System.out.println("Not Possible. Mismatching dimension of matrix");
		return Result;
	}
	
	//Multiply two matrix using simple ijk method
	public Matrix MultiplyMatrix(Matrix m , Matrix n){
		Matrix Result = new Matrix();
		if(m.numcols == n.numrows){
		Result.intialize(m.numrows, n.numcols);

		for (int i=0; i< m.numrows; i++){
			for (int j=0; j< n.numcols; j++){
				for (int k=0; k< m.numcols; k++){
					Result.data[i][j] += m.data[i][k] * n.data[k][j];
				}
			}
		}
		return Result;
	}
	System.out.println("Not Possible. Mismatching dimension of matrix");
	return Result;
	}
	//printing matrix

	//getter and setter
	
	public List<Matrix> GetMatrixList(){
		return Lst;
	}


//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
////		MatrixCalculator a = new MatrixCalculator();
////		
////		
////		a.ReadFile();
////		System.out.println(Lst.get(0).PrintMatrix());
////		System.out.println(Lst.get(1).PrintMatrix());
//////		System.out.println(Lst.get(2).PrintMatrix());
////		Matrix Res = a.MultiplyMatrix(Lst.get(0),Lst.get(1));
////		Res.PrintMatrix();
////		//a.FillMatrix(E);
////		//a.AddMatrix(m, n);
//	}
}
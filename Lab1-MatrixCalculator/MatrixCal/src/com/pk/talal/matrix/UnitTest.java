package com.pk.talal.matrix;

import java.util.LinkedList;
import java.util.List;

//Unit Test class. This class Test basic functionality of Matrix computer by applying few operation
//on the matrix which are read from the sample file the output is provide is 
//'Not Possible. Mismatching dimension of matrix'
public class UnitTest {
	
	List<Matrix> Lst = new LinkedList<Matrix>();
	
	//Testing Multiplication of two matrix from the sample file read.
	public void MultiplyTwoMatrix(Matrix a, Matrix b){
		MatrixCalculator Cal = new MatrixCalculator();
		System.out.println("---Testing two matrix---");
		int[ ][ ] answer = new int[2][2];
		//adding two matrix
		Matrix R = Cal.MultiplyMatrix(a, b);
		//Pre-calculated answer is hard-coded to check the answer for operations
		answer[0][0] = 32;
		answer[0][1] = 46;
		answer[1][0] = 45;
		answer[1][1] = 68;
		
		for(int i = 0 ;i<2;i++){
			for(int j =0 ;j<2;j++){
				if(answer[i][j] == R.data[i][j]){
					
				}
				else{
					System.out.println("----Test Failed---");
					return;
				}
			}
		}
		R.PrintMatrix();
		System.out.println("---Two Matrix Multiplication test sucessfull---");
				
	}
	
	//testing of three matrix addition
	public void AddThreeMatrix(Matrix a, Matrix b,Matrix c){
		MatrixCalculator Cal = new MatrixCalculator();
		System.out.println("---Testing Addition of three Matrix---");
		int[ ][ ] answer = new int[2][3];
		//Adding first two matrix
		Matrix temp = Cal.AddMatrix(a, b);
		//than adding the third matrix to the result of the previous operation
		Matrix R = Cal.AddMatrix(temp, c);
		//Pre-calculated answer is hard-coded to check the answer for operations
		answer[0][0] = 3;
		answer[0][1] = 7;
		answer[0][2] = 9;
		answer[1][0] = 13;
		answer[1][1] = 12;
		answer[1][2] = 14;
		for(int i = 0 ;i<2;i++){
			for(int j =0 ;j<3;j++){
				if(answer[i][j] == R.data[i][j]){
					
				}
				else{
					System.out.println("----Test Failed---");
					return;
				}
			}
		}
		
		
		R.PrintMatrix();
		System.out.println("---Three Matrix Addition test sucessfull---");
	}
	
	//Testing of Multiplication of two matrix than adding one matrix to it.
	public void MultiplyAndAddMatrix(Matrix a,Matrix b,Matrix c){
		MatrixCalculator Cal = new MatrixCalculator();
		System.out.println("---Testing Multiplication of two Matrix than Add one Matrix---");
		int[ ][ ] answer = new int[2][3];
		//Multiplying the first two matrix
		Matrix temp = Cal.MultiplyMatrix(a, b);
		//than adding the third matrix to the previous result
		Matrix R = Cal.AddMatrix(temp, c);
		//Pre-calculated answer is hard-coded to check the answer for operations
		answer[0][0] = 34;
		answer[0][1] = 50;
		answer[1][0] = 46;
		answer[1][1] = 71;
		
		for(int i = 0 ;i<2;i++){
			for(int j =0 ;j<2;j++){
				if(answer[i][j] == R.data[i][j]){
					
				}
				else{
					System.out.println("----Test Failed---");
					return;
				}
			}
		}
		
		
		
		R.PrintMatrix();
		System.out.println("---Multiplication of two Matrix than Add one Matrix test sucessfull---");
		
		
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MatrixCalculator Cal = new MatrixCalculator();
		Cal.ReadFile();
		UnitTest T = new UnitTest();
		T.Lst = Cal.GetMatrixList();

		
		//Testing by Multiply List[0] and List[1] matrix from the read file
		T.MultiplyTwoMatrix(T.Lst.get(0), T.Lst.get(1));
		//Testing by Adding List[0], List[2] and List[3] matrix from the read file
		T.AddThreeMatrix(T.Lst.get(0), T.Lst.get(2), T.Lst.get(3));
		//Testing by Multiplying List[0] and List[1] and than adding List[4] matrix from the read file
		T.MultiplyAndAddMatrix(T.Lst.get(0), T.Lst.get(1), T.Lst.get(4));

		
		
	}

}

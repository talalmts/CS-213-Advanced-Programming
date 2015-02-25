package pk.com.lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Interpret {
	
	//hashmap for stroing variables value against variable name. key value pair
	HashMap<String, Integer> Var;

	//default constructor
	Interpret() {
		Var = new HashMap<String, Integer>();
	}

	//reading file provided by the user
	void ReadFile(String file) {

		// reading from the file and placing it in the matrix
		try (BufferedReader br = new BufferedReader(
				new FileReader(file))) {
			String readline;
			while ((readline = br.readLine()) != null) {
				System.out.println("Readline :" +readline);
				//Each line read from the file is sent to check the proper syntax  
				CheckSyntax(readline);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//function to check the syntax of the line
	void CheckSyntax(String readline) {
		//split the line on the basics of "space"
		String[] r = readline.split(" ");
		//check if the line contains let than pass it on to the let syntax checker
		if (r[0].matches("let")) {
			if(checklet(readline)){
				System.out.println("---Check Passed---");
				letline(readline);
				//System.out.println("----END---");
			}
		//check if the line contains print than pass it on to the print function
		} else if (r[0].matches("print")) {
			printline(readline);
			//System.out.println("----END---");
		} 
		//check if the line contains '=' than pass it on to operation function where the expression is evaluated and executed 
		else if(readline.contains("=")){
			//System.out.println("has =");
			operation(readline);
			System.out.println(Var);
			//System.out.println("----END---");
		}
		//Functionality is provided or the syntax is wrong
		else{
			//System.out.println("BAD scene");
			System.out.println(" Systax Error in line : " +readline );
		}

	}
	
	//check the syntax of let line
	private boolean checklet(String readline){
		String[] token = readline.split(" ");
		//System.out.println("----In string check----");
		if(token.length != 4 ){
			System.out.println(" Systax Error in line : " +readline );
			return false;
		}
		//should be a string variable
		if(token[1].matches("[0-9]+")){
			System.out.println(" Systax Error in line : " +readline);
			return false;
		}
		//should be an assignment operator
		if(!token[2].matches("=")){
			System.out.println(" Systax Error in line : " +readline);
			return false;
		}
//		//should be a digit
//		if(!token[3].matches("[0-9]+")){
//			System.out.println(" Systax Error in line : " +readline);
//			return false;
//		}
		
		return true;
	}

	//assign the key value pair to hashmap
	private void letline(String readline) {
		// TODO Auto-generated method stub
		int a =0;
		String[] r = readline.split(" ");
		//System.out.println("---letline function---");
		if (Var.get(r[3]) != null) {
			a = Var.get(r[3]);
		} else if(r[3].matches("[0-9]+")) {
			a = Integer.parseInt(r[3]);
		}
		else {
			System.out.println(r[3]+" not intialized");
			System.out.println(" Systax Error in line : " +readline);
			return;
		}
		
		Var.put(r[1], a);
		System.out.println(Var);
		System.out.println("----END---");

	}
	//check the proper syntax and print appropriate output
	private void printline(String readline) {
		// TODO Auto-generated method stub
		System.out.println("---Print function---");
		String[] r = readline.split("print ");
		//System.out.println("r = "+r[0]);
		//print out the classic print statement
		if(r[1].startsWith("\"") && r[r.length-1].endsWith("\"")){
			String temp = r[1].replace("\"", "");
			System.out.println(temp);
			return;
		}
		r = readline.split(" ");
		//check for proper print syntax
		if(r.length != 2){
			System.out.println(" Systax Error in line : " +readline);
		}
		//check if the asked variable is initialized or not
		if (Var.get(r[1]) == null) {
			//if not thus throw exception
			System.out.println("Variable not intialized");
			System.out.println(" Systax Error in line : " +readline);
			return;
		}
		//else print the value of the desired variable
		else if(Var.get(r[1]) != null) {
//			System.out.println(r[1]);
			System.out.println("Value of "+ r[1]+" = " + Var.get(r[1]));
		}
		else{
			System.out.println(" Systax Error in line : " +readline);
		}
	
		System.out.println("----END---");

	}

	private void operation(String readline) {
		// TODO Auto-generated method stub
		int a = 0, c = 0;
		System.out.println("----Arthemetic function---");
		//split on the basics of  "space" to extract the expression 
		String[] r = readline.split(" ");
		//System.out.println(r[0]);
		//System.out.println("get " + Var.get(r[0]));
		
		//if the expression if of type 
		// x = 8 || x = y
		if(r.length == 3){
			int y =0;
			//should be a string
			if(r[0].matches("[0-9]+")){
				System.out.println(" Systax Error in line : " +readline);
				return;
			}
			//should be equal to =
			if(!r[1].matches("=")){
				System.out.println(" Systax Error in line : " +readline);
				return;
			}
			//the variable asked is assigned else throw error
			if (Var.get(r[0]) == null) {
				System.out.println("Variable not intialized");
				System.out.println(" Systax Error in line : " +readline);
				return;
			}
			//get the variable value
			int z = Var.get(r[0]);
			//get the assigning value 
			if (Var.get(r[2]) != null) {
			y = Var.get(r[2]);
			}else if(r[2].matches("[0-9]+")) {
				a = Integer.parseInt(r[2]);
			}
			else {
				System.out.println(r[2]+" not intialized");
				System.out.println(" Systax Error in line : " +readline);
				return;
			}
			//update the variable
			Var.put(r[0], y);
			System.out.println(Var);
			return;
		}
		
		//if expression of type 
		// x = x + 3 || y = 3 + 3 || x = x + y
		//should be string so it can be assigned
		if(r[0].matches("[0-9]+")){
			System.out.println(" Systax Error in line : " +readline);
			return;
		}
		//should contain =
		if(!r[1].matches("=")){
			System.out.println(" Systax Error in line : " +readline);
			return;
		}
		//varible to assigned must not be null
		if (Var.get(r[0]) == null) {
			System.out.println("Variable not intialized");
			return;
		}
		//get the variable
		int res = Var.get(r[0]);
		//get the operands
		if (Var.get(r[2]) != null) {
			a = Var.get(r[2]);
		} else if(r[2].matches("[0-9]+")) {
			a = Integer.parseInt(r[2]);
		}
		else {
			System.out.println(r[2]+" not intialized");
			System.out.println(" Systax Error in line : " +readline);
			return;
		}
		
		if (Var.get(r[4]) != null) {
			c = Var.get(r[4]);
		} else if(r[4].matches("[0-9]+")) {
			a = Integer.parseInt(r[2]);
		}
		else {
			System.out.println(r[4]+" not intialized");
			System.out.println(" Systax Error in line : " +readline);
			return;
		}
		//applying appropriate operation as provided
		if (r[3].contains("+")) {
			//System.out.println("Hello");
			res = a + c;
			//System.out.println("res = " + res);
			//updating the result in the variable
			Var.put(r[0], res);
			return;
		}
		if (r[3].contains("-")) {
			res = a - c;
			//System.out.println("res = " + res);
			//updating the result in the variable
			Var.put(r[0], res);
			return;
		}
		if (r[3].contains("*")) {
			//System.out.println("Hello");
			res = a * c;
			//updating the result in the variable
			//System.out.println("res = " + res);
			Var.put(r[0], res);
			return;
		}
		if (r[3].contains("/")) {
			res = a / c;
			//updating the result in the variable
			Var.put(r[0], res);
			return;
		}

		System.out.println("----END---");
	}

	void print() {
		System.out.println(Var);

	}

//	public static void main(String[] args) {
////		Interpret I = new Interpret();
////		I.ReadFile();
////		I.print();
//	}

}

package pk.com.lab2;

public class UnitTest {

	
		void UnitTest_let(){
			String let = "C:\\Users/Talal Saleem/workspace/AP/Interpreter/Interpreter/src/let.txt";
			Interpret I = new Interpret();
			I.ReadFile(let);
		}
		
		void UnitTest_op(){
			String op = "C:\\Users/Talal Saleem/workspace/AP/Interpreter/Interpreter/src/op.txt";
			Interpret I = new Interpret();
			I.ReadFile(op);
			//I.print();
		}
		
		void UnitTest_command(){
			String command = "C:\\Users/Talal Saleem/workspace/AP/Interpreter/Interpreter/src/command.txt";
			Interpret I = new Interpret();
			I.ReadFile(command);
			
		}
		
		void UnitTest_print(){
			String print = "C:\\Users/Talal Saleem/workspace/AP/Interpreter/Interpreter/src/print.txt";
			Interpret I = new Interpret();
			I.ReadFile(print);
		}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UnitTest Test = new UnitTest();
		//Test one file at a time
		Test.UnitTest_let();
		Test.UnitTest_op();
		Test.UnitTest_print();
		Test.UnitTest_command();
		
	}

}

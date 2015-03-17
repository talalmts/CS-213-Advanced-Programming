package pk.com.assignment1;

import java.util.Stack;

public class UnitTest {


	void UnitTest_Optimaltime(Lane L1,Lane L2){
		System.out.println("\n---UnitTest for checking the Optimal solution----");
		AssemblyLine A = new AssemblyLine();
		//run the scheduler for the given problem
		A.FastestWay(L1, L2);
		// optimal solution for this problem is 43
		if(A.getOptimaltime() == 34){
			System.out.println("---Optimal time UnitTest PASSED---");
		}
		else{
			System.out.println("---Optimal time UnitTest FAILED---");	
		}
	}

	void UnitTest_PathTest(Lane L1,Lane L2 ){
		System.out.println("\n---UnitTest for Optimal Path----");
		AssemblyLine A = new AssemblyLine();
		//run the scheduler for the given problem
		A.FastestWay(L1, L2);
		A.path();
		//The path should be
		Stack<Integer> original = new Stack<Integer>();
		original.push(2);
		original.push(2);
		original.push(2);
		original.push(1);
		original.push(1);
		original.push(2);
		if(A.getPath().equals(original)){
			System.out.print("\n---UnitTest for Optimal Path PASSED----");
		}
		else{
			System.out.println("---Path UnitTest FAILED---");	
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Assembly lane 1 Description
		//Time for car to process at each station in Assembly lane 1
		int[] perStime={7,1,1,10,5,4};
		//swap time from Assembly lane 1 --> Assembly lane 2
		int[] swaplane={2,3,2,4,1};
		//Entry time to enter Assembly lane 1
		int entrytime=11;
		//Exit time to exit from Assembly lane 1
		int exittime=7;
		Lane L1 =  new Lane(perStime,swaplane,entrytime,exittime);
		//System.out.println(L1.getTtime(4));
		L1.printLane(1);
		System.out.println();

		//Assembly lane 2 Description
		//Time for car to process at each station in Assembly lane 2
		int [] perStimeL2={10,7,3,4,5,2};
		//swap time from Assembly lane 1 --> Assembly lane 2
		int [] swaplaneL2={2,1,3,4,5};
		//Entry time to enter Assembly lane 2
		entrytime=4;
		//Exit time to exit from Assembly lane 2
		exittime=3;
		Lane L2 =  new Lane(perStimeL2,swaplaneL2,entrytime,exittime);
		L2.printLane(2);
//		AssemblyLine A = new AssemblyLine();
//		A.FastestWay(L1, L2);
		
		UnitTest U = new UnitTest();
		U.UnitTest_Optimaltime(L1, L2);
		U.UnitTest_PathTest(L1, L2);
	}

}



import java.util.ArrayList;


public class UnitTest {

	Crawler C = new Crawler();

	void UnitTest_ReadDIR(String Dir){
		System.out.println("---- READING DIRECTORY  \"" + Dir+"\" ----");


		int N = 5;
		ArrayList<Thread> thread = new ArrayList<Thread>(N);
		for (int i = 0; i < N; i++) {
			Thread t = new Thread(C.createWorker());
			thread.add(t);
			t.start();
		}

		C.CrawlDir(Dir);

		C.Workerque.finish();

		for (int i = 0; i < N; i++){
			try {
				thread.get(i).join();
			} catch (Exception e) {};
		}
		for(int i =0 ; i<C.list.size(); i++){
			if(C.list.get(i) ==null){
				continue;
			}
			System.out.println(C.list.get(i).name);
		}
		if(C.list.size() == 3 ){
			System.out.println("-----UNITTEST PASSED----");		
		}
		else{
			System.out.println("-----UNITTEST FAILED----");			
		}
		System.out.println("------READING DIRECTORY COMPLETE-------\n");

	}



	void UnitTest_SearchDIR(String SearchValue){
		int a = C.Search(SearchValue);
		if(a == 1){
			System.out.println("---UNITTEST PASSED : FILE FOUND---\n");
		}
		else{
			System.out.println("---UNITTEST FAILED : FILE NOT FOUND---\n");
		}

	}

	public static void main(String[] args) {

		UnitTest T = new UnitTest();

		String Dir = "D:\\talal/a/sas";
		T.UnitTest_ReadDIR(Dir);

		String SearchValue = "statement.java";
		T.UnitTest_SearchDIR(SearchValue);

		SearchValue = "drivssser.java";
		T.UnitTest_SearchDIR(SearchValue);

	}

}

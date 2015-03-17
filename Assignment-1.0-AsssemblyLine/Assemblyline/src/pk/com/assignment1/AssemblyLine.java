package pk.com.assignment1;

import java.util.Stack;

public class AssemblyLine {

	//optimal time to schedule a car in the assembly lanes
	public int optimaltime;
	//the last station lane for which the car has the optimal time
	public int exitlane;
	//record the time from start to the station(j) in an array started from lane 1
	int[] lane1fastest = new int[6];
	//record the time from start to the station(j) in an array started from lane 2
	int[] lane2fastest = new int[6];
	//record the optimal station at the stage in the array of lane 1
	int[] stationrecord1 = new int[6];
	//record the optimal station at the stage in the array of lane 2
	int [] stationrecord2 = new int[6];
	Stack<Integer> path = new Stack<Integer>();


	void FastestWay(Lane L1, Lane L2){
		//add the entry time of respective lane to the time taken to process the first station in that
		//lane.
		if(!(L1.getStationtime().length == L2.getStationtime().length && L1.getTransition().length == L2.getTransition().length)){
			System.out.println("Error : Invalid Lane Configuration");
			return;
		}
		lane1fastest[0]=L1.getEntrytime()+L1.getStime(0);
		lane2fastest[0]=L2.getEntrytime()+L2.getStime(0);

		//loop over the number of station in the lanes
		for(int j=1;j<L1.getStationtime().length;j++)
		{
			//staring from lane 1
			//calculating the optimal time through station(j)
			//add the value stored of the previous station optimal time to the next station in the same lane
			// OR
			//add the value stored of the previous station optimal time to the next station of the different
			//lane and also adding the time to swap b/w the lanes
			//which ever is the lowest add that value to the optimal time to present station and record 
			//the Assembly line number.			
			if((lane1fastest[j-1]+L1.getStime(j)) <= (lane2fastest[j-1]+L2.getTtime(j-1)+L1.getStime(j)))
			{
				lane1fastest[j]=lane1fastest[j-1]+L1.getStime(j);
				stationrecord1[j]=1;
			}
			else
			{
				lane1fastest[j]=lane2fastest[j-1]+L2.getTtime(j-1)+L1.getStime(j); 
				stationrecord1[j]=2;
			}
			//staring from lane 2
			//calculating the optimal time through station(j)
			//add the value stored of the previous station optimal time to the next station in the same lane
			// OR
			//add the value stored of the previous station optimal time to the next station of the different
			//lane and also adding the time to swap b/w the lanes
			//which ever is the lowest add that value to the optimal time to present station and record 
			//the Assembly line number.
			if((lane2fastest[j-1]+L2.getStime(j))<=(lane1fastest[j-1]+L1.getTtime(j-1)+L2.getStime(j)))
			{
				lane2fastest[j]=lane2fastest[j-1]+L2.getStime(j); 
				stationrecord2[j]=2;
			}
			else
			{
				lane2fastest[j]=lane1fastest[j-1]+L1.getTtime(j-1)+L2.getStime(j);
				stationrecord2[j]=1;
			}
		}

		//determine the optimal time for completion by adding the exit time to the previous calculated time
		//and decide which 
		if(lane1fastest[5]+L1.getExit() < lane2fastest[5]+L2.getExit())
		{ 	

			optimaltime=lane1fastest[5]+L1.getExit();
			//determine which lane has the best optimal time after adding the exit time
			exitlane=1;
		}
		else
		{
			optimaltime=lane2fastest[5]+L2.getExit();
			//determine which lane has the best optimal time after adding the exit time
			exitlane=2;
		}
		System.out.println("Optimal Time is: "+optimaltime);
		System.out.println("Optimal line is: "+exitlane);
		
	}

	@SuppressWarnings("unchecked")
	void path(){

		Stack<Integer> st = new Stack<Integer>();
		int temp=exitlane;
		path.push(exitlane);
		//backtract the recordstation array to calculate the optimal path across the assembly line.
		for(int j=5;j>=1;j--)
		{
			//if station is from lane 1 than record that previous optimal solution from in the stack.
			if (temp==1)
			{	
				path.push(stationrecord1[j]);
				temp=stationrecord1[j]; 
			}
			else
			{
				//System.out.println("Else");
				path.push(stationrecord2[j]);
				temp=stationrecord2[j];
			}
		}
		st = (Stack<Integer>) path.clone();
		System.out.println("Path to be followed for optimize solution :");
		System.out.print("Start -> ");
		for(int i =0;i<st.capacity();i++){
			System.out.print("Line "+st.pop()+ " -> ");
		}
		System.out.print("Exit");

//		System.out.print("\nf1 : ");
//		for(int i=0;i<6;i++)
//			System.out.print(lane1fastest[i]+ " ");
//		System.out.println();
//		System.out.print("S : ");
//		for(int i=0;i<6;i++)
//			System.out.print(stationrecord1[i]+ " ");
//		System.out.print("\nf2 : ");
//		for(int i=0;i<6;i++)
//			System.out.print(lane2fastest[i]+ " ");
//		System.out.println();
//		System.out.print("S : ");
//		for(int i=0;i<6;i++)
//			System.out.print(stationrecord2[i]+ " ");

	}

	/*
	 	Assembly Line is the problem where we need to find the optimal time for a car to cross 
	  	all the respective station in the lowest time.
		In this case we have two parallel lanes having multiple station with different performance
		complete the work at the given station.
	 */

	public Stack<Integer> getPath() {
		return path;
	}

	public void setPath(Stack<Integer> path) {
		this.path = path;
	}

	public int getOptimaltime() {
		return optimaltime;
	}

	public void setOptimaltime(int optimaltime) {
		this.optimaltime = optimaltime;
	}

	public int getExitlane() {
		return exitlane;
	}

	public void setExitlane(int exitlane) {
		this.exitlane = exitlane;
	}

	public int[] getLane1fastest() {
		return lane1fastest;
	}

	public void setLane1fastest(int[] lane1fastest) {
		this.lane1fastest = lane1fastest;
	}

	public int[] getLane2fastest() {
		return lane2fastest;
	}

	public void setLane2fastest(int[] lane2fastest) {
		this.lane2fastest = lane2fastest;
	}

	public int[] getStationrecord1() {
		return stationrecord1;
	}

	public void setStationrecord1(int[] stationrecord1) {
		this.stationrecord1 = stationrecord1;
	}

	public int[] getStationrecord2() {
		return stationrecord2;
	}

	public void setStationrecord2(int[] stationrecord2) {
		this.stationrecord2 = stationrecord2;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}
}



package pk.com.assignment1;

public class Lane {
	//Array of integer storing processing time for each station
	private int[] stationtime;
	//Array of integer storing swap time from the current lane to other lane. 
	private int[] transitiontime;
	//time to enter the lane
	private int entrytime;
	//time to exit the lane
	private int exittime;
	
	Lane(int []s, int []t, int e,int x){
		stationtime = new int[s.length];
		transitiontime = new int[t.length];
		entrytime = e;
		exittime = x;
		stationtime = s.clone();
		transitiontime = t.clone();
	}
	
	public int[] getStationtime() {
		return stationtime;
	}
	public int getStime(int index){
		return stationtime[index];
	}

	public void setStationtime(int[] stationtime) {
		this.stationtime = stationtime;
	}

	public int[] getTransition() {
		return transitiontime;
	}
	public int getTtime(int index){
		return transitiontime[index];
	}

	public void setTransition(int[] transition) {
		this.transitiontime = transition;
	}

	public int getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(int entrytime) {
		this.entrytime = entrytime;
	}

	public int getExit() {
		return exittime;
	}

	public void setExit(int exit) {
		this.exittime = exit;
	}
	
	public void printLane(int lanenum){
		System.out.println("\n\nAssembly lane  "+lanenum);
		System.out.println("Total number of station : " + stationtime.length);
		System.out.println("Time to enter the lane : "+ getEntrytime());
		System.out.println("Time to enter the lane : "+ getExit());
		System.out.print("per Station process Time :");
		for(int i=0;i<stationtime.length;i++)
			System.out.print(" "+getStime(i));
		System.out.print("\nTransition time : ");
		for(int i=0;i<transitiontime.length;i++)
			System.out.print(" "+getTtime(i));
		System.out.println();
	}



}

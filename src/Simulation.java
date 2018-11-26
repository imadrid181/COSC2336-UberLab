//Created By Ivan Madrid and Michael Fernandez 
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
public class Simulation {
	public static void main(String[] args) throws IOException, Exception  {
		//Scanner to take input for time of simulation
		Scanner input = new Scanner(System.in);
		System.out.println("How Long do you want to run the simulation for: ");
		int stopTime = input.nextInt();
		input.close();
		
		/*
		 * Variables used in the Simulator. Will be measuring miles drive, rides processed
		 * wait time, down time, earnings, missed earnings and rides not processed
		 */
		boolean simulator  = true;
		int currentTime = 0;
		int miles = 0;
		int totalMilesDriven = 0;
		int downTime = 0;
		int totalWaitTime = 0;
		int averageWaitTime = 0;
		int ridersNotProcessed = 0;
		double earning = 0.0;
		double missedEarning = 0.0;
		boolean transit = false;
		UberUser current= null;
		DecimalFormat dollars = new DecimalFormat("#.##");
		
		//In case the Simulation runs out of drives from UberLog.txt
		boolean ranOut = false;
		int ranOutTime = 0;
		
		/*
		 * Queues to hold UberUsers. The Priority Queue is all of the rides from UberLog.txt
		 * The Queue is to hold the rides that have been processed.
		 */
		PriorityQueue<UberUser> all = new PriorityQueue<UberUser>();
		Queue<UberUser> ridersDriven = new LinkedList<UberUser>();
		
		//Writer to write Statistics.txt
	    File file = new File("Statistics.txt");
	    //Create a file if nothing is there
	    if (!file.exists()) {
	      file.createNewFile();
	    } 
	    FileWriter fw = new FileWriter(file.getAbsoluteFile());
	    BufferedWriter bw = new BufferedWriter(fw);
	    
		//Reads UberLog.txt and adds all rides into the Priority queue
		File log = new File("UberLog.txt");
		BufferedReader reader = new BufferedReader(new FileReader(log));
		String line;
		while((line = reader.readLine()) != null) {
			all.add(new UberUser(line));
		}
		reader.close();

		while(simulator) {
			//Happens when there are no more rides
			if(current == null && all.isEmpty()) {
				System.out.println("No more drives to be processed");
				ranOut = true;
				ranOutTime = currentTime;
				break;
			}
			
			/*
			 * Sets the current equal to the first UberUser in the Priority Queue.
			 * If its pickUp time is greater than or equal to the current time
			 */
			if(current == null && !all.isEmpty()) {
				current = all.poll();
			}
						
			//Sets the UberUser to be 
			if(current.getRideTime() <= currentTime && transit == false) {
				System.out.println(current);
				transit = true;
				miles = current.getMiles();
				totalWaitTime += (currentTime - current.getRideTime());
			}
			
			//Drops off UberUser when there is no more miles left to be driven
			if(miles == 0 && transit == true) {
				System.out.println("Droping off "+current.getName());
				ridersDriven.add(current);
				earning += current.getEarnings();
				current = null;		
				transit = false;
			}
			
			//Reduces the miles left by 1 mile per 1 minute
			if(transit == true) {
				totalMilesDriven++;
				miles--;
			}
			
			//Keeps track of down time when their is no UberUser being driven
			if(transit  == false) {
				downTime++;
			}			
			
			//Stops Simulation when the time is equal to the stop time
			if(currentTime == stopTime) 
				simulator = false;
			
			//Prints out time and increase current Time
			Thread.sleep(100);
			System.out.println(currentTime);
			currentTime++;
		}
		
		averageWaitTime = totalWaitTime / ridersDriven.size();
		ridersNotProcessed = all.size();
		while(!all.isEmpty()) {
			current = all.poll();
			missedEarning += current.getEarnings();
		}
		//Writes all Statistics to Statistics.txt
	    bw.write("Statistics for Simulation of UberLog.txt ");
	    bw.newLine();	    
	    //Test to see if Simulation ran out of rides.
	    if(ranOut == false)
	    	bw.write("Simulation was ran for "+stopTime+" Minutes. ");
	    else
	    	bw.write("Simulation ran out of drives at "+ranOutTime+" Minutes.");
	    bw.newLine();
	    bw.write("The Driver processed "+ridersDriven.size()
	    		 +" request and earned a total of "+dollars.format(earning)+"$ ");
	    bw.newLine();
	    bw.write("The Driver drove "+totalMilesDriven+" miles.");
	    bw.newLine();
	    bw.write(ridersNotProcessed+" drives were not processed. ");
	    bw.newLine();
	    bw.write(dollars.format(missedEarning)+
	    		 "$ were missed from drives that were not processed. ");
	    bw.newLine();
	    bw.write("The Average Wait Time for requests was "+averageWaitTime+" minutes. ");
	    bw.newLine();
	    bw.write("The Driver was without a rider for a total of "+downTime+" minutes. ");
	    bw.close();
	}

}

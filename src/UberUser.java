
public class UberUser implements Comparable<Object> {
	//Fields for the UberUser Object
	private String name;
	private String pickUp;
	private String destination;
	private int rideTime;
	private int miles;
	private double earnings; 
	
	/**
	 * Default Constructor for the UberUser object. All fields are set to empty.
	 */
	public UberUser() {
		this.name = null;
		this.pickUp = null;
		this.destination = null;
		this.rideTime = 0;
		this.miles = 0;
		this.earnings = 0.0;		
	}
	
	/** 
	 * Constructor for the UberUser Object that takes a String s that contains
	 * all the information needed to create an UberUser object. The String will
	 * be tokenized and then each part will be used in creating the Object.
	 * @param s A String that contains all the info needed for the object.	 
	 */
	public UberUser(String s) {
		String[] tokens = s.split("\\,");
		this.name = tokens[0];
		this.pickUp = tokens[1];
		this.destination = tokens[2];
		this.rideTime = Integer.parseInt(tokens[3].trim());
		this.miles = Integer.parseInt(tokens[4].trim());
		this.earnings = Double.parseDouble(tokens[5].trim());
	}
	
	/**
	 * Gets the Name of the Uber User
	 * @return The Name of the Uber User
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the Pick Up Location of the Uber User
	 * @return The Pick Up Location of the Uber User
	 */
	public String getPickUp() {
		return this.pickUp;
	}
	
	/**
	 * Gets the Destination of the Uber User
	 * @return The Destination of the Uber User
	 */
	public String getDestination() {
		return this.destination;
	}
	
	/**
	 * Gets the Ride Time of the Uber User
	 * @return The Ride Time of the Uber User
	 */
	public int getRideTime() {
		return this.rideTime;
	}
	
	/**
	 * Gets the Miles of the Uber User
	 * @return The Miles of the Uber User
	 */
	public int getMiles() {
		return this.miles;
	}
	
	/**
	 * Gets the Earnings of the Uber User
	 * @return The Earnings of the Uber User
	 */
	public double getEarnings() {
		return this.earnings;
	}
	
	
	/**
	 * Sets the Name of the Uber User
	 * @param n New Name of Uber User
	 */
	public void setName(String n) {
		this.name = n;
	}
	/**
	 * Sets the Pick Up Location of the Uber User
	 * @param p New Pick Up Location of the Uber User
	 */
	public void setPickUp(String p) {
		this.pickUp = p;
	}
	
	/**
	 * Sets the Destination of the Uber User
	 * @param d New Destination of the Uber User
	 */
	public void setDestination(String d) {
		this.destination = d;
	}
	
	/**
	 * Sets the Ride Time of the Uber User
	 * @param r New Ride Time of the Uber User
	 */
	public void setRideTime(int r) {
		this.rideTime = r;
	}
	
	/**
	 * Sets the Miles of the Uber User
	 * @param m New Miles of the Uber User
	 */
	public void setMiles(int m) {
		this.miles = m;
	}
	
	/**
	 * Sets the Earnings of the Uber User
	 * @param e New Earnings of the Uber User
	 */
	public void setEarnings(double e) {
		this.earnings = e;
	}
	
	/**
	 * Converts the Object and all its' information into a String
	 * @return The Objects Info in a String 
	 */
	public String toString() {
		return "Picking Up: "+name+" from: "+pickUp+" to: "+destination+". Total Time: "+rideTime+". Total Miles: "+miles+". You will earn: $"+earnings;	
	}
	
	/**
	 * Checks if all fields of the Uber User Object are equal to that of an other Uber
	 * objects fields.
	 * @param u An Uber User Object that is checked if it's equal to the object
	 * @return True if all fields are the same. False if all fields are not the same.
	 * 
	 */
	public boolean equals(UberUser u) {
		if(this.name.equals(u.getName())) {
			if(this.pickUp.equals(u.getPickUp())) {
				if(this.destination.equals(u.getDestination())) {
					if(this.rideTime == u.getRideTime()) {
						if(this.miles == u.getMiles()) {
							if(this.earnings == u.getEarnings()) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Compares two Uber User Objects
	 * @param u Another Object that will be used to compare with the original Uber User
	 * @return Negative Number if the original Uber User is less than u, 0 if both Uber Users are the same, Positive Number if the original Uber User is greater than u
	 */
	public int compareTo(Object o) {
		UberUser u =  (UberUser)o;
		
		if(this.rideTime != u.getRideTime()) 
			return rideTime - u.getRideTime();
		
		else if(this.earnings != u.getEarnings())
			return (int)(this.earnings - u.getEarnings());
		
		else if(this.miles != u.getMiles()) 
			return miles - u.getMiles();
		
		else if(!this.name.equals(u.getName())) 
			return this.name.compareTo(u.getName());
		
		else if(!this.pickUp.equals(u.getPickUp()))
			return this.pickUp.compareTo(u.getPickUp());
		
		else if(!this.destination.equals(u.getDestination()))
			return this.destination.compareTo(u.getDestination());
				
		return 0;			
	}
}
	


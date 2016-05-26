package nagarjuna;

import java.util.ArrayList;

public class EntANDNumner {

	private ArrayList<String> entitlementName =  new ArrayList<String>();
	public ArrayList<String> getEntitlementName() {
		return entitlementName;
	}
	public void setEntitlementName(ArrayList<String> entitlementName) {
		this.entitlementName = entitlementName;
	}
	
	
	public ArrayList<Integer> getNumberOfUsers() {
		return numberOfUsers;
	}
	public void setNumberOfUsers(ArrayList<Integer> numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}
	private ArrayList<Integer> numberOfUsers = new ArrayList<Integer>();
}

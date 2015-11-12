package schwimmer.ups;

import java.util.Set;

/**
 * REQUIREMENT: You may not use an ArrayList (or any "List") in this class.
 */
public class UPSDatabase {

	/**
	 * Add a package to the specified Location
	 */
	public void addPackageToLocation( Location location, Package pkg ) {
		
	}
	
	/**
	 * Update a Package's Location.
	 */
	public void updatePackageLocation( Package pkg, Location location ) {
		
	}
	
	/**
	 * @return a Set of Packages at the specified Location or an empty Set if 
	 * the Location doesn't exist or there are no Packages at that Location.
	 */
	public Set<Package> getPackages( Location location ) {
		return null;
	}
	
	/**
	 * @return the Location of a Package or null if the Package doesn't exist.
	 */
	public Location getLocation(Package pkg) {
		return null;
	}
	
	
}

package schwimmer.ups;

import java.util.Set;

public class PackageTracker {

	/**
	 * Add a package to the specified Location
	 */
	public void addPackageToLocation( Location location, TrackingNumber pkg ) {
		
	}
	
	/**
	 * Update a Package's Location.
	 */
	public void updatePackageLocation( TrackingNumber pkg, Location location ) {
		
	}
	
	/**
	 * @return a Set of Packages at the specified Location or an empty Set if 
	 * the Location doesn't exist or there are no Packages at that Location.
	 */
	public Set<TrackingNumber> getPackages( Location location ) {
		return null;
	}
	
	/**
	 * @return the Location of a Package or null if the Package doesn't exist.
	 */
	public Location getLocation(TrackingNumber pkg) {
		return null;
	}
	
	
}

package schwimmer.ups;

public class Location {

	private long latitude;
	private long longitude;
	
	public Location(long latitude, long longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public long getLatitude() {
		return latitude;
	}

	public long getLongitude() {
		return longitude;
	}

	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}
	
}

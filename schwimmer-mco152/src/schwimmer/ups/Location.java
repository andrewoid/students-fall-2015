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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (latitude ^ (latitude >>> 32));
		result = prime * result + (int) (longitude ^ (longitude >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (latitude != other.latitude)
			return false;
		if (longitude != other.longitude)
			return false;
		return true;
	}
	
	
}

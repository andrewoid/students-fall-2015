package schwimmer.ups;

public class TrackingNumber {

	private final String value;

	public TrackingNumber(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "TrackingNumber [value=" + value + "]";
	}
	
}

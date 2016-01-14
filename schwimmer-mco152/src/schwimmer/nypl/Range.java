package schwimmer.nypl;

public class Range {

	private int current;
	private int max;
	
	public Range(int max) {
		this.max = max;
	}
	public Range(int current, int max) {
		this.current = current;
		this.max = max;
	}
	
	public boolean atMin() {
		return current == 0;
	}
	
	public boolean atMax() {
		return current == max - 1;
	}
	
	public int increase() {
		if ( !atMax() ) {
			current++;
		}
		return current;
	}
	
	public int decrease() {
		if ( !atMin() ) {
			current--;
		}
		return current;
	}

	public int getCurrent() {
		return current;
	}
	
	public String toString() {
		return (current+1)+"/"+(max);
	}
	
}

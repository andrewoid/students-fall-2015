package schwimmer.nypl;

public class ItemResponse {

	class NyplApi {
		NyplResponse response;
	}
	
	class NyplResponse {
		int numResults;
		Capture capture[];
	}
	
	class Capture {
		ImageLinks imageLinks;
	}
	
	class ImageLinks {
		String imageLink[];
	}
	
	NyplApi nyplAPI;
	
	public int getNumResults() {
		return nyplAPI.response.numResults;
	}
	
	public Capture[] getCaptures() {
		return nyplAPI.response.capture;
	}
	
	public String getFirstImageLink(int index) {
		return getImage(index, 0);
	}
	
	public String getImage( int index, int zoomLevel ) {
		return nyplAPI.response.capture[index].imageLinks.imageLink[zoomLevel];
	}
	
}

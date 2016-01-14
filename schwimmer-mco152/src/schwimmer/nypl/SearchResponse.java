package schwimmer.nypl;

public class SearchResponse {

	class NyplApi {
		NyplResponse response;
	}
	
	class NyplResponse {
		int numResults;
		NyplItem result[];
	}
	
	class NyplItem {
		String title;
		String apiItemURL;
		
		public String toString() {
			return title.length() > 30 ? title.substring(0,30)+"...": title;
		}
	}
	
	NyplApi nyplAPI;
	
	public int getNumResults() {
		return nyplAPI.response.numResults;
	}
	
	public NyplItem[] getItems() {
		return nyplAPI.response.result;
	}
}

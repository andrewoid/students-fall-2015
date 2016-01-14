package schwimmer.nypl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class ContentService {
	
	private String baseUrl;
	private Gson gson;

	public ContentService( String baseUrl ) {
		this.baseUrl = baseUrl;
		gson = new Gson();
	}
	
	private <T> T get( String url, Class<T> cls ) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestProperty("Authorization", "Token token=\"pjp8q9r0bnbky3v2\"");
		InputStream in = connection.getInputStream();
		return gson.fromJson(new InputStreamReader(in), cls);
	}
	
	public SearchResponse search( String query ) throws IOException {
		String url = baseUrl + "/api/v1/items/search?publicDomainOnly=true&q="+query;
		return get(url, SearchResponse.class);
	}
	
    public ItemResponse getItem( String url) throws IOException {
		return get(url, ItemResponse.class);
    }
	
}

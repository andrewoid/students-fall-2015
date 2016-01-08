package schwimmer.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JTextArea;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class ChatP2PHandler extends AbstractHandler {

	private JTextArea area;
	private Set<String> addresses;
	
	public ChatP2PHandler(JTextArea area) {
		this.area = area;
		addresses = new HashSet<String>();
	}

	@Override
	public void handle(String target, 
			Request baseRequest, 
			HttpServletRequest request, 
			HttpServletResponse response)
			throws IOException, ServletException {
		
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		
		InputStream in = request.getInputStream();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(in));
		String line = reader.readLine();
		area.append(line);
		area.append("\n");
		
		send(line, request.getRemoteAddr());
	}

	public void send( String line, String remoteAddress ) {
		
		addresses.add(remoteAddress);
		
		for ( String address : addresses ) {
			try {
				URL url = new URL("http://"+address+":8080");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				OutputStream out = connection.getOutputStream();
				PrintWriter writer = new PrintWriter(out);
				writer.println(line);
				writer.flush();
				
				connection.getInputStream();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}

}

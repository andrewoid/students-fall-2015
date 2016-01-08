package schwimmer.chat;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.eclipse.jetty.server.Server;

public class ChatFrame extends JFrame implements ActionListener {

	public ChatFrame() {
		
		setTitle("P2P Chat");
		setSize(400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container container = getContentPane();
		JTextArea area = new JTextArea();
		JButton button = new JButton("SEND");
		
		
		
		
		button.addActionListener(this);
		
		container.setLayout(new BorderLayout());
		container.add(button, BorderLayout.SOUTH);
		container.add(area, BorderLayout.NORTH);
		
		Server server = new Server(8080);
		server.setHandler(new ChatP2PHandler(area));
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			URL url = new URL("http://192.168.117.169:8080");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			OutputStream out = connection.getOutputStream();
			PrintWriter writer = new PrintWriter(out);
			writer.println("Hello World");
			writer.flush();
			
			connection.getInputStream();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
}

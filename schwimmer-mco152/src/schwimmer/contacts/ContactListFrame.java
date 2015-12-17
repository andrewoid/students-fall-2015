package schwimmer.contacts;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import com.google.gson.Gson;

public class ContactListFrame extends JFrame {

	private JList<Contact> list;
	protected ContactList contactList;
	
	public ContactListFrame() {
		setSize(200,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Contact List");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		list = new JList<Contact>();
		panel.add(list, BorderLayout.CENTER);

		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		        	int index = list.getSelectedIndex();
		        	Contact contact = contactList.get(index);
		        	new ContactFrame(contact);
		        } 
		    }
		});
		
		
		setContentPane(panel);
		
		setVisible(true);
		
		Thread thread = new Thread() {
			public void run() {
				Gson gson = new Gson();
				URL url;
				try {
					url = new URL("http://jsonplaceholder.typicode.com/users");
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					contactList = gson.fromJson(new InputStreamReader(connection.getInputStream()), ContactList.class);
					list.setListData(contactList.toArray(new Contact[0]));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
		
	}
	
	public static void main( String args[] ) {
		new ContactListFrame();
	}
	
	
}

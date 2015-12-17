package schwimmer.contacts;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import schwimmer.contacts.Contact.Address;

public class ContactFrame extends JFrame {

	public ContactFrame(Contact contact) {
		setSize(200,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Contact");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

		panel.add(new JLabel(contact.getName()));
		panel.add(new JLabel(contact.getEmail()));
		Address address = contact.getAddress();
		panel.add(new JLabel(address.getSuite()));
		panel.add(new JLabel(address.getStreet()));
		panel.add(new JLabel(address.getCity()));
		panel.add(new JLabel(address.getZipcode()));
		
		setContentPane(panel);
		
		setVisible(true);
	}
	
}

package schwimmer.nypl;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LibrarySearchFrame extends JFrame implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ContentService service;
	private JList<SearchResponse.NyplItem> list;
	private JLabel label;
	private JTextField searchBox;
	private JButton searchButton;
	private JPanel centerPanel;
	private JPanel capturesPanel;
	private JButton next;
	private JButton previous;
	private JLabel currentCapture;
	protected ItemResponse currentItem;
	private Range zoom;
	private Range index;
	
	private ActionListener doSearch = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new Thread() {
				public void run() {
					SearchResponse searchResponse;
					try {
						searchResponse = service.search(searchBox.getText());
						if ( searchResponse.getNumResults() > 0 ) {
							list.setListData(searchResponse.getItems());
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		
	};
	
	public LibrarySearchFrame() {
		setSize(800,600);
		setTitle("NYPL Search");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		service = new ContentService("http://api.repo.nypl.org");
		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		list = new JList<SearchResponse.NyplItem>();
		container.add(list, BorderLayout.WEST);
		list.addListSelectionListener(this);
		
		zoom = new Range(3, 8);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		label = new JLabel();
		capturesPanel = new JPanel();
		next = new JButton("Next");
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextCapture();
			}
		});
		next.setEnabled(false);
		previous = new JButton("Previous");
		previous.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				previousCapture();
			}
		});
		previous.setEnabled(false);
		currentCapture = new JLabel("");
		capturesPanel.add(previous);
		capturesPanel.add(currentCapture);
		capturesPanel.add(next);
		centerPanel.add(capturesPanel, BorderLayout.NORTH);
		centerPanel.add(new JScrollPane(label), BorderLayout.CENTER);
		
		container.add(centerPanel, BorderLayout.CENTER);
		
		searchBox = new JTextField();
		searchBox.addActionListener(doSearch);
		searchButton = new JButton("Search");
		searchButton.addActionListener(doSearch);
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.add(searchBox, BorderLayout.CENTER);
		northPanel.add(searchButton, BorderLayout.EAST);
		container.add(northPanel, BorderLayout.NORTH);	
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		SearchResponse.NyplItem item = list.getSelectedValue();
		new Thread() {
			public void run() {
				try {
					ItemResponse response = service.getItem(item.apiItemURL);
					showItem(response);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	public void showItem(ItemResponse response) {
		currentItem = response;
		index = new Range(currentItem.getNumResults());
		showImageAtIndex();
	}
	
	public void previousCapture() {
		index.decrease();
		showImageAtIndex();
	}
	
	public void nextCapture() {
		index.increase();
		showImageAtIndex();
	}
	
	private void showImageAtIndex() {
		
		previous.setEnabled(!index.atMin());
		next.setEnabled(!index.atMax());
		
		new Thread() {
			public void run() {
				URL url;
				try {
					currentCapture.setText(index.toString());
					url = new URL(currentItem.getImage(
							index.getCurrent(), zoom.getCurrent()));
					label.setIcon(new ImageIcon(url));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}

	public static void main(String[] args) {
		new LibrarySearchFrame().setVisible(true);
	}


}

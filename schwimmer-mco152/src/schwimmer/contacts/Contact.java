package schwimmer.contacts;

public class Contact {
	
	class Address {
		String street;
		String suite;
		String city;
		String zipcode;
		public String getStreet() {
			return street;
		}
		public String getSuite() {
			return suite;
		}
		public String getCity() {
			return city;
		}
		public String getZipcode() {
			return zipcode;
		}
	}
	
	private String name;
	private String email;
	private Address address;

	public Address getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}

	public String getEmail() {
		return email;
	}
}


public class Customer {
	private String name;
	private String surname;
	private int customerId;
	private int frequency = 0;
	public Customer(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return this.name;
	}
	public String getSurname() {
		return this.surname;
	}
	public int getCustomerId() {
		return this.customerId;
	}
	public String displayCustomer() {
		return customerId + "." + name + " " + surname;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
}

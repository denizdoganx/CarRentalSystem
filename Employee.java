
public class Employee {
	private String name;
	private String surname;
	private String gender;
	private Date dateOfBirth;
	private int officeId;
	private int employeeId;
	private int frequency = 0;
	private String profit;
	private int employeeIncome = 0;

	public Employee(String name,String surname,String gender,Date dateOfBirth, int officeId, int employeeId) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.officeId = officeId;
		this.employeeId = employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public Employee() {
		
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	public String getName() {
		return this.name;
	}
	public String getSurname() {
		return this.surname;
	}
	public String getGender() {
		return this.gender;
	}
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}
	public int getOfficeId() {
		return this.officeId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public String displayEmployee() {
		return employeeId + "." + name + " " + surname + " " + gender + " " + dateOfBirth.displayDate(); 
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public String displayMostRentEmp() {
		return "Employee" + employeeId + ";" + name + ";" + surname;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public int getEmployeeIncome() {
		return employeeIncome;
	}
	public void setEmployeeIncome(int employeeIncome) {
		this.employeeIncome = employeeIncome;
	}
	
}

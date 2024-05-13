
public class Contract {
	private int customerId;
	private int carId;
	private int contractId;
	private int employeeId;
	private Date startDate;
	private Date endDate;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String displayContract() {
		return contractId + ".Contract" + ";Employee" + employeeId + ";Customer" + customerId + ";Car" + carId + ";" + startDate.displayDate() + ";" + endDate.displayDate();
	}
	public String justContract() {
		return   "Contract" + ";Employee" + employeeId + ";Customer" + customerId + ";Car" + carId + ";" + startDate.displayDate() + ";" + endDate.displayDate();
	}
	
}
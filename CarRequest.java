
public class CarRequest {
	private String brand;
	private String model;
	private String classOfCar;
	private int officeId;
	private int customerId;
	private int carRequestId;
	private Date startDate;
	private Date endDate;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getClassOfCar() {
		return classOfCar;
	}
	public void setClassOfCar(String classOfCar) {
		this.classOfCar = classOfCar;
	}
	public int getOfficeId() {
		return officeId;
	}
	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getCarRequestId() {
		return carRequestId;
	}
	public void setCarRequestId(int carRequestId) {
		this.carRequestId = carRequestId;
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
	public String displayCarRequest() {
		return carRequestId + ".CarRequest" + ";" + officeId + ";" + customerId + ";" + brand + ";" + model + ";" + startDate.displayDate() + ";" + endDate.displayDate();
	}
}
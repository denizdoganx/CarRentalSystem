public class Car {
    private String brand;
    private String model;
    private String classOfCar;
    private int km;
    private int officeId;
    private int carId;
    private int frequency = 0;
    private int profit = 0;
    public Car(String brand, String model, String classOfCar, int km, int officeId, int carId) {
        this.brand = brand;
        this.model = model;
        this.classOfCar = classOfCar;
        this.km = km;
        this.officeId = officeId;
        this.carId = carId;
    }
    public Car() {

    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setClassOfCar(String classOfCar) {
        this.classOfCar = classOfCar;
    }
    public void setKm(int km) {
        this.km = km;
    }
    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }
    public void setCarId(int carId) {
        this.carId = carId;
    }
    public String getBrand() {
        return this.brand;
    }
    public String getModel() {
        return this.model;
    }
    public String getClassOfCar() {
        return this.classOfCar;
    }
    public int getKm() {
        return this.km;
    }
    public int getOfficeId() {
        return this.officeId;
    }
    public int getCarId() {

        return this.carId;
    }
    public String displayCar() {
        return carId + "." + brand + " " + model + " " + classOfCar + " " + km + "km"; 
    }
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
    public String displayMostRented() {
    	return "Car" + carId + ";" + brand + ";" + model; 
    }
	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}
    
}
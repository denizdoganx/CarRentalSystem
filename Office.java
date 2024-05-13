import java.util.Arrays;
import java.util.Random;
public class Office {
	private Employee[] employees;
	private Car[] cars;
	private Contract[] contracts;
	private Contract[] oldContracts;
	private String city;
	private String address;
	private String phone;
	private int officeId;
	private int countOfEmployee;
	private int countOfCar;
	private int countOfContract;
	private int countOfOldContract = 0;
	private static int employeeId = 1;
	private static int carId = 1;
	private Random rnd = new Random();
	public Office(int carOfCapasity, int employeeOfCapasity, String city, String address, String phone) {
		cars = new Car[carOfCapasity]; 
		employees = new Employee[employeeOfCapasity];
		this.city = city;
		this.address = address;
		this.phone = phone;
		countOfEmployee = 0;
		countOfCar = 0;
	}
	public Office(String phone, String address, String city) {
		this.city = city;
		this.address = address;
		this.phone = phone;
		countOfEmployee = 0;
		countOfCar = 0;
	}
	public Office() {
		countOfEmployee = 0;
		countOfCar = 0;
	}
	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getOfficeId() {
		return this.officeId;
	}
	public String getCity() {
		return this.city;
	}
	public String getAddress() {
		return this.address;
	}
	public String getPhone() {
		return this.phone;
	}
	public Employee[] getEmployees() {
		return employees;
	}
	public Car[] getCars() {
		return cars;
	}
	public void addEmployee(Employee employee) {
		employee.setEmployeeId(employeeId);
		if(countOfEmployee != 0) {
			Employee[] tempArray = new Employee[countOfEmployee];
			for(int i = 0;i < tempArray.length; i++) {
				tempArray[i] = employees[i];
			}
			employees = new Employee[countOfEmployee + 1];
			for(int i = 0;i < tempArray.length; i++) {
				employees[i] = tempArray[i];
			}
			employees[countOfEmployee] = employee;
		}
		else {
			employees = new Employee[countOfEmployee + 1];
			employees[countOfEmployee] = employee;
		}
		countOfEmployee++;
		employeeId++;
	}
	public void addCar(Car car) {
		if((car.getKm() >= 0) && (car.getKm() % 100 == 0)) {
			car.setCarId(carId);
			if(countOfCar != 0) {
				Car[] tempArray = new Car[countOfCar];
				for(int i = 0;i < tempArray.length; i++) {
					tempArray[i] = cars[i];
				}
				cars = new Car[countOfCar + 1];
				for(int i = 0;i < tempArray.length; i++) {
					cars[i] = tempArray[i];
				}
			}
			else {
				cars = new Car[countOfCar + 1];
			}
			cars[countOfCar] = car;
			carId++;
			countOfCar++;
		}
		else {
			System.out.println("CAR'S KM  MUST BE GREATER THAN 0 AND A MULTÝPLE OF 100 !!!");
		}
	}
	public void addContract(Contract contract) {
		if(countOfContract != 0) {
			Contract[] tempContract = new Contract[countOfContract];
			for(int i = 0;i < tempContract.length; i++) {
				tempContract[i] = contracts[i];
			}
			contracts = new Contract[countOfContract + 1];
			for(int i = 0;i < tempContract.length; i++) {
				contracts[i] = tempContract[i];
			}
		}
		else {
			contracts = new Contract[countOfContract + 1];
		}
		contracts[countOfContract] = contract;
		addOldContract(contract);
		countOfContract++;
	}
	public void deleteOldContract(int cntrctID) {
		if(countOfOldContract > 1) {
			for(int i = 0;i < countOfOldContract; i++) {
				if(oldContracts[i] != null) {
					if(oldContracts[i].getContractId() == cntrctID) {
						oldContracts[i] = null;
					}
				}
			}
		}
		else if(countOfOldContract == 1) {
			oldContracts = null;
		}
	}
	public void addOldContract(Contract contract) {
		if(countOfOldContract != 0) {
			Contract[] tempArray = new Contract[countOfOldContract];
			for(int i = 0;i < tempArray.length; i++) {
				tempArray[i] = oldContracts[i];
			}
			oldContracts = new Contract[countOfOldContract + 1];
			for(int i = 0;i < tempArray.length; i++) {
				oldContracts[i] = tempArray[i];
			}
		}
		else {
			oldContracts = new Contract[countOfOldContract + 1];
		}
		oldContracts[countOfOldContract] = contract;
		countOfOldContract++;
	}
	public  void reformOldContract() {
		if(oldContracts != null) {
			for(int i = 0;i <  countOfOldContract; i++) {
				if(Main.today.getDay() - oldContracts[i].getStartDate().getDay() < 10) {
					continue;
				}
				else {
					deleteOldContract(oldContracts[i].getContractId());
				}
			}
		}
		if(oldContracts != null) {
			int index = 0;
			for(int i = 0;i < countOfOldContract; i++) {
				if(oldContracts[i] != null) {
					oldContracts[i - index] = oldContracts[i];
				}
				else {
					index++;
				}
			}
			countOfOldContract = countOfOldContract - index;
		}
		if(countOfOldContract == 0) {
			oldContracts = null;
		}
	}
	public void deleteEmployee(int employeeId) {
		if(countOfEmployee > 1) {
			Employee[] tempArray = new Employee[countOfEmployee - 1];
			for(int i = 0;i < employees.length; i++) {
				if(employees[i].getEmployeeId() < employeeId) {
					tempArray[i] = employees[i];
				}
				else if(employeeId == (employees[i].getEmployeeId())) {
					continue;
				}
				else {
					tempArray[i - 1] = employees[i]; 
				}
			}
			employees = new Employee[countOfEmployee - 1];
			for(int i = 0;i < employees.length; i++) {
				employees[i] = tempArray[i];
			}
		}
		else if(countOfEmployee == 1) {
			employees = null;
		}
		countOfEmployee--;
	}
	public void deleteCar(int carId) {
		if(countOfCar > 1) {
			Car[] tempArray = new Car[countOfCar - 1];
			for(int i = 0;i < cars.length; i++) {
				if(cars[i].getCarId() < carId) {
					tempArray[i] = cars[i];
				}
				else if(cars[i].getCarId() == carId) {
					continue;
				}
				else {
					tempArray[i - 1] = cars[i];
				}
			}
			cars = new Car[countOfCar - 1];
			for(int i = 0;i < cars.length; i++) {
				cars[i] = tempArray[i];
			}
		}
		else if(countOfCar == 1) {
			cars = null;
		}
		countOfCar--;
	}
 	public void listCar() {
		if(countOfCar != 0) {
			System.out.println("#LIST OF CARS IN OFFICE " + officeId);
			for(int i = 0;i < countOfCar; i++) {
				System.out.println("\t" + cars[i].displayCar());
			}
		}
		else {
			System.out.println("NO REGÝSTERED CAR AT THE OFFICE FOUND");
		}
	}
	public void listEmployee() {
		if(countOfEmployee != 0) {
			System.out.println("#LIST OF EMPLOYEES IN OFFICE " + officeId);
			for(int i = 0;i < countOfEmployee; i++) {
				System.out.println("\t" + employees[i].displayEmployee());
			}
		}
		else {
			System.out.println("NO REGISTERED EMPLOYEE AT THE OFFICE FOUND");
		}
	}
	public String displayOffice() {
		return officeId + "." + city + " " + address + " " + phone;
	}
	public boolean employeeIdControl(int empId) {
		if(employees != null) {
			for(int i = 0;i < employees.length; i++) {
				if(employees[i].getEmployeeId() == empId) {
					return true;
				}
			}
			return false;
		}
		else {
			return false;
		}
	}
	public boolean carIdControl(int crId) {
		if(cars != null) {
			for(int i = 0;i < cars.length; i++) {
				if(cars[i].getCarId() == crId) {
					return true;
				}
			}
			return false;
		}
		else {
			return false;
		}
	}
	public boolean countOfEmployeeControl() {
		if(countOfEmployee < 3) {
			return true;
		}
		return false;
	}
	public boolean isThereAnEmployee() {
		if(countOfEmployee == 0) {
			return false;
		}
		return true;
	}
	public boolean isThereACar() {
		if(countOfCar == 0) {
			return false;
		}
		return true;
	}
	public boolean carClassControl(Car car) {
		String classOfCar = car.getClassOfCar().toLowerCase();
		car.setClassOfCar(classOfCar);
		if(classOfCar.equals("economy") || classOfCar.equals("sports") || classOfCar.equals("luxury")) {
			return true;
		}
		return false;
	}
	public int employeeContractControl() {
		if(employees != null) {
			if(contracts != null) {
				for(int i = 0;i < employees.length; i++) {
					boolean flagContractControl = true;
					for(int j = 0;j < contracts.length; j++) {
						if(employees[i].getEmployeeId() == contracts[j].getEmployeeId()) {
							flagContractControl = false;
							break;
						}
					}
					if(flagContractControl) {
						return employees[i].getEmployeeId();
					}
				}	
				System.out.println("\tERROR !!! NO EMPLOYEE FOR THE CONTRACT !!!");
				return 0;
			}
			else {
				int randomEmployee = rnd.nextInt(employees.length);
				return employees[randomEmployee].getEmployeeId();
			}
		}
		else {
			System.out.println("\tERROR !!! YOU HAVE NOT YET ADDED A EMPLOYEE TO THE OFFICE !!!");
			return 0;
		}
	}
	public void systemRecommedation() {
		int maxProfit = 0;
		int index = 0;
		for(int i = 0;i < countOfCar; i++) {
			if(cars[i].getProfit() > maxProfit) {
				maxProfit = cars[i].getProfit();
				index = i;
			}
		}
		if(countOfEmployee == 0){
			System.out.println("Delete Office (Number Of Employee = 0)");
		}
		else if(countOfCar == 0) {
			System.out.println("Delete Office (Number Of Car = 0)");
		}
		else if(countOfCar != 0 && countOfEmployee == 0) {
			System.out.println("Hire a new employee...");
		}
		else if(countOfEmployee > countOfCar) {
			System.out.println("Remove a worker");
		}
		else if(countOfContract == 3){
			System.out.println("Open a new office...");
		}
		else {
			System.out.println("Buy new car " + "(" + cars[index].getClassOfCar() + " class)");
		}
	}
	public int isThereAAvailableCar(String brand, String model, String clas) {
		if(cars != null) {
			if(contracts != null) {
				int numberOfSuitableCar = 0;
				for(int i = 0;i < cars.length; i++) {
					boolean isItUnderContract = false;
					if((cars[i].getBrand().equals(brand) || brand.equals("*")) && (cars[i].getModel().equals(model) || model.equals("*")) && (cars[i].getClassOfCar().equals(clas) || clas.equals("*"))) {
						for(int j = 0;j < contracts.length; j++) {
							if(cars[i].getCarId() == contracts[j].getCarId()) {
								isItUnderContract = true;
							}
						}
						if(!isItUnderContract) {
							return cars[i].getCarId();
						}
						numberOfSuitableCar++;
					}
				}
				if(numberOfSuitableCar == 0) {
					System.out.println("\tERROR !!! NO CAR !!!");
					return 0;
				}
				System.out.println("\tERROR !!! CAR NOT AVAILABLE !!!");
				return 0;
			}
			else {
				for(int i = 0;i < cars.length; i++) {
					if((cars[i].getBrand().equals(brand) || brand.equals("*")) && (cars[i].getModel().equals(model) || model.equals("*")) && (cars[i].getClassOfCar().equals(clas) || clas.equals("*"))) {
						return cars[i].getCarId();
					}
				}
				System.out.println("\tERROR !!! NO SUITABLE CAR WAS FOUND");
				return 0;
			}
		}
		else {
			System.out.println("\tERROR !!! NOT FOUND A CAR IN THE OFFİCE !!!");
			return 0;
		}
	}
	public Contract[] getContracts() {
		return contracts;
	}
	public void setContracts(Contract[] contracts) {
		this.contracts = contracts;
	}
	public void deleteContract(int contractId) {
		if(countOfContract > 1) {
			Contract[] tempContract = new Contract[countOfContract - 1];
			for(int i = 0;i < contracts.length; i++) {
				if(contracts[i].getContractId() < contractId) {
					tempContract[i] = contracts[i];
				}
				else if(contracts[i].getContractId() == contractId) {
					continue;
				}
				else {
					tempContract[i - 1] = contracts[i];
				}
			}
			contracts = new Contract[countOfContract - 1];
			for(int i = 0;i < tempContract.length; i++) {
				contracts[i] = tempContract[i];
			}
		}
		else if(countOfContract == 1) {
			contracts = null;
		}
		countOfContract--;
	}
	public void staticsOfLast10Days() {
		reformOldContract();
		if(oldContracts != null) {
			double averageRent = 0;
			double empAverageIncome = 0;
			for(int i = 0;i < countOfOldContract; i++) {
				averageRent += (oldContracts[i].getEndDate().getDay() - oldContracts[i].getStartDate().getDay() + 1); 
				for(int j = 0;j < countOfCar; j++) {
					if(oldContracts[i].getCarId() == cars[j].getCarId()) {
						cars[j].setFrequency(cars[j].getFrequency() + 1);
						break;
					}
				}
				for(int j = 0;j < countOfEmployee; j++) {
					if(oldContracts[i].getEmployeeId() == employees[j].getEmployeeId()) {
						employees[j].setFrequency(employees[j].getFrequency() + 1);
						break;
					}
				}
			}
			int[] frequencyOfCars = new int[countOfCar];
			int[] frequencyOfEmployees = new int[countOfEmployee];
			for(int i = 0;i < countOfCar; i++) {
				frequencyOfCars[i] = cars[i].getFrequency();
			}
			for(int i = 0;i < countOfEmployee; i++) {
				frequencyOfEmployees[i] = employees[i].getFrequency();
			}
			Arrays.sort(frequencyOfCars);
			Arrays.sort(frequencyOfEmployees);
			System.out.print("The most rented car:  ");
			for(int i = 0;i < countOfCar; i++) {
				if(frequencyOfCars[countOfCar - 1] == cars[i].getFrequency()) {
					System.out.print(cars[i].displayMostRented() + "   ");
				}
			}
			System.out.print("\nThe most rented car class:  ");
			for(int i = 0;i < countOfCar; i++) {
				if(frequencyOfCars[countOfCar - 1] == cars[i].getFrequency()) {
					System.out.print(cars[i].getClassOfCar() + "   ");
				}
			}
			System.out.println();
			int profitOfCar = 0;
			for(int i = 0;i < countOfCar; i++) {
				if(profitOfCar < cars[i].getProfit()) {
					profitOfCar = cars[i].getProfit();
				}
			}
			int carID = 0;
			for(int i = 0;i < countOfCar; i++) {
				if(cars[i].getProfit() == profitOfCar) {
					System.out.println("The car with the highest profit: " + cars[i].displayMostRented());
					System.out.println("The car class with the highest profit: " + cars[i].getClassOfCar());
					carID = cars[i].getCarId();
					break;
				}
			}
			System.out.println("The average number of days the cars are rented: " + (averageRent / countOfOldContract));
			System.out.print("The employee who rented most:  ");
			for(int i = 0;i < countOfEmployee; i++) {
				if(frequencyOfEmployees[countOfEmployee - 1] == employees[i].getFrequency()) {
					System.out.print(employees[i].displayMostRentEmp() + "   ");
				}
			}
			System.out.println();
			for(int i = 0;i < countOfOldContract; i++) {
				if(oldContracts[i].getCarId() == carID) {
					for(int j = 0;j < countOfEmployee; j++) {
						if(employees[j].getEmployeeId() == oldContracts[i].getEmployeeId()) {
							System.out.println("The most profitable employee:  " + employees[j].displayMostRentEmp() + " (" + employees[j].getProfit() + "cp)");
							break;
						}
					}
				}
				else {
					continue;
				}
				break;
			}
			for(int i = 0;i < countOfEmployee; i++) {
				empAverageIncome += employees[i].getEmployeeIncome();
			}
			System.out.println("Average income levels of the employees for the office:  " + (empAverageIncome / countOfEmployee) + "cp");
			for(int i = 0;i < countOfCar; i++) {
				cars[i].setFrequency(0);
			}
			for(int i = 0;i < countOfEmployee; i++) {
				employees[i].setFrequency(0);
			}
		}
		else {
			System.out.println("\tNo rentals have been made in the last 10 days...");
		}
		
	}
	public void statusRaport(Date date) {
		int incomes = 0;
		int expenses = 100;
		int countEconomy = 0;
		int countSports = 0;
		int countLux = 0;
		expenses += countOfEmployee * 30;
		String[] maintenance = new String[3];
		int index = 0;
		for(int i = 0;i < countOfContract; i++) {
			int empINDEX = 0;
			for(int j = 0;j < countOfEmployee; j++) {
				if(employees[j].getEmployeeId() == contracts[i].getEmployeeId()) {
					empINDEX = j;
					break;
				}
			}
			for(int j = 0;j < countOfCar; j++) {
				if(contracts[i].getCarId() == cars[j].getCarId()) {
					int care = 0;
					int km = (rnd.nextInt(3) + 1) * 100;
					if(cars[j].getClassOfCar().equals("economy")) {
						incomes += 100;
						expenses += 25 + ((km / 100) * 5);
						care = 20 + (km / 100) * 5;
						cars[j].setProfit(cars[j].getProfit() + (100 - care));
						int profitX = 100 - care;
						employees[empINDEX].setProfit("100 - " + care + " = " + profitX);
						employees[empINDEX].setEmployeeIncome(profitX);
						maintenance[index] = "Car" + cars[j].getCarId() + " maintenance : 20 + " + ((km / 100 ) * 5) + " = " + care + " (" + km + "km)"; 
						countEconomy++;
					}
					else if(cars[j].getClassOfCar().equals("sports")) {
						incomes += 200;
						expenses += 80 + ((km / 100) * 10);
						care = 70 + (km / 100) * 10;
						cars[j].setProfit(cars[j].getProfit() + (200 - care));
						int profitX = 200 - care;
						employees[empINDEX].setProfit("200 - " + care + " = " + profitX);
						employees[empINDEX].setEmployeeIncome(profitX);
						maintenance[index] = "Car" + cars[j].getCarId() + " maintenance : 70 + " + ((km / 100) * 10) + " = " + care + " (" + km + "km)"; 
						countSports++;
					}
					else {
						incomes += 300;
						expenses += 135 + ((km / 100) * 15);
						care = 120 + (km / 100) * 15;
						cars[j].setProfit(cars[j].getProfit() + (300 - care));
						int profitX = 300 - care;
						employees[empINDEX].setProfit("300 - " + care + " = " + profitX);
						employees[empINDEX].setEmployeeIncome(profitX);
						maintenance[index] = "Car" + cars[j].getCarId() + " maintenance : 120 + " + ((km / 100) * 15) + " = " + care + " (" + km + "km)"; 
						countLux++;
					}
					cars[j].setKm(cars[j].getKm() + km);
					index++;
				}
			}
		}
		
		System.out.println("Office" + officeId + " incomes : " + incomes);
		for(int i = 0;i < countOfContract; i++) {
			for(int j = 0;j < countOfCar; j++) {
				if(contracts[i].getCarId() == cars[j].getCarId()) {
					if(cars[j].getClassOfCar().equals("economy")) {
						System.out.println("\tCar" + cars[j].getCarId() + " : " + 100);				
					}
					else if(cars[j].getClassOfCar().equals("sports")) {
						System.out.println("\tCar" + cars[j].getCarId() + " : " + 200);
					}
					else {
						System.out.println("\tCar" + cars[j].getCarId() + " : " + 300);
					}
				}
			}
		}
		countEconomy *= 5; countSports *= 10; countLux *= 15;
		int performanceBonus = countEconomy + countSports + countLux;
		System.out.println("Office" + officeId + " expenses : " + expenses);
		System.out.println("\tOffice rent : 100");
		System.out.println("\tEmployee salaries : " + countOfEmployee * 30);
		System.out.println("\tPerformance bonuses : " + performanceBonus);
		for(int i = 0;i < maintenance.length; i++) {
			if(maintenance[i] != null) {
				System.out.println("\t" + maintenance[i]);
			}
		}
		System.out.println("Office" + officeId + " profit : " + (incomes - expenses));
	}
}

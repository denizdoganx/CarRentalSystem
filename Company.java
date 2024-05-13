import java.util.Random;
public class Company {
	public Office[] offices;
	public static Customer[] customers;
	private CarRequest[] carRequests;
	public Contract[] contracts;
	private int countOfOffice = 0;
	private static int officeId = 1;
	private int countOfCustomer = 0;
	private static int customerId = 1;
	private int countOfCarRequest = 0;
	private static int carRequestId = 1;
	private int countOfContract = 0;
	private static int contractId = 1;
	private Random rnd = new Random();
	public void addOffice(Office office) {
		//we create a temporary array and back up old elements
		//we set the unique id here
		office.setOfficeId(officeId); 
		if(countOfOffice != 0) {
			Office[] tempArray = new Office[countOfOffice];
			for(int i = 0;i < tempArray.length; i++) {
				tempArray[i] = offices[i];
			}
			offices = new Office[countOfOffice + 1];
			for(int i = 0;i < tempArray.length; i++) {
				offices[i] = tempArray[i];
			}
			offices[countOfOffice] = office;
		}
		else {
			offices = new Office[countOfOffice + 1];
			offices[countOfOffice] = office;
		}
		officeId++;
		countOfOffice++;
	}
	public void addOffice(String phone, String address, String city) {
		Office office = new Office();
		office.setPhone(phone);
		office.setAddress(address);
		office.setCity(city);
		office.setOfficeId(officeId);
		if(countOfOffice != 0) {
			Office[] tempArray = new Office[countOfOffice];
			for(int i = 0;i < tempArray.length; i++) {
				tempArray[i] = offices[i];
			}
			offices = new Office[countOfOffice + 1];
			for(int i = 0;i < tempArray.length; i++) {
				offices[i] = tempArray[i];
			}
			offices[countOfOffice] = office;
		}
		else {
			offices = new Office[countOfOffice + 1];
			offices[countOfOffice] = office;
		}
		officeId++;
		countOfOffice++;
	}
	public void addCustomer(Customer customer) {
		if(offices != null) {
			customer.setCustomerId(customerId);
			if(countOfCustomer != 0) {
				Customer[] tempArray = new Customer[countOfCustomer];
				for(int i = 0;i < tempArray.length; i++) {
					tempArray[i] = customers[i];
				}
				customers = new Customer[countOfCustomer + 1];
				for(int i = 0;i < tempArray.length; i++) {
					customers[i] = tempArray[i];
				}
				customers[countOfCustomer] = customer;
			}
			else {
				customers = new Customer[countOfCustomer + 1];
				customers[countOfCustomer] = customer;
			}
			customerId++;
			countOfCustomer++;
		}
		else {
			System.out.println("\tPLEASE ADD AN OFFICE BEFORE !!!");
		}
	}
	public void addCarRequest(CarRequest carRequest) {
		if(offices != null) {
			if(customers != null) {
				boolean flagOffice = false;
				boolean flagCustomer = false;
				//firstly we check office id and customer id
				for(int i = 0;i < customers.length; i++) {
					if(carRequest.getCustomerId() == customers[i].getCustomerId()) {
						flagCustomer = true;
						break;
					}
				}
				for(int i = 0;i < offices.length; i++) {
					if(offices[i].getOfficeId() == carRequest.getOfficeId()) {
						flagOffice = true;
						break;
					}
				}
				if(!flagOffice && flagCustomer) {
					System.out.println("\tAN OFFICE WITH THE GIVEN ID WERE NOT FOUND");
				}
				else if(!flagOffice && !flagCustomer) {
					System.out.println("\tAN OFFICE WITH THE GIVEN ID WERE NOT FOUND");
					System.out.println("\tNO CUSTOMER WITH THIS ID FOUND");
				}
				else if(flagOffice && !flagCustomer) {
					System.out.println("\tNO CUSTOMER WITH THIS ID FOUND");
				}
				else {
					//We put the car request in a array
					carRequest.setCarRequestId(carRequestId);
					if(countOfCarRequest != 0) {
						CarRequest[] tempArray = new CarRequest[countOfCarRequest];
						for(int i = 0;i < tempArray.length; i++) {
							tempArray[i] = carRequests[i];
						}
						carRequests = new CarRequest[countOfCarRequest + 1];
						for(int i = 0;i < tempArray.length; i++) {
							carRequests[i] = tempArray[i];
						}
						carRequests[countOfCarRequest] = carRequest;
					}
					else {
						carRequests = new CarRequest[countOfCarRequest + 1];
						carRequests[countOfCarRequest] = carRequest;
					}
					for(int i = 0;i < offices.length; i++) {
						if(offices[i].getOfficeId() == carRequest.getOfficeId()) {
							// is there an idle car, we are checking if there are any workers
							if(DateControl.IsTheDateReal(carRequest.getStartDate()) && DateControl.IsTheDateReal(carRequest.getEndDate())) {
								int empId = offices[i].employeeContractControl();
								int crId = offices[i].isThereAAvailableCar(carRequest.getBrand(), carRequest.getModel(), carRequest.getClassOfCar());
								boolean dateControl  = DateControl.ContractDateControl(carRequest.getStartDate(), carRequest.getEndDate());
								if(empId != 0 && crId != 0 && dateControl ) {
									Contract contract = new Contract();
									contract.setContractId(contractId);
									contract.setEmployeeId(empId);
									contract.setCustomerId(carRequest.getCustomerId());
									contract.setCarId(crId);
									contract.setStartDate(carRequest.getStartDate());
									contract.setEndDate(carRequest.getEndDate());
									offices[i].addContract(contract);
									if(countOfContract != 0) {
										Contract[] tempArray = new Contract[countOfContract];
										for(int j = 0;j < tempArray.length; j++) {
											tempArray[j] = contracts[j];
										}
										contracts = new Contract[countOfContract + 1];
										for(int j = 0;j < tempArray.length; j++) {
											contracts[j] = tempArray[j];
										}
										contracts[countOfContract] = contract;
									}
									else {
										contracts = new Contract[countOfContract + 1];
										contracts[countOfContract] = contract;
									}
									System.out.println("\t" + contract.justContract());
									contractId++;
									countOfContract++;
									break;
								}
							}
							else {
								System.out.println("\tINVALID DATE !!!");
							}
						}
					}
					carRequestId++;
					countOfCarRequest++;
				}
			}
			else {
				System.out.println("\tPLEASE ADD AN CUSTOMER BEFORE !!!");
			}
		}
		else {
			System.out.println("\tPLEASE ADD AN OFFICE BEFORE !!!");
		}
	}
	public void addCarRequestRandom(String[] command) {
		if(offices != null) {
			if(customers != null) {
				boolean flag = false;
				int officeID = -1;
				int officeIndex = 0;
				if(!command[1].equals("-1")) {
					for(int i = 0;i < offices.length; i++) {
						if(Integer.valueOf(command[1]) == offices[i].getOfficeId()) {
							officeIndex = i;
							officeID = Integer.valueOf(command[1]);
							flag = true;
							break;
						}
					}
				}
				else {
					officeIndex = rnd.nextInt(countOfOffice);
					officeID = offices[officeIndex].getOfficeId();
					flag = true;
				}
				if(flag) {
					int randomCustomerID = customers[rnd.nextInt(countOfCustomer)].getCustomerId();
					Date startDate = Main.today;
					Date endDate = new Date((Main.today.getDay() + (rnd.nextInt(3) + 1)), Main.today.getMonth(), Main.today.getYear());
					CarRequest cr  = new CarRequest();
					cr.setCarRequestId(carRequestId);
					cr.setOfficeId(officeID);
					cr.setCustomerId(randomCustomerID);
					cr.setBrand("*");
					cr.setModel("*");
					cr.setClassOfCar(command[2]);
					cr.setStartDate(startDate);
					cr.setEndDate(endDate);
					if(countOfCarRequest != 0) {
						CarRequest[] tempArray = new CarRequest[countOfCarRequest];
						for(int i = 0;i < tempArray.length; i++) {
							tempArray[i] = carRequests[i];
						}
						carRequests = new CarRequest[countOfCarRequest + 1];
						for(int i = 0;i < tempArray.length; i++) {
							carRequests[i] = tempArray[i];
						}
						carRequests[countOfCarRequest] = cr;
					}
					else {
						carRequests = new CarRequest[countOfCarRequest + 1];
						carRequests[countOfCarRequest] = cr;
					}
					int empId = offices[officeIndex].employeeContractControl();
					int crId = offices[officeIndex].isThereAAvailableCar(cr.getBrand(), cr.getModel(), cr.getClassOfCar());
					if(empId != 0 && crId != 0) {
						Contract contract = new Contract();
						contract.setContractId(contractId);
						contract.setEmployeeId(empId);
						contract.setCustomerId(cr.getCustomerId());
						contract.setCarId(crId);
						contract.setStartDate(cr.getStartDate());
						contract.setEndDate(cr.getEndDate());
						offices[officeIndex].addContract(contract);
						if(countOfContract != 0) {
							Contract[] tempArray = new Contract[countOfContract];
							for(int i = 0;i < tempArray.length; i++) {
								tempArray[i] = contracts[i];
							}
							contracts = new Contract[countOfContract + 1];
							for(int i = 0;i < tempArray.length; i++) {
								contracts[i] = tempArray[i];
							}
							contracts[countOfContract] = contract;
						}
						else {
							contracts = new Contract[countOfContract + 1];
							contracts[countOfContract] = contract;
						}
						System.out.println("\t" + contract.justContract());
						contractId++;
						countOfContract++;
					}
					carRequestId++;
					countOfCarRequest++;
				}
				else {
					System.out.println("\tAN OFFICE WITH THE GIVEN ID WERE NOT FOUND");
				}
			}
			else {
				System.out.println("\tPLEASE ADD AN CUSTOMER BEFORE !!!");
			}
		}
		else {
			System.out.println("\tPLEASE ADD AN OFFICE BEFORE !!!");
		}
	}
	public void addCarRequestNRandom(String[] command) {
		if(offices != null) {
			if(customers != null) {
				int carReqNumber = Integer.valueOf(command[2]);
				int j = 0;
				while(carReqNumber != j) {
					Office office = offices[rnd.nextInt(countOfOffice)];
					int custID = customers[rnd.nextInt(countOfCustomer)].getCustomerId();
					Date startDate = Main.today;
					Date endDate = new Date(Main.today.getDay()+ (rnd.nextInt(3) + 1), Main.today.getMonth(), Main.today.getYear());
					CarRequest cr = new CarRequest();
					cr.setCarRequestId(carRequestId);
					cr.setOfficeId(office.getOfficeId());
					cr.setCustomerId(custID);
					cr.setBrand("*");
					cr.setModel("*");
					cr.setClassOfCar("*");
					cr.setStartDate(startDate);
					cr.setEndDate(endDate);
					if(countOfCarRequest != 0) {
						CarRequest[] tempArray = new CarRequest[countOfCarRequest];
						for(int i = 0;i < tempArray.length; i++) {
							tempArray[i] = carRequests[i];
						}
						carRequests = new CarRequest[countOfCarRequest + 1];
						for(int i = 0;i < tempArray.length; i++) {
							carRequests[i] = tempArray[i];
						}
						carRequests[countOfCarRequest] = cr;
					}
					else {
						carRequests = new CarRequest[countOfCarRequest + 1];
						carRequests[countOfCarRequest] = cr;
					}
					int empId = office.employeeContractControl();
					int crId = office.isThereAAvailableCar(cr.getBrand(), cr.getModel(), cr.getClassOfCar());
					if(empId != 0 && crId != 0) {
						Contract contract = new Contract();
						contract.setContractId(contractId);
						contract.setEmployeeId(empId);
						contract.setCustomerId(cr.getCustomerId());
						contract.setCarId(crId);
						contract.setStartDate(cr.getStartDate());
						contract.setEndDate(cr.getEndDate());
						office.addContract(contract);
						if(countOfContract != 0) {
							Contract[] tempArray = new Contract[countOfContract];
							for(int i = 0;i < tempArray.length; i++) {
								tempArray[i] = contracts[i];
							}
							contracts = new Contract[countOfContract + 1];
							for(int i = 0;i < tempArray.length; i++) {
								contracts[i] = tempArray[i];
							}
							contracts[countOfContract] = contract;
						}
						else {
							contracts = new Contract[countOfContract + 1];
							contracts[countOfContract] = contract;
						}
						System.out.println("\t" + contract.justContract());
						contractId++;
						countOfContract++;
					}
					countOfCarRequest++;
					carRequestId++;
					j++;
				}
			}
			else {
				System.out.println("\tPLEASE ADD AN CUSTOMER BEFORE !!!");
			}
		}
		else {
			System.out.println("\tPLEASE ADD AN OFFICE BEFORE !!!");
		}
	}
	public void addEmployee(Employee employee) {
		if(offices != null) {
			if(DateControl.IsTheDateReal(employee.getDateOfBirth())) {
				boolean flagOffice = false;
				for(int i = 0; i < offices.length; i++) {
					if(offices[i].getOfficeId() == employee.getOfficeId()) {
						if(offices[i].countOfEmployeeControl()) {
							offices[i].addEmployee(employee);
						}
						else {
							System.out.println("\tA MAXIMUM OF 3 EMPLOYEES CAN WORK IN ONE OFFICE !!!");
						}
						flagOffice = true;
						break;
					}
				}
				if(!flagOffice) {
					System.out.println("\tAN OFFICE WITH THE GIVEN ID WERE NOT FOUND");
				}
			}
			else {
				System.out.println("\tINVALÝD DATE !!!");
			}
		}
		else {
			System.out.println("\tYOU MUST ADD AN OFFICE BEFORE ADDÝNG EMPLOYEE !!!");
		}
	}
	public void addCar(String brand, String model, String classOfCar, int km, int officeId){
		Car car = new Car();
		car.setBrand(brand);
		car.setModel(model);
		car.setClassOfCar(classOfCar);
		car.setKm(km);
		car.setOfficeId(officeId);
		if(offices != null) {
			boolean flag = false;
			for(int i = 0; i < offices.length; i++) {
				if(offices[i].getOfficeId() == officeId) {
					if(offices[i].carClassControl(car)) {
						offices[i].addCar(car);
					}
					else {
						System.out.println("\tYOU CAN ONLY ADD A CAR IN ECONOMY, SPORTS AND LUXURY CLASS !!!");
					}
					flag = true;
					break;
				}
			}
			if(!flag) {
				System.out.println("\tAN OFFICE WITH THE GIVEN ID WERE NOT FOUND");
			}
		}
		else {
			System.out.println("\tYOU MUST ADD AN OFFICE BEFORE ADDÝNG CAR !!!");
		}
	}
	public void listEmployee(int officeId) {
		//We make the necessary checks and get the list of workers from the given office ID and list them.
		if(offices != null) {
			boolean flag = false;
			for(int i = 0;i < offices.length; i++) {
				if(offices[i].getOfficeId() == officeId) {
					offices[i].listEmployee();
					flag = true;
					break;
				}
			}
			if(!flag) {
				System.out.println("\tAN OFFICE WITH THE GIVEN ID WERE NOT FOUND");
			}
		}
		else {
			System.out.println("\tYOU MUST ADD AN OFFICE BEFORE LISTING EMPLOYEES !!!");
		}
	}
	public void listCar(int officeId) {
		//We do operations similar to the ones in listEmployee.
		if(offices != null) {
			boolean flag = false;
			for(int i = 0;i < offices.length; i++) {
				if(offices[i].getOfficeId() == officeId) {
					offices[i].listCar();
					flag = true;
					break;
				}
			}
			if(!flag) {
				System.out.println("\tAN OFFICE WITH THE GIVEN ID WERE NOT FOUND !!!");
			}
		}
		else {
			System.out.println("\tYOU MUST ADD AN OFFICE BEFORE LISTING CARS !!!");
		}
	}
	public void listOffice() {
		if(countOfOffice != 0) {
			System.out.println("#LIST OF OFFICES");
			for(int i = 0 ; i < countOfOffice ; i++) {
				System.out.println("\t" + offices[i].displayOffice());
			}
		}
		else {
			System.out.println("\tYOU HAVE NOT ADD AN OFFICE YET");
		}
	}
	public void listCustomer() {
		if(countOfCustomer != 0) {
			for(int i = 0;i < customers.length; i++) {
				System.out.println("\t" + customers[i].displayCustomer());
			}
		}
		else {
			System.out.println("\tYOU HAVE NOT ADD AN CUSTOMER YET");
		}
	}
	public void listCarRequest() {
		if(countOfCarRequest != 0) {
			for(int i = 0;i < carRequests.length; i++) {
				System.out.println("\t" + carRequests[i].displayCarRequest());
			}
		}
		else {
			System.out.println("\tCAR REQUEST NOT FOUND !!!");
		}
	}
	public void listContract() {
		if(countOfContract != 0) {
			for(int i = 0;i < contracts.length; i++) {
				if(contracts[i] != null) {
					System.out.println("\t" + contracts[i].displayContract());
				}
			}
		}
		else {
			System.out.println("\tCONTRACT NOT FOUND !!!");
		}
	}
 	public void deleteOffice(int officeId) {
		if(offices != null) {
			boolean flag = false;
			//We check if there is an office in the given id
			for(int i = 0; i < offices.length; i++) {
				if(offices[i].getOfficeId() == officeId) {
					flag = true;
					break;
				}
			}
			if(flag) {
				if(countOfOffice > 1) {
					//We create the temporary array and back up the elements except the deleted element.
					Office[] tempArray = new Office[countOfOffice - 1];
					for(int i = 0;i < offices.length; i++) {
						if(offices[i].getOfficeId() < officeId) {
							tempArray[i] = offices[i];
						}
						else if(offices[i].getOfficeId() == officeId) {
							continue;
						}
						else {
							tempArray[i - 1] = offices[i];
						}
					}
					offices = new Office[countOfOffice - 1];
					for(int i = 0;i < tempArray.length; i++) {
						offices[i] = tempArray[i]; 
					}
					countOfOffice--;
				}
				else if(countOfOffice == 1) {
					offices = null;
					countOfOffice--;
				}
			}
			else {
				System.out.println("\tAN OFFICE WITH THIS ID IS NOT FOUND !!!");
			}
		}
		else {
			System.out.println("\tERROR!!! YOU TRIED TO DELETE A NON-EXISTENT OFFICE");
		}
	}
	public void deleteEmployee(int officeId, int employeeId) {
		if(offices != null) {
			boolean flag = false;
			for(int i = 0;i < offices.length; i++) {
				//We find the office from the office id and call the deleteEmployee command of that office.
				if(offices[i].getOfficeId() == officeId) {
					if(offices[i].employeeIdControl(employeeId)) {
						offices[i].deleteEmployee(employeeId);
					}
					else {
						System.out.println("\tNO EMPLOYEES WITH THIS ID WAS FOUND IN THIS OFFICE !!!");
					}
					flag = true;
					break;
				}
			}
			if(!flag) {
				System.out.println("\tAN OFFICE WITH THIS ID IS NOT FOUND !!!");
			}
		}
		else {
			System.out.println("\tYOU CANNOT DELETE EMPLOYEE WITHOUT ADDING AN OFFICE !!!");
		}
	}
	public void deleteCar(int officeId, int carId) {
		if(offices != null) {
			boolean flag = false;
			for(int i = 0;i < offices.length; i++) {
				if(offices[i].getOfficeId() == officeId) {
					if(offices[i].carIdControl(carId)) {
						offices[i].deleteCar(carId);
					}
					else {
						System.out.println("\tNO CARS WITH THIS ID WAS FOUND IN THIS OFFICE !!!");
					}
					flag = true;
					break;
				}
			}
			if(!flag) {
				System.out.println("\tAN OFFICE WITH THIS ID IS NOT FOUND !!!");
			}
		}
		else {
			System.out.println("\tYOU CANNOT DELETE CAR WITHOUT ADDING AN OFFICE !!!");
		}
	}
	public void deleteFromOffice(int cntrctID) {
		boolean flag = false;
		for(int i = 0;i < countOfOffice; i++) {
			if(offices[i].getContracts() != null) {
				Contract[] officeContract = offices[i].getContracts();
				for(int j = 0;j < officeContract.length; j++) {
					if(officeContract[j].getContractId() == cntrctID) {
						offices[i].deleteContract(cntrctID);
						flag = true;
						break;
					}
				}
			}
			if(flag) {
				break;
			}
		}
	}
	public void deleteContract(int cntrctID) {
		if(countOfContract > 1) {
			for(int i = 0;i < countOfContract; i++) {
				if(contracts[i] != null) {
					if(contracts[i].getContractId() == cntrctID) {
						contracts[i] = null;
					}
				}
			}
		
		}
		else if(countOfContract == 1) {
			contracts = null;
		}
	}
	public void reformContract() {
		if(contracts != null) {
			int index = 0;
			for(int i = 0;i < countOfContract; i++) {
				if(contracts[i] != null) {
					contracts[i - index] = contracts[i];
				}
				else {
					index++;
				}
			}
			countOfContract = countOfContract - index;
		}
		else {
			countOfContract = 0;
		}
	}
	public void nextDay() {
		if(!DateControl.IsTheDateReal(Main.today)) {
			DateControl.updatedDate(Main.today);
		}
		System.out.println("------ OFFICE PROFITS ------\n");
		System.out.println("\t\t\tDate : " + Main.today.getDay() + "." + Main.today.getMonth() + "." + Main.today.getYear() + "\n");
		for(int i = 0;i < countOfOffice; i++) {
			offices[i].statusRaport(Main.today);
			System.out.println();
		}
		if(Main.today.getDay() < 9) {
			System.out.println("------ Office Statistics of the Last 10 Days ------\n");
			for(int i = 0;i < countOfOffice; i++) {
				System.out.println("---Office" + offices[i].getOfficeId() + ":\n");
				offices[i].staticsOfLast10Days();
				System.out.println();
			}
		}
		if(Main.CountOfDay % 3 == 0 && Main.CountOfDay != 0) {
			System.out.println("\n------ System Recommendations ------\n");
			for(int i = 0;i < countOfOffice; i++) {
				System.out.print("Office" + offices[i].getOfficeId() + ": ");
				offices[i].systemRecommedation();
			}
		}
		Main.today.setDay(Main.today.getDay() + 1);
		for(int i = 0;i < countOfContract; i++) {
			if(contracts[i] != null) {
				if(!DateControl.dateControl(Main.today, contracts[i].getEndDate())) {
					int ID = contracts[i].getContractId();
					deleteContract(ID);
					deleteFromOffice(ID);
				}
			}
			else {
				continue;
			}
		}
		Main.CountOfDay++;
		reformContract();
	}
}
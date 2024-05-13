
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileOperations {
	static Company comp = new Company();
	public static void uploadInput(String fileName) throws IOException {
		File file = new File(fileName);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileReader fReader = new FileReader(file);
		BufferedReader bReader = new BufferedReader(fReader);
		String line;
		while((line = bReader.readLine()) != null) {
			System.out.println(">" + line);
			findCommand(line);
		}
		bReader.close();
	}
	public static void findCommand(String line) {
		String[] properties = line.split(";");
		if(line.startsWith("addOffice")) {
			Office office = new Office();
			office.setPhone(properties[1]);
			office.setAddress(properties[2]);
			office.setCity(properties[3]);
			comp.addOffice(office);
		}
		else if(line.startsWith("listOffice")) {
			comp.listOffice();
		}
		else if(line.startsWith("addEmployee")) {
			Employee emp = new Employee();
			emp.setName(properties[1]);
			emp.setSurname(properties[2]);
			emp.setGender(properties[3]);
			emp.setDateOfBirth(CreateDate(properties[4]));
			emp.setOfficeId(Integer.valueOf((properties[5])));
			comp.addEmployee(emp);
		}
		else if(line.startsWith("listEmployee") || line.startsWith("listCar;")) {
			if(line.startsWith("listEmployee")) {
				comp.listEmployee((Integer.valueOf(properties[1])));
			}
			else {
				comp.listCar((Integer.valueOf(properties[1])));
			}
		}
		else if(line.startsWith("addCar;")) {
			comp.addCar(properties[1], properties[2], properties[3], (Integer.valueOf(properties[4])), (Integer.valueOf(properties[5])));
		}
		else if(line.startsWith("addCustomer")) {
			Customer customer = new Customer(properties[1], properties[2]);
			comp.addCustomer(customer);
		}
		else if(line.startsWith("listCustomer")) {
			comp.listCustomer();
		}
		else if(line.startsWith("deleteEmployee") || line.startsWith("deleteCar")) {
			if(line.startsWith("deleteEmployee")) {
				comp.deleteEmployee(Integer.valueOf(properties[1]), Integer.valueOf(properties[2]));
			}
			else {
				comp.deleteCar(Integer.valueOf(properties[1]), Integer.valueOf(properties[2]));
			}
		}
		else if(line.startsWith("deleteOffice")) {
			comp.deleteOffice(Integer.valueOf(properties[1]));
		}
		else if(line.startsWith("addCarRequest") && (properties.length == 8 || properties.length == 3)) {
			if(properties.length == 8) {
				CarRequest cr = new CarRequest();
				cr.setOfficeId(Integer.valueOf(properties[1]));
				cr.setCustomerId(Integer.valueOf(properties[2]));
				cr.setBrand(properties[3]);
				cr.setModel(properties[4]);
				cr.setClassOfCar(properties[5]);
				cr.setStartDate(CreateDate(properties[6]));
				cr.setEndDate(CreateDate(properties[7]));
				comp.addCarRequest(cr);
			}
			else {
				if(properties[0].equals("addCarRequestNRandom")) {
					comp.addCarRequestNRandom(properties);
				}
				else {
					comp.addCarRequestRandom(properties);
				}
			}
		}
		else if(line.startsWith("listCarRequest")) {
			comp.listCarRequest();
		}
		else if(line.startsWith("listContract")) {
			comp.listContract();
		}
		else if(line.startsWith("nextDay")) {
			comp.nextDay();
		}
	}
	public static Date CreateDate(String dateCommand) {
		int[] dateArray = new int[3];
		int index = 0;
		String temp = "";
		for(int i = 0; i < dateCommand.length(); i++) {
			char chr = dateCommand.charAt(i);
			if(chr != '.') {
				temp += chr;
			}
			if(chr == '.' || i==dateCommand.length()-1) {
				dateArray[index] = Integer.valueOf(temp);
				index++;
				temp = "";
			}
		}
		
		Date date = new Date();
		date.setDay(dateArray[0]);
		date.setMonth(dateArray[1]);
		date.setYear(dateArray[2]);
		
		//if(!DateControl.IsTheDateReal(date)) {
			//DateControl.updatedDate(date);
		//}
		return date;
	}
}


public class DateControl {
	public static boolean IsTheDateReal(Date date) {
		boolean flag = true;
		int day = date.getDay();
		int month = date.getMonth();
		int year = date.getYear();
		if(day <= 0 || month <= 0 || year <= 0 || day > 31 || month > 12) {
			flag = false;
		}
		else {
			if(month == 2 || month == 4 || month == 6 || month == 9 || month == 11) {
				if(month == 2) {
					if(day > 29) {
						flag = false;
					}
					else if(day == 29 && year % 4 != 0) {
						flag = false;
					}
				}
				else {
					if(day == 31) {
						flag = false;
					}
				}
			}
			else {
				if(day > 31 && month != 12) {
					flag = false;

				}
				else if (day > 31 && month == 12) {
				}
			}
		}
		return flag;
	}
	
	public static Date updatedDate(Date date) {
		int day = date.getDay();
		int month = date.getMonth();
		int year = date.getYear();
		if(month == 2 || month == 4 || month == 6 || month == 9 || month == 11) {
			if(month == 2) {
				if(day > 29) {
					
					date.setDay(01);
					date.setMonth(03);
					date.setYear(year);
				}
				else if(day == 29 && year % 4 != 0) {
					date.setDay(01);
					date.setMonth(03);
					date.setYear(year);
				}
			}
			else {
				if(day == 31) {
					date.setDay(01);
					date.setMonth(month+1);
					date.setYear(year);
				}
			}
		}
		else {
			if(day > 31 && month != 12) {
				date.setDay(01);
				date.setMonth(month+1);
				date.setYear(year);
			}
			else if (day > 31 && month == 12) {
				date.setDay(01);
				date.setMonth(01);
				date.setYear(year+1);
			}
		}
		return date;
	}
	public static boolean dateControl(Date startDate, Date endDate) {
		boolean flag = true;
		int startDay = startDate.getDay();
		int startMonth = startDate.getMonth();
		int startYear = startDate.getYear();
		int endDay = endDate.getDay();
		int endMonth = endDate.getMonth();
		int endYear = endDate.getYear();
		if(startYear > endYear) {
			flag = false;
		}
		else {
			if(startYear == endYear) {
				if(startMonth > endMonth) {
					flag = false;
				}
				else if(startMonth == endMonth) {
					if(startDay > endDay) {
						flag = false;
					}
				}
			}
		}
		return flag;
	}

	
	public static boolean ContractDateControl(Date startDate, Date endDate) {
		boolean flag = true;
		if(DateControl.dateControl(startDate, endDate)) {
			if(startDate.getDay() == Main.today.getDay() && startDate.getMonth() == Main.today.getMonth() && startDate.getYear() == Main.today.getYear()) {
				if(endDate.getDay() - startDate.getDay() > 3) {
					System.out.println("\tCAR REQUEST MUST BE FOR 1-4 DAYS !!");
					flag = false;
				}
			}
			else {
				System.out.println("\tCAR REQUEST MUST BE FOR TODAY !!");
				flag = false;
			}
		}
		else {
			System.out.println("\tTHE END DAY OF THE CONTRACT MUST BE GREATER THAN OR EQUAL TO THE STARTDAY !!");
			flag = false;
		}
		return flag;
	}
}
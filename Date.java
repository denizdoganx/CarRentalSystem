
public class Date {
	  private int day;
	    private int month;
	    private int year;
	    public int count;
	    public int getDay() {
	        return day;
	    }
	    public Date(int day, int month, int year) {
	        this.day = day;
	        this.month = month;
	        this.year = year;
	        this.count = 0;
	    }
	    public Date() {

	    }
	    public void setDay(int day) {
	    	count++;
	        this.day = day;
	    }
	    public int getMonth() {
	        return month;
	    }
	    public void setMonth(int month) {
	        this.month = month;
	    }
	    public int getYear() {
	        return year;
	    }
	    public void setYear(int year) {
	        this.year = year;
	    }
	    public String displayDate() {
	        return day + "." + month + "." + year;
	    }
}

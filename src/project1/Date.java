package project1;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public Date() {} //create an object with today’s date (see Calendar class)
    public Date(String date) {
//        if(!isValid(date))
        String results[] = date.split("\\/");
        this.month = Integer.parseInt(results[0]);
        this.day = Integer.parseInt(results[1]);
        this.year = Integer.parseInt(results[2]);
    }//take “mm/dd/yyyy” and create a Date object

    @Override
    public int compareTo(Date date) {
        if ((this.year == date.year) && (this.month == date.month) && (this.day == date.day))
            return Compare.EQUAL; //current Date is the same as given date
        if (this.year > date.year)
            return Compare.MORETHAN; //current Date is earlier than given date
        if (this.month > date.month)
            return Compare.MORETHAN; //current Date is earlier than given date
        if (this.day > date.day)
            return Compare.MORETHAN; //current Date is earlier than given date
        return Compare.LESSTHAN; //current Date is older than given date
    }

    //check whether a year is a leap year
    private boolean checkLeapYear(int year) {
        if (year % Time.QUADRENNIAL == 0) {
            if (year % Time.CENTENNIAL == 0) {
                if (year % Time.QUATERCENTENNIAL == 0) {
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isValid(Date date) {
        //01/12/2021
        if (date.month == Time.JANUARY || date.month == Time.MARCH ||
                date.month == Time.MAY || date.month == Time.JULY ||
                date.month == Time.AUGUST || date.month == Time.OCTOBER ||
                date.month == Time.DECEMBER) {
            if (date.day <= Time.THIRTYONEDAYSMONTH) {
                return true;
            }
        }
        if (date.month == Time.APRIL || date.month == Time.JUNE ||
                date.month == Time.SEPTEMBER || date.month == Time.NOVEMBER) {
            if (date.day <= Time.THIRTYDAYSMONTH)
                return true;
        }
        if (date.month == Time.FEBRUARY) {
            if (checkLeapYear(date.year) && (date.day <= Time.DAYSINLEAPFEBRUARY))
                return true;
            if ((!checkLeapYear(date.year)) && (date.day <= Time.DAYSINNONLEAPFEBRUARY))
                return true;
        }
        return false;
    } //check if a date is a valid calendar date

    //testbed main
    public static void main (String[] args) {

    }
}

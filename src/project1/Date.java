package project1;

public class Date {
    private int year;
    private int month;
    private int day;
    public Date() {} //create an object with today’s date (see Calendar class)

    public Date(String date) {

    } //take “mm/dd/yyyy” and create a Date object

    //@Override
    public int compareTo(Date date) {
        return 0;
    }

    public boolean isValid() {
        return true;
    } //check if a date is a valid calendar date
}

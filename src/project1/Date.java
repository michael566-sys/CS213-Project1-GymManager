package project1;

import java.util.Calendar;

/**
 * Date class to store the year, month, and day, and perform validity check and
 * comparison functionalities.
 * 
 * @author Songyuan Zhang, Robert Jimenez
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Create a Date object to get the today's date.
     * Use Calendar class to get today's date, split it by space and store
     * information
     * into the instance variables.
     */
    public Date() {
        Calendar cal = Calendar.getInstance();
        String currentDate = Calendar.getInstance().getTime().toString();
        String[] results = currentDate.split("\\s");
        this.year = Integer.parseInt(results[5]);
        this.month = Integer.parseInt(this.getMonthNumberFromString(results[1]));
        this.day = Integer.parseInt(results[2]);
    }

    /**
     * Create a Date object to initialize a new date.
     * Split the input date string by forward slash and populate into instance
     * variables: month, day, and year.
     * 
     * @param date the string date contains date information
     */
    public Date(String date) {
        String results[] = date.split("\\/");
        this.month = Integer.parseInt(results[0]);
        this.day = Integer.parseInt(results[1]);
        this.year = Integer.parseInt(results[2]);
    }// take “mm/dd/yyyy” and create a Date object

    /**
     * Get the numeric number of the month from input string.
     * Convert the short form name of the month into numeric strings.
     * Used by Date() constructor to convert month names from Calendar class into
     * numeric strings.
     * Split the input date string by forward slash and populate into instance
     * variables: month, day, and year.
     * 
     * @param monthString the string that contains the short forms of the month.
     * @return string the string literals that represents the numeric value of the
     *         month.
     */
    private static String getMonthNumberFromString(String monthString) {
        monthString = monthString.toLowerCase();
        if (monthString.equals("jan"))
            return "01";
        if (monthString.equals("feb"))
            return "02";
        if (monthString.equals("mar"))
            return "03";
        if (monthString.equals("apr"))
            return "04";
        if (monthString.equals("may"))
            return "05";
        if (monthString.equals("jun"))
            return "06";
        if (monthString.equals("jul"))
            return "07";
        if (monthString.equals("aug"))
            return "08";
        if (monthString.equals("sep"))
            return "09";
        if (monthString.equals("oct"))
            return "10";
        if (monthString.equals("nov"))
            return "11";
        if (monthString.equals("dec"))
            return "12";
        return null;
    }

    /**
     * Check whether a year is a leap year.
     * Make use of Time.QUADRENNIAL, Time.CENTENNIAL, and Time.QUATERCENTENNIAL
     * constants
     * to check the leap-year.
     * 
     * @param year the integer year that represents the year in the form of number.
     * @return true if the year is a leap year, false otherwise.
     */
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

    /**
     * Compare two Dates.
     * 
     * @param date the date that wants to compare with current Date object
     * @return Compare.EQUAL if dates are equal, Compare.MORETHAN if the current
     *         Date object comes after parameter Date object, Compare.LESSTHAN if
     *         otherwise.
     */
    @Override
    public int compareTo(Date date) {
        if ((this.year == date.year) && (this.month == date.month) && (this.day == date.day))
            return Compare.EQUAL; // current Date is the same as given parameter date
        if (this.year > date.year)
            return Compare.MORETHAN; // current Date comes after given parameter date
        if (this.year < date.year)
            return Compare.LESSTHAN; // current Date comes before given parameter date
        if (this.month > date.month)
            return Compare.MORETHAN; // current Date comes after given parameter date
        if (this.month < date.month)
            return Compare.LESSTHAN; // current Date comes before given parameter date
        if (this.day > date.day)
            return Compare.MORETHAN; // current Date comes after than given parameter date
        return Compare.LESSTHAN; // current Date comes before given parameter date
    }

    /**
     * Check whether the Date object is a valid calendar date.
     * Make use of Time enum to perform the validation on month, year, and day.
     * 
     * @return true if the year is a valid calendar date, false otherwise.
     */
    public boolean isValid() {
        if (this.day < 0 || this.month < 0 || this.year < 0) {
            return false;
        }
        // 01/12/2021
        if (this.month == Time.JANUARY || this.month == Time.MARCH ||
                this.month == Time.MAY || this.month == Time.JULY ||
                this.month == Time.AUGUST || this.month == Time.OCTOBER ||
                this.month == Time.DECEMBER) {
            if (this.day <= Time.THIRTYONEDAYSMONTH) {
                return true;
            }
        }
        if (this.month == Time.APRIL || this.month == Time.JUNE ||
                this.month == Time.SEPTEMBER || this.month == Time.NOVEMBER) {
            if (this.day <= Time.THIRTYDAYSMONTH)
                return true;
        }
        if (this.month == Time.FEBRUARY) {
            if (checkLeapYear(this.year) && (this.day <= Time.DAYSINLEAPFEBRUARY))
                return true;
            if ((!checkLeapYear(this.year)) && (this.day <= Time.DAYSINNONLEAPFEBRUARY))
                return true;
        }
        return false;
    }

    /**
     * Get the values of the month instance variable.
     * 
     * @return this.month the value of the month instance variable
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Get the values of the year instance variable.
     * 
     * @return this.month the value of the year instance variable
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Get the values of the day instance variable.
     * 
     * @return this.month the value of the day instance variable
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Override the toString method and print class name, instructor, and start time
     * of the class.
     * 
     * @return string containing class name, instructor, and start time of the
     *         class.
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    };

    /**
     * Testbed main to exercise the code in the Date class
     * 
     * @param args input arguments
     */
    public static void main(String[] args) {
        // test CompareTo
        // test case 1
        System.out.println("**compareTo Test cases: ");
        Date date = new Date("2/29/2016");
        int expectedCompareToOutput = Compare.EQUAL;
        int actualCompareToOutput = date.compareTo(new Date("2/29/2016"));
        System.out.println("**Test case#1: two dates are the same: ");
        testCompareToResult(date, expectedCompareToOutput, actualCompareToOutput);
        // test case 2
        date = new Date("2/29/2016");
        expectedCompareToOutput = Compare.MORETHAN;
        actualCompareToOutput = date.compareTo(new Date("2/28/2016"));
        System.out.println("**Test case#2: current object's date is more than parameter object's date: ");
        testCompareToResult(date, expectedCompareToOutput, actualCompareToOutput);
        // test case 3
        date = new Date("2/28/2016");
        expectedCompareToOutput = Compare.LESSTHAN;
        actualCompareToOutput = date.compareTo(new Date("2/29/2016"));
        System.out.println("**Test case#3: current object's date is less than parameter object's date: ");
        testCompareToResult(date, expectedCompareToOutput, actualCompareToOutput);
        // test case 4
        date = new Date("3/28/2016");
        expectedCompareToOutput = Compare.MORETHAN;
        actualCompareToOutput = date.compareTo(new Date("2/28/2016"));
        System.out.println("**Test case#4: current object's month is more than parameter object's month: ");
        testCompareToResult(date, expectedCompareToOutput, actualCompareToOutput);
        // test case 5
        date = new Date("3/28/2016");
        expectedCompareToOutput = Compare.LESSTHAN;
        actualCompareToOutput = date.compareTo(new Date("2/28/2017"));
        System.out.println("**Test case#5: current object's year is less than parameter object's year: ");
        testCompareToResult(date, expectedCompareToOutput, actualCompareToOutput);

        // test isValid
        // test case 1
        System.out.println("**isValid Test cases: ");
        date = new Date("2/29/2003");
        boolean expectedIsValidOutput = false;
        boolean actualIsValidOutput = date.isValid();
        System.out.println("**Test case#1: a date in a non-leap year has only 28 days in February: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);
        // test case 2
        date = new Date("2/30/2008");
        expectedIsValidOutput = false;
        actualIsValidOutput = date.isValid();
        System.out.println("**Test case#2: a date in a leap year has 29 days in Feburary: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);
        // test case 3
        date = new Date("13/31/2003");
        actualIsValidOutput = date.isValid();
        expectedIsValidOutput = false;
        System.out.println("**Test case#3: a valid range for the month shall be 1-12: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);
        // test case 4
        date = new Date("13/8/2003");
        actualIsValidOutput = date.isValid();
        expectedIsValidOutput = false;
        System.out.println("**Test case#4: a valid range for the month shall be 1-12: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);
        // test case 5
        date = new Date("-1/31/2003");
        actualIsValidOutput = date.isValid();
        expectedIsValidOutput = false;
        System.out.println("**Test case#5: a valid month shall be positive: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);
        // test case 6
        date = new Date("12/32/1989");
        actualIsValidOutput = date.isValid();
        expectedIsValidOutput = false;
        System.out.println(
                "**Test case#6: a valid range for the days shall be 1-31 for January, March, May, July, August, October, and December: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);
        // test case 7
        date = new Date("4/31/2022");
        actualIsValidOutput = date.isValid();
        expectedIsValidOutput = false;
        System.out.println(
                "**Test case#7: a valid range for the days shall be 1-30 for April, June, September, November: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);
        // test case 8
        date = new Date("1/-1/2003");
        actualIsValidOutput = date.isValid();
        expectedIsValidOutput = false;
        System.out.println(
                "**Test case#8: a valid day shall be positive: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);
        // test case 9
        date = new Date("1/20/-1");
        actualIsValidOutput = date.isValid();
        expectedIsValidOutput = false;
        System.out.println(
                "**Test case#9: a valid year shall be positive: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);

        // test Date() constructor to get the current date
        System.out.println("**current date constructor test cases: ");
        date = new Date();
        int expectedCurrentDayOutput = 29;
        int expectedCurrentYearOutput = 2022;
        int expectedCurrentMonthOutput = 9;
        int actualCurrentDayOutput = date.day;
        int actualCurrentYearOutput = date.year;
        int actualCurrentMonthOutput = date.month;
        System.out.println("**Test case#1: test current date, assume today is 10/03/2022");
        System.out.println(date.toString());
        if (expectedCurrentDayOutput == actualCurrentDayOutput && expectedCurrentYearOutput == actualCurrentYearOutput
                && expectedCurrentMonthOutput == actualCurrentMonthOutput)
            System.out.println(", PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }
    /**
     * Test isValid method by comparing actual results and expected results.
     * Called by testbed main.
     *
     * @param date           the date that is being tested
     * @param expectedOutput the expected output for the isValid method
     * @param actualOutput   the actual output for the isValid method
     */
    private static void testIsValidResult(Date date, boolean expectedOutput, boolean actualOutput) {
        System.out.println(date.toString());
        System.out.println("isValid() returns " + actualOutput);
        if (actualOutput == expectedOutput)
            System.out.println(", PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }

    /**
     * Test compareTo method by comparing actual results and expected results.
     * Called by testbed main.
     *
     * @param date           the date that is being tested
     * @param expectedOutput the expected output for the compareTo method
     * @param actualOutput   the actual output for the compareTo method
     */
    private static void testCompareToResult(Date date, int expectedOutput, int actualOutput) {
        System.out.println(date.toString());
        System.out.println("compareTo() returns " + actualOutput);
        if (actualOutput == expectedOutput)
            System.out.println(", PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }

}
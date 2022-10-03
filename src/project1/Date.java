package project1;

import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public Date() {
        Calendar cal = Calendar.getInstance();
        String currentDate = Calendar.getInstance().getTime().toString();
        String[] results = currentDate.split("\\s");
        this.year = Integer.parseInt(results[5]);
        this.month = Integer.parseInt(this.getMonthNumberFromString(results[1]));
        this.day = Integer.parseInt(results[2]);
    } // create an object with today’s date (see Calendar class)

    public Date(String date) {
        // if(!isValid(date))
        String results[] = date.split("\\/");
        this.month = Integer.parseInt(results[0]);
        this.day = Integer.parseInt(results[1]);
        this.year = Integer.parseInt(results[2]);
    }// take “mm/dd/yyyy” and create a Date object

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

    // check whether a year is a leap year
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

    public boolean isValid() {
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
    } // check if a date is a valid calendar date
    public int getMonth(){
        return this.month;
    }
    public int getYear(){
        return this.year;
    }
    public int getDay(){
        return this.day;
    }
    @Override
    public String toString() {
        // April March, DOB: 3/31/1990, Membership expires 6/30/2023, Location:
        // PISCATAWAY, 08854, MIDDLESEX
        return this.month + "/" + this.day + "/" + this.year;
    };

    // testbed main
    public static void main(String[] args) {
        // test CompareTo
        System.out.println("**compareTo Test cases: ");
        Date date = new Date("2/29/2016");
        int expectedCompareToOutput = Compare.EQUAL;
        int actualCompareToOutput = date.compareTo(new Date("2/29/2016"));
        System.out.println("**Test case#1: two dates are the same: ");
        testCompareToResult(date, expectedCompareToOutput, actualCompareToOutput);

        date = new Date("2/29/2016");
        expectedCompareToOutput = Compare.MORETHAN;
        actualCompareToOutput = date.compareTo(new Date("2/28/2016"));
        System.out.println("**Test case#2: current object's date is more than parameter object's date: ");
        testCompareToResult(date, expectedCompareToOutput, actualCompareToOutput);

        date = new Date("2/28/2016");
        expectedCompareToOutput = Compare.LESSTHAN;
        actualCompareToOutput = date.compareTo(new Date("2/29/2016"));
        System.out.println("**Test case#2: current object's date is less than parameter object's date: ");
        testCompareToResult(date, expectedCompareToOutput, actualCompareToOutput);

        date = new Date("3/28/2016");
        expectedCompareToOutput = Compare.MORETHAN;
        actualCompareToOutput = date.compareTo(new Date("2/28/2016"));
        System.out.println("**Test case#3: current object's month is more than parameter object's month: ");
        testCompareToResult(date, expectedCompareToOutput, actualCompareToOutput);

        date = new Date("3/28/2016");
        expectedCompareToOutput = Compare.LESSTHAN;
        actualCompareToOutput = date.compareTo(new Date("2/28/2017"));
        System.out.println("**Test case#4: current object's year is less than parameter object's year: ");
        testCompareToResult(date, expectedCompareToOutput, actualCompareToOutput);

        // test isValid
        System.out.println("**isValid Test cases: ");
        date = new Date("2/29/2003");
        boolean expectedIsValidOutput = false;
        boolean actualIsValidOutput = date.isValid();
        System.out.println("**Test case#1: a date in a non-leap year has only 28 days in February: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);

        date = new Date("2/30/2008");
        expectedIsValidOutput = false;
        actualIsValidOutput = date.isValid();
        System.out.println("**Test case#2: a date in a leap year has 29 days in Feburary: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);

        date = new Date("13/31/2003");
        actualIsValidOutput = date.isValid();
        expectedIsValidOutput = false;
        System.out.println("**Test case#3: a valid range for the month shall be 1-12: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);

        date = new Date("13/8/2003");
        actualIsValidOutput = date.isValid();
        expectedIsValidOutput = false;
        System.out.println("**Test case#4: a valid range for the month shall be 1-12: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);

        date = new Date("-1/31/2003");
        actualIsValidOutput = date.isValid();
        expectedIsValidOutput = false;
        System.out.println("**Test case#5: a valid month shall be positive: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);

        date = new Date("12/32/1989");
        actualIsValidOutput = date.isValid();
        expectedIsValidOutput = false;
        System.out.println(
                "**Test case#6: a valid range for the days shall be 1-31 for January, March, May, July, August, October, and December: ");
        testIsValidResult(date, expectedIsValidOutput, actualIsValidOutput);

        date = new Date("4/31/2022");
        actualIsValidOutput = date.isValid();
        expectedIsValidOutput = false;
        System.out.println(
                "**Test case#7: a valid range for the days shall be 1-30 for April, June, September, November: ");
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
        System.out.println("**Test case#1: test current date, assume today is 9/29/2022");
        System.out.println(date.toString());
        if (expectedCurrentDayOutput == actualCurrentDayOutput && expectedCurrentYearOutput == actualCurrentYearOutput
                && expectedCurrentMonthOutput == actualCurrentMonthOutput)
            System.out.println(", PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }

    private static void testIsValidResult(Date date, boolean expectedOutput, boolean actualOutput) {
        System.out.println(date.toString());
        System.out.println("isValid() returns " + actualOutput);
        if (actualOutput == expectedOutput)
            System.out.println(", PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }

    private static void testCompareToResult(Date date, int expectedOutput, int actualOutput) {
        System.out.println(date.toString());
        System.out.println("compareTo() returns " + actualOutput);
        if (actualOutput == expectedOutput)
            System.out.println(", PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }
}
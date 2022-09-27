package project1;

import javax.swing.plaf.basic.BasicCheckBoxUI;

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

    public boolean isValid() {
        //01/12/2021
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
    } //check if a date is a valid calendar date

    @Override
    public String toString() {
        //April March, DOB: 3/31/1990, Membership expires 6/30/2023, Location: PISCATAWAY, 08854, MIDDLESEX
        return this.year + ", " + this.month + ", " + this.day;
    };

    //testbed main
    public static void main (String[] args) {
        //test CompareTo
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

        //test isValid
        System.out.println("**isValid Test cases: ");
        date = new Date("2/29/2011");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case#1: a date in a non-leap year has only 28 days in February: ");
        testIsValidResult(date, expectedOutput, actualOutput);

        date = new Date("2/29/2016");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case#2: a date in a leap year has 29 days in Feburary: ");
        testIsValidResult(date, expectedOutput, actualOutput);

        date = new Date("13/21/1999");
        actualOutput = date.isValid();
        expectedOutput = false;
        System.out.println("**Test case#3: a date with an invalid month value: ");
        testIsValidResult(date, expectedOutput, actualOutput);
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

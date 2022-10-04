package project1;
/**
 Member class to represent a member instance.
 Store the first name, last name, date of birth, expiration date of the membership,
 and the gym location that the membership is at.
 @author Songyuan Zhang, Robert Jimenez
 */
public class Member implements Comparable<Member> {
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;
    /**
     Create a Member object to populate the instance variables.
     @param fname the string for the first name
     @param lname the string for the last name
     @param dob the date of birth for the member
     @param expire the expiration date for the member
     @param location the gym location for the membership
     */
    public Member(String fname, String lname, Date dob, Date expire, Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }
    /**
     Check whether two member has the same first name, last name, and date of birth.
     @param obj the Object instance that is being compared with the current Member instance.
     @return true if the two members are the same, false if otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member) {
            Member member = (Member) obj; // casting
            if (member.fname.toLowerCase().equals(this.fname.toLowerCase()) &&
                    member.lname.toLowerCase().equals(this.lname.toLowerCase()) &&
                    member.dob.compareTo(this.dob) == Compare.EQUAL) {
                return true;
            }
        }
        return false;
    }
    /**
     Compare which member comes first than the other in the ascending order sequence.
     If two members have the same last name, ordered by the first name.
     This method mainly serves the PN command
     @param member the Object instance that is being compared with the current Member instance.
     @return Compare.EQUAL if members are equal, Compare.MORETHAN if the current member object comes after parameter member object, Compare.LESSTHAN if otherwise.
     */
    @Override
    public int compareTo(Member member) {
        if (this.lname.toLowerCase().equals(member.lname.toLowerCase())) {
            if (this.fname.toLowerCase().compareTo(member.fname.toLowerCase()) > 0)
                return Compare.MORETHAN;
            if (this.fname.toLowerCase().compareTo(member.fname.toLowerCase()) < 0)
                return Compare.LESSTHAN;
            return Compare.EQUAL;
        }
        if (this.lname.toLowerCase().compareTo(member.lname.toLowerCase()) > 0)
            return Compare.MORETHAN;
        return Compare.LESSTHAN;
    }
    /**
     Override the toString method and print the instance variables.
     Print the name, date of birth, expiration date, and location.
     @return string containing the name, date of birth, expiration date, and location.
     */
    @Override
    public String toString() {
        return this.fname + " " + this.lname + ", DOB: " + this.dob.toString() + ", Membership expires " + this.expire.toString() + ", "
                + "Location: " + this.location.toString();
    };
    /**
     Get the first name of the member instance variable.
     @return this.fname the value of the member instance variable
     */
    public String getFirstName() {
        return this.fname;
    }
    /**
     Get the last name of the member instance variable.
     @return this.lname the value of the member instance variable
     */
    public String getLastName() {
        return this.lname;
    }
    /**
     Get the date of birth of the member instance variable.
     @return this.dob the value of the member instance variable
     */
    public Date getDateOfBirth() {
        return this.dob;
    }
    /**
     Get the expiration date of the member instance variable.
     @return this.expire the value of the member instance variable
     */
    public Date getExpirationDate() {
        return this.expire;
    }
    /**
     Get the gym location of the member instance variable.
     @return this.location the value of the member instance variable
     */
    public Location getGymLocation() {
        return this.location;
    }
    /**
     Testbed main to exercise the code in the Member class
     @param args input arguments
     */
    public static void main(String[] args) {
        // test cases for equals
        // test case 1
        System.out.println("**equals Test cases: ");
        Member member = new Member("David", "Marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER);
        boolean expectedEqualsOutput = true;
        boolean actualEqualsOutput = member.equals(new Member("david", "marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER));
        System.out.println("**Test case#1: two members have the same name: ");
        testEqualsResult(member, expectedEqualsOutput, actualEqualsOutput);
        // test case 2
        member = new Member("David", "Marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER);
        expectedEqualsOutput = false;
        actualEqualsOutput = member.equals(new Member("James", "marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER));
        System.out.println("**Test case#2: two members have the different names: ");
        testEqualsResult(member, expectedEqualsOutput, actualEqualsOutput);

        // test cases for compareTo
        // test case 1
        System.out.println("**compareTo Test cases: ");
        member = new Member("David", "Marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER);
        int expectedCompareToOutput = Compare.EQUAL;
        int actualCompareToOutput = member.compareTo(new Member("davId", "mArr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER));
        System.out.println("**Test case#1: two members have the same first name and last name with different uppercase and lower case combinations: ");
        testCompareToResult(member, expectedCompareToOutput, actualCompareToOutput);
        // test case 2
        member = new Member("davId", "Carr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER);
        expectedCompareToOutput = Compare.LESSTHAN;
        actualCompareToOutput = member.compareTo(new Member("James", "mArr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER));
        System.out.println("**Test case#2: two members have the different last name, current member object's name comes before parameter member object's name: ");
        testCompareToResult(member, expectedCompareToOutput, actualCompareToOutput);
        // test case 3
        member = new Member("David", "mArr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER);
        expectedCompareToOutput = Compare.MORETHAN;
        actualCompareToOutput = member.compareTo(new Member("james", "Carr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER));
        System.out.println("**Test case#3: two members have the different last name, current member object's name comes after parameter member object's name: ");
        testCompareToResult(member, expectedCompareToOutput, actualCompareToOutput);
        // test case 4
        member = new Member("David", "mArr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER);
        expectedCompareToOutput = Compare.LESSTHAN;
        actualCompareToOutput = member.compareTo(new Member("james", "mArr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER));
        System.out.println("**Test case#4: two members have the same last name, different first name, current member object's name comes before parameter member object's name: ");
        testCompareToResult(member, expectedCompareToOutput, actualCompareToOutput);
        // test case 5
        member = new Member("james", "mArr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER);
        expectedCompareToOutput = Compare.MORETHAN;
        actualCompareToOutput = member.compareTo(new Member("David", "mArr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER));
        System.out.println("**Test case#5: Two members have the same last name, different first name, current member object's name comes after parameter member object's name: ");
        testCompareToResult(member, expectedCompareToOutput, actualCompareToOutput);
    }

    /**
     Test equals method by comparing actual results and expected results.
     Called by testbed main.
     @param member the member that is being tested
     @param expectedOutput the expected output for the equals method
     @param actualOutput the actual output for the equals method
     */
    private static void testEqualsResult(Member member, boolean expectedOutput, boolean actualOutput) {
        System.out.println(member.toString());
        System.out.println("equals() returns " + actualOutput);
        if (actualOutput == expectedOutput)
            System.out.println(", PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }
    /**
     Test compareTo method by comparing actual results and expected results.
     Called by testbed main.
     @param member the member that is being tested
     @param expectedOutput the expected output for the compareTo method
     @param actualOutput the actual output for the compareTo method
     */
    private static void testCompareToResult(Member member, int expectedOutput, int actualOutput) {
        System.out.println(member.toString());
        System.out.println("compareTo() returns " + actualOutput);
        if (actualOutput == expectedOutput)
            System.out.println(", PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }
}
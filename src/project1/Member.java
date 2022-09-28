package project1;

public class Member implements Comparable<Member>{
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    public Member(String fname, String lname, Date dob, Date expire, Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }
    @Override
    public String toString() {
        //April March, DOB: 3/31/1990, Membership expires 6/30/2023, Location: PISCATAWAY, 08854, MIDDLESEX
        return this.fname + ", " + this.lname + ": " + this.dob + ", " + "Membership expires " + expire + ", " + "Location: " + this.location;
    };
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member) {
            Member member = (Member) obj; //casting
            if (member.fname.toLowerCase().equals(this.fname.toLowerCase()) &
                    member.lname.toLowerCase().equals(this.lname.toLowerCase()) &
                    member.dob.compareTo(this.dob) == Compare.EQUAL
            ) {
                return true;
            }
        }
        return false;
    }
    @Override
    public int compareTo(Member member) { // this class mainly serves for the PN command
        /*
        ordered by the members’ last names and then first names;
        that is, if two members have the same last name, ordered by the first name.
        */
        if (member.lname.toLowerCase().equals(this.lname.toLowerCase())) {
            if (member.fname.toLowerCase().compareTo(this.fname.toLowerCase()) > 0)
                return Compare.MORETHAN;
            if (member.fname.toLowerCase().compareTo(this.fname.toLowerCase()) < 0)
                return Compare.LESSTHAN;
            return Compare.EQUAL;
        }
        if (member.lname.toLowerCase().compareTo(this.lname.toLowerCase()) > 0)
            return Compare.MORETHAN;
        return Compare.LESSTHAN;
    }
    public String getName() {
        // search what is requried for a close class??????
        // getter is also mutable?????
        return this.fname + " " + this.lname;
    }
    public Date getDateOfBirth() {
        return this.dob;
    }
    public Date getExpirationDate() {
        return this.expire;
    }
    public Location getGymLocation() {
        return this.location;
    }

    //testbed main
    public static void main (String[] args) {
        //test cases for equals
//        System.out.println("**equals Test cases: ");
//        Member member = new Member("David", "Marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER);
//        boolean expectedEqualsOutput = true;
//        boolean actualEqualsOutput = member.equals(new Member("david", "marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER));
//        System.out.println("**Test case#1: two members have the same name: ");
//        testEqualsResult(member, expectedEqualsOutput, actualEqualsOutput);
//
//        member = new Member("David", "Marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER);
//        expectedEqualsOutput = false;
//        actualEqualsOutput = member.equals(new Member("James", "marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER));
//        System.out.println("**Test case#2: two members have the different names: ");
//        testEqualsResult(member, expectedEqualsOutput, actualEqualsOutput);
//
//        //test cases for compareTo
//        System.out.println("**compareTo Test cases: ");
//        member = new Member("David", "Marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER);
//        int expectedCompareToOutput = Compare.EQUAL;
//        int actualCompareToOutput = member.compareTo(new Member("David", "Marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER));
//        System.out.println("**Test case#1: two members have the same name: ");
//        testCompareToResult(member, expectedCompareToOutput, actualCompareToOutput);
//
//        member = new Member("James", "Marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER);
//        expectedCompareToOutput = Compare.LESSTHAN;
//        actualCompareToOutput = member.compareTo(new Member("David", "Marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER));
//        System.out.println("**Test case#2: current member object's name comes before parameter member object's name: ");
//        testCompareToResult(member, expectedCompareToOutput, actualCompareToOutput);
//
//        member = new Member("David", "Marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER);
//        expectedCompareToOutput = Compare.MORETHAN;
//        actualCompareToOutput = member.compareTo(new Member("James", "Marr", new Date("2/12/2016"), new Date("3/12/2016"), Location.BRIDGEWATER));
//        System.out.println("**Test case#3: current member object's name comes after parameter member object's name: ");
//        testCompareToResult(member, expectedCompareToOutput, actualCompareToOutput);
    }
    private static void testEqualsResult(Member member, boolean expectedOutput, boolean actualOutput) {
        System.out.println(member.toString());
        System.out.println("equals() returns " + actualOutput);
        if (actualOutput == expectedOutput)
            System.out.println(", PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }
    private static void testCompareToResult(Member member, int expectedOutput, int actualOutput) {
        System.out.println(member.toString());
        System.out.println("compareTo() returns " + actualOutput);
        if (actualOutput == expectedOutput)
            System.out.println(", PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }
}

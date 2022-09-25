package project1;

public class Member implements Comparable<Member>{
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    public Member(String fname, String lname, String dob, String expire, Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = new Date(dob);
        this.expire = new Date(expire);
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
        if (member.lname.equals(this.lname)) {
            return member.fname.compareTo(this.fname);
        }
        return member.lname.compareTo(this.lname);
    }

//    public String getName() {
//        // search what is requried for a close class??????
//        // getter is also mutable?????
//        this.ln
//    }

    //testbed main
    public static void main(String[] args) {
        //test case for equals
        //
        //test case for compareTo

        System.out.println();
    }
}

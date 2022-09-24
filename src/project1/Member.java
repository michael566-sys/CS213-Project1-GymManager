package project1;

public class Member {
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    public Member() {
        this.dob = new Date();
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
                    member.dob.compareTo(this.dob) == 0
            ) {
                return true;
            }
        }
        return false;
    }
    //@Override
    public int compareTo(Member member) {
        if (member.fname.equals(this.fname)) {

        }
        return 1;
    }


    //testbed main
    public static void main(String[] args) {
        //test case for equals
        //
        //test case for compareTo

        System.out.println();
    }
}

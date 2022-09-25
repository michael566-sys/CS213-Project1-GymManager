package project1;

public class MemberDatabase {
    private Member [] mlist;
    private int size;
    private int find(Member member) {
        return 0;
    }
    private void grow() { }
    public boolean add(Member member) {
        System.out.println("entered MemberDatabase class");
        // find whether method exists first

        // check valid date
        /*
        o Any date that is not a valid calendar date
        o The date of birth is today or a future date
        o A member who is less than 18 years old
        o An invalid city name, that is, the gym location doesn’t exist
        */

        return false;
    }
    public boolean remove(Member member) {
        return false;
    }
    public void print() { } //print the array contents as is
    public void printByCounty() { } //sort by county and then zipcode
    public void printByExpirationDate() { } //sort by the expiration date
    public void printByName() { } //sort by last name and then first name
}

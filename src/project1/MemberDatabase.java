package project1;

public class MemberDatabase {
    private Member [] mlist;
    private int size; // this is # of members in the database
    public MemberDatabase() {
        this.size = 0; //Theres no members in database yet
        mlist = new Member[4]; // Set the initial capacity to 4 members
    }
    private int find(Member member) {
        for (int i = 0; i < this.size - 1;i++) { // Checks if member is in database
            if (mlist[i].compareTo(member) == 0) {
                return i; //returns index is member is in database
            }
        }
        return -1; //returns "NOT FOUND" if not

    }
    private void grow() { //Method to grow capacity by 4 into new array copying old array elements
        Member[]  oldLst = mlist; //Temp array to hold old array
        int newCapacity = getCapacity() + 4;
        mlist = new Member[newCapacity]; // new array with 4 more capacity

        for (int x = 0; x < oldLst.length; x++) { //copy old array elements into new array
            mlist[x] = oldLst[x];
        }
    }
    public boolean add(Member member) {
        for (int i = 0; i < this.size - 1;i++) { // Checks if member is already in database
            if (mlist[i].compareTo(member) != 0) {
                return false; //if not return false
            }
        }
        if ((this.size + 1) > getCapacity() ) { //checks if database has enough room for another member
            grow();  //increases the database if it doesnt.
            mlist[size] = member;
            size++;
            return true;
        } else {
            mlist[size] = member;
            size++;
            return true;
        }
    }
    public int getCapacity() {
        return mlist.length; //returns capacity of members array
    }
    public boolean remove(Member member) {
        return false;
    }
    public void print () { } //print the array contents as is
    public void printByCounty() { } //sort by county and then zipcode
    public void printByExpirationDate() { } //sort by the expiration date
    public void printByName() { } //sort by last name and then first name
}

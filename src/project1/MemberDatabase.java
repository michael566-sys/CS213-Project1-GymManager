package project1;

import jdk.javadoc.doclet.Taglet;

import javax.tools.DocumentationTool;

public class MemberDatabase {
    private Member [] mlist;
    private int size; // this is current # of members in the database
    public MemberDatabase() {
        this.size = 0; //There is no members in database yet
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

    private boolean checkDateOfBirthIsValid() {
        long millis = System.currentTimeMillis();
      //  if ()
        return true;
    }

    public boolean add(Member member) {
        Date todayDate = new Date();
        System.out.println("entered MemberDatabase class");

        // find whether method exists first

        //if ()
        // check valid date

//        o Any date that is not a valid calendar date
        if (member.getDateOfBirth().isValid() == false || member.getExpirationDate().isValid() == false) {
            return false;
        }
//        o The date of birth is today or a future date
        if (member.getDateOfBirth().compareTo(todayDate) >= 0) { //if dob is older than or equal to current date
            return false;
        }
   //    o A member who is less than 18 years old
        Date atLeast18 = new Date("09/29/2004"); //Hardcoded but im going to change to use Calendar Class
        if (member.getDateOfBirth().compareTo(atLeast18) > 0) { //if DOB comes after the date that makes someone 18 yrs old
            return false;
        }

   //     o An invalid city name, that is, the gym location doesn’t exist
        boolean found = false;
        for (Location gymLocations : Location.values()) {
            if (member.getGymLocation() == gymLocations ) {
                found = true;
                break;
            }
        }
        if (!found) {
            return false;
        }
        //check any date that is not a valid calendar date
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
        int indexToRemove = find(member);
        if (indexToRemove != -1) {
            mlist[indexToRemove] = mlist[this.size];
            mlist[this.size] = null;
            size--;
            return true;
        }
        return false;
    }
    public void print() {
        for (Member member: mlist) {
            System.out.println(member);
        }
    } //print the array contents as is
    public void printByCounty() {
        for (int i = 1; i < this.size ; i++) { //insertion sort algo
            Member currentMem = mlist[i];
            int j = i - 1;
            while (j >= 0 && mlist[j].getGymLocation().compare(currentMem.getGymLocation()) > 0) {
                mlist[j + 1] = mlist[j];
                j--;
            }
            mlist[j + 1] = currentMem;
        }
        for (Member member: mlist) {
            System.out.println(member);
        }
    } //sort by county and then zipcode
    public void printByExpirationDate() {
        for (int i = 1; i < this.size; i++) {
            Member currentMem = mlist[i];
            int j = i - 1;
            while (j >= 0 && mlist[j].getExpirationDate().compareTo(currentMem.getExpirationDate()) == 1) {
                mlist[j + 1] = mlist[j];
                j--;
            }
            mlist[j + 1] = currentMem;
        }
        for (Member member: mlist) {
            System.out.println(member);
        }
    } //sort by the expiration date
    public void printByName() {
        for (int i = 1; i < this.size; i++) {
            Member currentMem = mlist[i];
            int j = i - 1;
            while (j >= 0 && mlist[j].compareTo(currentMem) > 0) {
                mlist[j + 1] = mlist[j];
                j--;
            }
            mlist[j + 1] = currentMem;
        }
        for (Member member: mlist) {
            System.out.println(member);
        }
    } //sort by last name and then first name
    public static void main (String[] args ) {
        System.out.println("testing...testing");
    }
}

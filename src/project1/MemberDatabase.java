package project1;
/**
 MemberDatabase class to store members inside a member list.
 Performs add and remove member, display members with or without order.
 @author Songyuan Zhang, Robert Jimenez
 */
public class MemberDatabase {
    private Member [] mlist;
    private int size;
    /**
     Create a MemberDatabase object.
     Initialize one member list array and size of the array.
     */
    public MemberDatabase() {
        this.size = Compare.EMPTY_SIZE; //There is no members in database yet
        mlist = new Member[Compare.ARRAYGROWSIZE]; // Set the initial capacity to 4 members
    }
    /**
     Find the member from the database and return the index of the member.
     Find the member by traversing the member list.
     @param member member who needs to be found.
     @return index if the member is found, Compare.INDEXNOTFOUND otherwise.
     */
    private int find(Member member) {
        for (int index = 0; index < this.size; index++) { // Checks if member is in database
            if (mlist[index].equals(member)) {
                return index; //returns index is member is in database
            }
        }
        return Compare.NOT_FOUND; //returns "NOT_FOUND" if not
    }
    /**
     Grow capacity by 4 into new array and copy old array elements into the new array.
     Find the member from the database and return the index of the member.
     Find the member by traversing the member list.
     */
    private void grow() {
        Member []  oldList = mlist; //Temp array to hold old array
        int newCapacity = this.mlist.length + 4;
        this.mlist = new Member[newCapacity]; // new array with 4 more capacity
        for (int index = 0; index < oldList.length; index++) { //copy old array elements into new array
            mlist[index] = oldList[index];
        }
    }
    /**
     Check whether a member's age is more than 18 years old or not.
     @param member member to check valid age.
     @return true if the member is within the valid age, false otherwise.
     */
    public boolean checkValidMemberAge(Member member) {
        Date today = new Date(); //Hardcoded but im going to change to use Calendar Class
        Date memberDob = member.getDateOfBirth();
        if (memberDob.getYear() + Compare.VALIDAGE > today.getYear())
            return false;
        if (memberDob.getYear() + Compare.VALIDAGE == today.getYear()) {
            if (memberDob.getMonth() > today.getMonth()) {
                return false;
            }
            if (memberDob.getMonth() == today.getMonth()) {
                if (memberDob.getDay() > today.getDay())
                    return false;
            }
        }
        return true;
    }
    /**
     Add a member into the member list.
     Add member who is not already in the list. Grow the list when necessary.
     @param member member to add into the member list.
     @return true if the member is successfully added, false if member is already in the list.
     */
    public boolean add(Member member) {
        Date todayDate = new Date();
        //check any date that is not a valid calendar date
        for (int i = 0; i < this.size; i++) { // Checks if member is already in database
            if (mlist[i].equals(member)) {
                return false; //if not return false
            }
        }
        if ((this.size + 1) > this.mlist.length ) { //checks if database has enough room for another member
            grow();  //increases the database if it doesn't.
            mlist[size] = member;
            size++;
            return true;
        } else {
            mlist[size] = member;
            size++;
            return true;
        }
    }
    /**
     Remove a member from the member list.
     Remove a member who can be found in the list.
     After removing the member, move all subsequent members one space to the left.
     @param member member to remove from the member list.
     @return true if the member is successfully removed, false if member is not found in the list.
     */
    public boolean remove(Member member) {
        int indexToRemove = find(member);
        if (indexToRemove != Compare.NOT_FOUND) {
            mlist[indexToRemove] = mlist[this.size - 1];
            int tempIndex = indexToRemove;
            while (tempIndex + 1 <= this.size - 1) {
                this.mlist[tempIndex] = this.mlist[tempIndex + 1];
                tempIndex++;
            }
            mlist[tempIndex] = null;
            this.size--;
            return true;
        }
        return false;
    }
    /**
     Print all members from the MemberDatabase.
     Display the list of members in the database without sorting (current order in the array.)
     Corresponds to the P command from the GymManager class.
     */
    public void print() {
        if (this.size == 0) {
            System.out.println("Member database is empty!");
            return;
        }
        System.out.println();
        System.out.println("-list of members-");
        for (int memberIndex = 0; memberIndex < size; memberIndex++) {
            System.out.println(mlist[memberIndex]);
        }
        System.out.println("-end of list-");
        System.out.println();
    } //print the array contents as is
    /**
     Print all members from the MemberDatabase order by the county names and then the zip codes.
     If the locations are in the same county, ordered by the zip codes.
     Use insertion sorting algorithm. Corresponds to the PC command from the GymManager class.
     */
    public void printByCounty() {
        for (int index = 1; index < this.size ; index++) { //insertion sort algo
            Member currentMem = mlist[index];
            int previousIndex = index - 1;
            while (previousIndex >= 0
                    && mlist[previousIndex].getGymLocation().compare(currentMem.getGymLocation()) < Compare.EQUAL) {
                mlist[previousIndex + 1] = mlist[previousIndex];
                previousIndex--;
            }
            mlist[previousIndex + 1] = currentMem;
        }
        for (Member member: mlist) {
            System.out.println(member);
        }
    }
    /**
     Print the list of members in the database ordered by the expiration dates.
     If two expiration dates are the same, their order doesn’t matter.
     Use insertion sorting algorithm.
     Corresponds to the PD command.
     */
    public void printByExpirationDate() { //works fine
        for (int index = 1; index < this.size; index++) {
            Member currentMem = mlist[index];
            int previousIndex = index - 1;
            while (previousIndex >= 0
                    && mlist[previousIndex].getExpirationDate().compareTo(currentMem.getExpirationDate()) == 1) {
                mlist[previousIndex + 1] = mlist[previousIndex];
                previousIndex--;
            }
            mlist[previousIndex + 1] = currentMem;
        }
        for (Member member: mlist) {
            System.out.println(member);
        }
    }
    /**
     Print the list of members in the database ordered by the members’ last names and then first names.
     If two members have the same last name, ordered by the first name.
     Use insertion sorting algorithm.
     Corresponds to the PN command from the GymManager class.
     */
    public void printByName() {
        for (int index = 1; index < this.size; index++) {
            Member currentMem = mlist[index];
            int previousIndex = index - 1;
            while (previousIndex >= 0
                    && mlist[previousIndex].compareTo(currentMem) > 0) {
                mlist[previousIndex + 1] = mlist[previousIndex];
                previousIndex--;
            }
            mlist[previousIndex + 1] = currentMem;
        }
        for (Member member: mlist) {
            System.out.println(member);
        }
    }
    /**
     Populate the expiration date and location of a member.
     Called by GymManager to populate the expiration date and location of a member,
     whose first name, last name, and date of birth are given.
     @param tempMember a tempary member who only has name and date of birth.
     @return member if the population is success, null otherwise.
     */
    public Member addExpirationDateAndLocation(Member tempMember) {
        int memberIndex = find(tempMember);
        if (memberIndex == Compare.NOT_FOUND) {
            return null;
        }
        return new Member(tempMember.getFirstName(), tempMember.getLastName(), tempMember.getDateOfBirth(),
                this.mlist[memberIndex].getExpirationDate(), this.mlist[memberIndex].getGymLocation());
    }
    /**
     Check expiration date of a member has passed or not.
     Find the member and compare the expiration date with today's date.
     Called by GymManager to check the expiration date.
     @param member the member whose membership expiration date needs to be checked.
     @return true if the expiration date has passed, false otherwise.
     */
    public boolean checkExpirationDate(Member member) {
        int memberIndex = find(member);
        if (memberIndex == Compare.NOT_FOUND) {
            return false;
        }
        Member memberOnCheck = this.mlist[memberIndex];
        Date today = new Date();
        if (today.compareTo(memberOnCheck.getExpirationDate()) == Compare.MORETHAN) {
            return true; //it is expired
        }
        return false; //it is not expired
    }
    /**
     Check a member is the database or not.
     Called by GymManager to check the existence of a member in the database.
     @param member the member who needs to be found in the database.
     @return true if the member is found, false otherwise.
     */
    public boolean isInDatabase(Member member) {
        if (find(member) == Compare.NOT_FOUND) {
            return false;
        }
        return true;
    }
    /**
     Check the member database is empty or not.
     @return true if the database is empty, false otherwise.
     */
    public boolean checkEmptyDatebase() {
        if (this.size == 0)
            return true;
        return false;
    }
}

package project1;

public class MemberDatabase {
    private Member [] mlist;
    private int size; // this is current # of members in the database
    public MemberDatabase() {
        this.size = 0; //There is no members in database yet
        mlist = new Member[4]; // Set the initial capacity to 4 members
    }
    private int find(Member member) {
        for (int i = 0; i < this.size; i++) { // Checks if member is in database
            if (mlist[i].equals(member)) {
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
    //check whether a member is less than 18 years old
    public boolean checkValidMemberAge(Member member) {
        Date today = new Date(); //Hardcoded but im going to change to use Calendar Class
        Date memberDob = member.getDateOfBirth();
        if (memberDob.getYear() + Compare.EIGHTEENYEARS > today.getYear())
            return false;
        if (memberDob.getYear() + Compare.EIGHTEENYEARS == today.getYear()) {
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
    public boolean add(Member member) {
        Date todayDate = new Date();
        //check any date that is not a valid calendar date
        for (int i = 0; i < this.size; i++) { // Checks if member is already in database
            if (mlist[i].equals(member)) {
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
            mlist[indexToRemove] = mlist[this.size - 1];
            int tempIndex = indexToRemove;
            while (tempIndex + 1 <= this.size - 1) {
                this.mlist[tempIndex] = this.mlist[tempIndex + 1];
                tempIndex++;
            }
            mlist[tempIndex] = null;
//            mlist[this.size - 1] = null;
            this.size--;
            return true;
        }
        return false;
    }
    public void print() {
        if (this.size == 0) {
            System.out.println("Member database is empty!");
            return;
        }
        System.out.println();
        System.out.println("-list of members-");
        for (int memberIndex = 0; memberIndex < size; memberIndex ++) {
            System.out.println(mlist[memberIndex]);
        }
        System.out.println("-end of list-");
        System.out.println();
    } //print the array contents as is

    public void printByCounty() {
        for (int i = 1; i < this.size ; i++) { //insertion sort algo
            Member currentMem = mlist[i];
            int j = i - 1;
            while (j >= 0 && mlist[j].getGymLocation().compare(currentMem.getGymLocation()) < Compare.EQUAL) {
                mlist[j + 1] = mlist[j];
                j--;
            }
            mlist[j + 1] = currentMem;
        }
        for (Member member: mlist) {
            System.out.println(member);
        }
    } //sort by county and then zipcode

    public void printByExpirationDate() { //works fine
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
    public Member addExpirationDateAndLocation(Member tempMember) {
        int memberIndex = find(tempMember);
        if (memberIndex == -1) {
            return null;
        }
        return new Member(tempMember.getFirstName(), tempMember.getLastName(), tempMember.getDateOfBirth(),
                this.mlist[memberIndex].getExpirationDate(), this.mlist[memberIndex].getGymLocation());
    }
    // Checks expiration date
    public boolean checkExpirationDate(Member member) {
        int memberIndex = find(member);
        if (memberIndex == -1) {
            return false;
        }
        Member memberOnCheck = this.mlist[memberIndex];
        Date today = new Date();
        if (today.compareTo(memberOnCheck.getExpirationDate()) == Compare.MORETHAN) {
            return true; // it is expired
        }
        return false; // it is not expired
    }
    public boolean isInDatabase(Member member) {
        if (find(member) == -1) {
            return false;
        }
        return true;
    }
    public boolean checkEmptyStudentList() {
        if (this.size == 0)
            return true;
        return false;
    }
}

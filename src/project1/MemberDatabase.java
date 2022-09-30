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
        for (int i = 0; i < this.size; i++) { // Checks if member is in database
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
   //     System.out.println("entered MemberDatabase class");


//        o Any date that is not a valid calendar date
        if (member.getDateOfBirth().isValid() == false || member.getExpirationDate().isValid() == false) {
            System.out.println("Not valid date");
            return false;
        }
//        o The date of birth is today or a future date
        if (member.getDateOfBirth().compareTo(todayDate) > 0) { //if dob is older than or equal to current date
            System.out.println("DOB is today or future date");
            return false;
        }
   //    o A member who is less than 18 years old
        Date atLeastEightTeen = new Date("09/29/2004"); //Hardcoded but im going to change to use Calendar Class
        if (member.getDateOfBirth().compareTo(atLeastEightTeen) > 0) { //if DOB comes after the date that makes someone 18 yrs old
            System.out.println("Must be at least 18 years old.");
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
            System.out.println("Gym location doesnt exist");
            return false;
        }
        //check any date that is not a valid calendar date
        for (int i = 0; i < this.size; i++) { // Checks if member is already in database
            if (mlist[i].equals(member)) {
                System.out.println("Already in database");
                return false; //if not return false
            }
        }
        // Checks expiration date
        if (todayDate.compareTo(member.getExpirationDate()) > 0) {
            System.out.println("Membership is expired");
            return false;
        }
        if ((this.size + 1) > getCapacity() ) { //checks if database has enough room for another member
            grow();  //increases the database if it doesnt.
            mlist[size] = member;
            size++;
            return true;
        } else {
            System.out.println("Sucessfully added: " + member.getName()) ;
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
            mlist[this.size - 1] = null;
            size--;
            return true;
        }
        if (indexToRemove == -1) {
            System.out.println("Member not found! ");
            return false;
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
            while (j >= 0 && mlist[j].getGymLocation().compare(currentMem.getGymLocation()) < 0) {
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
    public boolean isValidDate(Member member) {
        //        o Any date that is not a valid calendar date
        if (member.getDateOfBirth().isValid() == false || member.getExpirationDate().isValid() == false) {
            System.out.println("Not valid date");
            return false;
        }
        return true;
    }
    public boolean isDobTodayOrFutureDate(Member member) {
        Date todayDate = new Date();
        //        o The date of birth is today or a future date
        if (member.getDateOfBirth().compareTo(todayDate) > 0) { //if dob is older than or equal to current date
            System.out.println("DOB is today or future date");
            return false;
        }
        return true;
    }
    public boolean isAtLeastEightTeen(Member member) {
        //    o A member who is less than 18 years old
        Date atLeastEightTeen = new Date("09/30/2004"); //Hardcoded but im going to change to use Calendar Class
        if (member.getDateOfBirth().compareTo(atLeastEightTeen) > 0) { //if DOB comes after the date that makes someone 18 yrs old
            System.out.println("Must be at least 18 years old.");
            return false;
        }
        return true;
    }
    public boolean isValidCityName(Member member) {
        //     o An invalid city name, that is, the gym location doesn’t exist
       // boolean found = false;
        for (Location gymLocations : Location.values()) {
            if (member.getGymLocation() == gymLocations ) {
                return true;

            }
        }
            System.out.println("Gym location doesnt exist");
            return false;

    }
    public boolean isAlreadyInDatabase(Member member) {
        if (find(member) == -1) {
            return false;
        }
        return true;
    }
    public boolean isMembershipActive(Member member) {
        Date todayDate = new Date();
        if (todayDate.compareTo(member.getExpirationDate()) > 0) {
            System.out.println("Membership is expired");
            return false;
        }
        return true;
    }
    public static void main (String[] args ) {
        MemberDatabase memberDatabase = new MemberDatabase();
//        Member member = new Member("David", "Marr", new Date("2/12/2004"), new Date("3/12/2016"), Location.BRIDGEWATER);
//        Member member1 = new Member("Harry", "Marr", new Date("2/12/2004"), new Date("3/12/2016"), Location.BRIDGEWATER);
//        Member member2 = new Member("Ramon", "Marr", new Date("2/12/2004"), new Date("3/12/2016"), Location.BRIDGEWATER);
//        Member member3 = new Member("Luis", "Marr", new Date("2/12/2004"), new Date("3/12/2016"), Location.BRIDGEWATER);
//        Member member4 = new Member("Kendrick", "Marr", new Date("2/12/2004"), new Date("3/12/2016"), Location.BRIDGEWATER);

        Member member = new Member("Aohn", "Doe", new Date("1/2/2001"), new Date("3/30/2023"), Location.BRIDGEWATER);
        Member member1 = new Member("Celly", "Doe", new Date("1/2/2002"),new Date("3/30/2023"), Location.EDISON);
        Member member2 = new Member("Damon" ,"Doe" ,new Date("1/20/2002"), new Date("3/30/2023"), Location.PISCATAWAY);
        Member member3 = new Member("Steve" ,"Doe" ,new Date("1/20/2001"), new Date("3/30/2023"), Location.FRANKLIN);
        Member member4 = new Member("Howard" ,"Doe" ,new Date("2/10/2003"), new Date("3/30/2023"), Location.FRANKLIN);
        Member member5 = new Member("Tyrone" ,"Doe" ,new Date("4/2/2003"), new Date("3/30/2023"), Location.SOMERVILLE);
        Member member6 = new Member("Dazjah" ,"Doe" ,new Date("3/10/2003"), new Date("3/30/2023"), Location.SOMERVILLE);
        Member member7 = new Member("Sam" ,"Doe" ,new Date("3/15/2003"), new Date("3/30/2023"), Location.EDISON);
        Member member8 = new Member("Edward" ,"Doe" ,new Date("1/5/2003"), new Date("4/30/2023"), Location.EDISON);
        Member member9 = new Member("Holland" ,"Doe" ,new Date("4/3/2001"), new Date("4/10/2025"), Location.EDISON);
        Member member10 = new Member("Jose" ,"Doe" ,new Date("4/3/2003"), new Date("4/10/2025"), Location.BRIDGEWATER);
        Member member11 = new Member("David" ,"doe" ,new Date("4/3/2001"), new Date("2/10/2031"), Location.BRIDGEWATER);
        Member member12 = new Member("Caria" ,"doe" ,new Date("1/20/2003"), new Date("3/10/2031"), Location.SOMERVILLE);
        Member member13 = new Member("Borge" ,"Doe" ,new Date("1/20/2003"), new Date("2/10/2023"), Location.SOMERVILLE);
        Member member14 = new Member("Aean" ,"doe" ,new Date("1/20/2003"), new Date("2/10/2023"), Location.EDISON);


 //       Member member12 = new Member("Jane", "Doe",new Date("5/1/1996"),new Date("3/30/2023"), Location.ABC);
  //      Member member13 = new Member("Jane", "Doe",new Date("5/1/1996"),new Date("3/30/2023"), Location.edison);

//        Member member = new Member();
//        Member member = new Member();
//        Member member = new Member();
//        Member member = new Member();
//        Member member = new Member();
//        Member member = new Member();
//        Member member = new Member();
//        Member member = new Member();




//       System.out.println(memberDatabase.add(member));
//     //  System.out.println(memberDatabase.find(member));
//        System.out.println(memberDatabase.add(member1));
//        System.out.println(memberDatabase.add(member2));
//        System.out.println(memberDatabase.add(member3));
//        System.out.println(memberDatabase.add(member4));
//        System.out.println(memberDatabase.remove(member));
//        System.out.println(memberDatabase.remove(member1));
//        System.out.println(memberDatabase.remove(member2));
//        System.out.println(memberDatabase.remove(member3));
//        System.out.println(memberDatabase.remove(member4));
//        System.out.println(memberDatabase.remove(member));
        memberDatabase.add(member);
        memberDatabase.add(member2);
        memberDatabase.add(member3);
        memberDatabase.add(member4);
        memberDatabase.add(member5);
        memberDatabase.add(member6);
        memberDatabase.add(member7);
        memberDatabase.add(member8);
        memberDatabase.add(member9);
        memberDatabase.add(member10);
        memberDatabase.add(member11);
        memberDatabase.add(member12);
        memberDatabase.add(member13);
        memberDatabase.add(member14);





        memberDatabase.printByCounty();
    }
}

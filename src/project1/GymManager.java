package project1;

import java.util.Scanner;
/**
 User Interface class to process the command lines entered o the IDE console and display the results on the console.
 Starts with " Gym Manager running...” and ends with "Gym Manager terminated."
 @author Songyuan Zhang, Robert Jimenez
 */
public class GymManager {
    private MemberDatabase memberDatabase;
    private FitnessClass fitnessClassPilates;
    private FitnessClass fitnessClassSpinning;
    private FitnessClass fitnessClassCardio;
    /**
     Create a GymManager instance
     @param no parameter
     @return true if person was deleted, false otherwise.
     */
    public GymManager() {
        this.memberDatabase = new MemberDatabase();
        // create three fitness class objects, each for one fitness class
        this.fitnessClassPilates = new FitnessClass("Pilates", Time.MORNING,"JENNIFER");
        this.fitnessClassSpinning = new FitnessClass("Spinning", Time.AFTERNOON, "DENISE");
        this.fitnessClassCardio = new FitnessClass("Cardio", Time.AFTERNOON, "KIM");
    }

    private boolean checkValidCalendarDate(Member member) {
        //Any date that is not a valid calendar date
        if (!member.getDateOfBirth().isValid()) {
            System.out.println("DOB " + member.getDateOfBirth().getMonth() + "/" + member.getDateOfBirth().getDay() + "/" + member.getDateOfBirth().getYear() + ": invalid calendar date!");
            return false;
        }
        if (member.getExpirationDate().toString().equals("0/0/0")) {
            return true;
        }
        if (!member.getExpirationDate().isValid()) {
            System.out.println("Expiration date " + member.getExpirationDate().getMonth() + "/" + member.getExpirationDate().getDay() + "/" + member.getExpirationDate().getYear() + ": invalid calendar date!");
            return false;
        }
        return true;
    }

    private boolean checkTodayOrFutureDate(Date date) {
        Date today = new Date();
        int compareResult = today.compareTo(date);
        if (compareResult == Compare.EQUAL || compareResult == Compare.LESSTHAN) {
            return true;
        }
        return false;
    }
    //An invalid city name, that is, the gym location doesn’t exist
    private boolean checkValidGymLocation(String inputString) {
        for (Location gymLocations : Location.values()) {
            if (inputString.equalsIgnoreCase(gymLocations.name()))
                return true;
        }
        System.out.println(inputString + ": invalid location!");
        return false;
    }

    // A command, to add a member to the member database
    private void addMember(String[] results, int countParameters, String commandLineString) {
        if (countParameters != 6) {
            System.out.println(commandLineString + "is a invalid command!");
            return;
        }
        if (!checkValidGymLocation(results[5]))
            return;
        Member newMember = new Member(results[1], results[2], new Date(results[3]), new Date(results[4]), Location.valueOf(results[5].toUpperCase()));
        Date memberDob = newMember.getDateOfBirth();
        if (!this.checkValidCalendarDate(newMember)) {
            return;
        }
        if (this.checkTodayOrFutureDate(newMember.getDateOfBirth())) {
            System.out.println("DOB " + memberDob.getMonth() + "/" + memberDob.getDay() + "/" + memberDob.getYear() + ": cannot be today or a future date!");
            return;
        }
        if (!(this.memberDatabase.checkValidMemberAge(newMember))) {
            System.out.println("DOB " + memberDob.getMonth() + "/" + memberDob.getDay() + "/" + memberDob.getYear() + ": must be 18 or older to join!");
            return;
        }
        if (this.memberDatabase.add(newMember)) {
            System.out.println(newMember.getFirstName() + " " + newMember.getLastName() +  " added.");
        } else {
            System.out.println(newMember.getFirstName() + " " + newMember.getLastName() + " is already in database.");
        }
    }
    //R command, to cancel the membership and remove the specified member from the database
    private void cancelMember(String[] results, int countParameters, String commandLineString) {
        if (countParameters != 4) {
            System.out.println(commandLineString + "is a invalid command!");
            return;
        }
        if (this.memberDatabase.remove(new Member(results[1], results[2], new Date(results[3]), new Date("00/00/0000"), Location.EDISON))) {
            //Date is set to a random date and locatin is set to EDISION by default
            System.out.println(results[1] + " " + results[2] + " removed.");
            return;
        }
        System.out.println(results[1] + " " + results[2] + " is not in the database.");
    }
    //P command,  to display the list of members in the database without sorting (current order in the array.)
    private void listMember(String[] results, int countParameters, String commandLineString) {
        if (countParameters != 1) {
            System.out.println(commandLineString + " is a invalid command!");
            return;
        }
        this.memberDatabase.print();
    }
    //PC command, to display the list of members in the database ordered by the county
    private void listMemberByCounty(String[] results, int countParameters, String commandLineString) {
        if (countParameters != 1) {
            System.out.println(commandLineString + "is a invalid command!");
            return;
        }
        if (this.memberDatabase.checkEmptyStudentList()) {
            System.out.println("Member database is empty!");
            return;
        }
        System.out.println();
        System.out.println("-list of members sorted by county and zipcode-");
        this.memberDatabase.printByCounty();
        System.out.println("-end of list-");
        System.out.println();
    }
    //PN command, display the list of members in the database ordered by the members names
    private void listMemberByName(String[] results, int countParameters, String commandLineString) {
        if (countParameters != 1) {
            System.out.println(commandLineString + "is a invalid command!");
            return;
        }
        if (this.memberDatabase.checkEmptyStudentList()) {
            System.out.println("Member database is empty!");
            return;
        }
        System.out.println();
        System.out.println("-list of members sorted by last name, and first name-");
        this.memberDatabase.printByName();
        System.out.println("-end of list-");
        System.out.println();
    }
    //PD command, display the list of members in the database ordered by the expiration dates.
    private void listMemberByExpirationDate(String[] results, int countParameters, String commandLineString) {
        if (countParameters != 1) {
            System.out.println(commandLineString + "is a invalid command!");
            return;
        }
        if (this.memberDatabase.checkEmptyStudentList()) {
            System.out.println("Member database is empty!");
            return;
        }
        System.out.println();
        System.out.println("-list of members sorted by membership expiration date-");
        this.memberDatabase.printByExpirationDate();
        System.out.println("-end of list-");
        System.out.println();
    }
    private void printNonEmptyStudentList() {
        System.out.println();
        System.out.println("-Fitness classes-");
        System.out.println(fitnessClassPilates);
        System.out.println("    ** participants **");
        Member[] studentList = fitnessClassPilates.getStudentList();
        for (Member student: studentList) {
            if (student == null)
                break;
            System.out.println("        " + student);
        }
        studentList = fitnessClassSpinning.getStudentList();
        System.out.println(fitnessClassSpinning);
        System.out.println("    ** participants **");
        for (Member student: studentList) {
            if (student == null)
                break;
            System.out.println("        " + student);
        }
        studentList = fitnessClassCardio.getStudentList();
        System.out.println(fitnessClassCardio);
        System.out.println("    ** participants **");
        for (Member student: studentList) {
            if (student == null)
                break;
            System.out.println("        " + student);
        }
        System.out.println();
    }
    //S command, display the fitness class schedule.
    private void getFitnessClassSchedule(String[] results, int countParameters, String commandLineString) {
        if (countParameters != 1) {
            System.out.println(commandLineString + "is a invalid command!");
            return;
        }
        if (this.fitnessClassPilates.checkEmptyStudentList() && this.fitnessClassSpinning.checkEmptyStudentList()
                && this.fitnessClassCardio.checkEmptyStudentList()) {
            System.out.println();
            System.out.println("-Fitness classes-");
            System.out.println(fitnessClassPilates);
            System.out.println(fitnessClassSpinning);
            System.out.println(fitnessClassCardio);
            System.out.println();
            return;
        }
        printNonEmptyStudentList();
    }
    private boolean checkInSpecificFitnessClass(String className, Member newMember) {
        if (className.equalsIgnoreCase("Pilates")) {
            if (this.fitnessClassPilates.checkInClass(newMember))
                return true;
        }
        if (className.equalsIgnoreCase("Spinning")) {
            if (this.fitnessClassSpinning.checkInClass(newMember))
                return true;
        }
        if (className.equalsIgnoreCase("Cardio")) {
            if (this.fitnessClassCardio.checkInClass(newMember))
                return true;
        }
        return false;
    }

    private boolean checkTimeConflict(String className, Member newMember) {
        if (className.equalsIgnoreCase("Spinning") && (this.fitnessClassCardio.checkTimeConflict(newMember))) {
            System.out.println("Spinning time conflict -- " + newMember.getFirstName() + " " + newMember.getLastName() + " has already checked in Cardio.");
            return true;
        }
        if (className.equalsIgnoreCase("Cardio") && (this.fitnessClassSpinning.checkTimeConflict(newMember))) {
            System.out.println("Cardio time conflict -- " + newMember.getFirstName() + " " + newMember.getLastName() + " has already checked in Spinning.");
            return true;
        }
        return false;
    }
    public boolean checkExistanceInDatabase(Member member) {
        if(!this.memberDatabase.isInDatabase(member)) {
            System.out.println(member.getFirstName() + " " + member.getLastName() + " " + member.getDateOfBirth().toString() + " is not in the database.");
            return false;
        }
        return true;
    }
    //C command, for members check-in to a fitness class
    private void checkInFitnessClass(String[] results, int countParameters, String commandLineString) {
        if(countParameters != 5) {
            System.out.println(commandLineString + " is a invalid command!");
            return;
        }
        if (!(results[1].equalsIgnoreCase("Pilates") || results[1].equalsIgnoreCase("Spinning") || results[1].equalsIgnoreCase("Cardio"))) {
            System.out.println(results[1] + " class does not exist.");
            return;
        }
        Member newMember = new Member(results[2], results[3], new Date(results[4]), new Date("00/00/0000"), Location.EDISON);
        if (!this.checkValidCalendarDate(newMember)) { //check dob is a valid date
            return;
        }
        if(!this.checkExistanceInDatabase(newMember)) {
            return;
        }
        newMember = this.memberDatabase.addExpirationDateAndLocation(newMember);
        if (this.checkTimeConflict(results[1], newMember))
            return;
        if (this.memberDatabase.checkExpirationDate(newMember)) { //check whether member has expired
            System.out.println(results[2] + " " + results[3] + " " + newMember.getDateOfBirth().toString() + " membership is expired");
            return;
        }
        if (this.checkInSpecificFitnessClass(results[1], newMember)) {
            System.out.println(newMember.getFirstName() + " " + newMember.getLastName() + " checked in "
                    + results[1].substring(0, 1).toUpperCase() + results[1].substring(1) + ".");
            return;
        }
        System.out.println(newMember.getFirstName() + " " + newMember.getLastName() + " has already checked in "
                + results[1].substring(0, 1).toUpperCase() + results[1].substring(1) + ".");
    }
    private boolean dropSpecificFitnessClass(String className, Member newMember) {
        if (className.equalsIgnoreCase("Pilates")) {
            if (this.fitnessClassPilates.dropClass(newMember)) {
                System.out.println(newMember.getFirstName() + " " + newMember.getLastName() + " dropped "
                        + className.substring(0, 1).toUpperCase() + className.substring(1) + ".");
                return true;
            }
        }
        if (className.equalsIgnoreCase("Spinning")) {
            if (this.fitnessClassSpinning.dropClass(newMember)) {
                System.out.println(newMember.getFirstName() + " " + newMember.getLastName() + " dropped "
                        + className.substring(0, 1).toUpperCase() + className.substring(1) + ".");
                return true;
            }
        }
        if (className.equalsIgnoreCase("Cardio")) {
            if (this.fitnessClassCardio.dropClass(newMember)) {
                System.out.println(newMember.getFirstName() + " " + newMember.getLastName() + " dropped "
                        + className.substring(0, 1).toUpperCase() + className.substring(1) + ".");
                return true;
            }
        }
        return false;
    }
    //D command, to drop the fitness classes after the member checked in to a class
    private void dropFitnessClass(String[] results, int countParameters, String commandLineString) {
        if(countParameters != 5) {
            System.out.println(commandLineString + "is a invalid command!");
            return;
        }
        if (!(results[1].equalsIgnoreCase("Pilates") || results[1].equalsIgnoreCase("Spinning") || results[1].equalsIgnoreCase("Cardio"))) {
            System.out.println(results[1] + " class does not exist.");
            return;
        }
        Member newMember = new Member(results[2], results[3], new Date(results[4]), new Date("00/00/0000"), Location.EDISON);
        if (!this.checkValidCalendarDate(newMember)) { //check dob is a valid date
            return;
        }
        if(!this.memberDatabase.isInDatabase(newMember)) {
            System.out.println(newMember.getFirstName() + " " + newMember.getLastName() + " is not a participant in "
                    + results[1].substring(0, 1).toUpperCase() + results[1].substring(1) + ".");
            return;
        }
        newMember = this.memberDatabase.addExpirationDateAndLocation(newMember);
        if (dropSpecificFitnessClass(results[1], newMember))
            return;
        System.out.println(newMember.getFirstName() + " " + newMember.getLastName() + " is not a participant in "
                + results[1].substring(0, 1).toUpperCase() + results[1].substring(1) + ".");
    }
    //Q command, to stop the program execution
    private boolean checkQCommand(String[] results, int countParameters, String commandLineString) {
        if(countParameters != 1) {
            System.out.println(commandLineString + "is a invalid command!");
            return false;
        }
        return true;
    }
    public void run() {
        System.out.println("Gym Manager running...");
        int countParameters = 0;
        Scanner scan = new Scanner(System.in);
        while (true) {
            String commandLineString = scan.nextLine();
            if (commandLineString == "") continue;
            String[] results = commandLineString.split("\\s");
            for (int x = 0; x < results.length; x++)
                countParameters ++;
            if (results[0].equals("A"))
                this.addMember(results, countParameters, commandLineString);
            else if (results[0].equals("R"))
                this.cancelMember(results, countParameters, commandLineString);
            else if (results[0].equals("P"))
                this.listMember(results, countParameters, commandLineString);
            else if (results[0].equals("PC"))
                this.listMemberByCounty(results, countParameters, commandLineString);
            else if (results[0].equals("PN"))
                this.listMemberByName(results, countParameters, commandLineString);
            else if (results[0].equals("PD"))
                this.listMemberByExpirationDate(results, countParameters, commandLineString);
            else if (results[0].equals("S"))
                this.getFitnessClassSchedule(results, countParameters, commandLineString);
            else if (results[0].equals("C"))
                this.checkInFitnessClass(results, countParameters, commandLineString);
            else if (results[0].equals("D"))
                this.dropFitnessClass(results, countParameters, commandLineString);
            else if (results[0].equals("Q") && checkQCommand(results, countParameters, commandLineString))
                break;
            else
                System.out.println(commandLineString + " is a invalid command!");
            countParameters = 0;
        }
        scan.close();
        System.out.println("Gym Manager terminated");
    }
}

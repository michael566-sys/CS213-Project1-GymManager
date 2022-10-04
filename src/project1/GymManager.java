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
     Create a GymManager object.
     Initialize one MemberDatabase object and three FitnessClass objects for each Pilates, Spinning, and Cardio.
     fitness classes.
     */
    public GymManager() {
        this.memberDatabase = new MemberDatabase();
        // create three fitness class objects, each for one fitness class
        this.fitnessClassPilates = new FitnessClass("Pilates", Time.MORNING,"JENNIFER");
        this.fitnessClassSpinning = new FitnessClass("Spinning", Time.AFTERNOON, "DENISE");
        this.fitnessClassCardio = new FitnessClass("Cardio", Time.AFTERNOON, "KIM");
    }
    /**
     Check valid calendar date for member's date of birth or expiration date.
     Extracts the dates from member and use isValid method from Dateclass to check whether date is valid.
     @param member the member with date of birth or expiration date to check valid calendar date.
     @return true if the date is a valid calendar date, false otherwise.
     */
    private boolean checkValidCalendarDate(Member member) {
        //Any date that is not a valid calendar date
        if (!member.getDateOfBirth().isValid()) {
            System.out.println("DOB " + member.getDateOfBirth().getMonth() + "/" + member.getDateOfBirth().getDay()
                    + "/" + member.getDateOfBirth().getYear() + ": invalid calendar date!");
            return false;
        }
        if (member.getExpirationDate().toString().equals("0/0/0")) {
            return true;
        }
        if (!member.getExpirationDate().isValid()) {
            System.out.println("Expiration date " + member.getExpirationDate().getMonth() + "/"
                    + member.getExpirationDate().getDay() + "/" + member.getExpirationDate().getYear()
                    + ": invalid calendar date!");
            return false;
        }
        return true;
    }
    /**
     Check the date is today or future date.
     Compare the results against today's date.
     @param date the date to check it is today or future date.
     @return true if the date is today or future date, false otherwise.
     */
    private boolean checkTodayOrFutureDate(Date date) {
        Date today = new Date();
        int compareResult = today.compareTo(date);
        if (compareResult == Compare.EQUAL || compareResult == Compare.LESSTHAN) {
            return true;
        }
        return false;
    }
    /**
     Check whether the input location is a valid gym location.
     Check if the location exists in Location enum class.
     @param inputString the string of the name of the gym location
     @return true if the date is today or future date, false otherwise.
     */
    private boolean checkValidGymLocation(String inputString) {
        for (Location gymLocations : Location.values()) {
            if (inputString.equalsIgnoreCase(gymLocations.name()))
                return true;
        }
        System.out.println(inputString + ": invalid location!");
        return false;
    }

    /**
     Add a valid member into the MemberDatabase.
     Add a member who has valid date of birth, age, member expiration date, and gym location.
     Correpsonds to the A command.
     @param results string array that contains the command line instruction tokens.
     @param countTokenNumber count for the number of tokens of the command line instruction.
     @param commandLineString string of the original command line instruction.
     */
    private void addMember(String[] results, int countTokenNumber, String commandLineString) {
        if (countTokenNumber != 6) {
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

    /**
     Cancel a member from the MemberDatabase.
     Remove a member who exists in the MemberDatabase. Corresponds to the R command.
     @param results string array that contains the command line instruction tokens.
     @param countTokenNumber count for the number of tokens of the command line instruction.
     @param commandLineString string of the original command line instruction.
     */
    private void cancelMember(String[] results, int countTokenNumber, String commandLineString) {
        if (countTokenNumber != 4) {
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
    /**
     Print all members from the MemberDatabase.
     Display the list of members in the database without sorting (current order in the array.)
     Corresponds to the P command.
     @param results string array that contains the command line instruction tokens.
     @param countTokenNumber count for the number of tokens of the command line instruction.
     @param commandLineString string of the original command line instruction.
     */
    private void listMember(String[] results, int countTokenNumber, String commandLineString) {
        if (countTokenNumber != 1) {
            System.out.println(commandLineString + " is a invalid command!");
            return;
        }
        this.memberDatabase.print();
    }
    /**
     Print all members from the MemberDatabase order by the county names and then the zip codes.
     If the locations are in the same county, ordered by the zip codes. Corresponds to the PC command.
     @param results string array that contains the command line instruction tokens.
     @param countTokenNumber count for the number of tokens of the command line instruction.
     @param commandLineString string of the original command line instruction.
     */
    private void listMemberByCounty(String[] results, int countTokenNumber, String commandLineString) {
        if (countTokenNumber != 1) {
            System.out.println(commandLineString + "is a invalid command!");
            return;
        }
        if (this.memberDatabase.checkEmptyDatebase()) {
            System.out.println("Member database is empty!");
            return;
        }
        System.out.println();
        System.out.println("-list of members sorted by county and zipcode-");
        this.memberDatabase.printByCounty();
        System.out.println("-end of list-");
        System.out.println();
    }
    /**
     Print the list of members in the database ordered by the members’ last names and then first names.
     If two members have the same last name, ordered by the first name.
     Corresponds to the PN command.
     @param results string array that contains the command line instruction tokens.
     @param countTokenNumber count for the number of tokens of the command line instruction.
     @param commandLineString string of the original command line instruction.
     */
    private void listMemberByName(String[] results, int countTokenNumber, String commandLineString) {
        if (countTokenNumber != 1) {
            System.out.println(commandLineString + "is a invalid command!");
            return;
        }
        if (this.memberDatabase.checkEmptyDatebase()) {
            System.out.println("Member database is empty!");
            return;
        }
        System.out.println();
        System.out.println("-list of members sorted by last name, and first name-");
        this.memberDatabase.printByName();
        System.out.println("-end of list-");
        System.out.println();
    }
    /**
     Print the list of members in the database ordered by the expiration dates.
     If two expiration dates are the same, their order doesn’t matter.
     Corresponds to the PD command.
     @param results string array that contains the command line instruction tokens.
     @param countTokenNumber count for the number of tokens of the command line instruction.
     @param commandLineString string of the original command line instruction.
     */
    private void listMemberByExpirationDate(String[] results, int countTokenNumber, String commandLineString) {
        if (countTokenNumber != 1) {
            System.out.println(commandLineString + "is a invalid command!");
            return;
        }
        if (this.memberDatabase.checkEmptyDatebase()) {
            System.out.println("Member database is empty!");
            return;
        }
        System.out.println();
        System.out.println("-list of members sorted by membership expiration date-");
        this.memberDatabase.printByExpirationDate();
        System.out.println("-end of list-");
        System.out.println();
    }
    /**
     Print the fitness class schedule together with students' information.
     Called by listFitnessClassSchedule method if the student lists in classes are
     not empty.
     */
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
    /**
     Print the fitness class schedule.
     A fitness class shall include the fitness class name, instructor’s name,
     the time of the class, and the list of members who have already checked in today.
     Corresponds to the S command.
     @param results string array that contains the command line instruction tokens.
     @param countTokenNumber count for the number of tokens of the command line instruction.
     @param commandLineString string of the original command line instruction.
     */
    private void listFitnessClassSchedule(String[] results, int countTokenNumber, String commandLineString) {
        if (countTokenNumber != 1) {
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
    /**
     Check into a fitness class for a member for time conflict with other fitness classes.
     Called by checkInFitnessClass method to perform the check in actin after all the validities
     has been verified at the checkInFitnessClass method.
     @param className string that shows the name of the class that the new member wants to check in.
     @param member the member who wants to check into a specific class.
     @return true if check in is successful, false otherwise.
     */
    private boolean checkInSpecificFitnessClass(String className, Member member) {
        if (className.equalsIgnoreCase("Pilates")) {
            if (this.fitnessClassPilates.checkInClass(member))
                return true;
        }
        if (className.equalsIgnoreCase("Spinning")) {
            if (this.fitnessClassSpinning.checkInClass(member))
                return true;
        }
        if (className.equalsIgnoreCase("Cardio")) {
            if (this.fitnessClassCardio.checkInClass(member))
                return true;
        }
        return false;
    }
    /**
     Check for time conflict with other fitness classes.
     Called by checkInFitnessClass method to check for time conflict with other fitness classes
     for a member.
     @param className string that shows the name of the class that the member wants to check in.
     @param member the member who wants to check into a specific class.
     @return true if there is a time conflict, false otherwise.
     */
    private boolean checkTimeConflict(String className, Member member) {
        if (className.equalsIgnoreCase("Spinning") && (this.fitnessClassCardio.checkExistence(member))) {
            System.out.println("Spinning time conflict -- " + member.getFirstName() + " " + member.getLastName() + " has already checked in Cardio.");
            return true;
        }
        if (className.equalsIgnoreCase("Cardio") && (this.fitnessClassSpinning.checkExistence(member))) {
            System.out.println("Cardio time conflict -- " + member.getFirstName() + " " + member.getLastName() + " has already checked in Spinning.");
            return true;
        }
        return false;
    }
    /**
     Check whether a member exists in the MemberDatabase.
     Called by checkInFitnessClass method to verify if the member exists in the MemberDatabase.
     @param member the member who wants to check into a specific class.
     @return true if member is already in the database, false otherwise.
     */
    private boolean checkExistanceInDatabase(Member member) {
        if(!this.memberDatabase.isInDatabase(member)) {
            System.out.println(member.getFirstName() + " " + member.getLastName() + " " + member.getDateOfBirth().toString() + " is not in the database.");
            return false;
        }
        return true;
    }
    /**
     Check into a fitness class.
     A member checks into a fitness class: Pilates, Spinning, or Cardio.
     Checks whether the member exists in the class, the date of birth is invalid,
     the fitness class exist or not, whether there is a time conflict with other fitness classes,
     or whether a member has already check into the class.
     Corresponds to the C command.
     @param results an array of strings that contains the command line instruction tokens.
     @param countTokenNumber count for the number of tokens of the command line instruction.
     @param commandLineString string of the original command line instruction.
     */
    private void checkInFitnessClass(String[] results, int countTokenNumber, String commandLineString) {
        if(countTokenNumber != 5) {
            System.out.println(commandLineString + " is a invalid command!");
            return;
        }
        if (!(results[1].equalsIgnoreCase("Pilates") || results[1].equalsIgnoreCase("Spinning") || results[1].equalsIgnoreCase("Cardio"))) {
            System.out.println(results[1] + " class does not exist.");
            return;
        }
        Member newStudent = new Member(results[2], results[3], new Date(results[4]), new Date("00/00/0000"), Location.EDISON);
        if (!this.checkValidCalendarDate(newStudent)) { //check dob is a valid date
            return;
        }
        if(!this.checkExistanceInDatabase(newStudent)) {
            return;
        }
        newStudent = this.memberDatabase.addExpirationDateAndLocation(newStudent);
        if (this.checkTimeConflict(results[1], newStudent))
            return;
        if (this.memberDatabase.checkExpirationDate(newStudent)) { //check whether member has expired
            System.out.println(results[2] + " " + results[3] + " " + newStudent.getDateOfBirth().toString() + " membership is expired");
            return;
        }
        if (this.checkInSpecificFitnessClass(results[1], newStudent)) {
            System.out.println(newStudent.getFirstName() + " " + newStudent.getLastName() + " checked in "
                    + results[1].substring(0, 1).toUpperCase() + results[1].substring(1) + ".");
            return;
        }
        System.out.println(newStudent.getFirstName() + " " + newStudent.getLastName() + " has already checked in "
                + results[1].substring(0, 1).toUpperCase() + results[1].substring(1) + ".");
    }
    /**
     Drop a member from a specific fitness class.
     Called by dropFitnessClass method to perform the action of dropping the
     member from the class: Pilates, Spinning, or Cardio fitness class.
     @param className the class name that the member wants to drop from
     @param member the member who wants to drop from the fitness class.
     @return true if member is successfully dropped from the class, false otherwise.
     */
    private boolean dropSpecificFitnessClass(String className, Member member) {
        if (className.equalsIgnoreCase("Pilates")) {
            if (this.fitnessClassPilates.dropClass(member)) {
                System.out.println(member.getFirstName() + " " + member.getLastName() + " dropped "
                        + className.substring(0, 1).toUpperCase() + className.substring(1) + ".");
                return true;
            }
        }
        if (className.equalsIgnoreCase("Spinning")) {
            if (this.fitnessClassSpinning.dropClass(member)) {
                System.out.println(member.getFirstName() + " " + member.getLastName() + " dropped "
                        + className.substring(0, 1).toUpperCase() + className.substring(1) + ".");
                return true;
            }
        }
        if (className.equalsIgnoreCase("Cardio")) {
            if (this.fitnessClassCardio.dropClass(member)) {
                System.out.println(member.getFirstName() + " " + member.getLastName() + " dropped "
                        + className.substring(0, 1).toUpperCase() + className.substring(1) + ".");
                return true;
            }
        }
        return false;
    }
    /**
     Drop a member from a specific fitness class.
     Drop the fitness classes after the member checked in to a class.
     Corresponds to the D command.
     @param results an array of strings that contains the command line instruction tokens.
     @param countTokenNumber count for the number of tokens of the command line instruction.
     @param commandLineString string of the original command line instruction.
     */
    //D command, to drop the fitness classes after the member checked in to a class
    private void dropFitnessClass(String[] results, int countTokenNumber, String commandLineString) {
        if(countTokenNumber != 5) {
            System.out.println(commandLineString + "is a invalid command!");
            return;
        }
        if (!(results[1].equalsIgnoreCase("Pilates") || results[1].equalsIgnoreCase("Spinning") || results[1].equalsIgnoreCase("Cardio"))) {
            System.out.println(results[1] + " class does not exist.");
            return;
        }
        Member student = new Member(results[2], results[3], new Date(results[4]), new Date("00/00/0000"), Location.EDISON);
        if (!this.checkValidCalendarDate(student)) { //check dob is a valid date
            return;
        }
        if(!this.memberDatabase.isInDatabase(student)) {
            System.out.println(student.getFirstName() + " " + student.getLastName() + " is not a participant in "
                    + results[1].substring(0, 1).toUpperCase() + results[1].substring(1) + ".");
            return;
        }
        student = this.memberDatabase.addExpirationDateAndLocation(student);
        if (dropSpecificFitnessClass(results[1], student))
            return;
        System.out.println(student.getFirstName() + " " + student.getLastName() + " is not a participant in "
                + results[1].substring(0, 1).toUpperCase() + results[1].substring(1) + ".");
    }
    /**
     Stop the program execution.
     Display "Gym Manager terminated." Corresponds to the Q command.
     @param results an array of strings that contains the command line instruction tokens.
     @param countTokenNumber count for the number of tokens of the command line instruction.
     @param commandLineString string of the original command line instruction.
     @return true if the command is valid and program can be terminated, false otherwise
     */
    private boolean checkQCommand(String[] results, int countTokenNumber, String commandLineString) {
        if(countTokenNumber != 1) {
            System.out.println(commandLineString + "is a invalid command!");
            return false;
        }
        return true;
    }
    /**
     Run the GymManager to receive and execute command line instructions.
     Use scanner to continuously take in command line instructions.
     Terminates when the Q command is received.
     */
    public void run() {
        System.out.println("Gym Manager running...");
        int countTokenNumber = 0;
        Scanner scan = new Scanner(System.in);
        while (true) {
            String commandLineString = scan.nextLine();
            if (commandLineString == "") continue;
            String[] results = commandLineString.split("\\s");
            for (int x = 0; x < results.length; x++)
                countTokenNumber ++;
            if (results[0].equals("A"))
                this.addMember(results, countTokenNumber, commandLineString);
            else if (results[0].equals("R"))
                this.cancelMember(results, countTokenNumber, commandLineString);
            else if (results[0].equals("P"))
                this.listMember(results, countTokenNumber, commandLineString);
            else if (results[0].equals("PC"))
                this.listMemberByCounty(results, countTokenNumber, commandLineString);
            else if (results[0].equals("PN"))
                this.listMemberByName(results, countTokenNumber, commandLineString);
            else if (results[0].equals("PD"))
                this.listMemberByExpirationDate(results, countTokenNumber, commandLineString);
            else if (results[0].equals("S"))
                this.listFitnessClassSchedule(results, countTokenNumber, commandLineString);
            else if (results[0].equals("C"))
                this.checkInFitnessClass(results, countTokenNumber, commandLineString);
            else if (results[0].equals("D"))
                this.dropFitnessClass(results, countTokenNumber, commandLineString);
            else if (results[0].equals("Q") && checkQCommand(results, countTokenNumber, commandLineString))
                break;
            else
                System.out.println(commandLineString + " is a invalid command!");
            countTokenNumber = 0;
        }
        scan.close();
        System.out.println("Gym Manager terminated");
    }
}

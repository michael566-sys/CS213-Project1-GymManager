package project1;

import java.util.Scanner;
import java.util.StringTokenizer;

public class GymManager {
    private MemberDatabase memberDatabase;
    private FitnessClass fitnessClassPilates;
    private FitnessClass fitnessClassSpinning;
    private FitnessClass fitnessClassCardio;

    public GymManager() {
        this.memberDatabase = new MemberDatabase();
        // create three fitness class objects, each for one fitness class
        this.fitnessClassPilates = new FitnessClass("Pilates", Time.PILATESCLASSTIME,"Jennifer");
        this.fitnessClassSpinning = new FitnessClass("SPINNING", Time.SPINNINGCLASSTIME, "Denise");
        this.fitnessClassCardio = new FitnessClass("CARDIO", Time.CARDIOCLASSTIME, "Kim");
    }

    private void addMember(String[] results, int countParameters, String commandLineString) {
        System.out.println("entered addMember");
        if (countParameters != 5) {
            System.out.println(commandLineString + "is a invalid command!");
            return;
        }
        this.memberDatabase.add(new Member(results[1], results[2], results[3], results[4], Location.valueOf(results[5])));
    }

    private void cancelMember(String[] results, int countParameters, String commandLineString) {
        System.out.println("entered cancelMember");
        if (countParameters != 4) {
            System.out.println(commandLineString + "is a invalid command!");
        }
        this.memberDatabase.remove(new Member(results[1], results[2], results[3], "", Location.valueOf(results[4])));
    }

    private void listMember(String[] results, int countParameters, String commandLineString) {
        System.out.println("entered listMember");
        if (countParameters != 0) {
            System.out.println(commandLineString + "is a invalid command!");
        }
        this.memberDatabase.print();
    }

    private void listMemberByCounty(String[] results, int countParameters, String commandLineString) {
        System.out.println("entered listMemberByCounty");
        if (countParameters != 0) {
            System.out.println(commandLineString + "is a invalid command!");
        }
        this.memberDatabase.printByCounty();
    }

    private void listMemberByName(String[] results, int countParameters, String commandLineString) {
        System.out.println("entered listMemberByName");
        if (countParameters != 0) {
            System.out.println(commandLineString + "is a invalid command!");
        }
        this.memberDatabase.printByName();
    }

    private void listMemberByExpirationDate(String[] results, int countParameters, String commandLineString) {
        System.out.println("entered listMemberByExpirationDate");
        if(countParameters !=0)
        {
            System.out.println(commandLineString + "is a invalid command!");
        }
        this.memberDatabase.printByExpirationDate();
    }
    private void getFitnessClassSchedule(String[] results, int countParameters, String commandLineString) {
        System.out.println("entered getFitnessClassSchedule");
        if(countParameters !=0)
        {
            System.out.println(commandLineString + "is a invalid command!");
        }
        this.memberDatabase.printByExpirationDate();
    }
    private void checkInFitnessClass(String[] results, int countParameters, String commandLineString) {
        System.out.println("entered checkInFitnessClass");
        if(countParameters != 0)
        {
            System.out.println(commandLineString + "is a invalid command!");
        }
    }
    private void dropFitnessClass(String[] results, int countParameters, String commandLineString) {
        System.out.println("entered dropFitnessClass");
        if(countParameters != 0)
        {
            System.out.println(commandLineString + "is a invalid command!");
        }
        this.memberDatabase.printByExpirationDate();
    }
    private boolean checkQCommand(String[] results, int countParameters, String commandLineString) {
        System.out.println("entered checkQCommand");
        if(countParameters != 1) {
            System.out.println(commandLineString + "is a invalid command!");
            return false;
        }
        return true;
    }

    public void run() {
        System.out.println("Gym Manager running...");
        char option = ' ';
        int countParameters = 0;
        Scanner scan = new Scanner(System.in);
        //create a new instance of
        while (option != 'D') {
            // Using Scanner for Getting Input from User
            String commandLineString = scan.nextLine();
            String[] results = commandLineString.split("\\s");
            for (int x = 0; x < results.length; x++) {
//                System.out.println(results[x]);
                countParameters ++;
            }
            if (results[0].equals("A"))
                    addMember(results, countParameters, commandLineString);
            else if (results[0].equals("R"))
                    cancelMember(results, countParameters, commandLineString);
            else if (results[0].equals("P"))
                    listMember(results, countParameters, commandLineString);
            else if (results[0].equals("PC"))
                    listMemberByCounty(results, countParameters, commandLineString);
            else if (results[0].equals("PN"))
                    listMemberByName(results, countParameters, commandLineString);
            else if (results[0].equals("PD"))
                    listMemberByExpirationDate(results, countParameters, commandLineString);
            else if (results[0].equals("S"))
                    getFitnessClassSchedule(results, countParameters, commandLineString);
            else if (results[0].equals("C"))
                    checkInFitnessClass(results, countParameters, commandLineString);
            else if (results[0].equals("D"))
                    dropFitnessClass(results, countParameters, commandLineString);
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

package project1;

public class FitnessClass {
    private String className;
    private Time startTime;
    private String instructor;
    private Member[] studentList;
    private  int size;

    public FitnessClass(String className, Time startTime, String instructor) {
        this.className = className;
        this.startTime = startTime;
        this.size = 0;
        studentList = new Member[4];
        this.instructor = instructor;
    }

    public boolean checkInClass(Member member){
        Date todaysDate = new Date();
        if (todaysDate.compareTo(member.getExpirationDate()) > 0) { //if expiration date comes after todays date
            System.out.println("expiration date is outdated");
            return false;
        }
        if (find(member) == -1) { //if member is not checked in
            if ((this.size + 1) > getCapacity() ) {
                System.out.println("Array too big use grow");
                grow();  //increases the database if it doesnt.
                studentList[size] = member;
                size++;
                return true;
            } else {
                System.out.println("Array is fine added");
                studentList[size] = member;
                size++;
                return true;
            }


        }
        System.out.println(member + " is already checked in");
        return false;
    }
    private void grow() { //Method to grow capacity by 4 into new array copying old array elements
        Member[]  oldLst = studentList; //Temp array to hold old array
        int newCapacity = getCapacity() + 4;
        studentList = new Member[newCapacity]; // new array with 4 more capacity

        for (int x = 0; x < oldLst.length; x++) { //copy old array elements into new array
            studentList[x] = oldLst[x];
        }
    }
    public boolean conflictingTimes(Member[] sLst) { // if (class.getStartTime == class2.getStartTime && class1.getStudentlist.conflictingTimes(class2.getSLst))
        boolean conflictingTimes = false;
        for (Member m: studentList){
            for (Member pM: sLst ) {
                if (m.equals(pM)) {
                  conflictingTimes = true;
                  break;
                }
            }
        }
        return conflictingTimes;
    }
    public void printMembersCheckedIn() {
        for (Member m:this.studentList) {
            System.out.println(m);
        }
    }
    public int find(Member member) {
 //       System.out.println("Seraching.");
        for (int i = 0; i < this.size; i++) { // Checks if member is in database
            if (member.equals(this.studentList[i])) {
                return i; //returns index is member is in array
            }
        }
        return -1; //returns "NOT FOUND" if not
    }

    public Time getStartTime() {
        return this.startTime;
    }
    private int getCapacity() {
        return studentList.length;
    }

    public String getClassName() {
        return this.className;
    }
    public Member[] getStudentList() {
        return this.studentList;
    }
    public static void main(String[] args) {
        FitnessClass fitnessClassSpinning = new FitnessClass("SPINNING", Time.SPINNINGCLASSTIME, "Denise");
        FitnessClass fitnessClassCardio = new FitnessClass("CARDIO", Time.CARDIOCLASSTIME, "Kim");
        Member David = new Member("David", "Marr", new Date("2/12/2016"), new Date("3/12/2023"), Location.BRIDGEWATER);
      //  Member David1 = new Member("David", "Marr", new Date("2/12/2016"), new Date("3/12/2023"), Location.BRIDGEWATER);

        System.out.println(fitnessClassSpinning.checkInClass(David));
        System.out.println(fitnessClassSpinning.find(David));
        System.out.println(fitnessClassSpinning.checkInClass(David));


        fitnessClassSpinning.printMembersCheckedIn();
    }
}

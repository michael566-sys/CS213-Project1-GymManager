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
        if (find(member) == -1) { //if member is not checked in
            if ((this.size + 1) > getCapacity() ) { //checks if database has enough room for another member
                grow();  //increases the database if it doesnt.
                studentList[size] = member;
                size++;
                return true;
            } else {
                studentList[size] = member;
                size++;
                return true;
            }


        }
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
        for (int i = 0; i < this.size - 1;i++) { // Checks if member is in database
            if (studentList[i].compareTo(member) == 0) {
                return i; //returns index is member is in database
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
}

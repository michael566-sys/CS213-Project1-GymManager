package project1;
/**
 MemberDatabast class to store members inside a member list.
 Performs add and remove member, display members with or without order.
 @author Songyuan Zhang, Robert Jimenez
 */
public class FitnessClass {
    private String className;
    private Time startTime;
    private String instructor;
    private Member [] studentList;
    private  int size;
    /**
     Initialize a FitnessClass object.
     Initialize one FitnessClass object with class name, start time, and instructor.
     @param className the name of the fitness class
     @param startTime the start time of the fitness class, it can be either morning or afternoon
     @param  instructor the name of the instructor of the fitness class
     */
    public FitnessClass(String className, Time startTime, String instructor) {
        this.className = className;
        this.startTime = startTime;
        this.size = Compare.EMPTY_SIZE;
        this.studentList = new Member[Compare.ARRAYGROWSIZE];
        this.instructor = instructor;
    }
    /**
     Check whether the member is in the student list.
     Traverse the student list to find the student.
     @param member the member that needs to be found.
     @return index if the member is found, Compare.NOT_FOUND otherwise.
     */
    private int find(Member member) {
        for (int index = 0; index < this.size; index++) { // Checks if member is in student list
            if (member.equals(this.studentList[index])) {
                return index; //returns index is member is in array
            }
        }
        return Compare.NOT_FOUND; //returns "NOT FOUND" if not
    }
    /**
     Increase the capacity of the student list if it has reached its capacity.
     Copying old array elements into the array with the new capacity.
     */
    private void grow() { //Method to grow capacity by 4 into new array copying old array elements
        Member[]  oldLst = studentList; //Temp array to hold old array
        int newCapacity = studentList.length + Compare.ARRAYGROWSIZE;
        studentList = new Member[newCapacity]; // new array with 4 more capacity
        for (int x = 0; x < oldLst.length; x++) { //copy old array elements into new array
            studentList[x] = oldLst[x];
        }
    }
    /**
     Allow member to check into a class if the student has not checked in.
     Find whether the student is in the class student list.
     Add the student into the list by appending to the end of the list.
     @param member the member that needs to be checked in.
     @return true if the member is check in successfully, false if the member is already checked in.
     */
    public boolean checkInClass(Member member){
        //checks if member is checked in
        if (this.find(member) != Compare.NOT_FOUND) {
            return false;
        }
        if (find(member) == Compare.NOT_FOUND) { //if member is not checked in
            if ((this.size + 1) > studentList.length ) {
                grow();  //increases the database if it doesn't.
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
    /**
     Check whether student has already checked into this class.
     Called by GymManager class to check time conflict in the class schedule.
     @param member the member who wants to check in another class that might have time conflict with this class.
     @return true if the member has already checked in, false if the member has not checked in.
     */
    public boolean checkExistence(Member member) {
        if (find(member) != -1) {
            return true;
        }
        return false;
    }
    /**
     Check whether the student list is empty.
     Called by GymManager class to help listFitnessClassSchedule method to see
     whether the class has student or not.
     @return true if the class has no student, false if otherwise.
     */
    public boolean checkEmptyStudentList() {
        if (this.size == 0)
            return true;
        return false;
    }
    /**
     Drop a class for a member.
     If member exists in the class, drop the member from the student list.
     Move every subsequence members on position to the left of the array.
     @param member who wants to drop the class.
     @return true if the class has been dropped, false if student is not in the class.
     */
    public boolean dropClass(Member member) {
        int indexToRemove = find(member);
        if (indexToRemove != Compare.NOT_FOUND) {
            this.studentList[indexToRemove] = this.studentList[this.size - 1];
            int tempIndex = indexToRemove;
            while (tempIndex + 1 <= this.size - 1) {
                this.studentList[tempIndex] = this.studentList[tempIndex + 1];
                tempIndex++;
            }
            this.studentList[tempIndex] = null;
            this.size--;
            return true;
        }
        return false;
    }
    /**
     Get the list of students checked into the class.
     Called by GymManager class to print list of students in a class.
     @return studentList containing list of students checked into the class.
     */
    public Member [] getStudentList() {
        return this.studentList;
    }
    /**
     Override the toString method and print class name, instructor, and start time of the class.
     @return string containing class name, instructor, and start time of the class.
     */
    @Override
    public String toString() {
        return this.className + " - " + this.instructor + " " + this.startTime.toString();
    }
}

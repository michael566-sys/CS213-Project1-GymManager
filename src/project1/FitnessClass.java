package project1;

public class FitnessClass {
    private String className;
    private Time startTime;
    private String instructor;
    private Member[] studentList;

    public FitnessClass(String className, Time startTime, String instructor) {
        this.className = className;
        this.startTime = startTime;
        studentList = new Member[4];
    }

    public void checkInClass(){

    }

}

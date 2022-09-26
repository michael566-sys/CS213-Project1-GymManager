package project1;

public enum Time {
    PILATESCLASSTIME ("9:30"),
    SPINNINGCLASSTIME ("14:00"),
    CARDIOCLASSTIME ("14:00");

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;
    public static final int THIRTYDAYSMONTH = 30;
    public static final int THIRTYONEDAYSMONTH = 31;
    public static final int DAYSINLEAPFEBRUARY = 29;
    public static final int DAYSINNONLEAPFEBRUARY = 28;

    private final String startTime;
    Time(String startTime){
        this.startTime = startTime;
    }
}
package project1;

public enum ReturnValue {
    LESSTHAN (-1),
    EQUAL (0),
    GREATERTHAN (1);
    private final int returnValue;
    ReturnValue(int returnValue) {
        this.returnValue = returnValue;
    }
}

package project1;

public enum Location {
    SOMERVILLE ("08876", "SOMERSET"),
    BRIDGEWATER ("08807", "SOMERSET"),
    EDISON ("08837", "MIDDLESEX"),
    PISCATAWAY ("08854", "MIDDLESEX"),
    FRANKLIN ("08873", "SOMERSET");
    private final String zipCode;
    private final String county;
    Location(String zipCode, String county) {
        this.zipCode = zipCode;
        this.county = county;
    }
    public String getZipCode() {
        return zipCode;
    }
    public String getCounty() {
        return county;
    }
}

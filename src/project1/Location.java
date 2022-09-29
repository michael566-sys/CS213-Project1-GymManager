package project1;

public enum Location implements Comparable<Location> {
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

    public int compare(Location location) {
        if (location.getCounty().toLowerCase().equals(getCounty().toLowerCase())) { // if counties are the same check zipcodes
            if (location.getZipCode().toLowerCase().compareTo(getZipCode().toLowerCase()) > 0)
                return Compare.MORETHAN;
            if (location.getCounty().toLowerCase().compareTo(getCounty().toLowerCase()) < 0)
                return Compare.LESSTHAN;
            return Compare.EQUAL;
        }
        if (location.getCounty().toLowerCase().compareTo(getCounty().toLowerCase()) > 0) {
            return Compare.MORETHAN;
        }
        if (location.getCounty().toLowerCase().compareToIgnoreCase(getCounty()) < 0) {
            return Compare.LESSTHAN;
        }
        return 0;
    }
}

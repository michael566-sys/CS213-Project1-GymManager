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
    @Override
    public String toString() {
        return this.name() + ", " + this.zipCode + ", " + this.county;
    }

    public int compare(Location location) {
        if (location.getCounty().toLowerCase().equals(getCounty().toLowerCase())) { // if counties are the same check zipcodes
            if (Integer.parseInt(location.getZipCode()) > Integer.parseInt(getZipCode())) // checks zipcodes
                return Compare.MORETHAN;
            if (Integer.parseInt(location.getZipCode()) < Integer.parseInt(getZipCode()))
                return Compare.LESSTHAN;
            return Compare.EQUAL;
        } //if counties are not the same then checks counties
        if (location.getCounty().toLowerCase().compareTo(getCounty().toLowerCase()) > 0) {
            return Compare.MORETHAN;
        }
        if (location.getCounty().toLowerCase().compareToIgnoreCase(getCounty()) < 0) {
            return Compare.LESSTHAN;
        }
        return Compare.EQUAL;
    }
}

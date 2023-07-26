package database;

public class Address {
    private String country;
    private String province;
    private String town;
    private String street;
    private int house;
    private int local;
    private String postCode;

    public Address() {};

    public Address(String country, String province, String town, String street, int house, int local, String postCode) {
        this.country = country;
        this.province = province;
        this.town = town;
        this.street = street;
        this.house = house;
        this.local = local;
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "Address:" +
                "country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", town='" + town + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", local=" + local +
                ", postCode='" + postCode + '\'';
    }
}

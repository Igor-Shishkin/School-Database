package database;

public class Address {
    private String country;
    private String province;
    private String town;
    private String street;
    private String house;
    private String local;
    private String postCode;

    public Address() {};

    public Address(String country, String province, String town, String street, String house, String local, String postCode) {
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

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
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

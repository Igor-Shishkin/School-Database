package school.database.data.objects;

public abstract class Person {

    private String name;
    private String secondName;
    private String surname;
    private char gender;

    private Address address;
    private String pesel;

    protected Person(String name, String secondName, String surname, char gender,
                  Address address, String pesel) {
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.gender = gender;
        this.address = address;
        this.pesel = pesel;
    }

    protected Person() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return "Person: " +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                "\naddress=" + address +
                "\npesel='" + pesel + '\'' +
                '}';
    }

}

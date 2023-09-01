package school.database.data.objects;

import school.database.exceptiones.RequiredFieldIsEmpty;
import school.database.exceptiones.WrongPeselException;

import java.util.regex.Pattern;

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
        if (name==null || name.isEmpty()) {
            throw new RequiredFieldIsEmpty("No name entered");
        }
        if (surname==null || surname.isEmpty()) {
            throw new RequiredFieldIsEmpty("No surname entered");
        }
        if (pesel==null) {
            throw new WrongPeselException("Pesel can't be null");
        }
        if (!Pattern.compile("^\\d{11}$").matcher(pesel).matches()){
            throw new WrongPeselException("Pesel number must contain 11 digits");
        }
    }

    protected Person() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (name==null || name.isEmpty()) {
            throw new RequiredFieldIsEmpty("No name entered");
        }
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
        if (surname==null || surname.isEmpty()) {
            throw new RequiredFieldIsEmpty("No surname entered");
        }
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
        if (pesel==null) {
            throw new WrongPeselException("Pesel can't be null");
        }
        if (!Pattern.compile("^\\d{11}$").matcher(pesel).matches()){
            throw new WrongPeselException("Pesel number must contain 11 digits");
        }
    }
}

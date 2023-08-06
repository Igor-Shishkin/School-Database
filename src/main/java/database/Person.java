package database;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public abstract class Person {

    private String name;
    private String secondName;
    private String surname;
    private char gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private Address address;
    private String pesel;

    public Person(String name, String secondName, String surname, char gender, int year, int month, int day,
                  Address address, String pesel) {
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirth = LocalDate.of(year, month, day);
        this.address = address;
        this.pesel = pesel;
    }

    public Person() {
    }

    //    public Person(String name, String secondName, String surname, char gender, int year, int month, int day, Address address, String pesel) {
//        this.name = name;
//        this.secondName = secondName;
//        this.surname = surname;
//        this.gender = gender;
//        this.year = year;
//        this.month = month;
//        this.day = day;
//        this.address = address;
//        this.pesel = pesel;
//    }
//    public Person(String name, String surname, char gender, int year, int month, int day, Address address, String pesel) {
//        this.name = name;
//        this.gender = gender;
//        this.secondName = null;
//        this.surname = surname;
//        this.year = year;
//        this.month = month;
//        this.day = day;
//        this.address = address;
//        this.pesel = pesel;
//    }
//
//    public Person(Person person) {
//        this.gender = '-';
//        this.name = null;
//        this.secondName = null;
//        this.surname = null;
//        this.year = 0;
//        this.month = 0;
//        this.day = 0;
//        this.address = null;
//        this.pesel = null;
//    }

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
                "\nyear=" + dateOfBirth.getYear() +
                ", month=" + dateOfBirth.getMonth() +
                ", day=" + dateOfBirth.getDayOfMonth() +
                "\naddress=" + address +
                "\npesel='" + pesel + '\'' +
                '}';
    }

}

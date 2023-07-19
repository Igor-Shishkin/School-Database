package database;

public class Person {
    private final String name;
    private final String secondName;
    private final String surname;
    private final int year;
    private final int month;
    private final int day;
    private final Address address;
    private final long pesel;

    public Person(String name, String secondName, String surname, int year, int month, int day, Address address, long pesel) {
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.year = year;
        this.month = month;
        this.day = day;
        this.address = address;
        this.pesel = pesel;
    }
    public Person(String name, String surname, int year, int month, int day, Address address, long pesel) {
        this.name = name;
        this.secondName = null;
        this.surname = surname;
        this.year = year;
        this.month = month;
        this.day = day;
        this.address = address;
        this.pesel = pesel;
    }

    public Person(Person person) {
    }
}

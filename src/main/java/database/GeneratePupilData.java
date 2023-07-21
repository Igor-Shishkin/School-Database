package database;

import database.marks.MarksTableForthFifthSixthClass;

import java.time.LocalDate;
import java.util.Random;

public class GeneratePupilData {
    private final String[] MALE_NAMES = new String[]{"Jan", "Jakub", "Mateusz" , "Adam", "Marcel", "Stanisław", "Michał",
            "Wiktor", "Leon", "Piotr", "Mark", "Ignacy", "Franek", "Kosma"};
    private final String[] FEMALE_NAMES = new String[]{"Zuzanna", "Julia", "Maja", "Zofia", "Hanna", "Lena", "Alicja",
            "Maria", "Amelia", "Oliwia", "Anna", "Sofia", "Maria", "Katarzyna", "Małgorzata", "Agnieszka", "Barbara", "Ewa",
            "Krystyna", "Elżbieta", "Magdalena."};
    public final String[] SURNAMES = new String[]{"Nowak", "Wójcik", "Woźniak", "Mazur", "Kowalik", "Ławniczak",
            "Zając", "Wróbiel", "Wieczorek", "Pietrzak", "Wilk",
            "Ratajczak", "Lis", "Kołodziej", "Sobczak", "Szulc", "Mróz", "Duda",
            "Banasiak", "Buszkiewić", "Bałut", "Czajka", "Tacka", "Kujałowić"};
    public final String[] TOWN = new String[]{"Kobylepole", "Swarzędz", "Zalasewo", "Garby", "Jasin",
            "Garby", "Tulce", "Poznań"};
    public final String[] STREETS = new String[]{"Jasna", "Radosna", "Warszawska", "Garbary", "Nowina",
            "Zielona", "Niedźwiedzia", "Wilka", "Szemerewskiego", "Niezależności", "Niebieska", "Pilgrzymia",
            "Programistów", "JavaSTREET"};

    private final String[] ACHIEVEMENT = new String[]{"First place in the Mathematical Olympiad \"The Gold Frog\"",
            "Laureate of the historical competition \"Wise Owl\"",
            "Second place in the Mathematical Olympiad \"The Gold Frog\"",
            "Third place in the Mathematical Olympiad \"The Gold Frog\"",
            "Winner of the chess tournament \"prancing pony\"",
            "First place in Physics Olympiad \"Unstable Isotope\"",
            "Second place in Physics Olympiad \"Unstable Isotope\"",
            "Third place in Physics Olympiad \"Unstable Isotope\"",
            "Laureate of the Art Competition \"Chicken Paw\"",
            "Winner of the computer science competition \"Binary World\""};
    public final Random random = new Random();
    public final LocalDate localDate = LocalDate.now();

    public String generateMaleName() {
        return MALE_NAMES[random.nextInt(MALE_NAMES.length - 1)];
    }
    public String generateFemaleName() { return FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length - 1)];
    }

    public String generateSurname() {
        return SURNAMES[random.nextInt(SURNAMES.length - 1)];
    }

    public int generateYear() {
        return localDate.getYear() - random.nextInt(9) - 6;
    }
    public int generateYearForParents() { return localDate.getYear() - random.nextInt(15) - 25; }

    public int generateMonth() {
        return random.nextInt(11) + 1;
    }

    public int generateDay() {
        return random.nextInt(30) + 1;
    }

    public String generateTown() {
        return TOWN[random.nextInt(TOWN.length - 1)];
    }

    public String generateStreet() { return STREETS[random.nextInt(STREETS.length - 1)]; }

    public int generateHouse() {  return random.nextInt(150) + 1; }

    public int generateLocal() { return random.nextInt(20) + 1;  }

    public String generatePostCode() {
        return (random.nextInt(30) + 40) + "-" + (random.nextInt(500) + 123);
    }

    public Address generateAddress() {
        return new Address("Polska", "Wielkopolskie", generateTown(), generateStreet(),
                generateHouse(), generateLocal(), generatePostCode());}

    public char generateGender() {
        return (random.nextBoolean())?'M':'F';}

    public String generateAchievement() {
        return (random.nextInt(100)>95)?ACHIEVEMENT[random.nextInt(ACHIEVEMENT.length - 1)]:null;
    }

    public MarksTableForthFifthSixthClass generateMarks456() {
     int isSenior = (random.nextBoolean())?2:0;
     int m1=random.nextInt(4)+2+isSenior,m2=random.nextInt(4)+2+isSenior,m3=random.nextInt(4)+2+isSenior,mY = (m1+m2+m3)/3;
     int p1=random.nextInt(4)+2+isSenior,p2=random.nextInt(4)+2+isSenior,p3=random.nextInt(4)+2+isSenior,pY = (p1+p2+p3)/3;
     int e1=random.nextInt(4)+2+isSenior,e2=random.nextInt(4)+2+isSenior,e3=random.nextInt(4)+2+isSenior,eY = (e1+e2+e3)/3;
     int i1=random.nextInt(4)+2+isSenior,i2=random.nextInt(4)+2+isSenior,i3=random.nextInt(4)+2+isSenior,iY = (i1+i2+i3)/3;
     int pe1=random.nextInt(4)+2+isSenior,pe2=random.nextInt(4)+2+isSenior,pe3=random.nextInt(4)+2+isSenior,
             peY = (pe1+pe2+pe3)/3;
     int mus1=random.nextInt(4)+2+isSenior,mus2=random.nextInt(4)+2+isSenior,mus3=random.nextInt(3)+2+isSenior,
             musY = (mus1+mus2+mus3)/3;
     int r1=random.nextInt(4)+2+isSenior,r2=random.nextInt(4)+2+isSenior,r3=random.nextInt(4)+2+isSenior,rY = (r1+r2+r3)/3;
     int n1=random.nextInt(4)+2+isSenior,n2=random.nextInt(4)+2+isSenior,n3=random.nextInt(4)+2+isSenior,nY = (n1+n2+n3)/3;
     int b1=random.nextInt(4)+2+isSenior,b2=random.nextInt(4)+2+isSenior,b3=random.nextInt(4)+2+isSenior,bY = (b1+b2+b3)/3;

        return new MarksTableForthFifthSixthClass(m1,m2,m3,mY,
                p1,p2,p3,pY,
                e1,e2,e3,eY,
                i1,i2,i3,iY,
                pe1,pe2,pe3,peY,
                mus1,mus2,mus3,musY,
                r1,r2,r3,rY,
                n1,n2,n3,nY,
                b1,b2,b3,bY);
    }


    public Person generatePerson() {
        int year = generateYear();
        int month = generateMonth();
        int day = generateDay();
        char gender = generateGender();
        String name = (gender=='M')?generateMaleName():generateFemaleName();

        int pesel1And2thDigits = year % 100;
        int pesel3And4thDigits = month + 20;
        int pesel7And8And9thDigits = random.nextInt(999);
        int pesel10thDigit = (gender=='F') ? random.nextInt(4) * 2 : (9 - random.nextInt(4) * 2);

        String pesel = String.format("%d%d%02d%03d%d%d", pesel1And2thDigits, pesel3And4thDigits, day,
                pesel7And8And9thDigits, pesel10thDigit, random.nextInt(10));

        return new Person(name, generateSurname(), gender, year, month, day, generateAddress(), pesel);
    }
    public Person generatePerson(char gender) {
        int year = generateYear();
        int month = generateMonth();
        int day = generateDay();
        String name = (gender=='M')?generateMaleName():generateFemaleName();

        int pesel1And2thDigits = year % 100;
        int pesel3And4thDigits = month + 20;
        int pesel7And8And9thDigits = random.nextInt(999);
        int pesel10thDigit = (gender=='F') ? random.nextInt(4) * 2 : (9 - random.nextInt(4) * 2);

        String pesel = String.format("%d%d%02d%03d%d%d", pesel1And2thDigits, pesel3And4thDigits, day,
                pesel7And8And9thDigits, pesel10thDigit, random.nextInt(10));

        return new Person(name, generateSurname(), gender, year, month, day, generateAddress(), pesel);
    }
    public Person generatePerson(Address address) {
        int year = generateYear();
        int month = generateMonth();
        int day = generateDay();
        char gender = generateGender();
        String name = (gender=='M')?generateMaleName():generateFemaleName();

        int pesel1And2thDigits = year % 100;
        int pesel3And4thDigits = month + 20;
        int pesel7And8And9thDigits = random.nextInt(999);
        int pesel10thDigit = (gender=='F') ? random.nextInt(4) * 2 : (9 - random.nextInt(4) * 2);

        String pesel = String.format("%d%d%02d%03d%d%d", pesel1And2thDigits, pesel3And4thDigits, day,
                pesel7And8And9thDigits, pesel10thDigit, random.nextInt(10));

        return new Person(name, generateSurname(), gender, year, month, day, address, pesel);
    }
    public Person generatePersonForParents(String surname, Address address) {
        int year = generateYearForParents();
        int month = generateMonth();
        int day = generateDay();
        char gender = generateGender();
        String name = (gender=='M')?generateMaleName():generateFemaleName();

        int pesel1And2thDigits = year % 100;
        int pesel3And4thDigits = month;
        int pesel7And8And9thDigits = random.nextInt(999);
        int pesel10thDigit = (gender=='F') ? random.nextInt(4) * 2 : (9 - random.nextInt(4) * 2);

        String pesel = String.format("%d%d%02d%03d%d%d", pesel1And2thDigits, pesel3And4thDigits, day,
                pesel7And8And9thDigits, pesel10thDigit, random.nextInt(10));

        return new Person(name, surname, gender, year, month, day, address, pesel);
    }

    public Person generatePerson(Address address, char gender) {
        int year = generateYear();
        int month = generateMonth();
        int day = generateDay();
        String name = (gender=='M')?generateMaleName():generateFemaleName();

        int pesel1And2thDigits = year % 100;
        int pesel3And4thDigits = month + 20;
        int pesel7And8And9thDigits = random.nextInt(999);
        int pesel10thDigit = (gender=='F') ? random.nextInt(4) * 2 : (9 - random.nextInt(4) * 2);

        String pesel = String.format("%d%d%02d%03d%d%d", pesel1And2thDigits, pesel3And4thDigits, day,
                pesel7And8And9thDigits, pesel10thDigit, random.nextInt(10));

        return new Person(name, generateSurname(), gender, year, month, day, address, pesel);
    }

    public Person generatePersonForParents(String surname, Address address, char gender) {
        int year = generateYearForParents();
        int month = generateMonth();
        int day = generateDay();
        String name = (gender=='M')?generateMaleName():generateFemaleName();

        int pesel1And2thDigits = year % 100;
        int pesel3And4thDigits = month;
        int pesel7And8And9thDigits = random.nextInt(999);
        int pesel10thDigit = (gender=='F') ? random.nextInt(4) * 2 : (9 - random.nextInt(4) * 2);

        String pesel = String.format("%d%d%02d%03d%d%d", pesel1And2thDigits, pesel3And4thDigits, day,
                pesel7And8And9thDigits, pesel10thDigit, random.nextInt(10));

        return new Person(name, surname, gender, year, month, day, address, pesel);
    }

    public Parents generateParents(String surname, Address address) {
        int chance = random.nextInt(100);
        if (chance>95) {
            return new Parents(generatePersonForParents(surname, address));
        } else {
            return new Parents(generatePersonForParents(surname, address,'M'),
                    generatePersonForParents(surname, address,'F'));
        }

    }

    public Pupil generatePupil() {
        Person person = generatePerson();
        int grade = localDate.getYear() - person.getYear()-6;

        return new Pupil(person, grade, generateParents(person.getSurname(), person.getAddress()), generateAchievement(),
                 generateMarks456(), false, false);
    }


}

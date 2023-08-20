package database;

import java.time.LocalDate;
import java.util.Random;

public class GeneratePupilData {
    private final String[] MALE_NAMES = new String[]{"Jan", "Jakub", "Mateusz" , "Adam", "Marcel", "Stanisław", "Michał",
            "Wiktor", "Leon", "Piotr", "Mark", "Ignacy", "Franek", "Kosma"};
    private final String[] FEMALE_NAMES = new String[]{"Zuzanna", "Julia", "Maja", "Zofia", "Hanna", "Lena", "Alicja",
            "Maria", "Amelia", "Oliwia", "Anna", "Sofia", "Maria", "Katarzyna", "Małgorzata", "Agnieszka", "Barbara",
            "Ewa", "Krystyna", "Elżbieta", "Magdalena"};
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
            "Winner of the chess tournament \"Prancing Pony\"",
            "First place in Physics Olympiad \"Unstable Isotope\"",
            "Second place in Physics Olympiad \"Unstable Isotope\"",
            "Third place in Physics Olympiad \"Unstable Isotope\"",
            "Laureate of the Art Competition \"Chicken Paw\"",
            "Winner of the computer science competition \"Binary World\""};
    public final Random random = new Random();
    public final LocalDate localDate = LocalDate.now();

    public String generateMaleName() {
        return MALE_NAMES[random.nextInt(MALE_NAMES.length)];
    }
    public String generateSecondMaleName() {
        return (random.nextInt(100)>70)? MALE_NAMES[random.nextInt(MALE_NAMES.length)] :null;  }
    public String generateFemaleName() { return FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length)]; }
    public String generateSecondFemaleName() {
        return (random.nextInt(100)>70)? FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length)] :null;  }

    public String generateSurname() {
        return SURNAMES[random.nextInt(SURNAMES.length)];
    }

    public int generateYear() {
        return localDate.getYear() - random.nextInt(9) - 6;
    }
    public int generateYearForParents() { return localDate.getYear() - random.nextInt(15) - 25; }

    public int generateMonth() {
        return random.nextInt(11) + 1;
    }

    public int generateDay() {
        return random.nextInt(28) + 1;
    }

    public String generateTown() {
        return TOWN[random.nextInt(TOWN.length)];
    }

    public String generateStreet() { return STREETS[random.nextInt(STREETS.length)]; }

    public String generateHouse() {  return Integer.toString(random.nextInt(150) + 1); }

    public String generateLocal() { return Integer.toString(random.nextInt(20) + 1);  }

    public String generatePostCode() {
        return (random.nextInt(30) + 40) + "-" + (random.nextInt(500) + 123);
    }

    public Address generateAddress() {
        return new Address("Polska", "Wielkopolskie", generateTown(), generateStreet(),
                generateHouse(), generateLocal(), generatePostCode());}

    public char generateGender() {
        return (random.nextBoolean())?'M':'F';}

    public String generateAchievement() {
        return (random.nextInt(100)>80)?ACHIEVEMENT[random.nextInt(ACHIEVEMENT.length - 1)]:null;
    }

    public Marks generateMarks456() {
     int isSenior = (random.nextBoolean())?2:0;
     int m1=random.nextInt(4)+1+isSenior,m2=random.nextInt(4)+1+isSenior,
             m3=random.nextInt(4)+1+isSenior,mY = getAverage(m1,m2,m3);
     int p1=random.nextInt(4)+1+isSenior,p2=random.nextInt(4)+1+isSenior,
             p3=random.nextInt(4)+1+isSenior,pY = getAverage(p1,p2,p3);
     int e1=random.nextInt(4)+1+isSenior,e2=random.nextInt(4)+1+isSenior,
             e3=random.nextInt(4)+1+isSenior,eY = getAverage(e1,e2,e3);
     int i1=random.nextInt(4)+1+isSenior,i2=random.nextInt(4)+1+isSenior,
             i3=random.nextInt(4)+1+isSenior,iY = getAverage(i1,i2,i3);
     int pe1=random.nextInt(4)+1+isSenior,pe2=random.nextInt(4)+1+isSenior,
             pe3=random.nextInt(4)+1+isSenior, peY = getAverage(pe1,pe2,pe3);
     int mus1=random.nextInt(4)+1+isSenior,mus2=random.nextInt(4)+1+isSenior,
             mus3=random.nextInt(4)+1+isSenior, musY = getAverage(mus1,mus2,mus3);
     int r1=random.nextInt(4)+1+isSenior,r2=random.nextInt(4)+1+isSenior,
             r3=random.nextInt(4)+1+isSenior,rY = getAverage(r1,r2,r3);
     int n1=random.nextInt(4)+1+isSenior,n2=random.nextInt(4)+1+isSenior,
             n3=random.nextInt(4)+1+isSenior,nY =getAverage (n1,n2,n3);
     int b1=random.nextInt(4)+1+isSenior,b2=random.nextInt(4)+1+isSenior,
             b3=random.nextInt(4)+1+isSenior,bY =getAverage (b1,b2,b3);

        return new Marks(m1,m2,m3,mY,
                p1,p2,p3,pY,
                e1,e2,e3,eY,
                i1,i2,i3,iY,
                pe1,pe2,pe3,peY,
                mus1,mus2,mus3,musY,
                r1,r2,r3,rY,
                n1,n2,n3,nY,
                -1,-1,-1,-1,
                -1,-1,-1,-1,
                -1,-1,-1,-1,
                b1,b2,b3,bY);
    }

    public Marks generateMarks7and8() {
        int isSenior = (random.nextBoolean()) ? 2 : 0;
        int m1 = random.nextInt(4) + 1 + isSenior, m2 = random.nextInt(4) + 1 + isSenior,
                m3 = random.nextInt(4) + 1 + isSenior, mY = getAverage (m1, m2, m3);
        int pol1 = random.nextInt(4) + 1 + isSenior, pol2 = random.nextInt(4) + 1 + isSenior,
                pol3 = random.nextInt(4) + 1 + isSenior, polY = getAverage(pol1,pol2,pol3) ;
        int e1 = random.nextInt(4) + 1 + isSenior, e2 = random.nextInt(4) + 1 + isSenior,
                e3 = random.nextInt(4) + 1 + isSenior, eY = getAverage(e1,e2,e3) ;
        int i1 = random.nextInt(4) + 1 + isSenior, i2 = random.nextInt(4) + 1 + isSenior,
                i3 = random.nextInt(4) + 1 + isSenior, iY = getAverage(i1,i2,i3);
        int pe1 = random.nextInt(4) + 1 + isSenior, pe2 = random.nextInt(4) + 1 + isSenior,
                pe3 = random.nextInt(4) + 1 + isSenior,  peY = getAverage(pe1,pe2,pe3) ;
        int mus1 = random.nextInt(4) + 1 + isSenior, mus2 = random.nextInt(4) + 1 + isSenior,
                mus3 = random.nextInt(4) + 1 + isSenior, musY = getAverage(mus1, mus2, mus3);
        int r1 = random.nextInt(4) + 1 + isSenior, r2 = random.nextInt(4) + 1 + isSenior,
                r3 = random.nextInt(4) + 1 + isSenior, rY = getAverage(r1,r2,r3) ;
        int n1 = random.nextInt(4) + 1 + isSenior, n2 = random.nextInt(4) + 1 + isSenior,
                n3 = random.nextInt(4) + 1 + isSenior, nY = getAverage(n1,n2,n3) ;
        int bl1 = random.nextInt(4) + 1 + isSenior, bl2 = random.nextInt(4) +1 + isSenior,
                bl3 = random.nextInt(4) + 1 + isSenior, blY = getAverage(bl1,bl2,bl3) ;
        int p1 = random.nextInt(4) + 1 + isSenior, p2 = random.nextInt(4) + 1 + isSenior,
                p3 = random.nextInt(4) + 1 + isSenior, pY = getAverage(p1,p2,p3) ;
        int g1 = random.nextInt(4) + 1 + isSenior, g2 = random.nextInt(4) + 1 + isSenior,
                g3 = random.nextInt(4) + 1 + isSenior, gY = getAverage(g1,g2,g3) ;
        int b1 = random.nextInt(4) + 1 + isSenior, b2 = random.nextInt(4) + 1 + isSenior,
                b3 = random.nextInt(4) + 1 + isSenior, bY = getAverage(b1,b2,b3) ;

        return new Marks(m1, m2, m3, mY,
                pol1, pol2, pol3, polY,
                e1, e2, e3, eY,
                i1, i2, i3, iY,
                pe1, pe2, pe3, peY,
                mus1, mus2, mus3, musY,
                r1, r2, r3, rY,
                -1, -1, -1, -1,
                bl1, bl2, bl3, blY,
                p1, p2, p3, pY,
                g1, g2, g3, gY,
                b1, b2, b3, bY);
    }


    public int getAverage(int a, int b, int c) {
        float av = (a+b+c)/3f;
        return Math.round(av);
    }

    public String generateTelephone () {
        return  Integer.toString(700000000 + random.nextInt(99999999));
    }

    public Parent generateParent(String surname, char gender, Address address) {
        int year = generateYearForParents();
        int month = generateMonth();
        int day = generateDay();
        String name = (gender=='M')?generateMaleName():generateFemaleName();
        String secondName = (gender=='M')?generateSecondMaleName():generateSecondFemaleName();

        int pesel1And2thDigits = year % 100;
        int pesel3And4thDigits = (year<2000) ? month : (year<2100) ? month +20 : month + 40;
        int pesel7And8And9thDigits = random.nextInt(999);
        int pesel10thDigit = (gender=='F') ? random.nextInt(4) * 2 : (9 - random.nextInt(4) * 2);
        String pesel = String.format("%d%02d%02d%03d%d%d", pesel1And2thDigits, pesel3And4thDigits, day,
                pesel7And8And9thDigits, pesel10thDigit, random.nextInt(10));
        String eMail = name.toLowerCase().concat(".").concat(surname.toLowerCase()).concat("@gmail.com");

        return new Parent(name, secondName, surname, gender,  address,
                pesel, generateTelephone(), eMail);
    }

    public Parent generateSecondParent(String surname, char gender, Address address) {
        int year = generateYearForParents();
        int month = generateMonth();
        int day = generateDay();
        String name = (gender=='M')?generateMaleName():generateFemaleName();
        String secondName = (gender=='M')?generateSecondMaleName():generateSecondFemaleName();

        int pesel1And2thDigits = year % 100;
        int pesel3And4thDigits = (year<2000) ? month : (year<2100) ? month +20 : month + 40;
        int pesel7And8And9thDigits = random.nextInt(1000);
        int pesel10thDigit = (gender=='F') ? random.nextInt(4) * 2 : (9 - random.nextInt(4) * 2);
        String pesel = String.format("%d%02d%02d%03d%d%d", pesel1And2thDigits, pesel3And4thDigits, day,
                pesel7And8And9thDigits, pesel10thDigit, random.nextInt(10));
        String eMail = name.toLowerCase().concat(".").concat(surname.toLowerCase()).concat("@gmail.com");

        return (random.nextInt(100)<90)? new Parent(name, secondName, surname, gender,  address,
                pesel, generateTelephone(), eMail) : null;
    }

    public Pupil generatePupil() {
        int year = generateYear();
        int month = generateMonth();
        int day = generateDay();
        char gender = generateGender();
        String name = (gender=='M')?generateMaleName():generateFemaleName();
        String secondName = (gender=='M')?generateSecondMaleName():generateSecondFemaleName();
        String surname = generateSurname();
        int grade = localDate.getYear() - year - 6;
        char parentGender = generateGender();
        char parentSecondGender = (parentGender=='M')?'F':'M';
        Address address = generateAddress();

        int pesel1And2thDigits = year % 100;
        int pesel3And4thDigits = (year<2000) ? month : (year<2100) ? month +20 : month + 40;
        int pesel7And8And9thDigits = random.nextInt(999);
        int pesel10thDigit = (gender=='F') ? random.nextInt(4) * 2 : (9 - random.nextInt(4) * 2);
        String pesel = String.format("%02d%02d%02d%03d%d%d", pesel1And2thDigits, pesel3And4thDigits, day,
                pesel7And8And9thDigits, pesel10thDigit, random.nextInt(10));

        Marks marks = (grade>6) ? generateMarks7and8() : (grade>3) ? generateMarks456() : null;
        boolean promotion = marks != null && marks.getPromotion(grade);
        boolean awardBar = marks != null && marks.isAwardBar(promotion, grade);

        String achievement = (grade>3) ? generateAchievement() : null;

        return new Pupil(name, secondName, surname, gender, year, month, day, address,
                pesel, Pupil.getIdNumber(), grade, generateParent(surname, parentGender, address),
                generateSecondParent(surname, parentSecondGender, address), achievement,
                marks, awardBar, promotion);
    }


}

package database;

public class Main {
    public static void main(String[] args) {
//        Parents = new Parents(new Person("Małgorzata", id))
//        Pupil pupil = new Pupil("Jan", "Kowalski", 2010, 4, 12,
//                new Address("Polska", "Wielkopolskie","Poznań", "Dobra",
//                        12,3,"34-345"), 10241244550, ;
        GeneratePupilData generate = new GeneratePupilData();
        Pupil pupil1 = generate.generatePupil();
        Pupil pupil2 = generate.generatePupil();
        Pupil pupil3 = generate.generatePupil();
        Pupil pupil4 = generate.generatePupil();
        Pupil pupil5 = generate.generatePupil();
        Pupil pupil6 = generate.generatePupil();

        System.out.println(pupil1);
        System.out.println(pupil2);
        System.out.println(pupil3);
        System.out.println(pupil4);
        System.out.println(pupil5);
        System.out.println(pupil6);

    }
}

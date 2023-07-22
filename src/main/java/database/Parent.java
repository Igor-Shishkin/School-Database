package database;

public class Parent extends Person{
    private int telephone;
    private String eMail;

    public Parent(String name, String secondName, String surname, char gender, int year, int month, int day, Address address, String pesel, int telephone, String eMail) {
        super(name, secondName, surname, gender, year, month, day, address, pesel);
        this.telephone = telephone;
        this.eMail = eMail;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return super.toString() +
                "telephone=" + telephone +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}

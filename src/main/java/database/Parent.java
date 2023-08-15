package database;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Parent extends Person implements Cloneable{
    private String telephone;
    private String eMail;
    public Parent() {
        super();
        this.telephone = null;
        this.eMail = null;
    }

    public Parent(String name, String secondName, String surname, char gender, int year, int month, int day, Address address, String pesel, String telephone, String eMail) {
        super(name, secondName, surname, gender, year, month, day, address, pesel);
        this.telephone = telephone;
        this.eMail = eMail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
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

    @JsonIgnore
    @Override
    public Parent clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Parent) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

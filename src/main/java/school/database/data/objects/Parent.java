package school.database.data.objects;

public class Parent extends Person{
    private String telephone;
    private String eMail;
    public Parent() {
        super();
        this.telephone = null;
        this.eMail = null;
    }

    public Parent(String name, String secondName, String surname, char gender,
                  Address address, String pesel, String telephone, String eMail) {
        super(name, secondName, surname, gender, address, pesel);
        this.telephone = telephone;
        this.eMail = eMail;
    }
    public Parent(Parent parent) {
        super(parent.getName(), parent.getSecondName(), parent.getSurname(), parent.getGender(), parent.getAddress(),
                parent.getPesel());
        this.telephone = parent.getTelephone();
        this.eMail = parent.eMail;
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
}

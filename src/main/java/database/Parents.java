package database;

public class Parents {
    private Person parent1;
    private Person parent2;

    public Parents(Person parent1, Person parent2) {
        this.parent1 = parent1;
        this.parent2 = parent2;
    }
    public Parents(Person parent1) {
        this.parent1 = parent1;
        this.parent2 = null;
    }

    public Person getParent1() {
        return parent1;
    }

    public void setParent1(Person parent1) {
        this.parent1 = parent1;
    }

    public Person getParent2() {
        return parent2;
    }

    public void setParent2(Person parent2) {
        this.parent2 = parent2;
    }
}

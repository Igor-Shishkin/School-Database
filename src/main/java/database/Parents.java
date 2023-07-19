package database;

public class Parents {
    private final Person parent1;
    private final Person parent2;

    public Parents(Person parent1, Person parent2) {
        this.parent1 = parent1;
        this.parent2 = parent2;
    }
    public Parents(Person parent1) {
        this.parent1 = parent1;
        this.parent2 = null;
    }
}

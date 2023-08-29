package schoolDatabaseProgram.database.objects;

public class User {
    private String password;
    private Permissions permissions;

    public User() {
    }
    public User(String password, Permissions permissions) {
        this.password = password;
        this.permissions = permissions;
    }
    public String getPassword() {
        return password;
    }
    public Permissions getPermissions() {
        return permissions;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }
}

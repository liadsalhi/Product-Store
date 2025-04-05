package Liadsalhi;

public abstract class User {
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public boolean setPassword(String password) {
        this.password = password;
        return true;
    }

    public String toString() {
        return "Name: "+name+
                "\nPassword: "+password;
    }
}



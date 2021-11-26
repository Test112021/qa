package pl.jsystems.qa.qadatabase.database.model;

public class UserDb {

    public String id;
    public String name;
    public String surname;

    public UserDb(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public UserDb() {
    }

    @Override
    public String toString() {
        return "UserDb{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}

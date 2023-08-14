package crud.model;

import java.util.Objects;

public class User {
    private long id;
    private String name;
    private int age;

    public User(final long value, final String str) {
        this.id = value;
        this.name = str;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(final long value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(final String str) {
        this.name = str;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

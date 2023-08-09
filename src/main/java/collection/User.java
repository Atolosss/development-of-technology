package collection;

import java.util.Objects;

public class User {
    private int id;
    private String name;

    public User(final int value, final String str) {
        this.id = value;
        this.name = str;
    }

    public int getId() {
        return id;
    }

    public void setId(final int value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(final String value) {
        this.name = value;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
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
        return id == user.id && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


}

package hu.cookerybook.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {
    @Getter private int id;
    @Getter @Setter private int userRole;
    @Getter @Setter private String username;
    @Getter @Setter private String email;
    @Getter @Setter private String password;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter private Date registerDate;

    public User() {
    }

    public User(int userRole, String username, String email, String password) {
        this.userRole = userRole;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(int userRole, String username, String email, String password, String firstName) {
        this.userRole = userRole;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
    }

    public User(int userRole, String username, String email, String password, String firstName, String lastName) {
        this.userRole = userRole;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(int userRole, String username, String email, String password, String firstName, String lastName, Date registerDate) {
        this.userRole = userRole;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registerDate = registerDate;
    }

    public User(int id, int userRole, String username, String email, String password, String firstName, String lastName) {
        this.id = id;
        this.userRole = userRole;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public User(int id, int userRole, String username, String email, String password, String firstName, String lastName, Date registerDate) {
        this.id = id;
        this.userRole = userRole;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registerDate = registerDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && userRole == user.userRole && username.equals(user.username) && email.equals(user.email) && password.equals(user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(registerDate, user.registerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userRole, username, email, password, firstName, lastName, registerDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userRole=" + userRole +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registerDate='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(registerDate) + '\'' +
                '}';
    }
}

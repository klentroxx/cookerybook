package hu.cookerybook.core.model;

import javafx.beans.property.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private IntegerProperty userRole = new SimpleIntegerProperty(this, "userRole");
    private StringProperty username = new SimpleStringProperty(this, "username");
    private StringProperty email = new SimpleStringProperty(this, "email");
    private StringProperty password = new SimpleStringProperty(this, "password");
    private StringProperty firstName = new SimpleStringProperty(this, "firstName");
    private StringProperty lastName = new SimpleStringProperty(this, "lastName");
    private ObjectProperty<Date> registerDate = new SimpleObjectProperty<>(this, "registerDate");

    public User() {
    }

    public User(IntegerProperty userRole, StringProperty username, StringProperty email, StringProperty password) {
        this.userRole = userRole;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(IntegerProperty userRole, StringProperty username, StringProperty email, StringProperty password, StringProperty firstName) {
        this.userRole = userRole;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
    }

    public User(IntegerProperty userRole, StringProperty username, StringProperty email, StringProperty password, StringProperty firstName, StringProperty lastName) {
        this.userRole = userRole;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(IntegerProperty userRole, StringProperty username, StringProperty email, StringProperty password, StringProperty firstName, StringProperty lastName, ObjectProperty<Date> registerDate) {
        this.userRole = userRole;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registerDate = registerDate;
    }

    public User(IntegerProperty id, IntegerProperty userRole, StringProperty username, StringProperty email, StringProperty password, StringProperty firstName, StringProperty lastName) {
        this.id = id;
        this.userRole = userRole;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(IntegerProperty id, IntegerProperty userRole, StringProperty username, StringProperty email, StringProperty password, StringProperty firstName, StringProperty lastName, ObjectProperty<Date> registerDate) {
        this.id = id;
        this.userRole = userRole;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registerDate = registerDate;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getUserRole() {
        return userRole.get();
    }

    public IntegerProperty userRoleProperty() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole.set(userRole);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public Date getRegisterDate() {
        return registerDate.get();
    }

    public ObjectProperty<Date> registerDateProperty() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate.set(registerDate);
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

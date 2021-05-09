package tr.edu.yildiz.erentutus.entity;

import java.util.ArrayList;

public class User {
    private String Name;
    private String Surname;
    private String Username;
    private String Email;
    private String Phone;
    private String BirthDate;
    private String Photo;
    private String PasswordHash;
    private String PasswordSalt;
    public static ArrayList<User> users = new ArrayList<User>();

    public User(String name, String surname, String username, String email, String phone, String birthDate, String photo, String passwordHash, String passwordSalt) {
        Name = name;
        Surname = surname;
        Username = username;
        Email = email;
        Phone = phone;
        BirthDate = birthDate;
        Photo = photo;
        PasswordHash = passwordHash;
        PasswordSalt = passwordSalt;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        this.BirthDate = birthDate;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return PasswordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        PasswordSalt = passwordSalt;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }
}

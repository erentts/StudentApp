package tr.edu.yildiz.erentutus.entity;

public class User {
    private String Name;
    private String Surname;
    private String Username;
    private String Email;
    private String Phone;
    private String BirthDate;
    private String Password;

    public User(String name, String surname,String username, String email, String phone, String birthDate, String password) {
        Name = name;
        Surname = surname;
        Username = username;
        Email = email;
        Phone = phone;
        BirthDate = birthDate;
        Password = password;
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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

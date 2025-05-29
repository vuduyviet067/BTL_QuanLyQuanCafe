package Com.Model;

import java.time.LocalDate;

public class Model_User {
    private String id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String phone;
    private String address;
    private String username;
    private String password;
    private String email;
    private String position;
    private byte[] image;
    private String adress;

    public Model_User(String id, String name, String gender, LocalDate birthDate, String phone, String address, String username, String password, String email, String position, byte[] image) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phone = phone;
        this.adress = address;
        this.username = username;
        this.password = password;
        this.email = email;
        this.position = position;
        this.image = image;
    }

    public Model_User(String id, String name, String gender, LocalDate birthDate, String phone, String address, String username, String password, String email, String position) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phone = phone;
        this.adress = address;
        this.username = username;
        this.password = password;
        this.email = email;
        this.position = position;
    }

    public Model_User(String name, String gender, LocalDate birthDate, String phone, String adress, String username, String password, String email, String position) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phone = phone;
        this.adress = adress;
        this.username = username;
        this.password = password;
        this.email = email;
        this.position = position;
    }

    public Model_User(String name, String gender, LocalDate birthDate, String phone, String address, String username, String password, String email, String position, byte[] image) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phone = phone;
        this.adress = address;
        this.username = username;
        this.password = password;
        this.email = email;
        this.position = position;
        this.image = image;
    }

    public Model_User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return adress;
    }

    public void setAddress(String address) {
        this.adress = adress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    
    
}

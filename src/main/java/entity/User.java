package entity;

public class User {

    private int id;
    private String username;
    private String password;
    private String phone;
    private String address;

    public User() {
    }

    public User(int id, String username, String password, String phone, String addres) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.address = addres;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String addres) {
        this.address = addres;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

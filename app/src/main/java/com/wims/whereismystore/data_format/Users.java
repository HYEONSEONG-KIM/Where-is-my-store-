package com.wims.whereismystore.data_format;

public class Users {

    private String pin;
    private String email;
    private String phone;
    private String name;
    private String password;
    private String created_at;
    private String lastacess_at;
    private int state;

    public Users(String email, String password,  String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password +'\''+
                '}';
    }

}

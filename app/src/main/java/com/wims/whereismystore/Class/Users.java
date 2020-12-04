package com.wims.whereismystore.Class;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;

public class Users implements Serializable {

    @PropertyName("email")
    private String email;
    @PropertyName("name")
    private String name;
    @PropertyName("password")
    private String password;

    public Users() {}

    public Users(String email,String name,String password){
        this.email = email;
        this.name = name;
        this.password = password;
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

}

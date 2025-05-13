package spring.boot.api.nger.Enums;

import jakarta.persistence.Enumerated;

public enum Role {

    ADMIN("admin"),
    USER("user");


    Role(String Role) {

    }

    public Role getAdmin(){
        return ADMIN;
    }

    public Role getUser(){
        return USER;
    }

}

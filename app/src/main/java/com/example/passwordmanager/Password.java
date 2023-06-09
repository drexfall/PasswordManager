package com.example.passwordmanager;

public class Password {
    private int id;
    private String username;
    private String password;
    private String website;
    private String type;

    public Password(int id, String username, String password, String website, String type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.website = website;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public String getWebsite() {
        return website;
    }
    public String getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
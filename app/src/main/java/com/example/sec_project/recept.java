package com.example.sec_project;

public class recept {

    int total,numofpeople;
    String email,name,cap;
    public recept() {

    }
    public recept(int total, int numofpeople, String email, String name, String cap) {
        this.total = total;
        this.numofpeople = numofpeople;
        this.email = email;
        this.name = name;
        this.cap = cap;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNumofpeople() {
        return numofpeople;
    }

    public void setNumofpeople(int numofpeople) {
        this.numofpeople = numofpeople;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }
}

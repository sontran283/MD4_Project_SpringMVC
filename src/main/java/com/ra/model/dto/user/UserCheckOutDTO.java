package com.ra.model.dto.user;

public class UserCheckOutDTO {
    private String name;
    private String email;
    private String address;
    private String phone;
    private String note;
    private int status;

    public UserCheckOutDTO() {
    }

    public UserCheckOutDTO(String name, String email, String address, String phone, String note, int status) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.status = status;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

package com.ra.model.entity;

import javax.validation.constraints.*;

public class User {

    private int userId;
    private String userImg;
    @NotEmpty(message = "not null")
    private String userName;
    @NotEmpty(message = "not null")
    @Email(message = "phải có @")
    private String userEmail;
    @NotEmpty(message = "not null")
    private String userAddress;
    @Pattern(regexp = "(0[3|5|7|8|9])+([0-9]{8})\\b", message = "sai")
    private String userPhoneNumber;
    @Size(min = 3, max = 100, message = "not null")
    private String userPassword;
    private boolean role;
    private boolean status;

    public User() {
    }

    public User(int userId, String userName, String userEmail, String userAddress, String userPhoneNumber, String userPassword, boolean role, boolean status, String userImg) {
        this.userId = userId;
        this.userImg = userImg;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
        this.role = role;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean getRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

package com.ra.model.entity;

public class User {
    private int userId;
    private String userImg;
    private String userName;
    private String userEmail;
    private String userAddress;
    private double userPhoneNumber;
    private String userPassword;
    private boolean role;
    private boolean status;

    public User() {
    }

    public User(int userId, String userName, String userEmail, String userAddress, double userPhoneNumber, String userPassword, boolean role, boolean status ,String userImg) {
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

    public double getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(double userPhoneNumber) {
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

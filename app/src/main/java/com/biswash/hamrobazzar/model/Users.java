package com.biswash.hamrobazzar.model;

public class Users {

    private String fullname;
    private String email;
    private String password;
    private String phone;
    private String mobilephone;
    private String address1;
    private String address2;
    private String address3;
    private String image;



    public Users(String fullname, String email, String password, String phone, String mobilephone, String address1, String address2, String address3, String image) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.mobilephone = mobilephone;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.image = image;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

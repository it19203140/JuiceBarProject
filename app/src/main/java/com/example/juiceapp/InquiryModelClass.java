package com.example.juiceapp;

public class InquiryModelClass {

    Integer id;
    String inquiry;
    String email;

    public InquiryModelClass(String inquiry, String email) {
        this.inquiry = inquiry;
        this.email = email;
    }

    public InquiryModelClass(Integer id, String inquiry, String email) {
        this.id = id;
        this.inquiry = inquiry;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInquiry() {
        return inquiry;
    }

    public void setInquiry(String inquiry) {
        this.inquiry= inquiry;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

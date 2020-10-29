package com.udemy.multiinstance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "Patient")
public class Patient {

    @Id
    private String id;

    private String mobileNumber;

    private String emailId;

    private String name;

    private String password;

    private Instant createDateTime;

    private Instant updateDateTime;

    private boolean registered;

    private String failureReason;

    private String country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Instant createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Instant getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Instant updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

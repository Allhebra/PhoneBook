package com.bereg.phonebook.models;

/**
 * Created by 1 on 21.03.2018.
 */

public class ContactModel {

    private int id;
    private String firstName;
    private String secondName;
    private String country;
    private String city;
    private String street;
    private String buildingNumber;
    private String phoneNumber;
    private boolean friendsGroupContained;
    private boolean peoplesGroupContained;
    private boolean animalsGroupContained;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isFriendsGroupContained() {
        return friendsGroupContained;
    }

    public void setFriendsGroupContained(boolean friendsGroupContained) {
        this.friendsGroupContained = friendsGroupContained;
    }

    public boolean isPeoplesGroupContained() {
        return peoplesGroupContained;
    }

    public void setPeoplesGroupContained(boolean peoplesGroupContained) {
        this.peoplesGroupContained = peoplesGroupContained;
    }

    public boolean isAnimalsGroupContained() {
        return animalsGroupContained;
    }

    public void setAnimalsGroupContained(boolean animalsGroupContained) {
        this.animalsGroupContained = animalsGroupContained;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

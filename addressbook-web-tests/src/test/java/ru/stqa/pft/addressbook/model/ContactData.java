package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String fname;
    private final String lname;
    private final String phone;
    private final String email;
    private final String group;

    public ContactData(String fname, String lname, String phone, String email, String group) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(fname, that.fname) && Objects.equals(lname, that.lname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname, lname);
    }
}

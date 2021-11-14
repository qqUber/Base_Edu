package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String mname;
    private final String fname;
    private final String lname;
    private final String nick;
    private final String phone;
    private final String mphone;
    private final String email;
    private final String bmonth;
    private final String year;
    private final String city;
    private final String group;

    public ContactData(String mname, String fname, String lname, String nick, String phone, String mphone, String email, String bmonth, String year, String city, String group) {
        this.mname = mname;
        this.fname = fname;
        this.lname = lname;
        this.nick = nick;
        this.phone = phone;
        this.mphone = mphone;
        this.email = email;
        this.bmonth = bmonth;
        this.year = year;
        this.city = city;
        this.group = group;
    }

    public String getMname() {
        return mname;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getNick() {
        return nick;
    }

    public String getPhone() {
        return phone;
    }

    public String getMphone() {
        return mphone;
    }

    public String getEmail() {
        return email;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getYear() {
        return year;
    }

    public String getCity() {
        return city;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(fname, that.fname) && Objects.equals(lname, that.lname) && Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname, lname, group);
    }
}

package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String fname;
    private String lname;
    private String hphone;
    private String mphone;
    private String wphone;
    private String email1;
    private String email2;
    private String email3;
    private String allphones;
    private String allemails;
    private String contactaddress;
    private String group;

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getHphone() {
        return hphone;
    }

    public String getMphone() {
        return mphone;
    }

    public String getWphone() {
        return wphone;
    }

    public String getGroup() {
        return group;
    }

    public String getAllphones() {
        return allphones;
    }
    public String getEmail() {
        return email1;
    }
    public String getContactAddress() {
        return contactaddress;
    }
    public String getAllEmails() {
        return allemails;
    }
    public String getEmail2() {
        return email2;
    }
    public String getEmail3() {
        return email3;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withPhones(String allPhones) {
        this.allphones = allPhones;
        return this;
    }


    public ContactData withEmails(String allEmails) {
        this.allemails = allEmails;
        return this;
    }


    public ContactData withAddress(String contactAddress) {
        this.contactaddress = contactAddress;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFname(String fname) {
        this.fname = fname;
        return this;
    }

    public ContactData withLname(String lname) {
        this.lname = lname;
        return this;
    }

    public ContactData withHphone(String hphone) {
        this.hphone = hphone;
        return this;
    }

    public ContactData withMphone(String mphone) {
        this.mphone = mphone;
        return this;
    }

    public ContactData withWphone(String wphone) {
        this.wphone = wphone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email1 = email;
        return this;

    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
          "id=" + id +
          ", fname='" + fname + '\'' +
          ", lname='" + lname + '\'' +
          '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(fname, that.fname) && Objects.equals(lname, that.lname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fname, lname);
    }
}

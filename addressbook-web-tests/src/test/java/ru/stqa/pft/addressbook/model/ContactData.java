package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contacts")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    private int id = Integer.MAX_VALUE;
    @Expose
    @Basic
    @Column(name = "firstname")
    private String fname;
    @Expose
    @Basic
    @Column(name = "lastname")
    private String lname;
    @Column(name = "home")
    @Type(type = "text")
    private String hphone;
    @Type(type = "text")
    private String mobile;
    @Type(type = "text")
    private String work;
    @Type(type = "text")
    private String email;
    @Type(type = "text")
    private String email2;
    @Type(type = "text")
    private String email3;
    @Transient
    private String allphones;
    @Transient
    private String allemails;
    @Transient
    private String address;
    @Transient
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();


    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

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

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getAllphones() {
        return allphones;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
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

    public ContactData withAllPhones(String allPhones) {
        this.allphones = allPhones;
        return this;
    }


    public ContactData withAllEmails(String allEmails) {
        this.allemails = allEmails;
        return this;
    }


    public ContactData withAddress(String address) {
        this.address = address;
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
        this.mobile = mphone;
        return this;
    }

    public ContactData withWphone(String wphone) {
        this.work = wphone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;

    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactData addGroup(GroupData group) {
        groups.add(group);
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

package ru.stqa.pft.addressbook;

public class fillAddnewEnum {
    private final String mname;
    private final String fname;
    private final String lname;
    private final String nick;
    private final String hphone;
    private final String mphone;
    private final String email;
    private final String bmonth;
    private final String year;
    private final String getGroup;
    private final String city;

    public fillAddnewEnum(String mname, String fname, String lname, String nick, String hphone, String mphone, String email, String bmonth, String year, String getGroup, String city) {
        this.mname = mname;
        this.fname = fname;
        this.lname = lname;
        this.nick = nick;
        this.hphone = hphone;
        this.mphone = mphone;
        this.email = email;
        this.bmonth = bmonth;
        this.year = year;
        this.getGroup = getGroup;
        this.city = city;
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

    public String getHphone() {
        return hphone;
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

    public String getGetGroup() {
        return getGroup;
    }

    public String getCity() {
        return city;
    }
}

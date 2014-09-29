package model;

import entity.PersonEntity;

/**
 * @author Lars
 */
public class Person {

    int id;
    String fName;
    String lName;
    String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person(String fName, String lName, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }

    public Person(PersonEntity p) {
        this.fName = p.getfName();
        this.lName = p.getlName();
        this.phone = p.getPhone();
        this.id = p.getId();
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" + "fName=" + fName + ", lName=" + lName + ", phone=" + phone + ", id=" + id + '}';
    }

}

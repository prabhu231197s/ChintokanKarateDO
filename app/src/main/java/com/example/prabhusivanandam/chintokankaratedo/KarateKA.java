package com.example.prabhusivanandam.chintokankaratedo;

/**
 * Created by Prabhu Sivanandam on 10-May-17.
 */

public class KarateKA {
    private String role,name,age,phone,email,loginFlag,belt,address,emergency_number,ka_id,bloodgroup,father_name,mother_name,dojo,username,password;


    public String getKa_id() {
        return ka_id;
    }

    public void setKa_id(String ka_id) {
        this.ka_id = ka_id;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getDojo() {
        return dojo;
    }

    public void setDojo(String dojo) {
        this.dojo = dojo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public  KarateKA()
    {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public KarateKA(String name, String age, String phone, String email, String belt, String address, String emergency_number, String ka_id, String loginFlag, String bloodgroup, String father_name, String mother_name, String dojo, String username, String password, String role) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.father_name=father_name;
        this.mother_name=mother_name;
        this.username=username;
        this.password=password;
        this.role=role;
        this.dojo=dojo;
        this.loginFlag = loginFlag;
        this.bloodgroup=bloodgroup;
        this.loginFlag="0";
        this.belt = belt;
        this.address = address;
        this.emergency_number = emergency_number;
        this.ka_id= ka_id;
    }
    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String getBelt() {
        return belt;
    }

    public void setBelt(String belt) {
        this.belt = belt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergency_number() {
        return emergency_number;
    }

    public void setEmergency_number(String emergency_number) {
        this.emergency_number = emergency_number;
    }

}

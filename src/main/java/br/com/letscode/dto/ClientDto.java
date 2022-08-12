package br.com.letscode.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ClientDto {

    public String name;
    public int age;
    public String vat;
    public String email;



//    public ClientDto(String name, int age, String vat, String email) {
//        this.name = name;
//        this.age = age;
//        this.vat = vat;
//        this.email = email;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", vat='" + vat + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


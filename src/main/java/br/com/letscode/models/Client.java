package br.com.letscode.models;

import br.com.letscode.dto.ClientDto;
import io.quarkus.hibernate.orm.panache.PanacheEntity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    @Size(
            min = 5,
            max = 100,
            message = "O nome deve ter no minimo {min} e no maximo {max} caracteres"
    )
    private String name;

    @NotNull
    @Min(18)
    private int age;

    @NotNull
    @Pattern(regexp = "^[A-Z]{2}\\d{9}$")
    private String vat;

    @NotNull
    @Email
    private String email;

//    private Categoria categoria;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

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

//    public Categoria getCategoria() {
//        return categoria;
//    }
//
//    public void setCategoria(Categoria categoria) {
//        this.categoria = categoria;
//    }
}


package br.com.letscode.form;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClientForm {

    @NotNull
    @NotEmpty
    @Size(
            min = 5,
            max = 100,
            message = "O nome deve ter no minimo {min} e no maximo {max} caracteres"
    )
    public String name;
    public int age;
    public String vat;
    public String email;
    public long categoriaCodigo;


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

    public long getCategoriaCodigo() {
        return categoriaCodigo;
    }

    public void setCategoriaCodigo(long codigoCategoria) {
        this.categoriaCodigo = codigoCategoria;
    }
}



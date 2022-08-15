package br.com.letscode;

import br.com.letscode.models.Categoria;
import br.com.letscode.services.CategoriaService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.inject.Inject;

@QuarkusMain
public class Main {
    public static void main(String[] args) throws Exception {

        Quarkus.run(args);

    }
}

package br.com.letscode.services;

import br.com.letscode.models.Categoria;
import br.com.letscode.models.Client;
import br.com.letscode.repository.CategoriaRepository;
import br.com.letscode.repository.ClientRepository;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@RequestScoped
@Traced
public class CategoriaService {

    @Inject
    CategoriaRepository categoriaRepository;

    public List<Categoria> listCategoria() {
        return categoriaRepository.listAll();
    }

    @Transactional
    public Categoria createCategoria(Categoria categoria) throws Exception {

        try {
            categoriaRepository.persist(categoria);
            return categoria;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Categoria getCategoria(long id) throws Exception {

        try {
            Categoria categoria = categoriaRepository.findById(id);

            return categoria;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Categoria updateCategoria(long id, Categoria categoria) {

        Categoria categoriaOld = categoriaRepository.findById(id);
        if (categoria == null) {
            throw new NotFoundException();
        }
        categoriaOld.setCodigo(categoria.getCodigo());
        categoriaOld.setNome(categoria.getNome());

        return categoriaOld;
    }

    @Transactional
    public void deleteCategoria(long id) throws Exception {

        Categoria categoria = categoriaRepository.findById(id);
        if(categoria == null) {
            throw new NotFoundException();
        }
        categoriaRepository.delete(categoria);

    }

}
package br.com.letscode.services;

import br.com.letscode.dto.ClientDto;
import br.com.letscode.form.ClientForm;
import br.com.letscode.models.Categoria;
import br.com.letscode.models.Client;
import br.com.letscode.repository.CategoriaRepository;
import br.com.letscode.repository.ClientRepository;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ClientService {

    @Inject
    ClientRepository clientRepository;

    @Inject
    CategoriaRepository categoriaRepository;

    public List<ClientDto> listClients() {

        ModelMapper modelMapper = new ModelMapper();

        List<ClientDto> listClient = clientRepository.streamAll()
                .map(c -> modelMapper.map(c, ClientDto.class))
                .collect(Collectors.toList());

        return listClient;
    }

    @Transactional
    public ClientForm createClient(ClientForm clientForm) throws Exception {

        Categoria categoria = categoriaRepository.findById(clientForm.getCategoriaCodigo());

        Client client = new Client();
        client.setName(clientForm.getName());
        client.setAge(clientForm.getAge());
        client.setVat(clientForm.getVat());
        client.setEmail(clientForm.getEmail());
        client.setCategoria(categoria);

        ModelMapper modelMapper = new ModelMapper();
        ClientForm clientForm2 = modelMapper.map(client, ClientForm.class);

        System.out.println(clientForm2);

        try {
            clientRepository.persist(client);
            return clientForm2;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ClientDto getClient(long id) throws Exception {

        try {
            Client client = clientRepository.findById(id);
            ModelMapper modelMapper = new ModelMapper();
            ClientDto clientDto = modelMapper.map(client, ClientDto.class);
            return clientDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Client updateClient(long id, ClientForm clientForm) {

        Client entity = clientRepository.findById(id);
        Categoria categoria = categoriaRepository.findById(clientForm.getCategoriaCodigo());

        System.out.println(categoria.getNome());

        if (entity == null) {
            throw new NotFoundException();
        }

        entity.setName(clientForm.getName());
        entity.setAge(clientForm.getAge());
        entity.setVat(clientForm.getVat());
        entity.setEmail(clientForm.getEmail());
        entity.setCategoria(categoria);

        return entity;
    }

    @Transactional
    public void deleteClient(long id) {

        Client entity = clientRepository.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        clientRepository.delete(entity);

    }

}
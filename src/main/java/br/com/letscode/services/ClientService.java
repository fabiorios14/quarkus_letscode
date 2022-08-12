package br.com.letscode.services;

import br.com.letscode.dto.ClientDto;
import br.com.letscode.models.Client;
import br.com.letscode.repository.ClientRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.eclipse.microprofile.opentracing.Traced;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestScoped
@Traced
public class ClientService {

    @Inject
    ClientRepository clientRepository;

    public List<ClientDto> listClients() {

        ModelMapper modelMapper = new ModelMapper();

        List<ClientDto> listClient = clientRepository.streamAll()
                .map(c -> modelMapper.map(c, ClientDto.class))
                .collect(Collectors.toList());

        //return clientRepository.findAll().project(ClientDto.class).stream().collect(Collectors.toList());
        //return clientRepository.listAll();
        return listClient;
    }

    @Transactional
    public Client createClient(Client client) throws Exception {

        try {
            clientRepository.persist(client);
            return client;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ClientDto getClient(long id) throws Exception {

        try {
            Client client = clientRepository.findById(id);
            //List<ClientDto> clientDto = clientRepository.find("id", id).project(ClientDto.class).stream().collect(Collectors.toList());
            ModelMapper modelMapper = new ModelMapper();
            ClientDto clientDto = modelMapper.map(client, ClientDto.class);
            return clientDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Client updateClient(long id, Client client) {

        Client entity = clientRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }

        entity.setName(client.getName());
        entity.setAge(client.getAge());
        entity.setVat(client.getVat());
        entity.setEmail(client.getEmail());

        return entity;
    }

    @Transactional
    public void deleteClient(long id) throws Exception {

        Client entity = clientRepository.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        clientRepository.delete(entity);

    }

}
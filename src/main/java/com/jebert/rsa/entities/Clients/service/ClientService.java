package com.jebert.rsa.entities.Clients.service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jebert.rsa.entities.Clients.helper.ClientSex;
import com.jebert.rsa.entities.Clients.helper.ClientType;
import com.jebert.rsa.entities.Clients.model.Client;
import com.jebert.rsa.entities.Clients.repository.ClientRepository;
import com.jebert.rsa.entities.Clients.vo.ClientVo;
import com.jebert.rsa.entities.address.model.Address;
import com.jebert.rsa.entities.address.model.vo.CEPVo;
import com.jebert.rsa.entities.address.service.AddressService;
import com.jebert.rsa.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressService addressService;

    @Transactional
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> findClientById(UUID id) {
        return Optional.of(clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Client Not Found with ID:" + id.toString())));
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public void delete(UUID uuid) {
        clientRepository.delete(findClientById(uuid).get());
        ;
    }

    public Client convertFromClientVo(ClientVo clientVo){
        Address address = addressService.findAddressByCep(new CEPVo(clientVo.cep())).get();
        address.setNumber(clientVo.addressNumber());
        address.setDeliveryAddress(clientVo.addressDelivery());
        address.setComplement(clientVo.complement());

        Client client = new Client();

        client.setUserName(clientVo.userName());
        client.setFullName(clientVo.fullName());
        client.setPassword(clientVo.password());
        client.setAccountNonExpired(false);
        client.setAccountNonLocked(false);
        client.setCredentialsNonExpired(true);
        client.setEnabled(true);
        client.setType(ClientType.fromAcronym(clientVo.type()));
        client.setSex(ClientSex.fromAcronym(clientVo.sex()));
        client.setCpf(clientVo.cpf());
        client.setBirthday(clientVo.birthday());
        client.setMailoffers(clientVo.mailOffers());
        client.setAddresses(address);

            return client;

    
    }/**        














        String userName, String fullName, String password, String email, Boolean accountNonExpired,
            Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled, ClientType type, ClientSex sex,
            String cpf, Date birthday, boolean mailoffers, Address address
             
    
    
    @NotNull(message = "Please enter Username!") String userName,
        @NotNull(message = "Please enter fullName!") String fullName,
        @NotNull(message = "Please enter password!") String password,
        @NotNull(message = "Please enter a e-mail!") @Email String email,
        @NotNull(message = "Please select type!") String type,
        String sex,
        @NotNull(message = "Please enter cpf!") String cpf,
        @NotNull(message = "Please enter Birthday!") Date birthday,
        String mailOffers,
        @NotNull(message = "Please enter cpf!") String cep,
        @NotNull(message = "Please enter Address Number!") String addressNumber,
        String complement,
        Boolean addressDelivery */


}

package com.jebert.rsa.entities.address.service;

import com.jebert.rsa.entities.address.helper.AddressVo;
import com.jebert.rsa.entities.address.helper.AddressVoVC;
import com.jebert.rsa.entities.address.model.Address;
import com.jebert.rsa.entities.address.repository.AddressRepository;
import com.jebert.rsa.entities.city.service.CityService;
import com.jebert.rsa.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    CityService cityService;
    @Autowired
    private AddressRepository addressRepository;

    public AddressService() {
    }

    @Transactional
    public Address saveAddress(Address address) {
        return address = addressRepository.save(address);
    }

    public List<Address> findAllAddresss() {
        return addressRepository.findAll();
    }

    public Optional<Address> findAddressById(UUID id) {
        Optional<Address> address = addressRepository.findById(id);
        return Optional.of(address.orElseThrow(() -> new ObjectNotFoundException("Address not found with id:" + id.toString())));
    }

    public Optional<Address> findAddressByCep(String cep) {
        AddressVoVC x = new RestTemplate().getForEntity("https://viacep.com.br/ws/" + cep + "/json/", AddressVoVC.class).getBody();

        if (x.cep() == null) throw new ObjectNotFoundException("CEP: " + cep + " is not valid!");
        return Optional.of(convertFromAddressVoForViaCep(x));
    }

    public List<Address> findAddressByPartial(String state, String city, String street) {

        List<Address> lista = new ArrayList<>();

        AddressVoVC[] lista1 = new RestTemplate()
                .getForEntity("https://viacep.com.br/ws/" + state + "/" + city + "/" + street + "/json/", AddressVoVC[].class).getBody();

        for (AddressVoVC add : lista1) {
            lista.add(convertFromAddressVoForViaCep(add));
        }
        return lista;
    }

    public void delete(UUID uuid) {
        addressRepository.delete(findAddressById(uuid).get());
        ;
    }

    public Address convertFromAddressVoForViaCep(AddressVoVC vo) {
        return new Address(null, vo.cep(), vo.logradouro(), null, vo.complemento(), vo.bairro(), false, cityService.findCityByIbgeCode(vo.ibge()));
    }

    public Address ConvertAddressFromVo(AddressVo addressVo) {
        Address address = findAddressByCep(addressVo.cep()).get();
        address.setNumber(addressVo.number());
        address.setComplement(addressVo.complement());
        address.setDeliveryAddress(addressVo.deliveryAddress());

        return address;
    }
}
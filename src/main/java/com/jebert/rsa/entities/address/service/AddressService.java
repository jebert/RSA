package com.jebert.rsa.entities.address.service;

import com.jebert.rsa.entities.address.model.vo.AddressVo;
import com.jebert.rsa.entities.address.model.vo.AddressVoVC;
import com.jebert.rsa.entities.address.model.Address;
import com.jebert.rsa.entities.address.model.vo.CEPVo;
import com.jebert.rsa.entities.address.repository.AddressRepository;
import com.jebert.rsa.entities.city.service.CityService;
import com.jebert.rsa.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    CityService cityService;
    @Autowired
    private AddressRepository addressRepository;
    private String URL = "https://viacep.com.br/ws/";

    public AddressService() {
    }

    @Transactional
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> findAllAddress() {
        return addressRepository.findAll();
    }

    public Optional<Address> findAddressById(UUID id) {
        return Optional.of(addressRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Address not found with id:" + id.toString())));
    }

    public Optional<Address> findAddressByCep(CEPVo cep) {

        AddressVoVC x = new RestTemplate().getForEntity(URL + cep.cep() + "/json/", AddressVoVC.class).getBody();

        if (x.cep() == null)
            throw new ObjectNotFoundException("CEP: " + cep + " is not valid!");
        return Optional.of(convertFromAddressVoForViaCep(x));
    }

    public List<Address> findAddressByPartial(String state, String city, String street) {

        return Arrays.stream(
                new RestTemplate().getForEntity(URL + state + "/" + city + "/" + street + "/json/", AddressVoVC[].class)
                        .getBody())
                .toList().stream().map(this::convertFromAddressVoForViaCep).collect(Collectors.toList());

    }

    public void delete(UUID uuid) {
        addressRepository.delete(findAddressById(uuid).get());
        ;
    }

    public Address convertFromAddressVoForViaCep(AddressVoVC vo) {
        return new Address(null, vo.cep(), vo.logradouro(), null, vo.complemento(), vo.bairro(), false,
                cityService.findCityByIbgeCode(vo.ibge()));
    }

    public Address ConvertAddressFromVo(AddressVo addressVo) {
        Address address = findAddressByCep(new CEPVo(addressVo.cep())).get();
        address.setNumber(addressVo.number());
        address.setComplement(addressVo.complement());
        address.setDeliveryAddress(addressVo.deliveryAddress());

        return address;
    }
}
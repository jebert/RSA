package com.jebert.rsa.entities.address.helper;

public record AddressVoVC(String cep,
                          String logradouro,
                          String complemento,
                          String bairro,
                          String localidade,
                          String uf,
                          Integer ibge,
                          Integer gia,
                          Integer ddd,
                          Integer siafi) {

}

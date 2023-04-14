package com.jebert.rsa.entities.address.model.vo;

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

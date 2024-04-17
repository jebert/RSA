package com.jebert.rsa.entities.Clients.vo;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ClientVo(
        @NotNull(message = "Please enter Username!") String userName,
        @NotNull(message = "Please enter fullName!") String fullName,
        @NotNull(message = "Please enter password!") String password,
        @NotNull(message = "Please enter a e-mail!") @Email String email,
        @NotNull(message = "Please select type!") String type,
        String sex,
        @NotNull(message = "Please enter cpf!") String cpf,
        @NotNull(message = "Please enter Birthday!") Date birthday,
        boolean mailOffers,
        @NotNull(message = "Please enter cpf!") String cep,
        @NotNull(message = "Please enter Address Number!") String addressNumber,
        String complement,
        Boolean addressDelivery
        ){
}

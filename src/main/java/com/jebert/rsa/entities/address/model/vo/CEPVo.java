package com.jebert.rsa.entities.address.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CEPVo(@NotBlank(message = "Cep is required!")
                    @Pattern(regexp = "[0-9]{5}-[0-9]{3}|[0-9]{5}[0-9]{3}", message = "Please insert a valid CEP! - XXXXXX-XXX")
                    String cep) {
}

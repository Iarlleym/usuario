package com.engcode.usuario.business.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTO {

    private long id;
    private String rua;
    private long numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;

}

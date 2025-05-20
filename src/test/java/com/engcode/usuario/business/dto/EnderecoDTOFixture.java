package com.engcode.usuario.business.dto;

public class EnderecoDTOFixture {

    public static EnderecoDTO build (

            long id,
            String rua,
            Long numero,
            String complemento,
            String cidade,
            String estado,
            String cep

    ) {
        return new EnderecoDTO(id, rua, numero, complemento, cidade, estado, cep);
    }

}

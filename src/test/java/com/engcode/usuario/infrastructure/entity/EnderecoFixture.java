package com.engcode.usuario.infrastructure.entity;

public class EnderecoFixture {

    public static Endereco build (

            long id,
            String rua,
            Long numero,
            String complemento,
            String cidade,
            String estado,
            String cep,
            Long usuario_id

    ) {
        return new Endereco(id, rua, numero, complemento, cidade, estado, cep, usuario_id);
    }

}

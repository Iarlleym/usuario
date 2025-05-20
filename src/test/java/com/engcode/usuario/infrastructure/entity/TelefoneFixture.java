package com.engcode.usuario.infrastructure.entity;

public class TelefoneFixture {

    public static Telefone build (

            long id,
            String numero,
            String ddd,
            Long usuario_id

    ) {
        return new Telefone(id, numero, ddd, usuario_id);
    }

}

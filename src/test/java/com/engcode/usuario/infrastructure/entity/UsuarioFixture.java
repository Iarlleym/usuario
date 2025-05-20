package com.engcode.usuario.infrastructure.entity;

import java.util.List;

public class UsuarioFixture {

    public static Usuario bulid (

            long id,
            String nome,
            String email,
            String senha,
            List<Endereco> enderecos,
            List<Telefone> telefones

    ){
        return new Usuario(id, nome, email, senha, enderecos, telefones);
    }

}

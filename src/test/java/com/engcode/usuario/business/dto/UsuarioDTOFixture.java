package com.engcode.usuario.business.dto;

import java.util.List;

public class UsuarioDTOFixture {
    public static UsuarioDTO build (

            String nome,
            String email,
            String senha,
            List<EnderecoDTO> enderecos,
            List<TelefoneDTO> telefones

    ) {
        return new UsuarioDTO(nome, email, senha, enderecos, telefones);
    }
}

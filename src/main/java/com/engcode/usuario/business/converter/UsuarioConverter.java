package com.engcode.usuario.business.converter;


import com.engcode.usuario.business.dto.EnderecoDTO;
import com.engcode.usuario.business.dto.TelefoneDTO;
import com.engcode.usuario.business.dto.UsuarioDTO;
import com.engcode.usuario.infrastructure.entity.Endereco;
import com.engcode.usuario.infrastructure.entity.Telefone;
import com.engcode.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario (UsuarioDTO usuarioDTO) {

        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                //Para contruir as listas de telefone e endereço foi feito os metodos para cada um.
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();
    }

    //Metodo para transformar cada lista de endereço e pegar cada um dos dados
    //java stream().map é uma alternativa ao for e foreach para iterar sobre um determinada lista.
    public List<Endereco> paraListaEndereco (List<EnderecoDTO> enderecoDTOS) {
        return enderecoDTOS.stream().map(this::paraEndereco).toList();

        /*se quiser usar o for each
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDTO enderecoDTO : enderecoDTOS) {
            enderecos.add(paraEndereco(enderecoDTO));
        }
        return enderecos;
         */

    }


    //metodo para conversão de um único endereço
    public Endereco paraEndereco (EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .build();
    }

    //FAZENDO A MESMA COISA PARA O TELEFONE.

    public List<Telefone> paraListaTelefone (List<TelefoneDTO> telefoneDTOS) {
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone (TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    // FAZENDO A CONVERÇÃO AO CONTRÁRIO


    public UsuarioDTO paraUsuarioDTO (Usuario usuarioDTO) {

        return UsuarioDTO.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                //Para contruir as listas de telefone e endereço foi feito os metodos para cada um.
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuarioDTO.getTelefones()))
                .build();
    }

    //Metodo para transformar cada lista de endereço e pegar cada um dos dados
    //java stream().map é uma alternativa ao for e foreach para iterar sobre um determinada lista.
    public List<EnderecoDTO> paraListaEnderecoDTO (List<Endereco> enderecoDTOS) {
        return enderecoDTOS.stream().map(this::paraEnderecoDTO).toList();

        /*se quiser usar o for each
        List<EnderecoDTO> enderecos = new ArrayList<>();
        for (Endereco enderecoDTO : enderecoDTOS) {
            enderecos.add(paraEndereco(enderecoDTO));
        }
        return enderecos;
         */

    }


    //metodo para conversão de um único endereço
    public EnderecoDTO paraEnderecoDTO (Endereco enderecoDTO) {
        return EnderecoDTO.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .build();
    }

    //FAZENDO A MESMA COISA PARA O TELEFONE.

    public List<TelefoneDTO> paraListaTelefoneDTO (List<Telefone> telefoneDTOS) {
        return telefoneDTOS.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO (Telefone telefoneDTO) {
        return TelefoneDTO.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    //Metodo para fazer a conparação e atualizar nome, email, senha. sem endereços e telefones.

    public Usuario upDateDeUsuario (UsuarioDTO usuarioDTO, Usuario usuario) {
       return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : usuario.getNome())
                //Faz um if. se o nome no usuarioDTO estiver vazio (Ou seja usuario não pasou uma senha nova) pega o nome no usuario entity.
                //faz a mesma coisa para a senha e email, só os endereços e telefones que vão ter metodos proprios pq são lista.
                .id(usuario.getId())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : usuario.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuario.getEmail())
                .enderecos(usuario.getEnderecos())
                .telefones(usuario.getTelefones())
                .build();
    }





}

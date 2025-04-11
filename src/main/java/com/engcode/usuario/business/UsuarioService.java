package com.engcode.usuario.business;

import com.engcode.usuario.business.converter.UsuarioConverter;
import com.engcode.usuario.business.dto.UsuarioDTO;
import com.engcode.usuario.infrastructure.entity.Usuario;
import com.engcode.usuario.infrastructure.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {



    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    //Os DTO são criados para não expor os dados da entidade, por isso usase os UsuarioDTO, EnderecoDTO e TelefoneDTO
    //CRIANDO O METODO DE SALVAR OS DADOS DO USUÁRIO.
    public UsuarioDTO salvaUsuario (UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO); //Esse objeto converte o UsuarioDTO para as Entity
        usuario = usuarioRepository.save(usuario); //Salva a informação no banco de dados de usuario como Entity.
        return usuarioConverter.paraUsuarioDTO(usuario);//retorna a informação convertendo o usario entity para usuarioDTO.
    }


}

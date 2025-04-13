package com.engcode.usuario.business;

import com.engcode.usuario.business.converter.UsuarioConverter;
import com.engcode.usuario.business.dto.UsuarioDTO;
import com.engcode.usuario.infrastructure.entity.Usuario;
import com.engcode.usuario.infrastructure.exceptions.ConflictException;
import com.engcode.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.engcode.usuario.infrastructure.repository.UsuarioRepository;
import com.engcode.usuario.infrastructure.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {



    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder; //Responsavel por fazer a encriptação das senhas
    private final JwtUtil jwtUtil;

    //Os DTO são criados para não expor os dados da entidade, por isso usase os UsuarioDTO, EnderecoDTO e TelefoneDTO
    //CRIANDO O METODO DE SALVAR OS DADOS DO USUÁRIO.
    public UsuarioDTO salvaUsuario (UsuarioDTO usuarioDTO) {

        emailExiste(usuarioDTO.getEmail()); //Chama o metodo para verificar se já existe o email a excepition.
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));// faz a encripriptação da senha do usuario.
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO); //Esse objeto converte o UsuarioDTO para as Entity
        usuario = usuarioRepository.save(usuario); //Salva a informação no banco de dados de usuario como Entity.
        return usuarioConverter.paraUsuarioDTO(usuario);//retorna a informação convertendo o usario entity para usuarioDTO.

    }

    //Esse metodo verifica se existe 2 e-mails iguais.
    public void emailExiste (String email) {
        try {

            boolean existe =  verificaEmailExistente(email);

            if (existe){
                throw new ConflictException("E-mail já cadastrado." + email);
            }

        }catch (ConflictException e) {
            throw new ConflictException ("E-mail já cadastrado." ,  e.getCause());
        }

    }

    //Esse metodo só faz a chamada do repository
    public boolean verificaEmailExistente (String email) {
        return usuarioRepository.existsByEmail(email);
    }

   //metodo para buscarUsuarioPorEmail
    public Usuario buscarUsuarioPorEmail (String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("E-mail não encontrado. " + email));
    }

    //metodo para deletarUsuarioPorEmail
    public void deletaUsuarioPorEmail (String email) {
        usuarioRepository.deleteByEmail(email);
    }



    //metodo para atualizarDadosDoUsuario sem email e sem telefone.
    public UsuarioDTO atualizaDaddosUsuario (String token, UsuarioDTO usuarioDTO) {

        //Busca o email do usuario através do token (tira a obrigatoriedade de passar o email.) retirando os 7 primeiros caracters.
       String email = jwtUtil.extrairEmailToken(token.substring(7));

       //criptografia de senha.
       usuarioDTO.setSenha(usuarioDTO.getSenha() != null ? passwordEncoder.encode(usuarioDTO.getSenha()): null );

       //Busca os dados do usuario pelo email no banco de dados
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow( () -> new ResourceNotFoundException("E-mail não encontrado. " + email));

        //Usa o metodo upDateUsuario para comparar o email e mesclar os dados da requisição DTO com os dados do bano de dados: nome, email e senha.
        Usuario usuario = usuarioConverter.upDateDeUsuario(usuarioDTO, usuarioEntity);


        //salvou os dados do usuario e depois converteu para UsuarioDTO.
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));


    }


}

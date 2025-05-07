package com.engcode.usuario.controller;

import com.engcode.usuario.business.UsuarioService;
import com.engcode.usuario.business.ViaCepService;
import com.engcode.usuario.business.dto.EnderecoDTO;
import com.engcode.usuario.business.dto.TelefoneDTO;
import com.engcode.usuario.business.dto.UsuarioDTO;
import com.engcode.usuario.infrastructure.clients.ViaCepDTO;
import com.engcode.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario") //coloca qual uri para o banco de dados.
@RequiredArgsConstructor
public class UsuarioController {

    //Cria a dependencia para a classe UsarioService, para usar os metodos que tem nela.
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager; //Classe do spring security
    private final JwtUtil jwtUtil; //importa o metodo do jwtUtil
    private final ViaCepService viaCepService;

    //Cria o metodo salvaUsuario e usa o metodo pra isso da UsuarioService.
    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario (@RequestBody UsuarioDTO usuarioDTO){
       return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }


    /*
     * Usa o ResponseEntity para levar as respostas HTTP para o usuário final.
     */

    /* @ResquestBory
     * Como o objeto usuário tem muitas informações, não faz sentido pegar todas as informações uma a uma, assim usa o
     * @ResquestyBody para pegar todas as informações ao mesmo tempo no objeto usuário.
     */

    //Metodo para fazer o login e retornar o token gerado.

    @PostMapping("/login")
    public String login (@RequestBody UsuarioDTO usuarioDTO){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getSenha()));//Verifica e-mail e senha

        return "Bearer " + jwtUtil.generateToken(authentication.getName()); //Recebe o email e gera um token.
    }

    //Metodo para buscar o usuário por e-mail
    @GetMapping
    public ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail (@RequestParam ("email") String email) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    //Metodo para deletar o usuario por email - Usa Void pq não tem retorno apenas se deu certo ou não.
    @DeleteMapping ("/{email}")
    public ResponseEntity <Void> deletaUsuarioPorEmail (@PathVariable String email) { //OBS o nome dentro das chaves {/email} tem que ser igual ao nome da variável.
        usuarioService.deletaUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    //Metodo para atualizar nome, email e senha
    @PutMapping
    public ResponseEntity<UsuarioDTO>  atualizaDadosDoUsuario (@RequestBody UsuarioDTO usuarioDTO, @RequestHeader ("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizaDaddosUsuario(token, usuarioDTO));
    }

    //Metodo para atualizar dados do endereço.
    @PutMapping ("/endereco")
    public  ResponseEntity<EnderecoDTO> atualizaEndereco (@RequestBody EnderecoDTO enderecoDTO, @RequestParam ("id") Long id) {
        return  ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoDTO));
    }

    //Metodo para atualizar dados do telefone.
    @PutMapping ("/telefone")
    public  ResponseEntity<TelefoneDTO> atualizaTelefone (@RequestBody TelefoneDTO telefoneDTO, @RequestParam ("id") Long id) {
        return  ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneDTO));
    }

    //Metodo para adicionar endereço
    @PostMapping ("/endereco")
    public ResponseEntity<EnderecoDTO> cadastraEndereco (@RequestBody EnderecoDTO enderecoDTO, @RequestHeader ("Authorization") String token) {
        return  ResponseEntity.ok(usuarioService.cadastraEndereco(token, enderecoDTO));
    }

    //Metodo para adicionar telefone
    @PostMapping ("/telefone")
    public ResponseEntity<TelefoneDTO> cadastraTelefone (@RequestBody TelefoneDTO telefoneDTO, @RequestHeader ("Authorization") String token) {
        return  ResponseEntity.ok(usuarioService.cadastraTelefone(token, telefoneDTO));
    }

    //Buscar informações do cep via api externa.
    //Esse cep ele pode ser recebido via parametro ou do jeito que está direto no end point.
    @GetMapping ("/endereco/{cep}")
    public ResponseEntity<ViaCepDTO> buscarDadosCep (@PathVariable ("cep") String cep) {
        return ResponseEntity.ok(viaCepService.buscarDadosEndereco(cep));
    }
}

package com.engcode.usuario.controller;

import com.engcode.usuario.business.UsuarioService;
import com.engcode.usuario.business.dto.UsuarioDTO;
import com.engcode.usuario.infrastructure.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario") //coloca qual uri para o banco de dados.
@RequiredArgsConstructor
public class UsuarioController {

    //Cria a dependencia para a classe UsarioService, para usar os metodos que tem nela.
    private final UsuarioService usuarioService;

    //Cria o metodo salvaUsuario e usa o metodo pra isso da UsuarioService.
    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario (@RequestBody UsuarioDTO usuarioDTO){
       return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

}

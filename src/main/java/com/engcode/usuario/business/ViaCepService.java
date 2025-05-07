package com.engcode.usuario.business;

import com.engcode.usuario.infrastructure.clients.ViaCepClient;
import com.engcode.usuario.infrastructure.clients.ViaCepDTO;
import com.engcode.usuario.infrastructure.exceptions.IllegalArgumentExcepition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient viaCepClient;

    public ViaCepDTO buscarDadosEndereco (String cep) {
        //se o usuario passar o cep com os tracinhos, ele deve conter só números sem espaços.
            return viaCepClient.buscaDadosEndereco(processarCep(cep));
    }

    //metodo para validar o cep digitado, caso seja digitado algo invalido.
    private String processarCep (String cep) {
        //Primeiro limpa o cep
        String cepFormatado = cep.replace(" ", "").replace("-", "").replace(".", "");
        //valida se tem so numeros ou não. \\d+ reprenseta os numeros
        //verifica se o cep tem existe algo diferente de numeros e se o tamanho é menor que 8.

        if(!cepFormatado.matches("\\d+") || !Objects.equals(cepFormatado.length(), 8)){
            throw new IllegalArgumentExcepition("O cep contém caracteres inválidos, favor verificar.");
        } else {
            return cepFormatado;
        }
    }

}

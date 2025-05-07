package com.engcode.usuario.infrastructure.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Coloca o nome e a url que no caso vai ser uma variavél no aplicationProperties
@FeignClient(name = "via-cep-client", url = "${viacep.url}")
public interface ViaCepClient {

    @GetMapping("/ws/{cep}/json/")
    ViaCepDTO buscaDadosEndereco(@PathVariable("cep") String cep);

}

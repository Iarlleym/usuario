package com.engcode.usuario.business.dto;

public class TelefoneDTOFixture {

    public static TelefoneDTO build (

            Long id,
            String numero,
            String ddd
    ) {
        return new TelefoneDTO(id, numero, ddd);
    }
}

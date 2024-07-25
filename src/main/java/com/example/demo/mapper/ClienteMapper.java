package com.example.demo.mapper;

import com.example.demo.domain.Cliente;
import com.example.demo.dto.ClienteDTO;

public class ClienteMapper {
    public static ClienteDTO toDto(Cliente cliente){
        ClienteDTO clienteRta = ClienteDTO.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .build();
        return clienteRta;
    }
}

package com.example.demo.service;

import com.example.demo.domain.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mapper.ClienteMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Service
public class ClienteService {
    public static List<Cliente> listaClientes;

    static {
        listaClientes = new ArrayList<>();
        listaClientes.add(
                new Cliente(1l, "Emmanuel")
        );
        listaClientes.add(
                new Cliente(2l, "Nico")
        );
        listaClientes.add(
                new Cliente(3l, "Juan")
        );
    }

    public ClienteDTO getClienteByName(String nombre) throws NotFoundException {
        String finalNombre = nombre.toLowerCase();


        Optional<Cliente> cliente = listaClientes.stream()
                .filter(x -> x.getNombre().toLowerCase().equals(finalNombre))
                .findFirst();

        if (cliente.isEmpty()) {
            throw new NotFoundException("No se encontró el cliente");
        } else {
            return ClienteMapper.toDto(cliente.get());
        }
    }

    public ClienteDTO saveCliente(ClienteDTO clienteBody) throws BadRequestException {
        if (clienteBody.getNombre().equals("")) {
            throw new BadRequestException("No se admiten vacíos");
        }

        OptionalLong idOpt = listaClientes.stream().mapToLong(Cliente::getId).max();
        Long id;
        if (idOpt.isEmpty()) {
            id = 1l;
        } else {
            id = idOpt.getAsLong() + 1l;
        }
        Cliente clienteCreado = new Cliente(id, clienteBody.getNombre());
        listaClientes.add(clienteCreado);

        return ClienteMapper.toDto(clienteCreado);
    }

}
